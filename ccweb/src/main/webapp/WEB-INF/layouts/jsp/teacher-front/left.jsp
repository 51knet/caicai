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
	<c:if test="${(sessionUserInfo!=null) && (sessionUserInfo.id != teacher_id) }">
	<div class="row-fluid">
	<c:if test="${followValue!=1}">
		<a href='<c:url value='/addrelation?uid=${teacherInfo.id}'></c:url>'  id="attention" class="btn btn-success btn-small">+关注</a> 
	</c:if>
	<!-- <a href='<c:url value='/sendmessage?uid=${teacherInfo.id}'></c:url>' class="btn btn-small">发私信</a>  -->	
		<a href="#myModal" role="button" class="btn btn-small" data-toggle="modal">发私信</a>
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

	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">发送信件</h3>
		</div>
		<form action='<c:url value='/teacher/message/sendMsgInfo'></c:url>' method="post">
			<div class="modal-body">
				<input type="hidden" value="${teacherInfo.id}" name="uid" >
				信件标题：<input type="text" name="title" placeholder="Title"><br>
				信件内容：<textarea name="content" placeholder="Content" cols="5" rows="8"></textarea>
			</div>
			<div class="modal-footer">
				<button class="btn" type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
				<button class="btn btn-primary" type="submit">保存</button>
			</div>
		</form>
		
		
	</div>
