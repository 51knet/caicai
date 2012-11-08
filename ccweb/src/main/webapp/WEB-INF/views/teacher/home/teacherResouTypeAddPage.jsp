<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher resource type page.</h1>
<div style="text-align: center;">

	<div style="text-align:center;">
		<div style="width:560px; text-align:left;">
			<form:form action="addInfo" method="post">  
				类别名称：<input type="text" name="typeName" placeholder="TypeName" />&nbsp;<span style="color:red; font-size:14px;">类别名称不能为空！</span>
				<span class="help-block"><form:errors path="typeName"></form:errors></span>
				<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
				<button type="reset" class="btn">Cancel</button>
			</form:form>
		</div>
	</div>

</div>