<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<a href='<c:url value="/admin/teacher/resource/list"></c:url>' ><b>资源管理</b></a><hr>
<div style="text-align: center;">
	<table class="table table-bordered">
	<thead><tr><th colspan="5">全部资源</th></tr></thead>
		<tbody>
		<tr><td colspan="5">
			<a href='<c:url value="/admin/teacher/resource/add"></c:url>' class="btn">添加资源</a>&nbsp;&nbsp;
			<a href='<c:url value="/admin/teacher/resource/type"></c:url>'  class="btn">类别管理</a><br></td></tr>
		<tr><td>文件名称</td><td>发布时间</td><td>文件描述</td><td>文件类型</td><td>详细操作</td></tr>
		<c:forEach items="${page.content}" var="page">
			<tr><td align="left">${page.name}</td>
			<td align="center">${page.date}</td>
			<td>${page.description}</td>
			<td>${page.resourceType.typeName}</td>
			<td><a href='<c:url value="/admin/teacher/resource/dele?id=${page.id }"></c:url>'> 删除</a> | <a href="${page.savePath}">下载</a></td></tr>
		</c:forEach>
		</tbody>
		<tfoot>
    <tr><td colspan="5">
        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
   		 </td></tr>
	</tfoot>
	</table>
	<br/>
		<!-- 	 <div class="pagination">
				<c:if test="${page.totalPages > 0}">
					<c:set var="prev" value="${page.number-1}" scope="page"></c:set>
					<c:set var="next" value="${page.number+1}" scope="page"></c:set>
					<ul>
						<c:choose>
							<c:when test="${page.hasPreviousPage()}">
								<li><a href='<c:url value="/admin/teacher/resource/list?pageNumber=${prev}" />'>Prev</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Prev</a></li></c:otherwise>
						</c:choose>
					    <li><a href="#" class="active">${page.number+1}/${page.totalPages}</a></li>
					    <c:choose>
							<c:when test="${page.hasNextPage()}">
								<li><a href='<c:url value="/admin/teacher/resource/list?pageNumber=${next}" />'>Next</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Next</a></li></c:otherwise>
						</c:choose>
					    
					  </ul>
				</c:if> 
			</div>
		-->
</div>