<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
	.border-white-all{
		border: 1px solid #fff;
	}
	.bb{
		/*border-bottom: 1px solid #f2cc81;*/
		border-bottom: 1px solid #ddd;
	}
	.color_green{
	color: #859c34;
}

.limitTable{
	width:100%; 
	table-layout:fixed
	background-color:#fafafa; 
	margin-bottom: 10px;
}

 .limitTd{
	word-wrap:break-word; word-break:break-all;
	color: #666; font-size: 13px;
}
</style>
<script type="text/javascript">

   function showReply(index){
      var id =  index+"_reply_div";
   	  if($("#"+id).css("display") == "block"){
   		  $("#"+id).css("display","none");
   	  }else{
   		  $("#"+id).css("display","block");
   	  }
    }
    
    function postReplyForm(){
    	//alert("form");
    }
</script>
<div class="row-fluid border-white-all custom" style="background-color: #fff;">
	<div class="content">	
			<table cellpadding="5"  class="limitTable">	
				 <tr  >
					 <td align="left" valign="top" colspan="2" class="limitTd">
						<c:if test="${trendBeans.trend.variety == '' }">
							${trendBeans.trend.context }<br>
						</c:if>
						<c:if test="${trendBeans.trend.variety == 'course' }">
							发布了 ${trendBeans.varietyDescription}：<a href='<c:url value="/trend/${trendBeans.trend.variety}/${trendBeans.trend.user.id}"></c:url>'>${trendBeans.trend.title}</a><br>
							  	<img src=' <c:url value="${trendBeans.trend.coverUrl}"></c:url>'  class="coursecover_width"><br>					
						</c:if>
						<c:if test="${trendBeans.trend.variety != 'course' && trendBeans.trend.variety != null}">
							发布了 ${trendBeans.varietyDescription}：<a href='<c:url value="/trend/${trendBeans.trend.variety}/${trendBeans.trend.user.id}"></c:url>'>${trendBeans.trend.title}</a>${trendBeans.trend.context }<br>						  						  
						</c:if>
					</td>
				</tr>
				<tr class="bb">
					<td align="left" valign="top">
						<a href='<c:url value="/admin/trend/view/${trendBeans.trend.id}"></c:url>'><span class="date"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></a>
					</td>
					<td align="right" valign="top">
						<c:if test="${sessionUserInfo == null }"><a style="" class="color_green" href='<c:url value='/'></c:url>' >请登录后评论</a></c:if>
						<c:if test="${sessionUserInfo != null }"><a href="javascript:void(0)" onclick="showCommentDiv(${trendBeans.trend.id})"><img src="<c:url value='/resources/img/default/commenttip.png'></c:url>" ></a></c:if>
						<c:if test="${sessionUserInfo.id ==trendBeans.trend.user.id }">
						|	<a class="color_green" href="javascript:void(0)" onclick="deleteTrends(${trendBeans.trend.id} ,${trendBeans.trend.user.id} )">删除</a>
						</c:if>
					</td>
				</tr>		
			</table>
			<div id="${trendBeans.trend.id}_comment_div" style="display: block ; margin: 0px 10px; " class="border-ccc-all">
			<c:if test="${sessionUserInfo != null }">
				<form style="margin-top: 10px;" method="post" action='<c:url value="/comment"></c:url>'>
					<input type="hidden" name="trendId" value="${trendBeans.trend.id}">
					<input type="hidden" name="trendRole" value="${trendRole }">
					<textarea rows="4" cols=""  style="width:100%; " name="contents"  class="border-green-all"></textarea><br>
					<div class="offset10">
						<button class="btn btn-success " type="submit">发布</button>
					</div>
				</form></c:if>
				<span class="color_green">共有 ${trendBeans.commentCount} 条评论>></span><br><br>
				<c:forEach items="${trendBeans.commentList}" var="comment">
					<table  cellpadding='0' style="margin-bottom: 10px; color:#444;">
					<tr><td  align='left' valign= 'top' colspan="2">
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'> <img src='<c:url value="${comment.user.photo_url }"></c:url>'  style="width:40px;"></a>
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'> ${comment.user.name }</a>
							<c:if test="${comment.host == null }">
								：${comment.context }<br>
							</c:if>
							<c:if test="${comment.host != null}">
								回复了 <a href='<c:url value="/id/${comment.host.id }"></c:url>'>${comment.host.name}</a> ：${comment.context }
							</c:if>
							</td></tr>
							<tr>
								<td align="left" valign="top">	<span class="date"><fmt:formatDate value="${comment.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span></td>
								<td align="right" valign="top">	
									<c:if test="${sessionUserInfo != null }"><a class="color_green" href="javascript:void(0)" onclick="showReply(${comment.id })">回复 </a></c:if>
									<c:if test="${sessionUserInfo == null }"><a  class="color_green" href='<c:url value='/'></c:url>' >请登录后回复</a></c:if>
								</td>
							</tr>
						<tr  class='bb'><td  align='right' valign= 'top'  colspan="2">
							<div style="display: none; margin-top: 10px;" id="${comment.id }_reply_div">
								<form  method="post" action='<c:url value="/reply"></c:url>'  >
									<input type="hidden" name="hostId" value="${comment.user.id }" >
									<input type="hidden" name="trendId" value="${trendBeans.trend.id}" >
									<input type="hidden" name="trendRole" value="${trendRole }">
									<textarea rows="4" cols="" style="width:100%; " name="contents"  class="border-green-all"></textarea><br>
									<div class="offset10">
										 <button class="btn btn-success offset"  onclick="postReplyForm()">发布</button>
										<!--<a href="javascript:void(0)" class="btn btn-success offset"  onclick="postReplyForm()">发布</a>   -->
									</div>
								</form>
							</div>
						</td></tr>
					</table>
				</c:forEach>
			</div>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


