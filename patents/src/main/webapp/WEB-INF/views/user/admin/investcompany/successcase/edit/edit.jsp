<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}

.row-fluid .custom .row {
	margin: 10px 40px;
	color: #3d4f67;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content{
	margin: 20px 40px;

}
</style>
<div class="row-fluid custom round">
	<div  class="row">
		<h4>成功案例>案例修改</h4>
	</div>
	<div class="content">
		<form action= "<c:url value='/admin/investcompany/successcase/edit/edit'></c:url>"  method="post" name="case_post" id="case_new_form"    >
			<input type="hidden" value="${successCase.id }" name="case_id"  />
			<div class="control-group" id="name">
				<label class="control-label" for="name">公司名称</label>
				<div class="controls">
					<input type="text" name="name" placeholder="公司名称" value="${successCase.name }"> <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="money">
				<label class="control-label" for="money">投资额度</label>
				<div class="controls">
					<input type="text" name="money" placeholder="投资额度" value="${successCase.money }"> <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="field">
				<label class="control-label" for="field">投资领域</label>
				<div class="controls">
					<input type="text" name="field" placeholder="投资领域" value="${successCase.field }"> <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="date">
				<label class="control-label" for="date">投资时间</label>
				<div class="controls">
					<input type="text" name="date" placeholder="投资时间" class="Wdate" onClick="WdatePicker()" value="${successCase.date }"> <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="content">
				<div class="controls">
					<textarea  style="width:670px;height:300px;"  name="content"  placeholder="公司介绍">${successCase.content }</textarea>
					<span class="help-inline"></span>
				</div>
			</div>
			<label style="clear: right;"></label>
			<button type="submit" class="btn btn-success">保存</button>&nbsp;&nbsp;
			<button type="reset" class="btn">取消</button>
		</form>
	</div>
</div>

<c:url var="uploadJson" value="/file_upload/${sessionUserInfo.id}"></c:url>
<c:url var="fileManagerJson" value="/file_manager/${sessionUserInfo.id}"></c:url>
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/themes/default/default.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			var editor = KindEditor.create('textarea[name="content"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
				}
			});
		
			prettyPrint();
			
			$("#case_new_form").submit(function(){
				return checkEmptyAjax("case_new_form", '<c:url value="/admin/investcompany/successcase/caseInfoAJAX"></c:url>');
			});
	    });
</script>