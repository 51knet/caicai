<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">

function checkResources(obj){
	var tempFlag = false;
	var fileValue = obj.myfiles.value;		
	if(fileValue=="" || fileValue == null){
		$("#myFilesError").html("上传文件不能为空");
		tempFlag =  false;
	}else{
		tempFlag =  true;
	}
	return  tempFlag;
}
	
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
.row-fluid.custom .content{
	margin: 20px 40px;
}
.row-fluid .custom .user-row {
	color: #3d4f67;
}
.error{
	color:#b94a48;
}
</style>

<div class="row-fluid custom round">
	<div  class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>领投人认证</h4>
		<i>请认真填写下列表格</i>
	</div>
	<div class="content row-fluid">
		<form action= '<c:url value="/admin/investvalid/add"></c:url>'  method="post" enctype="multipart/form-data"  id="valid_form" name="valid_post" onsubmit="return checkResources(this)">
			<div class="control-group" id="name">
				<div class="controls">
					<i class="icon-star"></i> 真实姓名：<input  type="text" name="name"   placeholder="真实姓名" > <span class="help-inline"><form:errors path="name" /></span>
				</div>
			</div>
		
			<div class="control-group" id="email">
				<div class="controls">
					<i class="icon-star"></i> 我的邮箱：<input  type="text" name="email"   placeholder="我的邮箱"  > <span class="help-inline"><form:errors path="email" /></span>
				</div>
			</div>
			<div class="control-group" id="phone">
				<div class="controls">
					<i class="icon-star"></i> 联系电话：<input  type="text" name="phone"   placeholder="联系电话"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
				</div>
			</div>
			
			<div class="control-group" id="myfile">
				<div class="controls">
					<i class="icon-star"></i> 我的资料：<input  type="file" name="myfiles"><span id="myFilesError" class="error"></span>
				</div>
			</div>
			
			<div class="control-group" id="content">
				<div class="controls " >
					我的简介：<br>
					<textarea  style="width:670px;height:120px;"  name="content"  placeholder="我的简介"></textarea>
					<span class="help-inline"><form:errors path="content" /></span>
				</div>
			</div>
			
			<div class="span9">
			 	<button type="submit" class="btn btn-success ">保存</button>&nbsp;&nbsp;
				<button type="reset" class="btn">取消</button>
			</div>	

			
		</form>
	</div>
</div>
<c:url var="uploadJson" value="/file_upload/${sessionUserInfo.id}"></c:url>
<c:url var="fileManagerJson" value="/file_manager/${sessionUserInfo.id}"></c:url>
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/themes/default/default.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.js"/>"></script>
<script type="text/javascript">
/*	$(document).ready(function() {
		var editor = KindEditor.create('textarea[name="content"]',{
			cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
			uploadJson : '${uploadJson}',
			fileManagerJson : '${fileManagerJson}',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
	
			}
		});
	
		$("#projects_form").submit(function(){
			editor.sync();
			return checkEmptyAjax("projects_form","projectsInfoAJAX");
		});
		prettyPrint();
    });*/
    
    $(document).ready(function() {
    	$("#valid_form").submit(function(){
			return checkEmptyAjax("valid_form","<c:url value='/admin/investvalid/validInfoAJAX'></c:url>");
		});	
    });
</script>
