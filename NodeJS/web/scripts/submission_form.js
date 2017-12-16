Date.prototype.getWeekOfMonth = function(){
    var firstDay = new Date(this.getFullYear(), this.getMonth(), 1).getDay();
    return Math.ceil((this.getDate() + firstDay)/7);
}

var selectedDates = [];

$(document).ready(function() {
    $('button#next').on('click', function(e){
        e.preventDefault();
        calendar.setMonth(calendar.getMonth()+1);
        setDate(calendar);
    });
    
    $('button#prev').on('click', function(e){
        e.preventDefault();
        calendar.setMonth(calendar.getMonth()-1);
        setDate(calendar);
    });
    
    $('#typeOfSub').on('change', function(){
        var opt = $(this).val();
        switch (opt) {
            case "Initial Submission":
                $('#target').slideDown();
                $('#target-sas').slideUp();
                break;
            case "Special Approval Slip":
                $('#target').slideUp();
                $('#target-sas').slideDown();
                break;
        }
    });
    
    $('#typeOfSASSub').on('change', function(){
        var opt = $(this).val();
        switch(opt) {
            case "In Case of Change":
            case "Activity Not in GOSM":
                $('#target').slideDown();
                break;
            default: 
                $('#target').slideUp();
                break;
        }
    });
    
    $('select#month').on('change', function(){
        calendar.setMonth($('select#month').val()-1);
        setDate(calendar);
    });
    
    $('input#year').prop('disabled', true);
    
    var multi = false;
    $('select#act-date-type').on('change', function(){
        var opt = $(this).find(":selected").val();
        
        selectedDates = [];
        setDate(calendar);
        $('.selected-dates').html('');
        
        switch(opt) {
            case "Multiple Dates":
                multi = true;
                $('#add-date').html('Add <i class = "fa fa-plus"> </i>');
                $('#act-date-container').slideDown();
                break;
            case "One-day Activity":
                $('#add-date').html('Select <i class = "fa fa-calendar"> </i>');
                multi = false;
                $('#act-date-container').slideDown();
                break;
            default:
                $('#act-date-container').slideUp();
        }
    });
    
    var calendar = new Date();
    setDate(calendar);
    
    if(!$('.checkbox').find('i').is(':visible')) {
        $('#act-type').prop('disabled', false);
        $('#act-type-others').prop('disabled', true);
    } else {
        $('#act-type').prop('disabled', true);
        $('#act-type-others').prop('disabled', false);
    }
    
    $('.checkbox').on('click', function(){
        $(this).find('i').toggle();
        
        if(!$(this).find('i').is(':visible')) {
            $('#act-type').prop('disabled', false);
            $('#act-type-others').prop('disabled', true);
        } else {
            $('#act-type').prop('disabled', true);
            $('#act-type-others').prop('disabled', false);
        }
    });
    
    $('#submit-document').on('click', function(e){
        e.preventDefault();
        submitDocument();
    });
    
    $('#add-date').on('click', function(e){
        e.preventDefault();
        var dateStr = $('#date-text').val();
        var month = parseInt(dateStr.split('/')[0]);
        var year = parseInt(dateStr.split('/')[2]);
        var day = parseInt(dateStr.split('/')[1]);
        var date = new Date(year, month-1, day);
        
        if(isNaN(Date.parse(dateStr))) {
            return false;
        }
        
        if(!includes(selectedDates, date)) {
            if(multi) {
                selectedDates.unshift(date);
            } else {
                selectedDates = [date];
                $('.selected-dates').html('');
            }
            
            setDate(calendar);
            var docu = document.createElement('div');
            var i = document.createElement('i');;

            $(docu).addClass('date');
            $(docu).append((date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear());
            $(i).addClass('fa');
            $(i).addClass('fa-times');

            $(i).on('click', function(){
                selectedDates.splice(selectedDates.indexOf(date), 1); 
                $(docu).remove();
                setDate(calendar);
            });

            $('.clear').on('click', function(){
                selectedDates = [date];
                $('.selected-dates').html('');
                setDate(calendar);   
            });

            $('button.currentDate').on('click', function(e){
                e.preventDefault();
                calendar.setTime(Date.now());
                setDate(calendar); 
            });

            $(docu).append(i);

            $('.selected-dates').prepend(docu);
            $(this).addClass('selected');
        }
    });
    
    $('.cal tr td').on('click', function(){
        if($(this).hasClass('unselectable'))
            return false;
        var month = $('select#month>option:selected').val();
        var year = $('input#year').val();
        var day = parseInt($(this).text());
        var date = new Date(year, month-1, day);
        
        if(!$(this).hasClass('selected')) {
            if(multi) {
                selectedDates.unshift(date);
            } else {
                selectedDates = [date];
                $('.selected-dates').html('');
                setDate(calendar);
            }
            var docu = document.createElement('div');
            var i = document.createElement('i');;
            
            $(docu).addClass('date');
            $(docu).append((date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear());
            $(i).addClass('fa');
            $(i).addClass('fa-times');

            $(i).on('click', function(){
                for(var i = 0; i < selectedDates.length; i++) {
                    if (selectedDates[i].getTime() == date.getTime()) {
                        selectedDates.splice(i, 1); 
                        $('.date:eq(' + i +')').remove();
                    }
                }
                
                setDate(calendar);
            });
            
            $('.clear').on('click', function(){
                selectedDates = [date];
                $('.selected-dates').html('');
                setDate(calendar);   
            });
            
            $('button.currentDate').on('click', function(e){
                e.preventDefault();
                calendar.setTime(Date.now());
                setDate(calendar); 
            });
            
            $(docu).append(i);

            $('.selected-dates').prepend(docu);
            $(this).addClass('selected');
            
        } else {
            for(var i = 0; i < selectedDates.length; i++) {
                if (selectedDates[i].getTime() == date.getTime()) {
                    selectedDates.splice(i, 1); 
                    $('.date:eq(' + i +')').remove();
                }
            
            }
            
            $(this).removeClass('selected');
            setDate(calendar);
        }
        console.log(selectedDates);
    });
});

function setDate (calendar) {
    $('select#month>option:eq('+ calendar.getMonth() +')').prop('selected', true);
    $('input#year').val(calendar.getFullYear());
    
    changeMonth(calendar.getMonth(), calendar.getFullYear());
}

function changeMonth (month, year) {
    var current = new Date(year, month, 1);
    var last = new Date(year, month + 1, 0);
    $('tr:eq(6)').show();
    for (var i = 1; i < 7; i++) {
        for (var j = 0; j < 7; j++) {
            $('tr:eq('+i+')>td:eq(' + j +')').html('');
            $('tr:eq('+i+')>td:eq(' + j +')').addClass('unselectable');
            $('tr:eq('+i+')>td:eq(' + j +')').removeClass('selected');
            $('tr:eq('+i+')>td:eq(' + j +')').removeClass('current');
        }
    }
    
    for (var i = 1; i <= last.getDate(); i++) {
        $('tr:eq('+current.getWeekOfMonth()+')>td:eq(' +current.getDay()+')').html(i);
        if(current.getDay() != 0)
            $('tr:eq('+current.getWeekOfMonth()+')>td:eq(' +current.getDay()+')').removeClass('unselectable');
        if (includes(selectedDates, current))
            $('tr:eq('+current.getWeekOfMonth()+')>td:eq(' +current.getDay()+')').addClass('selected');
        if (current.getTime() == (new Date).getTime())
            $('tr:eq('+current.getWeekOfMonth()+')>td:eq(' +current.getDay()+')').addClass('current');
        current = new Date(year, month, i + 1);
    }
    
    var empty = true;
    for (var i = 0; i < 7; i++) {
        if($('tr:eq(6)>td:eq(' + i + ')').text().trim() != '') {
            empty = false;
        }
    }
    
    if(empty) {
        $('tr:eq(6)').hide();
    }
}

function includes (arr, current) {
    for(var i = 0; i < arr.length; i++) {
        if(arr[i].getTime() == current.getTime())
            return true;
    } 
    return false;
}

function submitDocument () {
    var typeSub = $('#typeOfSub').val();
    var typeSASSub = $('#typeOfSASSub').val();
    var term = $('#term').val();
    var actTitle = $('#act-title').val();
    var dateType = $('#act-date-type').val();
    var dates;
    var actTime = $('#act-time').val();
    var actNature = $('#act-nature').val();
    var actType = $('#act-type').val();
    var actTypeOthers = $('.checkbox').find('i').is(':visible');
    var actTypeOthersText = $('#act-type-others').val();
    var typeAct;
    var actVenue = $('#act-venue').val();
    var errorMessage = "";
    var sasReq = false;
    var allReq = false;

    if(typeSub == null || typeSub.trim() == '') {
        errorMessage += "Type of Submission <br>";
        $('#typeSub-label').addClass('error');
    } else {
        $('#typeSub-label').removeClass('error');
        console.log(typeSub);
        if(typeSub == 'Initial Submission') {
            console.log("here");
            allReq = true;
            typeSASSub = "None"
        }
        else sasReq = true;
    }
    if(sasReq) {
        if(typeSASSub == null || typeSASSub.trim() == '') {
            errorMessage += "Type of SAS Submission <br>";
            $('#type-SAS-label').addClass('error');
        } else {
            $('#type-SAS-label').removeClass('error');
            if(typeSASSub.trim() == 'In Case of Change' 
                || typeSASSub.trim() == 'Activity Not in GOSM')
                allReq = true;
        }
    }
    
    if(term == null || term.trim() == '' ) {
        errorMessage += "Term <br>";
        $('#term-label').addClass('error');
    } else {
        $('#term-label').removeClass('error');
    }
    
    if(actTitle.trim() == '' || actTitle == null) {
        errorMessage += "Activity Title <br>";
        $('#act-title-label').addClass('error');
    } else {
        $('#act-title-label').removeClass('error');
    }
    console.log(allReq);
    if(allReq) {
        switch(dateType) {
            case null:
                errorMessage += "Activity Date Type <br>";
                $('#act-date-type-label').addClass('error');
                $('#act-date-label').addClass('error');
                break;
            case "Multiple Dates":
            case "One-day Activity":
                if(selectedDates.length == 0) {
                    errorMessage += "Activity Date/s <br>";
                    dates = $(".date").val();
                }
                break;
        }
        
        if(actNature == null || actNature.trim() == '') {
            errorMessage += "Activity Nature <br> ";
            $('#act-nature-label').addClass('error');
        } else {
            $('#act-nature-label').removeClass('error');
            
        }
        
        if(actTypeOthers) {
            if (actTypeOthers.trim() == '' || actTypeOthers == null) {
                errorMessage += "Activity Type <br>";
                $('#act-type-label').addClass('error');
            } else {
                typeAct = $("#act-type-others").val();
                $('#act-type-label').removeClass('error');
            }
        } else {
            if (actType == '' || actType == null) {
                errorMessage += "Activity Type <br>";
                $('#act-type-label').addClass('error');
            } else {
                typeAct = $("#act-type").val();
                $('#act-type-label').removeClass('error');
            }
        }
        
        if(actVenue == null || actVenue.trim() == '')  {
            errorMessage += "Activity Venue <br>";
            $('#act-venue-label').addClass('error');
        } else {
            $('#act-venue-label').removeClass('error');
        }
    } else {
        $('#act-date-type-label').removeClass('error');
        $('#act-date-label').removeClass('error');
        $('#act-type-label').removeClass('error');
        $('#act-type-label').removeClass('error');
        $('#act-venue-label').removeClass('error');
        $('#act-nature-label').removeClass('error')
    }
    
    
    if(errorMessage != '') {
        $('body').css('overflow', 'hidden');
        modalMessage("Missing information: <br> " + errorMessage,
                    "Okay", 
                    function(){
                        $('#modal-action').remove();
                        $('body').css('overflow', 'auto');
        
                    },
                    null,
                    null,
                    false);
    } else {
        var dates = selectedDates.join(', ');
        $.ajax({ 		
			type        : 'POST', 		
			url         : 'submitSubmission',		
            data        : {term:term, act_title:actTitle, act_date:dates, act_time:actTime, act_nature:actNature,
                           act_type:actType, act_venue:actVenue, type_sub:typeSub, type_sas:typeSASSub},
			dataType    : 'json',		
	 		success     : function(data) {
                console.log(data);
                $('body').css('overflow', 'hidden');
                modalMessage("Successfully sent a submission! Your submission will be checked within 3 - 5 working days.",
                            null,
                            null,
                            "Okay",
                            function(){
                                $('#modal-action').remove();  
                                $('body').css('overflow', 'auto');
                            },
                            false);
            },
            error   	: function(xhr,status,error){		
					console.log(xhr);   		
					alert(status);		
            }
        });
    }
}


