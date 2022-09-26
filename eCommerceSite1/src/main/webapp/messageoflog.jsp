<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="login.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
h3 {text-align: center;color: red;}
h3.green {text-align: center;color: green;}
</style>
</head>
<body>
<%
	session.setAttribute("pagename", "messageoflog");
%>
<c:set var="message" value="${sessionScope.message}"/>  
<c:if test="${message == \"alreadylog\"}">  
   <h3><%=rb.getString("alreadylog")%></h3>  
</c:if>  
<c:if test="${message == \"credentialswrong\"}">  
   <h3><%=rb.getString("creditwrong")%></h3>  
</c:if>  
<c:if test="${message == \"registersuccess\"}">  
   <h3 class="green"><%=rb.getString("regsuccess")%></h3>  
</c:if>
</body>
</html>