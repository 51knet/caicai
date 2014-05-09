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
			<li><a href='<c:url value='/incubator/${incubatUserInfo.id}'></c:url>'>主页</a></li>
			<li><a href='<c:url value='/incubator/${incubatUserInfo.id}/about'></c:url>'>关于我们</a></li>
			<li><a href='<c:url value='/incubator/${incubatUserInfo.id}/announcement/list'></c:url>'>新闻动态</a></li>
		</ul>
	</div>
</div>