$(document).ready(function () {
    initializeRichTextEditor();
    initializeInputMasks();
    initializeCheckboxChangeEvent();
});

var initializeRichTextEditor = function () {
    var quill = new Quill('#editor', {
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
};

var initializeInputMasks = function () {
    $('.money').mask('###0.00', {reverse: true});
    $('.amount').mask('#####0');
};

var initializeCheckboxChangeEvent = function () {
    var isBidCheckbox = $("#isBidCheckbox");
    var isBuyoutCheckbox = $("#isBuyoutCheckbox");

    isBuyoutCheckbox.change(function () {
        if(!isBidCheckbox.is(":checked")) {
            isBidCheckbox.prop("checked", true)
        }
    });

    isBidCheckbox.change(function () {
        if(!isBuyoutCheckbox.is(":checked")) {
            isBuyoutCheckbox.prop("checked", true)
        }
    });
};