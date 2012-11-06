<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher Course page.</h1>
<div style="text-align: center;">
	${sessionScope.userInfo.user["email"] }<br>
<div style="text-align: center;">
	
	<table class="table table-bordered">
		<thead><tr><th>课程标题</th><th>课程简述</th><th>发布时间</th></tr></thead>
		<tbody>
			<tr><td align="left">${course.courseName}</td>
			<td align="center">${course.courseDesc}</td>
			<td align="center">${course.courseDate}</td></tr>	
		</tbody>
	</table>
	<br>
	课程资源<hr>
	<a href='<c:url value="/admin/teacher/teacherCourse/addResourcePage?id=${course.id}"></c:url>'>添加附件</a>
	<table class="table table-bordered">
		<thead><tr><th>资源名称</th><th>上传时间</th><th>下载</th></tr></thead>
		<tbody>
			<c:forEach items="${resourceList}" var="resource">
				<tr>
					<td align="left">${resource.fileName}</td>
					<td align="center">${resource.date}</td>
					<td align="center"><a href="${resource.savePath}">下载</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

</div>

</div>