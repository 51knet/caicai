<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
.border_ccc{
	border: 1px solid #ccc;
}
.bb{
	border-bottom: 1px solid #ccc;
}
</style>
<script type="text/javascript">

    function	showCommentDiv(index){
	  var id =  index+"_comment_div";
	  $("#"+id).css("display","block");
	  $.ajax({
		   type: "POST",
		   url: '<c:url value="/showTrendsComment"></c:url>',
		   data: "trendId="+index,
		   dataType:"json",
		   success: function(msg){
			//   alert(msg);
		     var  t = "<table width='100%' cellpadding='5'>";
		     for( var i=0;i<msg.length;i++){
		    	 t+="<tr><td  align='left' valign= 'top'>评论人："+msg[i].name+"</td></tr><tr  class='bb'><td  align='left' valign= 'top'>"+msg[i].context+"</td></tr>";
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
<div class="row-fluid custom round">
	<div style="margin: 0 auto; width: 100%; text-align: center;">
		<form style="margin-top: 10px;" method="post" action='<c:url value="/admin/mytrends"></c:url>'>
			<textarea rows="10" cols="" style="width: 90%; " name="contents"  ></textarea><br>
			<div class="offset8">
				<button class="btn btn-success offset" type="submit">发布</button> <button class="btn btn-success " type="reset">取消</button>
			</div>
		</form>
	</div>
	<div class="content">	
			<c:forEach items="${myTrend}" var="trend">
				<div style="margin: 10px 0px;" class="border">
					<table width="100%" cellpadding="5">
						<tr>
							<td align="left" valign="top">${trend.context }</td>
						</tr>
						<tr >
							<td align="right" valign="top"><a href="javascript:void(0)" onclick="showCommentDiv(${trend.id})">评论</a>
								<c:if test="${sessionUserInfo.id ==trend.userId }">
								|	<a href="javascript:void(0)" onclick="deleteTrends(${trend.id} ,${trend.userId} )">删除</a>
								</c:if>
							</td>
						</tr>
					</table>
					<div id="${trend.id}_comment_div" style="display: none; margin: 0px 10px;">
						<form style="margin-top: 10px;" method="post" action='<c:url value="/comment"></c:url>'>
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


