<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/img.js" />"></script>
<style type="text/css">
#preview{}
#showimg {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
<script type="text/javascript">
function previewImages(file){
	previewImage(file);
}
</script>
<div style="margin-top: 10px;">
	<a href="#">修改封面</a>
	<hr />
	<div style="width: 780px; margin-top: 30px;">
		<span > 封面预览 </span>
		<div id="preview" style="margin-top:-15px; margin-left:100px;">
		<img name="showimg" id="showimg" src=""
			style="display: none;" />
		</div>
		<div id="courseCover" style="margin-top: -16px;">
			<span> <c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<img src='<c:url value="${course.courseCover}"> </c:url>' style="width: 210px; height: 110px; margin-left: 100px;" />
					</c:when>
					<c:otherwise>
						<img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 210px; height: 110px;" />
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
					<td><input type="file" style="width: 450px; margin-left: 40px;" size="50px" name="coverFile"  onChange="previewImages(this);"></td>
				</tr>
				<tr>
					<td style="color: red; font-size: 14px; padding-left: 98px;" colspan="2">只支持jpg、gif、bmp格式，建议封面宽度210px，高度110px</td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn btn-large btn-success" type="submit" style="margin-left: 100px;">保存</button></td>
				</tr>
			</table>
		</form>
	</div>
</div>