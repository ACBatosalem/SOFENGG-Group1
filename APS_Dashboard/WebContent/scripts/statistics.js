$(document).ready(function() {
    var ctx = document.getElementById("acads-non").getContext('2d');
    var data = {
        datasets: [{
            data: [acad, nonacad],
            backgroundColor: ['#64865C', '#1F804E'],
        }],
        labels: ['Academic', 'Non-academic']
    };
    
    var myPieChart = new Chart(ctx,{
        type: 'pie',
        data: data,
        options: {
            tooltips: {
                enabled: false
            }
        }
    });
});