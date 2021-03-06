<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.row-fluid.custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
</style>

<div class="row-fluid custom">
	<div class="row">
		<h4>全部博文</h4>
	</div>
    <div class="row">
	<table class="table table-bordered blue">
		
		<tbody>
			<tr>
				<td colspan="7"><a href="<c:url value="/admin/blog/new"></c:url>" class="btn">写博文</a>
					<a id="category_management"  href="<c:url value="/admin/blog/category/list"></c:url>" class="btn">管理分类</a></td>
			</tr>
			<tr style="display: none;">
				<td colspan="7">${page}</td>
			</tr>
			<tr>
				<th>标题</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>状态</th>
				<th>是否删除</th>
				<th colspan="2">操作</th>
			</tr>
			<c:forEach var="blogPost" items="${page.content}">
			<tr>
				<td><a href="<c:url value="/admin/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a></td>
				<td><fmt:formatDate value="${blogPost.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>  </td>
				<td><fmt:formatDate value="${blogPost.dateUpdated}" pattern="yyyy-MM-dd HH:mm"/>  </td>
				<td> ${blogPost.draft ? "草稿":"已发布"}</td>
								<td> ${blogPost.garbage ? "回收站":"不在回收站"}</td>
				<td><a href="<c:url value="/admin/blog/edit/${blogPost.id}"></c:url>">编辑</a></td>
				<td>
					<div class="btn-group">
					  <button class="btn">更多</button>
					  <button class="btn dropdown-toggle" data-toggle="dropdown">
					    <span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu">
		                  <li><a href="#">置顶</a></li>
		                  <li><a href="#">修改分类</a></li>
		                  <li class="divider"></li>
		                  <li>
		                  <!-- Button to trigger modal -->
<a class="deletePostBtn" href="#deletePostModal" role="button" data-toggle="modal" data-target="#deletePostModal">删除</a>
<input type="hidden" value="${blogPost.id}" />
<input type="hidden" value="${blogPost.title}" />
</li>
		              </ul>
					</div>
				</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr><td colspan="7">
				<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			</td></tr>
		</tfoot>
		
	</table>
</div>
</div>

<!-- Modal -->
<div class="modal hide fade" id="deletePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">请注意</h3>
  </div>
  <div class="modal-body">
    <p>你确定删除(<span id="blog_post_title"></span>)这篇博客文章吗？</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
    <form action='<c:url value="/admin/blog/destroy"></c:url>' method="post" style="display: inline-block;">
    	<input id="blog_post_id" type="hidden" name="blog_post_id" />
    	<button class="btn btn-success">确定</button>
    </form>
  </div>
</div>

<div style="display:none; visibility: hidden;">
	<div id="category_management_form">
		<h1>category form</h1>
	</div>
</div>
<script type="text/javascript">
		$(document).ready(function() {
			//$("#category_management").colorbox({inline:true, width:"50%", href:"#category_management_form"});
			
			// 表格里的删除按钮按下的时候，需要为对话框动态修改一些属性的值
			$('.deletePostBtn').on('click', function() {
				var blog_post_id = $(this).next().val();
				var blog_post_title = $(this).next().next().val();
				$('#deletePostModal #blog_post_id').val(blog_post_id);
				$('#deletePostModal #blog_post_title').html(blog_post_title);				
			});
		});
</script>
	