<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="pagination">
	<c:if test="${page.totalPages > 0}">
		<c:set var="prev" value="${page.number-1}" scope="page"></c:set>
		<c:set var="next" value="${page.number+1}" scope="page"></c:set>
		<c:set var="current_url"
			value="<%=new org.springframework.web.util.UrlPathHelper().getOriginatingRequestUri(request)%>"
			scope="page"></c:set>
		<s:eval expression="page.hasPreviousPage()" var="hasPreviousPage"></s:eval>
		<s:eval expression="page.hasNextPage()" var="hasNextPage"></s:eval>
		<ul>
			<c:choose>
				<c:when test="${hasPreviousPage}">
					<li><a href='${current_url}?pageNumber=${prev}'>上一页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="disabled">上一页</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="#" class="active">${page.number+1}/${page.totalPages}</a></li>
			<c:choose>
				<c:when test="${hasNextPage}">
					<li><a href='${current_url}?pageNumber=${next}'>下一页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="disabled">下一页</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</c:if>
</div>