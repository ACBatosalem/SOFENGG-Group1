$(document).ready(function(){
    var edit = true;
	
	$('#editsave').click(function() {
        if(edit) {
			edit = false;
			$(this).html('Save <i class = "fa fa-save"> </i>');
			$('.profile').prop('disabled', false);
        } else {
			$(this).html('Edit <i class = "fa fa-pencil"> </i>');
			
			var name = $(".name").val();
			var username = $(".username").val();
			var email = $(".email").val();
			var contact = $(".contact").val();

			$.ajax({ 		
				type        : 'POST', 		
				url         : 'editUser',		
				data        : {name:name, username:username, email:email, contact:contact},
				dataType    : 'html',		
		 		success     : function(data) {
						 console.log(data);
						 //show message if meron error like missing fields
						 if (data != "true") {
							$('.profile').prop('disabled', false);
							$("#editsave").html('Save <i class = "fa fa-save"> </i>');
							edit = true;
						 } else {
							$('.profile').prop('disabled', true);
							edit = false;
						 }
						 
				},		
		 		error   : function(xhr,status,error){		
					console.log(xhr);   		
					alert(status);		
				}		
			});
        }
	});
    
	$("#changePasswordForm").submit(function(e){
		e.preventDefault();
		
		var oldPW = $("#old_pw").val(); 
		var newPW = $("#new_pw").val(); 
		var retypePW = $("#retype_pw").val();
		
		$("#old_pw").val("");
		$("#new_pw").val("");
		$("#retype_pw").val("");
		
		if (oldPW == "" || newPW == "" || retypePW == "") {
			$("#changePW_msg").text("Please fill out all fields.");
		} else if (newPW != retypePW) {
			$("#changePW_msg").text("Passwords do not match! Please make sure you retyped your new password correctly.");
		} else if (checkpassword(newPW) == "") {
			$("#changePW_msg").text("");
			$.ajax({ 		
				type        : 'POST', 		
				url         : 'updatePassword',		
				data        : {old_pw:oldPW, new_pw:newPW, retype_pw:retypePW},
				dataType    : 'html',		
		 		success     : function(data) {		
						$("#changePW_msg").text(data);
				},		
		 		error   : function(xhr,status,error){		
					console.log(xhr);   		
					alert(status);		
				}		
			});
		} else {
			$("#changePW_msg").text(checkpassword(newPW));
		}
		
	});
});


var passwordregex = /^([A-Za-z0-9]{6,25}$)/;

function checkpassword(password) {
	console.log(passwordregex.test(password));
	if(password.length < 6) {
		return "Password must be 6 characters or more";
	} else if(password.length > 25) {
		return "Password must be 25 characters or less";
	} else if (!passwordregex.test(password)) {
		return "Password must only contain alphanumeric values";
	} else {
		return "";
	}
}