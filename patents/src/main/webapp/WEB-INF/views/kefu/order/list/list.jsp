<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
.margin_right{
	margin-right: 15px;
	font-size: 14px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>订单列表</h4>
	</div>
	<div class="content" >
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr>
					<th width="10%">订单号</th>
					<th>下单人</th>
					<th  width="20%">下单日期</th>
					<th  align="center" >下单项目</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/kefu/order/view/${page.id }"></c:url>' >${page.id }</a></td>
						<td ><a href='<c:url value="/admin/kefu/user/${page.user.id}/order/list"></c:url>' >${page.user.name}</a></td>
						<td ><fmt:formatDate value="${page.startTime }" pattern="yyyy-MM-dd hh:mm" /></td>
						<td  ><a href='<c:url value="/admin/kefu/projects/view/${page.projects.id }"></c:url>' >${page.projects.projectName }</a></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${searchParam != null }">
				  <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
			 	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		
	</div>
</div>

<script type="text/javascript">



</script>