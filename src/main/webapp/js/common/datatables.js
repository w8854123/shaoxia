var table;
var TableDatatablesManaged = function () {

	var language = {
		"aria" : {
			"sortAscending" : ": activate to sort column ascending",
			"sortDescending" : ": activate to sort column descending"
		},
		"emptyTable" : "没有内容！",
		"info" : "_START_ - _END_ 共 _TOTAL_ 条",
		"infoEmpty" : "没有找到内容",
		"infoFiltered" : "(从  _MAX_ 中筛选出 _TOTAL_ 条)",
		"processing" : "加载中...",
		"lengthMenu" : "每页显示 _MENU_",
		"search" : "搜索:",
		"zeroRecords" : "没有找到匹配的内容",
		"paginate" : {
			"previous" : "上一页",
			"next" : "下一页",
			"last" : "尾页",
			"first" : "首页"
		}
	}
	
	/**
	 * 获取服务端数据
	 * @param blockId 遮罩id
	 * @param url 请求地址
	 * @param serverSide 是否服务器模式 true false
	 * @param data dataTables原始请求数据
	 * @param callback dataTables回调渲染函数
	 * 
	 */
	var loadData = function(blockId,url,serverSide,data,callback){
     	//设置进度条
     	App.blockUI({
             target: '#'+blockId,
             animate: true
         });
     	
     	//封装输入参数
     	var dtParam="";
    	if(serverSide){  //启用服务器模式
        	dtParam=getQueryParam(data);
    	}
     	
     	$.ajax({
     		url : url,
     		type : "GET",
     		data : dtParam,
     		dataType : "json",
     		success : function(dtResult,textStatus) {
     			callback(dtResult);
     			App.unblockUI('#'+blockId);//关闭进度条
     		},
     		error : function(XMLHttpRequest) {
     			App.unblockUI('#'+blockId);//关闭进度条
     			if(XMLHttpRequest.status==500){
     				toastr["error"]("查询数据出错!500错误");
     			}
     		}
     	});
	}
	
	/**
	 * 首列生成行号
	 */
	var linenum = function(){
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
	
	/**
	 * 文章列表
	 * 
	 * @param serverSide
	 *            是否服务器模式true,false
	 */
    var articleTables = function (serverSide) {

        // 服务器模式
// table = $('#articleTables').DataTable({
//            "language": {
//                "aria": {
//                    "sortAscending": ": activate to sort column ascending",
//                    "sortDescending": ": activate to sort column descending"
//                },
//                "emptyTable": "没有文章！",
//                "info": "_START_ - _END_ 共 _TOTAL_ 篇",
//                "infoEmpty": "没有找到文章",
//                "infoFiltered": "(从  _MAX_ 篇文章中筛选出 _TOTAL_ 篇)",
//                "processing":"加载中...",
//                "lengthMenu": "每页显示 _MENU_",
//                "search": "搜索:",
//                "zeroRecords": "没有找到匹配的文章",
//                "paginate": {
//                    "previous":"上一页",
//                    "next": "下一页",
//                    "last": "尾页",
//                    "first": "首页"
//                }
//            },
//            "processing": false, //开启加载提示
//            "serverSide": true, //开启服务器模式
//            "bStateSave": false, // save datatable state(pagination, sort, etc) in cookie.
//            "searching": true,//是否允许Datatables开启本地搜索
//            "searchDelay":800,//设置搜索延迟时间单位ms
//            "lengthMenu": [
//                [5, 15, 20, 25],
//                [5, 15, 20, 25] // change per page values here
//            ],
//            // set the initial value
//            "pageLength": 20,            
//            "pagingType": "bootstrap_full_number",
//            "columns":[
//                       {"data":null},
//                       {"data":"articleTitle"},
//                       {"data":"articleAuthor"},
//                       {"data":null},
//                       {"data":"articleCommentCount"},
//                       {"data":"articleViewCount"},
//                       {"data":"goodPointCount"},
//                       {"data":"created"},
//                       {"data":null},
//                       {"data":"articleId"},
//                       {"data":"putTop"},
//                       {"data":"commentType"},
//                       {"data":"publish"}
//                   ],
//            "columnDefs": [
//                {  // set default column settings
//                    'orderable': false,
//                    'targets': [0,1,2,3,8,9]
//                }, 
//                {
//                    "searchable": false,
//                    "targets": [0,3,4,5,6,7,8,9]
//                },
//                {
//                    "render": function(data, type, row , meta) { //参看https://datatables.net/reference/option/columns.render
//                    	var time=new Date(data);
//                        return time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate();
//                    },
//                    "targets": [7]
//                },
//                {
//                	"visible": false, //可见性
//                    "targets": [9,10,11,12]
//                },
//                {
//                    "className": "dt-right", 
//                    //"targets": [2]
//                }
//            ],
//            "order": [
//                [7, "desc"]
//            ], // set first column as a default sort by asc
//            
//            "ajax" : function(data, callback, settings){ //data:发送给服务器的数据;callback:必须被执行，Datatables才能获取到数据;settings:Datatables的设置对象
//            	//设置进度条
//            	App.blockUI({
//                    target: '#blockui_articleTables',
//                    animate: true
//                });
//            	//封装输入参数
//            	var dtParam=getQueryParam(data);
//            	
//            	$.ajax({
//            		url : "/admin/article/query",
//            		type : "GET",
//            		data : dtParam,
//            		dataType : "json",
//            		success : function(dtResult,textStatus) {
//            			callback(dtResult);
//            			App.unblockUI('#blockui_articleTables');//关闭进度条
//            		},
//            		error : function(XMLHttpRequest) {
//            			App.unblockUI('#blockui_articleTables');//关闭进度条
//            			if(XMLHttpRequest.status==500){
//            				toastr["error"]("查询文章出错!500错误");
//            			}
//            		}
//            	});
//            },
//            
//            "createdRow":function(row, data, dataIndex){ //行渲染回调,在这里可以对该行dom元素进行任何操作
//            	//row:已经被创建的tr元素
//            	//data:包含这行的数据对象
//            	//dataIndex:Datatables内部存储的数据索引
//            	$(row).addClass("gradeX");
//            	var $tds=$('td', row);
//            	var actionInfo = {// 操作选项信息
//            			id:data.articleId,
//            			
//            			updateInfo : " 编辑文章",
//            			updateClass : "icon-note",
//            			updateUrl : "/admin/article/query/",
//            			updateType : "GET",
//
//            			putTopInfo : " 置顶文章",
//            			putTopClass : "icon-arrow-up",
//            			putTopUrl : "/admin/article/update/",
//            			putTopValue : {
//            				putTop : 1
//            			},
//            			putTopType : "PATCH",
//
//            			publishInfo : " 取消发布",
//            			publishClass : "icon-ban",
//            			publishUrl : "/admin/article/update/",
//            			publishValue : {
//            				publish : 1
//            			},
//            			publishType : "PATCH",
//
//            			commentTypeInfo : " 禁止评论",
//            			commentTypeClass : "icon-lock",
//            			commentTypeUrl : "/admin/article/update/",
//            			commentTypeValue : {
//            				commentType : 1
//            			},
//            			commentType : "PATCH",
//
//            			previewInfo : " 预览文章",
//            			previewClass : "icon-eye",
//            			previewUrl : "",
//            			previewType : "GET",
//
//            			viewCommentsInfo : " 查看评论",
//            			viewCommentsClass : "icon-bubbles",
//            			viewCommentsUrl : "",
//            			viewCommentsType : "GET",
//
//            			deleteInfo : " 删 除 ",
//            			deleteClass : "icon-trash",
//            			deleteUrl : "/admin/article/delete/",
//            			deleteType : "DELETE"
//
//            		}
//            	
//            	//设置状态
//            	var stateHtml="";
//            	if(data.publish==1){
//            		stateHtml +='<span class="label label-sm label-warning">草稿</span>';
//            		actionInfo.publishInfo=" 发布文章";
//            		actionInfo.publishClass="icon-feed";
//            		actionInfo.publishValue={publish:0};
//            	}else{
//            		stateHtml='<span class="label label-sm label-info">刊载</span>';
//            	}
//            	if(data.putTop==1){
//            		stateHtml +='<span class="label label-sm label-success">置顶</span>';
//            		actionInfo.putTopInfo=" 取消置顶";
//            		actionInfo.putTopClass="icon-arrow-down";
//            		actionInfo.putTopValue={putTop:0};
//            	}
//            	if(data.commentType==1){
//            		stateHtml +='<span class="label label-sm label-danger">禁评</span>';
//            		actionInfo.commentTypeInfo=" 允许评论";
//            		actionInfo.commentTypeClass="icon-lock-open";
//            		actionInfo.commentTypeValue={commentType:0};
//            	}
//            	$tds.eq(3).html(stateHtml);
//            	//设置操作
//            	var actionHtml="<div class='btn-group'><button class='btn btn-xs green dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'> 操作 <i class='fa fa-angle-down'></i></button><ul class='dropdown-menu pull-right' role='menu'>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.updateClass+"'></i>"+actionInfo.updateInfo+"</a></li>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.putTopClass+"'></i>"+actionInfo.putTopInfo+"</a></li>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.publishClass+"'></i>"+actionInfo.publishInfo+"</a></li>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.commentTypeClass+"'></i>"+actionInfo.commentTypeInfo+"</a></li>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.previewClass+"'></i>"+actionInfo.previewInfo+"</a></li>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.viewCommentsClass+"'></i>"+actionInfo.viewCommentsInfo+"</a></li><li class='divider'></li>" +
//            			"<li><a href='javascript:;' ><i class='"+actionInfo.deleteClass+"'></i>"+actionInfo.deleteInfo+"</a></li></ul></div>";
//            	$tds.eq(8).html(actionHtml).find("a").each(function(index,element){
//            		$(element).click(function(){
//            			operationArticle(actionInfo,index);
//            		});
//            	});
//            	
//            }
//            
//        });
        
    	//客户端模式
    	table = $('#articleTables').DataTable({
            "language": language,
            "processing": false, //关闭加载提示
            "serverSide": serverSide, //服务器模式选择
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
            	var url="/admin/article/query/all";
            	if(serverSide){
            		url="/admin/article/query";
            	}
            	loadData("blockui_articleTables",url,serverSide,data,callback);
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
            			updateUrl : "/admin/article/query/",
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
            			operationArticle(actionInfo,index);
            		});
            	});
            	
            }
            
        });
    	
    }
    
    /**
     * 评论列表
     * @param serverSide 是否服务器模式true,false
     */
    var commentTables = function (serverSide,commentStatus) {
    	table = $('#commentTables').DataTable({
    		"language": language,
            "processing": false, //关闭加载提示
            "serverSide": serverSide, //关闭服务器模式
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
                {"data":"commentId"},
                {"data":"articleId"},
                {"data":"articleTitle"},
                {"data":"commentContent"},
                {"data":null},
                {"data":"commentDate"},
                {"data":"commentEmail"},
                {"data":"commentName"},
                {"data":"commentQq"},
                {"data":"commentUrl"},
                {"data":"commentAudit"},
                {"data":"commentSpam"},
                {"data":"commentType"},
                {"data":"originalCommentId"},
                {"data":"originalCommentName"},
                {"data":"iconUrl"}
            ],
            "columnDefs": [
                {  // set default column settings
                	'orderable': false,
                    'targets': [0,1,2,3,4,5,7,8,9,10,11,12,13,14,15,16]
                }, 
                {
                    "searchable": false,
                    "targets": [0,1,2,5,6,7,10,11,12,13,14,15,16]
                },
                {
                    "render": function(data, type, row , meta) { //参看https://datatables.net/reference/option/columns.render
                    	var time=new Date(data);
                        return time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
                    },
                    "targets": [6]
                },
                {
                	"visible": false, //可见性
                    "targets": [1,2,7,9,10,11,12,13,14,15,16]
                },
                {
                    "className": "dt-right", 
                }
             ],
    		"order": [
    		    [6, "desc"]
             ],
             
             "ajax" : function(data, callback, settings){
            	
            	var url="/admin/comment/query/all";
             	if(serverSide){
             		url="/admin/comment/query";
             	}else if(commentStatus=="audit"){ //所有待审评论
             		url="/admin/comment/query/audit";
             	}else if(commentStatus=="approval"){  //所有批准评论
             		url="/admin/comment/query/approval";
             	}else if(commentStatus=="spam"){ //所有垃圾评论
             		url="/admin/comment/query/spam";
             	}else if(commentStatus=="all"){
             		url="/admin/comment/query/all";
             	}
             	loadData("blockui_commentTables",url,serverSide,data,callback);
             	
             },
             "createdRow":function(row, data, dataIndex){
            	//row:已经被创建的tr元素
             	//data:包含这行的数据对象
             	//dataIndex:Datatables内部存储的数据索引
            	var $tds=$('td', row);
            	//设置状态
             	var stateHtml="";
             	var id=data.commentId;
             	if(data.commentAudit==1){
             		stateHtml +='<span class="label label-sm label-warning" style="cursor:pointer;" onclick="operationComment(\'audit\',0,'+id+')">驳回</span>';
             	}else{
             		stateHtml='<span class="label label-sm label-success" style="cursor:pointer;" onclick="operationComment(\'audit\',1,'+id+')">批准</span>';
             	}
             	if(data.commentSpam==0){
             		stateHtml +='<span class="label label-sm label-danger" style="cursor:pointer;" onclick="operationComment(\'spam\',1,'+id+')">垃圾</span>';
             	}else{
             		stateHtml +='<span class="label label-sm label-primary" style="cursor:pointer;" onclick="operationComment(\'spam\',0,'+id+')">恢复</span>';
             	}
             	stateHtml += '<span class="label label-sm label-info" style="cursor:pointer;" onclick="operationComment(\'delete\',1,'+id+')">删除</span>';
             	//设置@谁 样式
             	var replyTo="";
             	if(data.commentType==1){
             		replyTo = '<span class="font-red-haze">@:</span><span class="font-blue-soft">'+data.originalCommentName+'</span><br>';
             	}
             	$tds.eq(2).html(replyTo+data.commentContent);
             	$tds.eq(3).html(stateHtml);
             	$tds.not(":eq(2)").css("vertical-align","middle");
             }
    	});
    	
    	
    }
    
    return {
        init: function (tableType) {
            if (!jQuery().dataTable) {
                return;
            }
            if(tableType.dataTablesType=="article"){
            	articleTables(tableType.serverSide); //false客户端模式加载
            	
            }else if(tableType.dataTablesType=="comment"){
            	commentTables(tableType.serverSide,tableType.commentStatus);
            }
            linenum();//生成行号
        }
    };
}();
