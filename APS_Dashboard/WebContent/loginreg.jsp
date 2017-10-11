<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CSO APS | Login</title>
		
	    <!-- LIBRARIES -->
	    <script src="jquery-3.2.1.js"></script>
		
		<!-- STYLES -->
	    <link type="text/css" href="CSS/login_style.css" rel="stylesheet">
	    
		<!-- SCRIPTS -->
		<script>
			function validateLogin () {    
				var username = $("#login_username").val().trim();
				var pw = $("#login_password").val();
				
				if (username == "" || pw == "") {
					$("#login_msg").text("Please fill out all fields.");
					return false;
				} else {
					$('#login_msg').text("");
					return true;
				}
			}
			
			function checkLogin () {
				var username = $("#login_username").val().trim();
				$.ajax({ 
		            type:		'POST', 
		            url: 		'checkUsername',
		            data: 		{user:username},
		            dataType: 	'html',
		            success: 	
			            function (data) {
			            	if(username == "")
			            		$('#login_msg').text("");
			            	
							if (data == "false")
								$('#login_msg').text("The username does not exist!");
		            	},
		        	error: 
			        	function (xhr, status, error) {
			                $('#login_msg').text("");
			            }
		        });
			}
		</script>
	</head>
	<body>
		<!-- IMAGE CONTAINER START -->
		<div id = "imagecontainer">
			<!-- IMAGE DIV START -->
			<div id="imagediv">
		        <img src="res/dlsulogo.png">
		    </div>
		    <!-- IMAGE DIV END -->
		</div>
		<!-- IMAGE CONTAINER END -->
		
		<!-- LOGIN DIV START -->
	    <div id="logindiv">
	    	<!-- LOGIN GROUP START -->
	        <div id="logingroup">
	        	<!-- LOGIN FORM START -->
	            <form id="loginform" action="login" onsubmit = "return validateLogin()" method="POST">
	                <!-- USERNAME START -->
	                <div class="option">
	                    <span class="label"> Username </span> <br>
	                    <input id="login_username" onblur = "checkUsername()" type="text" name="username">
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
	                <input id="btn_login" type="submit" value="LOGIN">
	            </form>
	            <!-- LOGIN FORM END -->
	        </div>
	        <!-- LOGIN GROUP END -->
	    </div>
	    <!-- LOGIN DIV END -->
	</body>
</html>