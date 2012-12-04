<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a href='<c:url value="/admin/teacher/resource/list"></c:url>'><b>资源管理</b></a>>>类别管理<hr>
<div style="text-align: center;">
	<!-- <a href='<c:url value="/admin/teacher/resource/type/add"></c:url>'>添加新类别</a>&nbsp;&nbsp;&nbsp;&nbsp;<br> -->
	<table class="table table-bordered" style="width:500px;">
		<thead><tr><th colspan="2">全部类别</th></tr></thead>
		<tbody>
			<tr><td colspan="2"> 
			 <a href='<c:url value="/admin/teacher/resource/add"></c:url>' class="btn">添加资源</a>&nbsp;&nbsp;
				<form  method="post" action="type/addInfo" class="form-inline pull-right">
					<input type="text" name="typeName" placeholder="输入你要新建的分类的名字">
					<input type="submit" class="btn btn-primary" value="新建分类">
				</form></td></tr>
			<tr><td>类别名称</td><td>详细操作</td></tr>
			<c:forEach items="${list}" var="l">
				<tr><td align="left">${l.typeName}</td>
				<td><a href='<c:url value="/admin/teacher/resource/type/dele?id=${l.id}"></c:url>' class=btn> 删除</a></td></tr>
			</c:forEach>
		</tbody>
	</table>
</div>