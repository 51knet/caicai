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
.row-fluid.custom .row2 {
	margin: 10px 40px;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 10px 40px;
	text-align: center;
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
			<label ><b style="font-size: 16px; ">${blogPost.title}</b ></label> <br/>
			<div >发表于： ${blogPost.dateCreated}&nbsp;&nbsp;&nbsp;&nbsp;标签： <span style="font-size: 14px; color:#80b029"  > <b>${blogPost.blogCategory.name}</b> </span></div>  
			<input type="hidden" value="${blogPost.id}" /> 
			<input type="hidden" value="${blogPost.title}" />
		</div>
		<div style="margin-top: 20px;text-align: left;">${blogPost.content}</div>
		<div class="row" style="width: 680px;margin-left: -2px;"></div>
		<div >
			<div style="background-color:#ccdfa8;width: 680px; padding:5px; text-align: left;"><b>评论</b></div>
			<div style="margin-top:20px; text-align: left;">
				<c:forEach var="comment" items="${blogCommentList}">
				<div  ><span style="font-size: 14px; color:#80b029">${comment.author.user.name}</span><span style="margin-left: 500px;">${comment.dateCreated}</span></div>
					<div class="row2" style="width: 680px;margin-left: -2px;"><span>${comment.content} </span></div>
				</c:forEach>
				<div style="margin-left:600px;">共${sumComment}条评论</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${sessionUserInfo==null}">
				<div class="span12"><b>请<a href="<c:url value="/" />">登录</a>评论</b></div>
			</c:when>
			<c:otherwise>
			<div >
				<div style="background-color:#ccdfa8;width: 680px; padding:5px;text-align: left;"><b>发表评论</b></div>
				<div style="margin-top: 10px;">
				<form action='<c:url value="/teacher/${teacher_id}/blog/comment" />' method="post">
					<input type="hidden" name="blogpost_id" value="${blogPost.id}">
					<textarea name="content" rows="4" cols="120" style="width: 98%"></textarea>
					<button class="btn btn-primary" type="submit" style="margin-left: 585px;float: left;">发表评论</button>
				</form>
				</div>
			</div>		
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
