<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="">
	<form method="post">
	  <legend>编辑博文</legend>
	  <div class="control-group" >
		  <label class="control-label" style="display:inline-block;width: 40px;">标题:</label>
		  <input type="text" name="title" placeholder="" value="${blogPost.title}">
		  <input type="hidden" name="id" value="${blogPost.id}">
	  </div>
	  <div class="control-group" >
		  <label class="control-label" style="display:inline-block;width: 40px;">状态:</label>
		  <select name="draft">
		  	<option value="false" ${blogPost.draft ? "":"selected='selected'"}>已发布</option>
		  	<option value="true" ${blogPost.draft ? "selected='selected'" : ""}>草稿</option> 
		  </select>
	  </div>
      <div class="control-group" >
		  <label class="control-label" style="display:inline-block; width: 40px;">分类:</label>
		  <select name="blogcategory_id">
		  	<c:forEach var="blogCategory" items="${blogCategories}">
		  		<option value="${blogCategory.id}" 
		  			${ (blogCategory.id==blogPost.blogCategory.id) ?"selected='selected'":"" }
		  		>${blogCategory.name}</option>
		  	</c:forEach>
		  </select>
	  </div>
	  
	  <div class="clearfix"></div>
	  <div class="control-group">
	  	<textarea rows="20" cols="30" id="textarea" name="content" style="width: 98%;">
	  	${blogPost.content}
	  	</textarea>
	  </div>
	  <button type="submit" class="btn btn-success">更新</button>
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
	    });
</script>
	