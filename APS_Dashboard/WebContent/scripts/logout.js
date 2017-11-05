$(document).ready(function(){
    $('#signout').on('click', function(){
        modalMessage(
        "Are you sure you want to logout?", 
        "No", 
        function(){
            $('#modal-action').remove();
        }, 
        "Yes", 
        function(){
            window.location.href = context+'/logout';
        });
    });

    $('#change-pass-form').on('submit', function(e){
        e.preventDefault();
        var oldPass = document.forms['change-pass']['oldpass'].value;
        var newPass = document.forms['change-pass']['newpass'].value;
        var cNewPass = document.forms['change-pass']['cnewpass'].value;
        var passwordregex = /^([A-Za-z0-9]{6,25}$)/;

        function success () {
            modalMessage("Password successfully changed!", 
            null, 
            null, 
            "OK", 
            function(){
                $('#modal-action').remove();
            });
            $('#error_msg').text("");
        }
       
        function checkpassword(password) {
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
        
        if (oldPass == "" || newPass == "" || cNewPass == "") {
            $('#error_msg').text("Fields should not be empty!");
        } else if (cNewPass != newPass) {
            $('#error_msg').text("Passwords do not match!");
        } else if (oldPass == newPass) {
            $('#error_msg').text("Old password is the same as the new password!");
        } else if (checkpassword(newPass) != "") {
            $('#error_msg').text(checkpassword(newPass));
        } else {
        	$.ajax({ 		
				type        : 'POST', 		
				url         : 'updatePassword',		
				data        : {old_pw:oldPass, new_pw:newPass, retype_pw:cNewPass},
				dataType    : 'html',		
		 		success     : function(data) {		
		            success();
				},		
		 		error   : function(xhr,status,error){		
					console.log(xhr);   		
					alert(status);		
				}		
			});
        }
        

        document.forms['change-pass']['oldpass'].value = "";
        document.forms['change-pass']['newpass'].value = "";
        document.forms['change-pass']['cnewpass'].value = "";
    });
});

function modalMessage (message, neg, negact, pos, posact) {
    var modalAct = document.createElement('div');
    var modalBg = document.createElement('div');
    var modalCon = document.createElement('div');
    var modalTit = document.createElement('div');
    var buttonYes = document.createElement('button');
    var buttonNo = document.createElement('button');

    $(modalAct).attr('id', 'modal-action');
    $(modalBg).attr('id', 'modal-action-bg');
    $(modalCon).attr('id', 'modal-action-content');
    $(modalTit).attr('id', 'modal-action-label');
    $(modalTit).text(message);
    
    $(modalCon).append(modalTit);
        
    if(neg != null) {       
        $(buttonNo).addClass('modal-action-neg');
        $(buttonNo).text(neg);
        $(buttonNo).click(negact);
        $(modalCon).append(buttonNo);
    }
    
    if(pos != null) {  
        $(buttonYes).addClass('modal-action-pos');
        $(buttonYes).text(pos);
        $(buttonYes).click(posact);
        $(modalCon).append(buttonYes);
    }
    
    $(modalAct).append(modalBg);
    $(modalAct).append(modalCon);

    $('body').append(modalAct);
}
