<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h5>资源详细</h5>
	</div>
	<!-- 
	<div class="row">
		<table class="table ">
			<tbody>
				<tr><td align="left" width="12%">资源名称：</td><td align="left">${resource.name}</td></tr>
				<tr><td align="left" width="12%">资源类型：</td><td align="left">${resource.resourceType.typeName}</td></tr>
				<tr><td align="left">发布时间：</td><td align="left">${resource.date}</td></tr>
				<tr><td align="left">资源描述：</td><td align="left">${resource.description}</td></tr>
				<tr><td align="left">资源下载：</td><td align="left"><a href='<c:url value="/resource/download/${page.id }"></c:url>'>下载</a></td></tr>	
			</tbody>
		</table>
	</div>
	 -->
	<div class="row">
		<div class="span12" style="text-align:left;">
			<h4 >${resource.name}</h4>
			<span >类别: ${resource.resourceType.typeName}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span >发布时间: ${resource.date}</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href='<c:url value="/resource/download/${resource.id }"></c:url>'>下载</a>
		</div>
		<div class="clearfix"></div>
		<hr />
		<div class="span12" style="margin-left: 5px; margin-right: 5px;">
			<c:choose>
				<c:when test="${resource.description != null && resource.description !=''}">${resource.description}</c:when>
				<c:otherwise>无描述</c:otherwise>
			</c:choose>
		</div>
		<div class="clearfix"></div>
		<hr />
	</div>

</div>

