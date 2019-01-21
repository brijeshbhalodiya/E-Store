package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.Auth;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action!=null)
		{
			if(action.equalsIgnoreCase("Log in"))
			{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				if(!(email.trim().equalsIgnoreCase("") || password.trim().equalsIgnoreCase(""))) 
				{
					User u = new User();
					u.setEmail(email);
					u.setPassword(password);
					User user = Auth.login(u);
					if(user!=null) 
					{
						HttpSession session = request.getSession();
						session.setAttribute("User", user);
						response.sendRedirect("index.jsp");
						
					}
					else 
					{
						request.setAttribute("error", "INVALID EMAIL OR PASSWORD");
		    			request.getRequestDispatcher("login.jsp").forward(request, response);
					}
			}
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
		
	}

}
}
