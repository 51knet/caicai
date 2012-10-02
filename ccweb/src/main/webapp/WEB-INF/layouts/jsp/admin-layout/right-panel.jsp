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
					<a id="category_management" href='#' class="btn">管理分类</a></td>
			</tr>
			<tr>
				<td><li>博文标题</li></td>
				<td>created_at</td>
				<td>3/24</td>
				<td><a href="#">编辑</a></td>
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
		                  <li><a href="#">删除</a></li>
		              </ul>
					</div>
				</td>
			</tr>
			
		</tbody>
	</table>
</div>
<div style="display:none; visibility: hidden;">
	<div id="category_management_form">
		<h1>category form</h1>
	</div>
</div>
<script type="text/javascript">
		$(document).ready(function() {
			$("#category_management").colorbox({inline:true, width:"50%", href:"#category_management_form"});
		});
</script>
	