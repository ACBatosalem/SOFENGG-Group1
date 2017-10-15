<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> CSO APS | Login </title>
		
		<!-- LIBRARIES -->
		<script src= "libraries/jquery-3.2.1.js"> </script>
		<link href = "libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<link href = "styles/main.css" rel = "stylesheet" type="text/css">
		
		<!-- STYLES -->
	    <link type="text/css" href="styles/login.css" rel="stylesheet">
	    
		<!-- SCRIPTS -->
		<script src = "scripts/login.js"> </script>
	</head>
	<body>
		<!-- IMAGE CONTAINER START -->
		<div id = "imagecontainer">
			<!-- IMAGE DIV START -->
			<div id="imagediv">
		        <img src="resources/dlsulogo.png">
		    </div>
		    <!-- IMAGE DIV END -->
		</div>
		<!-- IMAGE CONTAINER END -->
		
		<!-- LOGIN DIV START -->
	    <div id="logindiv">
	    	<!-- LOGIN GROUP START -->
	        <div id="logingroup">
	        	<!-- LOGIN FORM START -->
	            <form id="loginform" action="login" method="POST">
	                <!-- USERNAME START -->
	                <div class="option">
	                    <span class="label"> Username </span> <br>
	                    <input id="login_username"  type="text" name="username">
	                </div>
	                <br>
	                <!-- USERNAME END -->
	                
	                <!-- PASSWORD START -->
	                <div class="option" id="password">
	                    <span class="label"> Password </span> <br>
	                    <input id="login_password" type="password" name="password"> 
	                    <br>
	                </div>
	                <!-- PASSWORD END -->
	                
	                <!-- LOGIN MESSAGE -->
	                <p id="login_msg"> </p>
	                <input class = "btn" id="btn_login" type="submit" value="LOGIN">
	            </form>
	            <!-- LOGIN FORM END -->
	        </div>
	        <!-- LOGIN GROUP END -->
	    </div>
	    <!-- LOGIN DIV END -->
	</body>
</html>