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
	width: 1024px;
	max-width:1024px;
	text-align: left;
}

.nar{
	padding-top: 5px;
	width: 1024px;
	
}
.container.course.detail{
	margin-left: 46px;
	padding-left: 30px;
	padding-top: 30px;
}
.content{
	margin-left: 20px;
	font-size: 15px;
}
</style>
<div class="container teacher">
<c:choose>
<c:when test="${teacher.user.role == 'teacher'}">
    <div  class="nar" >
			<h4>讲师介绍</h4>
		</div>
    <div class="container course detail">
	    <c:choose >
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
			<a href='<c:url value="${url}/teacher/${teacher.id}"></c:url>'><img src='<c:url value="${url}${teacher.user.photo_url }"></c:url>' style="width: 100px;height:100px; float:left; margin-left:10px" />
										</a>
			</c:when>
			<c:otherwise>
			<a href='<c:url value="${url}/teacher/${teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width:100px;height: 100px; float:left; margin-left:10px" />
										</a>
			</c:otherwise>
		</c:choose>
    <span style="margin-left: 20px;font-size: 18px;"><a href='<c:url value="${url}/teacher/${teacher.id}"></c:url>'> <b>${teacher.user.name }</b></a></span>
   	<br/>
   	<br/>
   	<span class="content">所在学校：${teacher.school }</span><br/> 
   	<span class="content">所在学院：${teacher.college }</span><br/>
    <span class="content">专业：${teacher.major }</span><br/>
   </div>
</c:when>
<c:otherwise>
     <div class="nar">
			<h4>企业介绍</h4>
		</div>
    <div class="container course detail">
	    <c:choose>
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
				<img src='<c:url value="${url }${teacher.user.photo_url }"></c:url>' style="width: 100px;height:100px; float:left; margin-left:10px;" />
			</c:when>
			<c:otherwise>
			<img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height: 100px; float:left; margin-left:10px;" />
			</c:otherwise>
		</c:choose>
	<span style="margin-left: 20px;">
     ${teacher.user.name }
  	</span>
	</div>
</c:otherwise>
</c:choose>
</div>