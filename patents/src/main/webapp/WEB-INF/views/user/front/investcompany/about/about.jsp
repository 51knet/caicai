<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 20px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .content {
	margin: 20px 40px;
	width:675px;
	max-width:675px;
	word-wrap:break-word;	
}

.row-fluid.custom .content .bb{
	border-bottom: dashed #cccccc 1px;
}
.row-fluid.custom .content .limitTable{
	margin-top: 10px; 
	width:675px; 
	table-layout:fixed
}

.row-fluid.custom .content .limittable .limitTd{
	word-wrap:break-word; word-break:break-all;
}

</style>
<div class="row-fluid custom round">
	<div class="content ">
		<table width="100%">
			<tr>
				<td align="left" width="25%">
					<c:url var="avatar_url" value="${investComUserInfo.avatar}"></c:url>
					<img width="150px" height="150px" src="${avatar_url}" style="margin-top: 10px;">
				</td>
				<td width="3%"></td>
				<td align="left">
					<h3>${investComUserInfo.name}</h3>	
				</td>
			</tr>
		</table>
	</div>
	<div class="row ">
		<h4>联系方式</h4>
	</div>
	<div class="content ">
		<address>
			<c:choose>
			<c:when test="${investComUserInfo.address == null||investComUserInfo.address == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="地址">联系地址：</abbr> ${investComUserInfo.address} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${investComUserInfo.fax == null||investComUserInfo.fax == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="传真">传真：</abbr> ${investComUserInfo.fax}
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${investComUserInfo.phone == null||investComUserInfo.phone == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电话">电话：</abbr> ${investComUserInfo.phone} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${investComUserInfo.email == null||investComUserInfo.email == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电邮">电子邮件：</abbr> <a href="mailto:#">${investComUserInfo.email}</a>
			</c:otherwise>
			</c:choose>
		</address>
	</div>
	
	<div class="row">
		<h4>政策支持</h4>
	</div>
	<div class="content">
		<i class=" icon-th-large"></i> 投资额度：${investComInfo.investRange}<br>
		<i class=" icon-th-large"></i> 投资领域：${investComInfo.investField }<br>
	</div>
	
	<div class="row ">
		<h4>机构介绍</h4>
	</div>
	<div class="content">
		${investComInfo.companyinfo}
	</div>
	
</div>