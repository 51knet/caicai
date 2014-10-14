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
<div class="row-fluid centralize">
	<img width="150px" height="150px" src="${avatar_url}" style="margin: 15px 0px;" >
	<a href='#'><h4>${sessionUserInfo.name }</h4></a>
</div>		
<div class="left-menu-container">
	<ul class="nav nav-tabs nav-stacked">
		<li><a href='<c:url value="/admin/kefu/patent/list/all"></c:url>' >专利列表</a></li>
		<li><a href='<c:url value="/admin/kefu/technology/list/all"></c:url>' >技术列表</a></li>
		<li><a href='<c:url value="/admin/kefu/patentrequirement/list/all"></c:url>' >专利需求</a></li> 
		<li><a href='<c:url value="/admin/kefu/requirement/list/all"></c:url>' >技术需求</a></li>
		<li><a href='<c:url value="/admin/kefu/activity/list"></c:url>' >活动列表</a></li>
		<li><a href='<c:url value="/admin/kefu/projects/list/all"></c:url>' >融资项目</a></li>
		<li><a href='<c:url value="/admin/kefu/rzfh/list/all"></c:url>' >融资/孵化区</a></li>
		<li><a href='<c:url value="/admin/kefu/applyright/all/all"></c:url>' >个人权限审核</a></li>
		<li><a href='<c:url value="/admin/kefu/coapplyright/all/all"></c:url>' >机构权限审核</a></li>
		<li><a href='<c:url value="/admin/kefu/order/list"></c:url>' >订单审核</a></li>
	</ul>
</div>

<html>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search) ||$(this).attr('href') == (window.location.pathname) ) {
			$(this).parent().addClass('active');
		}
	});
});
</script>
</html>