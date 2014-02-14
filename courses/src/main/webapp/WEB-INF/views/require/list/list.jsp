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
	 width: 100%;
}
.active{
	background-color:#526a88;
}
</style>
 <!-- <div class="path_link"><a href="<c:url value='/'></c:url>" >首页 </a> >> <c:if test='${active == "technology" }'>技术需求</c:if><c:if test='${active == "patent" }'>专利需求</c:if> </div> -->
<div class="container title"  >
		<div class="innerLeftTitle " >
			<c:if test='${active == "patent" }'>  <a href="#">专利需求</a></c:if>
			 <c:if test='${active == "technology" }'><a href="#" >技术需求</a></c:if>
		</div>
 </div>

 <div class="container patent">
		<div class="span4" style="width: 350px;">
			 <table  style="width: 100%; " cellpadding="8"  border="0">
					<tbody>
						<c:forEach items="${page.content}" var="page" begin="0" step="2" varStatus="i">
							<tr class="bLine_dash" >
								<td >
									<a  href="<c:url value="/requirement/${active}/view/${ page.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" >   
									${page.title}
									</a>
								</td>
							</tr>
						</c:forEach>				
					</tbody>
				</table>
		</div>
		<div class="span4" style="width: 350px;">
			<table  style="width: 100%; " cellpadding="8"  border="0">
					<tbody>
						<c:forEach items="${page.content}" var="page" begin="1" step="2" varStatus="i">
							<tr class="bLine_dash" >
							<td >
								<a  href="<c:url value="/requirement/${active}/view/${ page.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" >  
									${page.title}
								</a>
							</td>
							</tr>
						</c:forEach>				
					</tbody>
				</table>
		</div>
			
 </div>
 <div class="container patent">
	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
</div>
