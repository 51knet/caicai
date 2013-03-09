<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

</script>
<style>
.row-fluid.custom.teacherCourse {
	text-align: left;
	margin-top: 10px;
	border:solid 0px #dcdcdc;
	color:#cccccc;
	background-image: url('<c:url value="/resources/img/default/course-topbg.png"></c:url>');
	background-position: center top;
	background-repeat: repeat-y; 
}
</style>
<div class=" row-fluid custom teacherCourse">
    <div style="float: left; width: 25%;">
	    <c:choose >
			<c:when test="${course.courseCover != null && course.courseCover != ''}">
				<img src='<c:url value="${course.courseCover}"> </c:url>'style="width: 210px;height:110px; float:left; margin-left:30px;margin-top: 20px;margin-bottom: 20px;"/>
			</c:when>
			<c:otherwise>
				 <img src='<c:url value="/resources/img/knetlogo.png"></c:url>'style="width: 210px;height: 110px; float:left; margin-left:30px;margin-top: 20px;"/>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="float: left; width: 75%;">
		<div style="margin-top: 10px; float: left; width:80%;">
			<h4><span>${course.courseName}</span></h4>
			<h4><span>${course.courseType}</span></h4>
		</div>
		<div  style="float: left; vertical-align: middle; width: 20%; text-align: center;">
			<c:if test="${course.publish==2}">
				<br>
				<a href= '<c:url value="/admin/teacher/course/edit/${course.id }/preview"></c:url>'  class="btn  btn-success" style="width: 80px;height: 20px;" target="_blank" >预览</a>
				<br>
				<a href='<c:url value="/admin/teacher/course/edit/${course.id }/publish"></c:url>'  class="btn  btn-success" style="width: 80px; height: 20px; margin-top: 15px;"  >发布</a>
			</c:if>
			<c:if test="${course.publish ==3 }">
				<br><br>
				<a href='<c:url value="/admin/teacher/course/edit/${course.id }/cancelpublish"></c:url>'  class="btn  btn-success" style="width: 80px;height:20px;"  >取消发布</a>
			</c:if>
		</div>
	</div>
</div>
					
					
			