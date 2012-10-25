<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to self url set page.</h1>
<div style="text-align: center;">

	<form:form method="post">

		<input type="text" name="url"
			value="${sessionScope.userInfo.user.self_url }" placeholder="url">
		<span class="help-block"><form:errors path="url"></form:errors></span>

		<label style="clear: right;"></label>
		<button type="submit" class="btn">OK</button>

	</form:form>

</div>