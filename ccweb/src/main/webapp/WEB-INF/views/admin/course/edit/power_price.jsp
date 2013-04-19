<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.price {
	margin-left: 6%;
	float: left;
}

.row-fluid.custom.price.power {
	margin-left: 10%;
	float: left;
	margin-top: 20px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var status=$("#status").attr("title");
	$("#status input[value='"+status+"']").attr("checked",true);
	var statusValues=$("#statusValues").attr("title");
	if(statusValues==""){
	$("#statusValues input[value='0']").attr("checked",true);
	$("#passwordInput").hide();
	}else{
		$("#statusValues input[value='1']").attr("checked",true);
		$("#passwordInput").show();
	}
	$("#statusValues").click(function(){
		var statusValue=$('input:radio[name="pass"]:checked').val();
		if(statusValue=='0'){
			$("#passwordInput").hide();
			$("#pwd").val("");
			$("#checkpwd").val("");
		}else{
			$("#passwordInput").show();
		}
	});
});
setTimeout(function(){
	document.getElementById("message").style.display="none";
},2000);
</script>
<style>
.row-fluid.custom.basic {
	margin-left:5px;
	float: left;
}
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
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
</style>
<div  class="row-fluid custom round">
	<div class="row">
		<h4>权限设置</h4>
	</div>
	<div class="row1">
		<form class="form-horizontal" action="<c:url value="/admin/course/edit/powerpricemodify"></c:url>"   method="post" onsubmit="return checkPwd()">
			<div id="message" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
			<input type="hidden" name="courseId" value="${course.id }" />
			<div id="statusValues" title="${course.pwd}">
			<label class="radio inline" style="width: 38%; font-family:Microsoft YaHei;">
			<input type="radio" name="pass" value="0"style="margin-bottom: 20px;">公开
			</label>
			<br/>
			<label class="radio inline" style="width: 38%; font-family:Microsoft YaHei;">
			<input type="radio" name="pass" value="1">设置密码
			</label>
			</div>
			<div id="passwordInput" style="margin-left: 2px;">
			<div class="modal-body" id="pwdform">
				修改密码：<input type="text" id="pwd" name="pwd" style="width: 250px;" value="${course.pwd}" title="${course.pwd}"  onblur="deleSpace();" onfocus="clearHtml();">
				<span style="font-size: 16px;color: red;">输入数字或字母且密码长度为3-12字符</span>
			<span id="pwdError"></span>
			</div>
			<div class="modal-body" id="checkpwdform" >
				确认密码：<input type="text" id="checkpwd" name="checkpwd" style="width: 250px;"  value="${course.pwd}">
			</div>
			</div>
			<div class="modal-body"  id="price"  >
				<span style="ont-family:Microsoft YaHei;">课程售价：
					<input type="text" value="${course.price }"name="price" style="width: 150px;" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">￥
					（<span style="font-size: 14px;color: red;">只支持输入数字</span>）
				</span>
			</div>
			<div class="modal-body" id="status" title="${course.status}" >
				<span style="font-family:Microsoft YaHei;  margin-left:3px;">发布到知识超市：
			<label class="radio inline" style="width: 5%; font-family:Microsoft YaHei;margin-left: 10px; margin-top: -8px;">
			<input type="radio" name="status" value="2">是&nbsp;&nbsp;
			</label>
			<label class="radio inline" style="width:5%; font-family:Microsoft YaHei;margin-top: -8px;">
			<input type="radio" name="status" value="1">否
			</label>
			</span>
			</div>
			<div class="control-group" style="margin-left: 40px;">
				<div class="controls">
					<button type="submit" class="btn  btn-success">保存</button>
				</div>
			</div>
		</form>
	</div>
</div>