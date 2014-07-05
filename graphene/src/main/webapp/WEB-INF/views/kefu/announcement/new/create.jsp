<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
	<div  class="row">
		<h4>新闻管理>添加新闻</h4>
	</div>
	<div class="content">
		<form action= "<c:url value='/admin/kefu/news/new'></c:url>"  method="post" name="anno_post" id="announcement_new_form"  enctype="multipart/form-data"  onsubmit="return checkPicture(this)">
			<div class="control-group" id="title">
				<label class="control-label" for="title">新闻标题</label>
				<div class="controls">
					<input type="text" name="title" placeholder="新闻标题"> <span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" id="content">
				<div class="controls">
					<textarea  style="width:670px;height:300px;"  name="content"  placeholder="公告内容"></textarea>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group" >
				<div class="controls">
					轮换图片：<input type="file" name="coverFile" /><span style="font-size: 13px; color: red;">${errorMsg }</span>
									<br><span style="color: red;  margin-left: 70px;">只支持jpg、gif、bmp、png格式，建议封面宽度440px，高度250px</span>
					<span class="help-inline"></span><br>
				</div>
			</div>
			<label style="clear: right;"></label>
			<button type="submit" class="btn btn-success">保存</button>&nbsp;&nbsp;
			<button type="reset" class="btn">取消</button>
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
					var self = this;
					KindEditor.ctrl(document, 13, function() {
						self.sync();
						document.forms['anno_post'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['anno_post'].submit();
					});
				}
			});
			$("#announcement_new_form").submit(function(){
				editor.sync();
				return checkEmptyAjax("announcement_new_form","createAnnouncementAjax");
			});
			prettyPrint();
	    });
</script>