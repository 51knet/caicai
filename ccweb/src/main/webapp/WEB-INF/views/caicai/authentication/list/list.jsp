<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#t").focus(function() {
		$(".help-inline").html("");
	});
	$("#c").focus(function() {
		$(".help-inline").html("");
	});
	
	$('.destoryEteacherPostBtn').on('click', function() {
		var t_id = $(this).next().val();
		$('#destoryEteacherPostModal #etid').val(t_id);	
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
.row-fluid.custom .content{
	margin: 20px 40px;

}
</style>


<div class="row-fluid custom round">
	<div  class="row" >
		<h4>申请信息</h4>
	</div>
	<div class="content">
		<div style="text-align: right;">
			<br>
			<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
				<thead><tr>
						<th  align="center" >标题</th>
						<th  align="center" >申请人</th>
						<th  align="center" >电话</th>
						<th  align="center">申请状态</th>
						<th  align="center" >拒绝原因</th>
						<th  align="center" width="20%" >申请时间</th>
						<th align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="center">${page.title}</td> 
							<td align="center">${page.user.name}</td> 
							<td align="center">${page.user.fix_phone}</td> 
							<td align="center" >
								<c:if test="${page.status=='pass' }">通过</c:if>
								<c:if test="${page.status=='submit' }">审核中</c:if>
								<c:if test="${page.status=='refuse' }">未通过</c:if>
							</td>
							<td align="center" >
								${page.reason }
							</td>
							<td  align="center">
								<fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd HH:mm" />
							</td>
							<td align="center" >
								<c:if test="${page.status == 'submit'}">
								<a href='<c:url value="/admin/caicai/authentication/pass/${page.id }"></c:url>'>通过</a> | 
								<a href='<c:url value="/admin/caicai/authentication/refuse/${page.id }"></c:url>'>拒绝</a>
								</c:if>
								<c:if  test="${page.status == 'pass'  }">已通过</c:if>
								<c:if  test="${page.status == 'refuse' }">已拒绝</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
		</div>
	</div>	
</div>

<!-- delete teacherForm -->
<div class="modal hide fade" id="destoryEteacherPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该教师吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/enterprise/teacher/destory"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="etid" type="hidden" name="eteacherid" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>