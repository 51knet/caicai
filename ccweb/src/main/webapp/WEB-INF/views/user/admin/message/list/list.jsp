<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
/*
	$(document).ready(function(){
		$(".unReadMsg").mouseover(function(){
			  $(this).css("background-color","#fafafa");
		});
		$(".unReadMsg").mouseout(function(){
			  $(this).css("background-color","");
		});
	});*/

	 var maxLen=200;
	function checkMaxInput(obj) {
		 if(obj.value.length>maxLen) {  
		 		obj.value=obj.value.substring(0,maxLen);
		 		remLen.innerText="你输入的内容超出了字数限制";
		 }
		 else{  
			 remLen.innerText='还剩下'+(maxLen-obj.value.length)+'/'+maxLen+'字';
		 }
	}   
	
	 function postReplyForm(){
	    	var id =  "reply_form";	    	
			$.post('<c:url value="/admin/message/detail/reply" />', $("#"+id).serialize(), function(msg){
				//alert(msg.sendMsg.user.name+"---"+msg.sendMsg.content);
				 var  t = "<table cellpadding='10' width=100%   style='border: 1px solid #ccc;  margin-bottom: 10px;' class='bb unReadMsg round'>";
			     t+="<tr ><td  rowspan='2' align='left' width='10%'><img src='/ccweb"+msg.sendMsg.user.photo_url+"  ' style='width:60px; height: 60px;' /></td><td align='left' style='font-weight: bold; padding:10px 10px;'>"+msg.sendMsg.user.name+"</td>";
			  	 t+="<td align='right'  width='20%'  style='color: #666; font-size: 12px;padding:10px 10px;'>"+msg.sendMsg.date+"</td></tr>";
			    t+="<tr> <td colspan='2' align='left' style='color: #80b029; font-size: 14px;padding:10px 10px;'>"+msg.sendMsg.content+"</td></tr></table>";
			     $("#reply_div").append(t); 
			     $("#reply_div").css("display","block");
			}, "json");
			$("#reply_form_textarea").html("");
	    }

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
.row-fluid.custom .content {
	margin: 20px 40px;
}
.bb{
	border-bottom: solid #cccccc 1px;
}

</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>站内消息</h4>
	</div>
	<div class="content">
		<form  method="post"  id="reply_form">
			<input type="hidden" name="senderId" value="${sender_id }" >
			<textarea rows="3" cols="" style="width:100%; " class="border-green-all" name="contents"  onKeyDown="checkMaxInput(this)" onKeyUp="checkMaxInput(this)" id="reply_form_textarea" ></textarea><br>
				 <font align="right" id="remLen"><b></b></font>
				 <!-- <button class="btn btn-success  pull-right"  >发布</button>  --> 
				<a href="javascript:void(0)" class="btn btn-success  pull-right"  onclick="postReplyForm()">发布</a><br>
		</form>
	</div>
	<div id="reply_div"  style="display: none; margin-bottom: 10px;" class="content">
	
	</div>
	<div class="content">	
		<c:forEach items="${page.content}" var = "page" >
			<table cellpadding="10" width=100%   style=" border: 1px solid #ccc; 	<c:if test='${page.sendMsg.user.id != sessionUserInfo.id}'> border: 1px solid #cae893; background-color:#fafafa;</c:if> margin-bottom: 10px;" class="bb unReadMsg round">
				  <c:if test="${page.sendMsg.user.id == sessionUserInfo.id}">
				  	  <tr>
					    <td rowspan="2" align="left" width="10%" ><img src="<c:url value='${page.sendMsg.user.photo_url} '></c:url>" style="width:60px; height: 60px;"></td>
					    <td align="left" style="font-weight: bold; padding:10px 10px;">${page.sendMsg.user.name}</td>
					    <td align="right"  width="20%"  style="color: #666; font-size: 12px;padding:10px 10px;"> ${page.sendMsg.date}</td>
					  </tr>
				  </c:if>
				  	<c:if test="${page.sendMsg.user.id != sessionUserInfo.id}">
					   <tr>
					    	<td align="left"  width="20%"  style="color: #666; font-size: 12px;padding:10px 10px;"> ${page.sendMsg.date}</td>
						    <td  align="right" style="font-weight: bold; padding:10px 10px;">${page.sendMsg.user.name}</td>
						    <td rowspan="2"  align="right" width="10%" ><img src="<c:url value='${page.sendMsg.user.photo_url} '></c:url>" style="width:60px; height: 60px;"></td>
					  </tr>
				  </c:if>
				  <tr>
				    <td colspan="2" align="left" style="color: #80b029; font-size: 14px;padding:10px 10px;">${page.sendMsg.content}</td>
				  </tr>
	  		</table>
  		</c:forEach>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div> 
		

	</div>
</div>

<!-- delete  msg Form -->
<div class="modal hide fade" id="deleteMsgPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定将该信件放入回收站吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/message/deleOne"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="meId" type="hidden" name="mId" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {	
	$('.deleteMsgPostBtn').on('click', function() {
		var m_id =$(this).next().val();
		$('#deleteMsgPostModal #meId').val(m_id);	
	});
});
</script>		