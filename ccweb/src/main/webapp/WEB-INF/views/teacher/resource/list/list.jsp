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
	margin: 5px 25px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 40px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>
<div class="row-fluid custom round">
	
	<div class="row">
		<h4>教师资源</h4>
	</div>
	<div class="row1">
		<table cellpadding="4" width="100%" style="margin-top:10px;margin-left: -16px;">
		<thead><tr><td><h5>文件名称</h5></td><td><h5>描述</h5></td><td width=10%><h5>类型</h5></td><td width=20%><h5>发布时间</h5></td><td width=8%><h5>下载</h5></td></tr></thead>
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
				<td align="center" class="bb">${page.date}</td>
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