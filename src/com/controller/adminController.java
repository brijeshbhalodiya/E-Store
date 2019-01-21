package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Category;
import com.bean.Product;
import com.dao.adminDao;

@WebServlet("/admin")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}


	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null)
		{
			if(action.equalsIgnoreCase("Add Category"))
			{
				String category = request.getParameter("name");
				category = category.toUpperCase();
				boolean isAvail = adminDao.isCategoryAvailable(category);
				if(isAvail)
				{
					request.setAttribute("status", "Category is already Available");
					request.getRequestDispatcher("add-category.jsp").forward(request, response);
				}
				else
				{
					Category c = new Category();
					c.setCategory_name(category);
					boolean status = adminDao.addCategory(c);
					if(status)
					{
						request.setAttribute("status", "Category Added");
						request.getRequestDispatcher("add-category.jsp").forward(request, response);
					}
					else {
						request.setAttribute("status", "Please Try Again");
						request.getRequestDispatcher("add-category.jsp").forward(request, response);
					}
				}
			}
			else if(action.equalsIgnoreCase("Add Product"))
			{
				String title = request.getParameter("title");
				String imageUrl = request.getParameter("imageUrl");
				int categoryId = Integer.parseInt(request.getParameter("category"));
				double price = Double.parseDouble(request.getParameter("price"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				
				if(categoryId == -1 && title == null && imageUrl == null)
				{
					request.setAttribute("error", "Please fill all the field");
					request.getRequestDispatcher("add-product.jsp").forward(request, response);
				}else
				{
					Product p = new Product();
					p.setProduct_name(title);
					p.setProduct_image(imageUrl);
					p.setPrice(price);
					p.setQuantity(quantity);
					Category c = adminDao.getCategoryById(categoryId);
					if(c==null) {
						request.setAttribute("error", "Please Try Again");
						request.getRequestDispatcher("add-product.jsp").forward(request, response);
					}else {
						p.setCategory_id(c);
						boolean isAdded = adminDao.addProduct(p);
						if(isAdded)
						{
							response.sendRedirect("index.jsp");
						}else {
							request.setAttribute("error", "ERROR | Product is not added");
							request.getRequestDispatcher("add-product.jsp").forward(request, response);
						}
					}
					
				}
			}else if(action.equalsIgnoreCase("edit"))
			{
				int pid = Integer.parseInt(request.getParameter("pid"));
				Product p = adminDao.findProductById(pid);
				if(p!=null)
				{
					request.setAttribute("Product", p);
					request.getRequestDispatcher("edit-product.jsp").forward(request, response);
				}else {
					response.sendRedirect("product.jsp");
				}
			}else if(action.equalsIgnoreCase("Update Product"))
			{
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				String title = request.getParameter("title");
				String imageUrl = request.getParameter("imageUrl");
				int categoryId = Integer.parseInt(request.getParameter("category"));
				double price = Double.parseDouble(request.getParameter("price"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				
				if(pid == null && title == null && imageUrl == null) {
					response.sendRedirect("product.jsp");
				}
				else 
				{
					Product p = new Product();
					p.setProduct_name(title);
					p.setProduct_image(imageUrl);
					p.setPrice(price);
					p.setQuantity(quantity);
					p.setPid(pid);
					Category c = adminDao.getCategoryById(categoryId);
					if(c==null) {
						request.setAttribute("error", "Please Try Again");
						request.getRequestDispatcher("edit-product.jsp").forward(request, response);
					}else {
						p.setCategory_id(c);
						boolean isUpdated = adminDao.updateProduct(p);
						if(isUpdated)
						{
							response.sendRedirect("product.jsp");
						}else {
							request.setAttribute("error", "ERROR | Product is not Updated");
							request.getRequestDispatcher("edit-product.jsp").forward(request, response);
						}
					}
					
				}
				
			}else if(action.equalsIgnoreCase("delete"))
			{
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				if(pid != null)
				{
					Product p = adminDao.findProductById(pid);
					if(p!=null)
					{
						boolean isDeleted = adminDao.deleteProduct(p);
						if(isDeleted)
						{
							response.sendRedirect("product.jsp");
						}else {
							request.setAttribute("error", "Product is not deleted | Please Try Again");
							request.getRequestDispatcher("product.jsp").forward(request, response);
						}
					}else {
						response.sendRedirect("product.jsp");
					}
					
				}else {
					response.sendRedirect("product.jsp");
				}
			}
		}
		else
		{
			response.sendRedirect("index.jsp");
		}
	}

}
