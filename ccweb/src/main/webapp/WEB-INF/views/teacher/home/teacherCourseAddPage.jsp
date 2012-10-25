<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher course page.</h1>
<div style="text-align: center;">
	
	<div style="text-align: center;">
	
		<form:form action='addCourseInfo' method="post">
			课程标题：<input type="text" name="courseName" placeholder="CourseName">
			<span class="help-block"><form:errors path="courseName"></form:errors></span>
			课程描述：<textarea name="courseDesc" placeholder="CourseDesc" cols="5" rows="8"></textarea>
			<span class="help-block"><form:errors path="CourseDesc"></form:errors></span>
			<label style="clear: right;"></label>
			<button type="submit" class="btn">OK</button>
	
		</form:form>
	
	</div>

</div>