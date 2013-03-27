<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<link href="<c:url value="/resources/js/uploadify3.2/uploadify.css" />" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var processInterval=null;
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
 
 function showProcessBar(){
	 //processInterval=setInterval(process, 100);
	 var img = "<img src='<c:url value="/resources/img/jinduL.gif "></c:url>'  />"; 
	 if($("#myFiles").val()!=null || $("#myFiles").val()!=""){
		 $("#processbar").css("display","block");
	 }
	 
 }
 
 function process(){
	 alert("======process");
	 $.ajax({
			url : '<c:url value="/resource/processbar"  />',
			type : 'post',
			dataType : 'text',
			success : function(html) {
				alert(html);
				$("#bar").css("width",html+"%");
			}
		});
 }
 
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
		<h4>资源管理>添加资源</h4>
	</div>
	<div class="content">
		<div style="text-align:center;">
	<!-- <div class="container">
			<div class="progress span5">
				<div class="bar" id="bar" style="width: 0%; background-color: #000;"></div>
			</div>
		</div> -->	
			<div style="width:560px; text-align:left;">
				<div id="processbar" style="margin-bottom: 3px; display: none"><img src='<c:url value="/resources/img/jinduL.gif "></c:url>'  /></div>
				<form:form action="new/create" method="post" enctype="multipart/form-data" id="resource_form" onsubmit="return showProcessBar()" >  
					资源描述：<input type="text" name="desc" placeholder="资源描述" />&nbsp;<span style="color:red; font-size:14px;">多文件上传添加统一描述</span>
					<div class="control-group"  >
							资源类型：<select name="type"  >
							<c:forEach items="${type}" var="l">
								<option  value="${l.id}">${l.typeName}</option>
							</c:forEach>
							</select>
					<!-- &nbsp;&nbsp;<a href="javascript:void(0)"  onclick="showResourceTypeAdd()" style="color:red; font-size:14px;">点击添加新类别</a> -->
					</div>
					上传资源：<input type="file" name="myFiles"  id="myFiles"/>&nbsp;&nbsp;<input  type="button" value="添加" onclick="addFile()"/>&nbsp;
					<span style="color:red;">单次上传不大于200M</span>
					<span style="color:red;" id="myFilesError"></span>
					<br>
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
		
 
	 <!-- test uploadify 3.2 -->
<!-- 
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/uploadify3.2/jquery.uploadify.min.js" />"></script>
		<script type="text/javascript">
			$(function() {
			    $('#file_upload').uploadify({
			    	'auto'     : false,//关闭自动上传
			    	'removeTimeout' : 1,//文件队列上传完成1秒后删除
			        'swf'      : '<c:url value="/resources/js/uploadify3.2/uploadify.swf" />',
			        'uploader' : '<c:url value="/admin/resource/new/new;jsessionid=${sessionScope.sessionUserInfo.id}" />',
			      
			        'method'   : 'post',//方法，服务端可以用$_POST数组获取数据
					'buttonText' : '选择图片',//设置按钮文本
			        'multi'    : true,//允许同时上传多张图片
			        'uploadLimit' : 10,//一次最多只允许上传10张图片
			        'fileTypeDesc' : 'Image Files',//只允许上传图像
			        'fileTypeExts' : '*.gif; *.jpg; *.png',//限制允许上传的图片后缀
			        'fileSizeLimit' : '20000KB',//限制上传的图片不得超过200KB 
			        'onUploadSuccess' : function(file, data, response) {//每次成功上传后执行的回调函数，从服务端返回数据到前端
						   alert(data);
			        },
			        'onQueueComplete' : function(queueData) {//上传队列全部完成后执行的回调函数
			           // if(img_id_upload.length>0)
			           // alert('成功上传的文件有：'+encodeURIComponent(img_id_upload));
			        }  
			        // Put your options here
			    });
			});
			</script>

	<input type="hidden"  name="sessionid" id="sessionid" value="${sessionScope.sessionUserInfo.id}">
	<input type="file" name="file_upload" id="file_upload" />

	<p><a href="javascript:$('#file_upload').uploadify('settings', 'formData', {'rdesc':$('#id_file').val() , 'sessionid': $('#sessionid').val() }) ;$('#file_upload').uploadify('upload','*')">上传</a>
	<a href="javascript:$('#file_upload').uploadify('cancel','*')">重置上传队列</a>
	</p>
	<input type="text"  name="rdesc" id="id_file">
 -->
	 
	</div>	
</div>