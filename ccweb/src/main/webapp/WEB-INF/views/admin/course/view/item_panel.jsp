<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div style="margin-top: 10px;">
<div style="vertical-align: middle;text-align: center;">
	<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url>
	<img width="90px" height="90px" src="${avatar_url}" style="margin: 15px 0px;">
  </div>
	<ul class="nav nav-tabs nav-stacked">
		<li><a href='<c:url value=""></c:url>'>全部课程</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/createcourse"></c:url>'>添加课程资料</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/basicmessage"></c:url>'>基本信息</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/detailmessage"></c:url>'>详细信息</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/createcover"></c:url>'>添加封面</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/watchvideo"></c:url>'>试看视频</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/powerprice"></c:url>'>权限&价格</a></li>
		<li><a href='<c:url value="/admin/teacher/course/edit/deletemessage"></c:url>'>取消课程</a></li>
	</ul>
</div>
