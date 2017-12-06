var check;
$(document).ready(function() {

    $("#org-pick").val('YAYCLUB');

    var table= $('#table_submissions').DataTable( {
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
            "lergthMenu": "Display _MENU_ records"
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
    
    $('#non-acad-box').change(function() {
        var orgID = $("#org-pick option:selected").attr('data-orgid');
        var filter;
    	if ($(this).is(':checked') && $("#acad-box").is(':checked')) {
            filter = "all";
        } else if ($(this).is(':checked')) {
            filter = "non-acad";
        } else if ($("#acad-box").is(':checked')) {
            filter = "acad";
        } else {
            $("#acad-box").prop('checked', true);
            filter = "acad";
        }

        console.log(filter);
        console.log(orgID);

        filterSubmissions(filter, orgID);
    });
    
    
    $('#acad-box').change(function() {
    	var orgID = $("#org-pick option:selected").attr('data-orgid');
        var filter;
    	if ($(this).is(':checked') && $("#non-acad-box").is(':checked')) {
            filter = "all";
        } else if ($(this).is(':checked')) {
            filter = "acad";
        } else if ($("#non-acad-box").is(':checked')) {
            filter = "non-acad";
        } else {
            $("#non-acad-box").prop('checked', true);
            filter = "non-acad";
        }

        console.log(filter);
        console.log(orgID);

        filterSubmissions(filter, orgID);
    });
    
    $("#org-pick").change(function() {
    	var orgID = $("#org-pick option:selected").attr('data-orgid');
        var filter;
    	if ($("#non-acad-box").is(':checked') && $("#acad-box").is(':checked')) {
            filter = "all";
        } else if ($("#non-acad-box").is(':checked')) {
            filter = "non-acad";
        } else if ($("#acad-box").is(':checked')) {
            filter = "acad";
        }

        console.log(filter);
        console.log(orgID);

        filterSubmissions(filter, orgID);
    });
    
    $('.half input').prop('disabled', true);
    $('.half textarea').prop('disabled', true);
    
    $('#check').on('click', function(){
        
    });
    
    $('#table_submissions tbody').on('click', 'tr', function() {
    	var docuID = $(this).attr('data-docuID');
        $('#modal-content input').val('');
        $('#modal-content textarea').val("");
        $('#modal-content select').val('');
    	
        $.ajax({ 		
			type        : 'POST', 		
			url         : 'modalData',		
			data        : {docuID:docuID},		
			dataType    : 'json',		
	 		success     : function(data) {
	 			$("#act-name").val(data.title);
	 			$("#org-name").val(data.submittedBy.org.name);
	 			
	 			$("#time").val(data.act_time);
	 			$("#venue").val(data.act_venue);
	 			$("#nature").val(data.act_nature);
	 			$("#type").val(data.act_type);
	 			$("#actDate").val(data.act_date);
	 			
                $("#submissionType").val(data.sub_type);
	 			$("#submittedBy").val(data.submittedBy.name);
	 			$("#submitDate").val(data.timestamp);
	 			$("#typeSAS").val(data.type_sas);
	 			
                if(data.status == 'PENDING') {
                    $('#resubmit').show();
                } else {
                    $('#resubmit').hide();
                }
                
                if(data.status == '' || data.status == null || data.status == '-') {
                    $('#check').show();
                    $('#recheck').hide();
                } else {
                    $('#recheck').show();
                    $('#check').hide();
                }

                $('#checker input').prop('disabled', true);
                $('#checker textarea').prop('disabled', true);
                $('#checker select').prop('disabled', true);
                
                if(data.checker != null) {            
                    $("#checkedBy").val(data.checker.name);
                    $("#dateChecked").val(data.datetime_checked);
                    $('#status').val(data.status.toUpperCase());
                    
                    
                } else {
                    $("#checkedBy").val('');
                    $("#dateChecked").val('');
                    $('#status').val('-'); 
                }
                
                if(data.remarks != null)
                    $("#remarks").val(data.remarks);
                else
                    $("#remarks").val('');
			},
			error   	: function(xhr,status,error){		
				console.log(xhr);   		
				alert(status);		
			}
    	});
    	
    	$('#modal-view').fadeIn();
        
        var check = true;
        $('#check').click(function(){
            if(check) {
                $('#checker input').prop('disabled', false);
                $('#checker textarea').prop('disabled', false);
                $('#checker select').prop('disabled', false);
                $(this).html('<i class = "fa fa-save"> </i> SUBMIT');
            } else {
                 modalMessage('Are you sure you want to change the checking details?',
                "No",
                function(){
                    
                },
                "Yes",
                function(){
                    $('#checker input').prop('disabled', true);
                    $('#checker textarea').prop('disabled', true);
                    $('#checker select').prop('disabled', true);
                    $('#check').html('<i class = "fa fa-check"> </i> RECHECK');
                    $('#modal-action').remove();
                }, true);
            }
            check = !check;
        });
        
        var recheck = true;
        $('#recheck').click(function(){
            if(recheck) {
                $('#checker input').prop('disabled', false);
                $('#checker textarea').prop('disabled', false);
                $('#checker select').prop('disabled', false);
                $(this).html('<i class = "fa fa-save"> </i> SUBMIT');
            } else {
                modalMessage('Are you sure you want to change the checking details?',
                "No",
                function(){
                    
                },
                "Yes",
                function(){
                    $('#checker input').prop('disabled', true);
                    $('#checker textarea').prop('disabled', true);
                    $('#checker select').prop('disabled', true);
                    $('#recheck').html('<i class = "fa fa-check"> </i> RECHECK');
                    $('#modal-action').remove();
                }, true);
            }
            recheck = !recheck;
        });
        $('body').css('overflow','hidden');
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
    var tr = document.createElement("tr");
    var time = document.createElement("td");
    var subBy = document.createElement("td");
    var title = document.createElement("td");
    var status = document.createElement("td");

    $(tr).attr('data-docuID', key);
    $(time).text(sub.timestamp);
    $(subBy).text(sub.submittedBy.org.name);
    $(title).text(sub.title);
    $(status).text(sub.status);

    switch (sub.status) {
        case "EARLY APPROVED" : $(status).addClass("early_approved");
            break;
        case "LATE APPROVED" : $(status).addClass("late_approved");
            break;
        case "PENDING" : $(status).addClass("pending");
            break;
        case "DENIED" : $(status).addClass("denied");
            break;
        default : $(status).text("No Checkers Yet");
    }

    $(tr).append(time);
    $(tr).append(subBy);
    $(tr).append(title);
    $(tr).append(status);
    $("#tbody").append(tr);
}

function filterSubmissions(filter, orgID) {
    $.ajax({
        type        : 'POST', 
        url         : 'filterSubmissions',
        data        : {filter:filter, orgID:orgID},
        dataType    : 'json',
        success     : function(subs) {
            console.log(subs);
            if (subs != "false") {
                $('#tbody tr').empty();
                for (var key in subs) {
                    addRow(key, subs[key]);
                } 
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