<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> APS | Submissions </title>
		<link rel="icon" type="image/gif" href="${pageContext.request.contextPath}/resources/logo.png" />
        
		<!-- LIBRARIES -->
		<script> var context = "${pageContext.request.contextPath}"; </script>
        <script src="${pageContext.request.contextPath}/libraries/jquery/jquery-3.2.1.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/libraries/datatables/datatables.js" type="application/javascript"> </script>
        <link type="text/css" href="${pageContext.request.contextPath}/libraries/datatables/datatables.css" rel="stylesheet">
      	<link type="text/css" href="${pageContext.request.contextPath}/libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel=stylesheet>
        
        <!-- SCRIPTS -->
        <script src="${pageContext.request.contextPath}/scripts/dashboard.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/scripts/logout.js" type="application/javascript"> </script>
        <script src="${pageContext.request.contextPath}/scripts/notification.js" type="application/javascript"> </script>
        
        <!-- STYLES -->
        <link type="text/css" href="${pageContext.request.contextPath}/styles/content.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/navigation.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/table-styles.css" rel ="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/styles/dashboard-style.css" rel="stylesheet">
	</head>
    <body>
        <!-- BACKGROUNDS AND OVERLAYS -->
        <div id="main-bg"></div>
        <div id="main_overlay"></div>
        
        <!-- MAIN VIEW START -->
        <div id="main">
	        <!-- MODAL VIEW START -->
	        <div id = "modal-view" tabindex="1">
	            <!-- MODAL BACKGROUND -->
	            <div id = "modal-bg"> </div>
	            <!-- MODAL CONTENT START -->
	            <div id = "modal-container-outer">
	                <button id = "modal-close"> </button>
	                
	                <div id = "modal-container">
	                    <div id = "modal-content">
	                    	<label id = "act-name" class = "modal-label modal-title"> Activity Title </label> <br>
	                		<label id = "org-name" class = "modal-label modal-title"> Organization Name </label> <br>
	
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
	                 <button id="submissions selected" class="nav-item nav-button"> 
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
                <!-- MAIN HEADER START -->
                <div id="main_header">
                    <!-- MAIN HEADER TITLE -->
                    <h1 class="body_label head"> Submissions </h1>
                </div>
                <!-- MAIN HEADER END -->
                <!-- MAIN BODY START -->
                <div id="main_body">
                    <div id = "table-container">
                        <!-- TABLE SUBMISSIONS START -->
                        <table id="table_submissions">
                            <thead>
                                <!-- HEADER: TIME STAMP, ORG NAME, TITLE, DATE, STATUS -->
                                <tr class = "header"> 
                                    <th class = "header timestamp"> Timestamp </th>
                                    <th class = "header org"> Organization </th>
                                    <th class = "header title"> Title </th>
                                    <th class = "header status"> Status </th>
                                </tr>
                            </thead>

                            <!-- DATA: SUBMISSIONS -->    
                            <tbody>
                                <c:forEach items="${dashboard_data}" var="data">
	                            <tr data-docuID = "${data.docuID}" data-orgID = "${data.orgID}" data-actID = "${data.actID}"
	                                data-subID = "${data.subID}" data-checkID = "${data.checkID}"> 
	                                <td> ${data.timeStamp} </td>
	                                <td> ${data.orgName} </td>
	                                <td> ${data.title}  </td>
		                            <c:choose>
		                                <c:when test = "${data.status eq 'EARLY APPROVED'}"> <td class = "early_approved"> ${data.status} </td> </c:when>
		                                <c:when test = "${data.status eq 'LATE APPROVED'}"> <td class = "late_approved"> ${data.status} </td> </c:when>
		                                <c:when test = "${data.status eq 'PENDING'}"> <td class = "pending"> ${data.status} </td> </c:when>
		                                <c:when test = "${data.status eq 'DENIED'}"> <td class = "denied"> ${data.status} </td> </c:when>
		                                <c:otherwise> <td> ${data.status} </td> </c:otherwise>
	                                </c:choose>
	                            </tr>
                            	</c:forEach>
                            </tbody>
                            <tfoot>

                            </tfoot>
                        </table>
                    </div>
                    <!-- TABLE SUBMISSION LIST -->
                </div>
                <!-- MAIN BODY END -->
            </div>
            <!-- MAIN CONTENT END -->
            
             <!-- NOTIFICATIONS START -->
            <div id = "notifications">
                <div id = "notif-btn">
                    <i class = "fa fa-bell-o notif_icon"> </i>
                    <div class = "badge1" data-badge="10" id = "notif-count"> </div>
                    <div class = "notif-label"> NOTIFICATIONS </div>                    
                </div>
                <ol id = "notifs-list">
                    <li class = "unread">
                        <div class = "act-title"> Unread Activity </div> 
                        <div class = "act-details"> Details have been updated. A long notification this is. </div>
                        <div class = "act-timelapsed"> x minutes ago </div>
                    </li>
                    <li>
                        <div class = "act-title"> Read Activity </div> 
                        <div class = "act-details"> Details have been updated. A long notification this is. </div>
                        <div class = "act-timelapsed"> x minutes ago </div>
                    </li>
                </ol>
            </div>
            <!-- NOTIFICATIONS END -->
        </div>
        <!-- MAIN VIEW END -->
    </body>
</html>