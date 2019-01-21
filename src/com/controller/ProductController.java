package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Cart;
import com.bean.Product;
import com.bean.User;
import com.dao.ViewDao;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		if(id != null)
		{
			Product p = ViewDao.findProductById(id);
			if(p != null)
			{
				request.setAttribute("Product", p);
				request.getRequestDispatcher("product-detail.jsp").forward(request, response);
			}else
			{
				request.setAttribute("error", null);
				request.getRequestDispatcher("product-detail.jsp").forward(request, response);
			}
		}else {
			response.sendRedirect("product.html");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

}
