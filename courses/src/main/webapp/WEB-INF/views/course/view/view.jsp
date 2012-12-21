<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar {
	margin-bottom: 0px;
}
/* CUSTOMIZE THE NAVBAR
-------------------------------------------------- */
.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}


/* COURSE CONTENT
-------------------------------------------------- */

.container.course {
	width: 1024px;
	max-width:1024px;
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}

.container.course.detail {
	width: 1024px;
	height: 110px;
}

.container.course.detail.desc{
	width: 950px;
	margin-left: 50px;
	margin-bottom: 10px;
}
</style>



<div class="container course" style=" margin-bottom: 20px;margin-top: 55px;">
	<!-- <div class="container course detail" style="background-color: #f7f7f7; height: 60px;margin-bottom: 10px; vertical-align: middle;">
		<h2>&nbsp;课程详细</h2>
	</div> -->
	<div class="container course row">
		<div class="container course detail" style="margin-bottom: 20px; margin-top:10px; height: 180px;">
			<!-- <table class="table table-bordered" style="width: 100%; height: 100%;" cellpadding="5">
				<tr>
					<td valign="bottom" width="22%" align="center">
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="http://localhost:8080/ccweb/${course.courseCover }"></c:url>' style="width: 200px; height: 100px;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 200px; height: 100px;" />
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>课程名称：${course.courseName}<br>课程类别：${course.courseType }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }
					</td>
					<td width="35%" align="left">
						<div style="width: 100%;height: 100%;">
							<div style="float: left; height: 100px; ">教师名称：${course.teacher.user.name } <br>所在学校：${course.teacher.college }</div>
						</div>
					</td>
				</tr>
			</table> -->
			<div style="width: 40%; height:150px; text-align:center;  float: left;border: 0px solid #cccccc;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="http://localhost:8080/ccweb/${course.courseCover }"></c:url>' style="width: 240px; height: 120px;margin-top: 10px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 240px; height: 120px;margin-top: 10px;" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="width: 60%; float: left; height:150px;border: 0px solid #cccccc; font-size: 13px;">
				<h4>${course.courseName}</h4><h4>${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</h4>
				<h5>类别：${course.courseType }&nbsp;&nbsp;&nbsp;&nbsp;课程评分：<span style="color: red;">7</span>分</h5><h5>发布时间：${course.courseDate }</h5>
				学员（20）&nbsp;&nbsp;评论（30）&nbsp;&nbsp;<a href='#' class="btn  btn-success">点击学习</a>
			</div>
		</div>
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; height: 40px;">
			<h4 style="margin-left: 50px;">课程介绍</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程课程
			</div>
		</div>
	</div>
</div>



