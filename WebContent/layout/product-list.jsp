<%@page import="com.dao.ViewDao"%>
<%@page import="java.util.List"%>
<%@page import="com.bean.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


	<section class="bg0 p-t-23 p-b-140">
		<div class="container">
			<div class="p-b-10 product-overview">
				<h3 class="ltext-103 cl5">
					Product Overview
				</h3>
			</div>

			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter="*">
						All Products
					</button>

					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".women">
						Women
					</button>

					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".men">
						Men
					</button>

					<!-- <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".shoes">
						Shoes
					</button>

					<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".watches">
						Watches
					</button> -->
				</div>
			</div>

			<div class="row isotope-grid">
			<%
				List<Product> list = ViewDao.getAllProduct();
			if(list.size() > 0){
				for(Product p: list){
					Category c = p.getCategory_id();
					if(c.getCategory_name().equalsIgnoreCase("women")){
			
			%>
				<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
				
				<%}else if(c.getCategory_name().equalsIgnoreCase("men")){ %>
				
				<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item men">
				
				<% } %>
					<!-- Block2 -->
					<div class="block2">
						<div class="block2-pic hov-img0">
							<img src="<%=p.getProduct_image() %>" alt="IMG-PRODUCT">

							
						</div>

						<div class="block2-txt flex-w flex-t p-t-14">
							<div class="block2-txt-child1 flex-col-l ">
								<a href="product?id=<%=p.getPid() %>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
									<%= p.getProduct_name() %>
								</a>
								<% 
    	
							    	if(session!= null)
							    	{
							    		if(session.getAttribute("User")!=null)
							    		{
							    			User u = (User)session.getAttribute("User");
							    			if(u.getUsertype().equalsIgnoreCase("admin")){
						    	%>
						    	<div class="admin-form">
						    		<form action="admin" method="POST" class="admin-edit">
						    			<input type="hidden" name="pid" value="<%= p.getPid() %>">
						    			<input type="submit" value="Edit" name="action" class="btn btn-edit">
						    		</form>
						    		<form action="admin" method="POST" class="admin-delete">
						    			<input type="hidden" name="pid" value="<%= p.getPid() %>">
						    			<input type="submit" value="Delete" name="action" class="btn btn-delete" >
						    		</form>
						    	</div>
						    	<% 
						    			}
							    	}
							    }
						    	%>
								<span class="stext-105 cl3">
									$<%= p.getPrice() %>
								</span>
							</div>
						</div>
					</div>
				</div>
				<%
					}
				}else{
				%>
				<h3>No Products Available</h3>
				<%} %>
			</div>

			
		</div>
		</div>
	</section>