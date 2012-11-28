<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="vertical-align: middle;text-align: center;">
	<c:url var="avatar_url" value="/resources/attached/${sessionUserInfo.id}/avatar_large.jpg"></c:url>
	<img width="90px" height="90px" src="${avatar_url}" style="margin: 15px 0px;"></div>
<div>
	<ul class="nav nav-tabs nav-stacked">
		<li class="active"><a href='<c:url value="/admin"></c:url>' >个人中心</a></li>
		<li><a href='<c:url value="/teacher/${sessionUserInfo.id}"></c:url>' >个人主页</a></li>
		<li><a href='<c:url value="/admin/teacher/resume"><c:param name="active" value="personal" /></c:url>' >我的简历</a></li>
		<li><a href='<c:url value="/admin/teacher/details"><c:param name="active" value="psw" /></c:url>' >账号信息</a></li>
		<li><a href='<c:url value="/admin/teacher/announcement/list"></c:url>' >发布公告</a></li>
		<li><a href='<c:url value="/admin/teacher/course/list"></c:url>' >课程管理</a></li>
		<li><a href='<c:url value="/admin/teacher/resource/list"></c:url>' >资源管理</a></li>
		<li><a href='<c:url value="/admin/teacher/achievement/list"></c:url>' >科研成果</a></li>
		<li><a href='<c:url value="/admin/blog/list"></c:url>' >我的博文</a></li>
		<li><a href='<c:url value="/admin/teacher/friendsRelated/list"></c:url>' >好友互动</a></li>
		<li><a href='<c:url value="/admin/teacher/message/list"></c:url>' >站内信</a></li>
	</ul>
</div>
