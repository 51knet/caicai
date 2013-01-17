<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="container">
	<div class="row">
		<div class="span12">
			<div class="heading-banner" style="margin-top: 60px;">
				<h1>登录</h1>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div style="background: white; border-right: solid #d9d9d9 1px;" class="span6">
			<div style="padding-top: 20px;">
				<form:form action="/courses/signin" modelAttribute="loginForm" method="post">
					<div class="control-group">
						<label for="signin-email">邮箱</label>
						<div class="controls">
							<input type="text" name="email" placeholder="邮箱地址">
							<span class="help-block"><form:errors path="email"></form:errors></span>
						</div>
					</div>
					<div class="control-group">
						<label for="signin-password">密码</label>
						<div class="controls">
							<input type="password" name="password" placeholder="密码">
							<span class="help-block"><form:errors path="password"></form:errors></span>
						</div>
					</div>
					<div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-success">登录</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div style="margin-left: -1px;" class="span5">
			<div style="padding: 20px;">
				<h3>没有账号？</h3>
				<p>
					立刻<a href="#" class="internal-home">注册</a>！
				</p>
			</div>
		</div>
	</div>
</div>
