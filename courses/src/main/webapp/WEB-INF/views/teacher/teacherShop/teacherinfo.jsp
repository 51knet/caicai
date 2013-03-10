<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container.teacher {
	width: 990px;
	max-width:990px;
	text-align: left;
}

.container.teacher .row{
}
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
</style>
<div class="container teacher">
<c:choose>
<c:when test="${teacher.isEnterprise == null}">
    <div  class="nar">
			<span class="content" style="padding-left:55px;"><b>讲师介绍</b></span>
		</div>
    <div style="margin-left: 60px;">
	    <c:choose >
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
			<a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 100px;height:100px; float:left; margin-left:30px" />
										</a>
			</c:when>
			<c:otherwise>
			<a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height: 100px; float:left; margin-left:30px" />
										</a>
			</c:otherwise>
		</c:choose>
    <span style="margin-left: 20px;font-size: 16px;"><a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'> <b>${teacher.user.name }</b></a></span>
   	<br/>
   	<span style="margin-left: 20px;font-size: 14px;">所在学校：${teacher.school }</span><br/> 
    <span style="margin-left: 20px;font-size: 14px;">职称：${teacher.major }</span><br/>
    <span style="margin-left: 20px;font-size: 14px;">专业：${teacher.college }</span><br/>
   </div>
</c:when>
<c:otherwise>
     <div  class="nar">
			<span class="content" style="padding-left:55px;"><b>企业介绍</b></span>
		</div>
    <div style="margin-left: 60px;">
	    <c:choose>
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
				<a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 100px;height:100px; float:left; margin-left:30px;" />
										</a>
			</c:when>
			<c:otherwise>
			<a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height: 100px; float:left; margin-left:30px;" />
										</a>
			</c:otherwise>
		</c:choose>
	<span style="margin-left: 20px;">
    <a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'> ${teacher.user.name }</a>
  	</span>
	</div>
</c:otherwise>
</c:choose>
</div>