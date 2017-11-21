$(document).ready(function() {
    var flag = true;
    var error = true;
    var table= $('#organizations').DataTable( {
       "scrollCollapse": true,
        "autoWidth": false,
        "ordering": false,
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "columnDefs":[{
            "targets":[1, 2],
            "orderable": false,
        },
        {
        	"visible": false,
        }],
        "order": [[ 0, "desc" ]],
        "scrollY": "500px",
        "language": {
            "info": ""
        },
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
            $('#add-prompt-message').fadeOut();
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
    
    function successAdd (org) {
        $('#username-toast').text(org.userName);
        $('#password-toast').text(org.password);
        $('#add-notif').slideDown();
        $('#ok-toast').on('click', function(){
            $('#add-notif').slideUp();
        });
        
        var tble = document.getElementById('organizations-body');
        var row = tble.insertRow(0);
        var user = row.insertCell(0);
        var pass = row.insertCell(1);
        var del = row.insertCell(2);
        var btndel = document.createElement('button');
        var btnfa = document.createElement('i');
        
        $(btnfa).addClass('fa');
        $(btnfa).addClass('fa-trash');
        $(btnfa).addClass('icon');

        $(btndel).attr('data-orgID', org.id);
        $(btndel).on('click', function(){
            var orgID = $(this).attr('data-orgID');
            deleteOrg(orgID, $(this).parent().parent());
            $('#add-notif').slideUp();
        });
        
        $(btndel).addClass('delete-org');
        $(btndel).append(btnfa);
        $(del).append(btndel);
        $(user).html(org.userName);
        $(pass).html(org.password);
        
        $('#undo-toast').attr('data-orgID', org.id);
        
        setTimeout(function(){
            $('#add-notif').slideUp();
        }, 5000);
        $('#username').val('');
    }
    
    function deleteOrg (orgID, row) {
		$.ajax({
			type        : 'POST', 
            url         : 'deleteOrg',
            data        : {id:orgID},
            dataType    : 'html',
            success     : function(data) {
            	if (data == "true"){
            		 row.remove()
            	} else {
            		
            	}
            },
            error   : function(xhr,status,error){
                console.log(xhr);  
                console.log(error);
                alert(status);
            }
		});
    }
    
    $('.close-prompt').on('click', function() {
        $('#add-prompt-message').fadeOut();
        flag = false;
    });
    
    $('#username').on('focus', function(){
        if(flag) {
            queryPrompt("Username should contain 2 - 20 characters and should only contain capital letters and up to two non-successive spaces.");
            $('#add-prompt-message').fadeIn();
            error = false;
        }
    });
    
    $('#username').keyup(function(event) {
        if (event.keyCode === 13) {
            $("#add-org-btn").click();
        }
    });
    
    $('#username').on('blur', function(){
        if(error) {
            $('#add-prompt-message').fadeOut(function(){
            	queryPrompt("Username should contain 2 - 20 characters and should only contain capital letters and up to two non-successive spaces.");
            });
        }
    });
    

    var usernameregex = /^(([A-Z]{1,}?(\s{1}[A-Z]{1,})?(\s{1}[A-Z]{1,})?))$/;
    //var fullnameregex = /^([A-Za-z]{2,2})([A-Za-z\s]{5,80}$)/;

    /*function checkfullname (check) {
    	if(check.length < 7) 
    		return "\nName must be 7 characters or more.";
    	else if (!fullnameregex.test(check))
    		return "\nName must only contain letters."
    	else
    		return "";
    }*/


    function checkusername (check) {
    	if(check.length < 2) 
    		return "\nUsername must be 2 characters or more.";
    	else if(check.length > 20)
    		return "\nUsername must be 20 characters or less.";
    	else if (!usernameregex.test(check))
    		return "\nUsername must only contain capital letters and two non-successive spaces."
    	else
    		return "";
    }
    
    function isJSON(str) {
	    try {
	        JSON.parse(str);
	    } catch (e) {
	  
	        return false;
	    }
	    return true;
    }
    
    $('#add-org-btn').on('click', function(){
        var username = $('#username').val();
        
        if(username == "") {
            errorPrompt("Username should not be empty!");
        } else if (checkusername(username) != "") {
            errorPrompt(checkusername(username));
        } else {
        	
			$.ajax({
				type        : 'POST', 
	            url         : 'addOrg',
	            data        : {/*name:name,*/ username:username},
	            dataType    : 'html',
	            success     : function(data) {
	            	if (isJSON(data)){
	                    successAdd(JSON.parse(data));
	                    $('#add-prompt-message').fadeOut();
	            	} else {
	            		errorPrompt(data);
	            	}
	            },
	            error   : function(xhr,status,error){
	                console.log(xhr);  
	                console.log(error);
	                alert(status);
	            }
			});
			
        }
        
        flag = true;
        error = true;
    });
	
    $('.delete-org').on('click', function(){
    	var deletedRow = this;
    	modalMessage("Are you sure you want to delete this?",
    			"No",
    			function(){
    				$("#modal-action").remove();
    			},
    			"Yes",
    			function(){
    		        var orgID = $(deletedRow).attr('data-orgID');
    		        deleteOrg(orgID, $('.delete-org[data-orgID="' + orgID + '"]').parent().parent());
    		        $("#modal-action").remove();
    			});
    });
    
    $('#undo-toast').on('click', function(){
        var orgID = $(this).attr('data-orgID');
        deleteOrg(orgID, $('.delete-org[data-orgID="' + orgID + '"]').parent().parent());
        $('#add-notif').slideUp();
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
        $(buttonYes).focus();
        $(buttonNo).focus();
    }
});
