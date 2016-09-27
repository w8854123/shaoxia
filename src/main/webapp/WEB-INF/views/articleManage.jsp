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
	<div class="portlet-body" id="blockui_articleTables">
		<table class="table table-striped table-bordered table-hover table-checkable order-column" id="articleTables">
			<thead>
				<tr>
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
			</tbody>
		</table>
	</div>
<!-- </div> -->
<!-- END EXAMPLE TABLE PORTLET-->

<!-- /.modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-full">
		<div class="modal-content">
			<div class="modal-body">
				<input id="articleId" type="hidden" value="">
				<h2 class="page-title">标题</h2>
				<!-- END PAGE TITLE-->
				<input id="articleTitle" type="text" class="form-control" placeholder="文章标题" maxlength="126">
				<h2 class="page-title">正文</h2>
				<div class="portlet light form-fit">
					<div class="portlet-body form">
						<div id="content"></div>
					</div>
				</div>
				<h2 class="page-title">
					标签 <small>（使用英文输入状态下的逗号进行分隔）</small>
				</h2>
				<input id="articleTags" type="text" class="form-control" placeholder="标签" maxlength="126">
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
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>