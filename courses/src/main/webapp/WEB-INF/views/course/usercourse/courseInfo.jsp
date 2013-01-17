<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
		<div  style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 20px;">
			<h4 style="margin-left: 40px; float: left;">课程介绍</h4>
		</div>
			<div style="margin-left:40px; margin-top: 5px;">
				<c:choose>
				<c:when test="${teacherCourse.courseDesc!=null}">
					${teacherCourse.courseDesc}
				</c:when>
				<c:otherwise>
					<div  style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>
		<div  style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 20px;">
			<h4 style="margin-left: 40px; float: left;">目标人群</h4>
		</div>
			<div style="margin-left:40px; margin-top: 5px;">
				<c:choose>
				<c:when test="${course.targetPerson!=null}">
					${course.targetPerson}
				</c:when>
				<c:otherwise>
					<div  style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>
		<div  style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 20px;">
			<h4 style="margin-left: 40px; float: left;">课程看点</h4>
		</div>
			<div style="margin-left:40px; margin-top: 5px;">
			<c:choose>
				<c:when test="${course.courseCharacter!=null }">
					${course.courseCharacter}
				</c:when>
				<c:otherwise>
					<div style="padding: 3px;">尚未添加资源</div>
				</c:otherwise>
			</c:choose>
			</div>



