<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<a href="javascript:history.back()"><button type='submit'>Go Back</button></a> <br>
	${sessionScope.sessionun} <br>
	<form id="changePasswordForm" method="POST">
		Old Password: <input type="password" name="old"> <br>
		New Password: <input type="password" name="new"> <br>
		Retype Password: <input type="password" name="retype"> <br>
		<input id="btn_changePassword" type="submit" value="Change Password">
	</form>
</body>
</html>