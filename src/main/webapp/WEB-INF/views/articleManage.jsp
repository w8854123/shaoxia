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
<!-- <a class="btn dark btn-outline sbold" data-toggle="modal" href="#full"> View Demo </a> -->
</h2>
<!-- <div class="portlet light bordered"> -->
	<div class="portlet-body" id="blockui_articleTables">
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
		<table class="table table-striped table-bordered table-hover table-checkable order-column" id="articleTables">
			<thead>
				<tr>
					<!-- <th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="group-checkable" data-set="#sample_1 .checkboxes" /> <span></span>
					</label></th> -->
					<th>#</th>
					<th>标题</th>
					<th>作者</th>
					<th>状态</th>
					<th>评论</th>
					<th>浏览</th>
					<th>点赞</th>
					<th>创建日期</th>
					<th>操作</th>
					<th>id</th>
					<th>置顶</th>
					<th>禁评</th>
					<th>草稿</th>
				</tr>
			</thead>
			<tbody>
				<!-- <tr class="odd gradeX">
					<td><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="checkboxes" value="1" /> <span></span>
					</label></td>
					<td></td>
					<td>不shuxer<input style="display: none;" value="1"></td>
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
								<li><a href="javascript:;"> <i class="icon-note"></i> 更 新
								</a></li>
								<li><a href="javascript:;"> <i class="icon-like"></i> 置 顶
								</a></li>
								<li><a href="javascript:;"> <i class="icon-eye"></i> 预 览
								</a></li>
								<li><a href="javascript:;"> <i class="icon-bubbles"></i> 评 论 <span class="badge badge-success">4</span>
								</a></li>
								<li class="divider"></li>
								<li><a href="javascript:;"> <i class="icon-trash"></i> 删 除
								</a></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"> <input type="checkbox"
							class="checkboxes" value="1" /> <span></span>
					</label></td>
					<td></td>
					<td>啊looper<input style="display: none;" value="2"></td>
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
					<td></td>
					<td>可以嘛打实大苏打阿斯顿阿斯顿阿斯顿阿斯顿阿斯顿揉揉绕测试这是什么结尾啦<input style="display: none;" value="3"></td>
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
					<td></td>
					<td>foopl<input style="display: none;" value="4"></td>
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
				</tr> -->
			</tbody>
		</table>
	</div>
<!-- </div> -->
<!-- END EXAMPLE TABLE PORTLET-->

<!-- /.modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-full">
		<div class="modal-content">
			<!--  			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">Modal Title</h4>
			</div> -->
			<div class="modal-body">
				<input id="articleId" type="hidden" value="">
				<h2 class="page-title">标题</h2>
				<!-- END PAGE TITLE-->
				<input id="articleTitle" type="text" class="form-control" placeholder="文章标题">
				<h2 class="page-title">正文</h2>
				<div class="portlet light form-fit">
					<div class="portlet-body form">
						<div id="content"></div>
					</div>
				</div>
				<h2 class="page-title">
					标签 <small>（使用英文输入状态下的逗号进行分隔）</small>
				</h2>
				<input id="articleTags" type="text" class="form-control" placeholder="标签">
				<h2 class="page-title">摘要</h2>
				<div class="portlet light form-fit">
					<div class="portlet-body form">
						<div id="abstr"></div>
					</div>
				</div>
				<div class="text-right">
					<p style="display: -webkit-inline-box;">允许评论：</p>
					<input id="allowComment" type="checkbox" checked class="make-switch replyCheckbox" data-size="small" value="0">
					<button type="button" data-loading-text="保存中..." onclick="saveArticle(1)" class="btn green mt-ladda-btn ladda-button draft"
						data-style="zoom-in">
						<i class="fa fa-save"></i> <span class="ladda-label">草稿箱</span>
					</button>
					<button type="button" data-loading-text="发布中..." onclick="saveArticle(0)" class="btn red mt-ladda-btn ladda-button publish"
						data-style="zoom-in">
						<i class="fa fa-share-square-o"></i> <span class="ladda-label">发&nbsp;&nbsp;布</span>
					</button>
				</div>

			</div>
			<!-- 			<div class="modal-footer">
				<button type="button" class="btn dark btn-outline" data-dismiss="modal">Close</button>
				<button type="button" class="btn green">Save changes</button>
			</div> -->
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>