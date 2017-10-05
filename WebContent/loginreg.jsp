<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CSO APS | Login</title>
<script src="jquery-3.2.1.js"></script>
</head>
<body>
	<form id="loginform" action="login" method="POST">
		Email: <input type="email" name="email">
		<br>
		Password: <input type="password" name="password">
		<br><bR>
		<input id="btn_login" type="submit" value="LOGIN">
	</form>
	<br><br>
	<form id="registerform" action="register" method="POST">
		Email: <input type="email" name="email">
		<br>
		Password: <input type="password" name="password">
		<br>
		Retype Password: <input type="password" name="retype">
		<br><br>
		<input id="btn_register" type="submit" value="SUBMIT">
	</form>
</body>
</html>