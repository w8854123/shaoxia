<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><span>系统设置</span> <i class="fa fa-circle"></i></li>
		<li><span id="twoMenuName"></span></li>
	</ul>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN SAMPLE FORM PORTLET-->
<h2></h2>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="actions">
			<button type="button" data-loading-text="正在设置..." onclick="saveSett('update')" class="btn green mt-ladda-btn ladda-button Settings" data-style="zoom-in">
				<i class="fa fa-save"></i>
				<span class="ladda-label">保存更改</span>
			</button>
		</div>
	</div>
	<div class="portlet-body form">
		<form role="form" class="form-horizontal" id="generalSetForm">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-4 control-label">文章列表显示方式：</label>
					<input type="hidden" name="listOption[0].optionId">
					<input type="hidden" name="listOption[0].optionName" value="articleListDisplay">
					<input type="hidden" name="listOption[0].autoload">
					<div class="col-md-4">
                        <select class="form-control" name="listOption[0].optionValue">
                            <option value="onlyTitle">仅标题</option>
                            <option value="titleAndAbstract">标题+摘要</option>
                            <option value="titleAndBody">标题+正文</option>
                        </select>
                    </div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">最新评论显示数目：</label>
					<input type="hidden" name="listOption[1].optionId">
					<input type="hidden" name="listOption[1].optionName" value="latestCommentsShowsNumber">
					<input type="hidden" name="listOption[1].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[1].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">评论最多文章显示数目：</label>
					<input type="hidden" name="listOption[2].optionId">
					<input type="hidden" name="listOption[2].optionName" value="mostCommentedPostsNumber">
					<input type="hidden" name="listOption[2].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[2].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">访问最多文章显示数目：</label>
					<input type="hidden" name="listOption[3].optionId">
					<input type="hidden" name="listOption[3].optionName" value="numberOfArticlesMostRead">
					<input type="hidden" name="listOption[3].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[3].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">分页每页显示文章数：</label>
					<input type="hidden" name="listOption[4].optionId">
					<input type="hidden" name="listOption[4].optionName" value="numberOfArticlesPerPage">
					<input type="hidden" name="listOption[4].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[4].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">分页页码最大宽度：</label>
					<input type="hidden" name="listOption[5].optionId">
					<input type="hidden" name="listOption[5].optionName" value="paginationPageNumbersLength">
					<input type="hidden" name="listOption[5].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[5].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">随机阅读显示数目：</label>
					<input type="hidden" name="listOption[6].optionId">
					<input type="hidden" name="listOption[6].optionName" value="randomArticleNumbers">
					<input type="hidden" name="listOption[6].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[6].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">相关阅读显示数目：</label>
					<input type="hidden" name="listOption[7].optionId">
					<input type="hidden" name="listOption[7].optionName" value="relatedArticlesNumbers">
					<input type="hidden" name="listOption[7].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[7].optionValue" class="touchSpin">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">站外相关阅读显示数目：</label>
					<input type="hidden" name="listOption[8].optionId">
					<input type="hidden" name="listOption[8].optionName" value="outsideRelatedArticlesNumbers">
					<input type="hidden" name="listOption[8].autoload">
					<div class="col-md-4">
						<input type="text" name="listOption[8].optionValue" class="touchSpin">
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- END SAMPLE FORM PORTLET-->
<!-- BEGIN SAMPLE FORM PORTLET-->
<!-- <div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption font-green-haze">
			<i class="icon-settings font-green-haze"></i> <span class="caption-subject bold uppercase"> Checkbox & Radios</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only blue" href="javascript:;"> <i class="icon-cloud-upload"></i>
			</a> <a class="btn btn-circle btn-icon-only green" href="javascript:;"> <i class="icon-wrench"></i>
			</a> <a class="btn btn-circle btn-icon-only red" href="javascript:;"> <i class="icon-trash"></i>
			</a> <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
			</a>
		</div>
	</div>
	<div class="portlet-body form">
		<form role="form" class="form-horizontal">
			<div class="form-body">
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="form_control_1">Checkboxes</label>
					<div class="col-md-10">
						<div class="md-checkbox-list">
							<div class="md-checkbox">
								<input type="checkbox" id="checkbox30" class="md-check"> <label for="checkbox30"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 1
								</label>
							</div>
							<div class="md-checkbox has-error">
								<input type="checkbox" id="checkbox31" class="md-check" checked> <label for="checkbox31"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 2
								</label>
							</div>
							<div class="md-checkbox has-warning">
								<input type="checkbox" id="checkbox32" class="md-check"> <label for="checkbox32"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 3
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="form_control_1">Inline Checkboxes</label>
					<div class="col-md-10">
						<div class="md-checkbox-inline">
							<div class="md-checkbox">
								<input type="checkbox" id="checkbox33" class="md-check"> <label for="checkbox33"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 1
								</label>
							</div>
							<div class="md-checkbox has-error">
								<input type="checkbox" id="checkbox34" class="md-check" checked> <label for="checkbox34"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 2
								</label>
							</div>
							<div class="md-checkbox has-info">
								<input type="checkbox" id="checkbox35" class="md-check"> <label for="checkbox35"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 3
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="form_control_1">Radios</label>
					<div class="col-md-10">
						<div class="md-radio-list">
							<div class="md-radio">
								<input type="radio" id="radio50" name="radio211" class="md-radiobtn"> <label for="radio50"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 1
								</label>
							</div>
							<div class="md-radio has-error">
								<input type="radio" id="radio51" name="radio211" class="md-radiobtn" checked> <label for="radio51">
									<span></span> <span class="check"></span> <span class="box"></span> Option 2
								</label>
							</div>
							<div class="md-radio has-warning">
								<input type="radio" id="radio52" name="radio231" class="md-radiobtn"> <label for="radio52"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 3
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="form_control_1">Inline Radios</label>
					<div class="col-md-10">
						<div class="md-radio-inline">
							<div class="md-radio">
								<input type="radio" id="radio53" name="radio2" class="md-radiobtn"> <label for="radio53"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 1
								</label>
							</div>
							<div class="md-radio has-error">
								<input type="radio" id="radio54" name="radio2" class="md-radiobtn" checked> <label for="radio54">
									<span></span> <span class="check"></span> <span class="box"></span> Option 2
								</label>
							</div>
							<div class="md-radio has-warning">
								<input type="radio" id="radio55" name="radio2" class="md-radiobtn"> <label for="radio55"> <span></span>
									<span class="check"></span> <span class="box"></span> Option 3
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-2 col-md-10">
						<button type="button" class="btn default">Cancel</button>
						<button type="button" class="btn blue">Submit</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div> -->
<!-- END SAMPLE FORM PORTLET-->