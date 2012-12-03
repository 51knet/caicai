<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to send message page.</h1>
<div style="text-align: center;">
	
<div style="text-align: center;">

	<form:form action="admin/message/sendMsgInfo" method="post">
		<input type="hidden" value="${uid }" name="uid" >
		信件标题：<input type="text" name="title" placeholder="Title">
		<span class="help-block"><form:errors path="title"></form:errors></span>
		
		
		信件内容：<textarea name="content" placeholder="Content" cols="5" rows="8"></textarea>
		<span class="help-block"><form:errors path="content"></form:errors></span>
		
	
		
		<label style="clear: right;"></label>
		<button type="submit" class="btn">OK</button>

	</form:form>

</div>

</div>