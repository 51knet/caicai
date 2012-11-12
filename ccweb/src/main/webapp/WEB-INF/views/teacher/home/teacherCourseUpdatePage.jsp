<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher Course Update page.</h1>
<div style="text-align: center;">
	${sessionScope.sessionUserInfo.user["email"] }<br>
<div style="text-align: center;">

	<form:form action='updateCourseInfo'  method="post">
		<input type="hidden" name="id" value="${course.id }" />
		课程标题：<input type="text" name="courseName" value="${course.courseName}" placeholder="Title">
		<span class="help-block"><form:errors path="courseName"></form:errors></span>
		课程内容：<textarea name="courseDesc"   placeholder="CourseDesc" cols="5" rows="8">${course.courseDesc}</textarea>
		<span class="help-block"><form:errors path="courseDesc"></form:errors></span>
		
	
		
		<label style="clear: right;"></label>
		<button type="submit" class="btn btn-primary">OK</button>&nbsp;
		<button type="submit" class="btn">Cancel</button>
	</form:form>

</div>

</div>