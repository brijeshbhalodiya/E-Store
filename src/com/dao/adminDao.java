package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Category;
import com.bean.Product;
import com.util.DBConnection;

public class adminDao {

	@SuppressWarnings("unchecked")
	public static List<Category> getAllCategory()
	{
		List<Category> list = null;
		try {
			Session session = DBConnection.getConnection();
			String hql = "from Category";
			list = session.createQuery(hql).list();
			session.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}

	public static boolean isCategoryAvailable(String category) {
		boolean isAvail = false;
		Session session = DBConnection.getConnection();
		try {
			String hql = "from Category where Category_name = :name";
			Query query = session.createQuery(hql);
			query.setParameter("name", category);
			List<Category> list = query.list();
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

	public static boolean addCategory(Category c) {
		boolean status = true;
		Session session = DBConnection.getConnection();
		try
		{
			Transaction tr = session.beginTransaction();
			session.save(c);
			tr.commit();
		}catch(Exception ex) {
			status = false;
			ex.printStackTrace();
		}finally {
			session.clear();
		}
		return status;
	}

	public static Category getCategoryById(int categoryId) {
		Category c = null;
		Session session = DBConnection.getConnection();
		try {
			c = (Category)session.get(Category.class, categoryId);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return c;
	}

	public static boolean addProduct(Product p) {
		boolean isAdded = true;
		Session session = DBConnection.getConnection();
		try {
			Transaction tr = session.beginTransaction();
			session.save(p);
			tr.commit();
		}catch(Exception ex)
		{
			isAdded = false;
			ex.printStackTrace();
		}finally
		{
			session.close();
		}
		return isAdded;
	}

	public static Product findProductById(int pid) {
		Product p =null;
		Session session = DBConnection.getConnection();
		try {
			p = session.get(Product.class, pid);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return p;
	}

	public static boolean updateProduct(Product p) {
		boolean isUpdated = true;
		Session session = DBConnection.getConnection();
		try {
			Transaction tr = session.beginTransaction();
			session.update(p);
			tr.commit();
		}catch(Exception ex)
		{
			isUpdated = false;
			ex.printStackTrace();
		}
		return isUpdated;
	}

	public static boolean deleteProduct(Product p) {
		boolean isDeleted = true;
		Session session = DBConnection.getConnection();
		try {
			Transaction tr = session.beginTransaction();
			session.delete(p);
			tr.commit();
		}catch(Exception ex)
		{
			isDeleted = false;
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isDeleted;
	}

	
}
