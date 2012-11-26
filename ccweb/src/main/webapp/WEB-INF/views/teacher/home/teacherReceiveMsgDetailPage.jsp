<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
	function sendMsgForm(){
		var sendForm = document.getElementById("sendMsgForm");
		sendForm.style.display="block";
	} 
	
	function hiddenAddForm() {
		var sendMsgForm = document.getElementById("sendMsgForm");
		
		sendMsgForm.style.display = "none";
		
	}
	$(document).ready(function() {
		$("#t").focus(function() {
			$(".help-inline").html("");
		});
		$("#c").focus(function() {
			$(".help-inline").html("");
		});
		checkAjax("receiveMsg_info_form","receiveMsgInfoAJAX");
		alert(1);
		
		});
		</script>

<h1>Welcome to teacher message page.</h1>
<div style="text-align: center;">

	Welcome to teacher announcement page.<br>
	${sessionUserInfo.user.email }<br>
	${sessionUserInfo.user.id }<br>
	${sessionUserInfo.user.name}<br>



<table width="500" border="1" cellspacing="0" cellpadding="3">
  <tr>
    <td style="background-color:#f2f2f2;"  align="left" valign="top">发件人：${sendMsg.user.name}<br> 
      <br>发送日期：${sendMsg.date }
    </td>
  </tr>
  <tr>
    <td align="left" >${sendMsg.content }</td>
  </tr>
</table><br> 
	<a href="javascript:void(0)" onclick="sendMsgForm()">点击回复</a><br>
	<div id="sendMsgForm" style="display:none; width:450px; height:300px;">
		<form:form action="sendMsg" method="post" id="receiveMsg_info_form">
			<input type="hidden" value="${sendMsg.user.id }" name="receiveId">
			<input type="hidden" value="${sendMsg.id }" name="sendMsgId" >
			<input type="hidden" value="${urmId }" name="urmId" >
			<div class="modal-body" id="title">
			信件标题：<input type="text" name="title" id="t" placeholder="Title">
			<span class="help-inline"></span>
			</div >
			<div class="modal-body" id="content">
			信件内容：<textarea name="content" id="c" placeholder="content" cols="5" rows="8"></textarea>
			<span class="help-inline"></span>
			</div>
			<button type="submit" class="btn btn-primary">保存</button>
			<button class="btn" type="reset" onclick="hiddenAddForm()">取消</button>
		</form:form>
	</div>

</div>