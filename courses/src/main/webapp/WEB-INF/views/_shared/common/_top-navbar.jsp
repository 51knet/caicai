<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="navbar custom navbar-inverse navbar-fixed-top">
	<div class="container" style="margin-left: 80px; margin-right: 80px;">
		<a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</a> <a class="brand" href="#">Courses</a> <a class="brand" href="#" style="padding: 0px 20px 0px;"><img src='<c:url value="/resources/img/logo.png"></c:url>' style="height: 40px;" /> </a>
		<div class="nav-collapse collapse navbar-responsive-collapse">
			<ul class="nav">
				<li class="active"><a href="/courses">主页</a></li>
				<li><a href="#">大学</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">关于 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li class="nav-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<!-- 
         <form class="navbar-search pull-left" action="">
           <input type="text" class="search-query span2" placeholder="Search">
         </form>
          -->
			<ul class="nav pull-right">
				<c:if test="${sessionUserInfo != null}">
				<li><a href='<c:url value="#"></c:url>' class="navbar-link">${sessionUserInfo.user.email }</a></li>
				<li><a style="margin-bottom: 10px; font-size: 14px;" href='<c:url value="/signout"></c:url>'>退出登录</a><br></li>
				</c:if>
				<c:if test="${sessionUserInfo == null}">
				<li><a style="margin-bottom: 10px; font-size: 14px;" href='<c:url value="/signin"></c:url>'>登录</a><br></li>
				</c:if>
				<li class="divider-vertical"></li>
				<li class="dropdown" style="display: none;"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>