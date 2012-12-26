<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/views/_shared/common/_html_head.jsp"></jsp:include>
	
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
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}

</style>
</head>
<body>
	<tiles:insertAttribute name="top-navbar" />

	<div class="container-fluid">
		<div class="container course">
			<tiles:insertAttribute name="courseInfo" />
		</div>
		<div class="container course">
			<tiles:insertAttribute name="teacherInfo" />
		</div>
		<div class="container course">
			<tiles:insertAttribute name="courseResource" />
		</div>
		<div class="container course">
			<tiles:insertAttribute name="comment" />
		</div>
		<br>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>
</body>
</html>
