<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function sendMsgForm(){
		var sendForm = document.getElementById("sendMsgForm");
		sendForm.style.display="block";
	} 
	
	function hiddenAddForm() {
		var sendMsgForm = document.getElementById("sendMsgForm");
		
		sendMsgForm.style.display = "none";
		
	}
</script>

<h1>Welcome to teacher message page.</h1>
<div style="text-align: center;">

	Welcome to teacher announcement page.<br>
	${userInfo.user.email }<br>
	${userInfo.user.id }<br>
	${userInfo.user.name}<br>



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
		<form:form action="sendMsg" method="post">
			<input type="hidden" value="${sendMsg.user.id }" name="receiveId">
			<input type="hidden" value="${sendMsg.id }" name="sendMsgId" >
			<input type="hidden" value="${urmId }" name="urmId" >
			信件标题：<input type="text" name="title" placeholder="Title">
			<span class="help-block"><form:errors path="title"></form:errors></span>
			信件内容：<textarea name="content" placeholder="Content" cols="5" rows="8"></textarea>
			<span class="help-block"><form:errors path="content"></form:errors></span>
			<button type="submit" class="btn btn-primary">OK</button>
			<button class="btn" type="reset" onclick="hiddenAddForm()">CANCEL</button>
		</form:form>
	</div>

</div>