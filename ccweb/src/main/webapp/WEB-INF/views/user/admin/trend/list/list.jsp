<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	border-bottom: 1px solid #f2cc81;
}
.date{
	font-size: 12px;
	color: #999;
}
.photo_width{
	width: 70px;
}

.coursecover_width{
	width:220px;
}
</style>
<script type="text/javascript">

    function	showCommentDiv(index){
	  var id =  index+"_comment_div";
	 // $("#"+id+">table").empty();
	  $("#"+id).css("display","block");
	 /* $.ajax({
		   type: "POST",
		   url: '<c:url value="/showTrendsComment"></c:url>',
		   data: "trendId="+index,
		   dataType:"json",
		   success: function(msg){
			//   alert(msg);
		     var  t = "<table width='100%' cellpadding='5'>";
		     for( var i=0;i<msg.length;i++){
		    	 t+="<tr><td  align='left' valign= 'top'>  <img src='/ccweb"+msg[i].photo_url+"  ' style='width:40px' />&nbsp;&nbsp;"+msg[i].name+"</td></tr><tr  class='bb'><td  align='left' valign= 'top'>"+msg[i].context+"</td></tr>";
		     }
		     $("#"+id).append(t);
		   }
		});*/
	}
	
    function deleteTrends(trend_id, user_id){
		alert(trend_id+"---"+user_id);
	}
    
    function closeCommentDiv(index){
    	  var id =  index+"_comment_div";
    	  $("#"+id).css("display","none");
    	 // $("#"+id+">table").empty();
    }
</script>
<div class="row-fluid custom round">
	<div style="margin: 0 auto; width: 100%; text-align: center;">
		<form style="margin-top: 10px;" method="post" action='<c:url value="/admin/trend/publish"></c:url>'>
			<textarea rows="2" cols="" style="width: 90%; " name="contents"  ></textarea><br>
			<div class="offset9">
				<button class="btn btn-success offset" type="submit">发布</button> 
			</div>
		</form>
	</div>
	<div class="content">	
			<c:forEach items="${myTrend}" var="trendBeans">
				<div style="margin: 10px 0px;" class="border" >
					<table width="100%" cellpadding="5" >	
					<c:if test="${trendBeans.trend.variety == null }">
						<tr>
							  <td rowspan="3" align="left" valign="top" width="10%"><img src=' <c:url value="${trendBeans.trend.photo_url }"></c:url>'  class="photo_width"></td>
							   <td align="left" valign="top" ><a href='<c:url value="/id/${trendBeans.trend.userId }"></c:url>' > ${trendBeans.trend.name}</a></td>
						 </tr>
						<tr  class="bb">
							   <td align="left" valign="top">${trendBeans.trend.context }<br><span class="date"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
						</tr>
					</c:if>
					<c:if test="${trendBeans.trend.variety == 'announcement' }">
						<tr>
							  <td rowspan="3" align="left" valign="top" width="10%"><img src=' <c:url value="${trendBeans.trend.photo_url }"></c:url>'  class="photo_width"></td>
							   <td align="left" valign="top" ><a href='<c:url value="/id/${trendBeans.trend.userId }"></c:url>' > ${trendBeans.trend.name}</a> 发布了一篇公告：<a href="javascript:void(0)">${trendBeans.trend.title }</a></td>
						 </tr>
						<tr  class="bb">
							   <td align="left" valign="top"><span class="date"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
						</tr>
					</c:if>
					<c:if test="${trendBeans.trend.variety == 'courseresource' }">
						<tr>
							  <td rowspan="3" align="left" valign="top" width="10%"><img src=' <c:url value="${trendBeans.trend.photo_url }"></c:url>'  class="photo_width"></td>
							   <td align="left" valign="top" ><a href='<c:url value="/id/${trendBeans.trend.userId }"></c:url>' > ${trendBeans.trend.name}</a> 上传了一个资源：<a href="javascript:void(0)">${trendBeans.trend.title }</a></td>
						 </tr>
						<tr  class="bb">
							   <td align="left" valign="top"><span class="date"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
						</tr>
					</c:if>
					<c:if test="${trendBeans.trend.variety == 'course' }">
						<tr>
							 <td rowspan="3" align="left" valign="top" width="10%"><img src=' <c:url value="${trendBeans.trend.photo_url }"></c:url>'  class="photo_width"></td>
							 <td align="left" valign="top" ><a href='<c:url value="/id/${trendBeans.trend.userId }"></c:url>' > ${trendBeans.trend.name}</a> 发布了一门课程：<a href="javascript:void(0)">${trendBeans.trend.title }</a></td>
						</tr>
						<tr  class="bb">
						  <td align="left" valign="top">
						  	<img src=' <c:url value="${trendBeans.trend.coverUrl}"></c:url>'  class="coursecover_width"><br>
						  	<span class="date"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span>
						  </td>
						</tr>
					</c:if>
					<tr >
						<td align="right" valign="top"><a href="javascript:void(0)" onclick="showCommentDiv(${trendBeans.trend.id})">评论(${trendBeans.commentCount})</a>
							<c:if test="${sessionUserInfo.id ==trendBeans.trend.userId }">
							|	<a href="javascript:void(0)" onclick="deleteTrends(${trendBeans.trend.id} ,${trendBeans.trend.userId} )">删除</a>
							</c:if>
						</td>
					</tr>		
					</table>
					<div id="${trendBeans.trend.id}_comment_div" style="display: none; margin: 0px 10px;">
						<form style="margin-top: 10px;" method="post" action='<c:url value="/comment"></c:url>'>
							<input type="hidden" name="trendId" value="${trendBeans.trend.id}">
							<textarea rows="1" cols="" style="width:100%; " name="contents"  ></textarea><br>
							<div class="offset9">
								<button class="btn btn-success offset" type="submit">发布</button> <button class="btn btn-success " type="reset" onclick="closeCommentDiv(${trendBeans.trend.id})">取消</button>
							</div>
						</form>
						
						<c:forEach items="${trendBeans.commentList}" var="comment">
							<table width='100%' cellpadding='5'>
								<tr><td  align='left' valign= 'top'>  <img src='<c:url value="${comment.photo_url }"></c:url>'  style="width:40px;"> ${comment.name }</td></tr>
								<tr  class='bb'><td  align='left' valign= 'top'>${comment.context }</td></tr>
							</table>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


