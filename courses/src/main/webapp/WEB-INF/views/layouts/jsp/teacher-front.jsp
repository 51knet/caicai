<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
	<style>

	
	.container-fluid {
		padding-right: 0px;
		padding-left: 0px;
	}
	
	.container-fluid.custom {
		min-width: 1024px;
		max-width: 1024px;
		width: 1024px;
		margin: 0 auto;
	}
	</style>
</head>
<body>
	<tiles:insertAttribute name="top-navbar" />
	<div class="container-fluid ">
		<div class="container-fluid custom">
			<div style="margin-top: 45px;"></div>
			<div class="row-fluid">
				<tiles:insertAttribute name="banner" />
			</div>
			<div class="row-fluid">
				<tiles:insertAttribute name="navbar" />
			</div>
			<div class="row-fluid content-panel">
				<div class="span3">
					<tiles:insertAttribute name="left" />
				</div>
				<!--/span-->
				<div class="span9">
					<tiles:insertAttribute name="right" />
				</div>
				<!--/span-->
			</div>
			<hr>
			<footer>
				<tiles:insertAttribute name="footer" />
			</footer>
		</div>

	</div>
	<!--/.fluid-container-->
</body>
</html>
