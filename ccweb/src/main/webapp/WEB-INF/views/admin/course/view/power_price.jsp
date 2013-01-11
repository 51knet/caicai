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
	return false;
});
</script>
<div style="margin-top: 10px;">
	<a href="#">权限设置</a>
	<hr />
	<div style="margin-top: 30px;">
	<form:form class="form-horizontal" action="powerpricemodify"   method="post" onsubmit="return checkPwd()">
		<input type="hidden" name="id" value="${course.id }" />

		<div class="modal-body" id="pwdform">
			修改密码：<input type="text" id="pwd" name="pwd" style="width: 250px;"  value="${course.pwd}" onblur="deleSpace()">
		</div>
		<div class="modal-body" id="checkpwdform" >
			确认密码：<input type="text" id="checkpwd" name="checkpwd" style="width: 250px;" value="${course.pwd}"  >

		</div>
		<div class="modal-body" id="status" title="${course.status}" style="margin-left: 40px;">
			发布到知识超市：
			<input type="radio" name="status" value="2">是&nbsp;&nbsp;
			<input type="radio" name="status" value="1">否
		</div>
		<div class="control-group" style="margin-left: 40px;">
			<div class="controls">
				<button type="submit" class="btn btn-large btn-success">保存</button>
			</div>
		</div>
	</form:form>
	</div>
</div>