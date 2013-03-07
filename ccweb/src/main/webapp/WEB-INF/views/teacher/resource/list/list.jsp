<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
.nar {
	background-color:#ccdfa8; 
	width:100%; 
	font-size: 14px;
	color: #f7f7f7;
}
</style>
<div class="row-fluid custom round">
	
	<div class="row">
		<h4>教师资源</h4>
	</div>
	<div class="row1">
		<table  width="100%" style="margin-top: 15px;"  cellpadding="5" >
		<tr class="nar"><td>文件名称</td><td>描述</td><td width=10%>类型</td><td width=20%>发布时间</td><td width=8%>下载</td></tr>
			<tbody>
			<c:forEach items="${page.content}" var="page">
				<tr><td align="left" class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/resource/view/${page.id}"></c:url>">${page.fileName}</a></td>
				<td class="bb">
					<c:choose>
						<c:when test="${page.resourceDesc != null && page.resourceDesc !=''}">${page.resourceDesc}</c:when>
						<c:otherwise>无描述</c:otherwise>
					</c:choose>
				</td>
				<td class="bb">${page.resourceType.typeName}</td>
				<td class="bb">${page.date}</td>
				<td class="bb"> <a href='<c:url value="/resource/download/${page.id }"></c:url>'>下载</a></td></tr>
			</c:forEach>
			</tbody>
			<tfoot>
	    <tr><td colspan="5">
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
	</div>
</div>