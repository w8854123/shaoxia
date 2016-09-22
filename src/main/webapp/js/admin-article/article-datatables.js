var table;
var TableDatatablesManaged = function () {

    var articleTables = function () {

        // begin first table
    	table = $('#articleTables').DataTable({
            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "没有文章！",
                "info": "_START_ - _END_ 共 _TOTAL_ 篇",
                "infoEmpty": "没有找到文章",
                "infoFiltered": "(从  _MAX_ 篇文章中筛选出 _TOTAL_ 篇)",
                "processing":"加载中...",
                "lengthMenu": "每页显示 _MENU_",
                "search": "搜索:",
                "zeroRecords": "没有找到匹配的文章",
                "paginate": {
                    "previous":"上一页",
                    "next": "下一页",
                    "last": "尾页",
                    "first": "首页"
                }
            },
            "processing": false, //开启加载提示
            "serverSide": true, //开启服务器模式
            "bStateSave": false, // save datatable state(pagination, sort, etc) in cookie.
            "searching": true,//是否允许Datatables开启本地搜索
            "searchDelay":800,//设置搜索延迟时间单位ms
            "lengthMenu": [
                [5, 15, 20, 25],
                [5, 15, 20, 25] // change per page values here
            ],
            // set the initial value
            "pageLength": 20,            
            "pagingType": "bootstrap_full_number",
            "columns":[
                       {"data":null},
                       {"data":"articleTitle"},
                       {"data":"articleAuthor"},
                       {"data":null},
                       {"data":"articleCommentCount"},
                       {"data":"articleViewCount"},
                       {"data":"goodPointCount"},
                       {"data":"created"},
                       {"data":null},
                       {"data":"articleId"},
                       {"data":"putTop"},
                       {"data":"commentType"},
                       {"data":"publish"}
                   ],
            "columnDefs": [
                {  // set default column settings
                    'orderable': false,
                    'targets': [0,1,2,3,8,9]
                }, 
                {
                    "searchable": false,
                    "targets": [0,3,4,5,6,7,8,9]
                },
                {
                    "render": function(data, type, row , meta) { //参看https://datatables.net/reference/option/columns.render
                    	var time=new Date(data);
                        return time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate();
                    },
                    "targets": [7]
                },
                {
                	"visible": false, //可见性
                    "targets": [9,10,11,12]
                },
                {
                    "className": "dt-right", 
                    //"targets": [2]
                }
            ],
            "order": [
                [7, "desc"]
            ], // set first column as a default sort by asc
            
            "ajax" : function(data, callback, settings){ //data:发送给服务器的数据;callback:必须被执行，Datatables才能获取到数据;settings:Datatables的设置对象
            	//设置进度条
            	App.blockUI({
                    target: '#blockui_articleTables',
                    animate: true
                });
            	//封装输入参数
            	var dtParam=getQueryParam(data);
            	
            	$.ajax({
            		url : "/admin/article/query",
            		type : "GET",
            		data : dtParam,
            		dataType : "json",
            		success : function(dtResult,textStatus) {
            			callback(dtResult);
            			App.unblockUI('#blockui_articleTables');//关闭进度条
            		},
            		error : function(XMLHttpRequest) {
            			App.unblockUI('#blockui_articleTables');//关闭进度条
            			if(XMLHttpRequest.status==500){
            				toastr["error"]("查询文章出错!500错误");
            			}
            		}
            	});
            },
            
            "createdRow":function(row, data, dataIndex){ //行渲染回调,在这里可以对该行dom元素进行任何操作
            	//row:已经被创建的tr元素
            	//data:包含这行的数据对象
            	//dataIndex:Datatables内部存储的数据索引
            	$(row).addClass("gradeX");
            	var $tds=$('td', row);
            	var actionInfo = {// 操作选项信息
            			id:data.articleId,
            			
            			updateInfo : " 编辑文章",
            			updateClass : "icon-note",
            			updateUrl : "",
            			updateType : "GET",

            			putTopInfo : " 置顶文章",
            			putTopClass : "icon-arrow-up",
            			putTopUrl : "/admin/article/update/",
            			putTopValue : {
            				putTop : 1
            			},
            			putTopType : "PATCH",

            			publishInfo : " 取消发布",
            			publishClass : "icon-ban",
            			publishUrl : "/admin/article/update/",
            			publishValue : {
            				publish : 1
            			},
            			publishType : "PATCH",

            			commentTypeInfo : " 禁止评论",
            			commentTypeClass : "icon-lock",
            			commentTypeUrl : "/admin/article/update/",
            			commentTypeValue : {
            				commentType : 1
            			},
            			commentType : "PATCH",

            			previewInfo : " 预览文章",
            			previewClass : "icon-eye",
            			previewUrl : "",
            			previewType : "GET",

            			viewCommentsInfo : " 查看评论",
            			viewCommentsClass : "icon-bubbles",
            			viewCommentsUrl : "",
            			viewCommentsType : "GET",

            			deleteInfo : " 删 除 ",
            			deleteClass : "icon-trash",
            			deleteUrl : "/admin/article/delete/",
            			deleteType : "DELETE"

            		}
            	
            	//设置状态
            	var stateHtml="";
            	if(data.publish==1){
            		stateHtml +='<span class="label label-sm label-warning">草稿</span>';
            		actionInfo.publishInfo=" 发布文章";
            		actionInfo.publishClass="icon-feed";
            		actionInfo.publishValue={publish:0};
            	}else{
            		stateHtml='<span class="label label-sm label-info">刊载</span>';
            	}
            	if(data.putTop==1){
            		stateHtml +='<span class="label label-sm label-success">置顶</span>';
            		actionInfo.putTopInfo=" 取消置顶";
            		actionInfo.putTopClass="icon-arrow-down";
            		actionInfo.putTopValue={putTop:0};
            	}
            	if(data.commentType==1){
            		stateHtml +='<span class="label label-sm label-danger">禁评</span>';
            		actionInfo.commentTypeInfo=" 允许评论";
            		actionInfo.commentTypeClass="icon-lock-open";
            		actionInfo.commentTypeValue={commentType:0};
            	}
            	$tds.eq(3).html(stateHtml);
            	//设置操作
            	var actionHtml="<div class='btn-group'><button class='btn btn-xs green dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'> 操作 <i class='fa fa-angle-down'></i></button><ul class='dropdown-menu pull-right' role='menu'>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.updateClass+"'></i>"+actionInfo.updateInfo+"</a></li>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.putTopClass+"'></i>"+actionInfo.putTopInfo+"</a></li>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.publishClass+"'></i>"+actionInfo.publishInfo+"</a></li>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.commentTypeClass+"'></i>"+actionInfo.commentTypeInfo+"</a></li>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.previewClass+"'></i>"+actionInfo.previewInfo+"</a></li>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.viewCommentsClass+"'></i>"+actionInfo.viewCommentsInfo+"</a></li><li class='divider'></li>" +
            			"<li><a href='javascript:;' ><i class='"+actionInfo.deleteClass+"'></i>"+actionInfo.deleteInfo+"</a></li></ul></div>";
            	$tds.eq(8).html(actionHtml).find("a").each(function(index,element){
            		$(element).click(function(){
            			operation(actionInfo,index);
            		});
            	});
            	
            }
            
        });
        
        //on 为 jQuery 里监听事件的方法，给元素绑定监听事件，这里监听了 order.dt 、search.dt 、processing.dt 三个事件，意思是当排序和搜索处理完数据后，重新生成行号
        table.on('order.dt search.dt processing.dt',function() {
        	//table.column(0,{selector}).nodes() 这个的作用是获取第一列的 DOM 元素，接着用 each 方法遍历，把行号设置进去。这里 {selector} 为 DT 的高级应用
        	table.column(0, {
                search: 'applied', //applied  看得到的数据
                order: 'applied'   
            }).nodes().each(function(cell, i) {
                cell.innerHTML = i + 1;
            });
        }).draw();//draw 最后一个重绘方法，生成序号后，重新绘制。DT 的每个操作，包括排序、过滤、翻页还是自己使用API操作这些操作或者是设置了数据都要再调用 draw 方法才行
        
    }

    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            articleTables();
        }
    };
}();

