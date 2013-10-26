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
		<h4>新关注我的</h4>
	</div>
	<div class="content">	
		<c:forEach items="${unReadFollower}" var="unReadFollower">
			<div style="float: left; padding: 2px 8px;">
				 <table cellpadding="0" width="100">
				 	<tr><td align="center"><a href='<c:url value="/id/${unReadFollower.id}"></c:url>' ><img class="border_ccc" src='<c:url value="${unReadFollower.photo_url}"></c:url>' style="width: 80px;" /></a></td></tr>
				 	<tr><td align="center">${unReadFollower.name}</td></tr>
				 </table>
			</div>
		</c:forEach>
		<c:if test="${unReadFollowerCount <= 0}">
			 <a href="<c:url value='/admin/fans/list'></c:url>" style="color: #80b029; font-size: 14px; ">没有新粉丝，点击查看所有粉丝</a>
		</c:if>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


