<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
body {
	max-width: 1600px;
	width: 100%;
	font-family: 'Microsoft YaHei';
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
}

.span4.login-panel {
	height: 100%;
	margin:0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.span4.register-panel {
	height: 100%;
	margin:0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.span4.forgotPsw-panel {
	height: 100%;
	margin:0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center no-repeat;
}

.login-context {
	width: 67.391%;
	margin:0 auto;
}

.login-logo {
	width: 100%;
	height: 103px;
	margin:70px 0 auto;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_logo.png' ></c:url>) center no-repeat;
}

.form-horizontal {
	margin: 40px 0 0;
}

.form-horizontal .controls {
	margin-left: 0px;
}
.hr-bg {
	height: 40px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.register-hr-bg {
	height: 20px;
	background: url(<c:url value='/resources/img/default/index/hr.png' ></c:url>) center no-repeat;
}
.inputError{
	height: 20px;
}
.radio.inline{
	width: 20%;
}
</style>
<style type="text/css" media="screen">@import url("<c:url value="/resources/js/jq-mail/jquery.autoMailSuggest.css"/>");</style>
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/jq-mail/jquery.autoMailSuggest.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#registerbtn").click(function() {
			$(".login-panel").hide(500);
			$(".register-panel").show(500);
			return false;
		});
		$("#loginbtn").click(function() {
			$(".login-panel").show(500);
			$(".register-panel").hide(500);
			return false;
		});
		$("#fogotPswbtn").click(function() {
			$(".forgotPsw-panel").show(500);
			$(".login-panel").hide(500);
			$(".register-panel").hide(500);
			return false;
		});
		$("#tologinbtn").click(function() {
			$(".login-panel").show(500);
			$(".forgotPsw-panel").hide(500);
			return false;
		});
		
		$("#psw").blur(function (){
			var psw=$("#psw").val();
			if(psw==""){
				$("#emptyPwd").html("密码不能为空");
				return false;
			}
		});
		$("#confirmpsw").blur(function(){
			var psw=$("#psw").val();
			var confirmpsw=$("#confirmpsw").val();
			if(confirmpsw==""){
				$("#passwordError").html("确认密码不能为空");
				return false;
			}
			if(psw!=confirmpsw){
				$("#passwordError").html("两次输入的密码不一致,请重新输入");
				return false;
			}
		});
		$("#psw").focus(function(){
			$("#emptyPwd").html("");
			return false;
		});
		$("#confirmpsw").focus(function(){
			$("#passwordError").html("");
			return false;
		});
		$("#emails").focus(function(){
			$("#checkEmails").html("");
			return false;
		});
		$("#emails").blur(function(){
			var email=$("#emails").val();
			if(email==""){
				$("#checkEmails").html("邮箱不能为空,请输入邮箱");
				return false;
			}
			var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;//邮箱验证正则表达式。 
			if(!reg.test(email)){                             //验证邮箱格式是否正确
				$("#checkEmails").html("输入的邮箱格式不正确");
				return false;
			}
			$.ajax({
			  type: "post",
			  url: "<c:url value='/register/email'/>",
			  data: "email="+email,
			  dataType:"text",
			  success:function(num){
					if(num=='1'){
					$("#checkEmails").html("此邮箱地址已存在");
					return false;
					}else{
						$("#checkEmails").html("");
						return false;
					}
				}
			});
		});
		$("#email").focus(function(){
			$("#emailError").html("");
			$("#em").html("");
			return false;
		});
		$("#password").focus(function(){
			$("#passwordErr").html("");
			$("#pass").html("");
			return false;
		});
		$("#password").blur(function(){
			var email=$("#email").val();
			var password=$("#password").val();
			if(email==""){
				$("#emailError").html("邮箱不能为空");
				return false;
			}
			if(password==""){
				$("#passwordErr").html("密码不能为空");
				return false;
			}
			$.ajax({
				  type: "post",
				  url: "<c:url value='/checkEmailAndPassword'/>",
				  data: "email="+email+"&password="+password,
				  dataType:"text",
				  success:function(number){
					if(number == "0"){
						$("#emailError").html("邮箱或密码输入错误");
						$("#passwordErr").html("邮箱或密码输入错误");
						return false;
					}
				  }
				});
			
		});
	});
	
	$(function(){
	    $("#email").autoMailSuggest(defaultMailSuffix);
	  });
	$(function(){
	    $("#emails").autoMailSuggest(defaultMailSuffix);
	  });
	$(function(){
	    $("#emailForPsw").autoMailSuggest(defaultMailSuffix);
	  });
</script>

