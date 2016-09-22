var ComponentsEditors = function () {
    
//    var handleWysihtml5 = function () {
//        if (!jQuery().wysihtml5) {
//            return;
//        }
//
//        if ($('.wysihtml5').size() > 0) {
//            $('.wysihtml5').wysihtml5({
//                "stylesheets": ["/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
//            });
//        }
//    }

    var handleSummernote = function () {
//        $("#contentBody [name='summernote']").summernote({height: 500,lang: 'zh-CN'});
        $('#content').summernote({
        	height: 500,lang: 'zh-CN',
        	toolbar:[
        	         // [groupName, [list of button]]
        	         ['style', ['style']],
        	         ['font', ['bold', 'italic','underline','clear','strikethrough','subscript','superscript']],
        	         ['fontname', ['fontname']],
        	         ['fontsize', ['fontsize']],
        	         ['color', ['color']],
        	         ['para', ['ul', 'ol', 'paragraph','height']],
        	         ['table', ['table']],
        	         ['insert', ['link', 'picture', 'video','hr']],
        	         ['view', ['fullscreen', 'codeview', 'help']]
        	]
        });
        
        $('#abstr').summernote({
        	height: 200,lang: 'zh-CN',
        	toolbar:[
        	         // [groupName, [list of button]]
        	         ['style', ['style']],
        	         ['font', ['bold', 'italic','underline','clear','strikethrough','subscript','superscript']],
        	         ['fontname', ['fontname']],
        	         ['fontsize', ['fontsize']],
        	         ['color', ['color']],
        	         ['para', ['ul', 'ol', 'paragraph','height']],
        	         ['table', ['table']],
        	         ['insert', ['link', 'picture', 'video','hr']],
        	         ['view', ['fullscreen', 'codeview', 'help']]
        	]
        });
        //API:
        //var sHTML = $('#summernote_1').code(); // get code
        //$('#summernote_1').destroy(); // destroy
    }

    return {
        //main function to initiate the module
        init: function () {
//            handleWysihtml5();
            handleSummernote();
        }
    };

}();

//jQuery(document).ready(function() {    
//   ComponentsEditors.init(); 
//});