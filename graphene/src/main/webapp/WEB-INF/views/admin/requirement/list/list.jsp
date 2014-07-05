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

.row-fluid .custom .user-row {

	color: #3d4f67;
	
}

.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div  class=" row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>" >
		<h4>技术需求</h4>
	</div>
	<div class="content">	
		<a  style="float: right;" href='<c:url value="/admin/requirement/new"></c:url>' class="btn">新需求</a><br><br>
	
			<table class=" <c:if test="${sessionUserInfo.role == 'teacher'}">green</c:if> <c:if test="${sessionUserInfo.role == 'user'}">blue</c:if>"    id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th >标题</th>
						
						<th width="20%">发布时间</th>
						<th width="13%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
						<td >
							<div style="width: 300px;" id="content">
								<a href='<c:url value="/admin/requirement/edit/${page.id }"></c:url>' >${page.title }</a>
							</div>
						</td>
						<td align="center">
							<fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm"/>
						</td>
						<td  align="center"><a href='<c:url value="/admin/requirement/edit/${page.id }"></c:url>' >修改</a> |
						 <a class="destoryRequirePostBtn" href="#destoryRequirePostModal" role="button" data-toggle="modal" data-target="#destoryRequirePostModal">删除</a><input type="hidden"  value="${page.id}"> </td>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>

</div>
<div class="modal hide fade" id="destoryRequirePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该需求吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/requirement/delete"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="require_delete_id" type="hidden" name="require_id" />
	    	
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.destoryRequirePostBtn').on('click', function() {
		var r_id = $(this).next().val();
		$('#require_delete_id').val(r_id);	
	});
});

</script>
