<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(document).ready(function() {

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
		<h4>提现申请</h4>
	</div>
	<div class="content">
		<!-- <div style="padding-bottom: 10px; text-align: right;">  
			<form class="navbar-form" action="<c:url value="/admin/caicai/resource/search"></c:url>" method="post">
					 <input type="text" name="searchParam" class="span5" placeholder="输入公告标题搜索"  value="${searchParam }">
					 <button type="submit" class="btn" style=" margin-top:4px;font-family:Arial,'Microsoft YaHei'; color: #808080; ">标题搜索</button>
			</form>
		</div> -->
		<div style="text-align: right;">
				<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th  align="center" >申请人</th>
						<th  align="center" >申请金额</th>
						<th  align="center" >申请日期</th>
						<th  align="center" >邮箱</th>
						<th  align="center">申请原因</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align= "center">${page.user.name }</td>
							<td align="center" >${page.sum} 元</td>
							<td align="center">
								<fmt:formatDate value="${page.date }" pattern="yyyy-MM-dd HH:mm" />
							</td>
							<td align= "center">${page.user.email}</td>
							<td align="left">${page.content }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
</div>
