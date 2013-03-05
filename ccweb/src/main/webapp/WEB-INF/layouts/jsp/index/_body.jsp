<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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

.container-fluid .row-fluid .row.login {
	margin: 0px auto;
	height: 750px;
	background: url(<c:url value='/resources/img/default/index/login_panel.png' ></c:url>) center -120px no-repeat;
}

.span8.left {
	width: 988px;
}

.span4.login-panel {
	width: 441px;
	height: 602px;
	margin-left: 0px;
	margin-top: 70px;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_logo.png' ></c:url>) center no-repeat;
}

.span4.login-context {
	width: 350px;
	height: 100%;
	background: url(<c:url value='/resources/img/default/index/hr_bg.png' ></c:url>) center no-repeat;
}

.span4.register-panel {
	width: 441px;
	height: 602px;
	margin-left: 0px;
	margin-top: 70px;
	text-align: center;
	background: url(<c:url value='/resources/img/default/index/login_logo.png' ></c:url>) center no-repeat;
}

.span4.register-context {
	width: 350px;
	height: 100%;
	background: url(<c:url value='/resources/img/default/index/hr_bg.png' ></c:url>) center no-repeat;
}

.form-horizontal {
	margin: 150px 0 20px;
}

.form-horizontal .control-label {
	float: left;
	width: 75px;
	padding-top: 5px;
	text-align: right;
}
.form-horizontal .controls {
	margin-left: 0px;
}
</style>

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
</script>

<div class="row bg">
	<div class="row ad">
		<div class="row login">
			<div class="span8 left"></div>
			<div class="span4 login-panel">
				<div class="span4 login-context" style="margin-left: 45px;">
				<form:form class="form-horizontal" action="signin" id="sigin_info_form" modelAttribute="loginForm" method="post">
					<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei';font-size: 25pt;">用户登录</h1>
					<div class="control-group">
						<h4 style="text-align: left;">邮箱地址</h4>
						<div class="controls" style="text-align: left;">
							<input type="text" id="email" name="email" placeholder="请输入您的邮箱地址" style="width:335px;"> <span class="help-block" id="em"><form:errors path="email"></form:errors></span>
							<div id="emailError"></div>
						</div>
					</div>
					<div class="control-group">
						<h4 style="text-align: left;">登录密码</h4>
						<div class="controls" style="text-align: left;">
							<input type="password" id="password" name="password" placeholder="请输入您的密码" style="width:335px;"> <span class="help-block" id="pass"><form:errors path="password"></form:errors></span>
							<div id="passwordErr"></div>
						</div>
					</div>
					<label style="clear: right;"></label>
					<button class="btn btn-large btn-block btn-primary" type="submit" style="margin-top: 30px">登录</button>
				</form:form>
				<a id="registerbtn" class="btn btn-large btn-block btn-success" style="margin-top: 20%;">快速注册</a>
				</div>
			</div>
			<div class="span4 register-panel" style="display:none;">
				<div class="span4 register-context" style="margin-left: 45px;">
				<form:form class="form-horizontal" action="register/common" modelAttribute="commonRegisterForm" method="post">
					<h1 class="form-signin-heading" style="font-family: 'Microsoft YaHei';font-size: 25pt;">快速注册</h1>
					<div class="control">
						<h4 style="text-align: left;margin-top: 20px;">邮箱地址</h4>
						<div class="controls" style="text-align: left;">
							<input type="text" id="emails" name="email" placeholder="请输入您的邮箱地址" style="width:335px;"> <span class="help-block"><form:errors path="email"></form:errors></span>
							<div id="checkEmails"></div>
						</div>
					</div>
					<div class="control">
						<h4 style="text-align: left;">密码</h4>
						<div class="controls" style="text-align: left;">
							<input type="password" id="psw" name="psw" placeholder="请设置您的密码" style="width:335px;"> <span class="help-block"><form:errors path="psw"></form:errors></span>
							<div id="emptyPwd"></div>
						</div>
					</div>
					<div class="control">
						<h4 style="text-align: left;">密码确认</h4>
						<div class="controls" style="text-align: left;">
							<input type="password" id="confirmpsw" name="confirmpsw" placeholder="请再次输入您的密码" style="width:335px;"> <span class="help-block"><form:errors path="confirmpsw"></form:errors></span>
							<div id="passwordError"></div>
						</div>
					</div>
					<label style="clear: right;"></label>
					<button type="submit" class="btn btn-large btn-block btn-primary" style="margin-top: 20px">确认注册</button>
				</form:form>
				<a id="loginbtn" class="btn btn-large btn-block btn-success" style="margin-top: 30px;">用户登录</a>
				</div>
			</div>
		</div>
	</div>
</div>
