<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<style>
.row-fluid.centralize {
	text-align: center;
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
</style>
<div class="row-fluid centralize round">
	<div class="round header"><h5> </h5></div>
	<div class="row-fluid">
		<img src='${teacherInfo.photoUrl}' height='80px' width='60px' align="middle">
		<a href='<c:url value='/teacher/${teacherInfo.id}'></c:url>'>${teacherInfo.name }</a>
	</div>
	<div class="row-fluid">
		<a href='#' >2 粉丝</a> | 
		<a href='#' >2 关注</a>
	</div>
	<c:if test="${(userInfo!=null) && (userInfo.id != teacher_id) }">
	<div class="row-fluid">
		<a href='<c:url value='/addrelation?uid=${teacherInfo.id}'></c:url>' class="btn btn-success btn-small">+关注</a> 
		<a href='<c:url value='/sendmessage?uid=${teacherInfo.id}'></c:url>' class="btn btn-small">发私信</a>
	</div>
	</c:if>
</div>

<div class="row-fluid centralize round">
	<div class="row-fluid">
		<div class="round header"><h5>个人信息</h5></div>
		<hr/>
		...
	</div>
</div>

<div class="row-fluid centralize round">
	<div class="row-fluid">
		<h5>联系方式</h5>
	</div>
	<hr/>
	${teacherInfo.announcementContext}
</div>
