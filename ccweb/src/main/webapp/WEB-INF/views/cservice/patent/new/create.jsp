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
		<h4>个人专利>添加专利</h4>
	</div>
	<div class="content">
		<form action= '<c:url value="/admin/patent/add"></c:url>'  method="post" style="margin-left:50px;" id="patent_form" name="patent_post">
			<div class="control-group" id="patentNum">
				<div class="controls">
					<i class="icon-star"></i> 专利号码：<input type="text" name="patentNum"   placeholder="专利号码" required value="${patent.patentNum }"> <span class="help-inline"><form:errors path="patentNum" /></span>
				</div>
			</div>
			<div class="control-group" id="patentType">
				<i class="icon-star"></i>专利类型：
					<select name="patentType" >
					  	 		<c:choose>
				  				<c:when test="${typeList.id == patent.patentType.id }">
				  					<option value="${typeList.id }" selected>${typeList.typeName }</option>
				  				</c:when>
				  				<c:otherwise>
				  					<option value="${typeList.id }" >${typeList.typeName }</option>
				  				</c:otherwise>
				  			</c:choose>
					</select>
			</div>
			
			<div class="control-group" id="patentField">
				<i class="icon-star"></i>适用领域：
					<select name="patentField">
				  			<c:choose>
				  				<c:when test="${fieldList.fieldName  == patent.patentField}">
				  					<option value="${fieldList.fieldName }" selected>${fieldList.fieldName }</option>
				  				</c:when>
				  				<c:otherwise>
				  					<option value="${fieldList.fieldName }" >${fieldList.fieldName }</option>
				  				</c:otherwise>
				  			</c:choose>
					</select>
			</div>
		
			<div class="control-group" id="patentName">
				<div class="controls">
					<i class="icon-star"></i> 专利名称：<input type="text" name="patentName"   placeholder="专利名称"  required value="${patent.patentName }"> <span class="help-inline"><form:errors path="patentName" /></span>
				</div>
			</div>
			
			<div class="control-group" id="mainClassNum">
				<div class="controls">
					<i class="icon-star"></i> 主分类号：<input type="text" name="mainClassNum"   placeholder="主分类号"  required value="${patent.mainClassNum }"> <span class="help-inline"><form:errors path="mainClassNum" /></span>
				</div>
			</div>
			
				<div class="control-group" id="classNum">
				<div class="controls">
					<i class="icon-star"></i> 分类号码：<input type="text" name="classNum"   placeholder="分类号码"  required> <span class="help-inline"><form:errors path="classNum" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applicant">
				<div class="controls">
					<i class="icon-star"></i> 申请人士：<input type="text" name="applicant"   placeholder="申请人士"  required> <span class="help-inline"><form:errors path="applicant" /></span>
				</div>
			</div>
			
			<div class="control-group" id="inventer">
				<div class="controls">
					<i class="icon-star"></i> 发明人士：<input type="text" name="inventer"   placeholder="发明人士" required> <span class="help-inline"><form:errors path="inventer" /></span>
				</div>
			</div>
			
			<div class="control-group" id="publishDate">
				<div class="controls">
					<i class="icon-star"></i> 公开日期：<input type="text" name="publishDate"   placeholder="公开日期" class="Wdate" onClick="WdatePicker()" required> <span class="help-inline"><form:errors path="publishDate" /></span>
				</div>
			</div>
			
			<div class="control-group" id="publishNum">
				<div class="controls">
					<i class="icon-star"></i> 公开号码：<input type="text" name="publishNum"   placeholder="公开号码" required> <span class="help-inline"><form:errors path="publishNum" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applicationDate">
				<div class="controls">
					<i class="icon-star"></i> 申请日期：<input type="text" name="applicationDate"   placeholder="申请日期" class="Wdate" onClick="WdatePicker()" required> <span class="help-inline"><form:errors path="applicationDate" /></span>
				</div>
			</div>
			
			<div class="control-group" id="agency">
				<div class="controls">
					代理机构：<input type="text" name="agency"   placeholder="代理机构" required> <span class="help-inline"><form:errors path="agency" /></span>
				</div>
			</div>
			
			<div class="control-group" id="agent">
				<div class="controls">
					 代理人士：<input type="text" name="agent"   placeholder="代理人士" required> <span class="help-inline"><form:errors path="agent" /></span>
				</div>
			</div>
			
	
			
			<div class="control-group" id="address">
				<div class="controls">
					联系地址：<input type="text" name="address"   placeholder="申请地址" required > <span class="help-inline"><form:errors path="address" /></span>
				</div>
			</div>
			
			<div class="control-group" id="summary">
				<div class="controls">
				<i class="icon-star"></i> 专利摘要：<br>
					<textarea  style="width:600px;height:300px;"  name="summary"  placeholder="专利摘要"></textarea>
					<span class="help-inline"><form:errors path="summary" /></span>
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
			var editor = KindEditor.create('textarea[name="summary"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					KindEditor.ctrl(document, 13, function() {
						self.sync();
						document.forms['patent_post'].submit();
					});
					KindEditor.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['patent_post'].submit();
					});
				}
			});
			$("#patent_form").submit(function(){
				editor.sync();
				return checkEmptyAjax("patent_form","patentInfoAJAX");
			});
			prettyPrint();
	    });
</script>
