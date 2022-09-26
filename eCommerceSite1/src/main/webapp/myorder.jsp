<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Order</title>
</head>
<body>
<%
	session.setAttribute("pagename", "myorder");
%>
<h2 style="text-align: center; text-decoration: underline overline;"><%=rb.getString("myorder")%></h2>
<div class="tablecontainer">
	<table class="carttable">
			<thead>
				<tr>
					<th scope="col"><%=rb.getString("date")%></th>
					<th scope="col"><%=rb.getString("name")%></th>
					<th scope="col"><%=rb.getString("category")%></th>
					<th scope="col"><%=rb.getString("quantity")%></th>
					<th scope="col"><%=rb.getString("price")%></th>
					<th scope="col"><%=rb.getString("tocancel")%></th>
				</tr>
			</thead>
			<tbody>
		<c:forEach var="product" items="${sessionScope.showorder}">
  <tr>
					<td>${product.getDate()}</td>
					<td>${product.getName()}</td>
					<td>${product.getCategory()}</td>
					<td>${product.getQuantity()}</td>
					<td>${product.getPrice()}</td>
					<td>
					<form action="cancelorder.do" method="post">
						<input type="hidden" name="id" value="${product.getOrderID()}">
						<input type="hidden" name="formid" value="cancelorder">
						<button class="rmvbtncartlist" type="submit"><%=rb.getString("cancel")%></button></form></td>
				</tr>
</c:forEach>
</tbody>
		</table>
</div>
</body>
</html>