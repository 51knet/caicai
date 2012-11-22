<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>

<div class="row-fluid custom round">
	
	<div class="row">
		<h5>教师资源</h5>
	</div>
	<div class="row">
		<table class="table">
		<thead><tr><th >文件名称</th><th >发布时间</th><th >文件描述</th><th >文件类型</th><th >下载</th></tr></thead>
			<tbody>
			<c:forEach items="${page.content}" var="page">
				<tr><td align="left">${page.name}</td>
				<td align="center">${page.date}</td>
				<td>
					<c:choose>
						<c:when test="${page.description != null && page.description !=''}">${page.description}</c:when>
						<c:otherwise>无描述</c:otherwise>
					</c:choose>
				</td>
				<td>${page.resourceType.typeName}</td>
				<td><a href="${page.savePath}">下载</a></td></tr>
			</c:forEach>
			</tbody>
			<tfoot>
	    <tr><td colspan="5">
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
	</div>
</div>