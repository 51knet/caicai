<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
<!--
 function  NoPost(){
 	return false;
 }
 
$(document).ready(function(){
	$(".projectsbgcolor").mouseover(function(){
		  $(this).css("background-color","#f1f1f1");
	});
	$(".projectsbgcolor").mouseout(function(){
		  $(this).css("background-color","");
	});
});
//-->
</script>
<style>
.right_line{
	/*background-image: url(' <c:url value="/resources/img/default/blueline.png" ></c:url> ' );
	background-position: right center;
	background-repeat: no-repeat;*/
	margin-left: 400px;
	margin-right: 20px;
}
.projects{
	 width: 100%;
	 margin:10px 0px;
	 padding-left: 20px;
}
.projects .detail{
	width: 200px;
	margin-right:20px; margin-top:10px;  
	float: left; padding:5px 5px;  
	border: 1px solid #ccc; text-align: center;
}
.projects .detail .logo{
	width: 200px; height: 139px;
}
.pName{
	font-size:14px;
	font-weight: bold;
	text-decoration: none;
}
._logo{
	width: 200px; height: 139px;
}
.process_out{
	width: 190px; height: 10px; margin-top:5px; 
	background-color: #c0defa; border: 1px solid #319bff;
}
.process_in{
	height: 10px; background-color: #319bff;
	max-width: 191px;
	overflow: hidden;
}
</style>
<div class="container title"  >
		<div class="innerLeftTitle " >
			 <img  src="<c:url value='/resources/img/default/rztitle1.png'></c:url> ">
			  <a href="<c:url value="/projects/list/uncomplete"></c:url>"><img class="right_line" src="<c:url value='/resources/img/default/rztitle2.png'></c:url> "></a>
			   <a href="<c:url value="/projects/list/complete"></c:url>"><img  src="<c:url value='/resources/img/default/rztitle3.png'></c:url> "></a>
		</div>
 </div>
 	
 <div class="container projects">
 	<c:forEach items="${page.content}" var="page"  >
 			<div class="detail projectsbgcolor">
					<a href="<c:url value='/projects/view/${page.id }'></c:url>"><img src="<c:url value='${p_url}${page.logoPath }'></c:url> " class="_logo"></a>
					<div style="width: 195px" id="contentlimit"><a href="<c:url value='/projects/view/${page.id }'></c:url>" class="pName">${page.projectName }</a><br>
					${page.industry }
					<div class="process_out">
						<div class="process_in" style="width: ${page.currentMoney/page.totalMoney*100}%; "></div>
					</div>
							<table style="width: 100%; text-align: center; font-weight: bold;" cellpadding="2">
								<tr><td >已融资</td><td>完成率</td><td>融资总额</td></tr>
								<tr><td>${page.currentMoney }万</td>
								<td><fmt:formatNumber type="number" value="${page.currentMoney/page.totalMoney*100}" maxFractionDigits="0"/>%</td>
								<td>${page.totalMoney }万</td></tr>
							</table>
					</div>
			</div>
	</c:forEach>
 </div>




