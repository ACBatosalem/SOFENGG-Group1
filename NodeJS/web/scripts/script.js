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
database.ref('posts').on('value', function(snapshot) {
    var posts = snapshot.val();
    var key;
    
    var count = 0;
    $('#post-lists').html('');
    for(key in posts) {
        ++count;
        addPost(posts[key].username, posts[key].post, key);
    }
    
    updateCount(count);
});

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
    database.ref('posts').push({
        username:username,
        post:post
    });
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

$(document).ready(function(){
    firebase.auth().getRedirectResult().then(function(result) {
  if (result.credential) {
    // This gives you a Google Access Token. You can use it to access the Google API.
    var token = result.credential.accessToken;
    // ...
  }
  // The signed-in user info.
  var user = result.user;
console.log(user);
}).catch(function(error) {
  // Handle Errors here.
  var errorCode = error.code;
  var errorMessage = error.message;
  // The email of the user's account used.
  var email = error.email;
  // The firebase.auth.AuthCredential type that was used.
  var credential = error.credential;
  // ...
});
});