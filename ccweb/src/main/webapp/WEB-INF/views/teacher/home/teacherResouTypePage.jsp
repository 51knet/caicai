<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher resource type  page.</h1>
<div style="text-align: center;">
	<a href="teacherResouTypeAdd">添加新类别</a>&nbsp;&nbsp;&nbsp;&nbsp;<br>
	<table width="500" border="1" cellspacing="0" cellpadding="5">
		<tr><td>类型名称</td><td>详细操作</td></tr>
		<c:forEach items="${list}" var="l">
			<tr><td align="left">${l.typeName}</td>
			<td><a href="teacherResouTypeDele?id=${l.id}"> 删除</a></td></tr>
		</c:forEach>
	</table>
	<br/>

</div>