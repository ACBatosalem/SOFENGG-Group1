var onSave = false;
$(document).ready(function() {
    $('.edit-btn').click(function() {
        if($('#' + $(this).attr('data-input')).is(':disabled')) {
            $('#' + $(this).attr('data-button')).removeClass('fa-pencil');
            $('#' + $(this).attr('data-button')).addClass('fa-save');
        } else {
            var text = $('#' + $(this).attr('data-input')).text();
            $('#' + $(this).attr('data-button')).removeClass('fa-save');
            $('#' + $(this).attr('data-button')).addClass('fa-pencil');
            var fn = window[$('#' + $(this).attr('data-function'))];

            if (typeof fn === "function") 
                fn.apply(null, [text]);
        }
        
        $('#' + $(this).attr('data-input')).prop('disabled', !$('#' + $(this).attr('data-input')).is(':disabled'));
        console.log($(this).attr('data-input'));
        
        function saveSheets (text) {
            
        }
        
        function saveEmail (text) {
            
        }
    });

    $('#change-pass-form').on('submit', function(e){
        e.preventDefault();
        var oldPass = document.forms['change-pass']['oldpass'].value;
        var newPass = document.forms['change-pass']['newpass'].value;
        var cNewPass = document.forms['change-pass']['cnewpass'].value;
        
        if (oldPass == "" || newPass == "" || cNewPass == "") {
            $('#error_msg').text("Fields should not be empty!");
        } else if (cNewPass != newPass) {
            $('#error_msg').text("Passwords do not match!");
        } else if (oldPass == newPass) {
            $('#error_msg').text("Old password is the same as the new password!");
        } else if (oldPass == "") {
            // REGEX OPTION
        } else {
            // TODO AJAX REQUEST ON CHANGING PASSWORD
            $.ajax({
                
            }); 
            // IF SUCCESSFUL
            success();
            function success () {
                modalMessage("Password successfully changed!", 
                null, 
                null, 
                "Yes", 
                function(){
                    $('#modal-action').remove();
                });
                $('#error_msg').text("");
                document.forms['change-pass']['oldpass'].value = "";
                document.forms['change-pass']['newpass'].value = "";
                document.forms['change-pass']['cnewpass'].value = "";
            }
        }
    });
});