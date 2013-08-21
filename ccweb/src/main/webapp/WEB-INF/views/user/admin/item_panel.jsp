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
 	/*background-color:#ccdfa8;*/
 	background-color:#fff;
 	background-position:top center;
 	background-repeat:repeat-y;
	vertical-align: middle;
}
.left-menu-container {
background-color: #fff;

}
.nav-tabs.nav-stacked > li > a {
text-align: center;
font-size: medium;
font-family: 'Microsoft YaHei',Arial;
color: #df9a1b;
}
.nav-tabs > li > a:hover {
color: #8aa942;
text-decoration: none;
background-color: #c7daa3;
}
.nav-tabs > .active > a , .nav-tabs > .active > a:hover {
text-decoration: none;
background-color: #c7daa3;
color: #8aa942;

}
	.border{
			background-color: #fff; border: 1.5px solid #eed593;
		}
</style>
<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url>
<div class="row-fluid centralize border">
	<c:choose>
		<c:when test='${sessionUserInfo.avatar == "/resources/img/avatar/avatar90.png" || sessionUserInfo.avatar == "/resources/img/avatar/avatar91.png" }'>
			    <div style="background-image:url(${avatar_url}); background-repeat:no-repeat;background-position:center;height:120px;width:120px;margin:15px auto;">
			    <div style="height: 35px;"></div>
			    <div style="height: 20px;background-color:gray;  padding:2px 2px;">
			    	<a href='<c:url value="/admin/details"><c:param name="active" value="avatar" /></c:url>' >上传头像</a>
			    </div>
		   </div>
		</c:when>
		<c:otherwise>
				<img src="${avatar_url}" style="margin: 15px 0px; width: 130px; " >
				<a href='<c:url value='/id/${sessionUserInfo.id}'></c:url>'><h4>${sessionUserInfo.name }</h4></a>	
		</c:otherwise>
	</c:choose>
	<div class="row-fluid">
		<a href='<c:url value='/admin/fans/list'></c:url>' >${sessionScope.admin_fansCount }同学</a> | 
		<a href='<c:url value='/admin/fans/list'></c:url>' >${sessionScope.admin_fansCount }粉丝</a> | 
		<a href='<c:url value='/admin/host/list'></c:url>'>${sessionScope.admin_hostCount }关注</a>
	</div>
</div>
<div class="left-menu-container " >
	<ul class="nav nav-tabs nav-stacked">
		<li><a href='<c:url value="/admin/trend?role="></c:url>' >所有动态</a></li>
		<li><a href='<c:url value="/admin/trend?role=teacher"></c:url>' >教师动态</a></li>
		<li><a href='<c:url value="/admin/trend?role=user"></c:url>' >学生动态</a></li>
		<li><a href='<c:url value="/admin/message/list"></c:url>' >站内信</a></li> 
	</ul>
</div>
<hr>
<div class="left-menu-container" >
	<ul class="nav nav-tabs nav-stacked">
		<li><a href='<c:url value="/admin/mycourse/list"></c:url>' >我的学习</a></li>
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