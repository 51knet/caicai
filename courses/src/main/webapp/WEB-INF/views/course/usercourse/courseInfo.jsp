<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nar >h4{
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 5px;
	padding-left:88px;
}
.nar_course.desc{
	margin-left: 78px;
	padding: 10px;
}
</style>
<div style="margin-bottom: 200px;">
		<div  class="nar">
			<h4>课程介绍</h4>
		</div>
			<div class="nar_course desc">
				<c:choose>
				<c:when test="${teacherCourse.courseDesc!=null}">
					${teacherCourse.courseDesc}
				</c:when>
				
				<c:otherwise>
					<div>尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>
			<div  class="nar">
			<h4>目标人群</h4>
		</div>
			<div class="nar_course desc">
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
			<h4>课程看点</h4>
		</div>
			<div class="nar_course desc">
			<c:choose>
				<c:when test="${course.courseCharacter!=null }">
					${course.courseCharacter}
				</c:when>
				<c:otherwise>
					<div style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>
</div>


