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
		<h5>个人专利</h5>
	</div>
	<div class="row">
		<table class="table">
			<thead>
				<tr><th width="15%">发明人</th><th>专利名称</th><th>专利类型</th><th>专利申请号</th></tr>
			</thead>
			<tbody>
				<c:forEach var="patent" items="${page.content}">
					<tr>
						<td >${patent.inventer}</td>
						<td >${patent.name}</td>
						<td >${patent.type}</td>
						<td >${patent.number}</td>
					</tr>
				</c:forEach>
			</tbody>
		<tfoot>
	    <tr><td colspan="4">
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
	</div>
</div>