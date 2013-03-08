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
.row-fluid.custom .row2 {
	margin: 10px 40px;
	border-bottom: solid #cccccc 1.5px;
}
</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>资源详细</h4>
	</div>
	<div class="row1">
		<div  style="text-align:left;">
		<label ><b style="font-size: 16px;margin-right: 10px;">${resource.fileName}}</b >发布时间:${resource.date}</label>
		<div >类别: <span style="font-size: 14px; color:#80b029"  > <b>${resource.resourceType.typeName}</b> </span>
		<a  href='<c:url value="/resource/download/${resource.id }"></c:url>'>下载</a>
		</div> 
		</div>
		<div  style="margin-top:10px;text-align: center;">
			<c:choose>
				<c:when test="${resource.resourceDesc != null && resource.resourceDesc!=''}"> ${resource.resourceDesc} </c:when>
				<c:otherwise>无描述</c:otherwise>
			</c:choose>
		</div>
		<div class="row2" style="width: 680px;margin-left: -2px;"><span>${comment.content} </span></div>
	</div>

</div>

