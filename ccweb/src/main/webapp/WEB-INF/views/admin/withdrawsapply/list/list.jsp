<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#withdraws_form").submit(function(){
		return checkEmptyAjax("withdraws_form","<c:url value='/admin/withdraws/createWithdrawsAjax'></c:url>");
	});
});
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
		<h4>账户管理>申请提现</h4>
	</div>
	<div class="content">
		<div style="text-align: right;">
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th  align="center" width="20%">申请金额</th>
						<th  align="center" width="20%">申请日期</th>
						<th  align="center">申请原因</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="center" >${page.sum} 元</td>
							<td align="center">
								<fmt:formatDate value="${page.date }" pattern="yyyy-MM-dd HH:mm" />
							</td>
							<td align="left">${page.content }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
	<div class="content">
		<form action="create" method="post"  id="withdraws_form" >  
			<div class="control-group" id="sum">
				<div class="controls">
					申请金额：<input type="text" name="sum" placeholder="申请金额" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"> 元 <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="content">
				<div class="controls">
					申请原因：<textarea style="width: 400px; height: 100px;" name="content" placeholder="申请原因"></textarea><span class="help-inline"></span>
				</div>
			</div>
			<div class="pull-left" style="margin-left: 70px;">
				<button type="submit" class="btn btn-success" >申请</button>&nbsp;&nbsp;
				<button type="reset" class="btn" >取消</button>
			</div>
		</form>
	</div>
</div>

