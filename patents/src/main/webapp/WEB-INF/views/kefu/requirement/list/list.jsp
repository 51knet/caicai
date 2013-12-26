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
		<h4>需求列表</h4>
	</div>
	<div class="content">
		<a class="margin_right"  href="<c:url value='/admin/kefu/requirement/list/all'></c:url>" > 全部 </a>
		 <a  class="margin_right" href="<c:url value='/admin/kefu/requirement/list/pass'></c:url>"> 通过 </a>
		  <a class="margin_right"  href="<c:url value='/admin/kefu/requirement/list/waite'></c:url>"> 审核中 </a><br><br>
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr>
					<th >需求标题</th>
					<th width="15%">需求类别</th>
					<th  width="15%">发布日期</th>
					<th  align="center" width="10%">通过审核</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/kefu/requirement/view/${page.id}"></c:url>' >${page.title }</a></td>
						<td align="center" >${page.requirType.typeName}</td>
						<td align="center" ><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd "/></td>
						<td  align="center"><input type="checkbox" <c:if test="${page.status ==1 }">checked </c:if>  onchange="changeStatus( ${page.id}, ${page.status } )" ></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>
</div>


<script type="text/javascript">
/*
$(document).ready(function() {
	$('.destoryPatentPostBtn').on('click', function() {
		var p_id = $(this).next().val();
		$('#p_delete_id').val(p_id);	
	});
});*/

function changeStatus(id,status){
	$.ajax({
		   type: "POST",
		   url: "<c:url value='/admin/kefu/requirement/status/change'></c:url>",
		   data: "id="+id+"&status="+status,
		   success: function(flag){
			   if(flag == true){
				   alert( "审核修改成功");
			   }else{
				   alert("服务器正忙，请稍后再试");
			   }
		   
		   }
		});
}

</script>