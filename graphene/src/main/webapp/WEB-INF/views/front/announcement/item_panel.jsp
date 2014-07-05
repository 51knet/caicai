<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<div class="cont_block">
 	<div class="leftmenu_titles">
 		<img  src="<c:url value='/resources/img/default/sectitle1.png' ></c:url>">
 	</div>
	<ul class="nav  nav-stacked">
		<li><a href='<c:url value="/front/announcement/admin"></c:url>'>新闻资讯</a></li>
		<li><a href='<c:url value="/front/activity/list"></c:url>'>平台动态</a></li>
		
		
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
