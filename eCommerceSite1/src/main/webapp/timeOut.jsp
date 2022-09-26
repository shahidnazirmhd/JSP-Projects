<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header1.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Time out</title>
</head>
<body>
<%
	session.setAttribute("pagename", "timeOut");
%>
<h3><%=rb.getString("timeoutmsg")%></h3>
</body>
</html>