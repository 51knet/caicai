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
		<h4>专利需求>添加需求</h4>
	</div>
	<div class="content">
		<form action= '<c:url value="/admin/patentRequirement/add"></c:url>'  method="post"  id="requirement_new_form" name="requirement_post" >
			项目基本信息
			<hr>
			<div class="control-group" id="title">
				<div class="controls">
						<i class="icon-star"></i> 需求名称： <input required="required" required="required" type="text" name="title" placeholder="需求名称"> <span class="help-inline"><form:errors path="title" /></span>
				</div>
			</div>	
			<div class="control-group" id="requirementField">
				<div class="controls">
						<i class="icon-star"></i> 所属领域： <input required="required" required="required" type="text" name="requirementField" placeholder="所属领域"> <span class="help-inline"><form:errors path="requirementField" /></span>
				</div>
			</div>	
			<div class="control-group" id="patentType">
				<i class="icon-star"></i> 专利类型：
					<select name="patentType" >
				  			<c:forEach items="${pTypeList }" var="typeList">
				  				<option value="${typeList.id }" >${typeList.typeName }</option>
				  			</c:forEach>
					</select>
			</div>
			<div class="control-group" id="cooperation">
				<div class="controls">
						<i class="icon-star"></i> 合作方式： <input required="required" required="required" type="text" name="cooperation" placeholder="合作方式"> <span class="help-inline"><form:errors path="cooperation" /></span>
				</div>
			</div>	
			<div class="control-group" id="money">
				<div class="controls">
					<i class="icon-star"></i> 拟定资金： <input required="required" required="required" type="text" name="money" placeholder="拟定资金"> <span class="help-inline"><form:errors path="money" /></span>
				</div>
			</div>
			<div class="control-group" id="content">
				<div class="controls">
					<i class="icon-star"></i> 需求简介：<br>
					<textarea  style="width:600px;height:300px;"  name="content"  placeholder="需求简介"></textarea>
					<span class="help-inline"><form:errors path="content" /></span>
				</div>
			</div>
			<br>
			联系信息<hr>
			<div class="control-group" id="company">
				<div class="controls">
					<i class="icon-star"></i> 公司名称： <input required="required" required="required" required="required" required="required" type="text" name="company" placeholder="公司名称"> <span class="help-inline"><form:errors path="company" /></span>
				</div>
			</div>
			<div class="control-group" id="contact">
				<div class="controls">
						<i class="icon-star"></i> 联系人士： <input required="required" required="required" required="required" type="text" name="contact" placeholder="联系人士"> <span class="help-inline"><form:errors path="contact" /></span>
				</div>
			</div>
			
			<div class="control-group" id="phone">
				<div class="controls">
						<i class="icon-star"></i> 联系电话： <input required="required" required="required" required="required" type="text" name="phone" placeholder="联系电话" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
				</div>
			</div>
			<div class="control-group" id="fax">
				<div class="controls">
						<i class="icon-star"></i> 联系传真： <input required="required" required="required" required="required" type="text" name="fax" placeholder="联系传真" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="fax" /></span>
				</div>
			</div>
			<div class="control-group" id="email">
				<div class="controls">
						<i class="icon-star"></i> 联系邮箱： <input required="required" required="required" required="required" type="text" name="email" placeholder="联系邮箱" > <span class="help-inline"><form:errors path="email" /></span>
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
				}
			});
			prettyPrint();
			
			$("#requirement_new_form").submit(function(){
				return checkEmptyAjax("requirement_new_form","new/createPatentRequirementAjax");
			});
	    });
</script>