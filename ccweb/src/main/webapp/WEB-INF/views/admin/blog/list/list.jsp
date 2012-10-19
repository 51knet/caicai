<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="5">全部博文</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="5"><a href="<c:url value="/admin/blog/new"></c:url>" class="btn">写博文</a>
					<a id="category_management"  href="<c:url value="/admin/blog/category/list"></c:url>" class="btn">管理分类</a></td>
			</tr>
			<tr style="display: none;">
				<td colspan="5">${page}</td>
			</tr>
			<c:forEach var="blogPost" items="${page.content}">
			<tr>
				<td><a href="<c:url value="/admin/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a></td>
				<td>${blogPost.date_created}</td>
				<td>${blogPost.date_updated}</td>
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
			<tr><td colspan="5">
				<div class="pagination">
				<c:if test="${page.totalPages > 0}">
					<c:set var="prev" value="${page.number-1}" scope="page"></c:set>
					<c:set var="next" value="${page.number+1}" scope="page"></c:set>
					<ul>
						<c:choose>
							<c:when test="${page.hasPreviousPage()}">
								<li><a href='<c:url value="/admin/blog/list?pageNumber=${prev}" />'>Prev</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Prev</a></li></c:otherwise>
						</c:choose>
					    <li><a href="#" class="active">${page.number+1}/${page.totalPages}</a></li>
					    <c:choose>
							<c:when test="${page.hasNextPage()}">
								<li><a href='<c:url value="/admin/blog/list?pageNumber=${next}" />'>Next</a></li></c:when>
							<c:otherwise>
								<li><a href="#" class="disabled">Next</a></li></c:otherwise>
						</c:choose>
					    
					  </ul>
				</c:if> 
				</div>
				</td>
			</tr>
		</tfoot>
		
	</table>
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
    	<button class="btn btn-primary">确定</button>
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
	