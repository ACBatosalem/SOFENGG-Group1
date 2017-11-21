var check;
$(document).ready(function() {
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
    
    /*$('#acad-box').prop('checked', true);
    $('#non-acad-box').prop('checked', true);*/
    
    $("#org-pick").val($("#org-pick").attr('data-selectedOrg'));
    
    $('#acad-box').change(function() {
    	var f = "";
        if($("#acad-box").is(":checked") && $('#non-acad-box').is(":checked")) {
        	f = "tt";
        } else if(!($("#acad-box").is(":checked")) && $('#non-acad-box').is(":checked")) {
        	f = "ft";
        } else if($("#acad-box").is(":checked") && !($('#non-acad-box').is(":checked"))) {
        	f = "tf";
        } else if (!($("#acad-box").is(":checked")) && !$('#non-acad-box').is(":checked")) {
        	$('#non-acad-box').prop('checked', true);
        	f = "ft";
        }
        
        var org = $("#org-pick").val();
        
        window.location = context + "/homeAPS?filter=" + f + "&org=" + org;
    });
    
    $('#non-acad-box').change(function() {
    	var f = "";
        if($("#acad-box").is(":checked") && $('#non-acad-box').is(":checked")) {
        	f = "tt";
        } else if(!($("#acad-box").is(":checked")) && $('#non-acad-box').is(":checked")) {
        	f = "ft";
        } else if($("#acad-box").is(":checked") && !($('#non-acad-box').is(":checked"))) {
        	f = "tf";
        } else if (!($("#acad-box").is(":checked")) && !$('#non-acad-box').is(":checked")) {
        	$('#acad-box').prop('checked', true);
        	f = "tf";
        }
        
        var org = $("#org-pick").val();
        
    	window.location = window.location.origin + "/APS_Dashboard/homeAPS?filter=" + f + "&org=" + org;
    });
    
    $("#org-pick").change(function() {
    	var org = this.value;
    	
    	if($("#acad-box").is(":checked") && $('#non-acad-box').is(":checked")) {
        	f = "tt";
        } else if(!($("#acad-box").is(":checked")) && $('#non-acad-box').is(":checked")) {
        	f = "ft";
        } else if($("#acad-box").is(":checked") && !($('#non-acad-box').is(":checked"))) {
        	f = "tf";
        }
    	
    	window.location = context + "/homeAPS?filter=" + f + "&org=" + org;
    });
    
    $('#table_submissions tbody').on('click', 'tr', function() {
    	var docuID = $(this).attr('data-docuID');
    	var orgID = $(this).attr('data-orgID');
    	var actID = $(this).attr('data-actID');
    	var subID = $(this).attr('data-subID');
    	var checkID = $(this).attr('data-checkID');
    	
    	$.ajax({ 		
			type        : 'POST', 		
			url         : 'getModalData',		
			data        : {docuID:docuID, orgID:orgID, actID:actID, subID:subID, checkID:checkID},		
			dataType    : 'json',		
	 		success     : function(data) {
	 			console.log(data);
	 			check = data;
	 			$("#act-name").text(data.document.title);
	 			$("#org-name").text(data.org.userName);
	 			
	 			$("#time").text("Time: " + data.act.time);
	 			$("#venue").text("Venue: " + data.act.venue);
	 			$("#nature").text("Nature of Activity: " + data.act.nature);
	 			$("#type").text("Type of Activity: " + data.act.type);
	 			$("#actDate").text("Activity Date/s: " + data.act.date);
	 			$("#tieup").text("Tie-up: " );
	 			
	 			$("#submittedBy").text("Submitted by: " + data.sub.submittedBy);
	 			$("#submitDate").text("Date Submitted: " + data.sub.dateSubmitted);
	 			$("#typeSAS").text("Type of SAS Submission: " + data.sub.sasType);
	 			
	 			$("#checkedby").text("Checked by: " + data.check.checkerName);
	 			$("#dateChecked").text("Date Checked: " + data.check.dateChecked);
	 			$("#remarks").text("Remarks: " + data.check.remarks);
			},
			error   	: function(xhr,status,error){		
				console.log(xhr);   		
				alert(status);		
			}
    	});
    	
    	$('#modal-view').fadeIn();
    	
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
    		$("#tieup").text("Tie-up: " );
    		
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
