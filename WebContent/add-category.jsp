<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bean.*"%> 
    <%@page import="com.dao.*"%> 


<jsp:include page="layout/header.jsp" /> 

					<% 
    	
					    	if(session!= null)
					    	{
					    		if(session.getAttribute("User")!=null)
					    		{
					    			User u = (User)session.getAttribute("User");
					    			if(u.getUsertype().equalsIgnoreCase("admin")){
									 if(request.getAttribute("status") != null){ 
					%>
					
									<div class="status"><%= (String)request.getAttribute("status") %></div>
							<% } %>


					
					    <main>
					        <form class="category-form" action="admin" method="POST">
					        <h3>Add Category</h3>
					            <div class="form-control">
					                <input type="text" name="name" id="title" class="text" required placeholder="Category Name">
					            </div><br>
					
					            <input type="submit" class="btn" id="btn-add" name="action" value="Add Category">
					        </form>
					    </main>
					    
					   <% 
					   			}
					    		else{ response.sendRedirect("login"); } }
					    		else{ response.sendRedirect("login"); } }
					    		else{ response.sendRedirect("login"); }
					    %>
					    
<jsp:include page="layout/footer.jsp" /> 