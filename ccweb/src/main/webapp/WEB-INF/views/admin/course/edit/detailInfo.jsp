<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.basic {
	margin-left:5px;
	float: left;
}
</style>
<div style="margin-top: 10px;">
	<a href="#">详细信息</a>
	<hr />
	<form action="detailinfomodify" method="post" name="detail_form">
		<div>
			<table >
				<tr>
					<td align="left" valign="top">目标人群：</td>
					<td  align="left"  valign="top"><textarea rows="6" cols="8" name="targetPerson" style="width: 670px; height:170px;">${course.targetPerson}</textarea></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="left" valign="top">课程看点：</td>
					<td align="left" valign="top"><textarea rows="6" cols="8" style="width: 670px; height:170px;" name="courseCharacter">${course.courseCharacter}</textarea></td>
				</tr>
				<tr><td style="text-align: left;margin-left: 80px;float: left;margin-top: 20px;" colspan="2" ><button class="btn btn-large btn-success">保存</button></td></tr>
			</table>
		</div>
	</form>
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
</div>