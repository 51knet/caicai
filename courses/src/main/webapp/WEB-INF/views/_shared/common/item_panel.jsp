<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<ul  class="nav nav-tabs nav-stacked" style="width:150px;">
		<li><a href='<c:url value="/course/study/courseinfo/${course.id}"></c:url>'><font style="size: 22px">课程信息</font></a></li>
		<li><a href='<c:url value="/course/study/view/${course.id}"></c:url>'><font style="size: 22px">课程资料</font></a></li>
		<li><a href='<c:url value="/course/study/comment/${course.id}"></c:url>'><font style="size: 22px">用户评价</font></a></li>
		<li><a href='<c:url value="/course/list/type?detail=all"></c:url>'><font style="size: 22px">全部课程</font></a></li>
		<li><a href='<c:url value="/"></c:url>'><font style="size: 22px">返回首页</font></a></li>
	</ul>
</div>
