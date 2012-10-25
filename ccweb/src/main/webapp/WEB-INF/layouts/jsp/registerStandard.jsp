<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
	<meta name="description" content="" />
	<meta name="author" content="" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="icon" type="image/png" href="<c:url value="/resources/img/icon.png" />" />
	
	<!--[if lt IE 9]>
	      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	    <![endif]-->
	<title><tiles:getAsString name="title" /></title>
	<style type="text/css" media="screen">
	@import url("<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>");
	@import url("<c:url value="/resources/css/standard.css"/>");
	</style>
	<!-- Add jQuery library -->
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.8.0.js" />"></script>
</head>
<body style="min-width: 1024px;max-width: 1024px;width: 1024px;margin: 0 auto;">
	<div class="container-fluid">
		<div class="row-fluid">
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
		</div>
		<!--/row-->

		<div class="row-fluid content-panel">
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
			/*button apply: show input fields when click apply*/
			$('#button_apply').bind('click', function() {
				$(this).css('display', 'none');
				$('#payment_details').css('display', 'block');
			});

		});
	</script>
</body>
</html>
