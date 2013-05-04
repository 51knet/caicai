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
		background-color: #ffffff;
	}
	
	.container-fluid {
		padding-right: 0px;
		padding-left: 0px;
	}
	
	.container-fluid.wrapper {
		<!--background-image: url(/ccweb/resources/img/default/front_bg.png);-->
		background-position: center top;
		background-repeat: repeat-y;
		<!--background-color: #929294;-->
		background-color: #ffffff;
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
	<div class="container-fluid wrapper">
		<div class="container-fluid custom">
			<div class="row-fluid">
				<tiles:insertAttribute name="banner" />
			</div>
			<div class="row-fluid">
				<tiles:insertAttribute name="navbar" />
			</div>
			<div class="row-fluid content-panel" style="margin-top: -20px;">
				<div class="span3"  style="width:210px;" >
					<tiles:insertAttribute name="ctype" />
				</div>
				<!--/span-->
				<div class="span9"  style="width:792px;">
					<tiles:insertAttribute name="detailinfo" />
				</div>
			</div>
			<div class="row-fluid content-panel">
				<div class="span3" style="width:180px;">
					<tiles:insertAttribute name="left" />
				</div>
				<!--/span-->
				<div class="span9"  style="width:822px;">
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
