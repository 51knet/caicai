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
.margin_right{
	margin-right: 15px;
	font-size: 14px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>审核列表</h4>
	</div>
	<div class="content" >
		<a class="margin_right"  href="<c:url value='/admin/kefu/applyright/list/all'></c:url>" >全部 </a>
		<a  class="margin_right" href="<c:url value='/admin/kefu/applyright/list/pass'></c:url>">通过 </a>
		<a class="margin_right"  href="<c:url value='/admin/kefu/applyright/list/waite'></c:url>">审核中 </a>



		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr>
					<th width="20%">申请人</th>
					<th>电话</th>
					<th>邮箱</th>
					<th  width="20%">申请日</th>
					<th  width="15%">申请权限</th>
					<th  width="15%">资料下载</th>
					<th  align="center" width="10%">通过审核</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/kefu/applyright/view/${page.id }"></c:url>' >${page.name }</a></td>
						<td >${page.phone}</td>
						<td >${page.user.email}</td>
						<td ><fmt:formatDate value="${page.date }" pattern="yyyy-MM-dd hh:mm" /></td>
						<td align="center" >
							<c:forEach items="${rightMap }" var="map">
								<c:if test="${map.key == page.applypermit }"> ${map.value }</c:if>
							</c:forEach>
						</td>
						<td align="center"><a href="<c:url value='/admin/kefu/applyright/download/${page.id }'></c:url>">点击下载</a></td>
						<td  align="center"><input  class="changeStatus"  type="checkbox" <c:if test="${page.status ==1 }">checked </c:if>><input type="hidden" value="${page.id}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${searchParam != null }">
				  <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
			 	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {

	$('.changeStatus').on('change', function() {
		var id = $(this).next().val();
		$.ajax({
			   type: "POST",
			   url: "<c:url value='/admin/kefu/applyright/status/change'></c:url>",
			   data: "id="+id+"&types=person",
			   success: function(flag){
				   if(flag == true){
					   alert( "审核修改成功");
				   }else{
					   alert("服务器正忙，请稍后再试");
				   }
			   }
			});
	});

});


</script>