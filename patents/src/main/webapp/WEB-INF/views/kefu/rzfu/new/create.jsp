<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/img.js" />"></script>
<style type="text/css">
#preview{}
#showimg {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}

</style>
<script type="text/javascript">
function previewImages(file){
	document.getElementById("logoCover").style.display="none";
	previewImage(file);
}

function checkLogo(obj){
	var flag = false;
	var fileValue = obj.coverFile.value;
	var temp = fileValue.substr(fileValue.indexOf('.'),fileValue.length).toLowerCase();
	if(fileValue==null || fileValue==""){
		alert("请添加logo封面");
		flag=false;
	}else{
		if(".gif"==temp || ".jpg"==temp || ".bmp"==temp || ".png" == temp){
			flag=true;
		}else{
			alert("只支持gif、jpg、bmp、png格式的图片！！");
			flag=false;
		}
	}
	return flag;
}
</script>
<style>
.preview_show{
	margin: -25px 0px 10px 90px;
}
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
.span5.custom{
	width: 350px;
}
</style>

<div class="row-fluid custom round">
	<div  class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>孵化园区/融资机构>>添加</h4>
	</div>
	<div class="content row-fluid">
			<span style="margin-left: 14px;">LOGO预览：</span> 
			<div id="preview" class="preview_show">
				<img name="showimg" id="showimg"  style="display: none;" />
			</div>
			<div id="logoCover" class="preview_show">
				<span> <img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width:260px; height:190px;" />
				</span>
			</div>
		<form action= '<c:url value="/admin/kefu/rzfh/add"></c:url>'  method="post" enctype="multipart/form-data"  id="rzfh_form" name="rzfh_post" >
			<!--  --><div class="control-group"> 
			<div class="controls">
				<i class="icon-star"></i> 上传LOGO：<input type="file" name="logoPath"  onChange="previewImages(this);"/> <span style="font-size: 13px; color: red;">${errorMsg }</span>
				<br><span style="color: red;  margin-left: 70px;">只支持jpg、gif、bmp、png格式，建议封面宽度260px，高度190px</span></div>
			</div>
			<div class="control-group" id="name">
				<div class="controls">
					<i class="icon-star"></i> 公司名称：<input type="text" name="name"   placeholder="公司名称" required "> <span class="help-inline"><form:errors path="name" /></span>
				</div>
			</div>
		
			<div class="control-group" id="types">
				<div class="controls">
					<i class="icon-star"></i> 所属类别：
					<select name="types" >		  		
				  			<option value="融资机构" selected>融资机构</option>
				  			<option value="孵化园区" >孵化园区</option>
					</select>
				</div>
			</div>
			<div class="control-group" id="webUrl">
				<div class="controls">
					<i class="icon-star"></i> 网站地址：<input type="text" name="webUrl"   placeholder="网站地址"  required > <span class="help-inline"><form:errors path="webUrl" /></span>
				</div>
			</div>
			
			<div class="control-group" id="content">
				<div class="controls " >
					<i class="icon-star"></i> 公司简介：<br>
					<textarea  style="width:670px;height:150px;"  name="content"  placeholder="公司简介"></textarea>
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
		$(document).ready(function() {
			var editor = KindEditor.create('textarea[name="content"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
				}
			});
			$("#rzfh_form").submit(function(){
				editor.sync();
				return checkEmptyAjax("rzfh_form","createrzfhAjax");
			});
			prettyPrint();
	    });
</script>
