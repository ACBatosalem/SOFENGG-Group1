$(document).ready(function(){
    $("#loginform").submit(function(e){
		e.preventDefault();
		var username = $("#login_username").val();		
		var password = $("#login_password").val();		
		if (username == "" || password == "") {		
			$("#login_msg").text("Please fill out all fields.");
			return false;
		} else $("#login_msg").text("");		
			$.ajax({ 		
				type        : 'POST', 		
				url         : 'login',		
				data        : {username:username, password:password},		
				dataType    : 'html',		
		 		success     : function(data) {		
					if (data == "aps" || data == "org") {
						window.location = data;		
					}
					else {		
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
			return true;
	}); 
});