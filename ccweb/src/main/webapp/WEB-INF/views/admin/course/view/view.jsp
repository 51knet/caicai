<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function courseOnclick(obj) {
		var courseId = obj.id;
		var id = courseId.substring(courseId.indexOf('_') + 1, courseId.length); // 这里indexOf('-')和lastIndexOf('-')相等
		$(".fileName_" + id).slideToggle();
		return false;
	}
</script>
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
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
.nar {
	background-color: #ccdfa8; 
	width:100%; 
	margin-bottom: 10px;  
	padding: 5px; 
}
.nar .content{
	margin-left: 30px;
	font-size: 15px;
}
 .cont {
	width: 93%;
	margin-left: 30px;
	margin-bottom: 10px;
}
.cont .top{
	font-size: 14px; 
	 background-color: #cccccc; 
	 padding: 3px;
}
</style>

<div  class="row-fluid custom round" >
	<div class="row" >
		<h4>课程管理>课程详细</h4>
	</div>
	<div class="row1" style="text-align: left;">
			<div  style="margin-bottom: 10px; height: 180px; width: 100%">
				<div style="width: 40%; height:150px; text-align:center;  float: left;border: 0px solid #cccccc;">
					<c:choose>
						<c:when test="${course.courseCover != null && course.courseCover != ''}">
							<a href='#'><img src='<c:url value="${course.courseCover }" ></c:url>' style="width: 220px; height: 165px;" />
							</a>
						</c:when>
						<c:otherwise>
							<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 220px; height: 165px;" /></a>
						</c:otherwise>
					</c:choose>
				</div>
				<div style="width: 60%; float: left; height:150px;border: 0px solid #cccccc; font-size: 13px;">
					<div style="width: 100%; float: left; width: ">
						<h4 style="width: 400px;" id="content">${course.courseName}</h4>
						<h5><span style="margin-right: 6px;">${course.user.name} </span></h5>
						<h5>类别：${course.cType.typeName }</h5>
						<h5>发布时间：${course.courseDate }</h5>
						<c:if test="${course.publish==2}">
								<a href= '<c:url value="/admin/course/edit/${course.id }/preview"></c:url>'  class="btn  btn-success"  target="_blank" >预览</a>
								<a href='<c:url value="/admin/course/edit/${course.id }/publish"></c:url>'  class="btn  btn-success"  >发布</a>
						</c:if>
						<c:if test="${course.publish==3}">
								<c:if test="${course.status==1 }">
									<a href= '<c:url value="/admin/course/edit/${course.id }/pubcourses"></c:url>'  class="btn  btn-success" >发布到知识超市</a>
								</c:if>	
								<a href='<c:url value="/admin/course/edit/${course.id }/cancelpublish"></c:url>'  class="btn btn-success"  >取消发布</a>	
						</c:if>
							<a href='<c:url value="/admin/course/edit/${course.id}/modifycourse"></c:url>'  class="btn  btn-success">修改</a>	
					</div>
				</div>
			</div>
			<div  class="nar">
				<span class="content"><b>课程介绍</b></span>
			</div>
			<div class="cont">
					<c:choose>
					<c:when test="${course.courseDesc!=null}">
						${course.courseDesc}
					</c:when>
					<c:otherwise>
						尚未添加课程介绍
					</c:otherwise>
				</c:choose>
			</div>
			
			<div  class="nar">
				<span class="content"><b>目标人群</b></span>
			</div>
			<div class="cont">
				<c:choose>
					<c:when test="${course.targetPerson!=null}">
						${course.targetPerson}
					</c:when>
					<c:otherwise>
						尚未添加目标人群
					</c:otherwise>
				</c:choose>
			</div>
			
			<div  class="nar">
				<span class="content"><b>课程看点</b></span>
			</div>
			<div class="cont">
				<c:choose>
					<c:when test="${course.courseCharacter!=null }">
						${course.courseCharacter}
					</c:when>
					<c:otherwise>
						尚未添加课程看点
					</c:otherwise>
				</c:choose>
			</div>
			
			<div  class="nar">
				<span class="content"><b>课程资料</b></span>
			</div>
			<div class="cont">
				<c:choose>
					<c:when test="${resourceCount>0 }">
						<c:forEach var="course" items="${courseMap}" varStatus="status">
								<table   style="width: 95%;  border: 2px solid #f1f1f1; margin-bottom: 5px;">
									<tbody>
										<tr>
											<td align="left">
												<div >
													<div class="top" id="course_${status.index}" onclick="javascript:courseOnclick(this);"><b>第${course.key}课时</b></div>
													<c:forEach var="fileNames" items="${course.value}" varStatus="resourceStatus">
														<c:if test="${fileNames.fileName!=null}">
														<div  class="fileName_${status.index}" >
															<c:if test="${fileNames.fileName != null }">
																<div style="width: 87%; float: left; margin-left: 5px; margin-top: 3px;">${fileNames.fileName}</div>
																<div style="width:10%; float: left; text-align: center;">
																	<a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																		<img src="<c:url value="/resources/img/u173_normal.jpg" ></c:url> ">
																	</a>
																	<a href="#myModal_${status.index}" role="button" data-toggle="modal"><i class="icon-play"></i></a>
															<!-- Modal -->
															<div id="myModal_${status.index}" class="modal hide fade" style="width:670px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
															  <div class="modal-header">
															    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
															    <h3 id="myModalLabel">${fileNames.fileName}</h3>
															  </div>
															  <div class="modal-body">
															    <video id="myPlayer_${resourceStatus.index}" class="video-js vjs-default-skin" controls width="640" height="320" 
																  poster="http://dummyimage.com/640x264/000000/51ff00.jpg&text=loading..." preload="auto" data-setup="{}">
																  <source type="video/mp4" src='<c:url value="${fileNames.relativePath}"></c:url>'></source>
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
																</div>
															</c:if>
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
						尚未添加资源
					</c:otherwise>
				</c:choose>
			</div>
	</div>
</div>


