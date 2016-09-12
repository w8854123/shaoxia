/**
 * 初始化组件
 */
var initOption={
		init:function(initOpt){
			if(initOpt.componentsEditors){	//初始化富文本编辑器
				ComponentsEditors.init();
			}
			if(initOpt.uiButtons){	//初始化UI按钮
				UIButtons.init();
			}
			if(initOpt.bootstrapSwitch){	//初始化Bootstrap开关
//				ComponentsBootstrapSwitch.init();//初始化Bootstrap开关
				$(".replyCheckbox").bootstrapSwitch();
			}
		}
}

/**
 * 发布新文章 初始化组件
 */
var newArti={
		uiButtons:true,
		componentsEditors:true,
		bootstrapSwitch:true
}
/**
 * 文章列表 初始化组件
 */
var articleMana={
}

/**
 * 加载contentBody
 * @param menu 菜单
 */
function loadContentBody(menu){
	
	$("#contentBody").empty();//清理界面
	var url="/admin/articleEdit.html"; 
	var data="";
	var initOpt={
			
	}
	if(menu=="newArticle"){ //发布新文章
		url="/admin/articleEdit.html";
		data="发布文章";
		initOpt=newArti;
	}
	if(menu=="articleManage"){ //文章列表
		url="/admin/articleEdit.html";
		data="文章列表";
		initOpt=articleMana;
	}
//	console.info(data);
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
//		console.info("加载成功");
		$("#twoMenuName").html(data);
		initOption.init(initOpt);
	});
}