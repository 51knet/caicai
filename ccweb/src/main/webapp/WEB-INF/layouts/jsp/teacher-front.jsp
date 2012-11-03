<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
</head>
<body style="min-width: 1024px;max-width: 1024px;width: 1024px;margin: 0 auto;">
	<tiles:insertAttribute name="top-navbar" />
	<div class="container-fluid">
		<div class="row-fluid">
			<tiles:insertAttribute name="banner" />
		</div>
		<div class="row-fluid">
			<tiles:insertAttribute name="navbar" />
		</div>
		<div class="row-fluid content-panel">
			<div class="span3">
				<div class="item-panel">
					<tiles:insertAttribute name="left1" />
				</div>
				<div class="item-panel">
					<tiles:insertAttribute name="left2" />
				</div>
				<div class="item-panel">
					<tiles:insertAttribute name="left3" />
				</div>
			</div>
			<!--/span-->
			<div class="span9">
				<div class="subcontext">
					<tiles:insertAttribute name="context1" />
				</div>
				<div class="subcontext">
					<tiles:insertAttribute name="context2" />
				</div>
				<div class="subcontext">
					<tiles:insertAttribute name="context3" />
				</div>
				<div class="subcontext">
					<tiles:insertAttribute name="context4" />
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
			/*button apply: show input fields when click apply*/
			$('#button_apply').bind('click', function() {
				$(this).css('display', 'none');
				$('#payment_details').css('display', 'block');
			});

		});
	</script>
</body>
</html>
