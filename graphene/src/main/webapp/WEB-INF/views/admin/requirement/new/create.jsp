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
.row-fluid .custom .user-row {
	color: #3d4f67;
}
</style>
<div class="row-fluid custom round">
	<div  class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>技术需求>添加需求</h4>
	</div>
	<div class="content">
		<form action= '<c:url value="/admin/requirement/add"></c:url>'  method="post"  id="requirement_new_form" name="requirement_post" >
			<div class="control-group" id="title">
				<div class="controls">
						<i class="icon-star"></i> 需求标题： <input type="text" name="title" placeholder="标题"   required> <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>
			
			<div class="control-group" id="money">
				<div class="controls">
					<i class="icon-star"></i> 拟定资金： <input type="text" name="money" placeholder="拟定资金"   required> <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>
			<div class="control-group" id="company">
				<div class="controls">
					<i class="icon-star"></i> 公司名称： <input type="text" name="company" placeholder="公司名称"  required> <span class="help-inline"><form:errors path="company" /></span>
				</div>
			</div>
			<div class="control-group" id="name">
				<div class="controls">
						<i class="icon-star"></i> 联系人士： <input type="text" name="name" placeholder="联系人士"  required> <span class="help-inline"><form:errors path="name" /></span>
				</div>
			</div>
			
			<div class="control-group" id="phone">
				<div class="controls">
						<i class="icon-star"></i> 联系电话： <input type="text"  required name="phone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
				</div>
			</div>
			<div class="control-group" id="address">
				<div class="controls">
						<i class="icon-star"></i> 联系地址： <input type="text"  required name="address" placeholder="联系地址"> <span class="help-inline"><form:errors path="address" /></span>
				</div>
			</div>
			
			<div class="control-group" id="title">
				<div class="controls">
						<i class="icon-star"></i> 截止日期： <input type="text"  required name="endTime" placeholder="截止日期" class="Wdate" onClick="WdatePicker()"> <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>
			
			<div class="control-group" id="content">
				<div class="controls">
				<i class="icon-star"></i> 需求简介：<br>
					<textarea  style="width:670px; height: 100px;"  name="content"  placeholder="需求简介"></textarea>
					<span class="help-inline"><form:errors path="content" /></span>
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
						document.forms['requirement_post'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['requirement_post'].submit();
					});
				}
			});
			$("#requirement_new_form").submit(function(){
				editor.sync();
				return checkEmptyAjax("requirement_new_form","new/createRequirementAjax");
			});
			prettyPrint();
	    });
</script>