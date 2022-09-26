<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header3.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
h1 {text-align: center;}
</style>
</head>
<body>
<%
	session.setAttribute("pagename", "register");
%>
<h1><%=rb.getString("regtitle")%></h1><br><br>
		<form style="margin: auto; padding: 20px; width: 13.5%; border-style: double;  background-color: MistyRose;" action="register.do;jsessionid=<%= session.getId() %>" method="post">
		<input type="hidden" name="formid" value="register">
		    <%=rb.getString("fname")%>:<br><input type="text" name="fullname" required><br><br>
		    <%=rb.getString("username")%>:<br><input type="text" name="uname" required><br><br>
		     <%=rb.getString("password")%>:<br><input type="password" name="upass" required><br><br>
		         <%=rb.getString("city")%>:<br><input type="text" name="city" required><br><br>
		    <%=rb.getString("mnum")%>:<br><input type="number" name="mobileNum" required><br><br>
		<input style="margin: 0 0 0 52px" type="submit" value="<%=rb.getString("register")%>"><br>
		</form><br><form style="margin: auto; width: 220px;" action="loginFromregister.do" method="post">
		<%=rb.getString("regqz")%> <input type="submit" value="<%=rb.getString("login")%>">
		<input type="hidden" name="formid" value="loginFromregister">
		</form>
</body>
</html>