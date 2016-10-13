/**
 * 常规设置
 */
var generalSettings={
		siteTitle:0,   //站点标题
		subtitle:1,   //副标题
		webUrl:2,  //站点地址
		metaKeywords:3,  //站点的关键词
		metaDescription:4,  //站点的描述
		htmlHead:5,    //htmlHead
		announcement:6,  //公告
		pageFooter:7,  //页脚
		
		length:8   //总共8个选项
}

/**
 * 批量插入或更新常规设置选项
 * @param operation 操作类型update：更新；默认插入
 */
function saveGeneralSett(operation){
	var url="/admin/options/insertBatch";
	var type="POST";
	if(operation=="update"){
		url="/admin/options/updateBatch";
		type="PUT";
	}
	buttons.generalSettButton.start();  //开启按钮进度条
	$.ajax({
		url:url,
		type:type,
		data:$("#generalSetForm").serialize(),
		dataType:"text",
		success:function(src,textStatus) {
			buttons.generalSettButton.stop(); //关闭按钮进度条
			toastr["success"]("操作成功！", "温馨提示");
		},
		error:function(XMLHttpRequest){
			buttons.generalSettButton.stop();
			toastr["error"]("保存常规设置选项失败!", XMLHttpRequest.status);
		}
	});
}

/**
 * 获取常规设置选项
 */
function getGeneralSett(){
	//设置进度条
 	App.blockUI({
         target: '#generalSetForm',
         animate: true
    });
	$.ajax({
		url:"/admin/options/query/all",
		type:"GET",
		data:"",
		dataType:"json",
		success:function(src,textStatus) {
			setTheValue(src,generalSettings);
			App.unblockUI('#generalSetForm');
		},
		error:function(XMLHttpRequest){
			App.unblockUI('#generalSetForm');
			toastr["error"]("读取常规设置选项失败!", XMLHttpRequest.status);
		}
	});
}

/**
 * 将配置数据设置到页面中
 * @param src
 * @param obj 页面设置项
 */
function setTheValue(src,obj){
	var n=0;
	for(var i=0;i<src.length;i++){
		if(n==obj.length){
			return;
		}
		for(var name in obj){
			if(src[i].optionName==name){
				$("[name='listOption["+obj[name]+"].optionId']").val(src[i].optionId);
				$("[name='listOption["+obj[name]+"].autoload']").val(src[i].autoload);
				$("[name='listOption["+obj[name]+"].optionValue']").val(src[i].optionValue);
				n++;
			}
		}
	}
}