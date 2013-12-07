<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.centralize {
	text-align: center;
	margin-bottom: 20px;
	padding: 0px 0px 10px;
 	/*background-image:url('<c:url value='/resources/img/default/admin_left_bg.png'></c:url>');*/
 	background-color:#ccdfa8;
 	background-position:top center;
 	background-repeat:repeat-y;
	vertical-align: middle;
}
</style>
<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url>
<c:choose>
	<c:when test='${sessionUserInfo.avatar == "/resources/img/avatar/avatar90.png"}'>
		<div class="row-fluid centralize">
		    <div style="background-image:url(${avatar_url}); background-repeat:no-repeat;background-position:center;height:120px;width:120px;margin:15px auto;">
		   </div>
		</div>	
	</c:when>
	<c:when test='${sessionUserInfo.avatar == "/resources/img/avatar/avatar91.png"}'>
		<div class="row-fluid centralize">
		    <div style="background-image:url(${avatar_url}); background-repeat:no-repeat;background-position:center;height:120px;width:120px;margin:15px auto;">
		    <div style="height: 35px;"></div>
		   </div>
		</div>	
	</c:when>
	<c:otherwise>
		<div class="row-fluid centralize">
			<img width="150px" height="150px" src="${avatar_url}" style="margin: 15px 0px;" >
			<a href='<c:url value='/teacher/${sessionUserInfo.id}'></c:url>'><h4>${sessionUserInfo.name }</h4></a>
		</div>		
	</c:otherwise>
</c:choose>
	<div class="left-menu-container">
		<ul class="nav nav-tabs nav-stacked">
			<li><a href='<c:url value="/admin/caicai/authentication"></c:url>' >企业申请</a></li>
			<li><a href='<c:url value="/admin/caicai/detail/user "></c:url>' >用户管理</a></li>
			<li><a href='<c:url value="/admin/caicai/announcement/list"></c:url>' >公告管理</a></li>
			<li><a href='<c:url value="/admin/caicai/course/list"></c:url>' >课程管理</a></li>
			<li><a href='<c:url value="/admin/caicai/resource/list"></c:url>' >资源管理</a></li>
			<li><a href='<c:url value="/admin/caicai/blog/list"></c:url>' >博文管理</a></li>
			<li><a href='<c:url value="/admin/caicai/activity/list"></c:url>' >活动管理</a></li>
			<li><a href='<c:url value="/admin/caicai/recharge/list"></c:url>' >充值卡管理</a></li>
			<li><a href='<c:url value="/admin/caicai/withdraws/list"></c:url>' >提现申请</a></li>
		</ul>
	</div>
<html>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search) ||$(this).attr('href') == (window.location.pathname) ){
			$(this).parent().addClass('active');
		}
	});
});
</script>
</html>