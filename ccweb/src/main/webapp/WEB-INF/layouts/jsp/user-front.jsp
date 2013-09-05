<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/layouts/jsp/common/_html_head.jsp"></jsp:include>
	<style type="text/css" media="screen">
		@import url("<c:url value='/resources/css/admin-layout.css'/>");
		body{
			/*background-color: #edf1e0;*/
			background-image: url("<c:url value='/resources/img/default/front-user-bg.png'/>");
			background-position: center top;
			background-repeat: repeat-y;
		}
		
		.border{
			/* border: 1.5px solid #eed593;*/
			 border: 1.5px solid #9db84d;
		}
		
		.border-green-all{
			border: 1.5px solid #9db84d;
		}
		.border-green-right{
			 border-right:1px solid #9db84d;
		}
	</style>
</head>
<body >
	<tiles:insertAttribute name="top-navbar" />
	<!-- /top-navbar -->
	<div class="container-fluid"  >
		<div class="container-fluid content-centered" >
			<div class="row-fluid">
				<tiles:insertAttribute name="banner" />
			</div>
				
			<!--/row-->
			<tiles:insertAttribute name="breadcrumbs" />
			<div class="row-fluid content-panel" style="background-color: #fff; margin-top: -20px;">
			<tiles:insertAttribute name="navbar" />
				<!--/span-->
				<div class="span8" style="width: 608px;">
						<tiles:insertAttribute name="left" />
				</div>
				
				<div class="span2" style="width: 278px; float: left;">
					   <tiles:insertAttribute name="right" />
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
