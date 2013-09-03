<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>

.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.border_ccc{
	border: 1px solid #ccc;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>我的同学</h4>
	</div>
	<div class="content">	
			<c:forEach items="${matesList}" var="mates">
				<div style="float: left; padding: 2px 8px;">
					<a href='<c:url value="/teacher/${mates.id}"></c:url>' >
					<img class="border_ccc" src='<c:url value="${mates.photo_url}"></c:url>' style="width: 80px;" /></a><br>
					 <span class="offset5">${mates.name}</span>
				</div>
			</c:forEach>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


