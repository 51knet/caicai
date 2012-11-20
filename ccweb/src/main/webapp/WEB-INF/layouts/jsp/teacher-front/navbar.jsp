<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a href='<c:url value='/teacher/${teacherInfo.id}'></c:url>'>个人主页</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/resume'></c:url>'>简历</a></li>
			<li><a href="#">科研成果</a></li>
			<li><a href="#">课程资料</a></li>
			<li><a href='<c:url value='/teacher/${teacherInfo.id}/blog/list'></c:url>'>我的博文</a></li>
			<li><a href="#">我的资源</a></li>
			<li><a href="#">公告</a></li>
		</ul>
	</div>
</div>