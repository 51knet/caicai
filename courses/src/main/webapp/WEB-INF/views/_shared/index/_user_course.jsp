<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
	.container .user-course{
		 background-image: url("<c:url value='/resources/img/default/carousel_bg.png'></c:url>");
		  height: 360px;
		  background-position: top center;
		  background-repeat: repeat-x;
		  text-align: center;
	}
	.bb{
		border-bottom: solid 1px #ccc;
	}
	
	.carouselbg{
		 background-image: url("<c:url value='/resources/img/default/maq_left.png'></c:url>");
		 height: 49px;
		 background-position: left top;
		 background-repeat: no-repeat;
		 background-color: #f6efe5;
	}
	.valign_center{
		margin-top: 15px;
	}
	.maq_left{
		font-size: 20px;
		color: #fff;
		font-weight: bold;
	}
	
	.maq_right_content{
		margin-right: 50px;
			font-size: 18px;
		font-weight: bold;
	}
</style>

<div class="carouselbg" >
	<div class="row-fluid">
		<div class="span3 valign_center maq_left" style="width: 203px;">专利数：${patentCount }</div>
		<div class="span8 valign_center "  style="width:820px; margin-left: -7px;">
			<marquee   id="mymarquee"   onmouseover="mymarquee.stop()"   onmouseout="mymarquee.start()"   scrollAmount="3"   scrollDelay="50"      behavior="scroll"   width="100%"   >   
				<span class="maq_right_content">专利总数：${patentCount }</span><span class="maq_right_content">国内专利：${patentCNCount}</span><span class="maq_right_content">国外专利：${patentCount-patentCNCount}</span><span class="maq_right_content">专利成交总数：${patentTradeCount }</span><span class="maq_right_content">需求总数：${requirementCount }</span>
				<span class="maq_right_content">专家总数：${teacherCount }</span>
			 </marquee>
  		</div>
	</div>
</div>

