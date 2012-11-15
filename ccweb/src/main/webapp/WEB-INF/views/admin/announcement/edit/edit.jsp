<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a href='<c:url value="/admin/teacher/announcement/list"></c:url>' ><b>教师公告</b></a><hr>
<div style="text-align: center;">
	${sessionScope.userInfo.user["email"] }<br>
<div style="text-align: center;">
	<form:form action= "edit"  method="post">
		<input type="hidden" name="id" value="${ann.id }" />
		公告标题：<input type="text" name="title" value="${ann.title }" placeholder="Title">
		<span class="help-block"><form:errors path="title"></form:errors></span>
		
		公告内容：<textarea name="content" placeholder="Content" cols="5" rows="8">${ann.content}</textarea>
		<span class="help-block"><form:errors path="content"></form:errors></span>
	
		<label style="clear: right;"></label>
		<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
		<button type="reset" class="btn">Cancel</button>
	</form:form>
</div>

</div>