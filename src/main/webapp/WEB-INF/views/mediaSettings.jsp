<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><span>系统设置</span> <i class="fa fa-circle"></i></li>
		<li><span id="twoMenuName"></span></li>
	</ul>
</div>
<!-- END PAGE BAR -->
<h2></h2>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="actions">
			<button type="button" data-loading-text="正在设置..." onclick="saveGeneralSett('update')"
				class="btn green mt-ladda-btn ladda-button generalSett" data-style="zoom-in">
				<i class="fa fa-save"></i> <span class="ladda-label">保存更改</span>
			</button>
		</div>
	</div>
	<form id="generalSetForm">
		<h2>缩略图大小</h2>
		<div class="row">
			<div class="col-md-2">
				<input type="hidden" name="listOption[0].optionId">
				<input type="hidden" name="listOption[0].optionName" value="siteTitle">
				<input type="hidden" name="listOption[0].autoload">
				<label>宽度(px)：</label> <input type="text" name="listOption[0].optionValue" class="touchSpin">
			</div>
			<div class="col-md-2">
				<label>高度(px)：</label> <input type="text" name="" class="touchSpin">
			</div>
		</div>
		<h2>七牛云</h2>
		<label>AccessKey：</label>
		<div>
			<input  type="text"  name="" class="form-control">
		</div>
		<label>SecretKey：</label>
		<div>
			<input  type="text"  name="" class="form-control">
		</div>
		<label>回源 HOST：</label>
		<div>
			<input  type="text"  name="" class="form-control">
		</div>
		<label>bucket：</label>
		<div>
			<input  type="text"  name="" class="form-control">
		</div>
	</form>
</div>



