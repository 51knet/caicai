<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course {
width: 990px;
max-width:990px;
text-align: left;
}
.container.course .row{
 margin-left: 0px;
}

.container.course.row .span4 {
width: 300px;
margin-top:20px;
margin-bottom:-5px;
margin-left:20px;
-webkit-box-shadow: #999 0px 1px 2px 0px;
box-shadow: #999 0px 1px 2px 0px;
border-top-width: 1px;
border-top-style: solid;
border-top-color: #EEE;
background: #F7F7F7;
}
</style>
<div class="container course" style="margin-bottom: 20px;">
	<div style="width:990px; text-align: center;"  class="container course row" >
	<div   style="background-color:#F7F7F7;height:40px; clear:both"><h4 style=" float:left; margin-left: 40px;">课程计划</h4></div>
		<c:forEach items="${teacherCourseList}" var="course">
			<div class="container course row span4" align="left"  >
				<div style="padding:5px;">
					<c:choose>
						<c:when test="${course.courseCover != null && course.courseCover != ''}">
							<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${course.courseCover }"></c:url>' style="width: 300px; height: 100px;" />
							</a>
						</c:when>
						<c:otherwise>
							<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 300px; height: 100px;" />
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div  style="padding: 5px;" id="contentlimit">${course.courseName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${course.courseType }<br>发布时间：${course.courseDate }</div>
			</div>
	</c:forEach>
	</div>
</div>