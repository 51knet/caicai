<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
	<div  class="row">
		<h4>专利需求>>需求预览</h4>
	</div>		
		<div class="content">
			<table width="100%" height="" border="1" cellpadding="5" cellspacing="0"   class=" <c:if test="${sessionUserInfo.role == 'teacher'}">blue</c:if> <c:if test="${sessionUserInfo.role == 'user'}">yellow</c:if>"   >
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">需求名称</td>
			    <td colspan="3">${patentRequirement.title }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">所属领域</td>
			    <td> ${patentRequirement.requirementField }</td>
			    <td align="center" bgcolor="#f3f3f3"> 专利类型</td>
			    <td>${patentRequirement.patentType.typeName }</td>
			  </tr>
			    <tr>
			    <td align="center" bgcolor="#f3f3f3">合作方式</td>
			    <td> ${patentRequirement.cooperation }</td>
			    <td align="center" bgcolor="#f3f3f3"> 拟定资金</td>
			    <td>${patentRequirement.money }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">公司名称</td>
			    <td colspan="3">${patentRequirement.company }</td>
			  </tr>
			  <tr>
			   <td align="center" bgcolor="#f3f3f3">联系人</td>
			    <td>${patentRequirement.contact }</td>
			    <td align="center" bgcolor="#f3f3f3">联系电话</td>
			    <td>${patentRequirement.phone }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">传真</td>
			    <td>${patentRequirement.fax }</td>
			    <td align="center" bgcolor="#f3f3f3">联系邮箱</td>
			    <td>${ patentRequirement.email}</td>
			  </tr>
			    <tr>
			    <td colspan="4" align="left" bgcolor="#f3f3f3">需求简介</td>
				</tr>
				 <tr>
			  	   <td colspan="4" align="left" >${patentRequirement.content }</td>
			  </tr>
			</table>
		</div>
	<div class="content" >
		<a href='<c:url value="/admin/kefu/patentrequirement/list/all"></c:url>'  class="btn btn-success">返回</a>&nbsp;&nbsp;
	</div>
</div>
<script type="text/javascript">
	
</script>