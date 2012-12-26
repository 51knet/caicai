<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
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
<div style="margin-top: 100px">
	<c:forEach var="course" items="${courseMap}" varStatus="i">
		<div id="course_${i.count}" onclick="javascript:courseOnclick(this);"style="background-color:#F7F7F7; ;height:40px; line-height:40px;clear:both" >
		<h4 style="float:left;margin-left: 40px;"><span >${course.key}&nbsp;&nbsp;</span></h4>
		</div>
		<c:forEach var="fileNames" items="${course.value}">
		<div class="fileName_${i.count}" style="display: none;">
		<span>${fileNames.fileName}&nbsp;&nbsp;</span> 
		</div>
		</c:forEach>
	</c:forEach>
</div>
