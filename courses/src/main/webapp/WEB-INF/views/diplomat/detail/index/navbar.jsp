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
			<li><a href='<c:url value='/diplomat/${teacherInfo.id}'></c:url>'>主页</a></li>
			<li><a href='<c:url value='/diplomat/${teacherInfo.id}/resume'></c:url>'>个人介绍</a></li>
			<li><a href='<c:url value='/diplomat/${teacherInfo.id}/announcement/list'></c:url>'>外交动态</a></li>
			<li><a href='<c:url value='/diplomat/${teacherInfo.id}/project/list'></c:url>'>推荐项目</a></li>
			<li><a href='<c:url value='/diplomat/${teacherInfo.id}/requirement/list'></c:url>'>技贸需求</a></li>
		</ul>
	</div>
</div>