<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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

<div class="row-fluid custom round ccc_border">
	<div class="row">
		<h4>博文</h4>
	</div>
	<div class="row ">
	<table style="width: 100%"  cellpadding="5" >
			<tr class="titlebg"><td align="left"><b>标题</b></td><td width=22% align="left"><b>发表日期</b></td></tr>
		<tbody>
			<c:forEach var="blogPost" items="${page.content}">
			<tr class="bb">
				<td><div style="width: 400px;" id="content"><a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>">
				 ${blogPost.title} </a></div>
				 </td>
				<td>
				<fmt:formatDate value="${blogPost.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr><td colspan="2">
				<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			</td></tr>
		</tfoot>
</table>
</div>
</div>