package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Cart;
import com.bean.User;
import com.dao.Auth;


@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null)
		{
			if(action.equalsIgnoreCase("register"))
			{
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				if(fname!= null && lname != null && email != null && password != null)
				{
					User u = new User(fname,lname,email,password);
					boolean isEmailUsed = Auth.isEmailUsed(email);
					
					if(isEmailUsed)
					{
						request.setAttribute("error", "E-Mail is already registered | Try Another email or Login");
						request.getRequestDispatcher("register.jsp").forward(request, response);
					}
					else
					{
						boolean isRegister = Auth.register(u);
						if(isRegister) {
							Cart c = new Cart();
							c.setUser_id(u);
							boolean cartCreated = Auth.createCart(c);
							if(cartCreated) {
								response.sendRedirect("login.jsp");
							}else {
								request.setAttribute("error", "Please Try Again || Cart Not Created");
								request.getRequestDispatcher("register.jsp").forward(request, response);
							}
						}else {
							request.setAttribute("error", "Please Try Again");
							request.getRequestDispatcher("register.jsp").forward(request, response);
						}
					}
					
				}
				else
				{
					request.setAttribute("error", "All Fields Are Required");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
		}
		else
		{
			response.sendRedirect("register.jsp");
		}
	}

}
