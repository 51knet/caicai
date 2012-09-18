<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to user info page.</h1>
<div style="text-align: center;">

	<form:form action="userDetailInfo" method="post">
	
		<input type="text" name="name" placeholder="name">
		<span class="help-block"><form:errors path="name"></form:errors></span>
		
		<input type="text" name="nickName" placeholder="nickName">
		<span class="help-block"><form:errors path="nickName"></form:errors></span>
		
		<input type="text" name="gender" placeholder="gender">
		<span class="help-block"><form:errors path="gender"></form:errors></span>

		<label style="clear: right;"></label>
		<button type="submit" class="btn">OK</button>
		<button type="submit" name="promote" value="promote" class="btn">Promote</button>

	</form:form>

</div>