<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.brand.custom {
		padding: 0px 0px 0px;
	}
</style>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid" style="text-align: center">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			
			<a class="brand custom" href="#" style="position: absolute;left: 50%"><img src="<c:url value="resources/img/knetlogo.png" ></c:url>" width="90" height="32" alt="knet"></a>

			<ul class="nav pull-right">
				<li id="fat-menu" class="dropdown"><a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">Dropdown 3 <b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">
						<li><a tabindex="-1" href="#">Action</a></li>
						<li><a tabindex="-1" href="#">Another action</a></li>
						<li><a tabindex="-1" href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="#">Separated link</a></li>
					</ul></li>
			</ul>

			<div class="nav-collapse collapse" style="visibility: hidden; display: none">
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