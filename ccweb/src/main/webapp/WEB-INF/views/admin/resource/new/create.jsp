<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
 function returnList(){
	//alert("return list");
	window.location.href='<c:url value="/admin/teacher/resource/list"></c:url>';
 }
 function collectFormData(fields) {
		var data = {};
		for (var i = 0; i < fields.length; i++) {
			var $item = $(fields[i]);
			data[$item.attr('name')] = $item.val();
		}
		return data;
	}

	$(document).ready(function() {
		var $form = $('#resource_type_form');
		$form.bind('submit', function(e) {
			var $inputs = $form.find('input');
			var data = collectFormData($inputs);
			$.post('resourceTypeAJAX', data, function(response) {
				//$form.find('.control-group').removeClass('error');
				//$form.find('.help-inline').empty();
				//$form.find('.alert').remove();
				if (response.status == 'FAIL') {
					for (var i = 0; i < response.errorMessageList.length; i++) {
						var item = response.errorMessageList[i];
						var $controlGroup = $('#' + item.fieldName);
						$controlGroup.addClass('error');
						$controlGroup.find('.help-inline').html(item.message);
					}
				} else {
					$form.unbind('submit');
					$form.submit();
				}
			}, 'json');
	
			e.preventDefault();
			return false;
		});
	});
	$(document).ready(function() {
		var $formResorce = $('#resource_form');
		$formResorce.bind('submit', function(e) {
			var $inputs = $formResorce.find('select');
			var data = collectFormData($inputs);
			$.post('resourceTypeAJAX', data, function(response) {
				//$form.find('.control-group').removeClass('error');
				//$form.find('.help-inline').empty();
				//$form.find('.alert').remove();
				if (response.status == 'FAIL') {
					for (var i = 0; i < response.errorMessageList.length; i++) {
						var item = response.errorMessageList[i];
						var $controlGroup = $('#' + item.fieldName);
						$controlGroup.addClass('error');
						$controlGroup.find('.help-inline').html(item.message);
					}
				} else {
					$formResorce.unbind('submit');
					$formResorce.submit();
				}
			}, 'json');
	
			e.preventDefault();
			return false;
		});
	});
 
 
 
 
 
</script>

<a href='<c:url value="/admin/teacher/resource/list"></c:url>'><b>资源管理</b></a>>>资源添加<hr>
<div style="text-align: center;">
	${user.user["email"] }<br>
	<div style="text-align:center;">
		<div style="width:560px; text-align:left;">
			<form:form action="new/create" method="post" enctype="multipart/form-data" id="resource_form">  
				
				资源描述：<input type="text" name="desc" placeholder="Desc" />&nbsp;<span style="color:red; font-size:14px;">多文件上传添加统一描述</span>
				<!-- <span class="help-block"><form:errors path="desc"></form:errors></span>	 --><br>
				
				<div class="control-group" id="typeName" >
						资源类型：<select name="type"  >
						<c:forEach items="${type}" var="l">
							<option  value="${l.id}">${l.typeName}</option>
						</c:forEach>
						</select>
				&nbsp;&nbsp;<a href="javascript:void(0)"  onclick="showResourceTypeAdd()" style="color:red; font-size:14px;">点击添加新类别</a>
				&nbsp;&nbsp;<span class="help-inline"></span>
					</div>
				<br>
				上传资源：<input type="file" name="myFiles" />&nbsp;&nbsp;<input  type="button" value="添加" onclick="addFile()"/>&nbsp;<span style="color:red;font-size:14px;">单次上传不大于200M</span>
				<br>
				<div id="container" style="margin-left:70px;"></div>
				<br>
				<label style="clear: right;"></label>
				<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
				<button type="reset" class="btn" >Cancel</button>
			</form:form>
		 </div>
		 <br>
		 <div id="addResourType" style="display:none; float: left; border: solid,1px;">
			 	<form action="type/new" method="post"  id="resource_type_form">  
			 	<div class="control-group" id="typeName">
						<div class="controls">
							类别名称:<input type="text"  name="typeName" placeholder="类别名称" >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
						<button type="reset" class="btn" onclick="closeResourceTypeAdd()">Cancel</button>
						</div>
					</div>
				</form>
		 </div> 
	</div>
</div>