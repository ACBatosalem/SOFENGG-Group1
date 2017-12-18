var check;
var recheck;
var click;
var checkerName;
var datetimeNow;
var subKey;
var clickedRow;
var table;
var filter = "all";

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

$(document).ready(function() {
    $.fn.dataTable.ext.search.push(
        function( settings, data, dataIndex ) {
            var orgID = $("#org-pick option:selected").attr('data-orgid');

            var org = data[5];
            var acad = data[6];
            
            var cond1 = false;
            var cond2 = false;

            if(orgID == 0 || orgID == null) {
                cond1 = true;
            } else if (orgID == org) {
                cond1 = true;
            } else {
                cond1 = false;
            }

            if (filter == 'all') {
                cond2 = true;
            } else if (acad == 'true' && filter == 'acad') {
                cond2 = true;
            } else if (acad == 'false' && filter == 'non-acad') {
                cond2 = true;
            } else {
                cond2 = false;
            }
        
            return cond1 && cond2;
        }
    );

    database.ref('submission').orderByChild('timestamp').endAt().limitToLast(1).on('child_added', function(snapshot){
        addRow(snapshot.val());
    });

    database.ref('submissions').orderByChild('org_id').equalTo(user.org_id).on('value', function(snapshot){
        console.log(snapshot.val());
    });
    
    table= $('#table_submissions').DataTable( {
        "scrollCollapse": true,
        "ordering": true,
        "sScrollX": false,
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "pageLength": 15,
        "scrollX": "100%",
        "autoWidth": false,
        "pageLength": 10,
        "columnDefs": [
            { "visible": false, "targets": [-1, -2] }
        ],
        "order": [[ 0, "desc" ]],
        "language": {
            "info": "Showing page _PAGE_ of _PAGES_",
            "infoEmpty": "",
            "lergthMenu": "Display _MENU_ records",
            "emptyTable": "No submissions available."
        },
        drawCallback: function(settings) {
            var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
            pagination.toggle(this.api().page.info().pages > 1);
        }
    }).columns.adjust().draw();
    
    $('.modal-division button').on('click', function(){
        $('.modal-division button').each(function(){
            $(this).removeClass('selected');
            $('#' + $(this).attr('data-form')).hide();
        });
        
        $(this).addClass('selected');
        $('#' + $(this).attr('data-form')).show();
    });
    $("#org-pick").val($("#org-pick").attr('data-selectedOrg'));
       
    $('#academic').click(filterCheckbox);
    $('#nonacademic').click(filterCheckbox);
    $("#org-pick").change(filterCheckbox);
    
    function filterCheckbox () {
        $(this).find('i').toggle();
        
        var nonacad = $('#nonacademic').find('i');
        var acad = $('#academic').find('i');

        if(acad.is(':visible') && nonacad.is(':visible'))
            filter = 'all';
        if(acad.is(':visible') && !nonacad.is(':visible'))
            filter = 'acad';
        if(!acad.is(':visible') && nonacad.is(':visible'))
            filter = 'non-acad';    
        if(!acad.is(':visible') && !nonacad.is(':visible')) {
            if($(this).attr('id') == acad.parent().attr('id')) { 
                nonacad.show();
                filter = 'non-acad';
            } else {
                acad.show();
                filter = 'acad';
            }
        }

        table.draw();
    }
    
    $('.half input').prop('disabled', true);
    $('.half textarea').prop('disabled', true);
    $('#table_submissions tbody').on('click', 'button.deletesub', function(){
        event.stopImmediatePropagation();
        event.stopPropagation();
        var docuID = $(this).attr('data-docuID');
        modalMessage('Are you sure you want to delete this submission?',
        "No",
        function(){
            $('#modal-action').remove();
        },
        "Yes",
        function(){
            $.ajax({ 		
                type        : 'POST', 		
                url         : 'deleteSubmission',		
                data        : {subKey:docuID},		
                dataType    : 'json',		
                success     : function(data) {
                    if (data.msg == 'true') {
                        $('tr[data-docuID='+ docuID + ']').remove();
                        $('#modal-action').remove();
                    } else {
                        console.log("Failed to delete the submission!");
                    }
                }
            });
        }, true);
    });

    $('#table_submissions tbody').on('click', '.clickable', function() {
        if($('#modal-action').is(':visible')) {
            return;
        }
        
        var docuID = $(this).attr('data-docuID');
        clickedRow = $(this);
        subKey = docuID;

        $('#modal-content input').val('');
        $('#modal-content textarea').val("");
        $('#modal-content select').val('');
    	
        $.ajax({ 		
			type        : 'POST', 		
			url         : 'modalData',		
			data        : {docuID:docuID},		
			dataType    : 'json',		
	 		success     : function(data) {
                checkerName = data.checkerName;
                datetimeNow = data.now;
                $("#term").val(data.sub.term);
	 			$("#act-name").val(data.sub.title);
	 			$("#org-name").val(data.sub.org.name);
	 			
	 			$("#time").val(data.sub.act_time);
	 			$("#venue").val(data.sub.act_venue);
	 			$("#nature").val(data.sub.act_nature);
	 			$("#type").val(data.sub.act_type);
	 			$("#actDate").val(data.sub.act_date);
	 			
                $("#submissionType").val(data.sub.sub_type);
                if(data.sub.submittedBy != null) 
	 			    $("#submittedBy").val(data.sub.submittedBy.name);
                $("#submitDate").val(data.sub.timestamp);
	 			$("#typeSAS").val(data.sub.type_sas);
                
                if(data.org_id == "org_1") {
                    $('#resubmit').hide();
                    $('#editSub').hide();
                    $('#cancelAct').hide();
                    if(data.sub.status == '' || data.sub.status == null || data.sub.status == '-') {
                        check = true;
                        recheck = false;
                        $('#check').html('<i class = "fa fa-check"> </i> CHECK');
                        $('#check').show();
                        $('#recheck').hide();
                    } else {
                        recheck = true;
                        check = false;
                        $('#recheck').html('<i class = "fa fa-check-circle"> </i> RECHECK');
                        $('#recheck').show();
                        $('#check').hide();
                    }
                }
                else {
                    $('#check').hide();
                    $('#recheck').hide();
                    $('#editSub').hide();
                    $('.deletesub').hide();
                    
                    $("#cancelAct").prop('disabled', false);
                    if(data.sub.status == 'EARLY APPROVED' || data.sub.status == 'LATE APPROVED') {
                        click = true;
                        $('#editSub').html('IN CASE OF CHANGE');
                        $('.eSub').prop('disabled', true);
                        $('#resubmit').hide();
                        $('#editSub').show();
                        
                    } else if(data.sub.status == 'PENDED') {
                        $('#resubmit').show();
                        $("#resubmit").prop('disabled', false);
                    } else {
                        $('#resubmit').hide();

                    }
                }

                $('#checker input').prop('disabled', true);
                $('#checker textarea').prop('disabled', true);
                $('#checker select').prop('disabled', true);
                
                if(data.sub.checker != null) {            
                    $("#checkedBy").val(data.sub.checker.name);
                    $("#dateChecked").val(data.sub.datetime_checked);
                    $('#status').val(data.sub.status.toUpperCase());
                    
                    
                } else {
                    $("#checkedBy").val('');
                    $("#dateChecked").val('');
                    $('#status').val('-'); 
                }
                
                if(data.sub.remarks != null)
                    $("#remarks").val(data.sub.remarks);
                else
                    $("#remarks").val('');
                
                $('#modal-view').fadeIn();
            },
			error   	: function(xhr,status,error){		
				console.log(xhr);   		
				alert(status);		
			}
    	});
        

        
        $('body').css('overflow','hidden');
    });
    
        $('#check').click(function(){
            if(check) {
                $("#checkedBy").val(checkerName);
                $("#dateChecked").val(datetimeNow);
               // $('#checker input').prop('disabled', false);
                $('#checker textarea').prop('disabled', false);
                $('#checker select').prop('disabled', false);
                $(this).html('<i class = "fa fa-save"> </i> SUBMIT');
            } else {
                 modalMessage('Are you sure you want to change the checking details?',
                "No",
                function(){
                    $('#modal-action').remove();
                },
                "Yes",
                function(){

                    var remarks = $('#checker textarea').val();
                    var status = $('#checker select').val();

                    $.ajax({ 		
                        type        : 'POST', 		
                        url         : 'checkSubmission',		
                        data        : {subKey:subKey, remarks:remarks, status:status},		
                        dataType    : 'json',		
                        success     : function(data) {
                            if (data.msg == 'true') {
                                $(clickedRow).find("td:eq(3)").removeClass();
                                $(clickedRow).find("td:eq(3)").text(status);
                                setClass($(clickedRow).find("td:eq(3)"), status);
                                
                                $('#checker input').prop('disabled', true);
                                $('#checker textarea').prop('disabled', true);
                                $('#checker select').prop('disabled', true);
                                $('#recheck').show();
                                $('#check').hide();
                                $('#check').html('<i class = "fa fa-check"> </i> CHECK');
                                $('#modal-action').remove();
                            } else {
                                console.log("Failed to check the submission!");
                            }
                        }
                    });
                }, true);
            }
            check = !check;
        });
        
        $('#recheck').click(function(){
            if(recheck) {
                $("#checkedBy").val(checkerName);
                $("#dateChecked").val(datetimeNow);
                //$('#checker input').prop('disabled', false);
                $('#checker textarea').prop('disabled', false);
                $('#checker select').prop('disabled', false);
                $(this).html('<i class = "fa fa-save"> </i> SAVE');
            } else {
                modalMessage('Are you sure you want to change the checking details?',
                "No",
                function(){
                    $('#modal-action').remove();
                },
                "Yes",
                function(){
                    
                    var remarks = $('#checker textarea').val();
                    var status = $('#checker select').val();

                    $.ajax({ 		
                        type        : 'POST', 		
                        url         : 'checkSubmission',		
                        data        : {subKey:subKey, remarks:remarks, status:status},		
                        dataType    : 'json',		
                        success     : function(data) {
                            if (data.msg == 'true') {
                                $(clickedRow).find("td:eq(3)").removeClass();
                                $(clickedRow).find("td:eq(3)").text(status);
                                setClass($(clickedRow).find("td:eq(3)"), status);

                                $('#checker input').prop('disabled', true);
                                $('#checker textarea').prop('disabled', true);
                                $('#checker select').prop('disabled', true);
                                $('#recheck').html('<i class = "fa fa-check-circle"> </i> RECHECK');
                                $('#modal-action').remove();
                            } else {
                                console.log("Failed to recheck the submission!");
                            }
                        }
                    });

                    
                }, true);
            }
            recheck = !recheck;
        });

           
    $(document).keyup(function(e){ 
        if (e.keyCode === 27) {
            $('#modal-view').fadeOut();
            $('body').css('overflow','auto');
        }
    });
    
    $('#modal-close').click(function(){
        $('#modal-view').fadeOut(500, function(){
        	clearModal();
        });
		
        $('body').css('overflow','auto');
    });

    

    $('button#editSub').click(function(){
        
        
        var docuID = $(this).attr('data-docuID');      
        if(click) {
           // $('#checker input').prop('disabled', false);
           $('.eSub').prop('disabled', false);
            $(this).html('<i class = "fa fa-save"> </i> SUBMIT');
            $("#resubmit").prop('disabled', true);
            $("#cancelAct").prop('disabled', true);
        } else {
             modalMessage('Are you sure you want to change the document details?',
            "No",
            function(){
                $('#modal-action').remove();
            },
            "Yes",
            function(){
                newSubmission("Special Approval Slip", "In Case of Change");
                
            }, true);
        }
        click = !click;
                     

    });

    $('button#resubmit').click(function(){
        
            
        $('body').css('overflow', 'none');
        modalMessage("Are you sure you want to resubmit the document for " + $('#act-name').val() + "?",
                     "No",
                     function(){
                        $('body').css('overflow', 'auto');
                        $('#modal-action').remove();
                    },
                     "Yes",
                     function(){
                        newSubmission("Pended", "N/A");
                    },
                     false);
                     

    });

    $('button#cancelAct').click(function(){
        
            
        $('body').css('overflow', 'none');
        modalMessage("Are you sure you want to cancel " + $('#act-name').val() + "?",
                     "No",
                     function(){
                        $('body').css('overflow', 'auto');
                        $('#modal-action').remove();
                    },
                     "Yes",
                     function(){
                        newSubmission("Special Approval Slip", "Cancellation of Activity");
                    },
                     false);
                     

    });
});

