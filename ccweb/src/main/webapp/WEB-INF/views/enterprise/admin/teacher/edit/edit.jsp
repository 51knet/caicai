<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
	document.getElementById("courseCover").style.display="none";
	previewImage(file);
}

function checkTeacherPicture(obj){
	var flag = false;
	var fileValue = obj.coverFile.value;
	var temp = fileValue.substr(fileValue.indexOf('.'),fileValue.length);
	if(fileValue==null || fileValue==""){
		flag=true;
	}else{
		if(".gif"==temp || ".jpg"==temp || ".bmp"==temp || ".png" == temp){
			flag=true;
		}else{
			alert("只支持gif、jpg、bmp格式的图片！！");
			flag=false;
		}
	}
	return flag;
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
</style>
<div class="row-fluid custom round">
	<div class="row" >
	<h4>教师管理>教师修改</h4> 
	</div>	
	<div class="content">
		<div class="tabbable">
			<div class="tab-content">
				<div style=" text-align: left;">
					<span style="margin-left: 14px;">头像预览：</span> 
					<div id="preview" style="margin-left:90px;margin-top: -12px;">
						<img name="showimg" id="showimg" src="" style="display: none;" />
					</div>
					<div id="courseCover" style="margin-top: -16px;">
							<img src='<c:url value="${eTeacher.photourl}"> </c:url>' style="width: 260px; height: 195px; margin-left: 100px;" />
					</div>
					<form:form action="update" method="post" enctype="multipart/form-data" id="teacher_info_form" onsubmit="return checkTeacherPicture(this)">
						<input type="hidden" value="${eTeacher.id }" name="eteacherid">
						<div class="modal-body"> 
							上传头像：<input type="file" name="coverFile" style="margin-bottom: 10px; width: 350px;" onChange="previewImages(this);"/> <span style="font-size: 13px; color: red;">${errorMsg }</span>
							<br><span style="color: red;  margin-left: 70px;">只支持jpg、gif、bmp、png格式，建议图片宽度260px，高度195px</span>
						</div>
						<div class="control-group" id="content">
							<div class="controls" style="margin-left: 13px;">
							教师描述：
							<div style="margin-left: 70px; margin-top: -20px;">
								<textarea id="KEcontent" name="content" rows="8" cols="5" style="width: 500px; height: 300px;">${eTeacher.content }</textarea>
								<span class="help-inline"></span>
							</div>
							</div>
						</div>
						<div class="modal-body" style="text-align: center;">
							<button type="submit" class="btn  btn-success">提交</button>
						</div>
					</form:form>
				</div>
				</div>
			</div>
		</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var teacherDescEditor = KindEditor.create('textarea[name="content"]',{
			cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
			uploadJson : '${uploadJson}',
			fileManagerJson : '${fileManagerJson}',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				KindEditor.ctrl(document, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
				KindEditor.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
			}
		});
		
	});
	
</script>