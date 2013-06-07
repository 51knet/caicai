<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/loginCheck.js" />"></script>
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
<div  class="row-fluid custom round">
	<div class="row">
		<h4>账号信息</h4><i class="icon-star"></i><i>必须填写项</i>
	</div>
	<div class="content">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li <c:if test='${active == "photo"}'>class="active"</c:if>><a href="#photo_tab" data-toggle="tab">默认头像</a></li> 
		    	<li <c:if test='${active == "avatar"}'>class="active"</c:if>><a href="#avatar_tab" data-toggle="tab">修改头像</a></li> 
		    	<li <c:if test='${active == "psw"}'>class="active"</c:if>><a href="#security_tab" data-toggle="tab">账号安全</a></li> 
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "photo"}'>active</c:if>" id="photo_tab">
					<form class="form-horizontal" action="photo" method="post" id="photo_form">
						<div class="control-group" id="mark" >
							<label><b>请选择默认头像：</b></label>
							<c:forEach begin="1" end="3" var="x">
								<label class="radio inline" >
									<img  src='<c:url value="/resources/img/avatar/p${x }.jpg"></c:url>'  style="width:120px;height: 120px;"><br>
									<input type="radio" name="photo" value="${x }"  style="margin-left: 50px; margin-top: 10px;" <c:if test="${x==1 }"> checked="checked"</c:if> >
								</label>
							</c:forEach>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit"  class="btn  btn-success">保存</button>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane <c:if test='${active == "avatar"}'>active</c:if>" id="avatar_tab">
					<%
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
					request.setAttribute("basePath", basePath);
					%>
					<script src="<c:url value="/resources/avatar/common.js?B6k" />" type="text/javascript"></script>	
					<script type="text/javascript">
					$(document).ready(function(){
						var uc_api = encodeURIComponent("${basePath}avatar/${sessionUserInfo.id}");
						var urlCameraFlash = "<c:url value="/resources/avatar/camera.swf" />"+"?nt=1&inajax=1&appid=1&input=1&uploadSize=1024&ucapi=" + uc_api;
						$('#flash_container').html(AC_FL_RunContent("width","450","height","253","scale","exactfit","src",""+urlCameraFlash+"","id","mycamera","name","mycamera","quality","high","bgcolor","#ffffff","wmode","transparent","menu","false","swLiveConnect","true","allowScriptAccess","always"));
						/* var mes=$("#message").attr("title");
						if(mes!=''){
							alert("信息已保存");
							return false;
						} */
					});
					setTimeout(function(){
				 		document.getElementById("message").style.display="none";
					},2000);
					</script>
					<div id="flash_container"></div>
				</div>
				
				<div class="tab-pane <c:if test='${active == "psw"}'>active</c:if>" id="security_tab">
					<form class="form-horizontal" action="changePsw" method="post" id="chanePsw_info_form">
				    <div id="message" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
						<div class="control-group" id="ori_psw">
							<label class="control-label" for="ori_psw">当前密码</label>
							<div class="controls">
								<input type="password"  id="oriPsw" name="ori_psw" placeholder="请输入您的当前密码" >
								<span class="help-inline" id="oriError"></span>
							</div>
						</div>
						<div class="control-group" id="new_psw">
							<label class="control-label" for="new_psw">新密码</label>
							<div class="controls">
								<input type="password" id="alter_new_psw" name="new_psw" placeholder="请输入您的新密码">
							    <span class="help-inline" id="newPsw"></span>
							</div>
						</div>
						<div class="control-group" id="confirm_new_psw">
							<label class="control-label" for="confirm_new_psw">确认密码</label>
							<div class="controls">
								<input type="password" id="alter_confirm_new_psw" name="confirm_new_psw" placeholder="再次输入一遍您的新密码">
								<span class="help-inline" id="errorPs"></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return changPwdOnclick();"  class="btn  btn-success">修改密码</button>
							</div>
						</div>
					</form>
				</div>
				
				<div class="tab-pane <c:if test='${active == "url"}'>active</c:if>" id="p_url_tab">
					<form class="form-horizontal" action="selfurl" method="post" id="selfurl_info_form">
						<div class="control-group" id="url">
							<label class="control-label" for="p_url">个性域名</label>
							<div class="controls">
								<input type="text" name="url" id="p_url" value="${sessionScope.userInfo.user.self_url }" placeholder="请输入您的个性域名">
							    <span class="help-inline"></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return selfurlOnclick();" class="btn  btn-success">保存</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
function selfurlOnclick(){
	return checkEmptyAjax("selfurl_info_form","selfurlInfoAJAX");
};
 function changPwdOnclick(){
	return modifyPwd("chanePsw_info_form","pswInfoAJAX");
}; 
$(document).ready(function(){
	$("#alter_confirm_new_psw").focus(function(){
		$("#errorPs").html("");
	});
	$("#alter_new_psw").focus(function(){
		$("#newPsw").html();
	});
	$("#oriPsw").focus(function(){
		$("#oriError").html("");
	});
/* 	$("#oriPsw").blur(function(){
		var password=$("#oriPsw").val();
		$.ajax({
			type:"post",
			url:"<c:url value='/admin/pswInfoCheck'/>",
			data:"oriPsw="+password,
			dataType:"text",
			success:function(num){
				if(num=='0'){
					$("#oriError").html("<font color='#ff0000'>" + "输入的密码不正确"
							+ "</font>");
					return false;
				}
			}
		});
		
	}); */
	/* $("#alter_confirm_new_psw").blur(function(){
		var newPsw=$("#alter_new_psw").val().trim();
		var confirmNewPsw=$("#alter_confirm_new_psw").val().trim();
		if(newPsw!=confirmNewPsw){
			$("#errorPs").html("<font color='#ff0000'>"+"两次输入的密码不一致,请重新输入!"+"</font>");
			return false;
		}
		
	}); */
	
});
</script>
		
