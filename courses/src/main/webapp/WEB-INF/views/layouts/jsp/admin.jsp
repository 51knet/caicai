<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
</head>
<style>
	
</style>
<body class="admin">
	<tiles:insertAttribute name="top-navbar" />
	<!-- /top-navbar -->
	<div class="container-fluid">
		<div class="container-fluid content-centered">
			<div class="row-fluid" style="margin-bottom: 20px;width: 810px;">
				<tiles:insertAttribute name="banner" />
			</div>
			<!--/row-->
			<tiles:insertAttribute name="breadcrumbs" />
			<div class="row-fluid">
				<div class="span2" >
					<div class="left-panel">
						<tiles:insertAttribute name="left" />
					</div>
				</div>
				<!--/span-->
				<div class="span10" style="width: 810px;">
					<div class="right-panel"  style="width: 810px;">
						<tiles:insertAttribute name="right" />
					</div>
					<div class="right-panel"  style="width: 810px;">
						<tiles:insertAttribute name="right2" />
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->

			<br>

			<footer><tiles:insertAttribute name="footer" /></footer>

		</div>
	</div>
	<!--/.fluid-container-->
</body>
</html>
