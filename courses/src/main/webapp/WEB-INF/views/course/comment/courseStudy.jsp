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
<div  align="left" style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 10px;">
    	<h4 style="margin-left: 40px; float: left;">课程学习</h4>
</div>
<div   style="margin-left:38px; margin-top: 10px; margin-bottom: 10px;">
		<c:choose>
			<c:when test="${resourceCount>0 }">
				<c:forEach var="course" items="${courseMap}" varStatus="i">
						<table   style="width: 95%;  border: 2px solid #f1f1f1; margin-bottom: 5px;">
							<tbody>
								<tr>
									<td align="left">
										<div id="course_${i.count}" onclick="javascript:courseOnclick(this);">
											<div style="font-size: 16px;  background-color: #f7f7f7; padding: 5px;"><b>第${course.key}课时</b></div>
											<c:forEach var="fileNames" items="${course.value}">
												<div class="fileName_${i.count}"  >
													<div style="width: 87%; float: left; margin-left: 5px; margin-top: 3px;">${fileNames.fileName}</div>
													<div style="width:10%; float: left; text-align: center;"><a   href='<c:url value="/teacherCourse/course/view/${fileNames.id}"></c:url>'><img src="<c:url  value="/resources/img/courseResource/u173_normal.jpg"></c:url>" ></a></div>
												</div>
											</c:forEach>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
			</c:when>
			<c:otherwise>
				<h5 style="padding: 5px;">尚未添加资源</h5>
			</c:otherwise>
		</c:choose>
</div>
