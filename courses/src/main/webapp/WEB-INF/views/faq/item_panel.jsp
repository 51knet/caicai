<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.nav-tabs.nav-stacked > li > a {
border: 1px solid #DDD;
-webkit-border-radius: 0;
-moz-border-radius: 0;
border-radius: 0;
text-align: center;
font-size: medium;
font-family: 'Microsoft YaHei',Arial;
padding: 16px 0px;
}
.nav-tabs > li > a:hover {
color: #FFF;
text-decoration: none;
background-color: #80B029;
}
.nav-tabs > .active > a, .nav-tabs > .active > a:hover {
color: #FFF;
cursor: default;
text-decoration: none;
background-color: #80B029;
border: 1px solid #DDD;
border-bottom-color: transparent;
}

.left-menu-container {
background-color: #ccdfa8;
width: 90%;
}
.left-menu-container a{
color: #666;
}
</style>
<div class="left-menu-container">
	<ul class="nav nav-tabs nav-stacked">
		<li><a href='<c:url value="/about"></c:url>' >平台简介</a></li>
		<li><a href='<c:url value="/contact"></c:url>' >联系我们</a></li>
		<!-- <li><a href='<c:url value="/help"></c:url>' >服务帮助</a></li>
		<li><a href='<c:url value="/legal"></c:url>' >法律声明</a></li> -->
		<li><a href='#' >服务帮助</a></li>
		<li><a href='#' >法律声明</a></li>
	</ul>
</div>

<html>
<script type="text/javascript">
$(document).ready(function() {
	$('ul > li > a').each(function(index){
		if ($(this).attr('href') == (window.location.pathname+window.location.search)) {
			$(this).parent().addClass('active');
		}
	});
});
</script>
</html>