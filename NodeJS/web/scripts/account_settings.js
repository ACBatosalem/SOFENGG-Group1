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
			$("#edit-error").text("");
			
			var nameRegexTest = checkName(name);
			var usernameRegexTest = checkusername(username);
			var emailRegexTest = checkemail(email);
			var contactRegexTest = checkcontact(contact);

			if(username == "" || username == undefined ||
            name == "" || name == undefined ||
            email == "" || email == undefined ||
            contact == "" || contact == undefined) {
				console.log("Please fill out all fields");
				$('#edit-error').text("Please fill out all fields");
				$('.profile').prop('disabled', false);
				$("#editsave").html('Save <i class = "fa fa-save"> </i>');
				edit = false;
			} else if(nameRegexTest != "" || usernameRegexTest != ""
					|| emailRegexTest != "" || contactRegexTest != "") {
					var msg = nameRegexTest;
					if(msg != "" && usernameRegexTest != "")
						msg += "<br>";
					msg += usernameRegexTest;
					if(msg != "" && emailRegexTest != "")
						msg += "<br>";
					msg += emailRegexTest;
					if(msg != "" && contactRegexTest != "")
						msg += "<br>";
					msg += contactRegexTest;
					$('#edit-error').html(msg);
					$('.profile').prop('disabled', false);
					$("#editsave").html('Save <i class = "fa fa-save"> </i>');
					edit = false;
			} else {
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
								edit = false;
							} if(data == "Name, username, contact number, or email already in use.") {
								$('#edit-error').text(data);
							} else {
								$('.profile').prop('disabled', true);
								edit = true;
							}
							
					},		
					error   : function(xhr,status,error){		
						console.log(xhr);   		
						alert(status);		
					}		
				});
			}
		}
	});

	$("#change_pass").click(function(){
		var oldPass = $("#oldpass").val();
		var newPass = $("#newpass").val();
		var cNewPass = $("#cnewpass").val();
		if (oldPass == "" || oldPass == undefined ||
		newPass == "" || newPass == undefined ||
		cNewPass == "" || cNewPass == undefined) {
			$("#error_msg").text("Please fill out all fields.");	
		} else if (cNewPass != newPass) {
			$("#error_msg").text("Passwords do not match!");	
        } else if(checkpassword(newPass) == ""){
			$.ajax({ 		
				type        : 'POST', 		
				url         : 'changePassword',		
				data        : {oldpass:oldPass, newpass:newPass, cnewpass:cNewPass},
				dataType    : 'html',		
				success     : function(data) {
					if (data == "false")
						window.location = context + "/home";
					else 
						$("#error_msg").text(data);		
				},		
				error   : function(xhr,status,error){		
					console.log(xhr);   		
					alert(status);		
				}		
			});
		} else $("#error_msg").text(checkpassword(newPass));	
		$("#oldpass").val("");
		$("#newpass").val("");
		$("#cnewpass").val("");
	});
});
	/*$("#changePasswordForm").submit(function(e){
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
});*/

var nameregex = /^(([A-Z]{1,}?(\s{1}[A-Z]{1,}){0,}))$/;
var usernameregex = /^([a-z]{2,}_[a-z]{2,})$/;
var emailregex = /^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$/;
var contactregex =  /^\d{11}$/;
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

function checkemail(check) {
	if (!emailregex.test(check))
		return "\nEnter a valid email."
	else
		return "";
}
function checkcontact(check) {
	if (!contactregex.test(check))
		return "\nEnter a valid contact number."
	else
		return "";
}

function checkName (check) {
	if(check.length < 7) 
		return "\nName must be 7 characters or more.";
	else if(check.length > 70)
	return "\nName must be 70 characters or less.";
	else if (!nameregex.test(check))
		return "\nName must only contain capital letters."
	else
		return "";
}

function checkusername(check) {
	if(check.length < 5) 
		return "\nUsername must be 5 characters or more.";
	else if(check.length > 30)
		return "\nUsername must be 30 characters or less.";
	else if (!usernameregex.test(check))
		return "\nUsername must only contain small letters and one underscore."
	else
		return "";
}