<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>资源管理</b></a>>>
<a href='<c:url value="/admin/teacher/course/edit/${course.id}"></c:url>'><b>课程修改</b></a><hr>
<div style="text-align: center;">
	${sessionScope.userInfo.user["email"] }<br>
<div style="text-align: center;">

	<form:form action='edit'  method="post">
		<input type="hidden" name="id" value="${course.id }" />
		课程标题：<input type="text" name="courseName" value="${course.courseName}" placeholder="CourseName">
		<span class="help-block"><form:errors path="courseName"></form:errors></span>
		课程内容：<textarea name="courseDesc"   placeholder="CourseDesc" cols="5" rows="8">${course.courseDesc}</textarea>
		<span class="help-block"><form:errors path="courseDesc"></form:errors></span>
		
	
		
		<label style="clear: right;"></label>
		<button type="submit" class="btn btn-primary">OK</button>&nbsp;
		<button type="submit" class="btn">Cancel</button>
	</form:form>

</div>

</div>