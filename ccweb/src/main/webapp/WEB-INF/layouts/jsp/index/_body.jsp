<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
body {
	max-width: 1024px;
	width: 100%;
	font-family: 'Microsoft YaHei';
	overflow-y:hidden;
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container-fluid .row-fluid {
	background: url(<c:url value="/resources/img/default/top_bg.jpg" ></c:url>) repeat;
}

.container-fluid .row-fluid .row.bg {
	margin: 0px auto;
	height: 750px;
	background: url(<c:url value='/resources/img/default/index/home_bg.png' ></c:url>) center -50px no-repeat;
}

.container-fluid .row-fluid .row.ad {
	margin: 0px auto;
	height: 750px;
	background: url(<c:url value='/resources/img/default/index/home_ad.png' ></c:url>) center -120px no-repeat;
}

.span8.left {
	width: 62%;
	height: 100%;
	margin-top: 40px;
	background: url(<c:url value='/resources/img/default/index/ad_bg.png' ></c:url>) center no-repeat;
}

.span9.left {
	width: 40%;
}

.span9.right {
	width: 55%;
	margin-top: -5px;
}

.span4.login-panel {
	height: 75%;
	width: 345px;
	margin:0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.span4.register-panel {
	height: 75%;
	width: 345px;
	margin:0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.span4.forgotPsw-panel {
	height: 75%;
	width: 345px;
	margin:0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.login-context {
	width: 67.391%;
	height: 100%;
	margin:0 auto;
}

.login-logo {
	height: 80px;
	margin:70px 0 auto;
	text-align: center;
}

.form-horizontal {
	margin: 0 auto;
	height: 280px;
}

.form-horizontal .controls {
	margin-left: 0px;
}

.form-horizontal-register {
	margin: 0 auto;
	height: 385px
}

.hr-bg {
	height: 5px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.login-hr-bg {
	height: 20px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.register-hr-bg {
	height: 10px;
	margin-top: -5px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.inputError{
	height: 10px;
}
.radio.inline{
	width: 20%;
}
</style>
<style type="text/css" media="screen">@import url("<c:url value="/resources/js/jq-mail/jquery.autoMailSuggest.css"/>");</style>
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/jq-mail/jquery.autoMailSuggest.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/jquery/loginCheck.js"/>"></script>
<script type="text/javascript">
	$(function(){
	    $("#em").autoMailSuggest(defaultMailSuffix);
	  });
	$(function(){
	    $("#es").autoMailSuggest(defaultMailSuffix);
	  });
	$(function(){
	    $("#emailForPsw").autoMailSuggest(defaultMailSuffix);
	  });
	function checkEmailAndPwd(){
		return checkEmailAndPass("sigin_info_form",'checkEmailAndPassword');
	}
	function registerOnclick(){
		return reginsterLogin("register_info_form",'registerEmailAndPwd');
	}
	function forgetPwdOnclick(){
		return forgetPwd("forgotPsw_info_form",'forgetPwdCheck');
	}
</script>

<div class="row bg">
	<div class="span8 left"></div>
	<div class="span4 login-panel">
		<div class="login-context">
			<div>
				<img class="login-logo" src="<c:url value='/resources/img/default/index/login_logo.png' ></c:url>">
			</div>
			<form:form class="form-horizontal" action="signin" id="sigin_info_form" modelAttribute="loginForm" method="post">
				<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei'; font-size: 25pt;">用户登录</h1>
				<div class="hr-bg"></div>
				<div style="height: 10px"></div>
				<div class="controls" id="email">
					<h5 style="text-align: left;">邮箱地址</h5>
					<div class="controls" style="text-align: left;">
						<input type="text" id="em" name="email" placeholder="请输入您的邮箱地址" style="width: 96.17%;">
						<span class="help-inline" id="emailErrors"></span>
					</div>
				</div>
				<div class="controls" id="password">
					<h5 style="text-align: left;">登录密码</h5>
					<div class="controls" style="text-align: left;">
						<input type="password" id="ps" name="password" placeholder="请输入您的密码" style="width: 96.17%;">
						<span class="help-inline" id="passwordErr"></span>
					</div>
				</div>
				<div class="span9 left">
					<h5 class="controls" style="text-align: left;">
						<a id="fogotPswbtn" href="<c:url value='#'></c:url>"><font color="#444">忘记密码？</font></a>
					</h5>
				</div>
				<div class="span9 right">
					<button class="btn btn-large btn-block btn-primary" type="submit" onclick="return checkEmailAndPwd();" style="font-family: 'Microsoft YaHei';">登录</button>
				</div>
				<label style="clear: right;"></label>
				<div style="height: 10px;"></div>
			</form:form>
			<div class="login-hr-bg"></div>
			<a id="registerbtn" class="btn btn-large btn-block btn-success"  style="font-family: 'Microsoft YaHei';">快速注册</a>
		</div>
	</div>
	<div class="span4 register-panel" style="display: none;">
		<div class="login-context">
			<div style="margin-top: 75px"></div>
			<form:form class="form-horizontal-register" style="margin-top: 10px;" id="register_info_form" action="register/common" modelAttribute="commonRegisterForm" method="post">
				<div class="control" id="emails">
					<h5 style="text-align: left;">邮箱地址</h5>
					<div class="controls" style="text-align: left;">
						<input type="text" id="es" name="emails" placeholder="请输入您的邮箱地址" style="width: 96.17%;">
						<!-- <div id="checkEmails" class="inputError"></div> -->
						<span class="help-inline" id="emailsError"><form:errors path="emails"></form:errors></span>
					</div>
				</div>
				<div class="control" id="psw" style="margin-top: -15px;">
					<h5 style="text-align: left;">密码</h5>
					<div class="controls" style="text-align: left;">
						<input type="password"  id="p" name="psw" placeholder="请设置您的密码" style="width: 96.17%;">
						<!-- <div id="emptyPwd" class="inputError"></div> -->
						<span class="help-inline" id="emptyPwd"><form:errors path="psw"></form:errors></span>
					</div>
				</div>
				<div class="control" id="confirmpsw" style="margin-top: -15px;">
					<h5 style="text-align: left;">密码确认</h5>
					<div class="controls" style="text-align: left;">
						<input type="password" id="conf" name="confirmpsw" placeholder="请再次输入您的密码" style="width: 96.17%;">
						<span class="help-inline" id="passwordError"><form:errors path="confirmpsw"></form:errors></span>
					</div>
				</div>
				<div class="control">
					<h5 style="text-align: left;">角色选择</h5>
					<div class="controls" style="text-align: left;">
						<label class="radio inline" style="font-family: 'Microsoft YaHei'; width: 30%;"> <input type="radio" name="userType" id="teacher" value="teacher" checked>教师用户
						</label> <label class="radio inline" style="font-family: 'Microsoft YaHei'; width: 30%;"> <input type="radio" name="userType" id="enterprise" value="enterprise">企业用户
						</label>
					</div>
				</div>
				<label style="clear: right;"></label>
				<button type="submit" class="btn btn-large btn-block btn-primary" onclick="return registerOnclick();" style="margin-top: 15px; font-family: 'Microsoft YaHei';">确认注册</button>
			</form:form>
			<div class="register-hr-bg"></div>
			<a id="loginbtn" class="btn btn-large btn-block btn-success" style="font-family: 'Microsoft YaHei';">用户登录</a>
		</div>
	</div>
	<div class="span4 forgotPsw-panel" style="display: none;">
		<div class="login-context">
			<div>
				<img class="login-logo" src="<c:url value='/resources/img/default/index/login_logo.png' ></c:url>">
			</div>
			<form:form class="form-horizontal" action="forgotPsw" id="forgotPsw_info_form" modelAttribute="forgotPswForm" method="post">
				<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei'; font-size: 25pt;">密码找回</h1>
				<div class="hr-bg"></div>
				<div style="height: 10px"></div>
				<div class="controls" id="forgotemail">
					<h5 style="text-align: left;">您的注册邮箱地址</h5>
					<div class="controls" style="text-align: left;">
						<input type="text" id="emailForPsw" name="forgotemail" placeholder="请输入您已注册的邮箱地址" style="width: 96.17%;">
						<!-- <div id="emailError" class="inputError"></div> -->
						<span class="help-inline" id="emailError"><form:errors path="emailForPsw"></form:errors></span>
					</div>
				</div>
				<label style="clear: right;"></label>
				<div style="height: 30px;"></div>
				<button class="btn btn-large btn-block btn-primary" type="submit" onclick="return forgetPwdOnclick();" style="font-family: 'Microsoft YaHei';">发送邮件，找回密码！</button>
			</form:form>
			<div class="login-hr-bg"></div>
			<a id="tologinbtn" class="btn btn-large btn-block btn-success" style="font-family: 'Microsoft YaHei';">返回登录</a>
		</div>
	</div>
</div>
