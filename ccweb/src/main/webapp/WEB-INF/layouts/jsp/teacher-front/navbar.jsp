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
			<li><a href='<c:url value='/teacher/${teacherInfo.id}'></c:url>'>主页</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/resume'></c:url>'>简历</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/announcement/list'></c:url>'>公告</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/course/list'></c:url>'>课程资料</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/resource/list'></c:url>'>文献资料</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/patent/list'></c:url>'>专利</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/blog/list'></c:url>'>博文</a></li>
		</ul>
	</div>
</div>