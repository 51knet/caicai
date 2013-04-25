<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<link href="<c:url value="/resources/js/uploadify3.2/uploadify.css" />" rel="stylesheet" type="text/css" />

<script type="text/javascript">

$(document).ready(function() {
	$("#authencation_form").submit(function(){
		//this.sync();
		return checkEmptyAjax('authencation_form','<c:url value="/admin/caicai/authentication/refuseAjax"></c:url>');
	});
	
});
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>申请信息>拒绝原因</h4>
	</div>
	<div class="content">
		<form:form action='new' method="post"  id="authencation_form" > 
			<input type="hidden" value="${authentication.id }" name="auth_id" /> 
			<div class="control-group" id="reason">
				<div class="controls">
					拒绝原因：<textarea style="width: 400px; height: 200px;" name="reason" ></textarea><span class="help-inline"></span>
				</div>
			</div>
			<div class="pull-left" style="margin-left: 70px;">
			<button type="submit" class="btn btn-success" >添加</button>&nbsp;&nbsp;
			<button type="reset" class="btn" >取消</button>
			</div>
		</form:form>
	</div>	
</div>

