<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>个人专利</h4>
	</div>
	<div class="content">
		<a  style="float: right;" href='<c:url value="/admin/patent/new"></c:url>' class="btn">添加新专利</a><br><br>
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr><th width="20%">申请号</th><th>名称</th><th  width="20%">申请日</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="patent">
					<tr>
						<td ><a href='<c:url value="/admin/patent/view/${patent.patentNum }"></c:url>' >${patent.patentNum }</a></td><td >${patent.patentName }</td>
						<td >${patent.applicationDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>
</div>