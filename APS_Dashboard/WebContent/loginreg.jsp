<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Login </title>
		
		<!-- LIBRARIES -->
		<script src= "libraries/jquery-3.2.1.js"> </script>
		<link href = "libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		
		<!-- STYLES -->
	    <link href = "styles/main.css" rel = "stylesheet" type="text/css">
	    <link type="text/css" href="styles/login1.css" rel="stylesheet">
	    
		<!-- SCRIPTS -->
		<script src = "scripts/login.js"> </script>
	</head>
	<body>
		
		<!-- LOGIN DIV START -->
	    <div id="logindiv">
	    	<!-- LOGIN GROUP START -->
	        <div id="logingroup">
                <img class = "dlsulogo" src="resources/dlsulogo.png">
	        	<!-- LOGIN FORM START -->
	            <form id="loginform" action="login" method="POST">
	                <!-- USERNAME START -->
	                <div class="option">
	                    <span class="label"> Username </span> <br>
	                    <input id="login_username" type="text" name="username">
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
	                
                    <button id = "btn_login" type = "submit" value ="LOGIN">
                        <div id = "grp-btn-container">
                            <div id = "grp-btn">
                                <p id = "login_start"> LOGIN </p>
                                <div class = "trim">
                                    <img id = "loading" src ="resources/ajax-loader.gif">
                                </div>
                            </div>
                        </div>
                    </button>
	            </form>
	            <!-- LOGIN FORM END -->
	        </div>
	        <!-- LOGIN GROUP END -->
	    </div>
	    <!-- LOGIN DIV END -->
	</body>
</html>