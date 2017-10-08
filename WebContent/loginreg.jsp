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
		Username: <input id="login_username" type="text" name="username">
		<br>
		Password: <input id="login_password" type="password" name="password"> 
		<br><br>
		<input id="btn_login" type="submit" value="LOGIN">
	</form>
	<span id="login_msg"></span>
	
	<br><br>
	
	<form id="registerform" action="register" method="POST">
		Username: <input type="text" name="username">
		<br>
		Password: <input type="password" name="password">
		<br>
		Retype Password: <input type="password" name="retype">
		<br><br>
		<input id="btn_register" type="submit" value="SUBMIT">
	</form>
	
	<p id="register_msg"></p>
	
	<script>
	
	function validateLogin ()
	{    
		var username = $("#login_username").val().trim();
		var pw = $("#login_password").val();
		if (username == "" || pw == "") {
			$("#login_msg").text("Please fill out all fields.");
			return true;
		} else $("#login_msg").text("");
		
		$.ajax({ 
            type        : 'POST', 
            url         : 'login',
            data        : {user:username, password:pw},
            dataType    : 'html',
            success     : function(data) {
            	if (data == "aps")
            		window.location = "home_aps.jsp";
            	else if (data == "org")
            		window.location = "home_org.jsp";
            	else
            		$("#login_msg").text(data);
            },
            error   : function(xhr,status,error){
                console.log(xhr);   
                alert(status);
            }
        });
	
	}
	
	$(document).ready(function(){
		$("#loginform").submit(function(e){
			e.preventDefault();
			validateLogin();
		});
	});

	</script>
</body>
</html>