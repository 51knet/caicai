<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to register page.</h1>
<div style="text-align: center;">
	Sorry, you are not authenticated users, please click and re-send the confirm mail:
	<hr />
	<a href='<c:url value="/sendMail"></c:url>' class="btn">发送验证邮件</a>
</div>