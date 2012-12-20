<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
</head>
<body class="admin">
	<tiles:insertAttribute name="top-navbar" />
	<!-- /top-navbar -->
	<div class="container-fluid block-with-bg">
		<div class="container-fluid content-centered">
			<div class="row-fluid">
				<tiles:insertAttribute name="banner" />
			</div>
			<!--/row-->
			<tiles:insertAttribute name="breadcrumbs" />
			<div class="row-fluid content-panel">
				<div class="span3">
					<div class="left-panel">
						<tiles:insertAttribute name="left" />
					</div>
				</div>
				<!--/span-->
				<div class="span9">
					<div class="right-panel">
						<tiles:insertAttribute name="right" />
						<div class="row-fluid content-panel">
							<div class="span9">
							<div >
								<tiles:insertAttribute name="right_top"/>
							</div>
							<div >
								<tiles:insertAttribute name="right_center"/>
							</div>
							<div >
								<tiles:insertAttribute name="right_footer"/>
							</div>
							</div>
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->

			<hr>

			<footer><tiles:insertAttribute name="footer" /></footer>

		</div>
	</div>
	<!--/.fluid-container-->
</body>
</html>
