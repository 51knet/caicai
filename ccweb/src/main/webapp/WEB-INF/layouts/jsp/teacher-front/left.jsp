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
		<!-- <img src='${teacherInfo.photoUrl}' height='80px' width='60px' align="middle"> -->
		<img width="90px" height="90px" src="http://dummyimage.com/90x90" style="margin: 15px 0px;">
		<a href='<c:url value='/teacher/${teacherInfo.id}'></c:url>'>${teacherInfo.name }</a>
	</div>
	<div class="row-fluid">
		<a href='<c:url value='/teacher/${teacherInfo.id}/fans/list'></c:url>'<c:url value='/teacher/${teacherInfo.id}'></c:url>'' >${fansCount } 粉丝</a> | 
		<a href='<c:url value='/teacher/${teacherInfo.id}/host/list'></c:url>' >${hostCount } 关注</a>
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
		<address>
		  	<abbr>姓名:</abbr> ${teacherInfo.name}
		  	<br>
		  	<abbr>性别:</abbr> ${teacherInfo.gender}
		  	<br>
		  	<abbr>院校:</abbr> ${teacherInfo.college}
		  	<br>
		  	<abbr>院系:</abbr> ${teacherInfo.school}
		</address>
	</div>
</div>

<div class="row-fluid centralize round">
	<div class="row-fluid">
		<h5>联系方式</h5>
	</div>
	<hr/>
	<address style="text-align: left; margin-left: 40px; margin-right: 40px;">
	  	<abbr title="地址"><i class="icon-home"></i>:</abbr> ${teacherInfo.address}
	  	<br>
	  	<abbr title="传真"><i class="icon-print"></i>:</abbr> ${teacherInfo.fax}
	  	<br>
	  	<abbr title="电话"><i class="icon-headphones"></i>:</abbr> ${teacherInfo.phone}
	  	<br>
	  	<abbr title="电邮"><i class="icon-envelope"></i>:</abbr> <a href="mailto:#">${teacherInfo.email}</a>
	</address>
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
