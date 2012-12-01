<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
				<td width="200px" style="display: none;">
					<form class="form_rename_category" method="post" action="<c:url value="/admin/blog/category/rename"></c:url>" class="form-inline">
						<input type="hidden" name="id" value="${blogCategory.id}">
						<input type="text" name="name" placeholder="" value="${blogCategory.name}">
						<button type="submit" class="btn" value="确定">确定</button>
						<a href="#" class="btn link-rename-cancel">取消</a>
					</form>
				</td>
				<td width="348px"><a href="#" class="link-rename pull-right"> 重命名 </a></td>
				<td width="50px">
					<a class="link-delete-category btn" href="#delete_category_modal" role="button" data-toggle="modal" data-target="#delete_category_modal"><i class="icon-remove"></i></a>
					<input type="hidden" value="${blogCategory.id}">
					<input type="hidden" value="${blogCategory.name}">
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<!-- Modal -->
<div class="modal hide fade" id="delete_category_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">请注意</h3>
  </div>
  <div class="modal-body">
    <p>你确定删除(<span id="blog_category_name"></span>)这个博客分类吗？</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
    <form action='<c:url value="/admin/blog/category/destroy"></c:url>' method="post" style="display: inline-block;">
    	<input id="blog_category_id" type="hidden" name="blog_category_id" />
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
			$('.form_rename_category').on('submit', function(event){
				event.preventDefault();
				var category = $(this).closest('.form_rename_category').serializeObject();
				console.log(category);
				$.postJSON('<c:url value="/admin/blog/category/rename"/>', category, function(data) {
					console.log(data);	
					//TODO: I have a better solution later of course
					window.location.href = window.location.href;
				});
				return false;
			});
			
			$('.link-rename').on('click', function(event) {
				event.preventDefault();
				$(this).parent().hide(); // rename link table cell
				$(this).parent().prev().show();  // rename form table cell 
			});
			$('.link-rename-cancel').on('click', function(event){
				event.preventDefault();
				$(this).parent().parent().hide(); // rename form table cell
				$(this).parent().parent().next().show(); // rename link table cell
			});
			$("#category_management").colorbox({inline:true, width:"50%", href:"#category_management_form"});
			
			// 表格里的删除按钮按下的时候，需要为对话框动态修改一些属性的值
			$('.link-delete-category').on('click', function() {
				var blog_category_id = $(this).next().val();
				var blog_category_name = $(this).next().next().val();
				$('#delete_category_modal #blog_category_id').val(blog_category_id);
				$('#delete_category_modal #blog_category_name').html(blog_category_name);				
			});
		});
</script>
	