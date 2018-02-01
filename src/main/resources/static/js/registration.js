"use strict";

$(document).ready(function () {
    initializePasswordField();
    initializeMonthsSelect();
});

var initializeMonthsSelect = function() {
    $("#months").material_select();
};

var initializePasswordField = function() {
    $('#registrationPassword').tooltip({
        html: true
    });

    $('#registrationPassword').password({
        shortPass: 'Hasło jest za krótkie',
        badPass: 'Spróbuj dodać liczby',
        goodPass: 'Spróbuj dodać znaki specjalne',
        strongPass: 'Silne hasło',
        containsUsername: 'The password contains the username',
        enterPass: 'Wpisz swoje hasło',
        showPercent: true,
        showText: true, // shows the text tips
        animate: true, // whether or not to animate the progress bar on input blur/focus
        animateSpeed: 'fast', // the above animation speed
        username: false, // select the username field (selector or jQuery instance) for better password checks
        usernamePartialMatch: true, // whether to check for username partials
        minimumLength: 8 // minimum password length (below this threshold, the score is 0)
    });

    $('#registrationPassword').focusin(function() {
        $('#passwordValidationError').addClass("hide");
    });
};