$(document).ready(function(){
    var curr = null;
    $.ajax({
        url: "https://docs.google.com/spreadsheets/d/1Yn29ak76Dy4mVlcLvH6V5rI7Jj7CLzyL7PNpoYi0w4U/export?format=tsv&gid=0",
        success: function (output) {
            curr = output;
            addNotification(changes(curr, output));
        }
    });
    
    function checkTime () {
        $.ajax({
            url: "notifyOrganization",
            success: function (output) {
                if(output) {
                    
                }
            }
        });
    }
    
    setInterval(function(){
        $.ajax({
            url: "https://docs.google.com/spreadsheets/d/1Yn29ak76Dy4mVlcLvH6V5rI7Jj7CLzyL7PNpoYi0w4U/export?format=tsv&gid=0",
            success: function (output) {
                if(curr == null) {
                    curr = output;
                } else if (curr != output) {
                    addNotification(changes(curr, output))
                    curr = output;
                }
            }
        });
    }, 1000);
    
    function changes (curr, output) {
        if(curr == output)
            return "initial";
        else {    
            var arr = csvToArray(curr);
            var outArr = csvToArray(output);
            var indecesChanged = "";
            var i = 0, j = 0;
        
            stop = false;
            while (!stop) {
                if(arr.length <= i && outArr.length <= i) {
                    stop = true;
                } else if (arr[0].length <= j && outArr[0].length <= j) {
                    j=0;
                    i++;
                } else if(arr.length >= i && outArr.length <= i) {
                    if (arr[i][j] != '')
                        indecesChanged += "[N|" + i + ", " + j + "]";
                    i++;
                } else if(arr.length <= i && outArr.length >= i) {
                    if (outArr[i][j] != '')
                        indecesChanged += "[D|" + i + ", " + j + "]";
                    i++;
                } else if(arr[0].length >= j && outArr[0].length <= j) {
                    if (arr[i][j] != '')
                        indecesChanged += "[N|" + i + ", " + j + "]";
                    j++;
                } else if(arr[0].length <= j && outArr[0].length >= j) {
                    if (outArr[i][j] != '')
                        indecesChanged += "[D|" + i + ", " + j + "]";
                    j++;
                } else {
                    if (arr[i][j] == '' && arr[i][j] != outArr[i][j])
                        indecesChanged += "[N|" + i + ", " + j + "]";
                    else if (outArr[i][j] == '' && arr[i][j] != outArr[i][j])
                        indecesChanged += "[D|" + i + ", " + j + "]";
                    else if (arr[i][j] != outArr[i][j])
                        indecesChanged += "[U|" + i + ", " + j + "]";
                    j++;
                }
            }
            
            return indecesChanged;
        }
    }
    
    function csvToArray (csv) {
        var arr = [];
        var lines = csv.split('\n');
        for(var line = 0; line < lines.length; line++){
            var tabs = lines[line].split('\t');
            arr.push([]);
            for(var tab = 0; tab < tabs.length; tab++){    
                arr[line].push(tabs[tab]); 
            }   
        }
        return arr;
    }
    
    function addNotification (add) {
        var li = document.createElement('li');
        $(li).html(add);
        $('ul').append(li);
    }
});

//https://docs.google.com/spreadsheets/d/1zKv-LbZydzzL8YoZIHrDUZQYn12_ctCeqFzmuxFt8uM/export?format=csv&gid=0