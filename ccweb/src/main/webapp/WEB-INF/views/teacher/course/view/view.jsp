<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nar {
	background-color:#ccdfa8; 
	width:100%; 
	font-size:15px;
	height: 20px; 
	margin-bottom:10px;
	padding-top: 10px;
}
 .cont {
	width: 100%;
	margin-left: 30px;
	margin-bottom: 10px;
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
		<div  style=" height: 180px; width: 100%">
			<div style="width: 40%; height:150px; text-align:center;  float: left;border: 0px solid #cccccc;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<a href='#'> <img src="/ccweb/${course.courseCover }" style="width: 240px; height: 120px;margin-top: 10px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 240px; height: 120px;margin-top: 10px;" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="width: 60%; float: left; height:150px;border: 0px solid #cccccc;">
				<div style="margin-left:60px;" >
				<br/>
					<span style="width: 300px; margin-top:60px;font-size: 20px;color: black;" id="content">${course.courseName}</span><br/>
					<span style="font-size: 14px;color: black;">${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</span><br/>
					<span style="font-size: 14px;color: black;"> 类别：${course.courseType }</span><br/>
					<span style="font-size: 14px;color: black;">发布时间：${course.courseDate }</span>
				<!-- 	学员（0）&nbsp;&nbsp;评论（0）&nbsp;&nbsp; -->
				</div>
			</div>
		</div>
		<div  class="nar">
			<span style="margin-left: 30px;margin-top: -15px;" ><b>课程简介</b></span>
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
			<span style="margin-left: 30px; "><b>目标人群</b></span>
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
			<span style="margin-left: 30px; "><b>课程看点</b></span>
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
			<span style="margin-left: 30px; "><b>课程资料</b></span>
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
												<div  style="font-size: 12px; color:#FFFFFF; background-color: #8E8E8E;"  id="course_${status.count}" onclick="javascript:courseOnclick(this);"><span style="margin-left: 10px;">第${course.key}课时</span>
												<img  style="margin-left: 550px;" src="<c:url  value="/resources/img/knet-05.png" ></c:url> ">
												</div>
												<c:forEach var="fileNames" items="${course.value}">
												<c:if test="${fileNames.fileName!=null}">
													<div  class="fileName_${status.count}"  style="background-color:#ffffff;">
														<c:if test="${fileNames.fileName != null }">
															<div style="width: 87%; float: left; margin-left: 10px; margin-top: 3px;">${fileNames.fileName}</div>
															<div style="width:10%; float: left; text-align: center;">
																<a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																	<img src="<c:url  value="/resources/img/u173_normal.jpg" ></c:url> ">
																</a>
																<a href="#myModal_${status.count}" role="button" data-toggle="modal"><i class="icon-play"></i></a>
																<!-- Modal -->
																<div id="myModal_${status.count}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
																  <div class="modal-header">
																    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
																    <h3 id="myModalLabel">Modal header</h3>
																  </div>
																  <div class="modal-body">
																    <div id="myPlayer_${status.count}"></div>
																    <script type="text/javascript">
																    	//TODO: fix me, the file name extension should be mp4
																	    jwplayer("myPlayer_${status.count}").setup({
																	        file: '<c:url value="/course/resource/download/${fileNames.id}"></c:url>',
																	        //image: "/uploads/myPoster.jpg"
																	        //TODO: each mp4 can have a preview image
																	    });
																	</script>
																  </div>
																  <div class="modal-footer">
																    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
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
