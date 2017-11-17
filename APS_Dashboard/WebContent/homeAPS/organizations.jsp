<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<title> APS | Dashboard </title>
		<link rel="icon" type="image/gif" href="${pageContext.request.contextPath}/resources/logo.png" />
        
		<!-- LIBRARIES -->
        <script> var context = "${pageContext.request.contextPath}"; </script>
        <script src="${pageContext.request.contextPath}/libraries/jquery/jquery-3.2.1.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/libraries/datatables/datatables.js" type="application/javascript"> </script>
        <link type="text/css" href="${pageContext.request.contextPath}/libraries/datatables/datatables.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel=stylesheet>
        
        <!-- SCRIPTS -->
        <script src="${pageContext.request.contextPath}/scripts/aps_orgs.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/scripts/logout.js" type="application/javascript"> </script>
        
        <!-- STYLES -->
        <link type="text/css" href="${pageContext.request.contextPath}/styles/content.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/navigation.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/table-styles.css" rel ="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/dashboard-style.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/aps_orgs.css" rel="stylesheet">
	</head>
    <body>
        <!-- BACKGROUNDS AND OVERLAYS -->
        <div id="main-bg"></div>
        <div id="main_overlay"></div>
        
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
	                    <button id="orgs" class="nav-item nav-button selected"> 
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
                <!-- MESSAGE TOAST START -->
                <div id = "add-notif" class = "toast"> 
                    <b id = "username-toast"> Username </b> with password <b id = "password-toast"> password </b> has been added.
                    <button id = "undo-toast">
                        UNDO
                    </button>
                    <button id = "ok-toast"> 
                        OKAY
                    </button>
                </div>
                <!-- MESSAGE TOAST END -->
                <!-- MAIN HEADER START -->
                <div id="main_header">
                    <!-- MAIN HEADER TITLE -->
                    <h1 class="body_label head"> Organizations </h1>
                </div>
                <!-- MAIN HEADER END -->
                <!-- MAIN BODY START -->
                <div id="main_body">
                    <!-- ADD ORG START -->
                    <div id = "add-org-container">
                        <!-- ADD CLOSE BUTTON -->
                        <div id = "add-close">
                            <i class = "fa fa-close close-icon"> </i>
                        </div>
                        <!-- MESSAGE ADD -->
                        <div id = "add-message" class ="label add-label"> 
                            <span>
                                Add Organization
                            </span> 
                        </div>
                        <!-- ADD ORG WITH USER AND BUTTON -->
                        <div id = "add-organization">
                            <input type="text" placeholder = "Username" id = "username">
                            
                            <button id ="add-org-btn" class = "btn"> Add Organization </button>
                            <div id = "add-prompt-message">
                                <i class = "fa fa-question icon-query"> </i>
                                <i class = "fa fa-close close-prompt"> </i>
                                <label> </label>
                                <div id = "add-message-triangle"> </div>
                            </div>
                        </div>
                    </div>
                    <!-- ADD ORG END -->
                    <!-- ORG TABLES CONTAINER START -->
                    <div id = "orgs-table-container">
                        <!-- TABLE ORGANIZATIONS LIST START -->
                        <table id="organizations">
                            <!-- THEAD START -->
                            <thead>
                                <!-- HEADER: TIME STAMP, ORG NAME, TITLE, DATE, STATUS -->
                                <tr class = "header"> 
                                	<th class = "header org"> Organization </th>
                                    <th class = "header pass no-sort"> Password </th>
                                    <th class = "header delete no-sort"> Delete </th>
                                </tr>
                            </thead>
                            <!-- THEAD END -->
                            <!-- TBODY START -->    
                            <tbody id = 'organizations-body'>                                
                                <c:forEach items="${orgs}" var="org">
                                <tr>
                                	<td> ${org.userName} </td>
									<td> ${org.password} </td>
									<td>
										<c:if test = "${org.userName != 'APS'}">
										     <button class="delete-org" data-orgID="${org.id }"> 
										         <i class="fa fa-trash icon"></i> 
										     </button>
									    </c:if>
									</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                            <!-- TBODY END -->
                        </table>
                        <!-- TABLE ORGANIZATIONS LIST END -->
                    </div>
                    <!-- ORG TABLES CONTAINER END -->
                </div>
                <!-- MAIN BODY END -->
            </div>
            <!-- MAIN CONTENT END -->
        </div>
        <!-- MAIN VIEW END -->
    </body>
</html>