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
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 10px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>阅读博文</h4>
	</div>
	<div class="row1">
		<div>
			<label ><b style="font-size: 25px;margin-right: 10px;">${blogPost.title}</b >发表于: ${blogPost.dateCreated} </label> 
			<span> 
			标签: ${blogPost.blogCategory.name}
			<input type="hidden" value="${blogPost.id}" /> 
			<input type="hidden" value="${blogPost.title}" />
			</span>
		</div>
		<div style="margin-top: 20px;">${blogPost.content}</div>
		<div class="bb"></div>
		<div >
			<ul>
				<c:forEach var="comment" items="${blogPost.blogComments}">
					<li>${comment.content} - ${comment.dateCreated}</li>
				</c:forEach>
			</ul>
		</div>
		<div class="row" style="width: 680px;margin-left: -2px;"></div>
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
