/**
 * 带进度条的按钮
 */
var buttons={
		draftButton:null, //文章存草稿
		publishButton:null, //发布文章
		settButton:null //设置 保存更改
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
				if(document.querySelector( '.draft' )){
					buttons.draftButton=Ladda.create( document.querySelector( '.draft' ) ); 
				}
				if(document.querySelector( '.publish' )){
					buttons.publishButton=Ladda.create( document.querySelector( '.publish' ) ); 
				}
				if(document.querySelector('.Settings')){
					buttons.settButton=Ladda.create( document.querySelector('.Settings'));
				}
				
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
			if(initOpt.touchSpin){
				$(".touchSpin").TouchSpin({
					min: 0,
	                max: 1000,
		            verticalbuttons: true
		        });
			}
			if(initOpt.qiniuUpload){
				initQiniuUp();
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
		serverSide:false, //dataTable开启客户端模式  true服务端模式
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
		uiButtons:true
}
/**
 * 媒体库设置 初始化组件
 */
var mediaSetMana={
		uiButtons:true,
		touchSpin:true
}
/**
 * 系统参数设置 初始化组件
 */
var systemSetMana={
		uiButtons:true
}
/**
 * 阅读参数设置 初始化组件
 */
var readSetMana={
		uiButtons:true,
		touchSpin:true
}
/**
 * 上传文件 初始化组件
 */
var uploadFileMana={
		uiButtons:true,
		qiniuUpload:true
}
/**
 * 媒体库管理 初始化组件
 */
var mediaMana={
		dataTables:true,
		dataTablesType:"media",
		serverSide:false
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
	var initOpt={};
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
	case "mediaManage":
		url="/admin/mediaManage.html";
		data="管理";
		initOpt=mediaMana;
		break;
	case "uploadFile":
		url="/admin/uploadFile.html";
		data="添加";
		initOpt=uploadFileMana;
		break;
	case "generalSettings":
		url="/admin/generalSettings.html";
		data="常规设置";
		initOpt=generalSetMana;
		break;
	case "mediaSettings":
		url="/admin/mediaSettings.html";
		data="媒体库设置";
		initOpt=mediaSetMana;
		break;
	case "systemSettings":
		url="/admin/systemSettings.html";
		data="参数设置";
		initOpt=systemSetMana;
		break;
	case "readSettings":
		url="/admin/readSettings.html";
		data="阅读设置";
		initOpt=readSetMana;
		break;
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
		if(data=="常规设置"){
			getSett(generalSettings);  //读取配置选项
		}
		if(data=="媒体库设置"){
			getSett(mediaSettings);
		}
		if(data=="阅读设置"){
			getSett(readSettings);
		}
	});
}