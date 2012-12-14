<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function checkPicture(obj){
	var flag = false;
	
	var fileValue = obj.coverFile.value;
	var temp = fileValue.substr(fileValue.indexOf('.'),fileValue.length);
	if(".gif"==temp || ".jpg"==temp || ".bmp"==temp){
		
		flag=true;
	}else{
		alert("只支持gif、jpg、bmp格式的图片！！");
		flag=false;
	}
	
	return flag;
}
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
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<div  class="row-fluid custom round">
	<div class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>课程管理</b></a>>>
		<a href='<c:url value="/admin/teacher/course/new"></c:url>'><b>课程添加</b></a><hr>
		<div style="text-align: center;">
			<div style="text-align:center;">
				<div style="width:560px; text-align:left;" >
					<form:form action="create" method="post" enctype="multipart/form-data" id="course_info_form" onsubmit="return checkPicture(this)">  
						<div class="modal-body">
							上传封面：<input type="file" name="coverFile"  />&nbsp;&nbsp;<span style="color:red;font-size:14px;">只支持jpg、gif、bmp格式</span>
						</div>
					
						<div class="modal-body" id="courseName">
							课程标题：<input type="text" id="names" name="courseName"  placeholder="课程标题">
							<span class="help-inline"><form:errors path="courseName"></form:errors></span>
						</div>
						
						<div class="modal-body" id="course">
							课程类别：<select name="courseType">
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
						</div>
						
						<div  class="modal-body" id="courseDesc">
							课程描述：<textarea name="courseDesc" id="descs" placeholder="课程描述" cols="5" rows="8" style="width:380px;"></textarea>
							<span class="help-inline"><form:errors path="courseDesc"></form:errors></span>
						</div>
						<div class="modal-body" style="text-align: center;">
							<button type="submit" class="btn btn-primary">添加</button>&nbsp;&nbsp;
							<button type="reset" class="btn">取消</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>