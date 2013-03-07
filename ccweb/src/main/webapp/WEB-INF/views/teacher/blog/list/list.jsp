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
	margin: 5px 25px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 40px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>博客</h4>
	</div>
	<div class="row1 ">
	<table style="margin-top: 10px;margin-left: -16px;"  cellpadding="4">
		<thead>
			<tr><td width="80%"><h5>标题</h5></td><td><h5>发表日期</h5></td></tr>
		</thead>
		<tbody>
			<c:forEach var="blogPost" items="${page.content}">
			<tr>
				<td class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a></td>
				<td class="bb">
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