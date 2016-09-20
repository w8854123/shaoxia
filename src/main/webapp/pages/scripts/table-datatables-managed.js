var TableDatatablesManaged = function () {

    var initTable1 = function () {

//        var table = $('#sample_1');

        // begin first table
        var table = $('#sample_1').DataTable({

            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
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
            
            "processing": true, //开启加载提示
            "serverSide": true, //开启服务器模式
            
            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
            // So when dropdowns used the scrollable div should be removed. 
            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",

            "bStateSave": false, // save datatable state(pagination, sort, etc) in cookie.
            
            "searching": true,//是否允许Datatables开启本地搜索
            
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
                       {"data":"articleId"}
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
                    "className": "dt-right", 
                    //"targets": [2]
                },
                {
                	"visible": false, //可见性
                    "targets": [9]
                },
                {
                    "render": function(data, type, row) {
                    	var time=new Date(data);
                        return time.getFullYear()+"年"+(time.getMonth()+1)+"月"+time.getDate()+"日";
                    },
                    "targets": [7]
                }
            ],
            "order": [
                [7, "desc"]
            ], // set first column as a default sort by asc
            
            ajax : function(data, callback, settings){ //data:发送给服务器的数据;callback:必须被执行，Datatables才能获取到数据;settings:Datatables的设置对象
            	//封装输入参数
            	var dtParam=getQueryParam(data);
            	
            	$.ajax({
            		url : "/admin/article/query",
            		type : "GET",
            		data : dtParam,
            		dataType : "json",
            		success : function(dtResult,textStatus) {
            			callback(dtResult);
            		},
            		error : function(XMLHttpRequest) {
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
            	var actionHtml="<div class='btn-group'><button class='btn btn-xs green dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false'> 操作 <i class='fa fa-angle-down'></i></button><ul class='dropdown-menu pull-right' role='menu'>" +
            			"<li><a href='javascript:;'><i class='icon-note'></i> 更 新 </a></li>" +
            			"<li><a href='javascript:;'><i class='icon-like'></i> 置 顶 </a></li>" +
            			"<li><a href='javascript:;'><i class='icon-eye'></i> 预 览 </a></li>" +
            			"<li><a href='javascript:;'><i class='icon-bubbles'></i> 评 论 <span class='badge badge-success'>4</span></a></li><li class='divider'></li>" +
            			"<li><a href='javascript:;'><i class='icon-trash'></i> 删 除 </a></li></ul></div>";
            	$tds.eq(8).html(actionHtml);
            }
            
        });
        
        //on 为 jQuery 里监听事件的方法，给元素绑定监听事件，这里监听了 order.dt 、search.dt 两个事件，意思是当排序和搜索后，重新生成行号
//        table.on('order.dt search.dt',function() {
//        	//table.column(0,{selector}).nodes() 这个的作用是获取第一列的 DOM 元素，接着用 each 方法遍历，把行号设置进去。这里 {selector} 为 DT 的高级应用
//        	table.column(0, {
//                search: 'applied', //applied  看得到的数据
//                order: 'applied'   
//            }).nodes().each(function(cell, i) {
//                cell.innerHTML = i + 1;
//            });
//        }).draw();//draw 最后一个重绘方法，生成序号后，重新绘制。DT 的每个操作，包括排序、过滤、翻页还是自己使用API操作这些操作或者是设置了数据都要再调用 draw 方法才行
        
//        var tableWrapper = jQuery('#sample_1_wrapper');

//        table.find('.group-checkable').change(function () {
//            var set = jQuery(this).attr("data-set");
//            var checked = jQuery(this).is(":checked");
//            jQuery(set).each(function () {
//                if (checked) {
//                    $(this).prop("checked", true);
//                    $(this).parents('tr').addClass("active");
//                } else {
//                    $(this).prop("checked", false);
//                    $(this).parents('tr').removeClass("active");
//                }
//            });
//        });
//
//        table.on('change', 'tbody tr .checkboxes', function () {
//            $(this).parents('tr').toggleClass("active");
//        });
    }

    return {

        //main function to initiate the module
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }

            initTable1();
        }

    };

}();

//if (App.isAngularJsApp() === false) { 
//    jQuery(document).ready(function() {
//        TableDatatablesManaged.init();
//    });
//}

/**
 * 分装输出参数
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