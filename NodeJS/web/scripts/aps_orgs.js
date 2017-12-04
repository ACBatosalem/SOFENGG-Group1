$(document).ready(function() {
    
    $('#manage-org').on('click', function(){
        $('#users-table').fadeOut(function(){
            $('#orgs-table').fadeIn();  
            $('#manage-user').removeClass('selected');
            $('#manage-org').addClass('selected'); 
       });
    });
    
    $('#manage-user').on('click', function(){ 
        $('#orgs-table').fadeOut(function(){
            $('#users-table').fadeIn(); 
            $('#manage-user').addClass('selected');
            $('#manage-org').removeClass('selected');
        });
    });
    
    $('#add-org').on('click', function(){
        $('#add-user-form').hide();
        $('body').css('overflow', 'none');
        $('#add-org-form').show(function(){       
            $('#modal-view').fadeIn();
        });
    });
    
    $('#add-user').on('click', function(){     
        $('#add-user-form .modal-title').text('Add User');
        $('#add-org-form').hide();
        $('#add-user-form .modal-save').hide();
        $('#add-user-form .modal-add').show();
        $('body').css('overflow', 'none');
        $('#add-user-form').show(function(){ 
        $('#modal-view').fadeIn(); 
        });
    });
    
    $('.edit').on('click', function(){     
        $('#add-user-form .modal-title').text('Edit User');
        $('#add-user-form .modal-add').hide();
        $('#add-user-form .modal-save').show();
        $('#add-org-form').hide();
            $('body').css('overflow', 'none');
        $('#add-user-form').show(function(){ 
        $('#modal-view').fadeIn(); 
        });
    });
    
    $('#modal-close').on('click', function(){
        $('#modal-view').fadeOut(function(){
            $('body').css('overflow', 'auto');
        }); 
    });
    
    $('.delete').on('click', function(){
        var orgRow = this;
        modalMessage("Are you sure you want to delete this user?",
            "No",
            function(){
                $('#modal-action').remove();
            },
            "Yes",
            function(){
                var orgID = $(orgRow).attr('data-orgID');
                $.ajax({
                    type        : 'POST', 
                    url         : 'deleteUser',
                    data        : {id:orgID},
                    dataType    : 'html',
                    success     : function(data) {
                        console.log(data);
                        window.location = context + "/aps/accounts";
                    },
                    error   : function(xhr,status,error){
                        console.log(xhr.responsetext);
                        alert(status);
                        alert(error);
                    }
                });
            }, false
        )
    });
    

    $('.changeStatus').on('click', function(){
        var orgRow = this;
        
        var text = $(orgRow).attr('value');
        var msg;
        if(text == "active")
            msg = "Are you sure you want to archive this organization?";
        else  msg = "Are you sure you want to unarchive this organization?";
       modalMessage(msg,
            "No",
            function(){
                $('#modal-action').remove();
            },
            "Yes",
            function(){
                var orgID = $(orgRow).attr('data-orgID');
                var text = $(orgRow).attr('value');
                console.log(orgID);
                console.log(text);
                changeStatusOrg(orgID,text);
                $("#modal-action").remove();
                window.location = context + "/aps/accounts";
            }, false
        ) 
    });
    

    function changeStatusOrg (orgID, status) {
		$.ajax({
			type        : 'POST', 
            url         : 'changeStatus',
            data        : {id:orgID, status:status},
            dataType    : 'html',
            success     : function(data) {
                console.log(data);
            },
            error   : function(xhr,status,error){
                console.log(xhr.responsetext);
                alert(status);
                alert(error);
            }
		});
    }
    

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
});
