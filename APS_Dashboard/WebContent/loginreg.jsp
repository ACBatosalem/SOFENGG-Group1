<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title> Login </title>
        <link rel="icon" type="image/gif" href="${pageContext.request.contextPath}/resources/logo.png" />
        
        <!-- LIBRARIES -->
        <script src="${pageContext.request.contextPath}/libraries/jquery/jquery-3.2.1.js" type="application/javascript"> </script>
        
        <!-- STYLES -->
    	<link type="text/css" href="${pageContext.request.contextPath}/styles/login-style.css" rel="stylesheet"/>
    	<link type="text/css" href="${pageContext.request.contextPath}/styles/dashboard-style.css" rel="stylesheet"/>
    	
    	<!-- SCRIPTS -->
    	<script src="${pageContext.request.contextPath}/scripts/modal.js" type = "application/javascript"> </script>
    	<script src="${pageContext.request.contextPath}/scripts/login.js" type = "application/javascript"> </script>
    </head>
    <body>
        <!-- MAIN BG -->
        <div id="main_bg"></div>
        <!-- MAIN OVERLAY -->
        <div id="main_overlay"></div>
        
        <!-- MAIN LOGIN CONTAINER START -->
        <div id="login_container">
            <!-- LOGIN MAIN DIV START -->
            <div class = "login-main">
                <!-- HEADER OVERLAY -->
                <div class = "label-header-overlay"> </div>
                <!-- HEADER IMAGE -->
                <div class = "label-header"> </div>
                
                <!-- LOGIN CONTENT START -->
                <div id="login_content">
                    <!-- LOGIN ICON HOLDER -->
                    <div class = "icon-holder"> </div>
                    <!-- DLSU LOGO -->
                    <img id = "dlsu-logo" src="resources/logo.png"/>
                    <!-- ICON HOLDER OVERLAY -->
                    <div class = "icon-holder-overlay"> </div>
                    <!-- FORM LOGIN START -->
                    <form id="loginform" action="login" method="post">
                        <!-- LABEL HEADER -->
                        <p class="label header"> Welcome Back Lasallian! </p>
                        
                        <!-- USERNAME -->
                        <p class="label"> Username </p>
                        <input id = "login_username" class="login username" type="text" name="username"/>
                        
                        <!-- PASSWORD -->
                        <p class="label"> Password </p>
                        <input id = "login_password" class="login password" type="password" name="password"/>
                        
                        <!-- ERROR MESSAGE -->
                        <p class="label" id="login_msg"> </p>
                        
                        <!-- LOGIN MESSAGE -->
                        <button id="btn_login" type="submit" value="LOGIN"> 
                        	Login 
                        	<img id="loader-ajax" src = "resources/ajax-loader.gif"/>
                        </button>
                        <br/>
                        <!-- CHANGE PASSWORD LINK -->
                        <a class="label" id="forgot_msg"> Forgot Password </a>
                    </form>
                    <!-- FORM LOGIN END -->
                    
					<!-- FORM LOGIN START -->
                    <form id="forgotpassword" action="forgotPassword" method="post">
                        <!-- LABEL HEADER -->
                        <p class="label header"> Forgot password? </p>
                        
                        <!-- EMAIL -->
                        <p class="label"> Do you want to send an email to sample@dlsu.edu.ph? </p>
                        
                        <!-- LOGIN MESSAGE -->
                        <button class = "btn" id="btn_forgot" type="submit"> Yes </button>
                        <button class = "btn" id="btn_back"> Cancel </button>
                    </form>
                    <!-- FORM LOGIN END -->
                </div>
                <!-- LOGIN CONTENT END -->
            </div>
            <!-- LOGIN MAIN DIV END -->
        </div>
        <!-- MAIN LOGIN CONTAINER END -->
    </body>
</html>