<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
<!--

//-->
</script>
<style>
.right_line{
	margin-left: 400px;
	margin-right: 20px;
}
.projects{
	 width: 100%;
	 background-color: #6797c8;
	 height: 600px;
	margin: 36px 0px 20px 0px;
}

.projects .user_info_div{
	width: 97%;
	float: right;
	background-color: #e4e7ec;
	height: 600px;
}
.user_img{
	width: 80px;
	margin: 10px 20px;float: left;
}
.user_info{
margin: 10px 10px;float: left;
}
.user_info>div{
width: 80px; float: left; margin: 10px 10px;
}
.bottom_line_solid{
	height: 5px;border-bottom: 1.5px solid #ccc;
	width: 100%;float: left;
}
.blue_top{
	background-color: #6797c8; color: #fff; font-weight: bold;
}
.white_btm{
	background-color: #fff; color: #6797c8; font-weight: bold;
}
</style>

 <div class="container projects">
 	<div class="user_info_div">
 		<div style="margin: 20px 0px 0px 10px; float: left;">
 			<span class="points" ><h4>项目发起人</h4></span>
 		</div>
 	 	<div class="bottom_line_solid"></div>
 	 	
 	 	<div>
 	 		<div class="user_img"><img src='<c:url value="${url}${projects.user.photo_url }"></c:url>' /></div>
 	 		<div class="points user_info"> <h4>${projects.user.name}</h4></div>
 	 	</div>
 	 	<div style="margin: 10px 0px 0px 10px; float: left; ">
 			<span class="points" ><h4>融资金额及进度</h4></span>
 		</div>
 	 	<div class="bottom_line_solid"></div>
 	 	<div class="user_info points">
 			<div style="">
 				<table  cellpadding="5">
 					<tr class="blue_top"><td align="center">当前融资</td></tr>
 					<tr class="white_btm">
 					<td align="center"><span><fmt:formatNumber type="number" value="${projects.currentMoney/projects.totalMoney*100}" maxFractionDigits="0"/> %</span></td></tr>
 				</table>
 			</div>
 			
 			<div >
 				<table  cellpadding="5">
 					<tr class="blue_top"><td align="center">已融资金</td></tr>
 					<tr class="white_btm"><td align="center"><span>${projects.currentMoney } 万</span></td></tr>
 				</table>
 			</div>
 	 	</div>
 	</div>
 </div>




