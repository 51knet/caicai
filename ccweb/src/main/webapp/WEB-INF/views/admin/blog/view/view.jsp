<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

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
		<h4>阅读博文</h4>
	</div>
<div class="row">
	  <div class="span12" >
		  <label>${blogPost.title}</label>
		  <span>发表于: ${blogPost.dateCreated} | 分类: ${blogPost.blogCategory.name}
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
	  <div class="span12">
	  	<ul>
	  		<c:forEach var="comment" items="${blogPost.blogComments}">
	  			<li>${comment.content} - ${comment.dateCreated}</li>
	  		</c:forEach>
	  	</ul>
	  </div>
	  <hr/>
	  <div class="clearfix"></div>
	  <div class="span12">
		  <b>发表评论</b>
		  
		  <form action='<c:url value="/admin/blog/comment/new" />' method="post">
			<input type="hidden" name="blogpost_id" value="${blogPost.id}">
			<textarea name="content" rows="4" cols="120" style="width:98%"></textarea>
			<button class="btn btn-success" type="submit">提交</button>
		  </form>
	  </div>
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
	