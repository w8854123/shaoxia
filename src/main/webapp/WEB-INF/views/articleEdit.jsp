<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><span>文章管理</span> <i class="fa fa-circle"></i></li>
		<li><span id="twoMenuName"></span></li>
	</ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE TITLE-->
<h2 class="page-title">
	标题 
</h2>
<!-- END PAGE TITLE-->
<input id="articleTitle" type="text" class="form-control" placeholder="文章标题">

<h2 class="page-title">正文</h2>
<div name="summernote" id="content"></div>

<h2 class="page-title">标签
	<small>（使用英文输入状态下的逗号进行分隔）</small>
</h2>
<input id="articleTags" type="text" class="form-control" placeholder="标签">

<h2 class="page-title">摘要</h2>
<div name="summernote" id="abstr"></div>
<div class="text-right">
<p style="display:-webkit-inline-box;" >允许评论：</p>
<input id="allowComment" type="checkbox" checked class="make-switch replyCheckbox" data-size="small" value="0">
<button type="button" data-loading-text="保存中..." onclick="saveArticle()"
	class="btn green mt-ladda-btn ladda-button" data-style="zoom-in">
	<i class="fa fa-save"></i>
	<span class="ladda-label">草稿箱</span>
</button>
<button type="button" data-loading-text="保存中..." onclick=""
	class="btn red mt-ladda-btn ladda-button" data-style="zoom-in">
	<i class="fa fa-share-square-o"></i>
	<span class="ladda-label">发&nbsp;&nbsp;布</span>
</button>
</div>
