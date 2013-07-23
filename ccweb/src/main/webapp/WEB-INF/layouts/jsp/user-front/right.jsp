<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
    function	showCommentDiv(index){
	  var id =  index+"_comment_div";
	  $("#"+id).css("display","block");
	  $("#"+id+">table").empty();
	  $.ajax({
		   type: "POST",
		   url: '<c:url value="/showTrendsComment"></c:url>',
		   data: "trendId="+index,
		   dataType:"json",
		   success: function(msg){
			//   alert(msg);
		     var  t = "<table width='100%' cellpadding='5'>";
		     for( var i=0;i<msg.length;i++){
		    	 t+="<tr><td  align='left' valign= 'top'>  <img src='/ccweb"+msg[i].user.photo_url+"  ' style='width:40px' />&nbsp;&nbsp;"+msg[i].user.name+"</td></tr><tr  class='bb'><td  align='left' valign= 'top'>"+msg[i].context+"</td></tr>";
		     }
		     $("#"+id).append(t);
		   }
		});
	}
	
    function deleteTrends(trend_id, user_id){
		alert(trend_id+"---"+user_id);
	}
    
    function closeCommentDiv(index){
    	  var id =  index+"_comment_div";
    	  $("#"+id).css("display","none");
    	  $("#"+id+">table").empty();
    }
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 20px 0px 0px 0px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.border_green{
	border: 1px solid #80b029;
}
.bb{
	border-bottom: 1px solid #ccc;
}
</style>
<!-- ${teacherInfo.id} -->
<div class="row-fluid custom round" >
<div class="row">
	<h4>我的主页</h4>
</div>
	<div class="content">	
			<c:forEach items="${myTrend}" var="trend">
				<div style="margin: 10px 0px;" class="border_green">
					<table width="100%" cellpadding="5">
						<tr>
							<td align="left" valign="top">${trend.context }</td>
						</tr>
						<tr >
							<td align="right" valign="top">
								<c:if test="${sessionUserInfo == null }"><a href='<c:url value='/'></c:url>' >请登录后评论</a></c:if>
								<c:if test="${sessionUserInfo != null }"><a href="javascript:void(0)" onclick="showCommentDiv(${trend.id})">评论</a></c:if>
								<c:if test="${sessionUserInfo.id ==trend.userId }">
								|	<a href="javascript:void(0)" onclick="deleteTrends(${trend.id} ,${trend.user.id} )">删除</a>
								</c:if>
							</td>
						</tr>
					</table>
					<div id="${trend.id}_comment_div" style="display: none; margin: 0px 10px;">
						<form style="margin-top: 10px;" method="post" action='<c:url value="/front/comment"></c:url>'>
							<input type="hidden" name="trendId" value="${trend.id}">
							<textarea rows="5" cols="" style="width:100%; " name="contents"  ></textarea><br>
							<div class="offset9">
								<button class="btn btn-success offset" type="submit">发布</button> <button class="btn btn-success " type="reset" onclick="closeCommentDiv(${trend.id})">取消</button>
							</div>
						</form>
					</div>
				</div>
			</c:forEach>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>

