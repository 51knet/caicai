<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nav-tabs.nav-stacked > li > a {
border: 1px solid #DDD;
-webkit-border-radius: 0;
-moz-border-radius: 0;
border-radius: 0;
text-align: center;
font-size: medium;
font-family: 'Microsoft YaHei',Arial;
}
.nav-tabs > li > a:hover {
color: #FFF;
text-decoration: none;
background-color: #80B029;
}
.nav-tabs > .active > a, .nav-tabs > .active > a:hover {
color: #FFF;
cursor: default;
text-decoration: none;
background-color: #80B029;
border: 1px solid #DDD;
border-bottom-color: transparent;
}
.left-menu-container {
background-color: #ccdfa8;
width: 148px;
}
.left-menu-container a{
color: #666;
}
</style>
<div class="left-menu-container">
	<ul  class="nav nav-tabs nav-stacked" style="width:150px;">
		<li><a href='<c:url value="/course/study/view/${course.id}"></c:url>'><font style="size: 22px">课程学习</font></a></li>
		<li><a href='<c:url value="/course/study/courseinfo/${course.id}"></c:url>'><font style="size: 22px">课程信息</font></a></li>
		<li><a href='<c:url value="/course/study/comment/${course.id}"></c:url>'><font style="size: 22px">用户评价</font></a></li>
		<li><a href='<c:url value="/course/list/type?detail=all"></c:url>'><font style="size: 22px">全部课程</font></a></li>
		<li><a href='<c:url value="/"></c:url>'><font style="size: 22px">返回首页</font></a></li>
	</ul>
</div>
