var buttons={
		draftButton:null,
		publishButton:null
}
/**
 * 初始化组件
 */
var initOption={
		init:function(initOpt){
			if(initOpt.maxlength){
				$('#articleTitle').maxlength({
		            limitReachedClass: "label label-danger",
		            threshold: 26, //剩余多少字符时提示
		            appendToParent: true  //默认为false，设为true后可以显示再modal窗口上
		        });
				$('#articleTags').maxlength({
					limitReachedClass: "label label-danger",
					threshold: 26,
					appendToParent: true
				});
			}
			if(initOpt.componentsEditors){	//初始化富文本编辑器
				ComponentsEditors.init();
			}
			if(initOpt.uiButtons){	//初始化UI按钮
//				UIButtons.init();
				buttons.draftButton=Ladda.create( document.querySelector( '.draft' ) );
				buttons.publishButton=Ladda.create( document.querySelector( '.publish' ) );
				
			}
			if(initOpt.bootstrapSwitch){	//初始化Bootstrap开关
//				ComponentsBootstrapSwitch.init();//初始化Bootstrap开关
				$(".replyCheckbox").bootstrapSwitch();
			}
			if(initOpt.dataTables){  //初始化jquery dataTables
				if (App.isAngularJsApp() === false) {
					TableDatatablesManaged.init(initOpt);
				}
			}
		}
}

/**
 * 发布新文章 初始化组件
 */
var newArti={
		uiButtons:true,
		componentsEditors:true,
		bootstrapSwitch:true,
		maxlength:true
}
/**
 * 文章列表 初始化组件
 */
var articleMana={
		dataTables:true,
		dataTablesType:"article",
		serverSide:false, //开启客户端模式  true服务端模式
		uiButtons:true,
		componentsEditors:true,
		bootstrapSwitch:true,
		maxlength:true
}
/**
 * 评论管理 初始化组件
 */
var commentMana={
		dataTables:true,
		dataTablesType:"comment",
		serverSide:false
}
/**
 * 常规设置 初始化组件
 */
var generalSetMana={
		
}

/**
 * 加载contentBody
 * @param menu 菜单
 */
function loadContentBody(menu,obj){
	$(".sub-menu li").removeClass("active open");	//清空所有的选中
	$(obj).parent('li').addClass("active open");    //添加选中样式
	
	$("#contentBody").empty();//清理界面
	var url="/admin/articleEdit.html"; 
	var data="";
	var initOpt={
			
	};
	switch(menu){
	case "newArticle":
		url="/admin/articleEdit.html";
		data="发布文章";
		initOpt=newArti;
		break;
	case "articleManage":
		url="/admin/articleManage.html";
		data="文章列表";
		initOpt=articleMana;
		break;
	case "allComment":
		url="/admin/commentManage.html";
		data="全部评论";
		commentMana.commentStatus="all";
		initOpt=commentMana;
		break;
	case "pendingComment":
		url="/admin/commentManage.html";
		data="待审评论";
		commentMana.commentStatus="audit";
		initOpt=commentMana;
		break;
	case "approvalComment":
		url="/admin/commentManage.html";
		data="已批准评论";
		commentMana.commentStatus="approval";
		initOpt=commentMana;
		break;
	case "spamComment":
		url="/admin/commentManage.html";
		data="垃圾评论";
		commentMana.commentStatus="spam";
		initOpt=commentMana;
		break;
	case "generalSettings":
		url="/admin/generalSettings.html";
		data="常规设置";
		initOpt=generalSetMana;
	}
	contentBodyLoad(data,url,initOpt);
}

/**
 * 加载contentBody中的页面
 * @param data 路径文字
 * @param url 访问地址
 * @param initOpt 初始化选项
 */
function contentBodyLoad(data,url,initOpt){
	$("#contentBody").load(url,function(response){
		$("#twoMenuName").html(data);
		initOption.init(initOpt);
		if(data=="编辑文章" && !response){
			$("#articleTitle").val(response.articleId);
			$('#content').summernote('code', response.articleContent);
			$("#articleTags").val(response.articleTags);
			$('#abstr').summernote('code', response.articleAbstract);
			$(".replyCheckbox").bootstrapSwitch('state', response.commentType==0?true:false);
		}
	});
}