$(document).ready(function(){
    $('#acads-tab-btn').click(function() {
        $('#acads-tab').show();
        $('#act-tab').hide();
        
        $('#acads-tab-btn').addClass('selected');
        $('#act-tab-btn').removeClass('selected');
        
        $('.options-box').hide();
    });
    
    $('#act-tab-btn').click(function() {
        $('#acads-tab').hide();
        $('#act-tab').show();
        
        $('#acads-tab-btn').removeClass('selected');
        $('#act-tab-btn').addClass('selected');
        
        $('.options-box').show();
    });
    
    $("input:checkbox").on('click', function() {
        var $box = $(this);
        if ($box.is(":checked")) {
            var group = "input:checkbox[name='" + $box.attr("name") + "']";
            $(group).prop("checked", false);
            $box.prop("checked", true);
        } else {
            $box.prop("checked", false);
        }
    });
    
    $('#close-modal').on('click', function() {
         $('#activity-modal').fadeOut();
    });
    
    $('tr').on('click', function() {
        $('#activity-modal').fadeIn();
    })
    
    $('#notif').on('click', function(){
        $('#notification-panel').slideToggle();
        $('#account-settings').slideUp();
    })
    
    $('#user-settings').click(function(){
        $('#account-settings').slideToggle(); $('#notification-panel').slideUp();
    });
});

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Activities', 'No. Of Activity'],
  ['Non-academic', 120],
  ['Academic', 221]
]);
    
  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Academic vs. Non-Academic Ratio', 'width': 500, 'height':300, is3D: true};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('acads-tab'));
  chart.draw(data, options);
}