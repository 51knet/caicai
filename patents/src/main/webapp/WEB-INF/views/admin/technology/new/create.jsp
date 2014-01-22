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
		<h4>我的技术>>添加技术</h4>
	</div>
	<div class="content row-fluid">
		<form action= '<c:url value="/admin/technology/new/add"></c:url>'  method="post" id="tech_form" name="tech_post">
		<input type="hidden" value="${technology.id }" name="tech_id">
		<div class="span5" style="width: 340px;">
			<div class="control-group" id="techName">
				<div class="controls">
					<i class="icon-star"></i> 项目名称：<input type="text" name="techName"   placeholder="项目名称" required value="${technology.techName }"> <span class="help-inline"><form:errors path="techName" /></span>
				</div>
			</div>
			<div class="control-group"  > 
				<div class="controls" id="techField" >
				<i class="icon-star"></i> 所属领域：<select name="techField" >		  		
				  		<c:forEach items="${techField }" var="techField" >
					  		<c:choose>
				  				<c:when test="${techField.value == technology.techField }">
				  					<option value="${techField.value }" selected>${techField.value }</option>
				  				</c:when>
				  				<c:otherwise>
				  					<option value="${techField.value}" >${techField.value }</option>
				  				</c:otherwise>
				  			</c:choose>
					  </c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group" id="department">
				<div class="controls">
					<i class="icon-star"></i> 完成单位：<input type="text" name="department"   placeholder="完成单位" required value="${technology.department }"> <span class="help-inline"><form:errors path="department" /></span>
				</div>
			</div>
			
			<div class="control-group" id="department">
				<div class="controls">
					<i class="icon-star"></i> 所 有 人 ：<input type="text" name="inventer"   placeholder="所有人" required value="${technology.inventer}"> <span class="help-inline"><form:errors path="inventer" /></span>
				</div>
			</div>
		
			<div class="control-group" id="phone">
				<div class="controls">
					<i class="icon-star"></i> 联系电话：<input type="text" name="phone"   placeholder="联系电话"  required value="${technology.phone }" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
				</div>
			</div>
			
			<div class="control-group" id="techType">
				<div class="controls">
					 项 目 类 型 ： <input type="text" name="techType"   placeholder="如：863计划"   value="${technology.techType }"> <span class="help-inline"><form:errors path="techType" /></span>
				</div>
			</div>
		</div>
		
		<div class="span5"   style="width: 350px;">
		
			
			<div class="control-group" id="maturity">
				<div class="controls">
					成 熟 程 度 ：<input type="text" name="maturity"   placeholder="成熟程度"   value="${technology.maturity }"> <span class="help-inline"><form:errors path="maturity" /></span>
				</div>
			</div>	
			<div class="control-group" id="progress">
				<div class="controls">
					 进 展 程 度 ：<input type="text" name="progress"   placeholder="进展程度" value="${technology.progress }"> <span class="help-inline"><form:errors path="progess" /></span>
				</div>
			</div>
			
			<div class="control-group" id="applyArea">
				<div class="controls">
					<i class="icon-star"></i> 应用前景：<input type="text" name="applyArea"   placeholder="应用前景" required value="${technology.applyArea }"> <span class="help-inline"><form:errors path="applyArea" /></span>
				</div>
			</div>
			
			<div class="control-group" id="cooperation">
				<div class="controls">
				<i class="icon-star"></i> 合作方式：<input type="text" name="cooperation"   placeholder="合作方式" required value="${technology.cooperation }"> <span class="help-inline"><form:errors path="cooperation" /></span>
				</div>
			</div>
			
			<div class="control-group" id="demand">
				<div class="controls">
					 合 作 要 求 ：<input type="text" name="demand"   placeholder="合作要求" value="${technology.demand }"> <span class="help-inline"><form:errors path="demand" /></span>
				</div>
			</div>
	
		</div>
		
		<div class="control-group" id="contents">
			<div class="controls span11 " >
				<i class="icon-star"></i> 项目简介：<br>
				<textarea  style="width:670px;height:200px;"  name="contents"  placeholder="项目简介">${technology.contents }</textarea>
				<span class="help-inline"><form:errors path="content" /></span>
			</div>
		</div>
		
		<div class="control-group" id="advantage">
			<div class="controls span11 " >
				<i class="icon-star"></i> 产品优点：<br>
				<textarea  style="width:670px;height:200px;"  name="advantage"  placeholder="产品优点">${technology.advantage }</textarea>
				<span class="help-inline"><form:errors path="advantage" /></span>
			</div>
		</div>
		
		<div class="control-group" id="achievement">
			<div class="controls span11 " >
				<i class="icon-star"></i> 获奖情况：（如有专利，请注明专利名称、专利号、申请人）<br>
				<textarea  style="width:670px;height:200px;"  name="achievement"  placeholder="获奖情况">${technology.achievement }</textarea>
				<span class="help-inline"><form:errors path="achievement" /></span>
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
			var editor = KindEditor.create('textarea[name="contents"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
			});
			
			var editor1 = KindEditor.create('textarea[name="advantage"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
			});
			
			var editor2 = KindEditor.create('textarea[name="achievement"]',{
				cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
				uploadJson : '${uploadJson}',
				fileManagerJson : '${fileManagerJson}',
				allowFileManager : true,
			});
			prettyPrint();
			
			$("#tech_form").submit(function(){
				return checkEmptyAjax("tech_form","technologyInfoAJAX");
			});
	    });
</script>
