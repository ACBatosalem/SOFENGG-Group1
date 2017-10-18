<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>Organizations </title>
		
		<!-- LIBRARIES -->
		<script src= "../libraries/jquery-3.2.1.js"> </script>
		<link href = "../libraries/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<!-- STYLES -->
	    <link type = "text/css" href = "../styles/add-organization.css" rel = "stylesheet">
		<link type = "text/css" href = "../styles/navigation.css" rel = "stylesheet">
		
		<!-- SCRIPTS -->
		<script src="../scripts/add-organizations.js"> </script>
		<script src="../scripts/navigation.js"> </script>
	</head>
	<body>
	    <!-- WEB PAGE BACKGROUND AND OVERLAYS -->
	    <div id = "main-bg"> </div>
	    <div id = "main-overlay"> </div>
	    
	    <!-- MAIN VIEW PORT START -->
	    <div id = "main">
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
            
            <!-- NAV START -->
            <nav class = "nav">
                <!--  NAV TITLE LABEL -->
                <h1 id = "nav-title" class = "nav-item nav-label"> APS TEAM DASHBOARD </h1>
                <!-- NAV GREETING LABEL -->
                <h3 class = "nav-item nav-label"> Good Day! ${user.userName} </h3>
                <!-- NAV SIGN OUT BUTTON -->
                
                <button class = "nav-item nav-button" id = "user-settings">
                    <i class = "fa fa-user-circle-o">
                    
                    </i>
                </button>    
            </nav>
            <!-- NAV END -->
	        
	        <!-- MAIN CONTENT START -->
	        <div class = "main-content">
				<div class = "main-group-container">
					<div class = "main-container-header">
						<p id = "nav-title" class = "nav-item nav-label">
						<a id = "btn_link" href="/APS_Dashboard/homeAPS">
							<button id = "btn_back" type='submit'>
								<i id="link_back" class="fa fa-arrow-left" aria-hidden="true"></i>
                            </button>
                        </a>
						Organization List </p>
					 </div>
					<div class = "main-container-content">
						<button id = "add-org"> Add a New User</button>
						<form id="addOrgForm" action="homeAPS/addOrg" method="POST">
							Username <input id="addOrg_username" type="text" name="username"> <span id="addOrg_msg"></span>
							<br>
							<input id="btn_addorg" type="submit" value="Add Organization">
						</form>
						<div class= "list-organization">
                            <table class="tbl_list">
                                    <tr class = "headers"> 
                                        <!-- HEADER: TIME STAMP, ORG NAME, TITLE, DATE, STATUS -->
                                        <th class="header empty">  </th>
                                        <th class = "header username"> Username </th>
                                        <th class = "header password"> Password </th>
                                    </tr>
                            </table>
                         </div> 
						<div id = "tbl_container">  
						<div class = "list-organizations">
                            
                                <table class="tbl_list">
                                    <!-- HEADERS START -->
                                    <tr class = "headers"> 
                                        <!-- HEADER: TIME STAMP, ORG NAME, TITLE, DATE, STATUS -->
                                        <th class="header empty">  </th>
                                        <th class = "header username"> </th>
                                        <th class = "header password"> </th>
                                    </tr>
                                    <!-- HEADERS END -->
                                    <c:forEach items="${orgs}" var="org">
                                        <tr data-orgid="${org.id}">
                                            <td>
                                            	<c:if test = "${org.userName != 'APS'}">
	                                                <button class="deletebutton" data-orgid="${org.id }"> 
	                                                    <i class="fa fa-trash-o" aria-hidden="true"></i> 
	                                                </button>
                                                </c:if>
                                            </td>
                                            <td>
                                            ${org.userName}
                                            </td>
                                            <td>
                                            ${org.password}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
						</div>    
					</div>    
				</div>
			</div>
	        <!-- MAIN CONTENT END -->
	    </div>
		<!-- MAIN VIEW PORT START -->
	</body>
</html>