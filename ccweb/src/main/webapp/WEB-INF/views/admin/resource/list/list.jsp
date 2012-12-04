<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row-fluid custom round">
	<div class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/resource/list"></c:url>' ><b>资源管理</b></a><hr>
		<div style="text-align: right;">
			<a  style="margin-bottom: 10px;" href='<c:url value="/admin/teacher/resource/new"></c:url>' class="btn">添加资源</a>&nbsp;&nbsp;
					<a  style="margin-bottom: 10px;" href='<c:url value="/admin/teacher/resource/type/list"></c:url>'  class="btn">类别管理</a><br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr><th>文件名称</th><th>发布时间</th><th>文件描述</th><th>文件类型</th><th>操作</th></tr>
				</thead>
				<tbody>
				<c:forEach items="${page.content}" var="page">
					<tr><td align="left">${page.name}</td>
					<td align="center">${page.date}</td>
					<td>
						<c:choose>
							<c:when test="${(page.description!=null) && (page.description != '')}">${page.description}</c:when>
							<c:otherwise>无描述</c:otherwise>
						</c:choose>
					</td>
					<td>${page.resourceType.typeName}</td>
					<td><a href='<c:url value="/admin/teacher/resource/destory/${page.id }"></c:url>'> 删除</a> 
						| <a href='<c:url value="/resource/download/${page.id }"></c:url>'>下载</a></td></tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="row"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>
	</div>
</div>		