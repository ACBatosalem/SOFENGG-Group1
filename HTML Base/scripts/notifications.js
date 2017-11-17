$(document).ready(function() {
    var open = false;
    $('#notif-btn').on('click', function(){
        if(open) {
            $('#notifications').animate({right:'-=35%'}, 400);
        } else {
            $('#notifications').animate({right:'+=35%'}, 400);
        }
        
        open = !open;
    });
});
