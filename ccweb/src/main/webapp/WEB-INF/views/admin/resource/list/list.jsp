<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(document).ready(function() {	
	$('.deleteResourcePostBtn').on('click', function() {
		var resource_id = $(this).next().val();
		$('#deleteResourcePostModal #resourceId').val(resource_id);	
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
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>资源管理</h4>
	</div>
	<div class="row1">
		<div style="text-align: right;">
			<a  style="margin-bottom: 10px;" href='<c:url value="/admin/teacher/resource/new"></c:url>' class="btn">添加资源</a>
			&nbsp;&nbsp;<a  style="margin-bottom: 10px;" href='<c:url value="/admin/teacher/resource/type/list"></c:url>'  class="btn">类别管理</a> <br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr><th  align="center">文件名称</th><th align="center">发布时间</th><th align="center">文件描述</th><th align="center">文件类型</th><th align="center">操作</th></tr>
				</thead>
				<tbody>
				<c:forEach items="${page.content}" var="page">
					<tr><td  align="center">${page.fileName}</td>
					<td  align="center">${page.date}</td>
					<td  align="center">
						<c:choose>
							<c:when test="${(page.resourceDesc!=null) && (page.resourceDesc != '')}">${page.resourceDesc}</c:when>
							<c:otherwise>无描述</c:otherwise>
						</c:choose>
					</td>
					<td align="center">${page.resourceType.typeName}</td>
					<td align="center">
					 <a class="deleteResourcePostBtn" href="#deleteResourcePostModal" role="button" data-toggle="modal" data-target="#deleteResourcePostModal">删除</a>
					 <input type="hidden"  value="${page.id }" id="resourceId">
						| <a href='<c:url value="/resource/download/${page.id }"></c:url>'>下载</a></td></tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="row1"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>
	</div>
</div>

<!-- delete resource Form -->
<div class="modal hide fade" id="deleteResourcePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该资源吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/teacher/resource/destory"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="resourceId" type="hidden" name="resourceId" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>		