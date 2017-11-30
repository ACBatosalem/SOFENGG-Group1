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
    
    firebase.database().ref('submissions').once('value', function(snapshot) {
        submissions = snapshot.val();
    });

    $('#table_submissions tbody tr').each(function(){
        var row = $(this);
        var key = row.attr('data-docuID');

        firebase.database().ref('submissions').child(key).on('child_changed', function(snapshot) {
            $(this).find('td').eq(3).text(snapshot.val().status);
            
            pushNotification(new notification(snapshot.val().title, "Some details have been updated!", Date.now(), true));
            firebase.database().ref('submissions').once('value', function(snapshot) {
                submissions = snapshot.val();
            });
        });
    });

    $("tr.item").each(function() {
        $this = $(this);
        var value = $this.find("span.value").html();
        var quantity = $this.find("input.quantity").val();
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