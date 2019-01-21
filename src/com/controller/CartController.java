package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.bean.Cart;
import com.bean.Cartitems;
import com.bean.User;
import com.dao.ViewDao;


@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("User") == null)
		{
			response.sendRedirect("login");
		}else {
			response.sendRedirect("shopping-cart.html");
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("User") == null)
		{
			response.sendRedirect("login");
		}else {
			String action = request.getParameter("action");
			if(action != null)
			{
				if(action.equalsIgnoreCase("Add to Cart")) {
					int pid = Integer.parseInt(request.getParameter("pid"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					int availQuantity = ViewDao.availQuantityById(pid);
					if(availQuantity < quantity)
					{
						request.setAttribute("error", "There is only "+availQuantity+" availble in stock");
						request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);
					}else
					{
						User u = (User) session.getAttribute("User");
						Cart c = u.getUser();
						boolean added = ViewDao.addtoCart(pid,quantity,c);
						if(added)
						{
							response.sendRedirect("shopping-cart.jsp");
						}
					}
				}else if(action.equalsIgnoreCase("remove"))
				{
					int cid = Integer.parseInt(request.getParameter("cid"));
					Cartitems ci = ViewDao.getCartItemsById(cid);
					if(ci != null)
					{
						boolean isDeleted = ViewDao.deleteCartItem(ci);
						if(isDeleted)
						{
							response.sendRedirect("shopping-cart.jsp");
						}else {
							request.setAttribute("error", "Please Try Again");
							request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);
						}
					}else {
						request.setAttribute("error", "Please Try Again");
						request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);
					}
				}
			}else {
				response.sendRedirect("shopping-cart.html");
			}
		}
	}

}
