<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher Anno page.</h1>
<div style="text-align: center;">
	${sessionScope.userInfo.user["email"] }<br>
<div style="text-align: center;">
	
	<table width="500" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td>课程标题</td>
			<td>课程简述</td>
			<td>发布时间</td>
		
		</tr>
	
			<tr><td align="left">${course.courseName}</td>
			<td align="center">${course.courseDesc}</td>
			<td align="center">${course.courseDate}</td></tr>
			
		
	</table>
	<br>
	课程资源<hr>
	<a href='<c:url value="/admin/teacher/teacherCourse/addResourcePage?id=${course.id}"></c:url>'>添加附件</a>
	<table width="500" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td>资源名称</td>
			<td>资源上传时间</td>
			<td>下载</td>
			
		</tr>
		<c:forEach items="${resourceList}" var="resource">
			<tr><td align="left">${resource.fileName}</td>
			<td align="center">${resource.date}</td>
			<td align="center"><a href="${resource.savePath}">下载</a></td>
			</tr>
		</c:forEach>
	</table>
	

</div>

</div>