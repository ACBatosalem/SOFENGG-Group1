$(document).ready(function() {
    // var data = {
    //     series: [{
    //         value: academic,
    //         className: "acad"
    //     }, {
    //         value: nonacademic,
    //         className: "non-acad"
    //     }]
    //   };

    //   var sum = function(a, b) { return a + b };

    //   new Chartist.Pie('#chart', data, { 
    //       labelInterpolationFnc: function(value) {
    //           if(Math.round(parseFloat(value) / (parseFloat(academic)+parseFloat(nonacademic)) * 100) != 0)
    //               return Math.round(parseFloat(value) / (parseFloat(academic)+parseFloat(nonacademic)) * 100) + '%';
    //           else 
    //               return "";
    //       }
    //   });
    
    Highcharts.chart('chart', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            style: {
                color: "#35865C",
                fontFamily: "Montserrat"
            },
            text: 'Ratio of Academic and Non-academic Submissions'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>',
            style: {
                color: '#35865C',
                fontFamily: "Montserrat"
            }
        },
        plotOptions: {
            pie: {
                colors: ['#64865C', '#4F9A63'],
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} ({point.y}) %',
                    style: {
                        fontSize: "15px",
                        fontFamily: "Palanquin",
                        color: '#35865C'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [{
                name: 'Academic',
                y: parseInt(academic)
            }, {
                name: 'Non-academic',
                y: parseInt(nonacademic)
            }]
        }]
    });

    //   var data = {
    //     series: [{
    //         value: academic,
    //         className: "acad"
    //     }, {
    //         value: nonacademic,
    //         className: "non-acad"
    //     }]
    //   };

    //   var sum = function(a, b) { return a + b };

    //   new Chartist.Pie('#chart', data, { 
    //       labelInterpolationFnc: function(value) {
    //           if(Math.round(parseFloat(value) / (parseFloat(academic)+parseFloat(nonacademic)) * 100) != 0)
    //               return Math.round(parseFloat(value) / (parseFloat(academic)+parseFloat(nonacademic)) * 100) + '%';
    //           else 
    //               return "";
    //       }
    //   });

});