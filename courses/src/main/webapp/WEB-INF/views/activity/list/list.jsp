<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
.titlebg{
	background-color:#ccdfa8; 
	font-size: 14px;
	width: 100%;
}
 .selete_filter{
 	margin-top:10px;
	text-align: left;
}
.patent{
	 width: 1024px;
	 margin: 10px 55px;
}
</style>
<div class="container title"  >
		<div class="innerLeftTitle">活动动态</div>
 </div>
 <div class="container patent">
	<c:choose>
		<c:when test="${activityCount <=0}">
			<h4 style="margin-left: 20px;">尚未添加内容</h4>
		</c:when>
		<c:otherwise>
			 <table  style="width: 90%; " cellpadding="8"  border="0">
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr class="bLine_dash" >
						<td width="88%">
							<a  href="<c:url value="/activity/view/${ page.id}"></c:url>">${page.title}</a>
						</td>
						<td><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd "/></td>
						</tr>
					</c:forEach>				
				</tbody>
				</table>
				  <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
 </div>



