<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> APS | Statistics </title>
		<link rel="icon" type="image/gif" href="${pageContext.request.contextPath}/resources/logo.png" />
        
		<script> 
         	var context = "${pageContext.request.contextPath}"; 
         	var acad = "${academic}";
         	var nonacad = "${nonacademic}";
        </script>
        <!-- LIBRARIES -->
       	<script src="${pageContext.request.contextPath}/libraries/jquery/jquery-3.2.1.min.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/libraries/chartlist/chartlist.min.js" type="application/javascript"> </script>
        <link type="text/css" href="${pageContext.request.contextPath}/libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel=stylesheet>
        
        <!-- SCRIPTS -->
        <script src="${pageContext.request.contextPath}/scripts/statistics.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/scripts/logout.js" type="application/javascript"> </script>
        
        <!-- STYLES -->
        <link type="text/css" href="${pageContext.request.contextPath}/styles/statistics.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/content.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/navigation.css" rel="stylesheet">
        
</head>
<body>
    <!-- WEB PAGE BACKGROUND AND OVERLAYS -->
    <div id = "main-bg"> </div>
    <div id = "main-overlay"> </div>
    
	<div id = "main">
       	<!-- SIDE BAR NAVIGATION START -->
        <nav id="nav">
            <!-- TOP ITEMS -->
            <div id="top_div">
                <!-- HEADER OVERLAY -->
                <div class = "label-header-overlay"> </div>
                <!-- HEADER IMAGE -->
                <div class = "label-header"> </div>
            
                <!-- HEADER CONTENT START -->
                <div id="header">
                    <!-- LOGIN ICON HOLDER -->
                    <div class = "icon-holder"> </div>
                    <!-- DLSU LOGO -->
                    <img id = "dlsu-logo" src="${pageContext.request.contextPath}/resources/logo.png">
                    <!-- ICON HOLDER OVERLAY -->
                    <div class = "icon-holder-overlay"> </div>
                </div>
                
                <!-- WELCOME MESSAGE -->
                <p class="nav label"> Welcome ${user.userName} </p>
                <!-- SUBMISSIONS BUTTON LINK START -->
                <a class = "nav-link" href="${pageContext.request.contextPath}"> 
                 <button id="submissions" class="nav-item nav-button"> 
                         Submissions
                		<div class = "triangle"> </div>
                 </button>
                </a>
                <!-- SUBMISSIONS BUTTON LINK END -->
                <!-- STATISTICS BUTTON LINK START -->
                <a class = "nav-link" href="${pageContext.request.contextPath}/homeORG/getStatistics"> 
                	<button class = "nav-item nav-button selected"> 
                        Statistics
                    <div class = "triangle"> </div>
                	</button>
                </a>
                <!-- STATISTICS BUTTON LINK END -->
            </div>
            <!-- BOTTOM ITEMS START -->
            <div id="bottom_div">
                <!-- ACCOUNT SETTINGS BUTTON -->
                <a class = "nav-link" href="${pageContext.request.contextPath}/homeORG/accountSettings">
                 <button id="change_pw" class="nav-item nav-button"> 
                         Account Settings
                     <div class = "triangle"> </div>
                 </button>
                </a>
                <!-- SIGN OUT BUTTON -->
                <a class = "nav-link">
                 <button id="signout" class="nav-item nav-button">   
                         Logout	                       
                 </button>
                </a>
            </div>
            <!-- BOTTOM ITEMS END -->
        </nav>
        <!-- SIDE BAR NAVIGATION END -->

		<!-- MAIN CONTENT START -->
	    <div id="main_content">
	        <!-- MAIN HEADER START -->
	        <div id="main_header">
	            <!-- MAIN HEADER TITLE -->
	            <h1 class="body_label head"> Statistics </h1>
	        </div>
	        
	        <div id = "main_body">
	            <div id = "chart"> </div>
	            <div class = "count-main">
	                <div id = "acad" class = "count-container">
	                    <label class = "label value"> ${academic} </label> <br>
	                    <label class = "label afield"> ACADEMIC </label>
	                </div>
	                <div id = "non-acad" class = "count-container"> 
	                    <label class = "label value"> ${nonacademic} </label> <br>
	                    <label class = "label afield"> NON-ACADEMIC </label>
	
	                </div>
	            </div>      
	        </div>
		</div>
		<!-- MAIN CONTENT END -->
    </div>
</body>
</html>