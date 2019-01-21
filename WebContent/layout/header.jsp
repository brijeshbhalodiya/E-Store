    <%@page import="com.dao.ViewDao"%>
	<%@page import="java.util.List"%>
    <%@page import="com.bean.*"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Home</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/custom.css">
<!--===============================================================================================-->

</head>
<body class="animsition">
	
	<!-- Header -->
	<header>
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<!-- Topbar -->
			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">

					<div class="right-top-bar flex-w h-full">
						
						<% 
    	
					    	if(session!= null)
					    	{
					    		if(session.getAttribute("User")!=null)
					    		{
					    			User u = (User)session.getAttribute("User");
					    			if(u.getUsertype().equalsIgnoreCase("admin")){
					    %>
							<a href="add-product.jsp" class="flex-c-m trans-04 p-lr-25">
								Add Product
							</a>
							<a href="add-category.jsp" class="flex-c-m trans-04 p-lr-25">
								Add Category
							</a>
							
							<% } %>
							
							<a href="logout" class="flex-c-m trans-04 p-lr-25">
								Logout
							</a>

						<% }else { %>
							<a href="login.jsp" class="flex-c-m trans-04 p-lr-25">
								Sign In
							</a>
							<a href="register.jsp" class="flex-c-m trans-04 p-lr-25">
								Sign Up
							</a>
						
						<%	
								} 
					    	} 
					    %>
					</div>
				</div>
			</div>

			<div class="wrap-menu-desktop">
				<nav class="limiter-menu-desktop container">
					
					<!-- Logo desktop -->		
					<a href="index.jsp" class="logo">
						<img src="images/icons/logo-01.png" alt="IMG-LOGO">
					</a>
	
					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li>
								<a href="index.jsp">Home</a>
							</li>

							<li>
								<a href="product.jsp">Products</a>
							</li>

							<li>
								<a href="shopping-cart.jsp">Shopping Cart</a>
							</li>
						</ul>
					</div>

					<!-- Icon header -->
					<div class="wrap-icon-header flex-w flex-r-m">
						<%
							if(session.getAttribute("User")!=null){
							User u = (User)session.getAttribute("User");
							Cart c = u.getUser();
							List<Cartitems> list = ViewDao.getCartItems(c);
							if(list.size() > 0){
						%>
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="<%=list.size() %>">
							<i class="zmdi zmdi-shopping-cart"></i>
						</div>
						<% } else { %>
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="0">
							<i class="zmdi zmdi-shopping-cart"></i>
						</div>
						<% } }else { %>
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="0">
							<i class="zmdi zmdi-shopping-cart"></i>
						</div>
						<% } %>

					</div>
				</nav>
			</div>	
		</div>
		
		<!-- Header Mobile -->
		<div class="wrap-header-mobile">
			<!-- Logo moblie -->		
			<div class="logo-mobile">
				<a href="index.html"><img src="images/icons/logo-01.png" alt="IMG-LOGO"></a>
			</div>


			<!-- Button show menu -->
			<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
			</div>
		</div>


		<!-- Menu Mobile -->
		<div class="menu-mobile">
			<ul class="topbar-mobile">


				<li>
					<div class="right-top-bar flex-w h-full">
					<% 
    	
					    	if(session!= null)
					    	{
					    		if(session.getAttribute("User")!=null)
					    		{
					    			User u = (User)session.getAttribute("User");
					    			if(u.getUsertype().equalsIgnoreCase("admin")){
					    %>
					    

							<a href="add-product.jsp" class="flex-c-m trans-04 p-lr-10">
								Add Product
							</a>
							<a href="add-category.jsp" class="flex-c-m trans-04 p-lr-10">
								Add Category
							</a>
							
							<% } %>
							
							<a href="logout" class="flex-c-m trans-04 p-lr-10">
								Logout
							</a>

						<% }else { %>
							<a href="login.jsp" class="flex-c-m trans-04 p-lr-10">
								Sign In
							</a>
							<a href="register.jsp" class="flex-c-m trans-04 p-lr-10">
								Sign Up
							</a>
						
						<%	
								} 
					    	} 
					    %>
					</div>
				</li>
			</ul>

			<ul class="main-menu-m">
				<li>
					<a href="index.jsp">Home</a>

				</li>

				<li>
					<a href="product.jsp">Products</a>
				</li>

				<li>
					<a href="shopping-cart.jsp">Shopping Cart</a>
				</li>

			</ul>
		</div>
		
		
	</header>
	<!-- Cart -->
	<div class="wrap-header-cart js-panel-cart">
		<div class="s-full js-hide-cart"></div>

		<div class="header-cart flex-col-l p-l-65 p-r-25">
			<div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Your Cart
				</span>

				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>
			
			<div class="header-cart-content flex-w js-pscroll">
				<ul class="header-cart-wrapitem w-full">
				<% 
						if(session.getAttribute("User")!=null){
							User u = (User)session.getAttribute("User");
							Cart c = u.getUser();
							List<Cartitems> list = ViewDao.getCartItems(c);
							if(!list.isEmpty())
							{
								double totalPrice = 0.0;	
								for(Cartitems ci: list){
									Product p = ci.getProduct_id();
							
				%>
					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="<%= p.getProduct_image() %>" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="product?id=<%=p.getPid() %>" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								<%= p.getProduct_name() %>
							</a>

							<span class="header-cart-item-info">
								<%= ci.getQuantity() %> x <%= p.getPrice() %>
							</span>
						</div>
					</li>
					
					<% 
									int quantity = ci.getQuantity();
									double productprice = p.getPrice();
									totalPrice = totalPrice + ((quantity)*(productprice));
								}
					%>

				</ul>
				
				<div class="w-full">
					<div class="header-cart-total w-full p-tb-40">
						Total: $<%= totalPrice %>
					</div>

					<div class="header-cart-buttons flex-w w-full">
						<a href="shopping-cart.jsp" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
							View Cart
						</a>
					</div>
				</div>
				<%
							}else{
				%>
				<p>No Product available in Cart</p>
				
				<%
							}
						}else{
				%>
				<p>Login To See Your Cart Detail</p>
				<div class="header-cart-buttons flex-w w-full">
					<a href="login" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
						Login
					</a>
				</div>
				<% } %>
			</div>
		</div>
	</div>
