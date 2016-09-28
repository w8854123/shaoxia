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
			//table.draw();//重绘表格   //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
			table.ajax.reload(); //重新发送ajax请求获取数据  客户端模式刷新
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

/* ---------------------------------------文章管理------------------------------------------  */

/**
 * 封装发送到后台的参数
 * @param data 原参数（datatables）
 * @returns {draw:1,order:"",search:"",start:0,length:10}
 */
function getQueryParam(data){
	var dtParam={};
	dtParam.draw=data.draw;
	//组装排序参数
	if(data.order&&data.order.length&&data.order[0]){
		switch (data.order[0].column){
		case 4:
			dtParam.order="article_comment_count ";
			break;
		case 5:
			dtParam.order="article_view_count ";
			break;
		case 6:
			dtParam.order="good_point_count ";
			break;
		case 7:
			dtParam.order="created ";
			break;
		}
		dtParam.order +=data.order[0].dir;
	}
	//组装查询参数
	serVal=data.search.value;
	if(serVal && serVal!=""){
		dtParam.search=" article_title like '%"+serVal+"%' OR article_author like '%"+serVal+"%' ";
	}
	//组装分页参数
	dtParam.start=data.start;
	dtParam.length=data.length;
	
	return dtParam;
}


/**
 * 文章操作
 * @param info 文章信息对象
 */
function operationArticle(info,index){
	if(!info || !info.id || info.id==""){
		toastr["error"]("请检查参数是否有误！", "无法识别的操作");
		return;
	}
	//设置进度条
	App.blockUI({
        target: '#blockui_articleTables',
        animate: true
    });
	var url="";
	var type="GET";
	var data="";
	switch(index){
	case 0:
		url=info.updateUrl;
		type=info.updateType;
		break;
	case 1:
		url=info.putTopUrl;
		type=info.putTopType;
		data=info.putTopValue;
		break;
	case 2:
		url=info.publishUrl;
		type=info.publishType;
		data=info.publishValue;
		break;
	case 3:
		url=info.commentTypeUrl;
		type=info.commentType;
		data=info.commentTypeValue;
		break;
	case 4:
		url=info.previewUrl;
		type=info.previewType;
		break;
	case 5:
		url=info.viewCommentsUrl;
		type=info.viewCommentsType;
		break;
	case 6:
		url=info.deleteUrl;
		type=info.deleteType;
		break;
	}
	
	if(url!=""){
		if(index==0){
//			contentBodyLoad("编辑文章",url+info.id,newArti);  //admin-index.js
			actionSend(url+info.id,type,data,"edit");
			
		}else if(index==6){
			//删除确认框
			bootbox.setLocale("zh_CN");
			bootbox.confirm({
				size: 'small',
			    message: '<div><p class="text-center" style="margin-bottom:0px;margin-top:0px;font-size:25px;">你确定删除吗?</p></div>', 
			    callback: function(result){
			    	if(result){
						actionSend(url+info.id,type,data,"");
					}else{
						App.unblockUI('#blockui_articleTables');
						return;
					}
			    }
			});
		}else{
			actionSend(url+info.id,type,data,"");
		}
	}else{
		App.unblockUI('#blockui_articleTables');//关闭进度条
		toastr["error"]("请检查地址是否正确！", "没有请求地址");
	}
	
}

/**
 * 表格操作请求
 * @param url 地址
 * @param type 请求类型
 * @param data 数据
 * @param flag 标识符edit:编辑文章
 * 
 */
function actionSend(url,type,data,flag){
	$.ajax({
		url : url,
		type : type,
		data : data,
		dataType : "json",
		success : function(src,textStatus) {
			if(flag=="edit"){
				$("#articleTitle").val(src.articleTitle);
				$("#articleTags").val(src.articleTags);
//				$('#content').summernote('insertText', src.articleContent);
				$('#content').summernote('code', src.articleContent);
				$('#abstr').summernote('code', src.articleAbstract);
				$('#allowComment').bootstrapSwitch('state',(src.commentType==0?true:false)); // true || false
				$("#articleId").val(src.articleId);
				
				App.unblockUI('#blockui_articleTables');
				$('#editModal').modal("show");
			}else{
				App.unblockUI('#blockui_articleTables');//关闭进度条
				toastr["success"]("操作成功！", "温馨提示"); //通知插件toastr配置信息在ui-toastr.js
				//table.draw();//重绘表格   //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
				table.ajax.reload(); //重新发送ajax请求获取数据  客户端模式刷新
			}
		},
		error : function(XMLHttpRequest) {
			App.unblockUI('#blockui_articleTables');//关闭进度条
			if(XMLHttpRequest.status==500){
				toastr["error"]("操作失败！500错误", "温馨提示");
			}else if(XMLHttpRequest.status==404){
				toastr["error"]("没有找到可编辑的数据！404错误", "温馨提示");
			}else{
				toastr["error"]("操作失败！", "温馨提示");
			}
		}
	});
}