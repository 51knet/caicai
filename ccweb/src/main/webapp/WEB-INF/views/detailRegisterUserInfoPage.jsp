<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Select your type</h1>
<div style="text-align: center;">
	<form:form action="/ccweb/usertype" method="POST">
		<input type="radio" name="userType" value="Teacher">教师<br>
		<input type="radio" name="userType" value="Student">学生<br>
		<input type="radio" name="userType" value="Enterprise">企业<br>
		<button type="submit" class="btn">确定</button>
	</form:form>

</div>