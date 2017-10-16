$(document).ready(function(){
	$("button.deletebutton").click(function(){
		var id= $(this).attr("data-orgid");
		//alert(id);
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
			},
			error   : function(xhr,status,error){
                console.log(xhr); 
                console.log(error);
                alert(status);
            }
		});
	});
	
	
	$("#addOrgForm").submit(function(e){
		e.preventDefault();
		var name = $("#addOrg_name").val().trim();
		var username = $("#addOrg_username").val().trim();
		
		$("#addOrg_msg").text("");
		
		if (name == "" || username == "")
			$("#addOrg_msg").text("Please fill out all the fields!");
		else {
			$("#addOrg_msg").text($("#addOrg_msg").text() + checkfullname(name)
					+ checkusername(username));
		}
		
		if($("#addOrg_msg").text() == "") {
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
	                console.log(error);
	                alert(status);
	            }
			});
		}
	});
	
    $("#add-org").click(function(){
        if($("#add-org").text() == "Cancel"){
            $("#add-org").text("Add a New User");
            $("#addOrg_name").val("");
            $("#addOrg_username").val("");
        } else {
            $("#add-org").text("Cancel");
        }
        $("#addOrg_msg").text("");
        $("#addOrgForm").slideToggle();
        
    });
	
});

var usernameregex = /^([A-Za-z]{2,20}$)/;
var fullnameregex = /^([A-Za-z]{2,2})([A-Za-z\s]{5,80}$)/;

function checkfullname (check) {
	if(check.length < 7) 
		return "\nName must be 7 characters or more.";
	else if (!fullnameregex.test(check))
		return "\nName must only contain letters."
	else
		return "";
}


function checkusername (check) {
	if(check.length < 2) 
		return "\nUsername must be 2 characters or more.";
	else if (!usernameregex.test(check))
		return "\nUsername must only contain letters."
	else
		return "";
}