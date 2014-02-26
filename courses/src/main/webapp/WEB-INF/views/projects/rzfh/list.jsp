<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
<!--

$(document).ready(function(){
	$(".projectsbgcolor").mouseover(function(){
		  $(this).css("background-color","#f1f1f1");
	});
	$(".projectsbgcolor").mouseout(function(){
		  $(this).css("background-color","");
	});
});

 function  NoPost(){
 	return false;
 }
//-->
</script>
<style>
.right_line{
	/*background-image: url(' <c:url value="/resources/img/default/blueline.png" ></c:url> ' );
	background-position: right center;
	background-repeat: no-repeat;*/
	margin-left: 330px;
	margin-right: 20px;
}

.projects{
	 width: 100%;
	 margin:10px 0px;
	 padding-left: 20px;
}
.projects .detail{
	width: 680px;
	margin-top:10px;  
	float: left; padding:5px 5px;  
	border-bottom: 1px solid #ccc; text-align:left;
}
.projects .detail .logo_div{
	float: left; border: 1px solid #ccc; padding: 2px 2px;
}

.projects .detail .info_div{
	float: left; margin-left: 20px; 
	max-width: 440px; overflow: hidden;
}

.projects .detai .info_div  .content_div{
 clear: both; width: 100%; max-width: 680px; 
 max-height: 60px; overflow: hidden;
}

/*.limitTable{
	width:100%; 
	table-layout:fixed;
	margin-bottom: 10px;
}
 .limitTd{
	word-wrap:break-word; word-break:break-all;
	color: #666; font-size: 13px;
}*/

._logo{
	width: 200px; height: 139px;
}
.process_out{
	width: 349px; height: 10px; margin-top:5px; 
	background-color: #c0defa; border: 1px solid #319bff;
}
.process_in{
	height: 10px; background-color: #319bff;
	max-width: 350px;
	overflow: hidden;
}
.process_tab{
	width: 350px;;  font-weight: bold;
	text-align: left;
	color: #6497ce
}
.date{
	font-size: 12px;
	font-weight: normal;
}
.points{
	font-size: 14px;
}
.myblock{
	padding: 3px 3px; background-color: #6597c8; color: #fff; font-size: 14px; font-weight: bold;
}
</style>
<div class="container title"  >
		<div class="innerLeftTitle " >
			 <img  src="<c:url value='/resources/img/default/rztitle1.png'></c:url> ">
		</div>
 </div>
 	
 <div class="container projects">
 	<c:forEach items="${page.content}" var="page"  >
 			<div class="detail projectsbgcolor">
				<div class="logo_div"><a href="<c:url value='${page.webUrl}'></c:url>"><img src="<c:url value='${p_url}${page.logoPath }'></c:url> " class="_logo"></a></div>
				<div  class="info_div">
					<a href="#"><h4>${page.name }</h4></a>
					<div class="content_div" style="max-height: 120px; overflow: hidden;">
						${page.content }
					</div>
				</div>
			</div>
	</c:forEach>
 </div>
 <div style="margin-left: 20px;">
 <c:choose>
	<c:when test="${searchParam != null }">
		  <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
	 	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
</div>



