<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
		<h4>用户：${buyer.name } 的订单</h4>
	</div>
	<div class="content" >
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr>
					<th width="10%">订单号</th>
					<th  width="20%">下单日期</th>
					<th  align="center" >下单项目</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/kefu/order/view/${page.id }"></c:url>' >${page.id }</a></td>
						<td ><fmt:formatDate value="${page.startTime }" pattern="yyyy-MM-dd hh:mm" /></td>
						<td  ><a href='<c:url value="/admin/kefu/projects/view/${page.projects.id }"></c:url>' > ${page.projects.projectName }</a></td>
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

<div class="modal hide fade" id="destoryPatentPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该专利吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/patent/delete"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="p_delete_id" type="hidden" name="patentNum" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.changeFocus').on('change', function() {
		var patentNum = $(this).next().val();
		$.ajax({
			   type: "POST",
			   url: "<c:url value='/admin/kefu/patent/focus/change'></c:url>",
			   data: "patentNum="+patentNum,
			   success: function(flag){
				   if(flag == true){
					   alert( "首页展示修改成功");
				   }else{
					   alert("服务器正忙，请稍后再试");
				   }
			    }
			});
	});
	
	$('.changeStatus').on('change', function() {
		var patentNum = $(this).next().val();
		$.ajax({
			   type: "POST",
			   url: "<c:url value='/admin/kefu/patent/status/change'></c:url>",
			   data: "patentNum="+patentNum,
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