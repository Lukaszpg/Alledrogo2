"use strict";

$(document).ready(function () {
    enableMaterializeSelect();
    initializeInputMasks();
    initializeInputErrorHide();
    initializeSelectErrorHide();
    initializeUserCopyToClipboardButton();
    displaySuccessMessageIfApplicable();
});

var enableMaterializeSelect = function () {
    $("#voivodeshipSelect").material_select();
};

var initializeInputMasks = function () {
    $('#phone').mask('000-000-000');
    $('#secondPhone').mask('000-000-000');
    $("#zipcode").mask("00-000");
};

var initializeInputErrorHide = function () {
    $("input").each(function () {
        $(this).focus(function () {
            $(this).parent().find(".validation-error-input").addClass("hide");
        });
    });
};

var initializeSelectErrorHide = function () {
    $("select").each(function () {
        $(this).change(function () {
            $(this).parent().parent().find(".validation-error-input").addClass("hide");
        });
    });
};

var initializeUserCopyToClipboardButton = function () {
    var whisperClipboard = new Clipboard(".user-details-copy-to-clipboard", {
        text: function (trigger) {
            return constructCopy(trigger);
        }
    });
    whisperClipboard.on("success", function (e) {
        Materialize.toast('Pomyślnie skopiowano do schowka.', 3000, 'toast-success')
    });
    whisperClipboard.on("error", function (e) {
        Materialize.toast('Nie udało się skopać do schowka.', 3000, 'toast-error')
    });
}

var constructCopy = function () {
    var name = $("#firstName").html();
    var surname = $("#surname").html();
    var address = $("#address").html();
    var zipCode = $("#zipCode").html();
    var city = $("#city").html();
    var voivodeship = $("#voivodeship").html();
    var firstPhoneNumber = $("#firstPhoneNumber").html();
    var secondPhoneNumber = $("#secondPhoneNumber").html();
    var company = $("#company").html();
    var result = name + " " + surname + "\n" + address + "\n" + zipCode + ", " + city + ", " + voivodeship + "\n" + "nr tel.: " + firstPhoneNumber + "\n";

    if (secondPhoneNumber != undefined) {
        result += "drugi nr tel.: " + secondPhoneNumber + "\n"
    }

    if (company != undefined) {
        result += "Firma: " + company;
    }

    var tempCopyInput = $("#tempCopyInput");
    tempCopyInput.val(result);
    tempCopyInput.select();

    return result;
};

var displaySuccessMessageIfApplicable = function () {
    var successMessage = $("#successMessage").val();

    if(successMessage != "") {
        Materialize.toast(successMessage, 3000, 'toast-success')
    }
};