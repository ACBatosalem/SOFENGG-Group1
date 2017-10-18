$(document).ready(function(){
    $('#acads-tab-btn').click(function() {
        $('#acads-tab').show();
        $('#act-tab').hide();
        
        $('#acads-tab-btn').addClass('selected');
        $('#act-tab-btn').removeClass('selected');
        
        $('.options-box').hide();
    });
    
    $('#act-tab-btn').click(function() {
        $('#acads-tab').hide();
        $('#act-tab').show();
        
        $('#acads-tab-btn').removeClass('selected');
        $('#act-tab-btn').addClass('selected');
        
        $('.options-box').show();
    });
    
    $("input:checkbox").on('click', function() {
        var $box = $(this);
        if ($box.is(":checked")) {
            var group = "input:checkbox[name='" + $box.attr("name") + "']";
            $(group).prop("checked", false);
            $box.prop("checked", true);
        } else {
            $box.prop("checked", false);
        }
    });
    
    $('#close-modal').on('click', function() {
         $('#activity-modal').fadeOut();
    });
    
    $('').css
    
    $('tr').on('click', function() {
        $('#activity-modal').fadeIn();
    })
    
    $('#notif').on('click', function(){
        $('#notification-panel').slideToggle();
        $('#account-settings').slideUp();
    })
    
    $('#user-settings').click(function(){
        $('#account-settings').slideToggle(); $('#notification-panel').slideUp();
    });
});