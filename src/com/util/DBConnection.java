package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.Cart;
import com.bean.Cartitems;
import com.bean.Category;
import com.bean.Order;
import com.bean.Orderitems;
import com.bean.Product;
import com.bean.User;

public class DBConnection {
	
	public static Session getConnection()
	{
		Session session = null;
		SessionFactory sf = null;
		
		try {
			
			sf = new Configuration()
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(Cart.class)
					.addAnnotatedClass(Category.class)
					.addAnnotatedClass(Product.class)
					.addAnnotatedClass(Cartitems.class)
					.configure()
					.buildSessionFactory();
			session = sf.openSession();
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		return session;
	}

}
