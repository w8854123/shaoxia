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
<!-- 		<div class="caption font-green-haze">
			<i class="icon-settings font-green-haze"></i> <span class="caption-subject bold uppercase"> Horizontal Form</span>
		</div> -->
		<div class="actions">
<!-- 			<a class="btn btn-circle btn-icon-only blue" href="javascript:;"> <i class="icon-cloud-upload"></i>
			</a> <a class="btn btn-circle btn-icon-only green" href="javascript:;"> <i class="icon-wrench"></i>
			</a> <a class="btn btn-circle btn-icon-only red" href="javascript:;"> <i class="icon-trash"></i>
			</a> <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
			</a> -->
			<button type="button" data-loading-text="正在设置..." onclick="saveGeneralSett()" class="btn green mt-ladda-btn ladda-button generalSett" data-style="zoom-in">
				<i class="fa fa-save"></i>
				<span class="ladda-label">保存更改</span>
			</button>
		</div>
	</div>
	<div class="portlet-body form">
		<form role="form" class="form-horizontal" id="generalSetForm">
			<div class="form-body">
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="siteTitle">站点标题：</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[0].optionId">
						<input type="hidden" name="listOption[0].optionName" value="siteTitle">
						<input type="hidden" name="listOption[0].autoload">
						<input type="text" class="form-control" id="siteTitle" name="listOption[0].optionValue">
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="subtitle">副标题：</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[1].optionId">
						<input type="hidden" name="listOption[1].optionName" value="subtitle">
						<input type="hidden" name="listOption[1].autoload">
						<input type="text" class="form-control" id="subtitle" name="listOption[1].optionValue">
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="webUrl">站点地址：</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[2].optionId">
						<input type="hidden" name="listOption[2].optionName" value="webUrl">
						<input type="hidden" name="listOption[2].autoload">
						<input type="text" class="form-control" id="webUrl" name="listOption[2].optionValue">
						<div class="form-control-focus"></div>
						<span class="help-block">http://xxx.xxx.xxx</span>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="metaKeywords">Meta Keywords:</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[3].optionId">
						<input type="hidden" name="listOption[3].optionName" value="metaKeywords">
						<input type="hidden" name="listOption[3].autoload">
						<input type="text" class="form-control" id="metaKeywords" name="listOption[3].optionValue">
						<div class="form-control-focus"></div>
						<span class="help-block">填入站点的关键词,多个词之间用英文逗号隔开</span>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-2 control-label" for="metaDescription">Meta Description:</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[4].optionId">
						<input type="hidden" name="listOption[4].optionName" value="metaDescription">
						<input type="hidden" name="listOption[4].autoload">
						<input type="text" class="form-control" id="metaDescription" name="listOption[4].optionValue">
						<div class="form-control-focus"></div>
						<span class="help-block">填入站点的描述</span>
					</div>
				</div>
				<div class="form-group form-md-line-input has-success">
					<label class="col-md-2 control-label" for="htmlHead">HTML head:</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[5].optionId">
						<input type="hidden" name="listOption[5].optionName" value="htmlHead">
						<input type="hidden" name="listOption[5].autoload">
						<textarea class="form-control" rows="3" id="htmlHead" name="listOption[5].optionValue"></textarea>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input has-success">
					<label class="col-md-2 control-label" for="announcement">公告：</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[6].optionId">
						<input type="hidden" name="listOption[6].optionName" value="announcement">
						<input type="hidden" name="listOption[6].autoload">
						<textarea class="form-control" rows="3" id="announcement" name="listOption[6].optionValue"></textarea>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input has-success">
					<label class="col-md-2 control-label" for="pageFooter">页脚：</label>
					<div class="col-md-10">
						<input type="hidden" name="listOption[7].optionId">
						<input type="hidden" name="listOption[7].optionName" value="pageFooter">
						<input type="hidden" name="listOption[7].autoload">
						<textarea class="form-control" rows="3" id="pageFooter" name="listOption[7].optionValue"></textarea>
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
<!-- 			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-2 col-md-10">
						<button type="button" class="btn default">Cancel</button>
						<button type="button" class="btn blue">Submit</button>
					</div>
				</div>
			</div> -->
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