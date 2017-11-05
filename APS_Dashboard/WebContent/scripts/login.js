$(document).ready(function(){
	$("#loginform").submit(function(e){
		e.preventDefault();
		var username = $("#login_username").val().trim();		
		var pw = $("#login_password").val();		
		if (username == "" || pw == "") {		
			$("#login_msg").text("Please fill out all fields.");
			return true;
		} else $("#login_msg").text("");		
			$.ajax({ 		
				type        : 'POST', 		
				url         : 'login',		
				data        : {user:username, password:pw},		
				dataType    : 'html',		
		 		success     : function(data) {		
					if (data == "aps")		
						window.location = "homeAPS";		
					else if (data == "org")		
						window.location = "homeORG";		
					else{		
						$("#login_msg").text(data);		
					}		
				},	
				beforeSend	: function (xhr) {
					$('#btn_login').prop('disabled', true);
					$('#loader-ajax').show();
				},
		 		error   	: function(xhr,status,error){		
					console.log(xhr);   		
					alert(status);		
				},	
				complete	: function (xhr,status) {
					$('#btn_login').prop('disabled', false);
					$('#loader-ajax').hide();
				}
			});
			return false;
	});
});