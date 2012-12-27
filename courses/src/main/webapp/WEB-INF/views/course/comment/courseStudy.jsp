<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.courseResource {

	text-align: left;
}
.container.courseResource.row {
}

</style>
<script type="text/javascript">
	function courseOnclick(obj) {
		var courseId = obj.id;
		var id = courseId.substring(courseId.indexOf('_') + 1, courseId.length); // 这里indexOf('-')和lastIndexOf('-')相等
		$(".fileName_" + id).slideToggle();
		return false;
	}
</script>

	<div align="left" style="background-color:#F7F7F7;height:40px">
		<h4 style="margin-left:40px; float: left;">课程资源</h4>
	</div>
	<div  class="row" style="margin-left:40px">
		<c:forEach var="course" items="${courseMap}" varStatus="i">
			<div id="course_${i.count}" onclick="javascript:courseOnclick(this);">
				<h5><span style="font-size: 15px;">第${course.key}课时</span></h5>
			</div>
			<c:forEach var="fileNames" items="${course.value}">
				<div class="fileName_${i.count}" >
					<div style="width: 80%; float: left;">${fileNames.fileName}</div>
					<div style="width: 20%; float: left;"><a   href='<c:url value="/teacherCourse/course/view/${fileNames.id}"></c:url>'><img src="<c:url  value="/resources/img/courseResource/u173_normal.jpg"></c:url>" ></a></div>
				</div>
			</c:forEach>
		</c:forEach>
	</div>
