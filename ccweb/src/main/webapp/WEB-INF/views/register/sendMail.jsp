<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="jumbotron">
		<h3 style="font-family: 'Microsoft YaHei'">账号未激活</h3>
		<hr />
		<p class="lead" style="font-family: 'Microsoft YaHei'">对不起，您的账号尚未激活，请您至您的注册邮箱点击注册验证邮件中的链接进行激活，如果您没有收到验证邮件，请点击按钮重新发送验证邮件。</p>
		<p>
			<a class="btn btn-primary btn-large" href='<c:url value="/sendMail"></c:url>' style="font-family: 'Microsoft YaHei'">发送验证邮件</a>
		</p>
	</div>
</div>