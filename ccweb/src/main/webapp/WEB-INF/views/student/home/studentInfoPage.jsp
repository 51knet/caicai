<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to student info page.</h1>
<div style="text-align: center;">

	<form:form action="studentDetailInfo" method="post">

		<input type="text" name="role" placeholder="Role">
		<span class="help-block"><form:errors path="role"></form:errors></span>
		
		<input type="text" name="college" placeholder="College">
		<span class="help-block"><form:errors path="college"></form:errors></span>
		
		<label style="clear: right;"></label>
		<button type="submit" class="btn">OK</button>

	</form:form>

</div>