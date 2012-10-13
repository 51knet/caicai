<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="5">全部分类</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="5">
					<form id="form_new_category" method="post" action="<c:url value="/admin/blog/category/new"></c:url>" class="form-inline pull-right">
						<input type="text" name="name" placeholder="输入你要新建的分类的名字">
						<input type="submit" class="btn" value="新建分类">
					</form>
				</td>
			</tr>
			<c:forEach var="blogCategory" items="${blogCategories}">
			<tr> <!-- TODO: vertical-align:text-top; -->
				<td>${blogCategory.id}</td>
				<td><a href="<c:url value="/admin/blog/category/view/${blogCategory.id}"></c:url>"> ${blogCategory.name} </a></td>
				<td>
					<form action="<c:url value="/admin/blog/category/rename"></c:url>" class="form-inline">
						<input type="text" name="name" placeholder="" value="${blogCategory.name}">
						<a href="#" class="btn">确定</a>
					</form>
				</td>
				<td><a href="<c:url value="/admin/blog/category/view/${blogCategory.id}"></c:url>"> 重命名 </a></td>
				<td>
					<a class="btn" href="#"><i class="icon-remove"></i></a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
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
			$.fn.serializeObject = function() {
				var o = {};
				var a = this.serializeArray();
				$.each(a, function() {
					if (o[this.name]) {
						if (!o[this.name].push) {
							o[this.name] = [o[this.name]];
						}
						o[this.name].push(this.value || '');
					} else {
						o[this.name] = this.value || '';
					}
				});
				return o;
			};
			
			$.postJSON = function(url, data, callback) {
			    return jQuery.ajax({
			        'type': 'POST',
			        'url': url,
			        'contentType': 'application/json',
			        'data': JSON.stringify(data),
			        'dataType': 'json',
			        'success': callback
			    });
			};
			
			$('#form_new_category').on('submit', function(event){
				event.preventDefault();
				var category = $('#form_new_category').serializeObject();
				
				$.postJSON('<c:url value="/admin/blog/category/new"/>', category, function(data) {
					console.log(data);	
					//TODO: I have a better solution later of course
					window.location.href = window.location.href;
				});
				return false;
			});
			$("#category_management").colorbox({inline:true, width:"50%", href:"#category_management_form"});
			
			// 表格里的删除按钮按下的时候，需要为对话框动态修改一些属性的值
			$('.deletePostBtn').on('click', function() {
				var blog_post_id = $(this).next().val();
				var blog_post_title = $(this).next().next().val();
				$('#deletePostModal #blog_post_id').val(blog_post_id);
				$('#deletePostModal #blog_post_title').html(blog_post_title);				
			});
		});
</script>
	