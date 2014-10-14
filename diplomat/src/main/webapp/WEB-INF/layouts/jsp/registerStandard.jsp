<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
	<style>
	body {
		max-width: 1600px;
		width: 100%;
		background-color: #FFFFFF;
	}
	.container {
		width: 1170px;
	}
	.container-fluid {
		min-width: 1024px;
		max-width: 1024px;
		margin: 0 auto;
		padding-right: 0px;
		padding-left: 0px;
	}
	</style>
</head>
<body>
	<tiles:insertAttribute name="top-navbar" />
	<div class="container-fluid">
		<div class="row-fluid">
			</div>
		<div class="row-fluid">
			</div>
		<div class="row-fluid">
			<tiles:insertAttribute name="body" />
		</div>
		<hr>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>

	</div>

</body>
</html>
