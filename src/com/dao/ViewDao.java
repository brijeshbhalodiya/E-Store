package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Cart;
import com.bean.Cartitems;
import com.bean.Product;
import com.util.DBConnection;

public class ViewDao {
	
	@SuppressWarnings("unchecked")
	public static List<Product> getAllProduct()
	{
		List<Product> list = null;
		Session session = DBConnection.getConnection();
		try {
			list = session.createQuery("from Product as p where p.quantity > 0 ").list();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public static Product findProductById(Integer id) {
		Product p = null;
		Session session = DBConnection.getConnection();
		try {
			p = (Product)session.get(Product.class, id);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return p;
	}

	public static int availQuantityById(int pid) {
		int quantity;
		Product p= findProductById(pid);
		quantity = p.getQuantity();
		return quantity;
	}

	public static boolean addtoCart(int pid, int quantity, Cart c) {
		boolean added = true;
		Product p = findProductById(pid);
		Cartitems ci = new Cartitems();
		ci.setProduct_id(p);
		ci.setQuantity(quantity);
		ci.setCart_id(c);
		Session session = DBConnection.getConnection();
		try {
			Transaction tr = session.beginTransaction();
			session.save(ci);
			tr.commit();
			reduceQuantity(p,quantity);
		}catch(Exception ex)
		{
			added = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return added;
	}

	public static void reduceQuantity(Product p, int quantity) {
		
		Session session = DBConnection.getConnection();
		int availQuantity = p.getQuantity() - quantity;
		p.setQuantity(availQuantity);
		try {
			Transaction tr = session.beginTransaction();
			session.update(p);
			tr.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isAvailInCart(Cart c,Product p) {
		boolean isAvail = false;
		Session session = DBConnection.getConnection();
		try {
			String hql = "from Cartitems as ci where ci.cart_id = :cart_id and ci.product_id = :pid";
			Query query = session.createQuery(hql);
			query.setParameter("cart_id", c);
			query.setParameter("pid", p);
			List<Cartitems> list = query.list();
			if(list.size() > 0)
			{
				isAvail = true;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}

		return isAvail;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Cartitems> getCartItems(Cart c)
	{
		List<Cartitems> list = null;
		Session session = DBConnection.getConnection();
		try {
			String hql = "from Cartitems as ci where ci.cart_id = :cart_id";
			Query query = session.createQuery(hql);
			query.setParameter("cart_id", c);
			list = query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}

	public static Cartitems getCartItemsById(int cid) {
		Cartitems ci =null;
		Session session = DBConnection.getConnection();
		try {
			ci = (Cartitems)session.get(Cartitems.class, cid);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return ci;
	}

	public static boolean deleteCartItem(Cartitems ci) {
		boolean isDeleted = true;
		Session session = DBConnection.getConnection();
		try {
			boolean quantityAdded = increaseQuantity(ci);
			if(quantityAdded)
			{
				Transaction tr = session.beginTransaction();
				session.delete(ci);
				tr.commit();
			}else {
				isDeleted = false;
			}
		}catch(Exception ex)
		{
			isDeleted = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isDeleted;
	}
	

	public static boolean increaseQuantity(Cartitems ci) {
		boolean isUpdated = true;
		Session session = DBConnection.getConnection();
		try {
			Product p = ci.getProduct_id();
			int quantityInStock = p.getQuantity();
			int newQuantity = quantityInStock + ci.getQuantity();
			p.setQuantity(newQuantity);
			Transaction tr = session.beginTransaction();
			session.update(p);
			tr.commit();
		}catch(Exception ex)
		{
			isUpdated = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isUpdated;
	}
	
	
}
