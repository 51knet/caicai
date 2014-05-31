<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">

function checkPersonResources(obj){
	var tempFlag = false;
	var fileValue = obj.myfiles.value;
	if(fileValue=="" || fileValue == null){
		$("#myFilesError").html("上传文件不能为空");
		tempFlag =  false;
	}else{
		tempFlag =  true;
	}
	return  tempFlag;
}

function checkComResources(obj){
	var tempFlag = false;
	var bizLic = obj.bizLic.value;
	var orgCode = obj.orgCode.value;
	if((bizLic ==""||bizLic == null) && (orgCode=="" || orgCode==null)){
		$("#bizLicError").html("营业执照复印件不能为空");
		$("#orgCodeError").html("组织机构代码不能为空");
		tempFlag = false;
	}else{
		tempFlag = true;
	}
	return tempFlag;

}
	
</script>
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
.error{
	color:#b94a48;
}
</style>

<div class="row-fluid custom round">
	<div  class="row <c:if test="${sessionUserInfo.role == 'user'}">user-row</c:if>">
		<h4>权限申请</h4>
		<i>请认真填写下列表格</i>
	</div>
	<div class="content">
		
		<div class="tabbable">
			<ul class="nav nav-tabs">
		    	<li <c:if test='${active == "invest"}'>class="active"</c:if>><a href="#invest_tab" data-toggle="tab">个人申请</a></li> 
		    	<li ><a href="#company_tab" data-toggle="tab">机构公司申请</a></li>
		    	<li ><a href="#expert_tab" data-toggle="tab">专家申请</a></li>
			</ul>
			<div class="tab-content">
						
				<div class="tab-pane <c:if test='${active == "invest"}'>active</c:if>" id="invest_tab">
					<form action= '<c:url value="/admin/applyright/person/add"></c:url>'  method="post" enctype="multipart/form-data"  id="valid_form" name="valid_post" onsubmit="return checkPersonResources(this)">
					<div class="control-group" >
						<div class="controls">
							<label class="radio inline">
							  <input type="radio" checked="checked"  name="applypermit" value="investor"> 投资人
							</label>
							<label class="radio inline">
							  <input type="radio"     name="applypermit" value="ledinvestor"> 领投人
							</label>
						</div>
					</div>
					<div class="control-group" id="name">
						<div class="controls">
							<i class="icon-star"></i> 真实姓名：<input  type="text" name="name"   placeholder="真实姓名" > <span class="help-inline"><form:errors path="name" /></span>
						</div>
					</div>
					<div class="control-group" id="idNum">
						<div class="controls">
							<i class="icon-star"></i> 身份证号：<input  type="text" name="idNum"   placeholder="身份证号"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="idNum" /></span>
						</div>
					</div>
					<div class="control-group" id="phone">
						<div class="controls">
							<i class="icon-star"></i> 联系电话：<input  type="text" name="phone"   placeholder="联系电话"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
						</div>
					</div>
					<div class="control-group" id="myfile">
							<div class="controls">
								<i class="icon-star"></i> 我的资料：<input  type="file" name="myfiles"><span id="myFilesError" class="error"></span>
								<br><i>包括身份证复印件或扫描件，投资成功案例等</i>
							</div>
						</div>
					
					<div class="control-group" id="content">
						<div class="controls " >
							我的简介：<br>
							<textarea  style="width:670px;height:120px;"  name="content"  placeholder="我的简介"></textarea>
							<span class="help-inline"><form:errors path="content" /></span>
						</div>
					</div>
					
					<div class="span9">
					 	<button type="submit" class="btn btn-success ">保存</button>&nbsp;&nbsp;
						<button type="reset" class="btn">取消</button>
					</div>	
				</form>
				</div>
				
				<div class="tab-pane <c:if test='${active == "company"}'>active</c:if>" id="company_tab">
					<form action= '<c:url value="/admin/applyright/company/add"></c:url>'  method="post" enctype="multipart/form-data"  id="co_valid_form" name="co_valid_post" onsubmit="return checkComResources(this)">
						<div class="control-group" >
							<div class="controls">
								<label class="radio inline">
								  <input type="radio"  name="comApplypermit" value="investcompany" checked="checked" > 投资机构
								</label>
								<label class="radio inline">
								  <input type="radio"  name="comApplypermit" value="incubator"> 孵化基地
								</label>
							</div>
						</div>
						<div class="control-group" id="boss">
							<div class="controls">
								<i class="icon-star"></i> 法人姓名：<input  type="text" name="boss"   placeholder="法人姓名" > <span class="help-inline"><form:errors path="boss" /></span>
							</div>
						</div>
						<div class="control-group" id="bossId">
							<div class="controls">
								<i class="icon-star"></i> 身份证号：<input  type="text" name="bossId"   placeholder="身份证号"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="bossId" /></span>
							</div>
						</div>
						<div class="control-group" id="comName">
							<div class="controls">
								<i class="icon-star"></i> 公司名称：<input  type="text" name="comName"   placeholder="公司名称" > <span class="help-inline"><form:errors path="comName" /></span>
							</div>
						</div>
						<div class="control-group" id="bossPhone">
							<div class="controls">
								<i class="icon-star"></i> 联系电话：<input  type="text" name="bossPhone"   placeholder="联系电话"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="bossPhone" /></span>
							</div>
						</div>
						<div class="control-group" id="bizLic">
							<div class="controls">
								<i class="icon-star"></i> 营业执照复印件：<input  type="file" name="bizLic"><span id="bizLicError" class="error"></span>
							</div>
						</div>
						<div class="control-group" id="orgCode">
							<div class="controls">
								<i class="icon-star"></i> 组织机构代码证：<input  type="file" name="orgCode"><span id="orgCodeError" class="error"></span>
							</div>
						</div>
						<div class="control-group" id="comContent">
							<div class="controls " >
								简介：<br>
								<textarea  style="width:670px;height:120px;"  name="comContent"  placeholder="简介"></textarea>
								<span class="help-inline"><form:errors path="comContent" /></span>
							</div>
						</div>					
						<div class="span9">
						 	<button type="submit" class="btn btn-success ">保存</button>&nbsp;&nbsp;
							<button type="reset" class="btn">取消</button>
						</div>	
					</form>
				</div>
				
				<div class="tab-pane <c:if test='${active == "expert"}'>active</c:if>" id="expert_tab">
					<form action= '<c:url value="/admin/applyright/expert/add"></c:url>'  method="post"   id="expert_form" >
						<div class="control-group" id="expname">
							<div class="controls">
								<i class="icon-star"></i> 真实姓名：<input  type="text" name="expname"   placeholder="真实姓名" > <span class="help-inline"><form:errors path="expname" /></span>
							</div>
						</div>
						<div class="control-group" >
							<div class="controls">
								<label class="radio inline">
								  <input type="radio"  name="expgender" value="男" checked="checked" > 男
								</label>
								<label class="radio inline">
								  <input type="radio"  name="expgender" value="女"> 女
								</label>
							</div>
						</div>
						<div class="control-group" id="expcollege">
							<div class="controls">
								<i class="icon-star"></i> 所属高校：<input  type="text" name="expcollege"   placeholder="所属高校"  style="margin: 0 auto;" data-provide="typeahead" data-items="8"
									data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'> <span class="help-inline"><form:errors path="expcollege" /></span>
							</div>
						</div>					
						<div class="control-group" id="expschool">
							<div class="controls">
								<i class="icon-star"></i> 所属院系：<input  type="text" name="expschool"   placeholder="所属院系" > <span class="help-inline"><form:errors path="expschool" /></span>
							</div>
						</div>					
						<div class="control-group" id="expresearch">
							<div class="controls">
								<i class="icon-star"></i> 研究方向：<input  type="text" name="expresearch"   placeholder="研究方向" > <span class="help-inline"><form:errors path="expresearch" /></span>
							</div>
						</div>
						<div class="control-group" id="exptitle">
							<div class="controls">
								<i class="icon-star"></i> 职称职务：<input  type="text" name="exptitle"   placeholder="职称/职务" > <span class="help-inline"><form:errors path="exptitle" /></span>
							</div>
						</div>
						<div class="span9">
						 	<button type="submit" class="btn btn-success ">保存</button>&nbsp;&nbsp;
							<button type="reset" class="btn">取消</button>
						</div>	
					</form>
				</div>
				
			</div>
		</div>
	</div>
	
</div>



<script type="text/javascript">
    $(document).ready(function() {
    	$("#valid_form").submit(function(){
			return checkEmptyAjax("valid_form","<c:url value='/admin/applyright/validInfoAJAX'></c:url>");
		});	
    	
    	$("#co_valid_form").submit(function(){
			return checkEmptyAjax("co_valid_form","<c:url value='/admin/applyright/coValidInfoAJAX'></c:url>");
		});
    	
    	$("#expert_form").submit(function(){
			return checkEmptyAjax("expert_form","<c:url value='/admin/applyright/expValidInfoAJAX'></c:url>");
		});
    	
    });
</script>
