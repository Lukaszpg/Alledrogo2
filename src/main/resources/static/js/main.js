"use strict";

$(document).ready(function () {
    initializeTabs();
    enableSelectValidation();
    searchClearIconClick();
    searchClearIconFadeout();
    searchBarOnFocusIn();
    searchBarOnFocusOut();
    initializeInputMasks();
    initializeMenuButtons();
    initializeSearchSelect();
    initializeModals();
    initializeBarRatings();
    bindMessagesViewClick();
    bindMessagesSentViewClick();
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

var initializeBarRatings = function () {
    var defaultValue = 5;
    $("#shippingTimeRating").val(defaultValue);
    $("#shipmentCostRating").val(defaultValue);
    $("#descriptionAccordanceRating").val(defaultValue);

    $('#shippingTimeRatingSelect').barrating({
        theme: 'fontawesome-stars',

        onSelect: function (value, text, event) {
            $("#shippingTimeRating").val(value);
        }
    });

    $('#shipmentCostRatingSelect').barrating({
        theme: 'fontawesome-star',

        onSelect: function (value, text, event) {
            $("#shipmentCostRating").val(value);
        }
    });

    $('#descriptionAccordanceRatingSelect').barrating({
        theme: 'fontawesome-stars',

        onSelect: function (value, text, event) {
            $("#descriptionAccordanceRating").val(value);
        }
    });
};

var bindMessagesViewClick = function () {
    $(".messages-view").click(function () {
        var elem = $(this);
        var id = elem.parent().find("#messageId").val();

        axios.get("/user-rest/messages/change-status/" + id)
            .then(function () {
                elem.parent().find("#messageNewBadge").remove();
            })
            .catch(function () {
                console.log(e);
            })

        appendMessageTitleAndContent(elem, "#modalMessageTitle", "#modalMessageContent");
        var messageAnswerAction = $("#messageAnswerAction");
        var senderId = elem.parent().find("#senderId").val();
        var originalHref = messageAnswerAction.attr("href");
        var truncatedHref = originalHref.substr(0, originalHref.lastIndexOf("/"));
        var hrefToAppend =  truncatedHref + "/" + senderId;
        messageAnswerAction.attr("href", hrefToAppend);
    });
};

var bindMessagesSentViewClick = function () {
    $(".messages-view-sent").click(function () {
        var elem = $(this);
        appendMessageTitleAndContent(elem, "#modalMessageTitleSent", "#modalMessageContentSent");
    });
};

var appendMessageTitleAndContent = function (elem, titleBox, contentBox) {
    var title = elem.parent().find("#messageTitle").val();
    var content = elem.parent().find("#messageContent").val();

    $(titleBox).html(title);
    $(contentBox).html(content);
}

