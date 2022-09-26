<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="register.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
h3 {text-align: center;color: red;}
</style>
</head>
<body>
<%
	session.setAttribute("pagename", "messageofreg");
%>
<c:set var="message" value="${sessionScope.message}"/>  
<c:if test="${message == 2}">  
   <h3><%=rb.getString("unamealexist")%></h3>  
</c:if>  
<c:if test="${message == 3}">  
   <h3><%=rb.getString("alaccount")%></h3>  
</c:if> 
<c:if test="${message == 4}">  
   <h3><%=rb.getString("regfail")%></h3>  
</c:if>   
</body>
</html>