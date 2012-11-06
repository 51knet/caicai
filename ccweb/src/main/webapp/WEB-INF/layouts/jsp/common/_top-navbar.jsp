<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid" style="text-align: center">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</a> 
			<a class="brand" href="#" style="position: absolute; left: 50%">Knet</a>
			<c:if test="${userInfo != null}">
				<ul class="nav pull-right">
					<li><a href='<c:url value="/"></c:url>' class="navbar-link">${userInfo.user.email }</a></li>
					<li id="fat-menu" class="dropdown"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog icon-white"></i></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
							<li><a tabindex="-1" href='<c:url value="/admin"></c:url>'>后台管理</a></li>
							<li><a tabindex="-1" href='<c:url value="/admin/teacher/details"></c:url>'>账号设置</a></li>
							<li><a tabindex="-1" href="#">其他设置</a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href='<c:url value="/signout"></c:url>'>退出登录</a></li>
						</ul>
					</li>
				</ul>
			</c:if>
		</div>
	</div>
</div>