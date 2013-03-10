<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="navbar custom navbar-inverse navbar-fixed-top">
	<div class="container-fluid" style="text-align: center; height: 54px;">
		<ul class="nav">
			<li><a id="logo" class="brand" href='<c:url value="/admin"></c:url>'></a></li>
			<li class="active"><a href="/courses">主页</a></li>
		</ul>
		<ul class="nav pull-right" style="margin-top: -5px;">
			<c:if test="${sessionUserInfo != null}">
				<li style="margin-top: 5px;"><a href='<c:url value="/admin"></c:url>' class="navbar-link">${sessionUserInfo.user.email }</a></li>
				<li id="fat-menu" class="dropdown"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"> <img src="<c:url value='/resources/img/default/gear.png'></c:url>"
						style="margin-top: 2px;"></img>
				</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
						<li><a href='<c:url value="http://www.51knet.com/"></c:url>'>回到知识网首页</a></li>
						<li class="divider"></li>
						<li><a href='<c:url value="/signout"></c:url>'>退出登录</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionUserInfo == null}">
				<li><a href='<c:url value="/signin"></c:url>'><h4>登录</h4></a><br></li>
				<li><a href='<c:url value="/signup"></c:url>'><h4>注册</h4></a><br></li>
			</c:if>
		</ul>
		<br>
	</div>
</div>
