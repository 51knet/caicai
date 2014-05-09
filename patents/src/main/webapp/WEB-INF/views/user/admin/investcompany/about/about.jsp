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

.row-fluid .custom .row {
	margin: 10px 40px;
	color: #3d4f67;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid .custom .user-row {
	color: #3d4f67;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div  class="row">
		<h4>机构介绍</h4>
	</div>
	<div class="content">
			<form action= "<c:url value='/admin/investcompany/about/new'></c:url>"  method="post" name="about_post">
				<input type="hidden" value="${comInfor.id }" name="cominfor_id" />
				<div class="control-group" id="investRange">
					<label class="control-label" for="investRange">投资额度</label>
					<div class="controls">
						<input type="text" name="investRange" placeholder="投资额度" value="${comInfor.investRange }"> <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="investField">
					<label class="control-label" for="investField">投资领域</label>
					<div class="controls">
						<input type="text" name="investField" placeholder="投资领域" value="${comInfor.investField }"> <span class="help-inline"></span>
					</div>
				</div>
				<textarea cols="100" rows="20" style="width:670px;height:300px;"  name="companyinfo"  placeholder="机构介绍" >
					${comInfor.companyinfo }
				</textarea>
				<span class="help-block"><form:errors path="companyinfo"></form:errors></span>
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
			var editor = KindEditor.create('textarea[name="companyinfo"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
				}
			});
			prettyPrint();
			
	    });
</script>