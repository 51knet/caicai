<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
<style>
.nav-stacked  > li{
	border-bottom: 0px solid #6d7b88;
}

.nav-stacked > li > a {
border: 0px solid #DDD;
text-align: left;
padding:8px 30px;
font-size: 14px;
font-family: 'Microsoft YaHei',Arial;
color: #3d4f67;
font-weight: bold;
}

.nav-stacked > li > a:hover {
color: #3d4f67;
text-decoration: none;
/*background-color: #80B029;*/
background-color:#c0d5ea;
font-weight: bold;

}
.nav-stacked > .active > a , .nav-stacked > .active > a:hover {
color: #3d4f67;
cursor: default;
text-decoration: none;
background-color: #c0d5ea;
border: 0px solid #DDD;
border-bottom-color: transparent;
}



</style>

<div  class="cont_block">
	<div class="leftmenu_titles">
 		<img  src="<c:url value='/resources/img/default/alliestitle.png' ></c:url>">
 	</div>
	
	<div style="text-align: center;" class="btl">
		<c:url var="avatar_url" value="${userInfo.avatar}"></c:url>
		<img src="${avatar_url}" style="width: 120px;">
		<h4><a href='<c:url value='/teacher/${userInfo.id}'></c:url>'>${userInfo.name }</a></h4>
	</div>
	<ul class="nav  nav-stacked">
		<li><a href='<c:url value="/allies/${userInfo.id}"></c:url>' >成员信息</a></li>
		<li><a href='<c:url value="/allies/${userInfo.id}/announcement/list"></c:url>' >成员新闻</a></li>
		<li><a href='<c:url value="/front"></c:url>'>返回首页</a></li>

	</ul>
	
	

</div>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search) ||$(this).attr('href') == (window.location.pathname) ){
			$(this).parent().addClass('active');
		}
	});
});
</script>
