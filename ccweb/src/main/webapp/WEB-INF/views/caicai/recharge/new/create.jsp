<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<link href="<c:url value="/resources/js/uploadify3.2/uploadify.css" />" rel="stylesheet" type="text/css" />

<script type="text/javascript">

$(document).ready(function() {
	$("#recharge_form").submit(function(){
		//this.sync();
		return checkEmptyAjax('recharge_form', '<c:url value="/admin/caicai/recharge/createAjax"></c:url>');
	});
});

function createCardId(){
	$.ajax({
		   type: "post",
		   url: "<c:url value='/admin/caicai/recharge/createCardId' />",
		   dataType:"text",
		   success: function(cardid){
		     if(cardid !="" || cardid!= null){
		    	$("#newCardId").val(cardid);
		     }
		   }
		});
}

</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>充值卡管理>创建充值卡</h4>
	</div>
	<div class="content" >
		<form action='new' method="post"  id="recharge_form" > 
			<div class="control-group" id="cardid">
				<div class="controls">
					充值卡标识：<input type="text"  name="cardid" placeholder="充值卡id" id="newCardId" readonly="readonly">
					<a href="javascript:void(0)"  onclick="createCardId()" >生成标识</a> <span class="help-inline"><form:errors path="cardid"></form:errors></span>
				</div>
			</div>
			<div class="control-group" id="price">
				<div class="controls">
					充值卡金额：<input type="text"  name="price" placeholder="充值卡金额" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"> 元
					 <span class="help-inline"><form:errors path="price"></form:errors></span>
				</div>
			</div>
			<div class="pull-left" style="margin-left: 70px;">
			<button type="submit" class="btn btn-success" >添加</button>&nbsp;&nbsp;
			<button type="reset" class="btn" >取消</button>
			</div>
		</form>
	</div>	
</div>

