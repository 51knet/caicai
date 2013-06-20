<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
<style>
body {
	background-position: center top;
	background-repeat: repeat-y;
	background-color: #edf1e0;
	}
.navbar {
	margin-bottom: 0px;
}
.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container.course {
	width: 990px;
	max-width:990px;
	text-align: center;
}

.container.course.row{

	 text-align: left;
}
</style>
</head>
<body >
	<tiles:insertAttribute name="top-navbar" />
	<div class="container-fluid">
			<div class="container course">
				<tiles:insertAttribute name="banner" />
			</div>
			<!--/row-->
			<tiles:insertAttribute name="breadcrumbs" />
				<div class="span2"  style="margin-top: 15px;">
					<tiles:insertAttribute name="left" />
				</div>
				<!--/span-->
				<div class="span10"  style="margin-top: 15px; background-color: #fff; border: 1.5px solid #eed593; width: 810px;" >
					<div class="container course row"  style="width: 810px;">
						<tiles:insertAttribute name="right" />
					</div>
					<div class="container course row"  style="width: 810px; " >
						<tiles:insertAttribute name="right_bottom" />
					</div>
				</div>
				<!--/span-->
			<!--/row-->

			<br>

			

	</div>
	<footer><tiles:insertAttribute name="footer" /></footer>
	<!--/.fluid-container-->
</body>
</html>
