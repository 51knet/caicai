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
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
.nar {
	background-color:#ccdfa8; 
	width:100%; 
	font-size: 14px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>公告</h4>
	</div>
	<div class="row1">
		<table style="margin-top: 15px;"  cellpadding="5">
			<tr class="nar" ><td>标题</td><td width="25%">发布时间</td></tr>
			<tbody>
				<c:forEach items="${page.content}" var="page">
					<tr><td align="left" class="bb" >
						<div style="width: 540px;" id="content"><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/view/${page.id}"></c:url>">
						${page.title}</a></div>
					</td>
					<td class="bb">${page.date}</td>
					</tr>
				</c:forEach>
			</tbody>		 
			<tfoot>
		    	<tr><td colspan="3">
		        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
		   		 </td></tr>
			</tfoot>
		</table>
	</div>	 
</div>
