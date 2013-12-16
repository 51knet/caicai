<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
</head>
<style>
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
     padding-left: 0px;
	 margin-left: 18%;
	 margin-top: -215px;
	 text-align: left;
}
</style>
<body >
	<tiles:insertAttribute name="top-navbar" />
	<div class="container-fluid">
			<div class="container course" >
				<tiles:insertAttribute name="banner" />
			</div>
			<!--/row-->
			<tiles:insertAttribute name="breadcrumbs" />
					<div class="container course" style="margin-top: 15px;">
						<tiles:insertAttribute name="left" />
					</div>
				<!--/span-->
				<div class="container course" >
					<div class="container course row"  style="width: 810px;">
						<tiles:insertAttribute name="right" />
					</div>
					<div class="container course row"  style="width: 810px;">
						<tiles:insertAttribute name="right_bottom" />
					</div>
				</div>	
				<!--/span-->
			<!--/row-->

			<br>

			<footer><tiles:insertAttribute name="footer" /></footer>

	</div>
	<!--/.fluid-container-->
</body>
</html>
