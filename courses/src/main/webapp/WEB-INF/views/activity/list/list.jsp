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
	 margin: 0px 55px;
}

</style>
 <div class="path_link"><a href="<c:url value='/'></c:url>" >首页 </a> >> 活动列表 </div>
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
			 	 <thead>
				 	<tr class="bb">
				 		<th align="left" width="88%">活动标题</th>
				 		<th align="left">发布时间</th>
				 	</tr>
				 </thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr class="bLine_dash" >
							<td >
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



