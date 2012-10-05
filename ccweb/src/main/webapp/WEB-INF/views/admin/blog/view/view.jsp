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
		  | <a href="#">删除</a> </span>  
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

<script type="text/javascript">
		$(document).ready(function() {
			
	    });
</script>
	