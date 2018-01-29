convertFormToJSONObject = function (form) {
    var object = {};
    var inputElements = $(':input', form).get();

    $.each(inputElements, function () {
        var inputElement = $(this);
        var value;

        switch (this.type) {
            case "checkbox":
            case "radio":
                value = input.is(":checked") ? inputElement.val() : null;
                break;
            default:
                value = $(this).val();
        }

        if (this.name !== "") {
            object[this.name] = value;
        }
    });

    return object;
};