<div class="row bg">
	<div class="row ad">
		<div class="span8 left"></div>
		<div class="span4 login-panel">
			<div class="login-context">
				<div class="login-logo"></div>
				<form:form class="form-horizontal" action="signin" id="sigin_info_form" modelAttribute="loginForm" method="post">
					<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei'; font-size: 25pt;">用户登录</h1>
					<div class="hr-bg"></div>
					<div style="height: 10px"></div>
					<div class="controls">
						<h4 style="text-align: left;">邮箱地址</h4>
						<div class="controls" style="text-align: left;">
							<input type="text" id="email" name="email" placeholder="请输入您的邮箱地址" style="width: 96.17%;"> <span class="help-block" id="em"><form:errors path="email"></form:errors></span>
							<div id="emailError" class="inputError"></div>
						</div>
					</div>
					<div class="controls">
						<h4 style="text-align: left;">登录密码</h4>
						<div class="controls" style="text-align: left;">
							<input type="password" id="password" name="password" placeholder="请输入您的密码" style="width: 96.17%;"> <span class="help-block" id="pass"><form:errors path="password"></form:errors></span>
							<div id="passwordErr" class="inputError"></div>
						</div>
					</div>
					<h5 class="controls" style="text-align: left;">
					<a id="fogotPswbtn" href="<c:url value='#'></c:url>"><font color="#555">忘记密码？</font></a>
					</h5>
					<label style="clear: right;"></label>
					<div style="height:10px;"></div>
					<button class="btn btn-large btn-block btn-primary" type="submit" style="font-family: 'Microsoft YaHei';">登录</button>
				</form:form>
				<div class="register-hr-bg"></div>
				<a id="registerbtn" class="btn btn-large btn-block btn-success" style="font-family: 'Microsoft YaHei';">快速注册</a>
			</div>
		</div>
		<div class="span4 register-panel" style="display: none;">
			<div class="login-context">
				<div style="margin-top: 100px"></div>
				<form:form class="form-horizontal" style="margin-top: 10px;" action="register/common" modelAttribute="commonRegisterForm" method="post">
					<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei'; font-size: 25pt;">快速注册</h1>
					<div class="hr-bg" style="height: 30px;"></div>
					<div class="control">
						<h4 style="text-align: left;">邮箱地址</h4>
						<div class="controls" style="text-align: left;">
							<input type="text" id="emails" name="email" placeholder="请输入您的邮箱地址" style="width: 96.17%;"> <span class="help-block"><form:errors path="email"></form:errors></span>
							<div id="checkEmails" class="inputError"></div>
						</div>
					</div>
					<div class="control">
						<h4 style="text-align: left;">密码</h4>
						<div class="controls" style="text-align: left;">
							<input type="password" id="psw" name="psw" placeholder="请设置您的密码" style="width: 96.17%;"> <span class="help-block"><form:errors path="psw"></form:errors></span>
							<div id="emptyPwd" class="inputError"></div>
						</div>
					</div>
					<div class="control">
						<h4 style="text-align: left;">密码确认</h4>
						<div class="controls" style="text-align: left;">
							<input type="password" id="confirmpsw" name="confirmpsw" placeholder="请再次输入您的密码" style="width: 96.17%;"> <span class="help-block"><form:errors path="confirmpsw"></form:errors></span>
							<div id="passwordError" class="inputError"></div>
						</div>
					</div>
					<div class="control">
						<h4 style="text-align: left;">角色选择</h4>
						<div class="controls" style="text-align: left;">
							<label class="radio inline" style="font-family: 'Microsoft YaHei';"> <input type="radio" name="userType" id="teacher" value="teacher" checked>教师用户</label>
							<label class="radio inline" style="font-family: 'Microsoft YaHei';"> <input type="radio" name="userType" id="enterprise" value="enterprise">企业用户</label>
						</div>
					</div>
					<label style="clear: right;"></label>
					<button type="submit" class="btn btn-large btn-block btn-primary"  style="margin-top: 20px;font-family: 'Microsoft YaHei';">确认注册</button>
				</form:form>
				<div class="register-hr-bg"></div>
				<a id="loginbtn" class="btn btn-large btn-block btn-success" style="font-family: 'Microsoft YaHei';">用户登录</a>
			</div>
		</div>
		
		<div class="span4 forgotPsw-panel" style="display: none;">
			<div class="login-context">
				<div class="login-logo"></div>
				<form:form class="form-horizontal" action="forgotPsw" id="forgotPsw_info_form" modelAttribute="forgotPswForm" method="post">
					<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei'; font-size: 25pt;">密码找回</h1>
					<div class="hr-bg"></div>
					<div style="height: 10px"></div>
					<div class="controls">
						<h4 style="text-align: left;">您的注册邮箱地址</h4>
						<div class="controls" style="text-align: left;">
							<input type="text" id="emailForPsw" name="email" placeholder="请输入您已注册的邮箱地址" style="width: 96.17%;"> <span class="help-block" id="em"><form:errors path="email"></form:errors></span>
							<div id="emailError" class="inputError"></div>
						</div>
					</div>
					<label style="clear: right;"></label>
					<div style="height:30px;"></div>
					<button class="btn btn-large btn-block btn-primary" type="submit" style="font-family: 'Microsoft YaHei';">发送密码找回邮件</button>
				</form:form>
				<div class="hr-bg"></div>
				<a id="tologinbtn" class="btn btn-large btn-block btn-success" style="font-family: 'Microsoft YaHei';">返回登录</a>
			</div>
		</div>
	</div>
</div>
