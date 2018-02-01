"use strict";

$(document).ready(function () {
    initializeCountdown();
});

var initializeCountdown = function () {
    var endDateValue = $("#auctionEndDate").val();
    $("#endCountdown").countdown(endDateValue, function (event) {
        $(this).text(
            event.strftime('%D dni %H:%M:%S')
        );
    });
};

