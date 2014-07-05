<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>

<div class="row-fluid custom round">
	<div  class=" row ">
		<h4>我的成果</h4>
	</div>
	<div class="content">
		<a  style="float: right;" href='<c:url value="/admin/technology/new"></c:url>' class="btn">添加新成果</a><br><br>
		<table class=" blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr><th width="70%">成果名称</th><th>应用领域</th><th width="15%">操作</th></tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/technology/view/${page.id }"></c:url>' >${page.techName }</a></td>
						<td align="center">${page.techField}</td>
						<td align="center"><a href='<c:url value="/admin/technology/edit/${page.id}"></c:url>'>修改</a> | 
						<a class="destoryTechnologyPostBtn" href="#destoryTechnologyPostModal" role="button" data-toggle="modal" data-target="#destoryTechnologyPostModal">删除</a><input type="hidden"  value="${page.id}"> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</div>
</div>

<div class="modal hide fade" id="destoryTechnologyPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该成果吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/technology/delete"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="tech_delete_id" type="hidden" name="tech_id" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.destoryTechnologyPostBtn').on('click', function() {
		var tech_id = $(this).next().val();
		$('#tech_delete_id').val(tech_id);	
	});
});

</script>