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
.margin_right{
	margin-right: 20px;
}
.projects_left{
	 width: 100%;
	 margin:10px 0px;
	text-align: left;
}
.projects_left .top{
	text-align: center;
	padding: 20px 0px  10px 0px;
	line-height: 28px;
}

.projects_left .top .pName{
	color: #3d4f65;
	font-size: 18px;
}
.projects_left .middle{
	text-align: center;
	padding: 20px 0px;
}
.projects_left .middle>table{
width: 100%; height:150px; 
background-color: #e4e7ec; text-align: left;
font-weight: bold;
}
.projects_left .middle>table .td_titles{
	height:35px;
	width: 60px;
}
.projects_left .middle>table .td_cont{
	width: 100px;
}

.projects_left .bottom{
	padding:10px 10px;
}

 .logo{
	width: 200px; height: 139px;
}
.bottom_line_dash{
	height: 5px;
	border-bottom:  1px dashed #ccc;
	width: 100%;
}

.link_{
	color: #7a8d9e;
	font-weight: bold;
}
.date{
	font-size: 12px;
	font-weight: normal;
	color: #999;
}
.myblock{
	padding: 5px 8px; background-color: #6597c8; color: #fff; font-size: 14px; font-weight: bold;
}
.points{
	font-size: 14px;
	color: #6597c8;
	font-weight: bold;
}
</style>
	
 <div class="container projects_left">
 	<span class="link_">所在位置：<a class="link_" href="<c:url value='/'> </c:url>">首页 </a> >><a class="link_" href="<c:url value='/projects/list'> </c:url>"> 项目列表 </a> >>  <a class="link_" href="#">项目详情</a></span>
 	<div class="bottom_line_dash"></div>
	<div class="top">
		<span class="pName margin_right" >${projects.projectName }</span>
		<span class="myblock"><fmt:formatNumber type="number" value="${projects.currentMoney/projects.totalMoney*100}" maxFractionDigits="0"/>%</span><br>
		<span class="date">发布时间：<fmt:formatDate value="${projects.date}" pattern="yyyy-MM-dd HH:mm"/></span>
	</div>
	<div class="bottom_line_dash"></div>
	<div class="middle">
		<table   cellpadding="5" border="0"  align="center">
				<tr>
					<td rowspan="4"  style="width: 220px;" align="center"><img src="<c:url value='${p_url}${page.logoPath }'></c:url> " class="_logo"></td>
					<td  class="td_titles points" >所属行业：</td>
					<td  class="td_cont">${projects.industry }</td>
					<td  class="td_titles points" >员工人数：</td>
					<td     class="td_cont">${projects.empNumber }</td>
				</tr>
				<tr>
					<td  class="td_titles points" >融资金额：</td>
					<td  >${projects.totalMoney} 万</td>
					<td  class="td_titles points" >已融金额：</td>
					<td  >${projects.currentMoney} 万</td>
				</tr>
				<tr>
					<td  class="td_titles points" >项目进度：</td>
					<td   >${projects.progress }</td>
					<td   class="td_titles points">所在城市：</td>
					<td   >${projects.location }</td>
				</tr>
		</table>
	</div>
	<span class="points" >企业项目简介</span>
	<div class="bottom_line_dash"></div>
	<div class="bottom">
		${projects.content }
	</div>
 </div>




