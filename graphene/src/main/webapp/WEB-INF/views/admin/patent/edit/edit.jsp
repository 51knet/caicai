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
	color: #3d4f67;
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
	<div  class="row ">
		<h4>我的专利>专利修改</h4>
	</div>
	<div class="content row-fluid">
		<form action= '<c:url value="/admin/patent/edit/add"></c:url>'  method="post"  id="patent_form" name="patent_post">
		<input type="hidden" name="patent_id" value="${patent.id }">
		<div class="span5" style="width: 340px;">
			<div class="control-group" id="patentNum">
				<div class="controls">
					<i class="icon-star"></i> 专利号码：<input type="text" name="patentNum"   placeholder="专利号码" required value="${patent.patentNum }" readonly="readonly"> <span class="help-inline"><form:errors path="patentNum" /></span>
				</div>
			</div>
			<div class="control-group" id="patentType">
				<i class="icon-star"></i>专利类型：
					<select name="patentType" >
						<c:forEach items="${pTypeList }" var="typeList" >
					  		<c:choose>
				  				<c:when test="${typeList.id == patent.patentType.id }">
				  					<option value="${typeList.id }" selected>${typeList.typeName }</option>
				  				</c:when>
				  				<c:otherwise>
				  					<option value="${typeList.id }" >${typeList.typeName }</option>
				  				</c:otherwise>
				  			</c:choose>
					  </c:forEach>
					</select>
			</div>
			
			<div class="control-group" id="patentField">
				<i class="icon-star"></i>适用领域：
					<select name="patentField">
					   <c:forEach items="${pFieldList }" var="fieldList" >
					   		<c:choose>
				  				<c:when test="${fieldList.fieldName  == patent.patentField}">
				  					<option value="${fieldList.fieldName }" selected>${fieldList.fieldName }</option>
				  				</c:when>
				  				<c:otherwise>
				  					<option value="${fieldList.fieldName }" >${fieldList.fieldName }</option>
				  				</c:otherwise>
				  			</c:choose>
					  </c:forEach>
					</select>
			</div>
		
			<div class="control-group" id="patentName">
				<div class="controls">
					<i class="icon-star"></i> 专利名称：<input type="text" name="patentName"   placeholder="专利名称"  required value="${patent.patentName }"> <span class="help-inline"><form:errors path="patentName" /></span>
				</div>
			</div>
			<div class="control-group"  > 
				<div class="controls">
						<i class="icon-star"></i> 专利范围：
					<label class="radio inline" style="margin-top: -8px;">
						<input type="radio" name="country" value="0" <c:if test="${patent.country ==0 }"> checked</c:if> >国内
					</label>
					<label class="radio inline" style="margin-top: -8px;">
						<input type="radio" name="country" value="1"  <c:if test="${patent.country ==1 }"> checked</c:if>>国外
					</label>
				</div>
			</div>
			<div class="control-group" id="mainClassNum">
				<div class="controls">
					<i class="icon-star"></i> 主分类号：<input type="text" name="mainClassNum"   placeholder="主分类号"  required value="${patent.mainClassNum }"> <span class="help-inline"><form:errors path="mainClassNum" /></span>
				</div>
			</div>
			
				<div class="control-group" id="classNum">
				<div class="controls">
					<i class="icon-star"></i> 分类号码：<input type="text" name="classNum"   placeholder="分类号码"  required value="${patent.classNum }"> <span class="help-inline"><form:errors path="classNum" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applicant">
				<div class="controls">
					<i class="icon-star"></i> 申请人士：<input type="text" name="applicant"   placeholder="申请人士"  required value="${patent.applicant }"> <span class="help-inline"><form:errors path="applicant" /></span>
				</div>
			</div>
		</div>
		<div class="span5" style="width: 340px;">
			<div class="control-group" id="inventer">
				<div class="controls">
					<i class="icon-star"></i> 发明人士：<input type="text" name="inventer"   placeholder="发明人士" required value="${patent.inventer }"> <span class="help-inline"><form:errors path="inventer" /></span>
				</div>
			</div>
			
			<div class="control-group" id="publishDate">
				<div class="controls">
					<i class="icon-star"></i> 公开日期：<input type="text" name="publishDate"   placeholder="公开日期" class="Wdate" onClick="WdatePicker()" required value="${patent.publishDate }"> <span class="help-inline"><form:errors path="publishDate" /></span>
				</div>
			</div>
			
			<div class="control-group" id="publishNum">
				<div class="controls">
					<i class="icon-star"></i>公开号码：<input type="text" name="publishNum"   placeholder="公开号码" required value="${patent.publishNum }"> <span class="help-inline"><form:errors path="publishNum" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applicationDate">
				<div class="controls">
					<i class="icon-star"></i> 申请日期：<input type="text" name="applicationDate"   placeholder="申请日期" class="Wdate" onClick="WdatePicker()" required value="${patent.applicationDate }"> <span class="help-inline"><form:errors path="applicationDate" /></span>
				</div>
			</div>
			
			<div class="control-group" id="agency">
				<div class="controls">
					代 理 机 构 ： <input type="text" name="agency"   placeholder="代理机构" required value="${patent.agency }"> <span class="help-inline"><form:errors path="agency" /></span>
				</div>
			</div>
			
			<div class="control-group" id="agent">
				<div class="controls">
					 代 理 人 士 ： <input type="text" name="agent"   placeholder="代理人士" required value="${patent.agent }"> <span class="help-inline"><form:errors path="agent" /></span>
				</div>
			</div>
			
			<div class="control-group" id="address">
				<div class="controls">
					 联 系 地 址 ： <input type="text" name="address"   placeholder="申请地址" required value="${patent.address }" > <span class="help-inline"><form:errors path="address" /></span>
				</div>
			</div>
		</div>
	
		<div class="control-group" id="summary">
			<div class="controls span11 " >
				<i class="icon-star"></i> 专利摘要：<br>
				<textarea  style="width:670px;height:300px;"  name="summary"  placeholder="专利摘要">${patent.summary }</textarea>
				<span class="help-inline"><form:errors path="summary" /></span>
			</div>
		</div>
		
		<div class="span9">
		 	<button type="submit" class="btn btn-success ">保存</button>&nbsp;&nbsp;
			<button type="reset" class="btn">取消</button>
		</div>	
		</form>
	</div>
</div>
<script type="text/javascript">
	function checkPatentFormEmptyAjax(){
		return checkEmptyAjax("patent_form","new/patentInfoAJAX");
	}; 
</script>

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
				return checkEmptyAjax("patent_form","new/patentInfoAJAX");
			});
			prettyPrint();
	    });
</script>
