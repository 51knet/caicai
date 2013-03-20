<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/img.js" />"></script>
<style type="text/css">
#preview{}
#showimg {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}

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
function previewImages(file){
	document.getElementById("courseCover").style.display="none";
	previewImage(file);
}
</script>
<div class="row-fluid custom round">
	<div class="row">
		<h4>修改封面</h4>
	</div>
	<div class="row1">
		<div style="width: 780px; margin-top: 30px;">
			<span > 封面预览</span>
			<div id="preview" style="margin-top:-15px; margin-left:100px;">
			<img name="showimg" id="showimg" src=""
				style="display: none;" />
			</div>
			<div id="courseCover" style="margin-top: -16px;">
				<span> <c:choose>
						<c:when test="${course.courseCover != null && course.courseCover != ''}">
							<img src='<c:url value="${course.courseCover}"> </c:url>' style="width: 260px; height: 195px; margin-left: 100px;" />
						</c:when>
						<c:otherwise>
							<img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 260px; height: 195px;" />
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</div>
		<br />
		<div style="margin-top: 2px; width: 780px;">
			<form action="<c:url value="/admin/teacher/course/edit/moidfycover"></c:url>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="courseId" value="${course.id}">
				<table>
					<tr>
						<td>上传封面</td>
						<td align="left"><input type="file" style=" margin-left: 40px;" size="50px" name="coverFile"  onChange="previewImages(this);"><span style="color:red;">${errorMsg }</span></td>
					</tr>
					<tr>
						<td style="color: red; font-size: 14px; padding-left: 98px;" colspan="2">只支持jpg、gif、bmp、png格式，建议封面宽度260px，高度195px，不大于2M</td>
					</tr>
					<tr>
						<td colspan="2"><button class="btn  btn-success" type="submit" style="margin-left: 100px;">保存</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>