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
<div style="margin-top: 10px;">
	<a href="#">基本信息</a>
	<hr />
	<form action="<c:url value="/admin/teacher/course/edit/basicinfomodify"></c:url>" method="post" id="basic_info_form" name="basic">
		<div>
			<div><input type="hidden" value="${course.id}" name="courseId"></div>
			<div style="margin-left:20px;">
				<div id="courseName" class="modal-body">
					课程名称：<input type="text"  style="width: 450px;margin-left:5px;margin-top: 2px;" name="courseName" value="${course.courseName }" />
					<span class="help-inline"><form:errors path="name"></form:errors></span>
				</div>
				<div class="modal-body">
					课程类别：
					<select name="courseType" style="width: 463px;margin-left: 2px;margin-top:2px;" id="courseType" title="${course.courseType}">
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
				
				<div class="control-group"  id="courseDesc" >
						课程介绍：
						<div class="controls" style="margin-left: 85px; margin-top: -20px;">
							<textarea  id="KEcourseDesc"  rows="5" name="courseDesc" cols="8" style="width:465px;height: 100px;margin-left: 5px;">${course.courseDesc}</textarea>
							<span class="help-inline"></span>
						</div>
					</div>
				<!-- <tr>
					<td><h5>知识形式:</h5></td>
					<td class="row-fluid custom basic"><select style="width: 250px;margin-top: 20px;">
							<option value="volvo">在线视频</option>
							<option value="saab">文档</option>
							<option value="opel">数据</option>
							<option value="audi">其它</option>
					</select></td>
				</tr>
				<tr>
					<td><h5>分类:</h5></td>
					<td class="row-fluid custom basic">
					<select style="width:200px;margin-top: 20px;">
							<option value="volvo">课程资料</option>
							<option value="saab">教学资源</option>
							<option value="audi">其它</option>
					</select>
					<select style="width:200px;margin-left: 20px;margin-top: 20px;">
							<option value="volvo">文学</option>
							<option value="saab">计算机</option>
							<option value="opel">数学</option>
							<option value="audi">管理学</option>
							<option value="audi">其它</option>
					</select>
					</td>
				</tr> -->
				<div style="text-align: left; margin-left: 80px; float: left; margin-top:10px;">
					<button type="submit" class="btn btn-large btn-success">保存</button></td>
				</div>
			</div>
		</div>
	</form>
</div>