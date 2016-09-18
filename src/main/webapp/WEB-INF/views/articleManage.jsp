<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><span>文章管理</span> <i class="fa fa-circle"></i></li>
		<li><span id="twoMenuName"></span></li>
	</ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN EXAMPLE TABLE PORTLET-->
<h2>
</h2>
<!-- <div class="portlet light bordered"> -->
	<div class="portlet-body">
		<!-- <div class="table-toolbar">
			<div class="row">
				<div class="col-md-6">
					<div class="btn-group">
						<button id="sample_editable_1_new" class="btn sbold green">
							Add New <i class="fa fa-plus"></i>
						</button>
					</div>
				</div>
				<div class="col-md-6">
					<div class="btn-group pull-right">
						<button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">
							Tools <i class="fa fa-angle-down"></i>
						</button>
						<ul class="dropdown-menu pull-right">
							<li><a href="javascript:;"> <i class="fa fa-print"></i> Print
							</a></li>
							<li><a href="javascript:;"> <i class="fa fa-file-pdf-o"></i> Save as PDF
							</a></li>
							<li><a href="javascript:;"> <i class="fa fa-file-excel-o"></i> Export to Excel
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div> -->
		<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="group-checkable" data-set="#sample_1 .checkboxes" /> <span></span>
					</label></th>
					<th>标题</th>
					<th>作者</th>
					<th>状态</th>
					<th>评论</th>
					<th>浏览</th>
					<th>点赞</th>
					<th>创建日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="odd gradeX">
					<td><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="checkboxes" value="1" /> <span></span>
					</label></td>
					<td>不shuxer</td>
					<td><a href="mailto:shuxer@gmail.com"> shuxer@gmail.com </a></td>
					<td><span class="label label-sm label-success"> 允许评论 </span></td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">12 Jan 2012</td>
					<td>
						<div class="btn-group">
							<button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
								操作 <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="javascript:;"> <i class="icon-docs"></i> 更新
								</a></li>
								<li><a href="javascript:;"> <i class="icon-tag"></i> 置顶
								</a></li>
								<li><a href="javascript:;"> <i class="icon-user"></i> 预览
								</a></li>
								<li class="divider"></li>
								<li><a href="javascript:;"> <i class="icon-flag"></i> 评论 <span class="badge badge-success">4</span>
								</a></li>
								<li><a href="javascript:;"> <i class="icon-user"></i> 删除
								</a></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="checkboxes" value="1" /> <span></span>
					</label></td>
					<td>啊looper</td>
					<td><a href="mailto:looper90@gmail.com"> looper90@gmail.com </a></td>
					<td><span class="label label-sm label-warning"> 草稿箱 </span></td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">12.12.2011</td>
					<td>
						<div class="btn-group">
							<button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
								操作 <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="javascript:;"> <i class="icon-docs"></i> New Post
								</a></li>
								<li><a href="javascript:;"> <i class="icon-tag"></i> New Comment
								</a></li>
								<li><a href="javascript:;"> <i class="icon-user"></i> New User
								</a></li>
								<li class="divider"></li>
								<li><a href="javascript:;"> <i class="icon-flag"></i> Comments <span class="badge badge-success">4</span>
								</a></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="checkboxes" value="1" /> <span></span>
					</label></td>
					<td>可以嘛打实大苏打阿斯顿阿斯顿阿斯顿阿斯顿阿斯顿揉揉绕测试这是什么结尾啦</td>
					<td><a href="mailto:userwow@gmail.com"> userwow@gmail.com </a></td>
					<td><span class="label label-sm label-danger"> 禁止评论 </span></td>
					<td class="center">12345</td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">12.12.2011</td>
					<td>
						<div class="btn-group">
							<button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
								操作 <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="javascript:;"> <i class="icon-docs"></i> New Post
								</a></li>
								<li><a href="javascript:;"> <i class="icon-tag"></i> New Comment
								</a></li>
								<li><a href="javascript:;"> <i class="icon-user"></i> New User
								</a></li>
								<li class="divider"></li>
								<li><a href="javascript:;"> <i class="icon-flag"></i> Comments <span class="badge badge-success">4</span>
								</a></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="checkboxes" value="1" /> <span></span>
					</label></td>
					<td>foopl</td>
					<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
					<td><span class="label label-sm label-info"> 已发布 </span></td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">0</td>
					<td class="center">12.12.2011</td>
					<td>
						<div class="btn-group">
							<button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
								操作 <i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="javascript:;"> <i class="icon-docs"></i> New Post
								</a></li>
								<li><a href="javascript:;"> <i class="icon-tag"></i> New Comment
								</a></li>
								<li><a href="javascript:;"> <i class="icon-user"></i> New User
								</a></li>
								<li class="divider"></li>
								<li><a href="javascript:;"> <i class="icon-flag"></i> Comments <span class="badge badge-success">4</span>
								</a></li>
							</ul>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
<!-- </div> -->
<!-- END EXAMPLE TABLE PORTLET-->
