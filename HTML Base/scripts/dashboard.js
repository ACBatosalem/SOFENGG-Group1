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
        "language": {
            "emptyTable": "No data",
            "info": "Showing page _PAGE_ of _PAGES_",
            "lergthMenu": "Display _MENU_ records"
        }
    }).columns.adjust().draw();

    
    $('#acad-box').prop('checked', true);
    $('#non-acad-box').prop('checked', true);
    $('#table_submissions tbody').on('click', 'tr', function() {
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
