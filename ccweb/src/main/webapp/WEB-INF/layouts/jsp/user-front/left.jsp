<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">

    function	showCommentDiv(index){
		  var id =  index+"_comment_div";
		 // $("#"+id+">table").empty();
	
		  if($("#"+id).css("display") == "block"){
			  $("#"+id).css("display","none");
		  }else{
			  $("#"+id).css("display","block");
		  }
	}

    
    function showReply(index){
    	 var id =  index+"_reply_div";
	   	  if($("#"+id).css("display") == "block"){
	   		  $("#"+id).css("display","none");
	   	  }else{
	   		  $("#"+id).css("display","block");
	   	  }
    }
    
    function   formatDate(now)   {   
        var   year=now.getYear();
        var   month=now.getMonth()+1;   
        var   date=now.getDate();   
        var   hour=now.getHours();   
        var   minute=now.getMinutes();     
        return   year+"-"+month+"-"+date+"   "+hour+":"+minute;   
      } 

    function postCommentForm(index){
    	var id =  index+"_comment_form";
    	var showId = index+"_ajax_comment_div";
		$.post('<c:url value="/ajaxcomment" />', $("#"+id).serialize(), function(msg){
			var d = new Date(msg.publishDate);
			var date = formatDate(d);
			 var  t = "<table width='96%' cellpadding='0' style='margin:10px 10px;' >";
		     t+="<tr><td  align='left' valign= 'top'><img style='width:40px' src='/ccweb"+msg.user.photo_url+"  '  />&nbsp;&nbsp;<a href='/ccweb/id/"+msg.user.id+" '>"+msg.user.name+"</a>："+msg.context+"</td></tr>";
		  //   t+="<tr  class='bb'><td  align='left' valign= 'top'><span class='date'>"+date+"</span>";
			  t+="<tr  class='bb'><td  align='left' valign= 'top'>&nbsp;</td></tr></table>";
		     $("#"+showId).append(t); 
		     $("#"+showId).css("display","block");
		}, "json");
		$("#"+id +" textarea").html("");
    }
    
    function postReplyForm(index , trendid){
    	var id =  index+"_reply_form";
    	var replydiv =  index+"_reply_div";
    	var showId = trendid+"_ajax_comment_div";
		$.post('<c:url value="/ajaxreply" />', $("#"+id).serialize(), function(msg){
			 var  t = "<table width='96%' cellpadding='0' style='margin:10px 10px' class='bb'>";
		     t+="<tr ><td  align='left' valign= 'top'><img src='/ccweb"+msg.user.photo_url+"  ' style='width:40px' />&nbsp;&nbsp;<a href='/ccweb/id/"+msg.user.id+" '>"+msg.user.name+"</a> 回复了 <a href='/ccweb/id/"+msg.host.id+" '>"+msg.host.name+"</a>："+msg.context+"</td></tr>";
		  	 t+="<tr  class='bb'><td  align='left' valign= 'top'>&nbsp;<td></tr></table>";
		     $("#"+showId).append(t); 
		     $("#"+showId).css("display","block");
		}, "json");
		$("#"+id +" textarea").html("");
		$("#"+replydiv).css("display","none");
    }
    
    function checkCommentTextMaxInput(obj) {
		 var maxLen=200;
		 var form_id = obj.parentNode.id+"_remLen";
		 if(obj.value.length>maxLen) {  
		 		obj.value=obj.value.substring(0,maxLen);
		 		document.getElementById(form_id).innerHTML="你输入的内容超出了字数限制";
		 }
		 else{  
			 	document.getElementById(form_id).innerHTML='还剩下'+(maxLen-obj.value.length)+'/'+maxLen+'字';
		 }
	}
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 20px 0px 0px 0px;
}
.row-fluid .custom .row {
	margin: 0px 30px 10px 30px;
	color: #80b029;
	/*border-bottom: solid #cccccc 1.5px;*/
}
.row-fluid .custom .row >a {
	margin: 0px 20px 0px 0px;
	text-decoration:none;
	/*border-bottom: solid #cccccc 1.5px;*/
	color:#666;
}
.row-fluid .custom .row > a:hover{
	color:  #7da622;
}

