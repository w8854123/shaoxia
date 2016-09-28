/**
 * 评论操作
 */
function operationComment(type,val,commentId){
	//设置进度条
	App.blockUI({
        target: '#blockui_commentTables',
        animate: true
    });
	var url="/admin/comment/update/"+commentId;
	var data="";
	var sendType="PATCH";
	if(type=="audit"){
		data={"commentAudit":val};
	}else if(type=="spam"){
		data={"commentSpam":val};
	}else if(type=="delete"){
		url="/admin/comment/delete/"+commentId;
		sendType="DELETE";
	}
	
	$.ajax({
		url : url,
		type : sendType,
		data : data,
		dataType : "json",
		success : function(src,textStatus) {
			App.unblockUI('#blockui_commentTables');//关闭进度条
			toastr["success"]("操作成功！", "温馨提示"); //通知插件toastr配置信息在ui-toastr.js
			//table.draw();//重绘表格   //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
			table.ajax.reload(); //重新发送ajax请求获取数据  客户端模式刷新
		},
		error : function(XMLHttpRequest) {
			App.unblockUI('#blockui_commentTables');//关闭进度条
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