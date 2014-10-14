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


</style>
<script type="text/javascript">

</script>
<div class="row-fluid custom round">
	<div class="row">
		<h4>订单详细</h4>
	</div>
	<div class="content">
		<table width="100%"  border="1" cellpadding="5" cellspacing="0"  class="blue">
			<tr >
				<td colspan="4" align="left" bgcolor="#f3f3f3">订单信息：</td>
			</tr>
			<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">订单编号</td>
			    <td width="190">${order.id}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">订单状态</td>
			    <td width="169">${order.status }</td>
			  </tr>
			  	<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">下单日期</td>
			    <td width="190"><fmt:formatDate value="${order.startTime }" pattern="yyyy-MM-dd hh:mm" /></td>
			    <td width="103" align="center" bgcolor="#f3f3f3">结束日期</td>
			    <td width="169"><fmt:formatDate value="${order.endTime }" pattern="yyyy-MM-dd hh:mm" /></td>
			  </tr>
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">下单人</td>
			    <td width="190">${order.user.name}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">联系邮箱</td>
			    <td width="169">${order.user.email}</td>
			  </tr>
				 <tr >
					<td colspan="4" align="left" bgcolor="#f3f3f3">投资项目信息：</td>
				</tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">项目名称</td>
			    <td><a href='<c:url value="/admin/kefu/projects/view/${projects.id }"></c:url>' >${projects.projectName }</a></td>
			    <td align="center" bgcolor="#f3f3f3">项目行业</td>
			    <td>${projects.industry }</td>
			  </tr>
				<tr>
					<td  align="center" bgcolor="#f3f3f3">融资金额：</td>
					<td  >${projects.totalMoney} 万</td>
					<td  align="center" bgcolor="#f3f3f3">已融金额：</td>
					<td  >${projects.currentMoney} 万</td>
				</tr>
			</table>
	</div>
	<div class="content" >
		<a href='<c:url value="/admin/kefu/order/list"></c:url>'  class="btn btn-success">返回</a>&nbsp;&nbsp;
	</div>
</div>