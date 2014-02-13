<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
	<style type="text/css" media="screen">
		@import url("<c:url value="/resources/css/admin-layout.css"/>");
		body{
		/*	background-image: url("<c:url value='/resources/img/default/admin-user-bg.png'/>");*/
			background-position: center top;
			background-repeat: no-repeat;
			background-color: #f1f1f1;
		}
		.back_white{
			background-color: #fff;
		}
		.border{
			/*border: 1.5px solid #eed593;*/
			border: 1.5px solid #718495;
		}
		.border-green-all{
			border: 1.5px solid #9db84d;
		}
		.border-green-right{
			 border-right:1px solid #9db84d;
		}
	</style>
</head>
<body class="admin" >
	<tiles:insertAttribute name="top-navbar" />
	<!-- /top-navbar -->
	<div class="container-fluid"  >
		<div class="container-fluid content-centered  back_white" >
			<div class="row-fluid  back_white">
				<tiles:insertAttribute name="banner" />
			</div>
			<!--/row-->
			<tiles:insertAttribute name="breadcrumbs" />
			<div class="row-fluid content-panel" style="padding: 0px 26px; ">
				<div class="span2" style="width: 190px; ">
					<div class="left-panel">
						<tiles:insertAttribute name="left" />
					</div>
				</div>
				<!--/span-->
				<div class="span9 border" style="background-color: #fff;" >
					<div class="right-panel">
						<tiles:insertAttribute name="flash" />
						<tiles:insertAttribute name="right" />
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
