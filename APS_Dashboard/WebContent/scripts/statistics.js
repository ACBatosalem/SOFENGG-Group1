$(document).ready(function() {
        var data = {
          series: [{
              value: acad,
              className: "acad"
          }, {
              value: nonacad,
              className: "non-acad"
          }]
        };

        var sum = function(a, b) { return a + b };

        new Chartist.Pie('#chart', data, { 
            labelInterpolationFnc: function(value) {
            	if(Math.round(parseFloat(value) / (parseFloat(acad)+parseFloat(nonacad)) * 100) != 0)
                	return Math.round(parseFloat(value) / (parseFloat(acad)+parseFloat(nonacad)) * 100) + '%';
            	else 
            		return "";
            }
        });

});