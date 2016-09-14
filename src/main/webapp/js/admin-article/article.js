function saveArticle(publish){
	var title=$("#articleTitle").val().trim(); //文章标题
	var tags=$("#articleTags").val().trim();  //文章标签
	var content=$("#content").summernote('code');   //文章正文
	var abstr=$("#abstr").summernote('code');    //文章摘要
	var allowComment=$("#allowComment:checked").val(); //允许评论
	if(!allowComment){
		allowComment=1;
	}
	var article={
		articleTitle:title,
		articleTags:tags,
		articleAbstract:abstr,
		commentType:allowComment,
		articleContent:content,
		publish:publish
		
	}
	send(article);
	
}

function send(data){
	$.ajax({
		url : "/admin/article/insert",
		type : "POST",
		data : data,
		dataType : "text",
		success : function(src,textStatus) {
			toastr["success"]("保存草稿箱成功！", data.articleTitle); //通知插件toastr配置信息在ui-toastr
		},
		error : function(XMLHttpRequest) {
			if(XMLHttpRequest.status==400){
				toastr["error"]("保存草稿箱失败！400错误", data.articleTitle);
			}else if(XMLHttpRequest.status==500){
				toastr["error"]("保存草稿箱失败!500错误", data.articleTitle);
			}
			
		}
	});
}