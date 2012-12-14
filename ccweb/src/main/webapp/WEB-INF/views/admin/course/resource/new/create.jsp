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
		<a href='<c:url value="/admin/teacher/course/view/${id}"></c:url>'><b>课程详细</b></a>>><a href='<c:url value="/admin/teacher/${id}/resource/new"></c:url>' ><b>添加附件</b></a><hr>
		<div style="text-align: center;">
			<div style="text-align:center;">
				<div style="width:560px; text-align:left;">
					<form:form action="create" method="post" enctype="multipart/form-data">  
						<input type="hidden" value="${id }" name="course_id" />
						<div class="modal-body">
							上传资源：<input type="file" name="file" />&nbsp;&nbsp;<!-- <input  type="button" value="添加" onclick="addFile()"/>&nbsp;<span style="color:red;font-size:14px;">单次上传不大于200M</span> -->
						</div>
							<div class="modal-body" id="resourceOrder">
							资源课时：<select name="resourceOrder">
										<option selected value="1">第一课</option>
										<option value="2">第二课</option>
										<option value="3">第三课</option>
										<option value="4">第四课</option>
										<option value="5">第五课</option>
										<option value="6">第六课</option>
										<option value="7">第七课</option>
										<option value="8">第八课</option>
										<option value="9">第九课</option>
								   </select>
						</div>
						
						<div class="modal-body">
							资源描述：<input type="text" name="resourceDesc" />&nbsp;&nbsp;<!-- <input  type="button" value="添加" onclick="addFile()"/>&nbsp;<span style="color:red;font-size:14px;">单次上传不大于200M</span> -->
						</div>
						<div id="container" style="margin-left:70px;"></div>
						<div class="modal-body" style="text-align: left;">
							<button type="submit" class="btn btn-primary">添加</button>&nbsp;&nbsp;
							<button type="reset" class="btn">取消</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>