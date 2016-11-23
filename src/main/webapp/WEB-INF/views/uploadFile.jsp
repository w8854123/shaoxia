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
<div class="col-md-9">
	<div class="fileinput fileinput-new" data-provides="fileinput">
		<div class="fileinput-preview thumbnail"></div>
		<div>
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
	</div>
	<div class="clearfix margin-top-10" id="fileInput">
		<button id="uploadButton" type="button" data-loading-text="正在上传..." class="btn green mt-ladda-btn ladda-button Settings" data-style="zoom-in">
			<i class="fa fa-save"></i>
			<span class="ladda-label">上传文件</span>
		</button>
	</div>
</div>
<input id="qiniuHOST" type="hidden" value="${qiniuHOST }">
