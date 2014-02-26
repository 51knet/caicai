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
  .grayscale{
          -webkit-filter:grayscale(1);
      }    
.right_line{
	/*background-image: url(' <c:url value="/resources/img/default/blueline.png" ></c:url> ' );
	background-position: right center;
	background-repeat: no-repeat;*/
	margin-left: 400px;
	margin-right: 20px;
}
.projects{
	margin: 10px 20px;
	max-width: 96%;
	text-align: cemter;
}
.projects .detail{
	width: 170px;
	margin:10px 15px 0px 1px;
	float: left; padding:5px 4px;  
	border: 1px solid #ccc; text-align: center;
}

.pName{
	font-size:14px;
	font-weight: bold;
	text-decoration: none;
}
._logo{
	width: 170px; 
}
.process_out{
	width: 163px; height: 10px; margin-top:5px; 
	background-color: #c0defa; border: 1px solid #319bff;
}
.process_in{
	height: 10px; background-color: #319bff;
	max-width: 164px;
	overflow: hidden;
}
.container.title.custom{
	margin: 0px 20px;
	max-width: 96%;
	background-image: url("<c:url value='/resources/img/default/maq_left.png'></c:url>");
	background-position: left bottom;
	background-repeat: no-repeat;
	border-bottom: 2px solid #335183;
	background-color: #fff;
}
.container.title .innerLeftTitle.custom{
	line-height: 45px;
	height: 30px;
}
.container.title .innerRightTitle a{
	line-height: 45px;
	color: #335183;
	font-weight: bold;
}
</style>
<br>
<div class="container title custom"  >
		<div class="innerLeftTitle custom" >
			 <img  src="<c:url value='/resources/img/default/rztitle2.png'></c:url> ">
		</div>
		<div class="innerRightTitle " >
			<a href="<c:url value='/projects/list/uncomplete'></c:url>"> 查看更多></a>
		</div>
 </div>
 	
 <div class="container projects">
 	<c:forEach items="${upList}" var="upList"  begin="0" end="4">
 			<div class="detail projectsbgcolor">
					<a href="<c:url value='/projects/view/${upList.id }'></c:url>"><img src="<c:url value='${p_url}${upList.logoPath }'></c:url> " class="_logo"></a>
					<div style="width: 165px" id="contentlimit"><a href="<c:url value='/projects/view/${upList.id }'></c:url>" class="pName">${upList.projectName }</a><br>
					${upList.industry }
					<div class="process_out">
						<div class="process_in" style="width: ${upList.currentMoney/upList.totalMoney*100}%; "></div>
					</div>
						<table style="width: 100%; text-align: center; font-weight: bold;" cellpadding="0">
							<tr><td >已融资</td><td>完成率</td><td>融资总额</td></tr>
							<tr><td>${upList.currentMoney }万</td>
							<td><fmt:formatNumber type="number" value="${upList.currentMoney/upList.totalMoney*100}" maxFractionDigits="0"/>%</td>
							<td>${upList.totalMoney }万</td></tr>
						</table>
					</div>
			</div>
	</c:forEach>
 </div>
 
 <div class="container title custom"  >
		<div class="innerLeftTitle custom" >
			 <img  src="<c:url value='/resources/img/default/rztitle3.png'></c:url> ">
		</div>
		<div class="innerRightTitle " >
			<a href="<c:url value='/projects/list/complete'></c:url>"> 查看更多></a>
		</div>
 </div>

 <div class="container projects">
 	<c:forEach items="${cpList}" var="cpList"  >
 			<div class="detail projectsbgcolor">
					<a href="<c:url value='/projects/view/${cpList.id }'></c:url>"><img src="<c:url value='${p_url}${cpList.logoPath }'></c:url> " class="_logo"></a>
					<div style="width: 165px" id="contentlimit"><a href="<c:url value='/projects/view/${cpList.id }'></c:url>" class="pName">${cpList.projectName }</a><br>
					${cpList.industry }
					<div class="process_out">
						<div class="process_in" style="width: ${cpList.currentMoney/cpList.totalMoney*100}%; "></div>
					</div>
						<table style="width: 100%; text-align: center; font-weight: bold;" cellpadding="0">
							<tr><td >已融资</td><td>完成率</td><td>融资总额</td></tr>
							<tr><td>${cpList.currentMoney }万</td>
							<td><fmt:formatNumber type="number" value="${cpList.currentMoney/cpList.totalMoney*100}" maxFractionDigits="0"/>%</td>
							<td>${cpList.totalMoney }万</td></tr>
						</table>
					</div>
			</div>
	</c:forEach>
 </div>



