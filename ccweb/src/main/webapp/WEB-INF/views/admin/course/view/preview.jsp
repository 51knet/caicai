<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course {
	width: 990px;
	max-width:990px;
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}

.container.course.detail {
	width: 990px;
}

.container.course.detail.desc{
	width: 950px;
	margin-left: 50px;
	margin-bottom: 10px;
}
</style>



<div class="container course" style=" margin-bottom: 20px;margin-top: 10px;">
	<div class="container course row">
		<div class="container course detail" style="margin-bottom: 20px; margin-top:10px; height: 180px;">
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
				<h4>${course.courseName}</h4>
				<h4>${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</h4>
				<h5>类别：${course.courseType }&nbsp;&nbsp;&nbsp;&nbsp;课程评分：<span style="color: red;">0</span>分</h5>
				<h5>发布时间：${course.courseDate }</h5>
				学员（0）&nbsp;&nbsp;评论（0）&nbsp;&nbsp;
				<a href='#' class="btn  btn-success">点击学习</a>
			</div>
		</div>
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程介绍</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				${course.courseDesc}
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">目标人群</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				${course.courseDesc}
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程看点</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				${course.courseDesc}
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">讲师介绍</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				<div>
					    <c:choose >
							<c:when test="${course.teacher.user.photo_url != null &&course. teacher.user.photo_url != ''}">
								<img src='<c:url value="${course.teacher.user.photo_url }"> </c:url>'style="width: 100px;height:100px; float:left; margin-left:10px"/>
							</c:when>
							<c:otherwise>
								 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'style="width: 100px;height: 100px; float:left; margin-left:30px"/>
							</c:otherwise>
						</c:choose>
				</div>
				<div style="width:500px; margin-left: 100px; float: left;" >
				    <table>
				    <tr >
				    <td style="width:150px;"><h4>${course.teacher.user.name }</h4></td><td>${course.teacher.college }</td>
				    </tr>
				    <tr>
				    <td style="width:150px;height: 30px">${course.teacher.title }</td><td>${course.teacher.school }</td>
				    </tr>
				    <tr>
				    <td style="width:150px;height: 30px">${course.teacher.major }</td>
				    </tr>
				    </table>
			  	</div>
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程计划</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				${course.courseDesc}
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">用户评价</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				${course.courseDesc}
			</div>
		</div>
		
	</div>
</div>



