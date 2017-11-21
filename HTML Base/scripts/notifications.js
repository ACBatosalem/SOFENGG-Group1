var interval;
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
    pushNotification(new notification("BANG", "BANG"));      
    interval = setInterval(function(){
        pushNotification(new notification("BANG", "BANG"));      
    }, 10000);
    
    $('#notif-clear').on('click', function(){
        notificationList.forEach(function(item, index){
            if(item.unread) {
                --count;
                item.unread = false;
                $(item.ui).removeClass('unread');
                updateNotificationCount(0);      
            }
        });
    });
});

var open = false;
var count = 0;
var notificationList = [];

function notification (title, details, ui) {
    this.title = title;
    this.details = details;
    this.unread = true;
    this.time = Date.now();
    this.ui = ui;
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
        notification.unread = false;
        $(item).removeClass('unread');
        updateNotificationCount(--count);
    });
    
    $(time).html("Just Now");
    $(item).append(title);
    $(item).append(details);
    $(item).append(time);

    notification.ui = item;
    count++;
    updateNotificationCount(count);  
    notificationList.push(notification);
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

function Request() {
    this.poll = false;
    this.activatePoll = function () {
        this.poll = true;
        this.runPoll();
    };

    this.disablePoll = function () {
        this.poll = false;
    };

    this.runPoll = function () {
        var self = this;
        var poll = setTimeout(function () {
            $.ajax({
                url: context + '/homeORG/notifications',
                success: function (response) {
                    console.log(response);
                },
                dataType: "json",
                complete: function () {
                    if (self.poll == false) {
                        clearTimeout(poll);
                    } else {
                        self.runPoll();
                    }
                }
            })}, 1000);
        };
}