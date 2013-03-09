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
<div style="margin-top: 10px;">
	<a href="#">试看视频</a>
	<hr />
	<form action="">
		<table style="text-align: center;">
			<tr>
				<td style="text-align: center; margin-left: 42%; float: left;" colspan="2">视频预览</td>
			</tr>
			<tr>
				<td style="text-align: center; margin-left: 22%; float: left; margin-top:10px;" colspan="2"><c:choose>
						<c:when test="">
							<img src='<c:url value=""> </c:url>' style="width: 100px; height: 100px; float: left; margin-left: 30px" />
						</c:when>
						<c:otherwise>
							<img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 300px; height: 200px; float: left; margin-left: 30px" />
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td style="margin-top: 5%; float: left; margin-left: 8%;" colspan="2">添加/改变视频</td>
			</tr>
			<tr>
				<td><input type="file" style="margin-left:12%;width:500px;margin-top: 10px;"></td>
				<td style="margin-left:150px;float: left;"><button class="btn  btn-success">上传</button></td>
			</tr>
			<tr>
				<td colspan="2" style="margin-left: 8%;float: left;margin-top: 20px;">限定图片mp4,avi,限定大小在100M</td>
			</tr>
			<tr>
				<td colspan="2" style="margin-left: 8%;float: left;margin-top: 40px;"><button class="btn  btn-success" type="submit">保存</button></td>
			</tr>
		</table>
	</form>
</div>