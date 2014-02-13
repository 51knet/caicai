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
	color: #80b029;
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
	<div  class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>我的专利</h4>
	</div>
	<div class="content">
		<a  style="float: right;" href='<c:url value="/admin/patent/new"></c:url>' class="btn">添加新专利</a><br><br>
		<table class=" <c:if test="${sessionUserInfo.role == 'teacher'}">green</c:if> <c:if test="${sessionUserInfo.role == 'user'}">blue</c:if>" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr><th width="20%">申请号</th><th>名称</th><th  width="15%">申请日</th><th width="12%">操作</th></tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="page">
					<tr>
						<td ><a href='<c:url value="/admin/patent/view?id=${page.patentNum }"></c:url>' >${page.patentNum }</a></td><td >${page.patentName }</td>
						<td >${page.applicationDate }</td>
						<td align="center"><a href='<c:url value="/admin/patent/edit/${page.patentNum}"></c:url>'>修改</a> | 
						<a class="destoryPatentPostBtn" href="#destoryPatentPostModal" role="button" data-toggle="modal" data-target="#destoryPatentPostModal">删除</a><input type="hidden"  value="${page.patentNum}"> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
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
	$('.destoryPatentPostBtn').on('click', function() {
		var p_id = $(this).next().val();
		$('#p_delete_id').val(p_id);	
	});
});

</script>