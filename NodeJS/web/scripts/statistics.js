$(document).ready(function() {
        var data = {
          series: [{
              value: academic,
              className: "acad"
          }, {
              value: nonacademic,
              className: "non-acad"
          }]
        };

        var sum = function(a, b) { return a + b };

        new Chartist.Pie('#chart', data, { 
            labelInterpolationFnc: function(value) {
            	if(Math.round(parseFloat(value) / (parseFloat(academic)+parseFloat(nonacademic)) * 100) != 0)
                	return Math.round(parseFloat(value) / (parseFloat(academic)+parseFloat(nonacademic)) * 100) + '%';
            	else 
            		return "";
            }
        });

});