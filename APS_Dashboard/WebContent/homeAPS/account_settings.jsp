<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<title> APS | Account Settings </title>
		<link rel="icon" type="image/gif" href="${pageContext.request.contextPath}/resources/logo.png" />
        
        <!-- LIBRARIES -->
        <script> var context = "${pageContext.request.contextPath}"; </script>
        <link type="text/css" href="${pageContext.request.contextPath}/libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/libraries/jquery/jquery-3.2.1.js" type="application/javascript"> </script>
        
        <!-- SCRIPTS -->
        <script src="${pageContext.request.contextPath}/scripts/logout.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/scripts/account_settings.js" type = "application/javascript"> </script>
       
        <!-- STYLES -->
        <link type="text/css" href="${pageContext.request.contextPath}/styles/content.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/account_settings.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/navigation.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/dashboard-style.css" rel="stylesheet">
    </head>
    <body>
        <!-- BACKGROUNDS AND OVERLAYS -->
        <div id="main-bg"></div>
        <div id="main_overlay"></div>
        
        <!-- MODAL VIEW START -->
        <div id = "modal-view" tabindex="1">
            <!-- MODAL BACKGROUND -->
            <div id = "modal-bg"> </div>
            <!-- MODAL CONTENT START -->
            <div id = "modal-container-outer">
                <button id = "modal-close"> </button>
                <label id = "act-name" class = "modal-label modal-title"> Activity Title </label> <br>
                <label id = "org-name" class = "modal-label modal-title"> Organization Name </label> <br>

                <div id = "modal-container">
                    <div id = "modal-content">
                        <section id = "details">
                            <label class = "modal-label modal-division"> Details </label> <br>
                            <label id = "time" class = "modal-label">Time: </label> <br>
                            <label id = "venue" class = "modal-label"> Venue: </label> <br>
                            <label id = "nature" class = "modal-label"> Nature of Activity: </label> <br>
                            <label id = "type" class = "modal-label"> Type of Activity: </label> <br>
                            <label id = "actDate" class = "modal-label"> Activity Date/s: </label> <br>
                            <label id = "tieup" class = "modal-label"> Tie-up: </label>
                        </section>

                        <div class = "modal-group"> 
                            <section id = "submission">
                                <label class = "modal-label modal-division"> Submission </label> <br> 
                                <label id = "submittedBy" class = "modal-label"> Submitted by: </label> <br> 
                                <label id = "submitDate" class = "modal-label"> Date Submitted: </label> <br>
                                <label id = "typeSAS" class = "modal-label"> Type of SAS Submission: </label> <br>
                            </section>

                            <section id = "checker">
                                <label class = "modal-label modal-division"> Checker </label> <br>
                                <label id = "checkedby" class = "modal-label"> Checked by: </label> <br>
                                <label id = "dateChecked" class = "modal-label"> Date Checked </label> <br>
                                <label id = "remarks" class = "modal-label"> Remarks: </label> <br>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
            <!-- MODAL CONTENT END -->
        </div>
        <!-- MODAL VIEW END -->
        
        <!-- MAIN VIEW START -->
        <div id="main">
        <!-- MAIN VIEW START -->
        <div id="main">
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
                    <a class = "nav-link" href="${pageContext.request.contextPath}/homeAPS/getStatistics"> 
	                   	<button class = "nav-item nav-button"> 
	                           Statistics
	                       <div class = "triangle"> </div>
	                   	</button>
                    </a>
                    <!-- STATISTICS BUTTON LINK END -->
                    <!-- ORGANIZATIONS BUTTON LINK START -->
                    <a class = "nav-link" href="${pageContext.request.contextPath}/homeAPS/getAllOrgs"> 
	                    <button id="orgs" class="nav-item nav-button"> 
	                            Organizations
	                        <div class = "triangle"> </div>
	                    </button>
                    </a>
                    <!-- ORGANIZATIONS BUTTON LINK END -->
                </div>
                <!-- BOTTOM ITEMS START -->
                <div id="bottom_div">
                    <!-- ACCOUNT SETTINGS BUTTON -->
                    <a class = "nav-link" href="${pageContext.request.contextPath}/homeAPS/accountSettings">
	                    <button id="change_pw" class="nav-item nav-button selected"> 
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
                    <h1 class="body_label head"> Account Settings </h1>
                </div>
                <!-- MAIN BODY START -->
                <div id="main_body">
                    <div id = "sheetsopt" class = "option">
                        <label id = "sheetsTile" class = "title"> Google Sheets </label>
                        <div class = "label"> Edit the link of the target Google spreadsheet here: </div>
                        <div class = "label"> <b></b> </div>
                        <div class = "label editlabel"> 
                            <input type = 'text' disabled class = "disabled-input" id = "setting-sheets" class = "field-set" value = "sample@dlsu.edu.ph">
                            <button class = "edit-btn" data-input = "setting-sheets" data-button="edit-sheets">
                               <i id = "edit-sheets" class = "fa fa-pencil"> </i>
                            </button>
                        </div>
                    </div>
                    <div id = "passwordforgot" class = "option">
                        <label id ="emailtitle" class = "title"> Email Configuration </label>
                        <div class = "label"> In case <b> password</b> is forgotten current password will be sent here: </div>
                        <div class = "label editlabel">
                            <input type = 'email' disabled class = "disabled-input" id = "setting-email" class = "field-set" value = "sample@dlsu.edu.ph">
                            <button class = "edit-btn" data-input = "setting-email" data-button="edit-email">
                               <i id = "edit-email" class = "fa fa-pencil"> </i>
                            </button>
                        </div>
                    </div>
                    
                    <div class = "option">
                        <form name = "change-pass" class = "option" id="change-pass-form" action="changePassword" method="POST">
                            <label class = "title"> Change Password </label>
                            <p class="label"> Old Password </p>
                            <input class="field" type="password" name="oldpass">
                            <p class="label"> New Password </p>
                            <input class="field" type="password" name="newpass">
                            <p class="label"> Confirm New Password </p>
                            <input class="field" type="password" name="cnewpass">
                            <p class="label" id="error_msg"> </p>
                            <button id="change_pass" type="submit"> Change Password </button>
                        </form>
                    </div>
                </div>
                <!-- MAIN BODY END -->
            </div>
            <!-- MAIN CONTENT END -->
        </div>
        <!-- MAIN VIEW END -->
    </body>
</html>