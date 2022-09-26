<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jeans</title>
</head>
<body>
<%
	session.setAttribute("pagename", "jeans");
%>
<h2 style="text-align: center; text-decoration: underline overline;"><%=rb.getString("jeans")%></h2>
<div class="cardcontainer">
		<c:forEach var="product" items="${sessionScope.showproduct}">
  <div class="card">
  <img src="Image-Resources/${product.getImage()}" alt="Product Image" style="width:100%">
  <h3>${product.getName()}</h3>
  <p class="price">₹${product.getPrice()}</p>
  <div class="cardbtngrp"><a href="addtocart.do?id=${product.getId()}&formid=addcart"><button class="btnaddcart"><%=rb.getString("addcart")%></button></a>
  <a href="buynow.do?id=${product.getId()}&formid=buynow"><button class="btnbuy"><%=rb.getString("buy")%></button></a></div>
  </div>
</c:forEach>
</div>
</body>
</html>