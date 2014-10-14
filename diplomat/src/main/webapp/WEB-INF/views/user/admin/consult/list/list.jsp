<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid .custom .user-row {
	color: #3d4f67;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>

<div class="row-fluid custom round">
	<div class="row user-row">
		<h4>咨询订单</h4>
	</div>
	<div class="content">
		<table class=" blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr><th>咨询标题</th><th>咨询专家</th><th  width="20%">日期</th><th width="12%">操作</th></tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/consult/order/view/${page.id }"></c:url>' >${page.title }</a></td>
						<td >${page.teacher.name }</td>
						<td >${page.date }</td>
						
						<td align="center"><a href='<c:url value="/admin/consult/order/view/${page.id}"></c:url>'>查看</a> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>
</div>
