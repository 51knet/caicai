<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>

.cont {
	margin: 0px 30px 0px 30px;
	padding:0px 20px 10px 20px;
	width: 100%;
}

.cont .top {
	font-size: 14px;
	background-color: #cccccc;
	padding: 3px;
}

.nar{
	height: 40px;	
}
.nar >h4{
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 10px;
	padding-left:20px;
	margin: 10px 30px 0px 30px;
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
				<table style="width: 87%; border: 2px solid #f1f1f1;">
					<tbody>
						<tr>
							<td align="left">
								<div>
									<div class="top" id="course_${status.index}" onclick="javascript:courseOnclick(this);">
										<b>第${course.key}课时</b>
									</div>
									<c:forEach var="fileNames" items="${course.value}" varStatus="resourceStatus">
										<c:if test="${fileNames.fileName!=null}">
											<div class="fileName_${status.index}" style="width: 100%; margin-top: 5px; margin-bottom: 1px; border-bottom: 1px dotted #dcdcdc;">
												<table style="width: 94%; margin-left: 20px;">
													<tbody>
														<tr>
															<td width="8%">
																<c:if test="${fileNames.resourceType.id ==1 }">
																	<img src='<c:url value="/resources/resourceType/text.jpg"></c:url>' style="width: 40px; height: 40px;" />
																</c:if> 
																<c:if test="${fileNames.resourceType.id ==2}">
																	<img src='<c:url value="/resources/resourceType/video.jpg"></c:url>' style="width: 40px; height: 40px;" />
																</c:if>
															</td>
															<td align="left" width="60%"><a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'> <span style="margin-left: 0px;">${fileNames.fileName}</span>
															</a></td>
															<td align="right" width="30%"><a href='<c:url  value="/course/study/view/resource/${fileNames.id}"></c:url>'><img
																	src="<c:url  value="/resources/img/default/u173_normal.jpg" ></c:url> "></a></td>
															<td>
															<c:if test="${fileNames.resourceType.id ==2 }">
																<a href="#myModal_${status.index}" role="button" data-toggle="modal"><i class="icon-play"></i></a> <!-- Modal -->
																		<div id="myModal_${status.index}" class="modal hide fade" style="width:670px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
																  <div class="modal-header">
																    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
																    <h3 id="myModalLabel">${fileNames.fileName}</h3>
																  </div>
																  <div class="modal-body">
																    <video id="myPlayer_${resourceStatus.index}" class="video-js vjs-default-skin" controls width="640" height="320" 
																	  poster="http://dummyimage.com/640x264/000000/51ff00.jpg&text=loading..." preload="auto" data-setup="{}">
																	  <source type="video/mp4" src='<c:url value="${url}${fileNames.relativePath}"></c:url>'></source>
																	</video>
	
																	<script type="text/javascript">
																		var myPlayer = _V_("myPlayer_${resourceStatus.index}");
																		$("#myModal_${status.index}").on('hidden',function(){
																			myPlayer.pause();
																		});
																	</script>
																  </div>
																		<div class="modal-footer">
																			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
																		</div>
																	</div>
																</c:if>
																</td>
														</tr>
													</tbody>
												</table>
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
			<div>尚未添加资源</div>
		</c:otherwise>
	</c:choose>
</div>
