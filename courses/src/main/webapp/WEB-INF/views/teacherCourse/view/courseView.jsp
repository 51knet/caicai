<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<tr><td > &nbsp;&nbsp;总评分数:&nbsp;&nbsp;${mark}</td><td>总评论人:${personNum}</td></tr>
		</table>
		</div>
		<div>
		<form >
		请输入标题:&nbsp;&nbsp;&nbsp;<input type="text" name="commentTitle"/><br/>
		<div>
		请选择分数:
		<input type="radio" name="commentMark" value="0">0
		<input type="radio" name="commentMark" value="1">1
		<input type="radio" name="commentMark" value="2">2
		<input type="radio" name="commentMark" value="3">3
		<input type="radio" name="commentMark" value="4">4
		<input type="radio" name="commentMark" value="5">5
		</div><br/>
		<textarea name="content1" cols="100" rows="8" style="width:500px;height:100px;"></textarea>
		<input type="submit" value="发表评论"/>
		</form>
		</div>
		<div>
		<table class="table">
		<tbody>
				<tr><td>&nbsp;&nbsp;&nbsp;昵称:${user.name}:&nbsp;&nbsp;&nbsp;本人评分数:${markNum}</td></tr>
				<tr><td>&nbsp;&nbsp;&nbsp;标题:${comment.commentTitle}&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;评论时间:${comment.commentDate }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
			<c:forEach var="comment" items="${page.content}">
				<tr><td>&nbsp;&nbsp;&nbsp;评论信息:${comment.commentDesc}</td></tr>
			</c:forEach>
		</tbody>
		
		
		
		</table>
		</div>
		</div>
	</div>
