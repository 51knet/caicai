<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
						<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url>
						<c:choose>
							<c:when test='${sessionUserInfo.avatar == "/resources/img/avatar/avatar90.png"}'>
						<div style="vertical-align: middle;text-align: center;">
						    <div style="background-image:url(${avatar_url}); background-repeat:no-repeat;background-position:center;height:90px;width:90px;margin:15px auto;">
						    <div style="height: 35px;"></div>
						    <div style="height: 20px;background-color:gray;padding:2px 2px;">
						    	<a href='<c:url value="/admin/teacher/details"><c:param name="active" value="avatar" /></c:url>' >上传头像</a>
						    </div>
						    <div style="height: 35px;"></div>
						   </div>
						</div>	
							</c:when>
							<c:otherwise>
						<div style="vertical-align: middle;text-align: center;">
							<img width="90px" height="90px" src="${avatar_url}" style="margin: 15px 0px;">
						</div>		
							</c:otherwise>
						</c:choose>
						<div>
							<ul class="nav nav-tabs nav-stacked">
								<li class="active"><a href='<c:url value="/admin"></c:url>' >个人中心</a></li>
								<li><a href='<c:url value="/teacher/${sessionUserInfo.id}"></c:url>' >个人主页</a></li>
								<li><a href='<c:url value="/admin/teacher/resume"><c:param name="active" value="personal" /></c:url>' >我的简历</a></li>
								<li><a href='<c:url value="/admin/teacher/details"><c:param name="active" value="avatar" /></c:url>' >账号信息</a></li>
								<li><a href='<c:url value="/admin/teacher/announcement/list"></c:url>' >发布公告</a></li>
								<li><a href='<c:url value="/admin/teacher/course/list"></c:url>' >课程管理</a></li>
								<li><a href='<c:url value="/admin/teacher/resource/list"></c:url>' >资源管理</a></li>
								<li><a href='<c:url value="/admin/teacher/achievement/list"></c:url>' >科研成果</a></li>
								<li><a href='<c:url value="/admin/blog/list"></c:url>' >我的博文</a></li>
								<!-- <li><a href='<c:url value="/admin/teacher/friendsRelated/list"></c:url>' >好友互动</a></li> -->
								<li><a href='<c:url value="/admin/teacher/message/list"></c:url>' >站内信</a></li>
								<li><a href='<c:url value="/admin/teacher/course/edit/createcourse"></c:url>' >教师资源管理</a></li>
								<!-- <li><a href='<c:url value="/admin/teacher/allCourse/list"></c:url>' >全部课程</a></li> -->
							</ul>
						</div>
