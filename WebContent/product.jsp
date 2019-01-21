<%@page import="com.dao.ViewDao"%>
<%@page import="java.util.List"%>
<%@page import="com.bean.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="layout/header.jsp" /> 

<% if(request.getAttribute("error") != null){ %>
		<div class="error"><%= (String)request.getAttribute("error") %></div>
<% } %>

<jsp:include page="layout/product-list.jsp" />


<jsp:include page="layout/footer.jsp" />