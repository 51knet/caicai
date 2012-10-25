<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pagination">
	<c:if test="${page.totalPages > 0}">
		<c:set var="prev" value="${page.number-1}" scope="page"></c:set>
		<c:set var="next" value="${page.number+1}" scope="page"></c:set>
		<c:set var="current_url"
			value="<%=new org.springframework.web.util.UrlPathHelper().getOriginatingRequestUri(request)%>"
			scope="page"></c:set>
		<ul>
			<c:choose>
				<c:when test="${page.hasPreviousPage()}">
					<li><a href='${current_url}?pageNumber=${prev}'>Prev</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="disabled">Prev</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="#" class="active">${page.number+1}/${page.totalPages}</a></li>
			<c:choose>
				<c:when test="${page.hasNextPage()}">
					<li><a href='${current_url}?pageNumber=${next}'>Next</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="disabled">Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</c:if>
</div>