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
	 width: 1024px;
	 margin: 10px 60px;
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
	<div class="top ">${requirement.title }</div>
	<div class="bottom  bLine_dash tLine_dash row-fluid">	
		<div class="span5" >
			<span class="middle">发布时间：<fmt:formatDate value="${requirement.date}" pattern="yyyy-MM-dd "/></span> <br>
			<span class="middle">截止日期：${requirement.endTime }</span><br>
			<span class="middle">预投金额：${requirement.money }</span>
		</div>
		<div class="span5">
			<span class="middle">联系人士：${requirement.name}</span> <br>
			<span class="middle">公司名称：--- 保 密 ---</span><br>
			<span class="middle">联系电话：--- 保 密 ---</span>
		</div>
	</div>
	<div class="top ">详情</div>
	<div class="bottom  bLine_dash tLine_dash">	
		${requirement.content }
	</div>
 </div>



