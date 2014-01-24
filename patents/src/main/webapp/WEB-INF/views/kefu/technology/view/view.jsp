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
<script type="text/javascript">

</script>
<div class="row-fluid custom round">
	<div class="row">
		<h4>技术详细</h4>
	</div>
	<div class="content">
			<table width="100%" height="" border="1" cellpadding="5" cellspacing="0"   class=" <c:if test="${sessionUserInfo.role == 'teacher'}">blue</c:if> <c:if test="${sessionUserInfo.role == 'user'}">yellow</c:if>"   >
			 <tr>
			    <td align="center" bgcolor="#f3f3f3">项目名称</td>
			    <td colspan="3">${technology.techName }</td>
			 </tr>
			<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">所属领域</td>
			    <td width="190">${technology.techField }</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">项目类型</td>
			    <td width="169">${technology.techType }</td>
			  </tr>
			  <tr>
			    <td  align="center" bgcolor="#f3f3f3">完成单位</td>
			    <td >${technology.department }</td>
			    <td  align="center" bgcolor="#f3f3f3">所有人</td>
			    <td >${technology.inventer}</td>
			  </tr>
		
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">成熟程度</td>
			    <td>${technology.maturity }</td>
			    <td align="center" bgcolor="#f3f3f3">进展程度</td>
			    <td>${technology.progress }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">应用前景</td>
			    <td colspan="3">${technology.applyArea }</td>
			    
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">合作方式</td>
			    <td>${technology.cooperation }</td>
			      <td align="center" bgcolor="#f3f3f3">合作要求</td>
			    <td >${technology.demand }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">项目简介</td>
			    <td colspan="3">${technology.contents }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">代理人</td>
			    <td>${patent.agency }</td>
			    <td align="center" bgcolor="#f3f3f3">代理机构</td>
			    <td>${patent.agent }</td>
			  </tr>
		      <tr>
			    <td colspan="4" align="left" bgcolor="#f3f3f3">项目简介</td>
			  </tr>
			  <tr>
			  	   <td colspan="4" align="left" >${technology.contents }</td>
			  </tr>
			   <tr>
			    <td colspan="4" align="left" bgcolor="#f3f3f3">产品优点</td>
			  </tr>
			  <tr>
			  	   <td colspan="4" align="left" >${technology.advantage }</td>
			  </tr>
			   <tr>
			    <td colspan="4" align="left" bgcolor="#f3f3f3">获奖情况：（如有专利，请注明专利名称、专利号、申请人）</td>
			  </tr>
			  <tr>
			  	   <td colspan="4" align="left" >${technology.achievement }</td>
			  </tr>
			</table>
	</div>
	<div class="content" >
		<a href='<c:url value="/admin/kefu/technology/list/all"></c:url>'  class="btn btn-success">返回</a>&nbsp;&nbsp;
	</div>
</div>