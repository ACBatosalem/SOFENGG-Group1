$(document).ready(function() {
        var data = {
          series: [{
              value: 20,
              className: "acad"
          }, {
              value: 25,
              className: "non-acad"
          }]
        };

        var sum = function(a, b) { return a + b };

        new Chartist.Pie('#chart', data, { 
            labelInterpolationFnc: function(value) {
                return Math.round(parseFloat(value) / 45 * 100) + '%';
            }
        });

});