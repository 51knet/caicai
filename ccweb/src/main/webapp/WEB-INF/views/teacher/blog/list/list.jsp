<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
		<h5>博客</h5>
	</div>
	<div class="row ">
	<table class="table">
		<thead>
			<tr><th width="80%">标题</th><th>发表日期</th></tr>
		</thead>
		<tbody>
			<c:forEach var="blogPost" items="${page.content}">
			<tr>
				<td><a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a></td>
				<td>
				<fmt:formatDate value="${blogPost.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr><td colspan="2">
				<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			</td></tr>
		</tfoot>
</table>
</div>
</div>