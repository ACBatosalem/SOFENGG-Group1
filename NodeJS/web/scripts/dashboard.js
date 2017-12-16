var check;
var recheck;
var table;
$(document).ready(function() {

    $("#org-pick").val('YAYCLUB');

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
    
    $('button#resubmit').on('click', function(event){
        event.stopPropagation();
        
        var docuID = $(this).attr('data-docuID');      
        $('body').css('overflow', 'none');
        modalMessage("Are you sure you want to resubmit the document for " + $('#act-name').val() + "?",
                     "No",
                     function(){
                        $('body').css('overflow', 'auto');
                        $('#modal-action').remove();
                    },
                     "Yes",
                     function(){
            
                    },
                     false);
                     

    });
    
    $('#academic').click(filterCheckbox);
    $('#nonacademic').click(filterCheckbox);
    $("#org-pick").change(filterCheckbox);
    
    function filterCheckbox () {
        var orgID = $("#org-pick option:selected").attr('data-orgid');
        var nonacad = $('#nonacademic').find('i');
        var acad = $('#academic').find('i');
        var filter = "";
        
        $(this).find('i').toggle();
        
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
        
        console.log(filter);
        filterSubmissions(filter, orgID);
    }
    
    $('.half input').prop('disabled', true);
    $('.half textarea').prop('disabled', true);
    
    $('#table_submissions tbody').on('click', '.clickable', function() {
        var docuID = $(this).attr('data-docuID');
        console.log(docuID)
        $('#modal-content input').val('');
        $('#modal-content textarea').val("");
        $('#modal-content select').val('');
    	
        $.ajax({ 		
			type        : 'POST', 		
			url         : 'modalData',		
			data        : {docuID:docuID},		
			dataType    : 'json',		
	 		success     : function(data) {
	 			$("#act-name").val(data.sub.title);
	 			$("#org-name").val(data.sub.org.name);
	 			
	 			$("#time").val(data.sub.act_time);
	 			$("#venue").val(data.sub.act_venue);
	 			$("#nature").val(data.sub.act_nature);
	 			$("#type").val(data.sub.act_type);
	 			$("#actDate").val(data.sub.act_date);
	 			
                $("#submissionType").val(data.sub.sub_type);
	 			$("#submittedBy").val(data.sub.submittedBy.name);
	 			$("#submitDate").val(data.sub.timestamp);
	 			$("#typeSAS").val(data.sub.type_sas);
                
                if(data.org_id == "org_1") {
                    $('#resubmit').hide();
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
                    if(data.sub.status == 'PENDING') {
                        $('#resubmit').show();
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
			},
			error   	: function(xhr,status,error){		
				console.log(xhr);   		
				alert(status);		
			}
    	});
    	
    	$('#modal-view').fadeIn();
        $('body').css('overflow','hidden');
    });
    
        $('#check').click(function(){
            if(check) {
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
                        data        : {remarks:remarks, status:status},		
                        dataType    : 'json',		
                        success     : function(data) {
                            if (msg == 'true') {
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
                        data        : {remarks:remarks, status:status},		
                        dataType    : 'json',		
                        success     : function(data) {
                            if (msg == 'true') {
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
        	$("#act-name").text("Activity Title");
    		$("#org-name").text("Organization");
    		
    		$("#time").text("Time: ");
    		$("#venue").text("Venue: ");
    		$("#nature").text("Nature of Activity: ");
    		$("#type").text("Type of Activity: ");
    		$("#actDate").text("Activity Date/s: ");
    		
            $("#submissionType").text("Submission Type: ");
    		$("#submittedBy").text("Submitted by: ");
    		$("#submitDate").text("Date Submitted: ");
    		$("#typeSAS").text("Type of SAS Submission: ");
    		
    		$("#checkedby").text("Checked by: ");
    		$("#dateChecked").text("Date Checked: ");
    		$("#remarks").text("Remarks: ");
        });
		
        $('body').css('overflow','auto');
    });
});

function addRow(key, sub) {
    var classs;

    switch (sub.status) {
        case "EARLY APPROVED" : classs = "early_approved";
        break;
    case "LATE APPROVED" : classs = "late_approved";
        break;
    case "PENDING" : classs = "pending";
        break;
    case "DENIED" : classs = "denied";
        break;
    default : sub.status = "No Checkers Yet";
    }

    var rowNode = table.row.add([
        sub.timestamp,
        sub.submittedBy.org.name,
        sub.title,
        sub.status
    ]).node();
    
    $(rowNode).attr('data-docuID', key);
    $(rowNode).addClass('clickable');
    $(rowNode).find("td:nth-child(4)").addClass(classs);
}

function filterSubmissions(filter, orgID) {
    $.ajax({
        type        : 'POST', 
        url         : 'aps/filterSubmissions',
        data        : {filter:filter, orgID:orgID},
        dataType    : 'json',
        success     : function(subs) {
            if (subs != "false") {
                table.clear();
                for (key in subs) {
                    addRow(subs[key].key, subs[key]);
                } 
                table.draw();
            } else {
                window.location = context + '/home';
            }
        },
        error   : function(xhr,status,error){
            console.log("error: " + xhr.responsetext);
            alert(status);
            alert(error);
        }
    });
}