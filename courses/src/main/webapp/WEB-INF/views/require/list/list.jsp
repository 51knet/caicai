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
.active{
	background-color:#ccdfa8;
}


</style>
<div class="container title"  >
		<div class="innerLeftTitle " style="<c:if test='${active != "patent" }' >background-color: #adcc75</c:if>"><a href="<c:url value="/requirement/patent/list"></c:url>" class="a_color_green">专利需求</a></div>
		<div class="innerLeftTitle" style="margin-left: 10px; <c:if test='${active != "technology" }'>background-color: #adcc75</c:if>"><a href="<c:url value="/requirement/technology/list"></c:url>" class="a_color_green">技术需求</a></div>
 </div>
 <div class="container patent">
	 <table  style="width: 90%; " cellpadding="8"  border="0">
	 <thead>
	 	<tr class="bb">
	 		<th align="left">需求标题</th><th align="left">发布人</th><th align="left">发布时间</th>
	 	</tr>
	 </thead>
		<tbody>
			<c:forEach items="${page.content}" var="page">
				<tr class="bLine_dash" >
				<td width="70%">
					<a  href="<c:url value="/requirement/view/${ page.id}"></c:url>">${page.title}</a>
				</td>
				<td width="20%">
					${page.user.name}
				</td>
				<td><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd "/></td>
				</tr>
			</c:forEach>				
		</tbody>
		</table>
		<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>

 </div>



