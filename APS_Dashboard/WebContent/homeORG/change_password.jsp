<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title> Change Password </title>
	
	<!-- LIBRARIES -->
	<script src= "../libraries/jquery-3.2.1.js"> </script>
	<link href = "../libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
   	<!-- STYLES -->
    <link href = "../styles/change-password.css" rel = "stylesheet">
	<link href = "../styles/navigation.css" rel = "stylesheet">
	
	<!-- SCRIPTS -->
	<script src="../scripts/change-password.js"> </script>
    <script src="../scripts/navigation.js"> </script>

</head>
<body>
    <!-- WEB PAGE BACKGROUND AND OVERLAYS -->
    <div id = "main-bg"> </div>
    <div id = "main-overlay"> </div>
    
    <div id = "main">
    	<div id = "notification-panel">
	       	<div id = "notification-list">
	            <div class = "notification-item">
	                Thesis Orientation 1 - Early Approved
	            </div>
	            <div class = "notification-item">
	                Thesis Orientation 2 - Early Approved
	            </div>
	            <div class = "notification-item">
	                Thesis Orientation 3 - Early Approved
	            </div>
	            <div class = "notification-item">
	                Internship Orientation 1 - Early Approved
	            </div>
	            <div class = "notification-item">
	                Internship Orientation 2 - Early Approved
	            </div>
	        </div>
	    </div>
	    
	    <!-- ACCOUNT SETTINGS START -->
	    <div id = "account-settings">
	        <a href = "changePassword">
	            <button id = "changepass" class = "nav-setting">
	                Change Password
	            </button>
	        </a>
	        <a href = "../logout">
	            <button id = "signout" class = "nav-setting"> Sign Out </button>
	        </a>
	    </div>
	    <!-- ACCOUNT SETTINGS END -->
		<!-- NAV CONTENT START -->
        <nav class = "nav">
			<!--  NAV TITLE LABEL -->
            <h1 id = "nav-title" class = "nav-item nav-label"> APS TEAM DASHBOARD </h1>
            <!-- NAV GREETING LABEL -->
            <h3 class = "nav-item nav-label"> Good Day! ${user.userName} </h3>
            <button class = "nav-item nav-button" id = "user-settings">
                <i class = "fa fa-user-circle-o">
                
                </i>
            </button>
            <button id = "notif" class = "nav-item nav-button">
                <i class = "fa fa-bell">
                </i>
            </button>
		</nav>
		<!-- NAV CONTENT END -->
		
        <!-- MAIN CONTENT START -->
        <div class = "main-content">
			<!-- MAIN GROUP CONTAINER START -->
            <div class = "main-group-container">
				<!-- MAIN CONTAINER HEADER START -->
                <div class = "main-container-header">
					<!-- NAV TITLE START -->
                    <p id = "nav-title" class = "nav-item nav-label">
						<!-- BACK BUTTON START -->
						<a id="btn_link" href="/APS_Dashboard/homeORG">
							<button id = "btn_back" type='submit'>
								<i id="link_back" class="fa fa-arrow-left" aria-hidden="true"></i>
							</button>
						</a>
						<!-- BACK BUTTON END -->
						${user.userName} 
					</p>
					<!-- NAV TITLE END -->
                </div>
				<!-- MAIN CONTAINER HEADER END -->
				
				<!-- MAIN CONTAINER CONTENT START -->
				<div class = "main-container-content">
					<!-- CHANGE PASSWORD FORM START -->
					<form id="changePasswordForm" method="POST">
						<!-- OLD PASSWORD -->
						<span> Old Password </span> <input id="old_pw" type="password" name="old"> <br>
						<!-- NEW PASSWORD -->
						<span> New Password </span> <input id="new_pw" type="password" name="new"> <br>
						<!-- RETYPE PASSWORD -->
						<span> Retype Password </span> <input id="retype_pw" type="password" name="retype"> <br>
						<!-- SUBMIT BUTTON PASSWORD -->
						<input id="btn_changePassword" type="submit" value="Change Password">
					</form>
					<!-- CHANGE PASSWORD FORM END -->
					<p id="changePW_msg"></p>
                </div>       
				<!-- MAIN CONTAINER CONTENT END -->
            </div>
			<!-- MAIN GROUP CONTAINER END -->
        </div>    
		<!-- MAIN CONTENT END -->
    </div>
</body>
</html>