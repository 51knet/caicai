<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
body {
	max-width: 1150px;
	width: 100%;
	font-family: 'Microsoft YaHei';
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container-fluid .row-fluid .row.bg {
	margin: 0px auto;
	height: 600px;
	background: url(<c:url value='/resources/img/default/index/home_bg.png' ></c:url>) center no-repeat;
}

.span8.left {
	width: 70%;
	height: 50%;
	margin-top: 250px;
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
	height: 78%;
	width: 345px;
	margin: 40px -85px auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.span4.register-panel {
	height: 78%;
	width: 345px;
	margin:40px -85px auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.span4.forgotPsw-panel {
	height: 78%;
	width: 345px;
	margin:40px -85px auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.login-context {
	width: 80%;
	height: 100%;
	margin:0 auto;
}

.login-logo {
	height: 80px;
	margin:5px auto;
	text-align: center;
}

.form-horizontal {
	margin: 0 auto;
	height: 295px;
}

.form-horizontal .controls {
	margin-left: 0px;
}

.form-horizontal-register {
	margin: 0 auto;
	height: 295px
}

.hr-bg {
	height: 5px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.login-hr-bg {
	height: 15px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.register-hr-bg {
	height: 20px;
	margin-top: -5px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
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
				<div class="controls" id="email">
					<h5 style="text-align: left;">邮箱地址</h5>
					<div class="controls" style="text-align: left;">
						<input type="text" id="em" name="email" placeholder="请输入您的邮箱地址" style="width: 96.17%;">
					</div>
				</div>
				<div class="controls" id="password">
					<h5 style="text-align: left;">登录密码</h5>
					<div class="controls" style="text-align: left;">
						<input type="password" id="ps" name="password" placeholder="请输入您的密码" style="width: 96.17%;">
						<span class="help-inline" id="passwordErr"><form:errors path="psw"></form:errors></span>
					</div>
				</div>
				<div class="span9 left">
					<h5 class="controls" style="text-align: left;">
						<a id="fogotPswbtn" href="<c:url value='#'></c:url>"><font color="#444444">忘记密码？</font></a>
					</h5>
				</div>
				<button class="btn btn-large btn-block btn-primary" type="submit" onclick="return checkEmailAndPwd();" style="font-family: 'Microsoft YaHei';">登录</button>
			</form:form>
			<div class="login-hr-bg"></div>
			<a id="registerbtn" class="btn btn-large btn-block btn-success"  style="font-family: 'Microsoft YaHei';">快速注册</a>
		</div>
	</div>
	<div class="span4 register-panel" style="display: none;">
		<div class="login-context">
			<div>
				<img class="login-logo" src="<c:url value='/resources/img/default/index/login_logo.png' ></c:url>">
			</div>
			<form:form class="form-horizontal-register" style="margin-top: 10px;" id="register_info_form" action="register/common" modelAttribute="commonRegisterForm" method="post">
				<div class="hr-bg"></div>
				<div class="control" id="emails">
					<h5 style="text-align: left; line-height: 5px; margin-top: 15px;">邮箱地址</h5>
					<div class="controls" style="text-align: left;">
						<input type="text" id="es" name="emails" placeholder="请输入您的邮箱地址" style="width: 96.17%;">
					</div>
				</div>
				<div class="control" id="psw" style="margin-top: -15px;">
					<h5 style="text-align: left; line-height: 5px; margin-top: 15px;">密码</h5>
					<div class="controls" style="text-align: left;">
						<input type="password"  id="p" name="psw" placeholder="请设置您的密码" style="width: 96.17%;">
					</div>
				</div>
				<div class="control" id="confirmpsw" style="margin-top: -15px;">
					<h5 style="text-align: left; line-height: 5px; margin-top: 15px;">密码确认</h5>
					<div class="controls" style="text-align: left;">
						<input type="password" id="conf" name="confirmpsw" placeholder="请再次输入您的密码" style="width: 96.17%;">
						<span class="help-inline" id="passwordError"><form:errors path="psw"></form:errors></span>
					</div>
				</div>
				<div class="control" style="margin-top: -10px;">
					<h5 style="text-align: left; line-height: 5px; margin-top: 15px;">角色选择</h5>
					<div class="controls" style="text-align: left; margin-top: -5px;">
						<label class="radio inline" style="font-family: 'Microsoft YaHei'; font-size: 8pt; width: 20%;"> <input type="radio" name="userType" id="user" value="user" checked>普通用户</label>
						<label class="radio inline" style="font-family: 'Microsoft YaHei'; font-size: 8pt; width: 20%;"> <input type="radio" name="userType" id="teacher" value="teacher" >教师用户
						</label> <label class="radio inline" style="font-family: 'Microsoft YaHei'; font-size: 8pt; width: 20%;"> <input type="radio" name="userType" id="enterprise" value="enterprise">企业用户
						</label>
					</div>
				</div>
				<button type="submit" class="btn btn-large btn-block btn-primary" onclick="return registerOnclick();" style="font-family: 'Microsoft YaHei'; margin-top: 3px;">确认注册</button>
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
