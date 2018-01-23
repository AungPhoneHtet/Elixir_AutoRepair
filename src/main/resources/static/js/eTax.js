$(function () {
    $('#myDatepicker2').datetimepicker({
        format: 'DD.MM.YYYY'
    });
    initFormload();
    $("#TaxType").change(function () {
        var e = document.getElementById("TaxType");
        var strUser = e.options[e.selectedIndex].value;
        if (strUser == "CG") {
            document.getElementById("idtimeGroup").style.display = "block";
            document.getElementById("idPayType").style.display = "block";
            document.getElementById("idDate").style.display = "block";
            document.getElementById("idMonth").style.display = "none";
            document.getElementById("idlblDatetime").style.display = "block";
            document.getElementById("idincomeyr").style.display = "block";
            removepaytype();
        } else if (strUser == "CT") {
            document.getElementById("idtimeGroup").style.display = "block";
            document.getElementById("idPayType").style.display = "block";
            document.getElementById("idDate").style.display = "none";
            document.getElementById("idMonth").style.display = "block";
            document.getElementById("idlblDatetime").style.display = "block";
            document.getElementById("idincomeyr").style.display = "block";
            removepaytype();
        } else if (strUser == "SGT") {
            document.getElementById("idtimeGroup").style.display = "block";
            document.getElementById("idPayType").style.display = "block";
            document.getElementById("idDate").style.display = "none";
            document.getElementById("idMonth").style.display = "block";
            document.getElementById("idlblDatetime").style.display = "block";
            document.getElementById("idincomeyr").style.display = "block";
            removepaytype();
        } else if (strUser == "SD") {
            document.getElementById("idtimeGroup").style.display = "none";
            document.getElementById("idPayType").style.display = "block";
            document.getElementById("idlblDatetime").style.display = "none";
            document.getElementById("idDate").style.display = "none";
            document.getElementById("idMonth").style.display = "none";
            document.getElementById("idincomeyr").style.display = "none";
            removepaytype();
        } else if (strUser == "IT") {
            document.getElementById("idtimeGroup").style.display = "block";
            document.getElementById("idPayType").style.display = "block";
            document.getElementById("idDate").style.display = "none";
            document.getElementById("idMonth").style.display = "none";
            document.getElementById("idlblDatetime").style.display = "none";
            document.getElementById("idlblQuarter").style.display = "block";
            document.getElementById("idDivQuarter").style.display = "block";
            document.getElementById("idincomeyr").style.display = "block";
            var x = document.getElementById("PayType");
            var option = document.createElement("option");
            option.text = "PAYE";
            option.value = "PA";
            x.add(option);
            var option = document.createElement("option");
            option.text = "WithHolding Tax";
            option.value = "HT";
            x.add(option);
        }
    });
});

function initFormload() {
    $("#idDivQuarter").hide();
    $("#idMonth").hide();
    $("#idDivQuarter").hide();
    $("#idlblQuarter").hide();


}

function removepaytype() {
    document.getElementById("idlblQuarter").style.display = "none";
    document.getElementById("idDivQuarter").style.display = "none";
    var varpay = document.getElementById("PayType");
    for (var i = 0; i < varpay.length; i++) {
        if (varpay.options[i].value == 'PA')
            varpay.remove(i);
        if (varpay.options[i].value == 'HT')
            varpay.remove(i);
    }
}