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
.row-fluid .custom .user-row {
	color: #3d4f67;
}

</style>
<script type="text/javascript">

</script>
<div class="row-fluid custom round">
	<div class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>外交动态>>动态详情</h4>
	</div>
	<div class="content">
			<table width="100%" height="" border="1" cellpadding="5" cellspacing="0"   class=" <c:if test="${sessionUserInfo.role == 'teacher'}">green</c:if> <c:if test="${sessionUserInfo.role == 'user'}">blue</c:if>"   >
			<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">项目名称</td>
			    <td width="190">${technology.techName }</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">所属领域</td>
			    <td width="169">${techField.value }</td>
			  </tr>
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">完成单位</td>
			    <td width="190">${technology.department }</td>
			    <td width="103" align="center" bgcolor="#f3f3f3"> 所 有 人</td>
			    <td width="169">${technology.inventer}</td>
			  </tr>
			  
			  <tr>
			    <td align="center" bgcolor="#f3f3f3"> 联系电话</td>
			    <td>${technology.phone }</td>
			    <td align="center" bgcolor="#f3f3f3">项目类型</td>
			    <td>${technology.techType }</td>
			  </tr>

			  <tr>
			    <td align="center" bgcolor="#f3f3f3">成熟程度</td>
			    <td >${technology.maturity }</td>
			    <td align="center" bgcolor="#f3f3f3">进展程度</td>
			    <td >${technology.progress }</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">应用前景</td>
			    <td>${technology.applyArea }</td>
			    <td align="center" bgcolor="#f3f3f3">合作方式</td>
			    <td>${technology.cooperation }"</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3"> 合作要求</td>
			    <td colspan="3">${technology.demand }</td>
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
				    <td colspan="4" align="left" bgcolor="#f3f3f3">获奖情况</td>
				  </tr>
				  <tr>
				  	   <td colspan="4" align="left" >${technology.achievement }</td>
				  </tr> 
			</table>
	</div>
	<div class="content" >
		<a href='<c:url value="/admin/technology/list"></c:url>'  class="btn btn-success">返回</a>
	</div>
</div>