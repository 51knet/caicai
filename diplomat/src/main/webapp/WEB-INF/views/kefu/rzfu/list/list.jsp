<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {	
	$('.deleteRzfhPostBtn').on('click', function() {
		var rzfh_id = $(this).next().val();
		$('#deleteRzfhPostModal #rzfh_id').val(rzfh_id);	
	});
});

</script>
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
	<div  class="row" >
		<h4>孵化园区/融资机构</h4>
	</div>
	<div class="content">
		<a class="margin_right"  href="<c:url value='/admin/kefu/rzfh/list/all'></c:url>" >全部 </a>
		<a  class="margin_right" href="<c:url value='/admin/kefu/rzfh/list/rzjg'></c:url>">融资机构 </a>
		<a class="margin_right"  href="<c:url value='/admin/kefu/rzfh/list/fhyq'></c:url>">孵化园区 </a>
		<div style="text-align: right;">
			<a href='<c:url value="/admin/kefu/rzfh/new"></c:url>' style="margin-bottom: 10px; font-size: 14px;"class="btn">
				添加</a><br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead><tr>
						<th  align="center" style="width: 80px;">Logo</th>
						<th  align="center">公司名称</th>
						<th  align="center" width="15%">类别</th>
						<th  align="center" width="12%">操作</th>			
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="left" ><img src="<c:url value='${page.logoPath}'></c:url>" style="width: 80px;"></td>
							<td align="left" >${page.name}</td>
							<td align="left" >${page.types}</td>
							<td align="center">
								 <a class="deleteRzfhPostBtn" href="#deleteRzfhPostModal" role="button" data-toggle="modal" data-target="#deleteRzfhPostModal">删除</a><input type="hidden" value="${page.id} ">  | 
								 <a href='<c:url value="/admin/kefu/rzfh/edit/${page.id}"></c:url>'>修改</a>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>	
</div>

<!-- delete RzfhForm -->
<div class="modal hide fade" id="deleteRzfhPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该活动吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/kefu/rzfh/delete"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="rzfh_id" type="hidden" name="rzfh_id" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>