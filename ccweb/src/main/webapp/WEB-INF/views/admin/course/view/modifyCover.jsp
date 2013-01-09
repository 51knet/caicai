<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.basic {
	margin-left: 4%;
	float: left;
}
</style>
<script type="text/javascript">
function viewmypic(mypic, imgfile) {
	if (imgfile.value) {
		document.getElementById("courseCover").style.display="none";
		mypic.src = imgfile.value;
		mypic.style.display = "";
		mypic.border = 1;
	}
}
</script>
<div style="margin-top: 10px;">
	<a href="#">修改封面</a>
	<hr />
	<div>
			<span style="text-align: center; margin-left: 42%; float: left;"> 封面预览 </span>
			<div  style="text-align: center; margin-left: 22%; float: left; margin-top: 10px;">
			<img name="showimg" id="showimg" style="display:none; width:300px; height:200px;"/> 
			</div>
			<div id="courseCover" >
				<span style="text-align: center; margin-left: 22%; float: left; margin-top: 10px;">
				<c:choose>
						<c:when test="${course.courseCover != null && course.courseCover != ''}">
							<img src='<c:url value="${course.courseCover}"> </c:url>' style="width: 300px; height: 200px; float: left; margin-left: 30px" />
						</c:when>
						<c:otherwise>
							<img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 300px; height: 200px; float: left; margin-left: 30px" />
						</c:otherwise>
				</c:choose></span>
			</div>
		</div>
	<form action="moidfycover" method="post" enctype="multipart/form-data">
		<table style="text-align: center;">
			<tr>
				<td>上传封面</td>
				<td><input type="file" name="coverFile" style="margin-left: 60px; width: 450px; margin-top: 10px; float: left;" onChange="viewmypic(showimg,this.form.coverFile);"></td>
			</tr>
			<tr>
				<td style="color: red; font-size: 14px; margin-left: 115px; float: left;" colspan="2">只支持jpg、gif、bmp格式，建议封面宽度300px，高度200px</td>
			</tr>
			<tr>
				<td colspan="2" style="margin-left: 280px; float: left; margin-top: 30px;"><button class="btn btn-large btn-success" type="submit">保存</button></td>
			</tr>
		</table>
	</form>
</div>