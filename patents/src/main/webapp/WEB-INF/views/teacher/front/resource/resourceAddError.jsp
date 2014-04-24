<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher resource add page.</h1>
<div style="text-align: center;">
	${user.user["email"] }<br>
<div style="text-align:center;">
<div style="width:560px; text-align:left;">
	<a href="javascript:history.go(-1);">单个文件出现大于200M的数据，请点击返回从新添加</a> 
</div>
</div>

</div>