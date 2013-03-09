<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<link href=" <c:url value="/resources/js/uploadify/css/default.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/js/uploadify/css/uploadify.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify/js/jquery.uploadify.v2.0.1.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify/js/swfobject.js" />"></script>
<script type="text/javascript">
 function addFile(){
 	var cont = document.getElementById("container");
 	var file = document.createElement("input");
 	file.type="file";
 	file.name="myFiles";
 	var btn = document.createElement("input");
 	btn.type="button";
 	btn.value="删除";
 	var br = document.createElement("br");
 	cont.appendChild(file);
 	cont.appendChild(btn);
 	cont.appendChild(br);
 	btn.onclick=function(){
 		cont.removeChild(file);
 		cont.removeChild(btn);
 		cont.removeChild(br);
 	}
 }
 
  
 function showResourceTypeAdd(){
	 var resourceType = document.getElementById("addResourType");
	 resourceType.style.display="block";
 }

 function closeResourceTypeAdd(){
	 var resourceType = document.getElementById("addResourType");
	 resourceType.style.display="none";
 } 

 function resourceOnclick(){
	 return checkEmptyAjax("resource_type_form","resourceTypeAJAX");
	 return false;
 }
$(document).ready(function() {
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
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>资源管理>添加资源</h4>
	</div>
	<div class="row1">
		<div style="text-align:center;">
			<div style="width:560px; text-align:left;">
				<form:form action="new/create" method="post" enctype="multipart/form-data" id="resource_form">  
					资源描述：<input type="text" name="desc" placeholder="资源描述" />&nbsp;<span style="color:red; font-size:14px;">多文件上传添加统一描述</span>
					<!-- <span class="help-block"><form:errors path="desc"></form:errors></span>	 --><br>
					<div class="control-group"  >
							资源类型：<select name="type"  >
							<c:forEach items="${type}" var="l">
								<option  value="${l.id}">${l.typeName}</option>
							</c:forEach>
							</select>
					<!-- &nbsp;&nbsp;<a href="javascript:void(0)"  onclick="showResourceTypeAdd()" style="color:red; font-size:14px;">点击添加新类别</a> -->
					</div>
					上传资源：<input type="file" name="myFiles"  id="myFiles"/>&nbsp;&nbsp;<input  type="button" value="添加" onclick="addFile()"/>&nbsp;
					<span style="color:red;font-size:14px;">单次上传不大于200M</span>
					<span style="color:red;font-size:14px;" id="myFilesError"></span>
					<br>
					<div id="container" style="margin-left:70px;"></div>
					<br>
					<label style="clear: right;"></label>
					<button type="submit" class="btn btn-success" onclick="typeNameOnclick();">添加</button>&nbsp;&nbsp;
					<button type="reset" class="btn" >取消</button>
				</form:form>
			 </div>
			 <br>
			 
			 <div id="addResourType" style="display:none; float: left; border: solid,1px;">
				 	<form action="type/new" method="post"  id="resource_type_form">  
				 	<div class="control-group" id="typeName">
							<div class="controls">
								类别名称:<input type="text"  id="typeNames" name="typeName" placeholder="类别名称" >
								<span class="help-inline" id="typeError"></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return resourceOnclick();" class="btn btn-success">添加</button>&nbsp;&nbsp;
								<button type="reset" class="btn" onclick="closeResourceTypeAdd()">取消</button>
							</div>
						</div>
					</form>
			 </div> 
		</div>
		
		<!-- test uploadify  -->
		<!-- 
		<div id="testUploadify" style="margin-top: 20px;">
			<div id="fileQueue" style="height: 100px; padding-left: 10px; display: block;"></div>
			<form id="course_form">
				课时：<input type="text" name="rdesc" id="courseOrder"  /><br>
			</form>
			<input type="file" name="uploadify"  id="uploadify_test" />
			<p>
			<a href="javascript: $('#uploadify_test').uploadifySettings('scriptData',{'rdesc':$('#courseOrder').val()}); jQuery('#uploadify_test').uploadifyUpload()">开始上传</a>&nbsp;
			<a href="javascript:jQuery('#uploadify_test').uploadifyClearQueue()">取消所有上传</a>
			</p>
			<script type="text/javascript">
				$(document).ready(function() {
					$("#uploadify_test").uploadify({
						'uploader'       : '<c:url value="/resources/js/uploadify/uploadify.swf" />',
						'script'        	    : '<c:url value="/admin/resource/new/new" />',
						'cancelImg'     : '<c:url value="/resources/js/uploadify/images/cancel.png" />',
					//	'scriptData'     : $("#course_form").serialize(),
					//	'scriptData'		:{'courseOrder': $("#courseOrder").val()},
						'folder'         	: 'uploads',
						'queueID'        : 'fileQueue',
						'method'			:'post',
						'auto'           : false,
						'multi'          : true,
						'simUploadLimit' : 2,
						'buttonText'	 : 'search',
						'onComplete': function(event, queueID, fileObj,response,data) {//当上传完成后的回调函数，ajax方式哦~~
							alert("-------");
							alert(data.speed); 
						}
					});
				});
			</script>
		</div>
		 -->
	</div>	
</div>