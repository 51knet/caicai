<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
body {
	padding-top: 68px;
}
.navbar .nav.custom > li > a {
	padding: 24px 15px;
}
.navbar-text.pull-right.custom{
	padding-top: 15px;
}
</style>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</a> 
			
			<a class="brand" href="#"><img src="<c:url value="/resources/img/logo.png"></c:url>" alt=""></a>
			
			<div class="nav-collapse collapse" style="visibility: hidden;">
				<p class="navbar-text pull-right custom">
					Logged in as <a href="#" class="navbar-link">Username</a>
				</p>
				<ul class="nav custom">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>