//if (App.isAngularJsApp() === false) { 
//    jQuery(document).ready(function() {
//        TableDatatablesManaged.init();
//    });
//}

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
function operation(info,index){
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
			contentBodyLoad("编辑文章",url+info.id,newArti);  //admin-index.js
		}else if(index==6){
			//删除确认框
//			bootbox.confirm("你确定删除吗?", function(result) {
//				if(result){
//					actionSend(url+info.id,type,data);
//				}else{
//					App.unblockUI('#blockui_articleTables');
//					return;
//				}
//		    });
			bootbox.setLocale("zh_CN");
			bootbox.confirm({
				size: 'small',
			    message: '<div><p class="text-center" style="margin-bottom:0px;margin-top:0px;font-size:25px;">你确定删除吗?</p></div>', 
			    callback: function(result){
			    	if(result){
						actionSend(url+info.id,type,data);
					}else{
						App.unblockUI('#blockui_articleTables');
						return;
					}
			    }
			});
		}else{
//			$.ajax({
//				url : url + info.id,
//				type : type,
//				data : data,
//				dataType : "json",
//				success : function(src,textStatus) {
//					App.unblockUI('#blockui_articleTables');//关闭进度条
//					toastr["success"]("操作成功！", "温馨提示"); //通知插件toastr配置信息在ui-toastr.js
//					table.draw();//重绘表格   //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
//				},
//				error : function(XMLHttpRequest) {
//					App.unblockUI('#blockui_articleTables');//关闭进度条
//					if(XMLHttpRequest.status==500){
//						toastr["error"]("操作失败！500错误", "温馨提示");
//					}else{
//						toastr["error"]("操作失败！", "温馨提示");
//					}
//				}
//			});
			actionSend(url+info.id,type,data);
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
 */
function actionSend(url,type,data){
	$.ajax({
		url : url,
		type : type,
		data : data,
		dataType : "json",
		success : function(src,textStatus) {
			App.unblockUI('#blockui_articleTables');//关闭进度条
			toastr["success"]("操作成功！", "温馨提示"); //通知插件toastr配置信息在ui-toastr.js
			table.draw();//重绘表格   //reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
		},
		error : function(XMLHttpRequest) {
			App.unblockUI('#blockui_articleTables');//关闭进度条
			if(XMLHttpRequest.status==500){
				toastr["error"]("操作失败！500错误", "温馨提示");
			}else{
				toastr["error"]("操作失败！", "温馨提示");
			}
		}
	});
}