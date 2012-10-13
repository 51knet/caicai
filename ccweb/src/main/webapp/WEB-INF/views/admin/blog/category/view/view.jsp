<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="">
	<form method="post">
	<input type="hidden" name="id" value="${blogPost.id}">
	</form>
	  <b>阅读博文</b>
	  <div class="span12" >
		  <label>${blogPost.title}</label>
		  <span>发表于: ${blogPost.date_created} | 分类: ${blogPost.blogCategory.name}
		  | <a href="<c:url value="/admin/blog/edit/${blogPost.id}"></c:url>">编辑</a>
		  | 
<!-- Button to trigger modal -->
<a class="deletePostBtn" href="#deletePostModal" role="button" data-toggle="modal" data-target="#deletePostModal">删除</a>
<input type="hidden" value="${blogPost.id}" />
<input type="hidden" value="${blogPost.title}" />
</span>  
	  </div>
	  <div class="clearfix"></div>
	  <hr/>
	  <div class="span12">
	  	
	  	${blogPost.content}
	  	
	  </div>
	   <div class="clearfix"></div>
	  <hr/>
	  <div class="clearfix"></div>
	  <b>评论</b>
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
<script type="text/javascript">
		$(document).ready(function() {
			// 表格里的删除按钮按下的时候，需要为对话框动态修改一些属性的值
			$('.deletePostBtn').on('click', function() {
				var blog_post_id = $(this).next().val();
				var blog_post_title = $(this).next().next().val();
				$('#deletePostModal #blog_post_id').val(blog_post_id);
				$('#deletePostModal #blog_post_title').html(blog_post_title);				
			});
	    });
</script>
	