$(document).ready(function(){
	$("#changePasswordForm").submit(function(e){
		e.preventDefault();
		
		var oldPW = $("#old_pw").val(); 
		var newPW = $("#new_pw").val(); 
		var retypePW = $("#retype_pw").val();
		
		if (oldPW == "" || newPW == "" || retypePW == "") {
			$("#changePW_msg").text("Please fill out all fields.");
		} else if (newPW != retypePW) {
			$("#changePW_msg").text("Passwords do not match! Please make sure you retyped your new password correctly.");
		} else {
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
		}
		
	});
});