<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#t").focus(function() {
		$(".help-inline").html("");
	});
	$("#c").focus(function() {
		$(".help-inline").html("");
	});
});

</script>
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
	<div  class="row" >
		<h4>充值卡管理</h4>
	</div>
	<div class="content">
		<div style="padding-bottom: 10px; text-align: right;">  
		<a href='<c:url value="/admin/caicai/recharge/create"></c:url>' class="btn pull-left">创建充值卡</a>
			<form class="navbar-form" action="<c:url value="/admin/caicai/recharge/search"></c:url>" method="post">
				<input type="text" name="searchParam" class="span5" placeholder="输入充值卡id搜索"  value="${searchParam }">
				<button type="submit" class="btn" style=" margin-top:4px;font-family:Arial,'Microsoft YaHei'; color: #808080; ">id搜索</button>
			</form>
		</div>
		<div style="text-align: left">
			<table class="blue" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th  align="center">充值卡id</th>
						<th  align="center" width="20%">金额</th>
						<th  align="center" width="30%">创建时间</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${rechargeListCount >=0 }">
							<c:if test="${rechargeListCount == 0 }">
								<tr><td colspan="5" align="left">未搜索到</td></tr>
							</c:if>
							<c:forEach items="${rechargeList }" var = "recharge">
								<tr>
									<td align="center" >${recharge.cardid}</td>
									<td align="center" >${recharge.price} 元</td>
									<td align="center"><fmt:formatDate value="${recharge.date}" pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${page.content}" var="page">
								<tr>
									<td align="center" >${page.cardid}</td>
									<td align="center" >${page.price} 元</td>
									<td align="center"><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
</div>
