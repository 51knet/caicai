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
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content{
	margin: 20px 40px;

}
</style>
<div class="row-fluid custom round">
	<div  class="row">
		<h4>账户管理>账户充值</h4>
	</div>
	<div class="content">
		<form action= "new"  method="post" name="recharge_post" id="recharge_new_form"  >
			<div class="control-group" id="cardid">
				<div class="controls">
					充值id：<input type="text" name="cardid" placeholder="充值卡id" onchange="checkTheCardid(this)"> <span class="help-inline"></span>
				</div>
			</div>
			<label style="clear: right;"></label>
			<div style="width: 150px; margin-left: 55px;">
			<button type="submit" class="btn btn-success">充值</button>&nbsp;&nbsp;
			<button type="reset" class="btn">取消</button>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
		$(document).ready(function() {
			$("#recharge_new_form").submit(function(){
				return checkEmptyAjax("recharge_new_form","<c:url value='/admin/recharge/createRechargeAjax' />");
			})
	    });
		
		function checkTheCardid(obj){
			var cardid = obj.value;
			if(cardid != null || cardid != ""){
				$.ajax({
					   type: "post",
					   url: "<c:url value='/admin/recharge/checkCardid' />",
					   data:"cardid="+cardid,
					   dataType:"text",
					   success: function(result){
					     if(result=="no"){
					    	 alert("充值卡id不正确或不存在此id");
					    	 obj.value="";
					    	 return false;
					     }
					   }
					});
			}
		}
</script>