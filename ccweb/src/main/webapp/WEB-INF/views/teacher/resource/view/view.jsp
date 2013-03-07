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
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 10px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>资源详细</h4>
	</div>

	<div class="row1">
		<div  style="text-align:left;">
			<h5 >${resource.fileName}</h5>
			<span style="font-size: 16px;color: black;">类别: ${resource.resourceType.typeName}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="margin-left:360px;font-size: 16px;color: black;">发布时间: ${resource.date}</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href='<c:url value="/resource/download/${resource.id }"></c:url>'>下载</a>
		</div>
		<div class="bb"></div>
		<div  style="margin-top:10px;text-align: center;">
			<c:choose>
				<c:when test="${resource.resourceDesc != null && resource.resourceDesc!=''}"> ${resource.resourceDesc} </c:when>
				<c:otherwise>无描述</c:otherwise>
			</c:choose>
		</div>
		<div class="bb"></div>
	</div>

</div>

