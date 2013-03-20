<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.basic {
	margin-left:5px;
	float: left;
}
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
<script type="text/javascript">
setTimeout(function(){
		document.getElementById("message").style.display="none";
},2000);
</script>
<div class="row-fluid custom round">
	<div class="row">
		<h4>详细信息</h4>
	</div>
	<div class="row1">
		<form action="<c:url value="/admin/teacher/course/edit/detailinfomodify"></c:url>" method="post" name="detail_form">
				<div id="message" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
				<input type="hidden" name="courseId" value="${course.id}">
				<div>
					<table >
						<tr>
							<td align="left" valign="top">目标人群：</td>
							<td  align="left"  valign="top"><textarea rows="6" cols="8" name="targetPerson" style="width: 600px; height:250px;">${course.targetPerson}</textarea></td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td align="left" valign="top">课程看点：</td>
							<td align="left" valign="top"><textarea rows="6" cols="8" style="width: 600px; height:250px;" name="courseCharacter">${course.courseCharacter}</textarea></td>
						</tr>
						<tr>
						</tr>
						<tr>
						<td colspan="2">
						<br/>
						<button class="btn  btn-success" style="margin-left: 70px;float: left;" >保存</button></td></tr>
					</table>
				</div>
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
			var editor = KindEditor.create('textarea[name="targetPerson"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					KindEditor.ctrl(document, 13, function() {
						self.sync();
						document.forms['detail_form'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['detail_form'].submit();
					});
				}
			});
			
			var editor1 = KindEditor.create('textarea[name="courseCharacter"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					KindEditor.ctrl(document, 13, function() {
						self.sync();
						document.forms['detail_form'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['detail_form'].submit();
					});
				}
			});
			prettyPrint();
	    });
</script>