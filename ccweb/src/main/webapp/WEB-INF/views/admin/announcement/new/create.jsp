<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<div class="row-fluid custom round">
	<div  class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/announcement/list"></c:url>' ><b>教师公告</b></a><hr>
		<div style="text-align: left;">
			
			<form action= "new"  method="post" name="anno_post">
				公告标签：<input type="text" name="title"  placeholder="公告标签">
				<span class="help-block"><form:errors path="title"></form:errors></span>
				
				<textarea cols="100" rows="20" style="width:700px;height:300px;"  name="content" id="textarea" placeholder="Content" ></textarea>
				<span class="help-block"><form:errors path="content"></form:errors></span>
			
				<label style="clear: right;"></label>
				<button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
				<button type="reset" class="btn">取消</button>
			</form>
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
					prettyPrint();
			    });
		</script>
		</div>
	</div>
</div>