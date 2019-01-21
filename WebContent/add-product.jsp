<%@page import="java.util.List"%>
    <%@page import="com.bean.*"%> 
    <%@page import="com.dao.*"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="layout/header.jsp" /> 
				<% 
    	
					    	if(session!= null)
					    	{
					    		if(session.getAttribute("User")!=null)
					    		{
					    			User u = (User)session.getAttribute("User");
					    			if(u.getUsertype().equalsIgnoreCase("admin")){

										List<Category> list = adminDao.getAllCategory();
										if(list.size() <= 0)
										{
				%>
			<main>
			<h1 class="category_error">Please first <a href="add-category.jsp">Add Category</a></h1>
			</main>
			<% }else{ %>

						<% if(request.getAttribute("error") != null){ %>
								<div class="error"><%= (String)request.getAttribute("error") %></div>
						<% } %>
					    <main>
					        <form class="product-form" action="admin" method="POST">
					        <h3>Add Product</h3>
					            <div class="form-control">
					                
					                <input type="text" name="title" id="title" required placeholder="Product Name">
					            </div>
					            <br>
					            <div class="form-control">
					                <input type="text" name="imageUrl" id="imageUrl" required placeholder="Image URL">
					            </div>
					            <br>
					            <div class="form-control">
					                <select name="category" required>
										<option selected disabled value="-1">---Select Category---</option>
								<%
									for(Category c:list)
									{
								%>
										<option value="<%=c.getCategoryid() %>"><%=c.getCategory_name() %></option>
								<%	
									}
								%>
									</select>
					                
					            </div>
					            <br>
					            <div class="form-control">
					                <input type="number" name="price" id="price" step="0.01" required placeholder="Price">
					            </div>
					            <br>
					            <div class="form-control">
					                <input type="number" name="quantity" id="quantity" required placeholder="Quantity">
					            </div>
								<br>
					            <input type="submit" class="btn" name="action" value="Add Product">
					        </form>
					    </main>
					   <% 		}
					   			}
					    		else{ response.sendRedirect("login"); } }
					    		else{ response.sendRedirect("login"); } }
					    		else{ response.sendRedirect("login"); }
					    %>
					    
<jsp:include page="layout/footer.jsp" /> 