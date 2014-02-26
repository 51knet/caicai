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
	width: 51px;
}
.projects_left .middle>table .td_cont{
	width: 80px;
}

.projects_left .bottom{
	padding:10px 10px;
}

 ._logo{
	width: 180px; ;
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
.tabbable .tab-content .tab-pane .title{
	padding:0px 20px 0px 20px;
}
.tabbable .tab-content .tab-pane .content{
	padding:0px 20px 0px 20px;
	text-align: left;
	margin:10px 10px;
}
.tabbable .tab-content .tab-pane .title >h4{
	color: #6597c8;
	padding: 0px 0px 4px 10px;
	text-align: left;
	font-size: 15px;
	letter-spacing: 0px;
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
					<td rowspan="4"  style="width: 200px;" align="right"><img src="<c:url value='${p_url}${projects.logoPath }'></c:url> " class="_logo"></td>
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
	<div class="bottom_line_dash"></div>
	<div class="row" style="margin: 20px 0;">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#biz_model_tab" data-toggle="tab">商业模式</a></li>
				<li><a href="#team_tab" data-toggle="tab">创业团队</a></li>
				<li><a href="#plan_tab" data-toggle="tab">未来计划</a></li>
				<li><a href="#file_tab" data-toggle="tab">项目附件</a></li>
				<li><a href="#news_tab" data-toggle="tab">项目动态</a></li>
				<li><a href="#comment_tab" data-toggle="tab">项目评论</a></li>
			</ul>
			<div class="tab-content" >
				<div class="tab-pane active " id="biz_model_tab">
					<div class="title">
						<h4>目标用户或客户群体定位</h4>
					</div>
					<div class="content">暂无内容</div>
					<div class="title">
						<h4>目标用户或客户群体目前困扰或需求定位</h4>
					</div>
					<div class="content">暂无内容</div>
					<div class="title">
						<h4>满足目标用户或客户需求的产品或服务模式说明</h4>
					</div>
					<div class="content">暂无内容</div>
					<div class="title">
						<h4>项目赢利模式说明</h4>
					</div>
					<div class="content">暂无内容</div>
					<div class="title">
						<h4>市场主要同行或竞争对手概述</h4>
					</div>
					<div class="content">暂无内容</div>
					<div class="title">
						<h4>项目主要核心竞争力说明</h4>
					</div>
					<div class="content">暂无内容</div>
				</div>
				<div class="tab-pane " id="team_tab">
					<div class="title">
						<h4>股东团队说明</h4>
					</div>
					<div class="content">暂无内容</div>
					<div class="title">
						<h4>非股东管理团队</h4>
					</div>
					<div class="content">暂无内容</div>
				</div>
				<div class="tab-pane " id="plan_tab">
					<div class="content">您需要登录才可以查看项目详细信息！</div>
				</div>
				<div class="tab-pane " id="file_tab">
					<div class="content">您需要登录才可以查看项目详细信息！</div>
				</div>
				<div class="tab-pane " id="news_tab">
					<div class="content">您需要登录才可以查看项目详细信息！</div>
				</div>
				<div class="tab-pane" id="comment_tab">
					<form action="#" method="post" style="width : 720px;">
						<div id="commentDesc" style="margin-top: 15px;">
							<textarea name="commentDesc" placeholder="请输入评论内容" id="c" cols="5" rows="8" style="width: 720px; height: 160px;"></textarea>
							<span class="help-inline"></span>
						</div>
						<div class="pull-right" style="padding-bottom: 20px;">
							<button type="submit" class="btn btn-primary">发表评论</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
 </div>




