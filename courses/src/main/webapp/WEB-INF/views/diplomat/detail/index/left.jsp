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
	<c:url var="avatar_url" value="${teacherInfo.avatar}"></c:url>
	<img width="199px" height="199px" src="${avatar_url}" style="margin-top: 10px;"><a href='<c:url value='/diplomat/${teacherInfo.id}'></c:url>'>
		<h4>${teacherInfo.name }</h4></a>
	</div>
</div>
<div class="leftInfo">
	<div class="top" >
		<h4>个人信息</h4>
	</div>
	<div>
		<address  class="content">
			<abbr>姓名:</abbr> ${teacherInfo.name} <br>
				 <c:choose>
					 <c:when test="${teacherInfo.gender==null||teacherInfo.gender==''}"></c:when>
					 <c:otherwise>
					 <abbr>性别:</abbr> ${teacherInfo.gender} <br> 
					 </c:otherwise>
					 </c:choose>
					 <c:choose>
					 <c:when test="${teacherInfo.college==null||teacherInfo.college==''}"></c:when>
					 <c:otherwise>
					<abbr>就职国家:</abbr> ${teacherInfo.college} <br> 
					 </c:otherwise>
					 </c:choose>
					 <c:choose>
					 <c:when test="${teacherInfo.school==null||teacherInfo.school==''}"></c:when>
					 <c:otherwise>
					  <abbr>职务:</abbr> ${teacherInfo.school}
					 </c:otherwise>
				 </c:choose>
		</address>
	</div>
</div>


