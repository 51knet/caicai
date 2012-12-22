<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="">
	<form name="blog_post" method="post">
	  <legend>写博文</legend>
	  <div class="control-group" >
		  <label class="control-label" style="display:inline-block;width: 40px;">标题:</label>
		  <input type="text" name="title" placeholder="">
	  </div>
      <div class="control-group" >
		  <label class="control-label" style="display:inline-block; width: 40px;">分类:</label>
		  <select name="blogcategory_id">
		  	<c:forEach var="blogCategory" items="${blogCategories}">
		  		<option value="${blogCategory.id}">${blogCategory.name}</option>
		  	</c:forEach>

		  </select>
	  </div>
	  <div class="clearfix"></div>
	  <div class="control-group">
	  	<textarea rows="20" cols="30" id="textarea" name="content" style="width: 98%;"></textarea>
	  </div>
	  <input type="hidden" id="draft" name="draft" value="false">
	  <a href="#" class="btn" id="btn-save-draft">保存草稿</a>
	  <button type="submit" id="btn-submit" class="btn btn-success">发布</button>
	</form>
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
						document.forms['blog_post'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['blog_post'].submit();
					});
				}
			});
			prettyPrint();
			
			$('#btn-save-draft').on('click',function(event){
				event.preventDefault();
				$('#draft').val('true');
				$('#btn-submit').click();
			});
	    });
</script>
	