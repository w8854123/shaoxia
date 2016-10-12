
var generalSettings={
		siteTitle:null,   //站点标题
		subtitle:null,   //副标题
		webUrl:null,  //站点地址
		metaKeywords:null,  //站点的关键词
		metaDescription:null,  //站点的描述
		htmlHead:null,    //htmlHead
		announcement:null,  //公告
		pageFooter:null  //页脚
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
			for(var i=0;i<src.length;i++){
				if(src[i].optionName=="siteTitle"){
					$("[name='listOption[0].optionId']").val(src[i].optionId);
					$("[name='listOption[0].autoload']").val(src[i].autoload);
					$("[name='listOption[0].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="subtitle"){
					$("[name='listOption[1].optionId']").val(src[i].optionId);
					$("[name='listOption[1].autoload']").val(src[i].autoload);
					$("[name='listOption[1].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="webUrl"){
					$("[name='listOption[2].optionId']").val(src[i].optionId);
					$("[name='listOption[2].autoload']").val(src[i].autoload);
					$("[name='listOption[2].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="metaKeywords"){
					$("[name='listOption[3].optionId']").val(src[i].optionId);
					$("[name='listOption[3].autoload']").val(src[i].autoload);
					$("[name='listOption[3].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="metaDescription"){
					$("[name='listOption[4].optionId']").val(src[i].optionId);
					$("[name='listOption[4].autoload']").val(src[i].autoload);
					$("[name='listOption[4].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="htmlHead"){
					$("[name='listOption[5].optionId']").val(src[i].optionId);
					$("[name='listOption[5].autoload']").val(src[i].autoload);
					$("[name='listOption[5].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="announcement"){
					$("[name='listOption[6].optionId']").val(src[i].optionId);
					$("[name='listOption[6].autoload']").val(src[i].autoload);
					$("[name='listOption[6].optionValue']").val(src[i].optionValue);
				}
				if(src[i].optionName=="pageFooter"){
					$("[name='listOption[7].optionId']").val(src[i].optionId);
					$("[name='listOption[7].autoload']").val(src[i].autoload);
					$("[name='listOption[7].optionValue']").val(src[i].optionValue);
				}
				
				App.unblockUI('#generalSetForm');
			}
		},
		error:function(XMLHttpRequest){
			App.unblockUI('#generalSetForm');
			toastr["error"]("读取常规设置选项失败!", XMLHttpRequest.status);
		}
	});
}