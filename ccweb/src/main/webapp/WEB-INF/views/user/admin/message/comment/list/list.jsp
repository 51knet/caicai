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
	
    function showReply(index){
   	 var id =  index+"_reply_div";
	   	  if($("#"+id).css("display") == "block"){
	   		  $("#"+id).css("display","none");
	   	  }else{
	   		  $("#"+id).css("display","block");
	   	  }
   }
	function postReplyForm(index){
    	var id =  index+"_reply_form";
    	var replydiv =  index+"_reply_div";
		$.post('<c:url value="/ajaxreply" />', $("#"+id).serialize(), function(msg){
			if(msg != null){
				alert("您的回复已提交");
			}
		}, "json");
		$("#"+replydiv).css("display","none");
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
.color_green{
color: #80b029; 
font-size: 14px;
}
.border-green-all{
		border: 1px solid #9db84d;
}
.date{
	font-size: 12px;
	color: #666;
}
.limitTable{
	width:100%; 
	table-layout:fixed
	background-color:#fafafa; 
	margin-bottom: 10px;
}

 .limitTd{
	word-wrap:break-word; word-break:break-all;
}

</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>站内消息</h4>
	</div>
	<div class="content">	
			<c:forEach items="${commentBeansList}" var = "commentBeansList" >
				<table border="0" cellpadding="4"  class="limitTable border-green-all round" id="${commentBeansList.comment.id}_comment_table">
				  	  <tr>
					    <td rowspan="3" align="left" valign="top" width="10%" ><img src="<c:url value='${commentBeansList.comment.user.photo_url} '></c:url>" style="width:60px; height: 60px;"></td>
					    <td align="left" style="font-weight: bold;">${commentBeansList.comment.user.name}</td>
					    <td align="left" valign="top"  width="20%"  class="date">${commentBeansList.comment.publishDate}</td>
					  </tr>
					  <tr>
					    <td colspan="2" align="left" valign="top" class="color_green limitTd">${commentBeansList.comment.context}</td>
					  </tr>
					  <tr>
					    <td  align="left" valign="top" class="date  limitTd">
					   		<c:if test="${commentBeansList.comment.host.id != sessionUserInfo.id }">
					   			评论了我的动态：${commentBeansList.trends.context}
					   		</c:if>	
					   		<c:if test="${commentBeansList.comment.host.id != null }">
					   			回复了我的评论
					   		</c:if>	 
					    </td>
					    <td align="center" >
					    	<a class="color_green" href="javascript:void(0)" onclick="showReply(${commentBeansList.comment.id  })">回复</a>
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="3">
				  			<div style="display: none; margin: 0 10px; " id="${commentBeansList.comment.id }_reply_div">
								<form  method="post" action='<c:url value="/reply"></c:url>'  id="${commentBeansList.comment.id }_reply_form">
									<input type="hidden" name="hostId" value="${commentBeansList.comment.user.id }" >
									<input type="hidden" name="trendId" value="${commentBeansList.trends.id}" >
									<textarea rows="3" cols="" style="width:100%; " class="border-green-all" name="contents"  ></textarea><br>
									<div class="offset10">
										<!-- <button class="btn btn-success offset"  onclick="postReplyForm()">发布</button> -->
										<a href="javascript:void(0)" class="btn btn-success comment_button"  onclick="postReplyForm(${commentBeansList.comment.id })">回复</a>  
									</div>
								</form>
							</div>
					  	</td>
					  </tr>
		  		</table>
	  		</c:forEach>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div> 
	</div>
</div>

