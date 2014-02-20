<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.left-menu-container {
background-color: #718495;
width: 230px;
height:550px;

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
/*-webkit-border-radius: 0;
-moz-border-radius: 0;
border-radius: 0;*/
text-align: left;
padding:7px 20px;
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
/*background-color: #80B029;*/
background-color:#b1b6ba;
}
.nav-stacked > .active > a, .nav-stacked > .active > a:hover {
color: #3d4f67;
cursor: default;
text-decoration: none;
/*background-color: #80B029;*/
background-color:#b1b6ba;
border: 0px solid #DDD;
border-bottom-color: transparent;
}

.search_input{
	width: 120px;
}
</style>
<div class="left-menu-container">
	<ul  class="nav  nav-stacked" >
		<li>
			<div style="padding: 10px 10px 0px 20px;">
					<img src="<c:url value='/resources/img/default/sstitle.png'></c:url>" >
			</div>
			<div style="padding: 10px 10px 1px 20px;">
			<form class="form-search" style="margin">
				<input type="text" name="searchProjects"  class="input-small search_input" placeholder="项目搜索"> <button class="btn">搜索</button>
			</form>
			</div>
		</li>
		
		<li>
			<div style="padding: 10px 10px 0px 20px;">
					<img src="<c:url value='/resources/img/default/rzxmtitle.png'></c:url>" >
			</div>
			<div style="padding: 10px 10px 1px 20px;">
			
			</div>
		</li>
		<li><a href='<c:url value="/projects/list/all"></c:url>' ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 所有项目</span></a></li>
		<li><a href='<c:url value="/projects/list/uncomplete"></c:url>'  ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 未完成项目</span></a></li>
		<li><a href='<c:url value="/projects/list/complete"></c:url>'  ><span ><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" > 已完成项目</span></a></li>
		<li>
			<div style="padding: 10px 10px 0px 20px;">
					<img src="<c:url value='/resources/img/default/rzjgtitle.png'></c:url>" >
			</div>
			<div style="padding: 10px 10px 1px 20px;">
			&nbsp;
			</div>
		</li>
		
		<li>
			<div style="padding: 10px 10px 0px 20px;">
					<img src="<c:url value='/resources/img/default/fhytitle.png'></c:url>" >
			</div>
			<div style="padding: 10px 10px 1px 20px;">
			&nbsp;
			</div>
		</li>
		
	</ul>
</div>
