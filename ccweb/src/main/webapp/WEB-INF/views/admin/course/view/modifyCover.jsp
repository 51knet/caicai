<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function viewmypic(url) {
		var patn = /\.jpg$|\.jpeg$|\.gif$/i;
		if (patn.test(url.value)) {
			var showimg=document.getElementById("showimg");
			document.getElementById("courseCover").style.display = "none";
			showimg.src= "";
			showimg.src= url.value;
			showimg.style.display = "";
			mypic.border = 1;
		} else {
			alert("您选择的似乎不是图像文件。");
		}
	}
</script>
<div style="margin-top: 10px;">
	<a href="#">修改封面</a>
	<hr />
	<div style="width: 780px;margin-top: 30px;">
		<span style="margin-left: 170px;"> 封面预览 </span> 
		<img name="showimg" id="showimg" src=""
			style="display: none; margin-top: 10px;" />
		<div id="courseCover" style="margin-top: 10px;">
			<span > 
			<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<img src='<c:url value="${course.courseCover}"> </c:url>' style="width: 210px; height: 110px;margin-left:100px;" />
					</c:when>
					<c:otherwise>
						<img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 210px; height: 110px;" />
					</c:otherwise>
				</c:choose>
				</span>
		</div>
	</div>
	<br/>
	<div style="margin-top:10px;width:780px;">
	<form action="moidfycover" method="post" enctype="multipart/form-data">
		<table >
			<tr>
				<td >上传封面</td>
				<td><input type="file"  style="width: 450px; margin-left:40px;" size="50px" name="file" id="coverFile"   onChange="viewmypic(this);"></td>
			</tr>
			<tr>
				<td style="color: red;font-size: 14px; padding-left: 98px;" colspan="2">只支持jpg、gif、bmp格式，建议封面宽度210px，高度110px</td>
			</tr>
			<tr >
				<td colspan="2" ><button class="btn btn-large btn-success" type="submit" style="margin-left: 100px;">保存</button></td>
			</tr>
		</table>
	</form>
	</div>
</div>