$(document).ready(function(){
    $("#acads-tab").hide();
    $('#acads-tab-btn').click(function() {
        $('#acads-tab').show();
        $('#act-tab').hide();
        
        $('#acads-tab-btn').addClass('selected');
        $('#act-tab-btn').removeClass('selected');
    });
    
    $('#act-tab-btn').click(function() {
        $('#act-tab').show();
        $('#acads-tab').hide();
        
        $('#acads-tab-btn').removeClass('selected');
        $('#act-tab-btn').addClass('selected');
    });
});