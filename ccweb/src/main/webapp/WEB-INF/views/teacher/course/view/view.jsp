<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
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
	margin: 0px 5px;
}
.nar {
	background-color: #f3f3f3; 
	width:100%; 
	margin-bottom: 10px; 
	 height: 40px; 
	 padding: 1px; 
}
 .cont {
	width: 100%;
	margin-left: 30px;
	margin-bottom: 10px;
}
</style>

<div  class="row-fluid custom round">
	<div class="row">
		<h5>课程详细</h5>
	</div>
	<div class="row" style="margin-top: 10px;">
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
			<div style="width: 60%; float: left; height:150px;border: 0px solid #cccccc; font-size: 13px;">
				<div style=" float: left; margin-left: 80px; ">
					<h5 style="width: 300px;" id="content">${course.courseName}</h5>
					<h5>${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</h5>
					<h5>类别：${course.courseType }</h5>
					<h5>发布时间：${course.courseDate }</h5>
				<!-- 	学员（0）&nbsp;&nbsp;评论（0）&nbsp;&nbsp; -->
				</div>
			</div>
		</div>
		<div  class="nar">
			<h4 style="margin-left: 30px; ">课程介绍</h4>
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
			<h4 style="margin-left: 30px; ">目标人群</h4>
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
			<h4 style="margin-left: 30px; ">课程看点</h4>
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
			<h4 style="margin-left: 30px; ">课程资料</h4>
		</div>
		<div class="cont">
			<c:choose>
				<c:when test="${resourceCount>0 }">
					<c:forEach var="course" items="${courseMap}" varStatus="i">
							<table   style="width: 95%;  border: 2px solid #f2f2f2; margin-bottom: 5px;">
								<tbody>
									<tr>
										<td align="left">
											<div >
												<div style="font-size: 16px;  background-color: #f2f2f2; padding: 5px;" id="course_${i.count}" onclick="javascript:courseOnclick(this);"><b>第${course.key}课时</b></div>
												<c:forEach var="fileNames" items="${course.value}">
													<div  class="fileName_${i.count}"  style="background-color:#ffffff;">
														<c:if test="${fileNames.fileName != null }">
															<div style="width: 87%; float: left; margin-left: 5px; margin-top: 3px;">${fileNames.fileName}</div>
															<div style="width:10%; float: left; text-align: center;">
																<a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'>
																	<img src="<c:url  value="/resources/img/u173_normal.jpg" ></c:url> ">
																</a>
															</div>
														</c:if>
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
					尚未添加资源
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
