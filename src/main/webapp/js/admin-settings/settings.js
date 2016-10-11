
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

function saveGeneralSett(){
	buttons.generalSettButton.start();  //开启按钮进度条
	$.ajax({
		url:"/admin/options/insertBatch",
		type:"POST",
		data:$("#generalSetForm").serialize(),
		dataType:"text",
		success:function(src,textStatus) {
			buttons.generalSettButton.stop(); //关闭按钮进度条
			
		},
		error:function(XMLHttpRequest){
			buttons.generalSettButton.stop();
			
		}
	});
}