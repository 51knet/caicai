<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
.row-fluid.centralize {
	text-align: center;
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #fff5e0;
	font-family:'Microsoft YaHei',Arial;
	border: 1px solid #f77605;
	color: #f77605;
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
<div class="row-fluid centralize " >
	<div class="row-fluid" style="	color: #f2e690;background-color: #f77605;">
		<h4>课程搜索</h4>
	</div>
	<div class="row-fluid" >
		<form class="" style="padding: 15px 0px 15px 0px;" action="<c:url value='/enterprise/searchCourse'></c:url>" method="post"> 
			<input type="hidden" name="userid" value="${user_id }">
			<input type="text" name="courseName" value="${courseName }" style="width: 145px;" placeholder="输入课程名"><br>
			价格：<input type="text" style="width: 35px;" name="minPrice" value="${minPrice }" > 至 <input type="text" name="maxPrice" value="${maxPrice }" style="width: 35px;" >
			<button type="submit" class="btn pull-right " style="margin-right: 20px;">搜索</button>
		</form>
	</div>
</div>
<div class="row-fluid centralize " >
	<div class="row-fluid">
		<c:url var="avatar_url" value="${userInfo.avatar}"></c:url>
		<img src="${avatar_url}" style="margin: 10px 5px; width: 140px;height: 140px;">
		<br><b>${userInfo.name}</b>
	</div>
	<!--
	<div class="row-fluid">
		<a href='<c:url value='/teacher/${userInfo.id}/fans/list'></c:url>'<c:url value='/teacher/${userInfo.id}'></c:url>'' >${sessionScope.fansCount } 粉丝</a> | <a
			href='<c:url value='/teacher/${userInfo.id}/host/list'></c:url>'>${sessionScope.hostCount } 关注</a>
	</div>-->
	<c:if test="${(sessionUserInfo!=null) && (sessionUserInfo.id !=user_id) }">
		<div class="row-fluid">
			<c:if test="${! sessionScope.isFollower}">
				<a href='<c:url value='/addrelation?uid=${userInfo.id}'></c:url>' id="attention" class="btn btn-success btn-small">+关注</a>
			</c:if>
			<!-- <a href='<c:url value='/sendmessage?uid=${userInfo.id}'></c:url>' class="btn btn-small">发私信</a>  -->
			<a href="#myModal" role="button" class="btn btn-small" data-toggle="modal">发私信</a>
		</div>
	</c:if>
</div>
<div class="eleftInfo">
	<div class="top" >
		<h4>学校信息</h4>
	</div>
	<div class="content">
		课程数量：${courseCount}<br>
		信誉度：暂未信誉评价
	</div>
</div>

<div   class="eleftInfo">
	<div class="top"  >
		<h4>课程排行</h4>
	</div>
	<div  class="content">
			<!--<c:forEach items="${cTypeList }" var="cType" begin="0" end="6">
				<a href='<c:url value='/enterprise/${teacher_id}/course/type/${cType.id}'></c:url>'><span style="color:#80b029;">${cType.typeName}</span></a><br>
			</c:forEach>-->
			<c:choose>
				<c:when test="${courseCount !=0}">
					<c:forEach items="${courseList}" var="course" begin="0" end="5">
				    	<div  id="contentlimit" style="width: 140px;">
				    		<a style="color: #80b029;" href='<c:url value='/enterprise/${userInfo.id}/course/view/${course.id }'></c:url>'>${course.courseName }</a>
				    	</div>
				    </c:forEach>
				</c:when>
				<c:otherwise>
					无内容
				</c:otherwise>
			</c:choose>
	</div>
</div>



<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">发送信件</h3>
	</div>
	<form action='<c:url value='/teacher/message/sendMsgInfo'></c:url>' method="post" id="sendMsg_info_form">
		<div class="modal-body" >
			<input type="hidden" value="${userInfo.id}" name="uid"> 
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
