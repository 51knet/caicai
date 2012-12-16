<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/commentAdd-Ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function (){
	$("#commentDesc").focus(function (){
		$("#commentDesc").text("");
		return false;
	});
});
function addComment(){
	 addCommentAjax("addComment");
	 return false;
}
</script>
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
		<h5>教师课程</h5>
	</div>
	<div class="row">
		<div>
		<table class="table">
					<tr><td >&nbsp;&nbsp;课程名称:&nbsp;&nbsp;${teacherCourse.courseName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
					<tr><td  >&nbsp;&nbsp;课程描述:${teacherCourse.courseDesc}&nbsp;&nbsp;</td></tr>
					<tr><td id="markNum"> &nbsp;&nbsp;总评分数:&nbsp;&nbsp;${markNum}</td><td id="personNum">总评论人:${personNum}</td></tr>
		</table>
		</div>
		<div>
		<form id="addComment">
		<div><input type="hidden" id="teachercourseid" value="${teacherCourse.id}" /></div>
		<div>
		请输入标题:&nbsp;&nbsp;&nbsp;<input type="text" id="commentTitle" name="commentTitle"/>
		<span  id="commentTitleError"></span>
		<br/>
		</div>
		<div>
		请选择分数:
		<span>
		<input type="radio" name="mark"  value="0" checked="checked">0
		<input type="radio" name="mark" value="1">1
		<input type="radio" name="mark" value="2">2
		<input type="radio" name="mark" value="3">3
		<input type="radio" name="mark" value="4">4
		<input type="radio" name="mark" value="5">5
		</span>
		</div><br/>
		<div>
		<textarea name="commentDesc" id="commentDesc" cols="40" rows="10" style="width:500px;height:100px;">请输入评论</textarea>
		<span class="help-inline" id="commentDescError"></span>
		</div>
		<input type="submit"  onclick="addComment();"   value="发表评论"/>
		</form>
		</div>
		<div id="comment">
			<c:forEach var="comment" items="${page.content}">
					<tr><td>&nbsp;&nbsp;&nbsp;昵称:${comment.user.name}:&nbsp;&nbsp;&nbsp;本人评分数:${markNum}</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;标题:${comment.commentTitle}&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;评论时间:${comment.commentDate }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;评论信息:${comment.commentDesc}</td></tr>
			</c:forEach>
			</div>
		</div>
	</div>
