<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
.fileName{}
</style>
<script type="text/javascript">
function courseOnclick(obj) {
	  var courseId = obj.id;
	  var id = courseId.substring(courseId.indexOf('_')+1, courseId.length); // 这里indexOf('-')和lastIndexOf('-')相等
		$(".fileName_" + id).slideToggle();
	  return false;
}
</script>
<div class="row-fluid custom round">
	<div class="row">
		<h5>课程资料</h5>
	</div>
	<div class="row">
		<div>
		<div class="table">
		<c:forEach var="course" items="${courseMap}" varStatus="i">
			<div id="course_${i.count}" onclick="javascript:courseOnclick(this);">
			<h4><span style="bordercolor=''#F0F0F0',bgcolor='CCCCCC'">${course.key}&nbsp;&nbsp;</span></h4>
			</div>
			<c:forEach var="fileNames" items="${course.value}">
			<div class="fileName_${i.count}" style="display: none;">
			<span>${fileNames.fileName}&nbsp;&nbsp;</span> 
			</div>
			</c:forEach>
		</c:forEach>
		</div>
		</div>
	</div>
</div>