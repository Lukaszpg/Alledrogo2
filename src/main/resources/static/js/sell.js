$(document).ready(function () {
    var quill = new Quill('#editor', {
        theme: 'snow'
    });

    quill.on('text-change', function(delta, oldDelta, source) {
        if (source === 'api') {
            console.log("An API call triggered this change.");
        } else if (source === 'user') {
            var content = $('.ql-editor').html();
            $("#editorContent").val(content);
        }
    });
});