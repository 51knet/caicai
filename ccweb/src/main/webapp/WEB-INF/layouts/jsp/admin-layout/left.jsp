<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="vertical-align: middle;text-align: center;">
	<img width="90px" height="90px" src="http://dummyimage.com/90x90" style="margin: 15px 0px;">
</div>
<div>
	<ul class="nav nav-tabs nav-stacked">
		<li class="active"><a href='<c:url value="/admin"></c:url>' >个人中心</a></li>
		<li><a href='<c:url value="/teacher/${userInfo.id}"></c:url>' >个人主页</a></li>
		<li><a href='<c:url value="/admin/teacher/announcement/detail"></c:url>' >发布公告</a></li>
		<li><a href='<c:url value="/admin/teacher/resource/list"></c:url>' >资源管理</a></li>
		<li><a href='<c:url value="/admin/blog/list"></c:url>' >我的博文</a></li>
		<li><a href='<c:url value="/admin/teacher/details"></c:url>' >账号信息</a></li>
		<li><a href='<c:url value="/admin/teacher/achievement/detail"></c:url>' >科研成果</a></li>
		<li><a href='<c:url value="/admin/teacher/friendsRelated/detail"></c:url>' >好友互动</a></li>
		<li><a href='<c:url value="/admin/teacher/teacherCourse/detail"></c:url>' >课程管理</a></li>
		<li><a href='<c:url value="/admin/teacher/message/list"></c:url>' >站内信</a></li>
		<li></li>
	</ul>
</div>
