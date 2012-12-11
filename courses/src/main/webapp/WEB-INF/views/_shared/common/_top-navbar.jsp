<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner custom">
		<div class="container" style="margin-left: 80px; margin-right: 80px;">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="#">Courses</a> <a class="brand" href="#" style="padding: 0px 20px 0px;"><img src="resources/img/logo.png" style="height: 40px;" /> </a>
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav">
					<li class="active"><a href="#">主页</a></li>
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
					<li><a style="margin-bottom: 10px; font-size: 14px;" href="#loginModal" data-toggle="modal">登录</a><br></li>
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
			<!-- /.nav-collapse -->
		</div>
	</div>
	<!-- /navbar-inner -->
</div>
<div id="loginModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true" style="text-align: left;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="loginModalLabel" style="text-align: center;">用户登录</h3>
	</div>
	<div class="span4 login-panel">
		<form:form class="form-horizontal" action="#" id="sigin_info_form" modelAttribute="loginForm" method="get">
			<div class="control-group" style="margin-top: 30px;">
				<label class="control-label" for="email">邮箱地址</label>
				<div class="controls">
					<input type="text" id="email" name="email" placeholder="请输入您的邮箱地址"> <span class="help-block" id="em"><form:errors path="email"></form:errors></span>
					<div id="emailError"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">登录密码</label>
				<div class="controls">
					<input type="password" id="password" name="password" placeholder="请输入您的密码"> <span class="help-block" id="pass"><form:errors path="password"></form:errors></span>
					<div id="passwordErr"></div>
				</div>
			</div>
			<div class="row-fluid" style="margin-left: 55%;">
				<div class="span6">
					<button class="btn btn-large btn-primary" type="submit">登录</button>
				</div>
			</div>
		</form:form>
	</div>
</div>