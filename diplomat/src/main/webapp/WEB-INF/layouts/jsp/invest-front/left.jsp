<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
.row-fluid.centralize {
	text-align: center;
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #ccdfa8;
	font-family:'Microsoft YaHei',Arial;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
</style>

<div class="row-fluid centralize round" >
	<div class="round header">
		<h5></h5>
	</div>
	<div class="row-fluid">
	<c:url var="avatar_url" value="${investComUserInfo.avatar}"></c:url>
	<img width="199px" height="199px" src="${avatar_url}" style="margin-top: 10px;"><a href='<c:url value='/incubator/${investComUserInfo.id}'></c:url>'>
		<h4>${investComUserInfo.name }</h4></a>
	</div>
</div>
<div class="leftInfo">
	<div class="top" >
		<h4>个人信息</h4>
	</div>
	<div>
		<address  class="content">
			<abbr>名称:</abbr> ${investComUserInfo.name} <br>
		</address>
	</div>
</div>

<div   class="leftInfo">
	<div class="top"  >
		<h4>联系方式</h4>
	</div>
	<div>
		<address  class="content">
			<c:choose>
				<c:when test="${investComUserInfo.address==null||investComUserInfo.address=='' }"></c:when>
				<c:otherwise>
				<abbr title="地址"><i class="icon-home"></i>:</abbr> ${investComUserInfo.address} <br>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${investComUserInfo.fax==null||investComUserInfo.fax=='' }"></c:when>
				<c:otherwise>
				<abbr title="传真"><i class="icon-print"></i>:</abbr> ${investComUserInfo.fax} <br> 
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${investComUserInfo.phone==null||investComUserInfo.phone=='' }"></c:when>
				<c:otherwise>
				<abbr title="电话"><i class="icon-headphones"></i>:</abbr> ${investComUserInfo.phone} <br> 
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${investComUserInfo.email==null||investComUserInfo.email=='' }"></c:when>
				<c:otherwise>
				<abbr title="电邮"><i class="icon-envelope"></i>:</abbr><a href="mailto:#">${investComUserInfo.email}</a>
				</c:otherwise>
			</c:choose>
		</address>
	</div>
</div>
