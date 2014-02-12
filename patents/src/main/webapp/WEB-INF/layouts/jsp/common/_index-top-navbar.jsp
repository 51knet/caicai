<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="navbar custom navbar-fixed-top">
	<div class="container-fluid" style="text-align: center; height: 39px; width: 1024px; margin: 0 auto;">
		<ul class="nav pull-left">
			<li><a id="logo" href='<c:url value="/"></c:url>'></a></li>
		</ul>
		<ul class="nav pull-right">
			<li><a href="<c:url value='/jumpToCourses'></c:url> "><img class="courselogo" src="<c:url value='/resources/img/default/courselogo.png'></c:url>">
			<img class="goto" src="<c:url value='/resources/img/default/index/goto.png'></c:url>"></a></li>
			<li ><div style="width: 30px;"></div></li>
		</ul>
	</div>
</div>
