<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="vertical-align: middle;text-align: center;">
	<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url>
	<img width="90px" height="90px" src="${avatar_url}" style="margin: 15px 0px;">
</div>
<div style="text-align: center;">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>我的博文</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="<c:url value="/admin/blog/list"></c:url>">全部博文</a></td>
			</tr>
			<tr>
				<td><a href="<c:url value="/admin/blog/list?type=draft"></c:url>">草稿箱</a></td>
			</tr>
			<tr>
				<td><a href="<c:url value="/admin/blog/list?type=garbage"></c:url>">回收站</a></td>
			</tr>
		</tbody>
	</table>

</div>