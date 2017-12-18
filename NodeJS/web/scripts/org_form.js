
$(document).ready(function() {
    var config = {
        apiKey: "AIzaSyBL3mFq5IA-6wlKsvxJCmeRvEuCrC8tp-k",
        authDomain: "labfirebase-f292b.firebaseapp.com",
        databaseURL: "https://labfirebase-f292b.firebaseio.com",
        projectId: "labfirebase-f292b",
        storageBucket: "labfirebase-f292b.appspot.com",
        messagingSenderId: "783008488555"
    };
    
    firebase.initializeApp(config);
    var database = firebase.database();
    $('#submission-form').hide();
    $('#ajax-loader').show();
    database.ref('message').on('value', function(snapshot) {
        $('#sub-form-message').val(snapshot.val().form);
        $('#ajax-loader').hide();
        $('#submission-form').show();
        autosize(document.getElementById('sub-form-message'));
    });

    $('#cancel').hide();
    $('#cancel').click(function(snapshot){
        $('#editsave-submission').html('Edit Message');
        $('#cancel').hide();
    });
    
    $('#sub-form-message').prop('disabled', true);

    var edit = false;
    $('#editsave-submission').click(function(){
        if(!edit) {    
            $('#editsave-submission').html('Save Message');
            $('#cancel').show();
            edit = true;
            $('#sub-form-message').prop('disabled',  false);
        } else {
            edit = false;
            var data = $('#sub-form-message').val();
            database.ref('message').child('form').set(data);
            $('#sub-form-message').prop('disabled', true);
            $('#editsave-submission').html('Edit Message');
            $('#cancel').hide();
        }
    })
});
