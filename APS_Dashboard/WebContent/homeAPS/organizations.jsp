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
	    <link href = "../styles/add-organization.css" rel = "stylesheet">
		<link href = "../styles/main.css" rel = "stylesheet">
		
		<!-- SCRIPTS -->
		<script src="../scripts/add-organizations.js"> </script>
	</head>
	<body>
	    <!-- WEB PAGE BACKGROUND AND OVERLAYS -->
	    <div id = "main-bg"> </div>
	    <div id = "main-overlay"> </div>
	    
	    <!-- MAIN VIEW PORT START -->
	    <div id = "main">
	    	<!-- NAV START -->
	        <nav class = "nav">
	            <!--  NAV TITLE LABEL -->
	            <h1 id = "nav-title" class = "nav-item nav-label"> APS TEAM DASHBOARD </h1>
	            <!-- NAV GREETING LABEL -->
	            <h3 class = "nav-item nav-label"> Good Day! ${user.userName} </h3>
	            <!-- NAV SIGN OUT BUTTON -->
	            <a href = "../logout"> 
	            	<button id = "signout" class = "nav-item nav-button"> 
	            		Sign Out 
	            	</button>
	            </a>
	        </nav>
	        <!-- NAV END -->
	        
	        <!-- MAIN CONTENT START -->
	        <div class = "main-content">
	        	<!-- MAIN GROUP CONTAINER START -->
	            <div class = "main-group-container">
	            	<!-- MAIN CONTAINER HEADER -->
	                <div class = "main-container-header">
	                    <!-- NAV TITLE START -->
	                	<p id = "nav-title" class = "nav-item nav-label">
		                	<!-- BACK BUTTON LINK START -->
		                	<a href="/APS_Dashboard/homeAPS">
		                    	<button id = "btn_back" type='submit'> Go Back </button>
		                    </a>
		                    <!-- BACK BUTTON LINK END -->
		                  	Organization List 
		                 </p>
		                 <!-- NAV TITLE END -->
	                </div>
	                <!-- MAIN CONTAINER END -->
	                 
	                <div class = "main-container-content">
	                	<!-- ADD ORG START -->
	                    <button id = "add-org"> 
	                    	Add a New User
	                    </button>
	                    <!-- ADD ORG END -->
	                    
	                    <!-- ADD ORG FORM START -->
	                    <form id="addOrgForm" action="homeAPS/addOrg" method="POST">
	                        <!-- ADD ORG FORM START -->
	                        <!-- Name <input id="addOrg_name" type="text" name="name"><br><br> -->
	                        Username <input id="addOrg_username" type="text" name="username"><br> 
	                        <input id="btn_addorg" type="submit" value="Add Organization">
	                    </form>
	                    <!-- ADD ORG FORM END -->
	                    
	                    <!-- ADD ORG MESSAGE -->
	                    <span id="addOrg_msg"></span>
	                    
	                    <!-- LIST OF ORGANIZATIONS START -->
	                    <div class = "list-organizations">
	                    	<!-- ITERATION OF EACH ORGANIZATION -->
	                        <c:forEach items="${orgs}" var="org">
	                        	<!-- ORG START -->
	                            <div data-orgid="${org.id}">
	                                <!-- DELETE BUTTON FOR ORG -->
	                                <c:if test = "${org.userName != 'APS'}">
	                                	<button class="deletebutton" data-orgid="${org.id }"> 
	                                		Delete 
	                                	</button>
	                                </c:if>
	                                <!-- ORG INFORMATION -->
	                                ${org.userName} <span id = "tabbing"> </span> ${org.password} 
	                            </div>
	                            <!-- ORG END -->
	                        </c:forEach>
	                    </div>
	                    <!-- LIST OF ORGANIZATIONS END -->
	                </div>    
	            </div>
	            <!-- MAIN GROUP CONTAINER END -->
	        </div>
	        <!-- MAIN CONTENT END -->
	    </div>
		<!-- MAIN VIEW PORT START -->
	</body>
</html>