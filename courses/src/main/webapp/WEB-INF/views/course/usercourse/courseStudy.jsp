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
<div align="left" style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 15px;">
    	<h4 style="margin-left: 40px; float: left;">课程学习</h4>
</div>
<div style="margin-left:38px; margin-top: 10px;">
		<c:choose>
			<c:when test="${resourceCount>0 }">
				<c:forEach var="course" items="${courseMap}" varStatus="i">
						<table   style="width: 95%;  border: 2px solid #f1f1f1; ">
							<tbody>
								<tr>
									<td align="left">
										<div >
											<div style="font-size: 16px;  background-color: #f7f7f7; padding: 5px;" id="course_${i.count}" onclick="javascript:courseOnclick(this);"><b>第${course.key}课时</b></div>
												<c:forEach var="fileNames" items="${course.value}" varStatus="resourceStatus">
												<c:if test="${fileNames.fileName!=null}">
												<div class="fileName_${i.count}" >
													<div style="width: 87%; float: left; margin-left: 5px; margin-top: 3px;">${fileNames.fileName}</div>
													<div style="width:10%; float: left; text-align: center;"><a href='<c:url  value="/course/study/view/resource/${fileNames.id}"></c:url>'><img src="<c:url  value="/resources/img/courseResource/u173_normal.jpg" ></c:url> "></a>
													<a href="#myModal_${resourceStatus.index}" role="button" data-toggle="modal"><i class="icon-play"></i></a> <!-- Modal -->
																				<div id="myModal_${resourceStatus.index}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
																					<div class="modal-header">
																						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
																						<h3 id="myModalLabel">${fileNames.fileName}</h3>
																					</div>
																					<div class="modal-body">
																						<div id="myPlayer_${resourceStatus.index}"></div>
																						<script type="text/javascript">
																    	//TODO: fix me, the file name extension should be mp4
																	    jwplayer("myPlayer_${resourceStatus.index}").setup({
																	        file: '<c:url value="/ccweb${fileNames.relativePath}"></c:url>',
																	        //image: "/uploads/myPoster.jpg"
																	        //TODO: each mp4 can have a preview image
																	    });
																	</script>

																					</div>
																					<div class="modal-footer">
																						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
																					</div>
																				</div>
													</div>
													
												</div>
												</c:if>
												</c:forEach>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
			</c:when>
			<c:otherwise>
				<div  style="padding: 5px;">尚未添加资源</div>
			</c:otherwise>
		</c:choose>
</div>
