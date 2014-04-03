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
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}


</style>
<script type="text/javascript">

</script>
<div class="row-fluid custom round">
	<div class="row">
		<h4>审核详细</h4>
	</div>
	<div class="content">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>申请人姓名：</td>
						<td>${rolevalid.name }</td>
						<td>申请人邮箱：</td>
						<td>${rolevalid.user.email }</td>
					</tr>
					<tr>
						<td>申请权限：</td>
						<td><c:forEach items="${permitMap }" var="map">
								<c:if test="${map.key == rolevalid.applypermit }"> ${map.value }</c:if>
							</c:forEach></td>
						<td>申请人电话：</td>
						<td>${rolevalid.phone }</td>
					</tr>
					<tr>
						<td>申请日期：</td>
						<td><fmt:formatDate value="${rolevalid.date }" pattern="yyyy-MM-dd hh:mm" /></td>
						<td>资料下载：</td>
						<td><a href="<c:url value='/admin/kefu/applypermit/download/${rolevalid.id }'></c:url>">点击下载</a></td>
					</tr>
					<tr>
						<td colspan="4">申请简介</td>
					</tr>
					<tr>
						<td colspan="4">${rolevalid.content }</td>
					</tr>
				</tbody>
			</table>
	</div>
	<div class="content" >
		<a href='<c:url value="/admin/kefu/applypermit/list/all"></c:url>'  class="btn btn-success">返回</a>&nbsp;&nbsp;
	</div>
</div>