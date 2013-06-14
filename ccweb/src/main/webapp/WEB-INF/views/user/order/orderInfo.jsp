<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">

</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
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
	<div  class="row" >
		<h4>订单管理</h4>
	</div>
	<div class="content">
		<div style="text-align: right;">
			<table <c:if test="${sessionUserInfo== user }"> class="blue" </c:if> <c:if test="${sessionUserInfo != user }"> class="yellow" </c:if>  id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th  align="center">订单号</th>
						<th  align="center">订单状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="center">${page.id}</td>
							<td align="center">${page.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
</div>

