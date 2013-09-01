<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar .navbar-inner .nav{
	margin-left: 30px;
}
.navbar .navbar-inner .nav >li{
	margin-left: 15px;
}
.navbars{
	background-image: url("<c:url value='/resources/img/default/user-navbar-bg.png'></c:url>");
	background-position: left top;
	background-repeat: no-repeat;
	height: 50px;
	margin-bottom: 20px;
}
.navbars >div{
	float: left;
	margin:12px 25px 0px 35px;
}
</style>
<div class="navbars">
	<div><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="<c:url value='/resources/img/default/front-home.png'></c:url>" ></a></div>
	<div><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="<c:url value='/resources/img/default/front-resume.png'></c:url>" ></a></div>
	<div><a href='<c:url value='/user/${userInfo.id}'></c:url>'><img src="<c:url value='/resources/img/default/front-friend.png'></c:url>" ></a></div>
</div>