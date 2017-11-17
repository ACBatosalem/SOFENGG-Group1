$(document).ready(function(){
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