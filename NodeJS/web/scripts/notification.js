var interval;
var submissions;

$(document).ready(function() {
    $('#notif-btn').on('click', function(){
        if(open) {
            $('#notifications').animate({right:'-=35%'}, 400);
        } else {
            $('#notifications').animate({right:'+=35%'}, 400);
        }
        
        open = !open;
    });
    
    updateNotificationCount(count);
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
    
    database.ref('notifications').on('child_added', function(snapshot) {
        if(snapshot.val() != undefined || snapshot.val() != null) {
            if(false == snapshot.val().email_sent) {
                if(snapshot.val().email_list.indexOf(user.email)) {
                    pushNotification({
                        title: snapshot.val().title,
                        details: snapshot.val().message,
                        unread: false,
                        time: Date.parse(snapshot.val().timestamp)
                    });    
                } 
            } 
        }
    });
    
    database.ref('notifications').on('child_removed', function(snapshot) {
        if(snapshot.val() != undefined || snapshot.val() != null) {
            delete notifications[snapshot.key];
        }
    });
});

var open = false;
var count = 0;
var notificationList = [];

function notification (title, details, time, unread) {
    this.title = title;
    this.details = details;
    this.unread = unread;
    this.time = time;
    this.ui = null;
}



function pushNotification (notification) {
    var item = document.createElement('li');
    var title = document.createElement('div');
    var details = document.createElement('div');
    var time = document.createElement('div');

    $(item).addClass('unread');
    $(title).addClass('act-title');
    $(details).addClass('act-details');
    $(time).addClass('act-timelapsed');

    $(title).html(notification.title);
    $(details).html(notification.details);
    
    setInterval(function() {
        var second = Math.round((Date.now() - notification.time)/1000);
        var minute = Math.round(second/60);
        var hour = Math.round(minute/60);
        var day  = Math.round(hour/24);
        var week = Math.round(day/7);
        var month = Math.round(week/4);
        
        if (month > 0)
            $(time).html(month + ((month == 1)?" month ago":" months ago"));
        else if (week > 0)
            $(time).html(week + ((week == 1)?" week ago":" weeks ago"));
        else if (day > 0)
            $(time).html(day + ((day == 1)?" day ago":" days ago"));
        else if (hour > 0)
            $(time).html(hour + ((hour == 1)?" hour ago":" hours ago"));
        else if (minute > 0)
            $(time).html(minute + ((minute == 1)?" minute ago":" minutes ago"));
        else $(time).html("Just Now");
    }, 1000);
    
    $(item).on('click', function(){
    	if(notification.unread) {
            notification.unread = false;
            $(item).removeClass('unread');
            updateNotificationCount(--count);
    	}
    });
    
    $(time).html("Just Now");
    $(item).append(title);
    $(item).append(details);
    $(item).append(time);

    notificationList.push(notification);
    
    if(notification.unread) {
        count++;
        updateNotificationCount(count);  
    }
    
    notification.ui = item;
    $('#notifs-list').prepend(item);
}

function updateNotificationCount (count) {
    if(count > 0) {
        $('#notif-count').show();
        $('#notif-count').attr('data-badge', count);
    } else {
        $('#notif-count').hide();
    }
}