<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course.title{
	height: 240px;
	width:1024px;
	background-image: url('<c:url value='/resources/img/default/carousel_bg.png'></c:url>');
	background-repeat: repeat-x;
	margin-top: 35px;
}
.images{
	margin-top: 40px;
	margin-left: 100px;
}
</style>
<div class="container course title" style="background-color: #f7f7f7; margin-top: 42px;">
	<c:choose>
		<c:when test="${course != null}">
		<div class="images">
	    	<table >
				<tr>
				<td  width="30%" align="center"  valign="top" >
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 200px; height: 150px;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width:200px; height: 150px;" />
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td align="left" valign="top" >
						<div style="margin-left: 20px; float: left;width: 195px;margin-top: -13px;">
						<h4>${course.courseName}</h4><h4>${course.cType.typeName }</h4></div>
					</td>
					<td width="40%" align="left"  valign="top" style="line-height: 30px;">
							<div style="float: left;padding-right: 15px;">
								<c:choose>
									<c:when test="${course.teacher.user.photo_url != null && course.teacher.user.photo_url != ''}">
										<a href='<c:url value="/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 100px; height: 100px;" />
										</a>
									</c:when>
									<c:otherwise>
										<a href='<c:url value="/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar90.png"></c:url>' style="width:100px; height:100px;" />
										</a>
									</c:otherwise>
								</c:choose>
							</div>
							<h4 style="margin-top: -3px;">${course.teacher.user.name }</h4>
							${course.teacher.college }<br/>
							<a  href='<c:url value="/teacher/${course.teacher.id}"></c:url>' class="btn  btn-success">查看发布课程</a>
					</td>
				</tr>
			</table>
   		</div></c:when>
		<c:otherwise>
			<div class="row" style="text-align:left; margin-left: 0px; margin-top: 10px;">
		    	<table class="table table-bordered" style="width: 97%; height: 100%;" cellpadding="5">
					<tr>
						<td valign="top"  align="left">
							<h3>您尚未学习任何课程</h3>
							<a href='<c:url value="/course/list/type?detail=all"></c:url>' class="btn btn-large btn-success">点击开始学习</a>
						</td>
					</tr>
				</table>
    		</div>
		</c:otherwise>
	</c:choose>
</div>



	  
 
