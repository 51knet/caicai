<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course {
	width: 815px;
	max-width:815px;
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}

.container.course.detail {
	width: 805px;
}

.container.course.detail.desc{
	width: 815px;
	margin-left: 50px;
	margin-bottom: 10px;
}
</style>



<div class="container course" style=" margin-bottom: 20px;margin-top: 10px;">
	<div class="container course row">
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程介绍</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				<c:choose>
				<c:when test="${teacherCourse.courseDesc!=null}">
					${teacherCourse.courseDesc}
				</c:when>
				<c:otherwise>
					尚未添加课程介绍
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">目标人群</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				<c:choose>
				<c:when test="${course.targetPerson!=null}">
					${course.targetPerson}
				</c:when>
				<c:otherwise>
					尚未添加目标人群
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程看点</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
			<c:choose>
				<c:when test="${course.courseCharacter!=null }">
					${course.courseCharacter}
				</c:when>
				<c:otherwise>
					尚未添加课程看点
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</div>



