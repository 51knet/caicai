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
.row-fluid.custom .content{
	margin: 20px 40px;

}
</style>


<div class="row-fluid custom round">
	<div  class="row" >
		<h4>申请信息</h4>
	</div>
	<div class="content">
		<div style="text-align: right;">
			<br>
			<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
				<thead><tr>
						<th  align="center" >标题</th>
						<th  align="center" >申请人</th>
						<th  align="center" >电话</th>
						<th  align="center">申请状态</th>
						<th  align="center" >拒绝原因</th>
						<th  align="center" width="20%" >申请时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="center"><a href='<c:url value="/admin/caicai/authentication/view/${page.id }"></c:url>'>${page.title}</a></td> 
							<td align="center">${page.user.name}</td> 
							<td align="center">${page.user.fix_phone}</td> 
							<td align="center" >
								<c:if test="${page.status=='pass' }">通过</c:if>
								<c:if test="${page.status=='submit' }">审核中</c:if>
								<c:if test="${page.status=='refuse' }">未通过</c:if>
							</td>
							<td align="center" >
								${page.reason }
							</td>
							<td  align="center">
								<fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
</div>