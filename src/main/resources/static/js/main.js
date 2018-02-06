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
    axios.defaults.baseURL = $("#appMainUrl").val();
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

