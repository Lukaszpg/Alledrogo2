"use strict";

$(document).ready(function () {
    initializeTabs();
    enableSelectValidation();
    searchClearIconClick();
    searchClearIconFadeout();
    searchBarOnFocusIn();
    searchBarOnFocusOut();
    setAxiosBaseURL();
    initializeInputMasks();
    initializeMenuButtons();
    initializeSearchSelect();
    initializeModals();
    initializeUserCopyToClipboardButton();
});

var initializeTabs = function () {
    $('ul.tabs').tabs();
};

var enableSelectValidation = function () {
    $("select[required]").css({display: "block", height: 0, padding: 0, width: 0, position: 'absolute'});
};

var searchClearIconClick = function () {
    $(".search-clear-icon").click(function () {
        $("#search").val("");
    });
};

var searchBarOnFocusIn = function () {
    $("#search").focus(function () {
        $(".search-clear-icon").fadeIn(300);
    });
};

var searchBarOnFocusOut = function () {
    $("#search").focusout(function () {
        searchClearIconFadeout();
    });
};

var searchClearIconFadeout = function () {
    $(".search-clear-icon").fadeOut(300);
};

var setAxiosBaseURL = function () {
    axios.defaults.baseURL = 'http://localhost:8080';
};

var initializeInputMasks = function () {
    $('.money').mask('###0.00', {reverse: true});
    $('.amount').mask('#####0');
};

var initializeMenuButtons = function () {
    $(".post-rating-button-menu").sideNav();
};

var initializeSearchSelect = function () {
    $("#searchCategoriesSelect").material_select();
};

var initializeModals = function () {
    $('.modal').modal();
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
    var name = $("#name").html();
    var surname = $("#surname").html();
    var address = $("#address").html();
    var zipCode = $("#zipCode").html();
    var city = $("#city").html();
    var voivodeship = $("#voivodeship").html();
    var firstPhoneNumber = $("#firstPhoneNumber").html();
    var secondPhoneNumber = $("#secondPhoneNumber").html();
    var company = $("#company").html();
    var result = name + " " + surname + "\n" + address + "\n" + zipCode + ", " + city + ", " + voivodeship + "\n" + "nr tel.: " + firstPhoneNumber + "\n";

    if(secondPhoneNumber != undefined) {
        result += "drugi nr tel.: " + secondPhoneNumber + "\n"
    }

    if(company != undefined) {
        result += "Firma: " + company;
    }

    var tempCopyInput = $("#tempCopyInput");
    tempCopyInput.val(result);
    tempCopyInput.select();

    return result;
};

