<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
	<style>
	body {
		max-width: 1600px;
		width: 100%;
	}
	
	.container-fluid {
		padding-right: 0px;
		padding-left: 0px;
	}
	
	.container-fluid.wrapper {
		background: url(/ccweb/resources/img/teacher_front_bg.jpg) repeat;
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
			<!--/row-->
			<div class="row-fluid">
				<tiles:insertAttribute name="navbar" />
			</div>
			<div class="row-fluid content-panel">
				<div class="span3">
					<div class="login-panel">
						<tiles:insertAttribute name="left" />
					</div>
				</div>
				<!--/span-->
				<div class="span9">
					<div class="right-panel">
						<tiles:insertAttribute name="right" />
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->

			<hr>

			<footer>
				<tiles:insertAttribute name="footer" />
			</footer>

		</div>
	</div>
	<!--/.fluid-container-->

	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>
