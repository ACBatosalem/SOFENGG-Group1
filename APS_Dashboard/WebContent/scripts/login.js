$(document).ready(function(){
	$("#loginform").submit(function(e){
		e.preventDefault();
		var username = $("#login_username").val();		
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
	
   $('#forgot_msg').on('click', function(){
	      $('#loginform').fadeOut(function() {
	            $('#forgotpassword').fadeIn(); 
	      });
	   });
	    
	    $('#btn_back').on('click', function(e){
	        e.preventDefault();
	        $('#forgotpassword').fadeOut(function() {
	            $('#loginform').fadeIn(); 
	        });
	    });
	    
	    // set errors here
	    $('forgot_msg_error').html();
	    
	    $('#btn_forgot').on('click', function(e){
	        e.preventDefault();
	        modalMessage("An email has been sent to sample@email.com. ", 
	                        null, 
	                        null, 
	                        "OK",
	                        function(){
	                            $('#forgotpassword').fadeOut(function() {
	                                $('#loginform').fadeIn(); 
	                                $('#modal-action').fadeOut();
	                            });
	                        });
	    });
});