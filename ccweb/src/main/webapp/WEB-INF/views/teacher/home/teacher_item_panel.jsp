<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div style="text-align: center;">
	<a href='<c:url value="/admin/teacher/announcement/detail"></c:url>' class="btn">发布公告</a>
	<hr />
	<a href='<c:url value="/admin/teacher/resource/list"></c:url>' class="btn">资源管理</a>
	<hr />
	<a href='<c:url value="/admin/blog/list"></c:url>' class="btn">我的博文</a>
	<hr />
	<a href='<c:url value="/"></c:url>' class="btn">个人主页</a>
	<hr />
	<a href='<c:url value="/admin/teacher/details"></c:url>' class="btn">账号信息</a>
	<hr />
	<a href='<c:url value="/admin/teacher/achievement/detail"></c:url>' class="btn">科研成果</a>
	<hr />
	<a href='<c:url value="/admin/teacher/friendsRelated/detail"></c:url>' class="btn">好友互动</a>
	<hr />
	<a href='<c:url value="/admin/teacher/teacherCourse/detail"></c:url>' class="btn">课程管理</a>
	<hr />
	<a href='<c:url value="/admin/teacher/message/list"></c:url>' class="btn">站内信</a>
	
</div>