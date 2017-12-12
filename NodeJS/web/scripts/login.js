$(document).ready(function(){
    $('#forgot_msg').click(function(e){
        $('#loginform').fadeOut(function(){
            $('#forgotpassword').fadeIn();
        });
    });
    
    $('#btn_back').click(function(e){
        e.preventDefault();
        $('#forgotpassword').fadeOut(function(){
            $('#loginform').fadeIn();
            $('#email').val('');
            $('#forgot_msg_error').text('');
        });
    });
    
    $('#forgotpassword').submit(function(e){
        e.preventDefault();
        var email = $('#email').val();
        var test = checkemail(email);
		// SEND EMAIL
		if(test != "") {
			$('#forgot_msg_error').text(test);
			$('body').css('overflow', 'none');
		}
        else {
			$.ajax({ 		
				type        : 'POST', 		
				url         : 'forgotPassword',		
				data        : {email:email},		
				dataType    : 'html',		
		 		success     : function(data) {
					$('body').css('overflow', 'none');
					modalMessage(data, null, null, "Okay", function(){
						$('#modal-action').remove();
						$('body').css('overflow', 'auto');
						$('#btn_back').click();
					});
				 },	
				 beforeSend	: function (xhr) {
					 $('#btn_forgot').prop('disabled', true);
					 $('#btn_back').prop('disabled', true);
					 $('#loader-ajax').show();
				 },
				  error   	: function(xhr,status,error){		
					 console.log(xhr);   		
					 alert(status);		
				 },	
				 complete	: function (xhr,status) {
					 $('#btn_forgot').prop('disabled', false);
					 $('#btn_back').prop('disabled', true);
					 $('#loader-ajax').hide();
				 }
				});
        //SET TEXT IF ERROR: 
			
		}
    });
    
    
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

	var emailregex = /^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$/;
	function checkemail(check) {
		if (!emailregex.test(check))
			return "\nPlease enter a valid email."
		else
			return "";
	}
});