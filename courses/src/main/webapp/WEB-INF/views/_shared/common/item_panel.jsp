<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.left-menu-container {
background-color: #3c4f65;
width: 230px;


background-image: url("<c:url value='/resources/img/default/item_bg.png'></c:url>");
background-position: top right;
background-repeat: repeat-y;
margin-bottom: 10px;
}

.nav-stacked {
	width: 225px;
}
.nav-stacked  > li{
	border-bottom: 0px solid #6d7b88;
	width: 223px;
	background-image: url("<c:url value='/resources/img/default/item_second_bLing.png'></c:url>");
	background-position: bottom left;
	background-repeat: repeat-x;
}
.nav-stacked.nav-stacked > li > a {
border: 0px solid #DDD;

text-align: left;
padding:12px 20px;
font-size: 15px;
color: #FFF;
font-weight: bold;
}

.nav-stacked.nav-stacked > li >a>span{
	padding-left: 10px;
	font-size: 13px;
	font-weight: normal;
}
.nav-stacked  > li > a:hover {

color: #3d4f67;
text-decoration: none;
/*background-color: #80B029;*/
background-color:#a1a6ac;
}
.nav-stacked > .active > a, .nav-stacked > .active > a:hover {
color: #3d4f67;
cursor: default;
text-decoration: none;
/*background-color: #80B029;*/
background-color:#a1a6ac;
border: 0px solid #DDD;
border-bottom-color: transparent;
}
</style>
<div class="left-menu-container">
	<ul  class="nav  nav-stacked" >
		<li><a href='<c:url value="/"></c:url>'>首页</a></li>
		<li><a href='<c:url value="/projects/list"></c:url>'>融资项目</a></li>
		<li><a href='<c:url value="/patent/list"></c:url>'>专利列表</a></li>
		<li><a href='<c:url value="/patent/list/china"></c:url>' ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 国内专利</span></a></li>
		<li><a href='<c:url value="/patent/list/foreign"></c:url>'  ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 国外专利</span></a></li>
		<li><a href='<c:url value="/technology/list"></c:url>'>技术成果</a></li>
		<li><a href='<c:url value="/activity/list"></c:url>'>活动列表</a></li>
		<li><a href='<c:url value="#"></c:url>'>需求列表</a></li>
		<li><a href='<c:url value="/requirement/patent/list"></c:url>' ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 专利需求</span></a></li>
		<li><a href='<c:url value="/requirement/technology/list"></c:url>'  ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 技术需求</span></a></li>
		<li><a href='<c:url value="/teacher/list"></c:url>'>专家列表</a></li>
		<li><a href='<c:url value="/search/patent/detail"></c:url>'>高级搜索</a></li>
		<li><a href='<c:url value="/fastupload"></c:url>'>快速上传</a></li>
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search) ||$(this).attr('href') == (window.location.pathname) ) {
			$(this).parent().addClass('active');
		}
	});
});
</script>