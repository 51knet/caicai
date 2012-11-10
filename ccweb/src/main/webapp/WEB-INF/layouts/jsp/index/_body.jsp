<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
body {
	max-width: 1600px;
	width: 100%;
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container-fluid .row-fluid {
	background: url(<c:url value="/resources/img/top_bg.jpg" ></c:url>) repeat;
}

.container-fluid .row-fluid .row.mac {
	margin: 0px auto;
	height: 640px;
	background: url(<c:url value='/resources/img/home_bg.png' ></c:url>) center -10px no-repeat;
}

.span4.login-panel {
	width: 340px;
	margin-top: 40px;
	padding-bottom: 10px;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	background: #F8F8FA;
	text-align: center;
	
}

.span4.register-panel {
	width: 340px;
	margin-top: 40px;
	padding-bottom: 10px;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	background: #F8F8FA;
	text-align: center;
	display: none;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("#registerbtn").click(function() {
			$(".login-panel").hide(500);
			$(".register-panel").show(500);
		});
		$("#loginbtn").click(function() {
			$(".login-panel").show(500);
			$(".register-panel").hide(500);
		});
	});
</script>

<div class="row mac">
	<div class="span7"></div>
	<div class="span4 login-panel">
		<form:form class="form-signin" action="/ccweb/signin" modelAttribute="loginForm" method="post">
			<h3 class="form-signin-heading" style="font-family:'Microsoft YaHei'">用户登录</h3>
			<input type="text" name="email" placeholder="邮箱地址">
			<span class="help-block"><form:errors path="email"></form:errors></span>
			<input type="password" name="password" placeholder="密码">
			<span class="help-block"><form:errors path="password"></form:errors></span>
			<label class="checkbox inline"> <input type="checkbox" name="rememberMe" id="inlineCheckbox1" value="1"> 记住密码 &nbsp;&nbsp;<a href="#">忘记密码？</a>
			</label>
			<label style="clear: right;"></label>
			<button class="btn btn-large btn-primary" type="submit" style="margin-top: 10px">登录</button>
		</form:form>
		<hr />
		<a id="registerbtn" class="btn btn-large btn-success">快速注册</a>
		
	</div>
	<div class="span4 register-panel" style="display: none;">
		<form:form action="register/common" modelAttribute="commonRegisterForm" method="post">
			<h3 class="form-signin-heading" style="font-family:'Microsoft YaHei'">快速注册</h3>
			<input type="text" name="email" placeholder="邮箱地址">
			<span class="help-block"><form:errors path="email"></form:errors></span>
			<input type="password" name="psw" placeholder="密码">
			<span class="help-block"><form:errors path="psw"></form:errors></span>
			<input type="password" name="confirmpsw" placeholder="密码确认">
			<span class="help-block"><form:errors path="confirmpsw"></form:errors></span>
			<label style="clear: right;"></label>
			<button type="submit" class="btn btn-large btn-primary">确认注册</button>
		</form:form>
		<hr />
		<a id="loginbtn" class="btn btn-large btn-success" style="margin-top: -7px">用户登录</a>
		
	</div>
</div>
 	
