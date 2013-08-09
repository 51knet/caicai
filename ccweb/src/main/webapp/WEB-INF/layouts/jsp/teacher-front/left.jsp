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
<script type="text/javascript">
	$(document).ready(function() {
		var contentEditor = KindEditor.create('textarea[name="content"]',{
			cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
			uploadJson : '${uploadJson}',
			fileManagerJson : '${fileManagerJson}',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				KindEditor.ctrl(document, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
				KindEditor.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
			}
		});
		$("#sendMsg_info_form").submit(function(){
			contentEditor.sync();
			return checkEmptyAjax("sendMsg_info_form","sendMsgInfoAJAX");
		});
		prettyPrint();
		$("#t").focus(function() {
			$(".help-inline").html("");
		});
		$("#c").focus(function() {
			$(".help-inline").html("");
		});
	});
	</script>
<div class="row-fluid centralize round" >
	<div class="round header">
		<h5></h5>
	</div>
	<div class="row-fluid">
	<c:url var="avatar_url" value="${teacherInfo.avatar}"></c:url>
	<img width="199px" height="199px" src="${avatar_url}" style="margin-top: 10px;"><a href='<c:url value='/teacher/${teacherInfo.id}'></c:url>'>
		<h4>${teacherInfo.name }</h4></a>
	</div>
	<div class="row-fluid">
		<a href='<c:url value='/teacher/${teacherInfo.id}/fans/list'></c:url>'<c:url value='/teacher/${teacherInfo.id}'></c:url>'' >${sessionScope.fansCount } 粉丝</a> | 
		<a href='<c:url value='/teacher/${teacherInfo.id}/host/list'></c:url>'>${sessionScope.hostCount } 关注</a>
	</div>
	<c:if test="${(sessionUserInfo!=null) && (sessionUserInfo.id != teacher_id) }">
		<div class="row-fluid">
			<c:if test="${! sessionScope.isFollower}">
				<a href='<c:url value='/addrelation?uid=${teacherInfo.id}'></c:url>' id="attention" class="btn btn-success btn-small">+关注</a>
			</c:if>
			<c:if test="${sessionScope.isFollower}">
				<a href='<c:url value='/delerelation?uid=${teacherInfo.id}'></c:url>' id="attention" class="btn  btn-small">取消关注</a>
			</c:if>
			<!-- <a href='<c:url value='/sendmessage?uid=${teacherInfo.id}'></c:url>' class="btn btn-small">发私信</a>  -->
			<a href="#myModal" role="button" class="btn btn-small" data-toggle="modal">发私信</a>
		</div>
	</c:if>
</div>
<div class="leftInfo">
	<div class="top" >
		<h4>个人信息</h4>
	</div>
	<div>
		<address  class="content">
			<abbr>姓名:</abbr> ${teacherInfo.name} <br>
			<c:if test="${teacherInfo.teacher.isEnterprise == null}">
				 <c:choose>
					 <c:when test="${teacherInfo.gender==null||teacherInfo.gender==''}"></c:when>
					 <c:otherwise>
					 <abbr>性别:</abbr> ${teacherInfo.gender} <br> 
					 </c:otherwise>
					 </c:choose>
					 <c:choose>
					 <c:when test="${teacherInfo.college==null||teacherInfo.college==''}"></c:when>
					 <c:otherwise>
					<abbr>院校:</abbr> ${teacherInfo.college} <br> 
					 </c:otherwise>
					 </c:choose>
					 <c:choose>
					 <c:when test="${teacherInfo.school==null||teacherInfo.school==''}"></c:when>
					 <c:otherwise>
					  <abbr>院系:</abbr> ${teacherInfo.school}
					 </c:otherwise>
				 </c:choose>
			</c:if>
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
				<c:when test="${teacherInfo.address==null||teacherInfo.address=='' }"></c:when>
				<c:otherwise>
				<abbr title="地址"><i class="icon-home"></i>:</abbr> ${teacherInfo.address} <br>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${teacherInfo.fax==null||teacherInfo.fax=='' }"></c:when>
				<c:otherwise>
				<abbr title="传真"><i class="icon-print"></i>:</abbr> ${teacherInfo.fax} <br> 
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${teacherInfo.phone==null||teacherInfo.phone=='' }"></c:when>
				<c:otherwise>
				<abbr title="电话"><i class="icon-headphones"></i>:</abbr> ${teacherInfo.phone} <br> 
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${teacherInfo.email==null||teacherInfo.email=='' }"></c:when>
				<c:otherwise>
				<abbr title="电邮"><i class="icon-envelope"></i>:</abbr><a href="mailto:#">${teacherInfo.email}</a>
				</c:otherwise>
			</c:choose>
		</address>
	</div>
</div>



<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">发送信件</h3>
	</div>
	<form action='<c:url value='/message/sendMsgInfo'></c:url>' method="post" id="sendMsg_info_form">
		<div class="modal-body" >
			<input type="hidden" value="${teacherInfo.id}" name="uid"> 
		</div>
		<div class="control-group" id="title" style="padding-left: 20px;">
			<div class="controls">
			信件标题：
				<input type="text" name="title" id="t" placeholder="信件标题"> 
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" id="content" style="padding-left: 20px;">
			<div class="controls" >
			信件内容：
				<textarea  id="KEContent" name="content" placeholder="信件内容" id="c" cols="5" rows="8" style="width:490px;"></textarea>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" style="float: right; margin-right: 20px;">
			<div class="controls">
				<button type="submit"  class="btn  btn-success">发送</button>
				<button type="reset" data-dismiss="modal" class="btn ">取消</button>
			</div>
		</div>
	</form>
</div>
