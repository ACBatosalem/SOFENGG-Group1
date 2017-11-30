$(document).ready(function() {

    $("#sub_type").on("change", function(){
        if (this.value == "Special Approval Slip")
                $("div#type_sas").show();
        else {
            $("div#type_sas").hide();
            $("#act_details").show();
        }
    });

    //In case of change, not in gosm
    $("select#type_sas").on("change", function(){
        if (this.value == "In Case of Change" || this.value == "Activity Not in GOSM")
            $("#act_details").show();
        else $("#act_details").hide();
    });
});