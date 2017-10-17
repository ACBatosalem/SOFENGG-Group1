<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<title> Home </title>
		<!-- LIBRARIES -->
		<script src= "libraries/jquery-3.2.1.js"> </script>
		<link href = "libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<!-- STYLES -->
	    <link type = "text/css" href = "styles/dashboard.css" rel = "stylesheet">
		<link type = "text/css" href = "styles/main.css" rel = "stylesheet">
		<link type = "text/css" href = "styles/navigation.css" rel = "stylesheet">
		
		<!-- SCRIPTS -->
		<script src="scripts/navigation.js"> </script>
        
	 </head>
    <body>
        <!-- WEB PAGE BACKGROUND AND OVERLAYS -->
        <div id = "main-bg"> </div>
        <div id = "main-overlay"> </div>
        
        <!-- MAIN START -->
        <div id = "main"> 
            <!-- NAV START -->
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
            <!-- NAV END -->
            
            <!-- MAIN CONTENT START -->
            <div class = "main-content"> 
                <!-- MAIN BUTTON CONTAINER START -->
                <div class = "main-group-container">
                    <!-- GROUP BUTTON CONTAINER START -->
                    <div class = "group-button-container">
                        <!-- INPUT BOXES: ACADS, NON-ACADS START -->
                        <form>
                            <!-- ACADEMIC -->
                            <div class = "options-box">
                                <label id = "acads-grp" class = "container"> 
                                <input id = "acads-opt" type="checkbox" name="view-options">
                                    <span class = "checkmark"> </span>
                                    <span class = "namelabel"> ACADEMIC </span> 
                                </label>
                            </div>
                            <!-- NON ACADEMIC -->
                            <div class = "options-box">
                                <label id = "non-acads-grp" class = "container"> 
                                    <input id = "nonacads-opt" type="checkbox" name="view-options"> 
                                    <span class = "checkmark"> </span>
                                    <span class = "namelabel"> NON-ACADEMIC </span> 
                                </label>
                            </div>
                        </form>
                        <!-- INPUT BOXES END -->
                    </div>
                    <!-- GROUP BUTTON CONTAINER END -->

                </div>
                <!-- MAIN BUTTON CONTAINER END -->
                
                <!-- TAB MAIN START -->
                <div class = "tab-main"> 
                    <!-- TAB OPTIONS START -->
                    <div class = "tab-options">
                        <!-- TAB OPTIONS: Activities, GOSM, Academic -->
                        <button id = "acads-tab-btn" class = "tab-option tab-button"> Academic </button>
                        <button id = "act-tab-btn" class = "tab-option tab-button selected"> Activities </button>
                    </div>
                    <!-- TAB OPTIONS END -->
                    
                    <!-- ACTIVITIES CONTAINER START -->
                    <div id = "act-tab" class = "activites-container-tab">
                        <!-- ACTIVITIES TABLE START -->
                        <table class = "activities">
                            <!-- HEADERS START -->
                            <tr class = "headers"> 
                                <!-- HEADER: TIME STAMP, ORG NAME, TITLE, DATE, STATUS -->
                                <th class = "header timestamp"> Time Stamp </th>
                                <th class = "header org"> Organization Name </th>
                                <th class = "header title"> Title </th>
                                <th class = "header date"> Date </th>
                                <th class = "header status"> Status </th>
                            </tr>
                            <!-- HEADERS END -->
                            <c:forEach items="${dashboard_data}" var="data">
	                            <tr> 
	                                <td>
	                                ${data.timeStamp}
	                                </td>
	                                <td>
	                                 ${data.orgName}
	                                </td>
	                                <td>
	                                 ${data.title}
	                                </td>
	                                <td>
	                                 ${data.date}
	                                </td>
	                                <td>
	                                 ${data.status}
	                                </td>
	                            </tr>
                            </c:forEach>
                        </table>
                        <!-- ACTIVITIES TABLE END -->
                        
                        <!-- ACTIVITIES OPTION START -->
                        <div class = "activities-option">
                            <!-- ACTIVITIES OPTIONS: LEFT, LABEL, RIGHT -->
                            <button class = "activities-options activities-option-button"> 
                                <i class="fa fa-arrow-left" aria-hidden="true"></i> 
                            </button>
                            <label class = "activities-options activities-option-label"> Page 1 of X </label> 
                            <button class = "activities-options activities-option-button"> 
                                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                            </button>
                        </div>
                        <!-- ACTIVITIES OPTION END -->
                    </div>
                    <!-- ACTIVITIES CONTAINER END --> 
                    
                    <!-- ACADEMIC CONTAINER START -->
                    <div id = "acads-tab">

                    
                    </div>
                    <!-- ACADEMIC CONTAINER END -->
                
                </div>
                <!-- TAB MAIN END -->
            </div>
            <!-- MAIN CONTENT END -->
            
            
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
                <a href = "homeORG/changePassword">
                    <button id = "changepass" class = "nav-setting">
                        Change Password
                    </button>
                </a>
                <a href = "logout">
                    <button id = "signout" class = "nav-setting"> Sign Out </button>
                </a>
            </div>
            <!-- ACCOUNT SETTINGS END -->
        </div>
        <!-- MAIN END -->
    </body>
</html>