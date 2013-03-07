<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="jumbotron">
		<h3 style="font-family: 'Microsoft YaHei'">密码找回邮件发送！</h3>
		<hr />
		<p class="lead" style="font-family: 'Microsoft YaHei'">您的密码找回请求已接受，我们已向您的注册邮箱发送一封确认电子邮件，请点击邮件中的链接修改您的密码。</p>
		<p>
			<a class="btn btn-success btn-large" href="http://www.${hrefString}" style="font-family: 'Microsoft YaHei'">进入邮箱 »</a>
		</p>
	</div>
</div>