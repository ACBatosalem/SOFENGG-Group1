<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>APS | Dashboard</title>
</head>
<body>
	<script src = "jquery-3.2.1.js">
	<script>
		$(document).onload(function(){
			var sessionun = $("#sessionun").text();
			alert(sessionun);
			console.log(sessionun);
			if (sessionun == "")
				window.location = "loginreg.jsp"
		});
	</script>
	<div id="sessionun">${sessionScope.sessionun}</div>
	Welcome APS!
	<a href="logout"><button type='submit'>Logout</button></a>
	<a href="getAllOrgs"><button type='submit'>Edit Organizations</button></a>
</body>
</html>