<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="jumbotron">
		<h3 style="font-family: 'Microsoft YaHei'">注册成功！</h3>
		<hr />
		<p class="lead" style="font-family: 'Microsoft YaHei'">感谢您注册成为知识网会员，我们已向您的注册邮箱发送一封确认电子邮件，请点击确认邮件中的链接激活您的账号。</p>
		<p>
			<a class="btn btn-success btn-large" href="http://mail.${hrefString}" style="font-family: 'Microsoft YaHei'">进入邮箱 »</a>
		</p>
	</div>
</div>