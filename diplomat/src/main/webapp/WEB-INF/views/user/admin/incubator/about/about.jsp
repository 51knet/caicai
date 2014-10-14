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
		<h4>孵化园介绍</h4>
	</div>
	<div class="content">
			<form action= "<c:url value='/admin/incubator/about/new'></c:url>"  method="post" name="incub_post">
				<i>政策支持</i>
				<hr>
				<div class="control-group" id="financSuppt">
					<label class="control-label" for="financSuppt">资金支持</label>
					<div class="controls">
						<input type="text" name="financSuppt" placeholder="资金支持" value="${incubator.financSuppt }"> <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="venueSuppt">
					<label class="control-label" for="venueSuppt">场地优惠</label>
					<div class="controls">
						<input type="text" name="venueSuppt" placeholder="场地优惠" value="${incubator.venueSuppt }"> <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="revenueSuppt">
					<label class="control-label" for="revenueSuppt">税收优惠</label>
					<div class="controls">
						<input type="text" name="revenueSuppt" placeholder="税收优惠" value="${incubator.revenueSuppt}"> <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="accommodatSuppt">
					<label class="control-label" for="accommodatSuppt">住宿优惠</label>
					<div class="controls">
						<input type="text" name="accommodatSuppt" placeholder="住宿优惠" value="${incubator.accommodatSuppt }"> <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="otherSuppt">
					<label class="control-label" for="otherSuppt">其他服务</label>
					<div class="controls">
						<input type="text" name="otherSuppt" placeholder="其他服务" value="${incubator.otherSuppt }"> <span class="help-inline"></span>
					</div>
				</div>
				<i>孵化园相关</i>
				<hr>
				<div class="control-group" id="industry">
					<label class="control-label" for="industry">所属行业</label>
					<div class="controls">
						<input type="text" name="industry" placeholder="所属行业" value="${incubator.industry }"> <span class="help-inline"></span>
					</div>
				</div>
				<textarea cols="100" rows="20" style="width:670px;height:300px;"  name="incubatInfor"  placeholder="孵化园介绍" >
					${incubator.incubatInfor }
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
			var editor = KindEditor.create('textarea[name="incubatInfor"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
				}
			});
			prettyPrint();
			
			$("#patent_form").submit(function(){
				return checkEmptyAjax("patent_form","<c:url value='/admin/announcement/annoInfoAJAX'></c:url>");
			});
	    });
</script>