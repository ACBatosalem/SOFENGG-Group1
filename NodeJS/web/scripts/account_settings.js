$(document).ready(function(){
    var edit = false;
    $('#editsave').click(function() {
        edit = !edit;
        $('.profile').prop('disabled', !edit);
        if(edit) {
            $(this).html('Save <i class = "fa fa-save"> </i>');
        } else {
            $(this).html('Edit <i class = "fa fa-pencil"> </i>');
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