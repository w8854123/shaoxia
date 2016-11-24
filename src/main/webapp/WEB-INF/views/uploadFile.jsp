<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><span>媒体库管理</span> <i class="fa fa-circle"></i></li>
		<li><span id="twoMenuName"></span></li>
	</ul>
</div>
<!-- END PAGE BAR -->
<h2></h2>
<input id="qiniuHOST" type="hidden" value="${qiniuHOST }">
<div class="col-md-9">
<!-- 	<div class="fileinput fileinput-new" data-provides="fileinput">
		<div class="fileinput-preview thumbnail" id="fileInput"></div>
		<div id="uploadButton">
			<span class="btn red btn-outline btn-file">
				<span class="fileinput-new">选择文件</span>
				<span class="fileinput-exists">更换文件</span>
				<input type="file" name="file">
			</span>
			<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput">移除文件</a>
		</div>
	</div>
	<div class="clearfix margin-top-10">
		<span class="label label-success">提示!</span>请上传小于50MB的文件
	</div> -->
<!-- 	<div class="clearfix margin-top-10" id="fileInput">
		<button id="uploadButton" type="button" data-loading-text="正在上传..." class="btn green mt-ladda-btn ladda-button Settings" data-style="zoom-in">
			<i class="fa fa-save"></i>
			<span class="ladda-label">上传文件</span>
		</button>
	</div> -->
	<div id="fileInput">
		<a href="javascript:;" class="btn btn-lg green" id="uploadButton"> 选择文件
	        <i class="fa fa-plus"></i>
	    </a>
	</div>
	<div class="portlet-body" id="blockui_mediaTables">
		<table class="table table-striped table-bordered table-hover table-checkable order-column" id="mediaTables">
			<thead>
				<tr>
					<th>#</th>
					<th>id</th>
					<th>文件名</th>
					<th>外链地址</th>
					<th>文件类型</th>
					<th>文件后缀</th>
					<th>存储位置</th>
					<th>云服务商</th>
					<th>七牛key</th>
					<th>上传时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>