function newSubmission(typeSub, typeSAS) {
    var term = $('#details #term').val();
    var title = $('#details #act-name').val();
    var orgName = $('#details #org-name').val();
    var actDate = $('#details #actDate').val();
    var time = $('#details #time').val();
    var venue = $('#details #venue').val();
    var nature = $('#details #nature').val();
    var type = $('#details #type').val();

    $.ajax({ 		
        type        : 'POST', 		
        url         : 'resubmitSubmission',		
        data        : {term:term, act_title:title, act_date:actDate, act_time:time, act_nature:nature,
                       act_type:type, act_venue:venue, type_sub:typeSub, 
                       type_sas:typeSAS, org:orgName},
        dataType    : 'json',		
         success     : function(data) {
            var message = "";

            if(data.msg)
                message = "Successfully sent a submission! Your submission will be checked within 3 - 5 working days.";
            else message = "Submission not added. Please supply missing details";
            $('#editSub').html('IN CASE OF CHANGE');
            $('#modal-action').remove();
            $('.eSub').prop('disabled', true);

            $('body').css('overflow', 'hidden');
            modalMessage(message,
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
                console.log(xhr.responseText);
                console.log(error);   		
                alert(status);		
        }
    });
}

function addRow(key, sub) {
    var classs;

    switch (sub.status) {
        case "EARLY APPROVED" : classs = "early_approved";
        break;
    case "LATE APPROVED" : classs = "late_approved";
        break;
    case "PENDED" : classs = "pending";
        break;
    case "DENIED" : classs = "denied";
        break;
    default : sub.status = "No Checkers Yet";
    }

    if(user.org.privilege.toUpperCase() == 'ADMIN') {    
        var rowNode = table.row.add([
            sub.timestamp,
            sub.org.name,
            sub.title,
            sub.status,
            '',
            sub.org_id,
            sub.nature.toUpperCase()=='ACADEMIC' 
        ]).node();
        $(rowNode).find("td:nth-child(5)").html('<button class = "deletesub"> <i class = "fa fa-trash"> </i> </button>');
    } else {
        var rowNode = table.row.add([
            sub.timestamp,
            sub.org.name,
            sub.title,
            sub.status,
            sub.org_id,
            sub.nature.toUpperCase()=='ACADEMIC' 
        ]).node();
    }
    
    $(rowNode).attr('data-docuID', key);
    $(rowNode).addClass('clickable');
    $(rowNode).find("td:nth-child(4)").addClass(classs);
}

function clearModal () {
    $('#modal-view .field').val('');
}

function setClass(td, status) {
    switch (status) {
        case "EARLY APPROVED": $(td).addClass('early_approved');
            break;
        case "LATE APPROVED": $(td).addClass('late_approved');
            break;
        case "PENDING": $(td).addClass('pending');
            break;
        case "DENIED": $(td).addClass('denied');
            break;
        default:
    }
}