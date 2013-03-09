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
		<h4>公告管理>修改公告</h4>
	</div>
	<div class="row1">
		<div style="text-align: left;">
			<form action= "edit"  method="post" name="anno_post">
				<input type="hidden" value="${anno.id }" name="id" />
				公告标签：<input type="text" name="title"  placeholder="公告标签" value="${anno.title }">
				<span class="help-block"><form:errors path="title"></form:errors></span>
				<textarea cols="100" rows="20" style="width:670px;height:300px;"  name="content" id="textarea" placeholder="Content" >
					${anno.content }
				</textarea>
				<span class="help-block"><form:errors path="content"></form:errors></span>
				<label style="clear: right;"></label>
				<button type="submit" class="btn btn-success">保存</button>&nbsp;&nbsp;
				<button type="reset" class="btn">取消</button>
			</form>
		</div>
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
			prettyPrint();
	    });
</script>