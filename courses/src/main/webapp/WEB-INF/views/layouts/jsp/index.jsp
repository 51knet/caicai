<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
</head>
<body>
	<tiles:insertAttribute name="top-navbar" />

	<div class="container-fluid">
		<div class="row-fluid">
			<tiles:insertAttribute name="search" />
		</div>
			<div class="row-fluid">
			<tiles:insertAttribute name="test" />
		</div>
		<div class="row-fluid">
			<tiles:insertAttribute name="body" />
		</div>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
</body>
</html>
