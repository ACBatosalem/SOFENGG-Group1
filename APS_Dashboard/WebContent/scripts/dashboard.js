
$(document).ready(function() {
    var table= $('#table_submissions').DataTable( {
        "scrollCollapse": true,
        "ordering": true,
        "sScrollX": false,
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "pageLength": 15,
        "sScrollX": "100%",
        "scrollX": "100%",
        "autoWidth": false,
        "pageLength": 10,
        "language": {
            "info": "Showing page _PAGE_ of _PAGES_",
            "lergthMenu": "Display _MENU_ records"
        }
    }).columns.adjust().draw();
    
    $(window).resize(function(){
        table.columns.adjust().draw();
    });
    
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
        
        window.location = window.location.origin + "/APS_Dashboard/homeAPS?filter=" + f + "&org=" + org;
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
    	
    	window.location = window.location.origin + "/APS_Dashboard/homeAPS?filter=" + f + "&org=" + org;
    });
    
    $('#table_submissions tbody').on('click', 'tr', function() {
    	var data = $(this).attr('data-dashdata');
    	console.log(data);
    	$("#act-name").text(data.title);
    	$("#org-name").text(data.userName);
    	
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
        $('#modal-view').fadeOut();
        $('body').css('overflow','auto');
    });
});
