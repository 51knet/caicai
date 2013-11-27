<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course {
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}
.container.course.detail {
	width: 100%;
}
.container.course.detail.desc{
	margin-left:70px;
	padding: 0px;20px;
	width:83.5%;
}
.container.course.title{
	height: 240px;
	width:1024px;
	background-image: url('<c:url value='/resources/img/default/courseInfo.png'></c:url>');
	background-repeat: repeat-x;
	margin-top: 41px;
}
.container.course.content{
	width: 37%; 
	text-align:left; 
	float: left;
	margin-top: 32px;
	margin-left: 70px;
}
.nar{
	font-size:16px;
	color:#adcc75;
	height: 40px;
}
.nar >h4{
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 10px;
	padding-left:88px;
}
</style>

	
		<div class="container course title" >
			<div class="container course content">
					<span style="font-size: 25px;color:#80b029;" id="content">专利名称：${patent.patentName}</span><br/>
					<span style="font-size: 18px;color:#80b029;">发布日期：${patent.publishDate}</span><br/>
    				<span style="font-size: 14px;color: black;">发明人：${patent.inventer} 人</span>
				<span style="font-size: 14px;color: black;">售价： 0  元</span><br/>
			</div>
		</div>
		<div class="container course detail desc">
			<div class="row" style="margin-top: 30px;">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#patent_info_tab" data-toggle="tab">专利详情</a></li>
						<li><a href="#purchase_info_tab" data-toggle="tab">购买记录</a></li>
						<li><a href="#comment_tab" data-toggle="tab">评价详情</a></li>
					</ul>
					<div class="tab-content" >
						<div class="tab-pane active " id="patent_info_tab">
							<!-- <div class="title">
								<h4>专利详情</h4>
							</div> -->
							<div class="content">
								<table width="100%" height="" border="1" cellpadding="5" cellspacing="0"  class="blue">
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
									    <td align="center" bgcolor="#f3f3f3">专利名称</td>
									    <td colspan="3">${patent.patentName }</td>
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
									  <tr>
									    <td align="center" bgcolor="#f3f3f3">申请人</td>
									    <td>${patent.applicant }</td>
									    <td align="center" bgcolor="#f3f3f3">发明人</td>
									    <td>${patent.inventer }</td>
									  </tr>
									  <tr>
									    <td align="center" bgcolor="#f3f3f3">联系地址</td>
									    <td colspan="3">${patent.address }</td>
									  </tr>
									  <tr>
									    <td align="center" bgcolor="#f3f3f3">代理人</td>
									    <td>${patent.agency }</td>
									    <td align="center" bgcolor="#f3f3f3">代理机构</td>
									    <td>${patent.agent }</td>
									  </tr>
								  	   <tr>
									    <td colspan="4" align="left" bgcolor="#f3f3f3">专利摘要</td>
									  </tr>
									  <tr>
									  	   <td colspan="4" align="left" >${patent.summary }</td>
									  </tr>
									</table>
							</div>
						</div>
						<div class="tab-pane " id="purchase_info_tab">
							<div class="content">尚未有人购买</div>
						</div>
						<div class="tab-pane " id="comment_tab">
							<div class="content">
										暂无内容
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		

