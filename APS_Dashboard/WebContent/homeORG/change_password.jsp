<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<script src="../JavaScript/jquery-3.2.1.js"></script>
<script src="../JavaScript/ChangePassword.js"></script>
</head>
<body>
	<a href="javascript:history.back()"><button type='submit'>Go Back</button></a> <br>
	${sessionScope.sessionun} <br>
	<form id="changePasswordForm" method="POST">
		Old Password: <input id="old_pw" type="password" name="old"> <br>
		New Password: <input id="new_pw" type="password" name="new"> <br>
		Retype Password: <input id="retype_pw" type="password" name="retype"> <br>
		<input id="btn_changePassword" type="submit" value="Change Password">
	</form>
	<p id="changePW_msg"></p>
</body>
</html>