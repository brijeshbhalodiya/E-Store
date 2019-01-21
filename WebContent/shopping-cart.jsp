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
						if(session.getAttribute("User")!=null){
							User u = (User)session.getAttribute("User");
							Cart c = u.getUser();
							List<Cartitems> list = ViewDao.getCartItems(c);
							if(!list.isEmpty())
							{
								double totalPrice = 0.0;		
				%>
							<!-- Shoping Cart -->
							<div class="bg0 p-t-75 p-b-85">
								<div class="container">
									<div class="row">
										<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
											<div class="m-l-25 m-r--38 m-lr-0-xl">
												<div class="wrap-table-shopping-cart">
													<table class="table-shopping-cart">
														<tr class="table_head">
															<th class="column-1">Product</th>
															<th class="column-2"></th>
															<th class="column-3">Price</th>
															<th class="column-4">Quantity</th>
															<th class="column-5">Total</th>
															<th class="column-6"></th>
														</tr>
											<%
											for(Cartitems ci: list){
												Product p = ci.getProduct_id();
											%>
														<tr class="table_row">
															<td class="column-1">
																<div class="how-itemcart1">
																	<img src="<%= p.getProduct_image() %>" alt="IMG">
																</div>
															</td>
															<td class="column-2"><%=p.getProduct_name() %></td>
															<td class="column-3">$ <%=p.getPrice() %> </td>
															<td class="column-4"><%=ci.getQuantity() %></td>
															<% double price = ci.getQuantity()*p.getPrice();
																totalPrice = totalPrice + price;
															%>
															<td class="column-5">$ <%=price %></td>
															<td class="column-6">
																<form action="cart" method="POST">
																	<input type="hidden" name="cid" value="<%= ci.getCartitemid() %>">
																	<input type="submit" name="action" value="Remove" class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
																</form>
															</td>
														</tr>
														
													<% } %>
													</table>
												</div>
											</div>
										</div>
						
										<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
											<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
												<h4 class="mtext-109 cl2 p-b-30">
													Cart Totals
												</h4>

						
						
												<div class="flex-w flex-t p-t-27 p-b-33">
													<div class="size-208">
														<span class="mtext-101 cl2">
															Total:
														</span>
													</div>
						
													<div class="size-209 p-t-1">
														<span class="mtext-110 cl2">
															$<%=totalPrice %>
														</span>
													</div>
												</div>
						
												<button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
													Proceed to Checkout
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<%
							}else{
							%>
							<div class="bg0 p-t-75 p-b-85">
								<div class="container">
									<p>No Product available in Cart</p>
								</div>
							</div>
							
							<%
										}
									}else{
							%>
							<div class="bg0 p-t-75 p-b-85">
								<div class="container">
								<p>Login To See Your Cart Detail</p>
									<a href="login" class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
										Login
									</a>
								</div>
							</div>
							<% } %>


<jsp:include page="layout/footer.jsp" /> 