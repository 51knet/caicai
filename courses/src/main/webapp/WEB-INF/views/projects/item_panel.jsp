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
.bottom_line{
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
font-family: 'Microsoft YaHei',Arial;
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
background-color:#a1a6ac;
}
.nav-stacked > .active > a, .nav-stacked > .active > a:hover {
color: #3d4f67;
cursor: default;
text-decoration: none;
background-color:#a1a6ac;
border: 0px solid #DDD;
border-bottom-color: transparent;
}
.pic_title{
padding: 10px 10px 0px 20px;
}
.pic_btm{
	padding: 10px 10px 1px 20px;
}
.search_input{
	width: 120px;
}
</style>
<div class="left-menu-container">
	<ul  class="nav  nav-stacked" >
		<li  class="bottom_line">
			<div  class="pic_title">
					<a href='<c:url value="/projects/list"></c:url>' ><img src="<c:url value='/resources/img/default/rzhometitle.png'></c:url>" ></a>
			</div>
			<div class="pic_btm">
			&nbsp;
			</div>
		</li>
	
		<li class="bottom_line">
			<div  class="pic_title">
					<img src="<c:url value='/resources/img/default/sstitle.png'></c:url>" >
			</div>
			<div class="pic_btm">
			<form class="form-search" action="<c:url value="/projects/search"></c:url>" method="get">
				<input type="text" name="searchParam"  class="input-small search_input" placeholder="项目搜索" value="${ searchParam}"> <button class="btn">搜索</button>
			</form>
			</div>
		</li>
		
		<li  class="bottom_line">
			<div  class="pic_title">
					<img src="<c:url value='/resources/img/default/rzxmtitle.png'></c:url>" >
			</div>
			<div class="pic_btm">
			
			</div>
		</li>
		<li ><a href='<c:url value="/projects/list/all"></c:url>' ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 所有项目</span></a></li>
		<li ><a href='<c:url value="/projects/list/uncomplete"></c:url>'  ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 未完成项目</span></a></li>
		<li class="bottom_line"><a href='<c:url value="/projects/list/complete"></c:url>'  ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 已完成项目</span></a></li>
		<li  class="bottom_line">
			<div class="pic_title">
					<a href='<c:url value="/rzfh/list/rzjg"></c:url>' ><img src="<c:url value='/resources/img/default/rzjgtitle.png'></c:url>" ></a>
			</div>
			<div class="pic_btm">
			&nbsp;
			</div>
		</li>
		
		<li  class="bottom_line">
			<div  class="pic_title">
					<a href='<c:url value="/rzfh/list/fhyq"></c:url>' ><img src="<c:url value='/resources/img/default/fhytitle.png'></c:url>" ></a>
			</div>
			<div class="pic_btm">
			&nbsp;
			</div>
		</li>
		
		<li  class="bottom_line">
			<div  class="pic_title">
					<a href='<c:url value="/"></c:url>' ><img src="<c:url value='/resources/img/default/hometitle.png'></c:url>" ></a>
			</div>
			<div class="pic_btm">
			&nbsp;
			</div>
		</li>
		
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
