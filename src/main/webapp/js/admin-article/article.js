function saveArticle(publish){
	//按钮加载动画
	buttons.draftButton.start();
	buttons.publishButton.start();
	
	var title=$("#articleTitle").val().trim(); //文章标题
	var tags=$("#articleTags").val().trim();  //文章标签
	var content=$("#content").summernote('code');   //文章正文
	var abstr=$("#abstr").summernote('code');    //文章摘要
	var allowComment=$("#allowComment:checked").val(); //允许评论
	var id=$("#articleId").val(); //文章id
	if(!allowComment){
		allowComment=1;
	}
	if(!title || title==""){
		buttons.draftButton.stop();
		buttons.publishButton.stop();
		toastr["warning"]("标题不能为空！", "请填写标题");
		return;
	}
	var article={
		articleTitle:title,
		articleTags:tags,
		articleAbstract:abstr,
		commentType:allowComment,
		articleContent:content,
		publish:publish
		
	};
	if(id && id!=""){
		send(article,"/admin/article/update/"+id,"PUT",function(){
			table.draw();//重绘表格   //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
		});  //保存修改
	}else{
		send(article,"/admin/article/insert","POST",function(){});  //新增
	}
	
}

/**
 * 发送ajax请求
 * @param data 传至后端的数据
 * @param url 地址
 * @param type 请求类型
 */
function send(data,url,type,callback){
	$.ajax({
		url : url,
		type : type,
		data : data,
		dataType : "text",
		success : function(src,textStatus) {
			buttons.draftButton.stop();
			buttons.publishButton.stop();
			if(data.publish==1){
				toastr["success"]("保存草稿箱成功！", data.articleTitle); //通知插件toastr配置信息在ui-toastr.js
			}else{
				toastr["success"]("发布成功！", data.articleTitle);
			}
			callback();
		},
		error : function(XMLHttpRequest) {
			buttons.draftButton.stop();
			buttons.publishButton.stop();
			if(XMLHttpRequest.status==400){
				toastr["error"]("保存草稿箱失败！400错误", data.articleTitle);
			}else if(XMLHttpRequest.status==500){
				toastr["error"]("保存草稿箱失败!500错误", data.articleTitle);
			}
			
		}
	});
}