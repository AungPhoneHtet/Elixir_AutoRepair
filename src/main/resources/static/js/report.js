$(function () {
    $('#reportingPeriod').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        singleDatePicker: true,
        singleClasses: "picker_2"
    });

    $('#fromPeriod').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        singleDatePicker: true,
        singleClasses: "picker_2"
    });

    $('#toPeriod').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        singleDatePicker: true,
        singleClasses: "picker_2"
    });
});