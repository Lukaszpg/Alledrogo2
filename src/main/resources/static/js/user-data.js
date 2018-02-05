"use strict";

$(document).ready(function () {
    enableMaterializeSelect();
    initializeInputMasks();
    initializeInputErrorHide();
    initializeSelectErrorHide();
    checkShouldChangeToPasswordChangeTab();
});

var enableMaterializeSelect = function () {
    $("#voivodeshipSelect").material_select();
};

var initializeInputMasks = function () {
    $('.money').mask('###0.00', {reverse: true});
    $('.amount').mask('#####0');
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

var checkShouldChangeToPasswordChangeTab = function() {
    var tabValue = $("#changePasswordTab").val();

    if(tabValue == "true") {
        $('ul.tabs').tabs('select_tab', 'changePassword');
    }
};