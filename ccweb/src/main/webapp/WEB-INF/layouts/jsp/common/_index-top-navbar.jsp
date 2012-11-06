<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar.custom {
	height: 45px;
	z-index: 2;
	background: #4E4E4E url(<c:url value="/resources/img/noise.png"></c:url>);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -moz-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -webkit-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -o-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), -ms-linear-gradient(#4E4E4E, #1C1C1C);
	background: url(<c:url value="/resources/img/noise.png"></c:url>), linear-gradient(#4E4E4E, #1C1C1C);
	box-shadow: 0 1px 5px rgba(0, 0, 0, 0.45);
}

.navbar.custom #logo {
	width: 100px;
	height: 32px;
	position: absolute;
	text-indent: -999em;
	background: url(<c:url value="/resources/img/logo_bak.png"></c:url>);
	left: 50%;
	top: 5px;
	margin-left: -50px;
	box-shadow: none;
}
</style>
<div class="navbar custom navbar-fixed-top">
	<a id="logo" href="#">Knet</a>
</div>
