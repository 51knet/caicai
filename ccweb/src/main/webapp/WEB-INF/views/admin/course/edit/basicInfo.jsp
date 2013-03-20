<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function(){
	var courseType=$("#courseType").attr("title");
	$("#courseType option[value='"+courseType+"']").attr("selected","selected");
	var courseDescEditor = KindEditor.create('textarea[name="courseDesc"]',{
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
});
</script>

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
<script type="text/javascript">
setTimeout(function(){
		document.getElementById("message").style.display="none";
},2000);
</script>
<div class="row-fluid custom round">
	<div class="row">
		<h4>基本信息</h4>
	</div>
	<div class="row1">
		<form action="<c:url value="/admin/teacher/course/edit/basicinfomodify"></c:url>" method="post" id="basic_info_form" name="basic">
				<div id="message" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
				<input type="hidden" value="${course.id}" name="courseId">

				<div id="courseName" class="modal-body">
						课程名称：<input type="text"  style="width: 450px; " name="courseName" value="${course.courseName }" />
						<span class="help-inline"><form:errors path="name"></form:errors></span>
				</div>
				<div class="modal-body">
							课程类别：<select name="courseType" style="width: 460px; " id="courseType" title="${course.courseType}">
								<option selected value="计算机科学与技术">计算机科学与技术</option>
								<option value="生物">生物</option>
								<option value="数学">数学</option>
								<option value="化学">化学</option>
								<option value="语文">语文</option>
								<option value="金融">金融</option>
								<option value="英语">英语</option>
								<option value="哲学">哲学</option>
								<option value="其他">其他</option>
						</select>
						<span class="help-inline"></span>
					</div>
					<div class="control-group"  id="courseDesc" style="margin-left: 15px;">
						课程介绍：
						<div class="controls" style="margin-left: 70px; margin-top: -20px;">
							<textarea  id="KEcourseDesc"  rows="5" name="courseDesc" cols="8" style="width:600px;height: 250px;margin-left: 5px;">${course.courseDesc}</textarea>
							<span class="help-inline"></span>
						</div>
					</div>
					<div style="text-align: left; margin-left: 80px; float: left; ">
						<button type="submit" class="btn  btn-success">保存</button>
					</div>

		</form>
	</div>
	
</div>
