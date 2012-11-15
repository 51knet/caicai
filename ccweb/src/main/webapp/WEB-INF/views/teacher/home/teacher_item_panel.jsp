<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.divboxstyle{
	border-radius:5px;
	-webkit-border-radius:5px;
	-moz-border-radius:5px;
	background:#FFFFFF;
	/*height:300px;
	width:300px;*/
	display:inline-block;
}
.divboxstyle:hover{
	box-radius:1px 2px 3px rgba(0,0,0,0.5);
	-webkit-box-radius:1px 2px 3px rgba(0,0,0,0.5);
	-moz-box-radius:1px 2px 3px rgba(0,0,0,0.5);
	border-bottom:1px solid rgba(10,85,125,0.8);
	position: relative;
	background:#f5fbff;
}
li {
	display: list-item;
	text-align: -webkit-match-parent;
}

#Idpersonalcentermenu {
	background: #F0F2F4;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}

#Idpersonalcentermenu ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

#Idpersonalcentermenu ul li {
	padding-left: 15px;
	height: 36px;
	line-height: 36px;
	border-top: 1px solid white;
	box-shadow: 0 -1px #C7CED4;
}

#Idpersonalcentermenu ul li:hover {
	background: url(/ccweb/resources/img/menubg_001.jpg);
	padding-left: 15px;
	color: #ffffff;
}
</style>
<div style="width: 180px; height: 640px; float: left;" class="divboxstyle">
	<div style="width: 180px; height: 140px; line-height: 120px;" align="center" id="Idpersonalcentermenu">
		<img width="90px" height="90px" style="margin-top: 15px;">
	</div>
	<div style="width: 180px; height: 40px; font-family: '微软雅黑'; font-size: 16px; line-height: 40px;">
		<span style="padding: 15px;"><a href='<c:url value="/admin"></c:url>' >个人中心</a></span>
	</div>
	<div style="width: 180px; height: 460px;" id="Idpersonalcentermenu">
		<ul>
			<li><a href='<c:url value="/"></c:url>' >个人主页</a></li>
			<li><a href='<c:url value="/admin/teacher/announcement/list"></c:url>' >发布公告</a></li>
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
</div>