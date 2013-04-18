<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<link href="<c:url value="/resources/js/uploadify3.2/uploadify.css" />" rel="stylesheet" type="text/css" />

<script type="text/javascript">

$(document).ready(function() {
	clearInterval(processInterval);
	$("#typeNames").focus(function(){
		$("#typeError").html("");
	});
	
	$("form:first").submit(function(){
		 var myFiles=$("#myFiles").val();
		if(myFiles==""){
			$("#myFilesError").html("上传文件不能为空");
			return false;
		}
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
		<h4>申请信息>添加申请</h4>
	</div>
	<div class="content">
		<form:form action="new/create" method="post" enctype="multipart/form-data" id="resource_form" >  
			申请标题：<input type="text" name="title" placeholder="申请标题" id="auth_title"  /><br>
			申请详情：<textarea style="width: 400px; height: 200px;" name="content" id="auth_content" ></textarea><br>
			上传资料：<input type="file" name="myFiles"  id="myFiles"/>
			<span style="color:red;">单次上传不大于200M</span>
			<br><br>
			<div class="pull-left" style="margin-left: 70px;">
			<button type="submit" class="btn btn-success" >添加</button>&nbsp;&nbsp;
			<button type="reset" class="btn" >取消</button>
			</div>
		</form:form>
	</div>	
</div>