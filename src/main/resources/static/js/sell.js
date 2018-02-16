"use strict";

$(document).ready(function () {
    enableSelects();
    initializeRichTextEditor();
    initializeInputMasks();
    initializeCheckboxChangeEvent();
    initializeTitleInputChangeEvent();
    initializeInputErrorHide();
    initializeSelectErrorHide();
    checkBidCheckbox();
    checkBuyoutCheckbox();
    checkUntilOutOfItemsCheckbox();
    initializeSellInputMasks();
});

var initializeSellInputMasks = function () {
    $('.money').mask('###0.00', {reverse: true});
    $('.amount').mask('#####0');
};

var checkBidCheckbox = function () {
    var bidCheckbox = $("#isBidCheckbox");
    if (bidCheckbox.is(":checked")) {
        $(".auction-bid-input").each(function () {
            $(this).removeClass("hide");
        });
        $(".until-out-of").addClass("hide");
    } else {
        $(".auction-bid-input").each(function () {
            $(this).addClass("hide");
        });
        $(".until-out-of").removeClass("hide");
    }

    checkShouldDisableItemsAmountAndSetFixedValue(bidCheckbox);
};

var checkBuyoutCheckbox = function () {
    var buyoutCheckbox = $("#isBuyoutCheckbox");
    if (buyoutCheckbox.is(":checked")) {
        $(".auction-buyout-input").each(function () {
            $(this).removeClass("hide");
        });
        $(".until-out-of").removeClass("hide");
    } else {
        $(".auction-buyout-input").each(function () {
            $(this).addClass("hide");
        });
        $(".until-out-of").addClass("hide");
    }
};

var checkUntilOutOfItemsCheckbox = function () {
    var checkbox = $("#untilOutOfItemsCheckbox");
    if (checkbox.is(":checked")) {
        $(".auction-duration-row").addClass("hide");
    } else {
        $(".auction-duration-row").removeClass("hide");
    }
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

var initializeTitleInputChangeEvent = function () {
    $("#auctionTitle").keypress(function () {
        if (isTitleNotNull()) {
            unhideExtendedAuction();
        }
    });
};

var enableSelects = function () {
    $("#itemStateSelect").material_select();
    $("#auctionDurationSelect").material_select();
};

var isTitleNotNull = function () {
    return $("#auctionTitle").val() !== "";
};

var unhideExtendedAuction = function () {
    $(".auction-extend").each(function () {
        $(this).removeClass("hide");
    });
};

var initializeRichTextEditor = function () {
    var container = $("#editor");
    var editorContentContainer = $("#editorContent");

    if (container.length) {
        var quill = new Quill('#editor', {
            modules: {toolbar: '#toolbar-container'},
            theme: 'snow'
        });

        if (editorContentContainer.val().length > 0) {
            var textToSet = editorContentContainer.val();
            textToSet = textToSet.replace(/(<([^>]+)>)/ig, "");
            quill.setText(textToSet);
        }

        quill.on('text-change', function (delta, oldDelta, source) {
            if (source === 'api') {
                console.log("An API call triggered this change.");
            } else if (source === 'user') {
                var editor = $('.ql-editor');
                editorContentContainer.val(editor.html());
                var errorObject = editor.parent().parent().find(".validation-error");

                if (errorObject.length > 0) {
                    errorObject.addClass("hide");
                }
            }
        });
    }
};
var checkShouldDisableItemsAmountAndSetFixedValue = function(bidCheckbox) {
    var field = $(".items-amount");
    if (bidCheckbox.is(":checked")) {
        field.val("1");
        field.prop('disabled', true);
    } else {
        field.prop('disabled', false);
        field.val("");
    }
};

var initializeCheckboxChangeEvent = function () {
    var isBidCheckbox = $("#isBidCheckbox");
    var isBuyoutCheckbox = $("#isBuyoutCheckbox");
    var untilCheckbox = $("#untilOutOfItemsCheckbox");

    isBuyoutCheckbox.change(function () {
        if (!isBidCheckbox.is(":checked")) {
            isBidCheckbox.prop("checked", true)
            $(".until-out-of").addClass("hide");
        }

        isCheckboxChecked(isBidCheckbox, ".auction-bid-input");
        isCheckboxChecked(isBuyoutCheckbox, ".auction-buyout-input");
        checkShouldDisableItemsAmountAndSetFixedValue(isBidCheckbox);
    });

    isBidCheckbox.change(function () {
        if (!isBuyoutCheckbox.is(":checked")) {
            isBuyoutCheckbox.prop("checked", true)
        }

        isCheckboxChecked(isBidCheckbox, ".auction-bid-input");
        isCheckboxChecked(isBuyoutCheckbox, ".auction-buyout-input");
        checkShouldDisableItemsAmountAndSetFixedValue(isBidCheckbox);

        if(isBidCheckbox.is(":checked")) {
            $(".until-out-of").addClass("hide");
        } else {
            $(".until-out-of").removeClass("hide");
        }
    });

    untilCheckbox.change(function() {
        isCheckboxNotChecked(untilCheckbox, ".auction-duration-row");
    });

};

var isCheckboxChecked = function (object, classSelector) {
    if (object.is(":checked")) {
        $(classSelector).each(function () {
            $(this).removeClass("hide");
        });
    } else {
        $(classSelector).each(function () {
            $(this).addClass("hide");
        });
    }
};


var isCheckboxNotChecked = function (object, classSelector) {
    if (object.is(":checked")) {
        $(classSelector).each(function () {
            $(this).addClass("hide");
        });
    } else {
        $(classSelector).each(function () {
            $(this).removeClass("hide");
        });
    }
};