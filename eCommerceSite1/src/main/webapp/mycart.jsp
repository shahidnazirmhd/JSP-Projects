<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
</head>
<body>
<%
	session.setAttribute("pagename", "mycart");
%>
<h2 style="text-align: center; text-decoration: underline overline;"><%=rb.getString("mycart")%></h2>
<div class="tablecontainer">
<div class="chekdiv"><h3 class="totdis"><%=rb.getString("totpri")%>: ₹ ${sessionScope.totalprice}</h3><a class="chkout" href="cartcheckout.do?formid=checkout"><Button class="checkoutbtn"><%=rb.getString("ckotbtn")%></Button></a></div>
	<table class="carttable">
			<thead>
				<tr>
					<th scope="col"><%=rb.getString("name")%></th>
					<th scope="col"><%=rb.getString("category")%></th>
					<th scope="col"><%=rb.getString("price")%></th>
					<th scope="col"><%=rb.getString("chagqua")%></th>
					<th scope="col"><%=rb.getString("buynow")%></th>
					<th scope="col"><%=rb.getString("remove")%></th>
				</tr>
			</thead>
			<tbody>
		<c:forEach var="product" items="${sessionScope.showcart}">
  <tr>
					<td>${product.getName()}</td>
					<td>${product.getCategory()}</td>
					<td>${product.getPrice()}</td>
					<td>
							<div>
								<a  href="quantityincdec.do?formid=quantityinc&id=${product.getId()}"><button>+</button></a>
								<input type="text" name="quantity" size="3" class="form-control"  value="${product.getQuantity()}" readonly> 
								<a  href="quantityincdec.do?formid=quantitydec&id=${product.getId()}"><button>-</button></a>
							</div>
					</td>
					<td><form action="order.do" method="post" id="buyfrmcart">
						<input type="hidden" name="id" value="${product.getId()}">
						<input type="hidden" name="formid" value="carttoorder">
						<button class="buybtnincartlist" type="submit"><%=rb.getString("buy")%></button></form></td>
					<td><form action="order.do" method="post">
						<input type="hidden" name="id" value="${product.getId()}">
						<input type="hidden" name="formid" value="removefromcart">
						<button class="rmvbtncartlist" type="submit"><%=rb.getString("rmvlist")%></button></form></td>
				</tr>
</c:forEach>
</tbody>
		</table>
</div>
</body>
</html>