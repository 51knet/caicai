<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nar {
	background-color: #ccdfa8; 
	width:100%; 
	font-size:14px;
	height: 20px; 
	padding-top: 10px;
	margin-bottom: 10px;  
	padding: 5px; 
}
.nar .content{
	margin-left: 30px;
	font-size: 15px;
}
.course.content{
	margin-left:35px;
	margin-top: 5px;
}
</style>
		<div  class="nar">
			<span class="content"><b>课程介绍</b></span>
		</div>
			<div class="course content">
				<c:choose>
				<c:when test="${teacherCourse.courseDesc!=null}">
					${teacherCourse.courseDesc}
				</c:when>
				<c:otherwise>
					<div  style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>
			<div  class="nar">
			<span class="content"><b>目标人群</b></span>
		</div>
			<div class="course content">
				<c:choose>
				<c:when test="${course.targetPerson!=null}">
					${course.targetPerson}
				</c:when>
				<c:otherwise>
					<div  style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>
		<div  class="nar">
			<span class="content"><b>课程看点</b></span>
		</div>
			<div class="course content">
			<c:choose>
				<c:when test="${course.courseCharacter!=null }">
					${course.courseCharacter}
				</c:when>
				<c:otherwise>
					<div style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>



