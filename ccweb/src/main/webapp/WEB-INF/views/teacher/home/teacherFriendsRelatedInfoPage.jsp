<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome to teacher home page.</h1>
<div style="text-align: center;">

	Welcome to teacher home page.<br>
	
	<a href='<c:url value="/admin/teacher/resource/add"></c:url>'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href='<c:url value="/admin/teacher/resource/type"></c:url>'></a><br>
	<!-- 
		<table width="500" border="1" cellspacing="0" cellpadding="5">
			<tr><td>文件名称</td><td>发布时间</td><td>文件描述</td><td>文件类型</td><td>详细操作</td></tr>
			<c:forEach items="${list}" var="l">
				<tr><td align="left">${l.name}</td>
				<td align="center">${l.date}</td>
				<td>${l.description}</td>
				<td>${l.resourceType.typeName}</td>
				<td><a href='<c:url value="/admin/teacher/resource/dele?id=${l.id }"></c:url>'> 删除</a> | <a href="${l.savePath}">下载</a></td></tr>
			</c:forEach>
		</table>
	 -->
	<br/>
	<a href="">发送好友请求人数：<span style="color:red;"><b>${count}人</b></span>&nbsp;&nbsp;点击查看详细</a>
	<br><hr>
	<a href='<c:url value="/admin/teacher/friendsRelated/add"></c:url>' >发送好友添加请求</a>

</div>