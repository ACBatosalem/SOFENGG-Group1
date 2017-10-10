<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Organizations</title>
	<script src="jquery-3.2.1.js"></script>
	
	<script>
		$(document).ready(function(){
			
			// whenever a button is clicked
			$("button.deletebutton").click(function(){
				// get that button's id
				var id= $(this).attr("data-orgid");
				
				$.ajax({
					"url" : "deleteOrg",
					"method" : "POST",
					"success" : function(result){
						
						if (result){
							$("div[data-orgid=" + id + "]").remove();
						}
							
						else {
							alert("Error has occured while deleting student");
						}
					},
					"data" : {
						"id" : id
					}
				});
			});
			
			$("button.updatebutton").click(function(){
				// get that button's id
				var id= $(this).attr("data-orgid");
				
				window.location = "getOrg?id="+id;
			});
			
			$("#addOrgForm").submit(function(e){
				e.preventDefault();
				var name = $("#addOrg_name").val().trim();
				var username = $("#addOrg_username").val().trim();
				if (name == "" || username == "")
					$("#addOrg_msg").text("Please lang. Fill out all the fields!");
				else if (name.length <= 5)
					$("#addOrg_msg").text("Name must be 6 characters or more.");
				else if (username.length < 2)
					$("#addOrg_msg").text("Username must be 2 characters or more.");
				else {
					$("#addOrg_msg").text("");
					$.ajax({
						type        : 'POST', 
			            url         : 'addOrg',
			            data        : {name:name, username:username},
			            dataType    : 'html',
			            success     : function(data) {
			            	if (data == "added"){
			            		window.location = "getAllOrgs";
			            	}
			            	else{
			            		$("#addOrg_msg").text(data);
			            	}
			            },
			            error   : function(xhr,status,error){
			                console.log(xhr);   
			                alert(status);
			            }
					});
				}
			});
			
			
		});
	</script>
</head>
<body>
	<a href="home_aps.jsp"><button type='submit'>Go Back</button></a>
	Add Organization:
	<form id="addOrgForm" action="addOrg" method="POST">
		Name <input id="addOrg_name" type="text" name="name"><br>
		Username <input id="addOrg_username" type="text" name="username"><br>
		<input id="btn_addorg" type="submit" value="Add Organization"><br>
	</form>
	<span id="addOrg_msg"></span>
	<br>
	<c:forEach items="${orgs}" var="s">
		<div data-orgid="${s.id }">
			Name : ${s.name } <br>
			Username :${s.userName } <br>
			Password : ${s.password } <br>
			<button class="deletebutton" data-orgid="${s.id }"> Delete </button>
			<button class="updatebutton" data-orgid="${s.id }"> Update </button>
			<br>
			<br>
		</div>
	</c:forEach>
	

</body>
</html>