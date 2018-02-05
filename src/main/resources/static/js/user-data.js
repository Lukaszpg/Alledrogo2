"use strict";

$(document).ready(function () {
    enableMaterializeSelect();
    initializeInputMasks();
    initializeInputErrorHide();
    initializeSelectErrorHide();
    checkShouldChangeToPasswordChangeTab();
    checkShouldChangeToEmailChangeTab();
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

var checkShouldChangeToEmailChangeTab = function() {
    var tabValue = $("#changeEmailTab").val();

    if(tabValue == "true") {
        $('ul.tabs').tabs('select_tab', 'changeEmail');
    }
};

var checkShouldChangeToPasswordChangeTab = function() {
    var tabValue = $("#changePasswordTab").val();

    if(tabValue == "true") {
        $('ul.tabs').tabs('select_tab', 'changePassword');
    }
};