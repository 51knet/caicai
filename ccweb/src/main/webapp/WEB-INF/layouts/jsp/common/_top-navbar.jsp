<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="navbar custom navbar-fixed-top">
	<div class="container-fluid" style="text-align: center; height: 39px; width: 1024px; margin: 0 auto;">
		<ul class="nav pull-left">
			<li><a id="logo" href='<c:url value="/admin"></c:url>'></a></li>
		</ul>
		<ul class="nav pull-right">
			<li><a href="http://www.51knet.com/courses"><img class="courselogo" src="<c:url value='/resources/img/default/courselogo.png'></c:url>"> <img class="goto"
					src="<c:url value='/resources/img/default/index/goto.png'></c:url>"></a></li>
			<c:if test="${sessionUserInfo != null}">
				<li style="margin-right: -12px;"><a href='<c:url value="/admin"></c:url>' class="navbar-link">${sessionUserInfo.user.email }</a></li>
				<li id="fat-menu" class="dropdown" style="margin-right: -10px"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"> <img src="<c:url value='/resources/img/default/gear.png'></c:url>"
						style="margin-top: -3px;height:15px;"></img>
				</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
						<li><a href='<c:url value="/admin"></c:url>'>后台管理</a></li>
						<li><a href='<c:url value="/teacher/${sessionUserInfo.id}"></c:url>'>我的主页</a></li>
						<li><a href='<c:url value="/admin/teacher/resume?active=personal"></c:url>'>完善简历</a></li>
						<li><a href='<c:url value="/admin/teacher/details?active=avatar"></c:url>'>账号设置</a></li>
						<li class="divider"></li>
						<li><a href='<c:url value="http://www.51knet.com/courses"></c:url>'>发现知识</a></li>
						<li class="divider"></li>
						<li><a href='<c:url value="/signout"></c:url>'>退出登录</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionUserInfo == null}">
				<li ><div style="width: 30px;"></div></li>
			</c:if>
		</ul>
		<br>
	</div>
</div>
