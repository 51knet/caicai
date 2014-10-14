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
.margin_right{
	margin-right: 15px;
	font-size: 14px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>专利需求</h4>
	</div>
	<div class="content">	
		<a class="margin_right"  href="<c:url value='/admin/kefu/patentrequirement/list/all'></c:url>" >全部 </a>
		<a  class="margin_right" href="<c:url value='/admin/kefu/patentrequirement/list/pass'></c:url>">通过 </a>
		<a class="margin_right"  href="<c:url value='/admin/kefu/patentrequirement/list/waite'></c:url>">审核中 </a><br><br>
			<table class=" <c:if test="${sessionUserInfo.role == 'teacher'}">blue</c:if> <c:if test="${sessionUserInfo.role == 'user'}">yellow</c:if>"    id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th >标题</th>
						
						<th width="20%">发布时间</th>
						<th width="13%">通过审核</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
						<td >
							<div style="width: 300px;" id="content">
								<a href='<c:url value="/admin/kefu/patentrequirement/view/${page.id }"></c:url>' >${page.title }</a>
							</div>
						</td>
					
						<td align="center">
							<fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm"/>
						</td>
						<td  align="center"><input  class="changeStatus"  type="checkbox" <c:if test="${page.status ==1 }">checked </c:if>><input type="hidden" value="${page.id}" /></td>
						 </tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>

</div>

<script type="text/javascript">
$(document).ready(function() {
	/*$('.changeFocus').on('change', function() {
		var patentNum = $(this).next().val();
		$.ajax({
			   type: "POST",
			   url: "<c:url value='/admin/kefu/patentRequirement/focus/change'></c:url>",
			   data: "patentNum="+patentNum,
			   success: function(flag){
				   if(flag == true){
					   alert( "首页展示修改成功");
				   }else{
					   alert("服务器正忙，请稍后再试");
				   }
			    }
			});
	});*/
	
	$('.changeStatus').on('change', function() {
		var pre_id = $(this).next().val();
		$.ajax({
			   type: "POST",
			   url: "<c:url value='/admin/kefu/patentrequirement/status/change'></c:url>",
			   data: "pre_id="+pre_id,
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
