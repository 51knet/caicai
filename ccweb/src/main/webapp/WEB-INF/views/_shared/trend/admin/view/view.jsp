<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
<div class="row-fluid custom round">
	<div class="content">	
		<div  class="border" >
			<table width="100%" cellpadding="5" >	
				<tr>
					  <td rowspan="3" align="left" valign="top" width="10%"><a href='<c:url value="/id/${trendBeans.trend.user.id }"></c:url>' >
					  <img src=' <c:url value="${trendBeans.trend.user.photo_url }"></c:url>'  class="photo_width"></a></td>
					   <td align="left" valign="top" ><a href='<c:url value="/id/${trendBeans.trend.user.id }"></c:url>' > ${trendBeans.trend.user.name}</a></td>
				 </tr>
			<c:if test="${trendBeans.trend.variety == null }">
				<tr  class="bb">
					   <td align="left" valign="top">${trendBeans.trend.context }<br>
			</c:if>
			<c:if test="${trendBeans.trend.variety == 'course' }">
				<tr  class="bb">
				  <td align="left" valign="top">发布了 ${trendBeans.varityDescription}：<a href='<c:url value="/trend/${trendBeans.trend.variety}/${trendBeans.trend.user.id}"></c:url>'>${trendBeans.trend.title}</a><br>
				  	<img src=' <c:url value="${trendBeans.trend.coverUrl}"></c:url>'  class="coursecover_width"><br>					
			</c:if>
			<c:if test="${trendBeans.trend.variety != 'course' && trendBeans.trend.variety != null}">
				<tr  class="bb">
				  <td align="left" valign="top">发布了 ${trendBeans.varityDescription}：<a href='<c:url value="/trend/${trendBeans.trend.variety}/${trendBeans.trend.user.id}"></c:url>'>${trendBeans.trend.title}</a><br>						  
			</c:if>
				<span class="date"><a href="/admin/trend/view/${trendBeans.trend.id}"><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></a></span></td>
			</tr>
			<tr >
				<td align="right" valign="top"><a href="javascript:void(0)" onclick="showCommentDiv(${trendBeans.trend.id})">评论(${trendBeans.commentCount})</a>
					<c:if test="${sessionUserInfo.id ==trendBeans.trend.user.id }">
					|	<a href="javascript:void(0)" onclick="deleteTrends(${trendBeans.trend.id} ,${trendBeans.trend.user.id} )">删除</a>
					</c:if>
				</td>
			</tr>		
			</table>
			<div id="${trendBeans.trend.id}_comment_div" style='display: block ; margin: 0px 10px;'>
				<form style="margin-top: 10px;" method="post" action='<c:url value="/comment"></c:url>'>
					<input type="hidden" name="trendId" value="${trendBeans.trend.id}">
					<textarea rows="1" cols=""  style="width:100%; " name="contents"  ></textarea><br>
					<div class="offset10">
						<button class="btn btn-success " type="submit">发布</button>
					</div>
				</form>

				<c:forEach items="${trendBeans.commentList}" var="comment">
					<table width='98%' cellpadding='0' style="margin-bottom: 10px;">
						<c:if test="${comment.host == null }">
							<tr><td  align='left' valign= 'top'> <a href='<c:url value="/id/${comment.user.id }"></c:url>'> <img src='<c:url value="${comment.user.photo_url }"></c:url>'  style="width:40px;"></a>
							<a href='<c:url value="/id/${comment.user.id }"></c:url>'> ${comment.user.name }</a>：${comment.context }<br>
								<span class="date"><fmt:formatDate value="${comment.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span>
								<a href="javascript:void(0)" onclick="showReply(${comment.id })">回复</a>
							</td></tr>
						</c:if>
						<c:if test="${comment.host != null}">
							<tr ><td  align='left' valign= 'top'>
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'><img src='<c:url value="${comment.user.photo_url }"></c:url>'  style="width:40px;"></a> <a href='<c:url value="/id/${comment.user.id }"></c:url>'> ${comment.user.name }</a> 回复了 
								<a href='<c:url value="/id/${comment.host.id }"></c:url>'>${comment.host.name}</a> ：${comment.context }<br>
								<span class="date"><fmt:formatDate value="${comment.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span>
								<a href="javascript:void(0)" onclick="showReply(${comment.id })">回复</a>
							</td></tr>
						</c:if>
						<tr  class='bb'><td  align='right' valign= 'top'>
							<div style="display: none; margin-top: 10px;" id="${comment.id }_reply_div">
								<form  method="post" action='<c:url value="/reply"></c:url>'  >
									<input type="hidden" name="hostId" value="${comment.user.id }" >
									<input type="hidden" name="trendId" value="${trendBeans.trend.id}" >
									<textarea rows="1" cols="" style="width:100%; " name="contents"  ></textarea><br>
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
		</div>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>

