<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nar {
	background-color: #ccdfa8; 
	width:100%; 
	font-size:14px;
	height: 20px; 
	padding-top: 10px;
	margin-bottom: 10px;  
	padding: 5px; 
}
.nar .content{
	margin-left: 30px;
	font-size: 15px;
}

 .cont {
	width: 92.5%;
	margin-left: 33px;
	margin-bottom: 10px;
}
.cont .top{
	font-size: 14px; 
	 background-color: #cccccc; 
	 padding: 3px;
}
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 10px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
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
<div  class="row-fluid custom round">
	<div class="row">
		<h4>课程详细</h4>
	</div>
	<div class="row1" style="margin-top: 10px;">
		<div  style=" height: 160px; width: 100%">
			<div style="width: 40%; text-align:center;  float: left;border: 0px solid #cccccc;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<a href='#'> <img src="${course.courseCover }" style="width: 160px; height: 120px;margin-top: 10px;margin-left: 70px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 160px; height: 120px;margin-top: 10px;margin-left: 70px;" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="width: 60%; float: left; height:150px;border: 0px solid #cccccc;">
				<div style="margin-left:30px; margin-top: 10px;" >
					<div style="width: 200px; font-size: 18px;color: black;" id="content">${course.courseName}</div>
					<span style="font-size: 14px;color: black;">${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</span><br/>
					<span style="font-size: 14px;color: black;"> 类别：${course.cType.typeName }</span><br/>
					<span style="font-size: 14px;color: black;">发布时间：${course.courseDate }</span>
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
			<span style="margin-left: 30px;"><b>目标人群</b></span>
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
							<table   style="width: 95%;  border: 2px solid #f2f2f2; margin-bottom: 5px;">
								<tbody>
									<tr>
										<td align="left">
											<div >
												<div  class="top"  id="course_${status.index}" onclick="javascript:courseOnclick(this);">
													<span style="margin-left: 10px;"><b>第${course.key}课时</b></span>
												</div>
												<c:forEach var="fileNames" items="${course.value}">
												<c:if test="${fileNames.fileName!=null}">
													<div  class="fileName_${status.index}"  >
														<c:if test="${fileNames.fileName != null }">
															<div id="${fileNames.id}_courseResourceShowDetail" style="width: 100%; margin-top: 5px; margin-bottom: 1px; border-bottom: 1px dotted #dcdcdc;">
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
																				<td align="left" width="60%">
																					<a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'> <span style="margin-left: 0px;">${fileNames.fileName}</span>
																					</a>
																				</td>
																				<td align="right" width="30%">
																				<a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																				<img src="<c:url  value="/resources/img/u173_normal.jpg" ></c:url> ">
																				</a>
																				</td>
																				<td>
																				<c:if test="${fileNames.resourceType.id ==2 }">
																				<a href="#myModal_${status.index}" role="button" data-toggle="modal"><i class="icon-play"></i></a>
																				 <!-- Modal -->
																					<div id="myModal_${status.index}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
																						<div class="modal-header">
																							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
																							<h3 id="myModalLabel">${fileNames.fileName}</h3>
																						</div>
																						<div class="modal-body">
																							<div id="myPlayer_${status.index}"></div>
																							<script type="text/javascript">
																						    	//TODO: fix me, the file name extension should be mp4
																							    jwplayer("myPlayer_${status.index}").setup({
																							        file: '<c:url value="${fileNames.relativePath}"></c:url>',
																							        //image: "/uploads/myPoster.jpg"
																							        //TODO: each mp4 can have a preview image
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
