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
});

