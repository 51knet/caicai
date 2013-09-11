<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
<!--
	$(document).ready(function(){
		$(".unReadMsg").mouseover(function(){
			  $(this).css("background-color","#fafafa");
		});
		$(".unReadMsg").mouseout(function(){
			  $(this).css("background-color","");
		});
	});
//-->
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
a{
	text-decoration: none;
}
</style>
<div class="row-fluid custom round">
	<div class="row">
		<h4>站内消息</h4>
	</div>
	<div class="content">	
		<div style="text-align: center; margin-bottom: 10px;">
				<table cellpadding="10" width=100%  border=0 style="" class="bb unReadMsg">
		  			  <tr>
					    <td rowspan="2" align="left" width="10%" ><a href="#"><img src="<c:url value='/resources/img/default/comm-icon.png'></c:url>" style="width:60px; height: 60px;"></a></td>
					    <td align="left" style="font-weight: bold; ">评论</td>
					    <td align="right"  width="40%"  style="color: #666; font-size: 13px;"> <fmt:formatDate value="${newComment.publishDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					  </tr>
					  <tr>
					    <td colspan="2" align="left" style="color: #666; font-size: 13px;">
					   			<c:if test="${newComment == null }"> 暂无评论</c:if>
					   			<c:if test="${newComment != null }"> ${newComment.user.name }：${newComment.context }</c:if>  
					    </td>
					  </tr>
			  	</table>
		</div>
		<div style="text-align: right;">
					<c:forEach items="${unReadListSender}" var = "unReadMsgList" >
						<table cellpadding="10" width=100%  border=0 style="" class="bb unReadMsg">
				  			  <tr>
							    <td rowspan="2" align="left" width="10%" ><a href='<c:url value="/admin/message/detail/${unReadMsgList.sendMsg.user.id}"></c:url>'><img src="<c:url value='${unReadMsgList.sendMsg.user.photo_url} '></c:url>" style="width:60px; height: 60px;"></a></td>
							    <td align="left" style="font-weight: bold; padding:10px 10px;">${unReadMsgList.sendMsg.user.name}</td>
							    <td align="right"  width="20%"  style="color: #666; font-size: 13px;padding:10px 10px;"> ${unReadMsgList.sendMsg.date}</td>
							  </tr>
							  <tr>
							    <td colspan="2" align="left" style="color: #666; font-size: 13px;padding:10px 10px;"><a href="#">${unReadMsgList.sendMsg.content}</a></td>
							  </tr>
				  		</table>
				  
			  		</c:forEach>
			<!-- <div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div> -->
		</div>
		
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