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
		Email: <input id="login_email" type="email" name="email" onchange="checkEmailForLogin()"> <span id="login_email_msg"></span>
		<br>
		Password: <input id="login_password" type="password" name="password"> <span id="login_password_msg"></span>
		<br><br>
		<input id="btn_login" type="submit" value="LOGIN">
	</form>
	<span id="login_msg"></span>
	
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
	
	<p id="register_msg"></p>
	
	<script>
	var ching = 10;
	
	function checkBlanksForLogin() {
		var email = $("#login_email").val();
		var password = $("#login_password").val();
		email = email.trim();
		password = password.trim();
		if (email == "" || password == "") {
			$("#login_msg").text("Please fill out all fields.");
			return true;
		} else $("#login_msg").text("");
		return false;
	}
	
	function checkEmailForLogin() {
		//do the regex?
		console.log("Hellooo");
		var ajaxdata = $("#login_email").val();
		$.ajax({
            "method"   : "post",
            "url"	   : "ajaxLoginEmail/" + ajaxdata,
            "dataType" : "json",
            "success"  : function(data){
            	ching = data;
            	console.log(data);
            	if (data.validity == "valid")
  					$("#login_email_msg").val("This email is valid.");
            	else $("#login_email_msg").val("Invalid email!");
            }
		});
	}
	
	function validateLogin ()
	{    
		checkBlanksForLogin();
		//checkEmailForLogin
		//checkPasswordForLogin
		
		/* var email = $("#login_email").val();
		var password = $("#login_password").val();
		var ajaxdata = "email=" + email + "&password=" + password;
	    $.ajax({
	                type     : "POST",
	                url		 : "login/" + ajaxdata,
	                success  : function(data){ 
	      				
	                },
	                async    :    false,
	    }); */
	
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