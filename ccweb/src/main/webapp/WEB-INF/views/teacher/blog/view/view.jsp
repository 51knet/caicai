<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .row {
	margin: 10px 40px;
}
.comments-container h5 {
	background-color:#ccdfa8;
	padding:5px;
}
.comments-container .content {
	border-bottom: 1px dashed;
}
</style>

<div class="row-fluid custom">
	<div class="row">
		<h4>阅读博文</h4>
	</div>
	<div class="row">
		<div>
			<h4>${blogPost.title}</h4>
			<div>发表于：<fmt:formatDate value="${blogPost.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>  | 标签： <span style="color:#80b029"  > <b>${blogPost.blogCategory.name}</b> </span></div>  
			<input type="hidden" value="${blogPost.id}" /> 
			<input type="hidden" value="${blogPost.title}" />
		</div>
		<div>${blogPost.content}</div>
		
		<div class="comments-container">
			<h5>评论</h5>
			<c:forEach var="comment" items="${blogCommentList}">
			<div>
				<span style="color:#80b029">${comment.author.user.name}</span>
				<span>
					<fmt:formatDate value="${comment.dateCreated}" pattern="yyyy-MM-dd HH:mm"/>
				</span>
			</div>
			<div class="content"><span>${comment.content}</span></div>
			</c:forEach>
			<div class="pull-right">共${sumComment}条评论</div>
			<br>
		<c:choose>
			<c:when test="${sessionUserInfo==null}">
				<div><b>请<a href="<c:url value="/" />">登录</a>评论</b></div>
			</c:when>
			<c:otherwise>
			<div>
				<h5>发表评论</h5>
				<form action='<c:url value="/teacher/${teacher_id}/blog/comment" />' method="post">
					<input type="hidden" name="blogpost_id" value="${blogPost.id}">
					<textarea name="content" rows="4" cols="120" style="width: 99%"></textarea>
					<button class="btn btn-success pull-right" type="submit">发表评论</button>
				</form>
			</div>		
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
