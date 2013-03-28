<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar .navbar-inner .nav{
	margin-left: 30px;
}
.navbar .navbar-inner .nav >li{
	margin-left: 15px;
}
</style>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a href='<c:url value='/enterprise/${teacherInfo.id}'></c:url>'>首页</a></li>
			<li><a href='<c:url value='/enterprise/${teacherInfo.id}/course'></c:url>'>学习资源</a></li>
			<li><a href='<c:url value='/enterprise/${teacherInfo.id}/teacher/list'></c:url>'>知名老师</a></li>
			<li><a href='<c:url value='/enterprise/${teacherInfo.id}/resume'></c:url>'>学校介绍</a></li>
			<li><a href='<c:url value='/enterprise/${teacherInfo.id}/announcement/list'></c:url>'>公告专栏</a></li>
			<%-- <c:if test="${teacherInfo.isEnterprise == null}">
				<li><a href='<c:url value='/teacher/${teacherInfo.id}/achievement/list'></c:url>'>科研成果</a></li>
			</c:if> 
			<li><a href='<c:url value='/enterprise/${teacherInfo.id}/blog/list'></c:url>'>博文</a></li>--%>
		</ul>
	</div>
</div>