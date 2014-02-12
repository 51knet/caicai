<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
.titlebg{
	background-color:#ccdfa8; 
	font-size: 14px;
	width: 100%;
}
.patent{
	 width: 100%;
	 margin: 10px 30px;
}
.patent .top{
	font-size: 17px;
	color: #3f5517;
	padding:10px 0px 8px 10px;
	width: 85%;
}

.middle{
	 font-size: 14px; 
	line-height: 25px;
}

.patent .bottom{
	padding:10px 10px;
	width: 85%;
}
.date{
	margin-left: 40px; font-size: 12px; color: #666;
	font-weight: normal;
}
</style>

<div class="container title"  >
		<div class="innerLeftTitle">需求详情</div>
 </div>
 <div class="container patent">
	<div class="top ">${patentRequirement.title }</div>
	<div class="bottom  bLine_dash tLine_dash row-fluid">	
		<div class="span5" >
			<span class="middle">发布时间：<fmt:formatDate value="${patentRequirement.date}" pattern="yyyy-MM-dd "/></span> <br>
			<span class="middle">合作方式：${patentRequirement.cooperation}</span><br>
			<span class="middle">预投金额：${patentRequirement.money }</span>
			
			
		</div>
		<div class="span5">
		<span class="middle">需求领域：${patentRequirement.requirementField }</span><br>
			<span class="middle">公司名称：--- 保 密 ---</span><br>
			<span class="middle" >联系方式：</span>
				
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=826619119&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:826619119:41" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>
		
		</div>
	</div>
	<div class="top ">详情</div>
	<div class="bottom  bLine_dash tLine_dash">	
		${patentRequirement.content }
	</div>
 </div>



