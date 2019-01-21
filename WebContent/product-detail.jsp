<%@page import="java.util.List"%>
<%@page import="com.dao.ViewDao"%>
<%@page import="com.bean.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="layout/header.jsp" />


<% if(request.getAttribute("error") != null){ %>
		<div class="error"><%= (String)request.getAttribute("error") %></div>
<% } %>	
		
<% 
Product p = null; 
p = (Product)request.getAttribute("Product");

if(p != null){
	
%>
	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

							<div class="slick3 gallery-lb">
								<div class="item-slick3" data-thumb="<%= p.getProduct_image() %>">
									<div class="wrap-pic-w pos-relative">
										<img src="<%= p.getProduct_image() %>" alt="IMG-PRODUCT">

										<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="<%= p.getProduct_image() %>">
											<i class="fa fa-expand"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
					
				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<h4 class="mtext-105 cl2 js-name-detail p-b-14">
							<%= p.getProduct_name() %>
						</h4>

						<span class="mtext-106 cl2">
							$<%= p.getPrice() %>
						</span>

						
						<!--  -->
						<div class="p-t-33">
						
						<% 
						if(session.getAttribute("User")!=null){
							User u = (User)session.getAttribute("User");
							if(u.getUsertype().equalsIgnoreCase("customer")){
							Cart c = u.getUser();
							boolean isAvailInCart = ViewDao.isAvailInCart(c,p);
							if(isAvailInCart)
							{
						%>
						<a href="shopping-cart.jsp" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">Go To Cart</a>
						<% }else{ %>
						<form action="cart" method="POST">
						
							<div class="flex-w flex-r-m p-b-10">
								<div class="size-204 flex-w flex-m respon6-next">
									<div class="wrap-num-product flex-w m-r-20 m-tb-10">
									
									<input type="hidden" name="pid" value="<%= p.getPid() %>">
									<!-- Qauntity -->
										<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
											<i class="fs-16 zmdi zmdi-minus"></i>
										</div>

										<input class="mtext-104 cl3 txt-center num-product" type="number" name="quantity" value="1">
										
										<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
											<i class="fs-16 zmdi zmdi-plus"></i>
										</div>
										
										
									</div>
									<input type="submit" value="Add to Cart" name="action" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">

								</div>
							</div>
							
						</form>	
						
						<%} }else if(u.getUsertype().equalsIgnoreCase("admin")){ %>
						
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
						
						<%}}else { %>
							<p>You have to Logged In To Add Product In Cart</p>
							<a href="login" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">Login</a>
						<% } %>
							</div>
						<!--  -->

					</div>
				</div>
	</section>
	
<% }else{ response.sendRedirect("product.jsp"); } %>


	

<jsp:include page="layout/footer.jsp" /> 