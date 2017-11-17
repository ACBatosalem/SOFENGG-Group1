$(document).ready(function() {
    var flag = true;
    var error = true;
    var table= $('#organizations').DataTable( {
       "scrollCollapse": true,
        "autoWidth": false,
        "ordering": true,
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "columnDefs":[{
            "targets":[1, 2],
            "orderable": false,
        }],
        "scrollY": "500px",
        "language": {
            "info": ""
        }
    }).columns.adjust().draw();

    $(document).resize(function() {
        table.columns.adjust().draw();
    });
    
    $(window).resize(function() {
        table.columns.adjust().draw();
    });
    
    var addclose = "#add-close";
    $(addclose).on('click', function(){
        if($(addclose).hasClass('close')) {
            $(addclose).removeClass('close');
            $("#add-organization").animate({width:'toggle'}, 420, function(){
                $("#add-message").animate({width:'toggle'}, 420);
            });
        } else {
            $('#username').val('');
            $(addclose).addClass('close');
            $("#add-message").animate({width:'toggle'}, 420, function(){
                $("#add-organization").animate({width:'toggle'}, 420);
            });
        }
    });
    
    function errorPrompt (message) {
        $('#add-prompt-message label').text(message);
        $('#add-prompt-message .icon-query').removeClass('fa-question');
        $('#add-prompt-message .icon-query').addClass('fa-exclamation');
        $('#add-prompt-message').addClass('error');
        $('#add-prompt-message').fadeIn();
    }
    
    function queryPrompt (message) {
        $('#add-prompt-message label').text(message);
        $('#add-prompt-message .icon-query').addClass('fa-question');
        $('#add-prompt-message .icon-query').removeClass('fa-exclamation');
        $('#add-prompt-message').removeClass('error');
    }
    
    function successAdd (username, password) {
        $('#username-toast').text(username);
        $('#password-toast').text(password);
        $('#add-notif').slideDown();
        $('#ok-toast').on('click', function(){
            $('#add-notif').slideUp();
        });
        setTimeout(function(){
            $('#add-notif').slideUp();
        }, 5000);
        $('#username').val('');
    }
    
    function deleteOrg (id) {
        // AJAX DELETE ORGANIZATION
    }
    
    $('.close-prompt').on('click', function() {
        $('#add-prompt-message').fadeOut();
        flag = false;
    });
    
    $('#username').on('focus', function(){
        if(flag) {
            queryPrompt("Username should contain 5 - 40 characters and should contain only contain capital letters.");
            $('#add-prompt-message').fadeIn();
            error = false;
        }
    });
    
    $('#username').on('blur', function(){
        if(error) {
            $('#add-prompt-message').fadeOut(function(){
                queryPrompt("Username should contain 5 - 40 characters and should contain only contain capital letters.");
            });
        }
    });
    
    $('#add-org-btn').on('click', function(){
        var username = $('#username').val();
        console.log(username);
        
        if(username == "") {
            errorPrompt("Username should not be empty!");
        } else if (username == "") {
            // REGEX ERROR HERE
            errorPrompt("Username should be ..")
        } else {
            successAdd(username, "random");
            $('#add-prompt-message').fadeOut();
        }
        
        flag = true;
        error = true;
    });
    
    $('.delete-org').on('click', function(){
        var orgID = $(this).attr('data-id');
        alert("DELETED " + orgID);
        deleteOrg(orgID);
    });
    
    $('#undo-toast').on('click', function(){
        var orgID = $(this).attr('data-id');
        alert("DELETED " + orgID);
        deleteOrg(orgID);
    });
});
