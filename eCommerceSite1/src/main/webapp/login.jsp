<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="header3.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home-Login</title>
<style>
h1 {text-align: center;}
</style>
</head>
<body>
<%
	session.setAttribute("pagename", "login");
%>
<h1><%=rb.getString("logtitle")%></h1><br><br>
<form style="margin: auto; padding: 25px; width: 13.5%; border-style: double; background-color: WhiteSmoke;" action="login.do;jsessionid=<%= session.getId() %>" method="post">
		<input type="hidden" name="formid" value="login">
		<%=rb.getString("username")%>:<br><input type="text" name="uname" required><br><br><br>
		<%=rb.getString("password")%>:<br><input type="password" name="upass" required><br><br>
		<input style="margin: 0 0 0 57px" type="submit" value="<%=rb.getString("login")%>">
		</form><br><form style="margin: auto; width: 220px;" action="registerFromlogin.do" method="post">
		<%=rb.getString("logqz")%> <input type="submit" value="<%=rb.getString("register")%>">
		<input type="hidden" name="formid" value="registerFromlogin">
		</form>
</body>
</html>
