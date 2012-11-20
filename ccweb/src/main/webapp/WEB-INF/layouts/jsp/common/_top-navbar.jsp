<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${sessionUserInfo == null }">
<style>
	body {padding-top: 0px;}
</style>
</c:if>
<c:if test="${ sessionUserInfo != null}">
<style>
.navbar-inner.custom {
	height: 45px;
	z-index: 2;
	background: #4E4E4E url(<c:url value="/resources/img/noise.png"></c:url>);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -moz-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -webkit-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -o-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -ms-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), linear-gradient(#4E4E4E, #1C1C1C);
	box-shadow: 0 1px 5px rgba(0, 0, 0, 0.45);
}
</style>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner custom">
		<div class="container-fluid" style="text-align: center">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</a> 
			<a id="logo" class="brand" href='<c:url value="/admin"></c:url>'>Knet</a>
			<c:if test="${sessionUserInfo != null}">
				<ul class="nav pull-right">
					<li><a href='<c:url value="/admin"></c:url>' class="navbar-link">${sessionUserInfo.user.email }</a></li>
					<li id="fat-menu" class="dropdown"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog icon-white" style="margin-top: 30%"></i></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
							<li><a tabindex="-1" href='<c:url value="/admin"></c:url>'>后台管理</a></li>
							<li><a tabindex="-1" href='<c:url value="/teacher/${sessionUserInfo.id}"></c:url>'>我的主页</a></li>
							<li><a tabindex="-1" href='<c:url value="/admin/teacher/details?active=personal"></c:url>'>账号设置</a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href='<c:url value="/signout"></c:url>'>退出登录</a></li>
						</ul>
					</li>
				</ul>
			</c:if>
		</div>
		
	</div>
</div>
</c:if>