<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	color: #3d4f67;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid .custom .user-row {
	color: #3d4f67;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>

<div class="row-fluid custom round">
	<div  class="row">
		<h4>本站已投项目</h4>
	</div>
	<div class="content">
		<table class=" blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr><th width="10%">LOGO</th><th width="20%">项目标题</th><th  width="15%">申请日</th></tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td align="center"><img src="<c:url value='${page.logoPath}'></c:url>" ></td>
						<td ><a href='<c:url value="/admin/investcompany/projects/view/${page.id }"></c:url>' >${page.projectName }</a></td>
						<td  align="center"><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>
</div>
