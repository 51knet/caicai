<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span12">
	<div class="banner">
		Banner
		<div class="pull-right">
			<c:if test="${userInfo != null}">
				<a href='<c:url value="/signout"></c:url>'>退出登陆</a>
			</c:if>
		</div>
	</div>
	<!--/.banner -->
</div>
<!--/span-->