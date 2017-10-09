<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
</head>
<body>
	<script src="jquery-3.2.1.js"></script>
	<script>
		$(document).ready(function(){
			$("#updateOrgForm").submit(function(e){
				e.preventDefault();
				var id = $("#org_id").val();
				var name = $("#updateOrg_name").val().trim();
				var alias = $("#updateOrg_alias").val().trim();
				if (name == "" || alias == "")
					$("#updateOrg_msg").text("Please lang. Fill out all the fields!");
				else if (name.length <= 5)
					$("#updateOrg_msg").text("Name must be 6 characters or more.");
				else if (alias.length < 2)
					$("#updateOrg_msg").text("Alias must be 2 characters or more.");
				else {
					$("#updateOrg_msg").text("");
					$.ajax({
						type        : 'POST', 
			            url         : 'updateOrg',
			            data        : {id:id, name:name, alias:alias},
			            dataType    : 'html',
			            success     : function(data) {
			            	if (data == "updated"){
			            		window.location = "getAllOrgs";
			            	}
			            	else{
			            		$("#updateOrg_msg").text(data);
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
	<a href="getAllOrgs"><button type='submit'>Go Back</button></a>
	Current Org:
	<form id="updateOrgForm" action="updateOrg" method="POST" id="updateOrg">
		<input id="org_id" type="hidden" value="${org.id }" name="id">
		Name <input id="updateOrg_name" type="text" name="name" value="${org.name}"><br>
		Alias <input id="updateOrg_alias" type="text" name="alias" value="${org.alias}"><br>
		<input id="btn_updateOrg" type="submit" value="Update Organization"><br>
	</form>
	<span id="updateOrg_msg"></span>
</body>
</html>