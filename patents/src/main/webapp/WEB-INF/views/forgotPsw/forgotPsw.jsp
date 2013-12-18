<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(document).ready(function (){
	
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
				<h1>密码重置</h1>
			</div>
			<div id="errorMessage"></div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div style="background: white; border-right: solid #d9d9d9 1px;" class="span6">
			<div style="padding-top: 20px;">
				<form:form action="/ccweb/resetPsw" id="reset_psw_form" modelAttribute="resetPswForm" method="post">
					<input name="id" type="hidden" value="${id}">
					<div class="control-group">
						<label for="signup-password">新密码</label>
						<div class="controls">
							<input type="password" name="psw" id="psw" placeholder="密码"> <span class="help-block"></span>
						</div>
					</div>
					<div class="control-group">
						<label for="signup-password">确认密码</label>
						<div class="controls">
							<input type="password" name="confirmpsw" id="confirmpsw" placeholder="确认密码"> <span class="help-block"></span>
						</div>
					</div>
					<div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-success">提交</button>
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
					立刻<a href="<c:url value="/" />" class="internal-home">登录</a>！
				</p>
			</div>
		</div>
	</div>
</div>
