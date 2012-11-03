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
	@import url("<c:url value="/resources/colorbox/colorbox.css"/>");
	</style>
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.8.0.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/colorbox/jquery.colorbox.js" />"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/kindeditor.js" />"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/lang/zh_CN.js" />"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/js/myutil.js" />"></script>
</head>
<body>
	<tiles:insertAttribute name="top-navbar" />
	
	<div class="container-fluid">
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
	<!--/.fluid-container-->

	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>
