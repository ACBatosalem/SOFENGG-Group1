CB.CloudApp.init('ytmnqclnktry','1cadbe54-9dc7-4d06-9ba9-2568d9f5c10d');


function updateCount(count) {
    $('#count').html(count+"");
}

function deletePost (key) {
    database.ref('posts').child(key).remove();
    $(this).parent().remove();
};

function addPostToDB () {
    var username = $('#inputUser').val();
    var post = $('#inputSend').val();
    var posts = new CB.CloudObject("posts");
    posts.set(username, post);
    posts.save({
        success: function(posts) {
            addPost(post, username, 0)
        }, 
        error: function (e) {
            
        }
    })
};

function addPost (strUser, strPost, key) {
    var username = document.createElement('h3');
    var message = document.createElement('span');
    var item = document.createElement('li');
    var del = document.createElement('button');
    
    $(item).addClass('post-item');
    $(username).addClass('post-username');
    $(message).addClass('post-message');
    $(del).addClass('btn');
    $(del).addClass('btn_del');
    $(del).click(function(){
        deletePost(key);
        $(item).remove();
    });
    
    $(username).html(strUser);
    $(message).html(strPost);
    $(del).html('DEL');
    
    $(item).append(username);
    $(item).append(message);
    $(item).append(del);
    
    $('#post-lists').prepend(item);
}