<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.left-menu-container {
background-color: #3c4f65;
width: 230px;
height:480px;

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
padding:15px 20px;
}

.nav-stacked  > li > a:hover {

color: #3d4f67;
text-decoration: none;
background-color:#b1b6ba;
}
.nav-stacked > .active > a, .nav-stacked > .active > a:hover {
color: #3d4f67;cursor: default;text-decoration: none;
background-color:#b1b6ba;border: 0px solid #DDD;
border-bottom-color: transparent;
}
</style>
<div class="left-menu-container">
	<ul  class="nav  nav-stacked" >
		<li><a href='<c:url value="/projects/list"></c:url>'><img src="<c:url value='/resources/img/default/rzabout-3.png'></c:url>"></a></li>
		<li><a href='<c:url value="/projects/about"></c:url>'><img src="<c:url value='/resources/img/default/rzabout-1.png'></c:url>"></a></li>
		<li><a href='<c:url value="/projects/guide"></c:url>'><img src="<c:url value='/resources/img/default/rzabout-2.png'></c:url>"></a></li>
		<li><a href='<c:url value="/projects/rules"></c:url>'><img src="<c:url value='/resources/img/default/rzabout-4.png'></c:url>"></a></li>
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