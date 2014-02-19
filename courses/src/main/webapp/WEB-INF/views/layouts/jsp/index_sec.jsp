<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
</head>
<style>
	.bg_white{
		background-color: #fff;
	}
	.margin_top_10{
		margin-top: 10px;
	}
</style>
<body>
	<tiles:insertAttribute name="top-navbar" />

	<div class="container-fluid" >
		<div style="position: relative;">
			<div class="row-fluid bg_white">
				<tiles:insertAttribute name="search" />
			</div>
			<div class="row-fluid bg_white">
				<tiles:insertAttribute name="userCourse" />
			</div>
		</div>
		<div class="row-fluid bg_white">
				<div class="span3 margin_top_10"  style="width: 230px; margin-left: 20px;">
					<tiles:insertAttribute name="left" />
				</div>
				
				<div class="span9 margin_top_10" style="width: 730px; " >
					<tiles:insertAttribute name="right" />
				</div>
				
		</div>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
</body>
</html>
