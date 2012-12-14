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
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<div  class="row-fluid custom round">
	<div class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/message/list"></c:url>' ><b>站内信</b></a><hr>
		<div style="text-align: center;">
		<table width="500" border="1" cellspacing="0" cellpadding="3">
		  <tr>
		    <td style="background-color:#f2f2f2;"  align="left" valign="top">
		    		信件标题：${sendMsg.date }<br>
		    		发件人：${sendMsg.user.name}<br> 
		      		发送日期：${sendMsg.date }<br>
		    </td>
		  </tr>
		  <tr>
		    <td align="left" >${sendMsg.content }</td>
		  </tr>
		</table><br> 
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
					<div class="modal-body" id="title">
					信件标题：<input type="text" name="title" id="t" placeholder="标题">
					<span class="help-inline"></span>
					</div >
					<div class="modal-body" id="content">
					信件内容：<textarea name="content" id="c" placeholder="内容" cols="5" rows="8" style="width:380px;"></textarea>
					<span class="help-inline"></span>
					</div>
						<div class="modal-footer">
					
						<button class="btn btn-primary" type="submit">保存</button>
							<button class="btn" type="reset" data-dismiss="modal"
							aria-hidden="true">取消</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>