.row-fluid .custom .row > a:visited{
	color:  #7da622;
}

.row-fluid.custom .content {
	margin: 20px 30px;
}
.border_green{
	border: 1px solid #80b029;
}
.bb{
	border-bottom: 1px solid #ccc;
}
.date{
	font-size: 12px;
		color: #859c34;
}
.border-ccc-all{
	border: 1px solid #ccc;
}
.color_green{
	color: #859c34;
}
.comment_button{
	font-family:'Microsoft YaHei',Arial; 
	background-color: #a6c575; 
	color: #fff; font-weight: bold; 
	font-size: 14px; 
	width:40px;
}

.limitTable{
	width:100%; 
	table-layout:fixed
	margin-bottom: 10px;
}

 .limitTd{
	word-wrap:break-word; word-break:break-all;
	color: #666; font-size: 13px;
}

</style>
<div class="row-fluid custom" >
	<div class="row"  >
		<a href='<c:url value='/user/${userInfo.id}'></c:url>' >全部</a>
		<a href='<c:url value='/user/${userInfo.id}'></c:url>' >原创</a>
		<a href='<c:url value='/user/${userInfo.id}'></c:url>' >资源</a>
		<a href='<c:url value='/user/${userInfo.id}'></c:url>' >公告</a>
	</div>
	<div class="content">	
		<c:forEach items="${trend}" var="trendBeans">

				<table class="limitTable" cellpadding="5">
					<tr>
						<td align="left" valign="top" colspan="2" class="limitTd">${trendBeans.trend.context }</td>
					</tr>
					<tr class="bb">
						<td aligh="left"><a style="margin: 10px 10px;" href='<c:url value="/user/${userInfo.id}/trend/view/${trendBeans.trend.id}"></c:url>'>
						<span class="date"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></a></td>
						<td align="right" valign="top">
							
							<a href="javascript:void(0)" onclick="showCommentDiv(${trendBeans.trend.id})"><img src="<c:url value='/resources/img/default/commenttip.png'></c:url>" ></a>
							<c:if test="${sessionUserInfo.id == trendBeans.trend.user.id }">
							|	<a class="color_green" href="javascript:void(0)" onclick="deleteTrends(${trendBeans.trend.id} ,${trendBeans.trend.user.id} )">删除</a>
							</c:if>
						</td>
					</tr>
				</table>
				<div id="${trendBeans.trend.id}_comment_div" style="display: none; " class="border-ccc-all">
				<c:if test="${sessionUserInfo == null }"><br><a style="margin:0px 20px;" class="color_green" href='<c:url value='/'></c:url>' >请登录后评论>></a><br><br></c:if>
				<c:if test="${sessionUserInfo != null }">
					<form style="margin: 10px 15px " method="post" action='<c:url value="/front/comment"></c:url>' id="${trendBeans.trend.id}_comment_form">
						<input type="hidden" name="trendId" value="${trendBeans.trend.id}">
						<textarea rows="3" cols="" style="width:100%; " name="contents" class="border-green-all" onKeyDown="checkCommentTextMaxInput(this)" onKeyUp="checkCommentTextMaxInput(this)"></textarea><br>
						
							<!--<button class="btn btn-success " type="submit">发布</button> -->
							<font  id="${trendBeans.trend.id}_comment_form_remLen" class="pull-left"><b></b></font>
							<a href="javascript:void(0)" class="btn btn-success comment_button pull-right"  onclick="postCommentForm(${trendBeans.trend.id})">发布</a><br>  
						
					</form>
				</c:if>
					<a style="margin: 10px 10px;" href='<c:url value="/user/${userInfo.id}/trend/view/${trendBeans.trend.id}"></c:url>'><span class="color_green">共有 ${trendBeans.commentCount} 条评论，点击查看详细 >>></span></a><br><br>
					<div id="${trendBeans.trend.id}_ajax_comment_div"  style="display: none; margin-bottom: 10px;">
					
					</div>
					<c:forEach items="${trendBeans.commentList}" var="comment" begin="0" end="6">
						<table width='96%' cellpadding='0' style="margin: 10px 10px;" id="${comment.id }_comment_table">
							<tr><td  align='left' valign= 'top' colspan="2">
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'> <img src='<c:url value="${comment.user.photo_url }"></c:url>'  style="width:40px;"></a>
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'> ${comment.user.name }</a>
							<c:if test="${comment.host == null }">
								：${comment.context }<br>
							</c:if>
							<c:if test="${comment.host != null}">
								回复了 <a href='<c:url value="/id/${comment.host.id }"></c:url>'>${comment.host.name}</a> ：${comment.context }<br>
							</c:if>
							</td></tr>
							<tr>
								<td align="left" valign="top">	<span class="date"><fmt:formatDate value="${comment.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
								<td align="right" valign="top">
									<c:if test="${sessionUserInfo != null }">
										<a class="color_green" href="javascript:void(0)" onclick="showReply(${comment.id })">回复 </a>
										<c:if test="${sessionUserInfo.id == comment.user.id }">
										|	 <a class="deleteMsgPostBtn" href="#deleteMsgPostModal" role="button" data-toggle="modal" data-target="#deleteMsgPostModal"><span class="color_green">删除</span></a><input type="hidden" value="${comment.id} ">
										</c:if>
									</c:if>
									<c:if test="${sessionUserInfo == null }"><a  class="color_green" href='<c:url value='/'></c:url>' >请登录后回复</a></c:if>
								</td>
							</tr>
							<tr  class='bb'><td  align='right' valign= 'top' colspan="2">
								<div style="display: none; margin: 10px 10px; " id="${comment.id }_reply_div">
										
									<c:if test="${sessionUserInfo != null }">
										<form  style=""  method="post" action='<c:url value="/reply"></c:url>'  id="${comment.id }_reply_form">
											<input type="hidden" name="hostId" value="${comment.user.id }" >
											<input type="hidden" name="trendId" value="${trendBeans.trend.id}" >
											<textarea rows="3" cols="" style="width:100%; " name="contents" class="border-green-all" onKeyDown="checkCommentTextMaxInput(this)" onKeyUp="checkCommentTextMaxInput(this)"></textarea><br>
											
												<!-- <button class="btn btn-success offset"  onclick="postReplyForm()">发布</button> -->
												<font  id="${comment.id}_reply_form_remLen" class="pull-left"><b></b></font>
												<a href="javascript:void(0)" class="btn btn-success comment_button pull-right"  onclick="postReplyForm(${comment.id} , ${trendBeans.trend.id})">发布</a><br>  
										
										</form>
									</c:if>
								</div>
							</td></tr>
						</table>
					</c:forEach>
				</div>

		</c:forEach>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>
<!-- delete msgForm -->
<div class="modal hide fade" id="deleteMsgPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/message/comment/destory"></c:url>' method="post" style="display: inline-block;" >
	    	<a href="javascript:void(0)" class="btn btn-success" id="deleCommentAjax"  data-dismiss="modal" aria-hidden="true">确定</a><input  type="hidden" id="commentId" name="commentId" /> 
	    </form>
	  </div>
</div>
<script type="text/javascript">
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
$(document).ready(function() {	
	$('.deleteMsgPostBtn').on('click', function() {
		var comment_id = $(this).next().val().trim();
		$('#deleteMsgPostModal #commentId').val(comment_id);	
	});
	
	$("#deleCommentAjax").on("click" , function(){
		var comment_id=$(this).next().val();
		$.ajax({
			   type: "POST",
			   url: '<c:url value="/ajaxCommentDestory"></c:url>',
			   data: "commentId="+comment_id,
			   success: function(msg){
				   if(msg == 'true'){
					   var comments = document.getElementById(comment_id+"_comment_table");
					     $(comments).remove();
				   }
			   }
			});
	});
});

</script>
