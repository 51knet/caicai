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
	margin: 10px 20px;
	color: #80b029;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.border_ccc{
	border: 1px solid #ccc;
}
.trend_button{
	font-family:'Microsoft YaHei',Arial; 
	background-color: #a6c575; 
	color: #fff; font-weight: bold; 
	font-size: 14px; 
	width: 80px;
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
		//alert(trend_id+"---"+user_id);
	}
    
    function closeCommentDiv(index){
    	  var id =  index+"_comment_div";
    	  $("#"+id).css("display","none");
    	 // $("#"+id+">table").empty();
    }
    
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

</script>
<div class="row-fluid custom round">
	<div style="margin: 0 auto; width: 100%; text-align: center;">
		<form method="post" action='<c:url value="/admin/trend/publish"></c:url>'>
		<input type="hidden" name="trendRole" value="${trendRole }">
		<input type="hidden" name="trendVariety" value="${trendVariety }">
			<textarea  style="width: 100%; height: 100px;" class="border-green-all" name="contents"  onKeyDown="checkMaxInput(this)" onKeyUp="checkMaxInput(this)"></textarea><br>

			<font  id="remLen" class="pull-left"><b></b></font><button class="btn btn-success trend_button pull-right"  type="submit">发 布</button> <br>

		</form>
	</div>
	<div class="row-fluid" >
		<jsp:include page="/WEB-INF/views/_shared/trend/admin/list/trend.jsp"></jsp:include>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


