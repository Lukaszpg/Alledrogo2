"use strict";

$(document).ready(function () {
    initializeCountdown();
    initializeBarRatingsDisplay();
});

var initializeCountdown = function () {
    var endDateValue = $("#auctionEndDate").val();
    $("#endCountdown").countdown(endDateValue, function (event) {
        $(this).text(
            event.strftime('%D dni %H:%M:%S')
        );
    });
};


var initializeBarRatingsDisplay = function() {
    var timeRating  = $("#shippingTimeRatingHidden").val();
    var costRating =  $("#shippingCostRatingHidden").val();
    var descriptionRating = $("#descriptionRatingHidden").val();
    $('#shippingTimeRatingDisplay').barrating({
        theme: 'fontawesome-stars-o',
        readonly: true,
        initialRating: timeRating
    });

    $('#shippingCostRatingDisplay').barrating({
        theme: 'fontawesome-stars-o',
        readonly: true,
        initialRating: costRating
    });

    $('#descriptionRatingDisplay').barrating({
        theme: 'fontawesome-stars-o',
        readonly: true,
        initialRating: descriptionRating
    });

    $(".shipping-time-rating-wrapper").find(".br-widget").append("<label class='display-rating'>" + timeRating + "</label>");
    $(".shipping-cost-rating-wrapper").find(".br-widget").append("<label class='display-rating'>" + costRating + "</label>");
    $(".description-rating-wrapper").find(".br-widget").append("<label class='display-rating'>" + descriptionRating + "</label>");
}