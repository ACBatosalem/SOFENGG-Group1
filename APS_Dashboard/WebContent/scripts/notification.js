count = 0;
$(document).ready(function() {
    var open = false;
    $('#notif-btn').on('click', function(){
        if(open) {
            $('#notifications').animate({right:'-=35%'}, 400);
        } else {
            $('#notifications').animate({right:'+=35%'}, 400);
        }
        
        open = !open;
    });
    
    function pushNotification (notification) {
    	var item = document.createElement('li');
    	var title = document.createElement('div');
    	var details = document.createElement('div');
    	var time = document.createElement('div');
    	
    	item.addClass('unread');
    	title.addClass('act-title');
    	details.addClass('act-details');
    	time.addClass('act-timelapsed');
    	
    	title.html(notification.title);
    	details.html(notification.details);
    	var time = notification.time;
    	try {
    		Date.parse(time);
    	} catch (e) {
    		time = Date.now();
    	}
    	
    	time.html(Date.now() - time);
    	
        $('#notifs-list').prepend(item);
    }
    
});
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
