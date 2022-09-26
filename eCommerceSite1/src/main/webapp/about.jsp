<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
</head>
<body>
<%
	session.setAttribute("pagename", "about");
%>
<div style=" margin: auto; width: fit-content;">
  <h3><%=rb.getString("aboutmsg1")%></h3>
  <h5><%=rb.getString("aboutmsg2")%></h5>
<h3><%=rb.getString("aboutmsg3")%></h3></div>
</body>
</html>