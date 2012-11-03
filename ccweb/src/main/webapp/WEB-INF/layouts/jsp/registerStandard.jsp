<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
</head>
<body>
	<tiles:insertAttribute name="top-navbar" />
	
	<div class="container-fluid">
		<div class="row-fluid">
			<tiles:insertAttribute name="banner" />
		</div>
		<div class="row-fluid content-panel">
			<!--/span-->
			<div class="span9">
				<div class="right-panel">
					<tiles:insertAttribute name="body" />
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
	<!--/.fluid-container-->

	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>
