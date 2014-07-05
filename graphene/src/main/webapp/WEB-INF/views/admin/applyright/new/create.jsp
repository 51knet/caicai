<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
function checkAlliesResources(obj){
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
</script>
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
.row-fluid.custom .content{
	margin: 20px 40px;
}

.error{
	color:#b94a48;
}
</style>

<div class="row-fluid custom round">
	<div  class="row">
		<h4>权限申请</h4>
		<i>请认真填写下列表格</i>
	</div>
	<div class="content">
		
		<div class="tabbable">
			<ul class="nav nav-tabs">
		    	<li <c:if test='${active == "allies"}'>class="active"</c:if>><a href="#allies_tab" data-toggle="tab">联盟成员申请</a></li> 
		    	<li ><a href="#park_tab" data-toggle="tab">园区申请</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "allies"}'>active</c:if>" id="allies_tab">
					<form action= '<c:url value="/admin/applyright/allies/add"></c:url>'  method="post" enctype="multipart/form-data"  id="allies_form" name="allies_post" onsubmit="return checkAlliesResources(this)" >
					<div class="control-group" id="alliesBoss">
						<div class="controls">
							<i class="icon-star"></i> 真实姓名：<input  type="text" name="alliesBoss"   placeholder="真实姓名" > <span class="help-inline"><form:errors path="alliesBoss" /></span>
						</div>
					</div>

					<div class="control-group" id="alliesPhone">
						<div class="controls">
							<i class="icon-star"></i> 联系电话：<input  type="text" name="alliesPhone"   placeholder="联系电话"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="phone" /></span>
						</div>
					</div>
					
					<div class="control-group" id="alliesEmail">
						<div class="controls">
							<i class="icon-star"></i> 联系邮箱：<input  type="text" name="alliesEmail"   placeholder="联系邮箱"  > <span class="help-inline"><form:errors path="phone" /></span>
						</div>
					</div>
					
					<div class="control-group" id="myfiles">
						<div class="controls">
							<i class="icon-star"></i> 我的资料：<input  type="file" name="myfiles"><span id="myFilesError" class="error"></span>
							<br><i>包括企业营业执照复印件或扫描件等</i>
						</div>
					</div>
			
					<div class="control-group" id="alliesContent">
						<div class="controls " >
							简介：<br>
							<textarea  style="width:670px;height:120px;"  name="alliesContent"  placeholder="我的简介"></textarea>
							<span class="help-inline"><form:errors path="content" /></span>
						</div>
					</div>
					
					<div class="span9">
					 	<button type="submit" class="btn btn-success ">保存</button>&nbsp;&nbsp;
						<button type="reset" class="btn">取消</button>
					</div>	
				</form>
				</div>
				
				<div class="tab-pane <c:if test='${active == "park"}'>active</c:if>" id="park_tab">
					<form action= '<c:url value="/admin/applyright/park/add"></c:url>'  method="post" enctype="multipart/form-data"  id="park_form" name="park_post" >
						<div class="control-group" id="parkName">
							<div class="controls">
								<i class="icon-star"></i> 园区名称：<input  type="text" name="parkName"   placeholder="园区名称" > <span class="help-inline"><form:errors path="parkName" /></span>
							</div>
						</div>
						<div class="control-group" id="parkBoss">
							<div class="controls">
								<i class="icon-star"></i> 法人姓名：<input  type="text" name="parkBoss"   placeholder="法人姓名" > <span class="help-inline"><form:errors path="parkBoss" /></span>
							</div>
						</div>
				
						<div class="control-group" id="parkPhone">
							<div class="controls">
								<i class="icon-star"></i> 联系电话：<input  type="text" name="parkPhone"   placeholder="联系电话"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> <span class="help-inline"><form:errors path="parkPhone" /></span>
							</div>
						</div>
						<div class="control-group" id="parkEmail">
							<div class="controls">
								<i class="icon-star"></i> 联系邮箱：<input  type="text" name="parkEmail"   placeholder="联系邮箱"  > <span class="help-inline"><form:errors path="parkEmail" /></span>
							</div>
						</div>
						<div class="control-group" id="parkContent">
							<div class="controls " >
								简介：<br>
								<textarea  style="width:670px;height:120px;"  name="parkContent"  placeholder="简介"></textarea>
								<span class="help-inline"><form:errors path="parkContent" /></span>
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
    	$("#allies_form").submit(function(){
			return checkEmptyAjax("allies_form","<c:url value='/admin/applyright/alliesValidInfoAJAX'></c:url>");
		});	
    	
    	$("#park_form").submit(function(){
			return checkEmptyAjax("park_form","<c:url value='/admin/applyright/parkValidInfoAJAX'></c:url>");
		});
    	
    	$("#expert_form").submit(function(){
			return checkEmptyAjax("expert_form","<c:url value='/admin/applyright/expValidInfoAJAX'></c:url>");
		});
    	
    });
</script>
