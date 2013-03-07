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
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .row1 {
	margin: 20px 40px;

}
</style>
<div class="row-fluid custom round">
	<div  class="row">
		<h4>公告管理>添加公告</h4>
	</div>
	<div class="row1">
		<form action= "new"  method="post" name="anno_post" id="announcement_new_form">
			<div class="control-group" id="title">
				<label class="control-label" for="title">公告标签</label>
				<div class="controls">
					<input type="text" name="title" placeholder="公告标签"> <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="content">
				<div class="controls">
					<textarea  cols="100" rows="20" style="width:670px;height:300px;"  name="content"  placeholder="公告内容"></textarea>
					<span class="help-inline"></span>
				</div>
			</div>
			<label style="clear: right;"></label>
			<button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
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
					KindEditor.ctrl(document, 13, function() {
						self.sync();
						document.forms['anno_post'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['anno_post'].submit();
					});
				}
			});
			$("#announcement_new_form").submit(function(){
				editor.sync();
				return checkEmptyAjax("announcement_new_form","createAnnouncementAjax")
			});
			prettyPrint();
	    });
</script>