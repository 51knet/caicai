<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
	/*function sendMsgForm(){
		var sendForm = document.getElementById("sendMsgForm");
		sendForm.style.display="block";
	} 
	
	function hiddenAddForm() {
		var sendMsgForm = document.getElementById("sendMsgForm");
		sendMsgForm.style.display = "none";
	}*/
	$(document).ready(function() {
		$("#t").focus(function() {
			$(".help-inline").html("");
		});
		$("#c").focus(function() {
			$(".help-inline").html("");
		});
		checkAjax("receiveMsg_info_form","receiveMsgInfoAJAX");
		});
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
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
</style>
<div  class="row-fluid custom round">
	<div class="row" >
		<h4>站内短信>详细信息</h4>
	</div>
	<div class="row1">
		<div style="text-align: center;">
		<table border="0" cellspacing="0" cellpadding="8" width="100%">
		  <tr>
		    <td width="70%"  align="left" valign="top" style="background-color: #ccdfa8; font-size: 16px;">
		    		<b>标题：${sendMsg.title }</b>
		    </td>
		  </tr>
		  <tr  class="btline">
		  	<td align="left"  width="30%">
		  		<b>发件人：${sendMsg.user.name}</b><br>
		  		<b>发送时间：${sendMsg.date }</b>
		  	</td>
		  </tr>
		  <tr>
		    <td align="left" colspan="2">
		    	${sendMsg.content }
		    </td>
		  </tr>
		</table><hr>
			<a href="#myModal" role="button" class="btn btn-small" data-toggle="modal">点击回复</a>
			<div id="myModal" style="text-align: left;"  class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="myModalLabel">发送信件</h3>
				</div>
				<form:form action="sendMsg" method="post" id="receiveMsg_info_form">
					<input type="hidden" value="${sendMsg.user.id }" name="receiveId">
					<input type="hidden" value="${sendMsg.id }" name="sendMsgId" >
					<input type="hidden" value="${urmId }" name="urmId" >
					<div class="control-group" id="title" style="padding-left: 20px; margin-top: 10px;">
						<div class="controls">
						信件标题：
							<input type="text" name="title" id="t" placeholder="信件标题"> 
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="content" style="padding-left: 20px;">
						<div class="controls" >
						信件内容：
							<textarea  name="content" placeholder="信件内容" id="c" cols="5" rows="8" style="width:450px;"></textarea>
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" style="margin-left: 20px;">
						<div class="controls"  style="text-align: right; margin-right: 10px;">
							<button type="submit"  class="btn  btn-success">保存</button>
							<button type="reset" data-dismiss="modal" class="btn ">取消</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>