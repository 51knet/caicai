<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(document).ready(function (){
	
	
	$("#email").blur(function(){
	var email=$("#email").val();
	if(email==""){
		$("#errorMessage").html("");
		$("#errorMessage").html('<font size="3" color="red">邮箱不能为空</font>');
		return false;
	}
	var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;//邮箱验证正则表达式。 
	if(!reg.test(email)){                             //验证邮箱格式是否正确
		$("#errorMessage").html("");
		$("#errorMessage").html('<font size="3" color="red">输入的邮箱格式不正确</font>');
		return false;
	}
	$.ajax({
		  type: "post",
		  url: "<c:url value='/checkemailajax'/>",
		  data: "email="+email,
		  dataType:"text",
		  success:function(number){
				if(number=='1'){
				$("#errorMessage").html("");
				$("#errorMessage").html('<font size="3" color="red">此账号已存在</font>');
				return false;
				}else{
					$("#errorMessage").html("");
					return false;
				}
			}
		});
	});
	$("#psw").blur(function(){
		var psw=$("#psw").val();
		if(psw==""){
			$("#errorMessage").html("");
			$("#errorMessage").html('<font size="3" color="red">密码不能为空</font>');
			return false;
		}
	});
	$("#confirmpsw").blur(function(){
		var psw=$("#psw").val();
		var confirmpsw=$("#confirmpsw").val();
		if(confirmpsw==""){
			$("#errorMessage").html("");
			$("#errorMessage").html('<font size="3" color="red">确认密码不能为空</font>');
			return false;
		}
		if(psw!=confirmpsw){
			$("#errorMessage").html("");
			$("#errorMessage").html('<font size="3" color="red">两次输入密码不一致</font>');
			return false;
		}
	});
	
});
</script>
<div class="container">
	<div class="row">
		<div class="span12">
			<div class="heading-banner" style="margin-top: 60px;">
				<h1>注册</h1>
			</div>
			<div id="errorMessage"></div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div style="background: white; border-right: solid #d9d9d9 1px;" class="span6">
			<div style="padding-top: 20px;">
				<form:form action="/courses/signup" id="signup_form" modelAttribute="signupForm" method="post">
					<div class="control-group">
						<label for="signup-email">邮箱</label>
						<div class="controls">
							<input type="text" name="email" id="email" placeholder="邮箱地址">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="control-group">
						<label for="signup-password">密码</label>
						<div class="controls">
							<input type="password" name="psw" id="psw" placeholder="密码">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="control-group">
						<label for="signup-password">确认密码</label>
						<div class="controls">
							<input type="password" name="confirmpsw" id="confirmpsw" placeholder="确认密码">
							<span class="help-block"></span>
						</div>
					</div>
					<div>
						<div class="control-group">
							<div class="controls">
								<button type="submit"  class="btn btn-success">注册</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div style="margin-left: -1px;" class="span5">
			<div style="padding: 20px;">
				<h3>已经拥有一个账号？</h3>
				<p>
					立刻<a href="<c:url value="signin" />" class="internal-home">登录</a>！
				</p>
			</div>
		</div>
	</div>
</div>
