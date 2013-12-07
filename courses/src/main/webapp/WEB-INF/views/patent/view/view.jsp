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

.patent .middle{
	 font-size: 14px; 
	line-height: 25px;
}

.patent .bottom{
	padding:10px 10px;
	width: 85%;
}

.margin_left_40{
	margin-left: 40px;
}
.date{
	margin-left: 40px; font-size: 12px; color: #666;
	font-weight: normal;
}
</style>
 <div class="path_link"><a href="<c:url value='/'></c:url>" >首页 </a> >> <a href="<c:url value='/patent/list'></c:url>" >专利列表</a> >> 专利详情 </div>
<div class="container title"  >
		<div class="innerLeftTitle">专利详情</div>
 </div>
 <div class="container patent">
	<div class="top ">${patent.patentName }
		 <span style="float: right; margin-right: 60px;">	
			<div class=" dropdown" style="font-size: 15px;">
				<a href="#" style="text-decoration: none;" class="dropdown-toggle"  data-toggle="dropdown">点击咨询</a>
				<div class="dropdown-menu" style="text-align: left;  width: 200px; height: 80px; padding:10px 15px; line-height: 30px;" role="menu" aria-labelledby="dropdownMenu">
						联系电话：400-8567-4582<br>
						QQ在线：<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=826619119&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:826619119:41" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>
				</div>
			</div>
		</span>
	</div>
	<div class="bottom   tLine_dash">	
	<br>
		<table width="95%" height="" border="0" cellpadding="5"  class="blue">
			<tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">专利号码</td>
			    <td width="190">${patent.patentNum}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">申请日期</td>
			    <td width="169">${patent.applicationDate}</td>
			  </tr>
			  <tr>
			    <td width="122" align="center" bgcolor="#f3f3f3">适用领域</td>
			    <td width="190">${patent.patentField}</td>
			    <td width="103" align="center" bgcolor="#f3f3f3">专利类型</td>
			    <td width="169">${patent.patentType.typeName}</td>
			  </tr>
		
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">公开号码</td>
			    <td>${patent.publishNum}</td>
			    <td align="center" bgcolor="#f3f3f3">公开日期</td>
			    <td>${patent.publishDate}</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">主分类号</td>
			    <td colspan="3">${patent.mainClassNum}</td>
			  </tr>
			  <tr>
			    <td align="center" bgcolor="#f3f3f3">分类号码</td>
			    <td colspan="3">${patent.classNum }</td>
			  </tr>
			</table>
			<br>
	</div>
	<div class="top ">专利摘要</div>
	<div class="bottom   tLine_dash">	
		${patent.summary }
	</div>
	
	<div class="top ">评论</div>
	<div class="bottom  tLine_dash">	
		尚未有人发表评论
	</div>
 </div>



