<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
li{
 list-style-type: none;
}
.top_block{
float: left; width: 570px; height:254px; border: 1px solid #6597c8;
}

.mid_block{
	float: left; width: 332px; height:254px;border: 1px solid #6597c8; 
}
.btm_block{
	float: left; width: 505px; height:150px;border: 1px solid #6597c8;
}

.padding_right{
	padding: 0px 10px 0px 0px;
}
.conts{
 padding-top: 15px;
 float: left;
}
.box{position:relative;width:440px;margin:0px auto;}
.cont{height:254px;overflow:hidden; width:440px;}
.item{background:rgba(0,0,0,0.5);color:#fff;font-size:80px;position:absolute;left:0;bottom:0;width:100%;text-align:right;height:40px; line-height: 30px;}
.item>a{margin-right:10px;cursor:pointer;text-shadow:0 0 3px rgba(0,0,0,0.8); text-decoration:  none;}
.item>a.seld{color:#06C;}
.item>a.hide{display:none;}
</style>
	<script type="text/javascript">
		var n = 0;
		$(document).ready(function(){
			count=$(".cont a").length;
			$(".item a").click(function(){
				$(this).addClass("seld").siblings().removeClass("seld");	
				var _index=$(this).index();
				$(".cont>a").eq(_index).fadeIn(300).siblings().fadeOut(300);
			});
			t = setInterval("showAuto()", 2000);
			$(".box").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 2000);});
		})
		function showAuto()
		{
			n = n >=(count - 1)?0: ++n;
			$(".item a").eq(n).trigger('click');
		}	
		
		function showAnno(){
			$("#annoShow").css("display","block");
			$("#activityShow").css("display","none");
		}
		function showActivity(){
			$("#annoShow").css("display","none");
			$("#activityShow").css("display","block");
		}
	</script>
<div style="height:254px;">
 <div style="float: left; width: 440px;  ">
 	<div class="box">
    	<div class="cont">
	    	 <c:forEach  var="annos" items="${annoList}">
	    	 	<c:if test="${annos.filePath != null}">
	    	 	  	<a ><img src="<c:url value="${annos.filePath}"></c:url>"></img></a>
	    	 	</c:if>
	        </c:forEach>  
          
        </div>
        <div class="item">
       		 <c:forEach  var="annos" items="${annoList}"   varStatus="i">
       		 <c:if test="${annos.filePath != null}">
       			 <a class="seld">·</a>
       		 </c:if>
            	
           </c:forEach> 
        </div>
    </div>
 </div>
 <div style=" margin-left: 10px;" class="top_block">
 	<div class="_titles">
 		<a href="javascript:void(0)" onmouseover="showAnno()"><img  src="<c:url value='/resources/img/default/title1-1.png' ></c:url>"></a><a href="javascript:void(0)" onmouseover="showActivity()"><img  src="<c:url value='/resources/img/default/title1-2.png' ></c:url>"></a>
 	</div>
 	<div id="annoShow" style="display: block;">
	 	<ul class="padding_right">
	 		<c:forEach var="annos" items="${annoList}" >
		          <li class="btl"><a href="<c:url value='/front/announcement/view/${annos.id }'></c:url>">${annos.title }</a><span style="float: right; ">${annos.date }</span></li>
		    </c:forEach>
	 	</ul>
 	</div>
 	<div id="activityShow" style="display: none;">
	 	<ul class="padding_right">
	 		<c:forEach var="activity" items="${acList}" >
		          <li class="btl"><a href="<c:url value='/front/activity/view/${activity.id }'></c:url>">${activity.title }</a><span style="float: right; ">${activity.date }</span></li>
		    </c:forEach>
	 	</ul>
 	</div>
 </div>
</div>
<div class="conts">
	 <div style="" class="mid_block">
	 	<div class="_titles">
	 		<a href="<c:url value='/front/technology/all'></c:url>"><img  src="<c:url value='/resources/img/default/title2.png' ></c:url>"></a>
	 	</div>
	 <div>
	 	<ul class="padding_right">
	 		<c:forEach var="techs" items="${techList}" >
		          <li class="btl"><a href="<c:url value='/front/technology/view/${techs.id }'></c:url>">${techs.techName }</a><span style="float: right; "><fmt:formatDate value="${techs.date }" pattern="yyyy-MM-dd"/> </span></li>
		    </c:forEach>
	 	</ul>
	 	</div>
	 </div>
	 
	  <div style="margin-left: 11px;" class="mid_block">
	 	<div class="_titles">
	 		<a href="<c:url value='/front/patent/all'></c:url>"><img  src="<c:url value='/resources/img/default/title3.png' ></c:url>"></a>
	 	</div>
	 	<div>
	 	<ul class="padding_right">
	 		<c:forEach var="patents" items="${patentList}" >
		          <li class="btl"><a href="<c:url value='/front/patent/view/${patents.id }'></c:url>">${patents.patentName }</a><span style="float: right; ">${patents.publishDate }</span></li>
		    </c:forEach>
	 	</ul>
	 	</div>
	 </div>
	 
	  <div style="margin-left: 11px;" class="mid_block">
	 	<div class="_titles">
	 		<a href="<c:url value='/front/requirement/all'></c:url>"><img  src="<c:url value='/resources/img/default/title4.png' ></c:url>"></a>
	 	</div>
	 	<div>
	 	<ul class="padding_right">
	 		<c:forEach var="techReqs" items="${techReqList}" >
		          <li class="btl"><a href="<c:url value='/front/requirement/view/${techReqs.id }'></c:url>">${techReqs.title }</a><span style="float: right; margin-right: 10px;"><fmt:formatDate value="${techReqs.date }" pattern="yyyy-MM-dd"/>  </span></li>
		    </c:forEach>
	 	</ul>
	 	</div>
	 </div>
</div>

<div class="conts">
	 <div class="btm_block">
	 	<div class="_titles">
	 		<a href="<c:url value='/front/experts/all'></c:url>"><img  src="<c:url value='/resources/img/default/title5.png' ></c:url>"></a>
	 	</div>
	 	<div>
			<c:forEach var="experts" items="${expertList}" >
					<div style="float: left; text-align: center;  padding: 10px 12px;"><a href='<c:url value="/teacher/${experts.id}"></c:url>'><img src='<c:url value="${experts.photo_url }"></c:url>' style="width: 55px; height:55px;" class="img-circle" /></a>
						<br>
						${experts.name}
					</div>
			</c:forEach>
	 	</div>
	 </div>
	 
	  <div style=" margin-left: 10px;" class="btm_block">
	 	<div class="_titles">
	 		<img  src="<c:url value='/resources/img/default/title6.png' ></c:url>">
	 	</div>
	 	<div>
	 	<ul class="padding_right">
		          <li class="btl">内蒙古石墨烯材料研究院</li>
		           <li class="btl">泰州石墨烯研究与检测平台</li>
	 	</ul>
	 	</div>
	 </div>
</div>

