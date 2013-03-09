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

.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 10px 0px 0px 0px;
}

.row-fluid.custom .row {
	margin: 10px 40px 0px 40px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed #cccccc 1px;
}
.titlebg{
	background-color:#ccdfa8; 
	font-size: 14px;
	width: 100%;
}
</style>
<div class="row-fluid custom round">
	
	<div class="row">
		<h4>教学资源</h4>
	</div>
	<div class="row">
		<table  style="width: 100%" cellpadding="5" >
		<tr class="titlebg"><th align="left"><b>文件名称</b></th><th align="left"><b>描述</b></th><th align="left" width=10%><b>类型</b></th><th  align="left" width=20%><b>发布时间</b></th><th align="left" width=8%><b>下载</b></th></tr>
			<tbody>
			<c:forEach items="${page.content}" var="page">
				<tr class="bb"><td align="left"><a href="<c:url value="/teacher/${teacherInfo.id}/resource/view/${page.id}"></c:url>">${page.fileName}</a></td>
				<td >
					<c:choose>
						<c:when test="${page.resourceDesc != null && page.resourceDesc !=''}">
						<div style="width: 120px;" id="content">${page.resourceDesc}</div>
						</c:when>
						<c:otherwise>无描述</c:otherwise>
					</c:choose>
				</td>
				<td >${page.resourceType.typeName}</td>
				<td >${page.date}</td>
				<td> <a href='<c:url value="/resource/download/${page.id }"></c:url>'>下载</a></td></tr>
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