<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a href='<c:url value="/admin/teacher/resource/list"></c:url>' ><b>资源管理</b></a><hr>
<div style="text-align: center;">
	<table class="table table-bordered">
		<tbody>
		<tr><td colspan="5">
			<a href='<c:url value="/admin/teacher/resource/new"></c:url>' class="btn">添加资源</a>&nbsp;&nbsp;
			<a href='<c:url value="/admin/teacher/resource/type/list"></c:url>'  class="btn">类别管理</a><br></td></tr>
		<tr><td>文件名称</td><td>发布时间</td><td>文件描述</td><td>文件类型</td><td>资源操作</td></tr>
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
		<tfoot>
    <tr><td colspan="5">
        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
   		 </td></tr>
	</tfoot>
	</table>
	<br/>
</div>