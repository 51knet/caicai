<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>我的需求</h4>
	</div>
	<div class="content">	
		<a  style="float: right;" href='<c:url value="/admin/requirement/new"></c:url>' class="btn">新需求</a><br><br>
	
			<table class="yellow" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th width="25%">标题</th>
						<th >内容</th>
						<th width="17%">发布时间</th>
						<th width="12%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
						<td >
							<div style="width: 200px;" id="content">
								<a href='<c:url value="/admin/requirement/view/${page.id }"></c:url>' >${page.title }</a>
							</div>
						</td>
						<td >	<div style="width: 305px;" id="content">${page.content}</div></td>
						<td align="center">
							<fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm"/>
						</td>
						<td  align="center">修改 | 删除</td>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>

</div>
