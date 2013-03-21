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
 .cont {
	margin-left:90px;
	margin-bottom: 43px;
	padding-top: 20px;
}
.cont .top{
	font-size: 14px; 
	 background-color: #cccccc; 
	 padding: 3px;
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
<div class="nar">
	<h4>课程学习</h4>
</div>
<div class="cont">
		<c:choose>
			<c:when test="${resourceCount>0 }">
				<c:forEach var="course" items="${courseMap}" varStatus="status">
				<table style="width: 89%; border: 1px solid #f1f1f1;">
					<tbody>
						<tr>
							<td align="left">
								<div>
									<div class="top" id="course_${status.index}" onclick="javascript:courseOnclick(this);">
										<b>第${course.key}课时</b>
									</div>
									<div style="margin-top: 5px;">
									<c:forEach var="fileNames" items="${course.value}" >
										<c:if test="${fileNames.fileName!=null}">
											<div class="fileName_${status.index}" style="width: 100%; margin-top:5px; margin-bottom: 1px; border-bottom: 1px dotted #dcdcdc;">
												<table style="width: 94%; margin-left: 20px;">
													<tbody>
														<tr>
															<td width="4%"><c:if test="${fileNames.resourceType.id ==1 }">
																	<img src='<c:url value="/resources/resourceType/text.jpg"></c:url>' style="width: 40px; height: 40px;" />
																</c:if> <c:if test="${fileNames.resourceType.id ==2}">
																	<img src='<c:url value="/resources/resourceType/video.jpg"></c:url>' style="width: 40px; height: 40px;" />
																</c:if></td>
															<td align="left" width="60%"> <span>${fileNames.fileName}</span></td>
														</tr>
													</tbody>
												</table>
											</div>
										</c:if>
									</c:forEach>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<div>尚未添加资源</div>
			</c:otherwise>
		</c:choose>
</div>
