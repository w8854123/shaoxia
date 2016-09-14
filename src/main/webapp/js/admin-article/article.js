
function saveArticle(){
	var title=$("#articleTitle").val().trim(); //文章标题
	var tags=$("#articleTags").val().trim();  //文章标签
	var content=$("#content").summernote('code');   //文章正文
	var abstr=$("#abstr").summernote('code');    //文章摘要
	var allowComment=$("#allowComment:checked").val(); //允许评论
//	console.info(title);
//	console.info(tags);
//	console.info(content);
//	console.info(abstr);
//	console.info(allowComment);
	
}

function send(deta){
	$.ajax({
		url : "",
		type : "POST",
		data : $("#form").serialize(),
		dataType : "json",
		success : function(src) {
			messages.append("<p>Robot:<br>" + src.text + "</p>");
			messages.scrollTop(messages[0].scrollHeight);
		},
		error : function() {
		}
	});
}