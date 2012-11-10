<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a href='<c:url value="/admin"></c:url>'>个人中心</a></li>
			<li><a href='<c:url value='/teacher/${userInfo.id}'></c:url>'>个人主页</a></li>
			<li><a href='<c:url value="#"></c:url>'>科研成果</a></li>
			<li><a href="#">个人资料</a></li>
			<li><a href="#">科研成果</a></li>
			<li><a href="#">课程资料</a></li>
			<li><a href='<c:url value='/teacher/${userInfo.id}/blog/list'></c:url>'>我的博文</a></li>
			<li><a href="#">知识库</a></li>
			<li><a href="#">公告</a></li>
			<li><a href="#">English Version</a></li>
		</ul>
	</div>
</div>