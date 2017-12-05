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
        $('.modal-field').val("");
        $('#org-username-error').text("");
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

    $('#addOrgButton').on('click',function(){
        var name = $('#orgName').val();
        var username = $('#orgUsername').val();
        $('#org-username-error').text("");
        var nameRegexTest = checkName(name);
        var usernameRegexTest = checkorgusername(username);
        if(username == "" || username == undefined || 
            name == "" || name == undefined) {
            console.log("Please fill out all fields");
            $('#org-username-error').text("Please fill out all fields");
        } else if(nameRegexTest != "" || usernameRegexTest != "") {
            $('#org-username-error').html(nameRegexTest + "<br>" + usernameRegexTest);
        } else {
            $.ajax({
                type        : 'POST', 
                url         : 'addOrganization',
                data        : {name:name, username:username},
                dataType    : 'html',
                success     : function(data) {
                    console.log(data);
                    if(data == "Organization name or username already in use.")
                        $('#org-username-error').text(data);
                    else
                    window.location = context + "/aps/accounts";
                },
                error   : function(xhr,status,error){
                    console.log(xhr.responsetext);
                    alert(status);
                    alert(error);
                }
            });
        }
    });

    $('#addUserButton').on('click',function(){
        var name = $('#userName').val();
        var username = $('#userUsername').val();
        var contact = $('#userContact').val();
        var email = $('#userEmail').val();
        var org = $('#userOrg').val();
        console.log(org);
        $('#user-error').text("");
        var nameRegexTest = checkName(name);
        var usernameRegexTest = checkusername(username);
        var emailRegexTest = checkemail(email);
        var contactRegexTest = checkcontact(contact);
        if(username == "" || username == undefined ||
            name == "" || name == undefined ||
            email == "" || email == undefined ||
            contact == "" || contact == undefined) {
            console.log("Please fill out all fields");
            $('#user-error').text("Please fill out all fields");
        } else if(nameRegexTest != "" || usernameRegexTest != ""
                || emailRegexTest != "" || contactRegexTest != "") {
            $('#user-error').html(nameRegexTest + "<br>" + usernameRegexTest
            + "<br>" + emailRegexTest + "<br>" + contactRegexTest);
        } else {
            $.ajax({
                type        : 'POST', 
                url         : 'addUser',
                data        : {name:name, username:username, email:email, contact:contact, org:org},
                dataType    : 'html',
                success     : function(data) {
                    if(data == "Name, username, contact number, or email already in use.")
                        $('#user-error').text(data);
                    window.location = context + "/aps/accounts";
                },
                error   : function(xhr,status,error){
                    console.log(xhr.responsetext);
                    alert(status);
                    alert(error);
                }
            });
        }
    });
    

    var orgusernameregex = /^(([A-Z]{1,}?(\s{1}[A-Z]{1,})?(\s{1}[A-Z]{1,})?))$/;
    var nameregex = /^(([A-Z]{1,}?(\s{1}[A-Z]{1,}){0,}))$/;
    var usernameregex = /^([a-z]{2,}_[a-z]{2,})$/;
    var emailregex = /^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$/;
    var contactregex =  /^\d{11}$/;

    function checkemail(check) {
        if (!emailregex.test(check))
            return "\nEnter a valid email."
        else
            return "";
    }
    function checkcontact(check) {
        if (!contactregex.test(check))
            return "\nEnter a valid contact number."
        else
            return "";
    }

    function checkName (check) {
    	if(check.length < 7) 
            return "\nName must be 7 characters or more.";
        else if(check.length > 70)
        return "\nName must be 70 characters or less.";
    	else if (!nameregex.test(check))
    		return "\nName must only contain capital letters."
    	else
    		return "";
    }

    function checkusername(check) {
        if(check.length < 5) 
            return "\nUsername must be 5 characters or more.";
        else if(check.length > 30)
            return "\nUsername must be 30 characters or less.";
        else if (!usernameregex.test(check))
            return "\nUsername must only contain small letters and one underscore."
        else
            return "";
    }

    function checkorgusername (check) {
    	if(check.length < 2) 
    		return "\nUsername must be 2 characters or more.";
    	else if(check.length > 20)
    		return "\nUsername must be 20 characters or less.";
    	else if (!orgusernameregex.test(check))
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
