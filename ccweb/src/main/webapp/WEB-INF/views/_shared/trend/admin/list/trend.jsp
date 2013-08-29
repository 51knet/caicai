<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
<!--
.bb{
	/*border-bottom: 1px solid #f2cc81;*/
	border-bottom: 1px solid #ddd;
}

.border-white-all{
	border: 1px solid #fff;
}

.border-ccc-all{
	border: 1px solid #ccc;
}
.date{
	font-size: 12px;
	color: #999;
}
.photo_width{
	width: 55px;
}

.coursecover_width{
	width:180px;
}

.variety_filter{
	width:12%; 
	float: left; 
	padding:3px 0px;
	 text-align: center;
}

.variety_filter >a{
color:  #adc877;
font-weight: bold;
text-decoration: none;
}
.variety_filter >a:hover{
color: #9db84d;
font-weight: bold;
text-decoration: none;
}
-->
</style>
<script type="text/javascript">

    function	showCommentDiv(index){
		  var id =  index+"_comment_div";
		 // $("#"+id+">table").empty();
	
		  if($("#"+id).css("display") == "block"){
			  $("#"+id).css("display","none");
		  }else{
			  $("#"+id).css("display","block");
		  }
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
    
    function postReplyForm(){
    	//alert("form");
    }
    
    function postCommentForm(index){
    	var id =  index+"_comment_form";
    	var showId = index+"_ajax_comment_div";
		$.post('<c:url value="/ajaxcomment" />', $("#"+id).serialize(), function(msg){
			var d = new Date(msg.publishDate);
			var date = formatDate(d);
			 var  t = "<table width='100%' cellpadding='5'>";
		     t+="<tr><td  align='left' valign= 'top'><img src='/ccweb"+msg.user.photo_url+"  ' style='width:40px' />&nbsp;&nbsp;<a href='/ccweb/id/"+msg.user.id+" '>"+msg.user.name+"</a>："+msg.context+"</td></tr>";
		  //   t+="<tr  class='bb'><td  align='left' valign= 'top'><span class='date'>"+date+"</span>";
		  //   t+="<tr  class='bb'><td  align='left' valign= 'top'><a href='javascript:void(0)' onclick='showReply("+msg.id+")'>回复</a></td></tr></table>";
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
			 var  t = "<table width='100%' cellpadding='5'>";
		     t+="<tr class='bb'><td  align='left' valign= 'top'><img src='/ccweb"+msg.user.photo_url+"  ' style='width:40px' />&nbsp;&nbsp;<a href='/ccweb/id/"+msg.user.id+" '>"+msg.user.name+"</a> 回复了 <a href='/ccweb/id/"+msg.host.id+" '>"+msg.host.name+"</a>："+msg.context+"</td></tr>";
		  //   t+="<tr  class='bb'><td  align='left' valign= 'top'><span class='date'>"+date+"</span>";
		    t+="</table>";
		     $("#"+showId).append(t); 
		     $("#"+showId).css("display","block");
		}, "json");
		$("#"+id +" textarea").html("");
		$("#"+replydiv).css("display","none");
    }
</script>
<div class="row-fluid border-green-all" style="background-color:#ebf4df;">
	<div class="row-fluid border-green-right variety_filter"  ><a href='<c:url value='/admin/trend/${trendRole }/all'></c:url>' >全部</a></div>
	<div class="row-fluid border-green-right variety_filter"  ><a href='<c:url value='/admin/trend/${trendRole }/course'></c:url>' >课程</a></div>
	<div class="row-fluid border-green-right variety_filter"><a href='<c:url value='/admin/trend/${trendRole }/resource'></c:url>'>资源</a></div>
	<div class="row-fluid border-green-right variety_filter" ><a href='<c:url value='/admin/trend/${trendRole }/announcement'></c:url>'>公告</a></div>
	<div class="row-fluid  border-green-right variety_filter" ><a href='<c:url value='/admin/trend/${trendRole }/shuoshuo'></c:url>'>说说</a></div>
</div>
<div class="row-fluid  border-white-all" style="background-color: #fff;">
	<div class="row" style="color: #444;">	
		<c:forEach items="${trend}" var="trendBeans">
			<div style="margin: 10px 0px; " class="" >
				<table width="100%" cellpadding="5" >	
					<tr>
						  <td rowspan="3" align="left" valign="top" width="10%"><a href='<c:url value="/id/${trendBeans.trend.user.id }"></c:url>' >
						  <img src=' <c:url value="${trendBeans.trend.user.photo_url }"></c:url>'  class="photo_width"></a></td>
						   <td align="left" valign="top" ><a href='<c:url value="/id/${trendBeans.trend.user.id }"></c:url>' > ${trendBeans.trend.user.name}</a></td>
					 </tr>
				<c:if test="${trendBeans.trend.variety == null }">
					<tr  >
						   <td align="left" valign="top">${trendBeans.trend.context }<br>
				</c:if>
				<c:if test="${trendBeans.trend.variety == 'course' }">
					<tr  >
					  <td align="left" valign="top">发布了 ${trendBeans.varietyDescription}：<a href='<c:url value="/trend/${trendBeans.trend.variety}/${trendBeans.trend.user.id}"></c:url>'>${trendBeans.trend.title}</a><br>
					  	<img src=' <c:url value="${trendBeans.trend.coverUrl}"></c:url>'  class="coursecover_width"><br>					
				</c:if>
				<c:if test="${trendBeans.trend.variety != 'course' && trendBeans.trend.variety != null}">
					<tr  >
					  <td align="left" valign="top">发布了 ${trendBeans.varietyDescription}：<a href='<c:url value="/trend/${trendBeans.trend.variety}/${trendBeans.trend.user.id}"></c:url>'>${trendBeans.trend.title}</a>${trendBeans.trend.context }<br>						  
				</c:if>
					<span class="date"><a href='<c:url value="/admin/trend/view/${trendBeans.trend.id}"></c:url>'><fmt:formatDate value="${trendBeans.trend.publishDate}" pattern="yyyy-MM-dd HH:mm"/></a></span></td>
				</tr>
				<tr class="bb">
					<td align="right" valign="top"><a href="javascript:void(0)" onclick="showCommentDiv(${trendBeans.trend.id})">评论(${trendBeans.commentCount})</a>
						<c:if test="${sessionUserInfo.id ==trendBeans.trend.user.id }">
						|	<a href="javascript:void(0)" onclick="deleteTrends(${trendBeans.trend.id} ,${trendBeans.trend.user.id} )">删除</a>
						</c:if>
					</td>
				</tr>		
				</table>
				<div id="${trendBeans.trend.id}_comment_div" class="border-ccc-all"  style='display: <c:if test="${ show == trendBeans.trend.id }"> block</c:if><c:if test="${show != trendBeans.trend.id}"> none</c:if> ; padding: 0px 10px;'>
					<form style="margin-top: 10px;" method="post" action='<c:url value="/comment"  ></c:url>'  id="${trendBeans.trend.id}_comment_form">
						<input type="hidden" name="trendId" value="${trendBeans.trend.id}">
						<input type="hidden" name="trendRole" value="${trendRole }">
						<textarea rows="3" cols=""  style="width:100%; " name="contents"  ></textarea><br>
						<div class="offset10">
							<!--<button class="btn btn-success " type="submit">发布</button> -->
							<a href="javascript:void(0)" class="btn btn-success offset"  onclick="postCommentForm(${trendBeans.trend.id})">发布</a>  
						</div>
					</form>
					<div id="${trendBeans.trend.id}_ajax_comment_div"  style="display: none; margin-bottom: 10px;">
					
					</div>
					<c:forEach items="${trendBeans.commentList}" var="comment">
						<table width='98%' cellpadding='0' style="margin-bottom: 10px;">
							<tr><td  align='left' valign= 'top'>
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'> <img src='<c:url value="${comment.user.photo_url }"></c:url>'  style="width:40px;"></a>
								<a href='<c:url value="/id/${comment.user.id }"></c:url>'> ${comment.user.name }</a>
							<c:if test="${comment.host == null }">
								：${comment.context }<br>
							</c:if>
							<c:if test="${comment.host != null}">
								回复了 <a href='<c:url value="/id/${comment.host.id }"></c:url>'>${comment.host.name}</a> ：${comment.context }<br>
							</c:if>
							<span class="date"><fmt:formatDate value="${comment.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span>
									<a href="javascript:void(0)" onclick="showReply(${comment.id })">回复</a>
							</td></tr>
							<tr  class='bb'><td  align='right' valign= 'top'>
								<div style="display: none; margin-top: 10px;" id="${comment.id }_reply_div">
									<form  method="post" action='<c:url value="/reply"></c:url>'  id="${comment.id }_reply_form">
										<input type="hidden" name="hostId" value="${comment.user.id }" >
										<input type="hidden" name="trendId" value="${trendBeans.trend.id}" >
											<input type="hidden" name="trendRole" value="${trendRole }">
										<textarea rows="3" cols="" style="width:100%; " name="contents"  ></textarea><br>
										<div class="offset10">
											<!-- <button class="btn btn-success offset"  onclick="postReplyForm()">发布</button> -->
											<a href="javascript:void(0)" class="btn btn-success offset"  onclick="postReplyForm(${comment.id} , ${trendBeans.trend.id})">发布</a>  
										</div>
									</form>
								</div>
							</td></tr>
						</table>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
		<c:if test="${trendCount <= 0 }">
			<h3>尚无该角色动态</h3>
		</c:if>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


