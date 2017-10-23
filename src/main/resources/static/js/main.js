$(document).ready(function () {
    $('select').material_select();

    searchClearIconClick();
    searchClearIconFadeout();
    searchBarOnFocusIn();
    searchBarOnFocusOut();
});

var searchClearIconClick = function () {
    $(".search-clear-icon").click(function () {
        $("#search").val("");
    });
}

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
}

