<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/emptyCheck-ajax.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/themes/default/default.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#usercourse_info_form").submit(function(){
		return checkEmptyAjax("usercourse_info_form","<c:url value='/admin/mycourse/comment/commentajax'></c:url>");
	});
});
</script>
<style>
.nar{
	height: 40px;
	padding-top: 2px;
}
.nar >h4{
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 10px;
	padding-left:30px;
	margin: 10px 0px 0px 0px;
}
.comment{
	width: 100%;
	margin-bottom: 200px;
	padding-top: 15px;
	background-position: top center;
}
.radio.inline{
	margin-bottom: 0px;
	vertical-align: middle;
	display: inline-block;
}
</style>
    <div class="nar">
		<h4>课程评论</h4>
	</div>
	<div class="comment">
    <div style="margin-left: 85px;"><font color='#ff0000' >${message}</font></div>
	<div style="margin-left: 85px;">
		<form id="usercourse_info_form" action="<c:url value='/admin/mycourse/comment/new'></c:url>" class="form-horizontal" method="post">
			<input type="hidden" name="courseid"  value="${course.id}" />
			<div class="control-group" id="commentDesc" style="margin-top: 15px;">
				<textarea name="commentDesc"  placeholder="请输入评论内容" id="c" cols="5" rows="8" style="width:640px;height: 160px;"></textarea>
				<span class="help-inline"></span>
	        </div>
			<div class="control-group" id="mark" style="margin-top: -15px;">
				<label><b>请选择分数：</b></label>
				<label class="radio inline" style="width: 1%; ">
					<input type="radio" name="mark" value="0" checked="checked">0
				</label>
				<label class="radio inline" style="width: 1%; ">
					<input type="radio" name="mark" value="1" >1
				</label>
				<label class="radio inline" style="width: 1%; ">
					<input type="radio" name="mark" value="2" >2
				</label>
				<label class="radio inline" style="width: 1%; ">
					<input type="radio" name="mark" value="3" >3
				</label>
				<label class="radio inline" style="width: 1%; ">
					<input type="radio" name="mark" value="4" >4
				</label>
				<label class="radio inline" style="width: 1%; ">
					<input type="radio" name="mark" value="5" >5
				</label>
			</div>
			<div style="padding-bottom: 20px;">
				<button type="submit" class="btn btn-success" >发表评论</button>
			</div>
		</form>
	</div>
</div>

