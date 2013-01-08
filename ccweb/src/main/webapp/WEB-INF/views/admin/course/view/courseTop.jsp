<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

</script>
<style>
.row-fluid.custom.teacherCourse {
	text-align: left;
	margin-top: 10px;
	border:solid 1px #dcdcdc;
}
</style>
<div class=" row-fluid custom teacherCourse">
    <div>
	    <c:choose >
			<c:when test="${course.courseCover != null && course.courseCover != ''}">
				<img src='<c:url value="${course.courseCover}"> </c:url>'style="width: 210px;height:110px; float:left; margin-left:30px;margin-top: 20px;margin-bottom: 20px;"/>
			</c:when>
			<c:otherwise>
				 <img src='<c:url value="/resources/img/knetlogo.png"></c:url>'style="width: 210px;height: 110px; float:left; margin-left:30px;margin-top: 20px;"/>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<div style="margin-top: 10px;margin-left:60px;float: left;">
		<h4><span>${course.courseName}</span></h4>
		<h4><span>${course.courseType}</span></h4>
		</div>
		<div >
		<span style="margin-left: 86%;float: left;margin-top: -115px;"><a href= '<c:url value="/admin/teacher/course/edit/${course.id }/preview"></c:url>'  class="btn btn-large btn-success" style="width: 80px;" target="_blank" >预览</a></span>
		<br/>
		<span style="margin-left: 86%;float: left;margin-top: -70px;">
			<c:if test="${course.publish ==1 }">
					<a href='<c:url value="/admin/teacher/course/edit/${course.id }/publish"></c:url>'  class="btn btn-large btn-success" style="width: 80px;"  >发布</a>
			</c:if>
			<c:if test="${course.publish ==2 }">
					<a href='<c:url value="/admin/teacher/course/edit/${course.id }/cancelpublish"></c:url>'  class="btn btn-large btn-success" style="width: 80px;"  >取消发布</a>
			</c:if>
		</span>
		</div>
	</div>
</div>
					
					
			