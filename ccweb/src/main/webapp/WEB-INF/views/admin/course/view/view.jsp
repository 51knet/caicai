<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>课程管理</b></a>>>
<a href='<c:url value="/admin/teacher/course/view/${course.id}"></c:url>'><b>课程详细</b></a><hr>
<div style="text-align: center;">

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
	
	<table class="table table-bordered">
		<tbody>
			<tr><td colspan="3"><a href='<c:url value="/admin/teacher/${course.id}/resource/new"></c:url>' class="btn">添加附件</a></td></tr>
			<tr><td>资源名称</td><td>上传时间</td><td>下载</td></tr>
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