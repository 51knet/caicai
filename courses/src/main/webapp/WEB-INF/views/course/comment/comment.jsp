<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function (){
	checkAjaxs("comment_info_form","commentAjax");
});
</script>

	<div  align="left" style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 20px;">
    	<h4 style="margin-left: 50px; float: left;">课程评论</h4>
    </div>
    
    
	<div class="row" style="margin-left: 50px;">
		<form id="comment_info_form" action="addComment" class="form-horizontal" method="post">
		<div ><input type="hidden" name="teachercourseid"  value="${id}" /></div>
				<div class="control-group" id="commentTitle">
					<label class="control-label" for="commentTitle"><i class="icon-star"></i>标题</label>
					<div class="controls">
						<input type="text" id="commentTitles" name="commentTitle" placeholder="标题">
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="mark">
				<label class="control-label" for="mark"><i class="icon-star"></i>请选择分数:</label>
				<span>
				<input type="radio" name="mark" value="0" checked="checked">0
				<input type="radio" name="mark" value="1">1
				<input type="radio" name="mark" value="2">2
				<input type="radio" name="mark" value="3">3
				<input type="radio" name="mark" value="4">4
				<input type="radio" name="mark" value="5">5
				</span>
				</div>
				<div class="control-group" id="commentDesc">
				<label class="control-label" for="commentDesc"><i class="icon-star"></i>请输入评论内容:</label>
				<textarea name="commentDesc" id="commentdescs" placeholder="请输入评论内容" id="c" cols="5" rows="8" style="width:380px;"></textarea>
				<span class="help-inline"></span>
		        </div>
				<div class="control-group">
					<div class="controls">
					<div>
						<font color='#ff0000'></font>
						</div>
						<button type="submit"  class="btn btn-large btn-success">发表评论</button>
					</div>
				</div>
			</form>
		</div>

