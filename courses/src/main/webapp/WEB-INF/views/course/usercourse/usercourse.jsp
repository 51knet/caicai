<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/js/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
function commentAjax(){
	checkAjaxs("usercourse_info_form","usercourseajax");
	return false;
};
</script>

	<div  align="left" style="width:100%;background-color:#F7F7F7; height: 40px; margin-bottom: 20px;">
    	<h4 style="margin-left: 50px; float: left;">课程评论</h4>
    </div>
    <div style="text-align: left; margin-left: 50px"><font color='#ff0000'>${message}</font></div>
    
	<div class="row" style="margin-left: 50px;margin-bottom: 185px;">
		<form id="usercourse_info_form" action="new" class="form-horizontal" method="post">
		<div ><input type="hidden" name="teachercourseid"  value="${id}" /></div>
		
			<div class="control-group" id="commentDesc">
			<textarea name="commentDesc" id="commentdescs" placeholder="请输入评论内容" id="c" cols="5" rows="8" style="width:380px;"></textarea>
			<span class="help-inline"></span>
	        </div>
				<div class="control-group" id="mark">
				请选择分数:
				<input type="radio" name="mark" value="0" checked="checked">0
				<input type="radio" name="mark" value="1">1
				<input type="radio" name="mark" value="2">2
				<input type="radio" name="mark" value="3">3
				<input type="radio" name="mark" value="4">4
				<input type="radio" name="mark" value="5">5
				</div>
				
				<div class="control-group">
						<button type="submit" onclick="commentAjax();" class="btn  btn-success">发表评论</button>
				</div>
			</form>
		</div>

