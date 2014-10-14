<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<h4>需求详细</h4>
	</div>
	<div class="content">
		<table width="100%" height="" border="1" cellpadding="5" cellspacing="0"  class="blue">
		 	 <tr>
			    <td align="center" bgcolor="#f3f3f3">需求标题</td>
			    <td colspan="3">${requirement.title }</td>
			  </tr>
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">需求类别</td>
			    <td width="190">${requirement.requirType.typeName}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">审核状态</td>
			    <td width="169"><c:if test="${requirement.status == 1 }">审核通过</c:if><c:if test="${requirement.status == 0 }">审核未通过</c:if></td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">发布时间：</td>
			    <td ><fmt:formatDate value="${requirement.date}" pattern="yyyy-MM-dd "/></td>
			    <td align="center" bgcolor="#f3f3f3">截止日期：</td>
			    <td >${requirement.endTime }</td>
			  </tr>
			  <tr>
			    <td  align="center" bgcolor="#f3f3f3">预投金额：</td>
			    <td >${requirement.money }</td>
			    <td  align="center" bgcolor="#f3f3f3">公司名称</td>
			    <td >${requirement.company}</td>
			  </tr>
			    <tr>
			    <td  align="center" bgcolor="#f3f3f3">发布人：</td>
			    <td >${requirement.user.id }</td>
			    <td  align="center" bgcolor="#f3f3f3">发布人邮箱：</td>
			    <td >${requirement.user.email}</td>
			  </tr>
			   <tr>
				    <td colspan="4" align="left" bgcolor="#f3f3f3">需求详情</td>
			   </tr>
			  <tr>
			  	   <td colspan="4" align="left" >${requirement.content }</td>
			  </tr>
			</table>
	</div>
	<div class="content" >
		<a href='<c:url value="/admin/kefu/requirement/list/all"></c:url>'  class="btn btn-success">返回</a>
	</div>
</div>