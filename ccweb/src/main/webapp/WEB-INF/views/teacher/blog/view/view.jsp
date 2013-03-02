<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}

.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}

.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<div class="row-fluid custom round">
	<div class="row">
		<h5>阅读博文</h5>
	</div>
	<div class="row">
		<div class="span12">
			<label>${blogPost.title}</label> 
			<span>发表于: ${blogPost.dateCreated} | 分类: ${blogPost.blogCategory.name} 
			<input type="hidden" value="${blogPost.id}" /> 
			<input type="hidden" value="${blogPost.title}" />
			</span>
		</div>
		<div class="clearfix"></div>
		<hr />
		<div class="span12">${blogPost.content}</div>
		<div class="clearfix"></div>
		<hr />
		<div class="clearfix"></div>
		<div class="span12">
			<ul>
				<c:forEach var="comment" items="${blogPost.blogComments}">
					<li>${comment.content} - ${comment.dateCreated}</li>
				</c:forEach>
			</ul>
		</div>
		<hr />
		<div class="clearfix"></div>
		<c:choose>
			<c:when test="${sessionUserInfo==null}">
				<div class="span12"><b>请<a href="<c:url value="/" />">登录</a>评论</b></div>
			</c:when>
			<c:otherwise>
			<div class="span12">
				<b>发表评论</b>
				<form action='<c:url value="/teacher/${teacher_id}/blog/comment" />' method="post">
					<input type="hidden" name="blogpost_id" value="${blogPost.id}">
					<textarea name="content" rows="4" cols="120" style="width: 98%"></textarea>
					<button class="btn btn-primary" type="submit">提交</button>
				</form>
			</div>		
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
