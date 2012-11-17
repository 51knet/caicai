<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--
	添加多个上传文件的file
//-->

 function addFile(){
 	
 	var cont = document.getElementById("container");
 	var file = document.createElement("input");
 	file.type="file";
 	file.name="file";
 	var btn = document.createElement("input");
 	btn.type="button";
 	btn.value="删除";
 	var br = document.createElement("br");
 	cont.appendChild(file);
 	cont.appendChild(btn);
 	cont.appendChild(br);
 	
 	btn.onclick=function(){
 		cont.removeChild(file);
 		cont.removeChild(btn);
 		cont.removeChild(br);
 	}
 }
 
 function showResourceTypeAdd(){
	 var resourceType = document.getElementById("addResourType");
	 resourceType.style.display="block";
 }
</script>
<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>资源管理</b></a>>>
<a href='<c:url value="/admin/teacher/course/view/${id}"></c:url>'><b>课程详细</b></a>>><a href='<c:url value="/admin/teacher/${id}/resource/new"></c:url>' ><b>添加附件</b></a><hr>
<div style="text-align: center;">
	
<div style="text-align:center;">
<div style="width:560px; text-align:left;">
	<form:form action="create" method="post" enctype="multipart/form-data">  
		
		<input type="hidden" value="${id }" name="course_id" />
		<br>
		上传资源：<input type="file" name="file" />&nbsp;&nbsp;<input  type="button" value="添加" onclick="addFile()"/>&nbsp;<span style="color:red;font-size:14px;">单次上传不大于200M</span>
		
		<br>
		<div id="container" style="margin-left:70px;"></div>
		<br>
		<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
		<button type="reset" class="btn">Cancel</button>
	</form:form>
</div>
 
</div>

</div>