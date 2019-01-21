package com.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.Cart;
import com.bean.User;
import com.util.DBConnection;

public class Auth {

	public static boolean register(User u) {
		boolean isRegister = true;
		u.setUsertype("customer");
		try {
			Session session = DBConnection.getConnection();
			Transaction tr = session.beginTransaction();
			session.save(u);
			tr.commit();
			session.close();
		}catch(Exception e)
		{
			isRegister = false;
			e.printStackTrace();
		}
		return isRegister;
	}

	public static boolean createCart(Cart c) {
		boolean cartCreated = true;
		try {
			Session session = DBConnection.getConnection();
			Transaction tr = session.beginTransaction();
			session.save(c);
			tr.commit();
			session.close();
		}catch(Exception e)
		{
			cartCreated = false;
			e.printStackTrace();
		}
		return cartCreated;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public static User login(User u) {
		Session session = DBConnection.getConnection();
		List users = null;
		try {
			String hql = "from User as u where u.email = :email and u.password = :password";
			Query query = session.createQuery(hql);
			query.setParameter("email", u.getEmail());
			query.setParameter("password", u.getPassword());
			users = query.list();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		if(users.size() > 0)
		{
		return (User)users.get(0);
		}else {
			return null;
		}
	}

	public static boolean isEmailUsed(String email) {
		boolean isEmailUsed = false;
		Session session = DBConnection.getConnection();
		try {
			String hql = "from User as u where u.email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List<User> list = query.list();
			if(list.size() > 0)
			{
				isEmailUsed = true;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isEmailUsed;
	}

	
}
