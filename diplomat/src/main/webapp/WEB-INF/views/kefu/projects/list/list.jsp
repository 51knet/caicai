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
		<h4>融资项目</h4>
	</div>
	<div class="content">
		<a class="margin_right"  href="<c:url value='/admin/kefu/projects/list/all'></c:url>" >全部 </a>
		<a  class="margin_right" href="<c:url value='/admin/kefu/projects/list/pass'></c:url>">通过 </a>
		<a class="margin_right"  href="<c:url value='/admin/kefu/projects/list/waite'></c:url>">审核中 </a><br><br>
		<table class=" <c:if test="${sessionUserInfo.role == 'teacher'}">blue</c:if> <c:if test="${sessionUserInfo.role == 'user'}">yellow</c:if>" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr>
					<th width="50%">项目名称</th>
					<th>应用领域</th>
					<th  align="center" width="20%">发布时间</th>
					<th  align="center" width="10%">通过审核</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/kefu/projects/view/${page.id }"></c:url>' >${page.projectName }</a></td>
						<td align="center">${page.industry}</td>
						<td align="center"><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH" /></td>
						<td  align="center"><input  class="changeStatus"  type="checkbox" <c:if test="${page.status ==1 }">checked </c:if>><input type="hidden" value="${page.id }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	
	$('.changeStatus').on('change', function() {
		var id = $(this).next().val();
		$.ajax({
			   type: "POST",
			   url: "<c:url value='/admin/kefu/projects/status/change'></c:url>",
			   data: "id="+id,
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

</script>