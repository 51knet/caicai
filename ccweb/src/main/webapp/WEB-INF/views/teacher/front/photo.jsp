<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="text-align: center;">
	<a href='<c:url value='/teacher/${uid}'></c:url>' class="btn">${name }</a>
	${sessionScope.userInfo.user.id }
</div>

<div style="text-align: center;">
	<img src='${photoUrl}' height='80px' width='60px'
		align="middle">
</div>

<div style="text-align: center;">
	<c:if test="${sessionScope.userInfo.user.id!= uid }">
		<a href='<c:url value='/addrelation?uid=${uid}'></c:url>' class="btn">关注ta</a>
		<a href='<c:url value='/sendmessage?uid=${uid}'></c:url>' class="btn">发私信</a>
	</c:if>
	<hr />
</div>