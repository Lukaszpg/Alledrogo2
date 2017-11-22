$(document).ready(function () {
    enableMaterializeSelect();
    initializeRichTextEditor();
    initializeInputMasks();
    initializeCheckboxChangeEvent();
    initializeTitleInputChangeEvent();
});

var initializeTitleInputChangeEvent = function () {
    $("#auctionTitle").keypress(function () {
        if (isTitleNotNull()) {
            unhideExtendedAuction();
        }
    });
};

var enableMaterializeSelect = function() {
    $("#itemStateSelect").material_select();
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

    if (container.length) {
        var quill = new Quill('#editor', {
            modules: { toolbar: '#toolbar-container' },
            theme: 'snow'
        });
        quill.on('text-change', function (delta, oldDelta, source) {
            if (source === 'api') {
                console.log("An API call triggered this change.");
            } else if (source === 'user') {
                var content = $('.ql-editor').html();
                $("#editorContent").val(content);
            }
        });
    }
};

var initializeInputMasks = function () {
    $('.money').mask('###0.00', {reverse: true});
    $('.amount').mask('#####0');
};

var initializeCheckboxChangeEvent = function () {
    var isBidCheckbox = $("#isBidCheckbox");
    var isBuyoutCheckbox = $("#isBuyoutCheckbox");

    isBuyoutCheckbox.change(function () {
        if (!isBidCheckbox.is(":checked")) {
            isBidCheckbox.prop("checked", true)
        }
    });

    isBidCheckbox.change(function () {
        if (!isBuyoutCheckbox.is(":checked")) {
            isBuyoutCheckbox.prop("checked", true)
        }
    });
};