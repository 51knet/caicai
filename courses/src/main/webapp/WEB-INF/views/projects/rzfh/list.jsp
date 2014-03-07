<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
<!--

$(document).ready(function(){
	$(".projectsbgcolor").mouseover(function(){
		  $(this).css("background-color","#f1f1f1");
	});
	$(".projectsbgcolor").mouseout(function(){
		  $(this).css("background-color","");
	});
});

//-->
</script>
<style>

.projects{
	 width: 100%;
	 margin:10px 0px;
	 padding-left: 20px;
}
.projects .detail{
	width: 680px;
	margin-top:10px;  
	float: left; padding:5px 5px;  
	border-bottom: 1px solid #ccc; text-align:left;
}
.projects .detail .logo_div{
	float: left; border: 1px solid #ccc; padding: 2px 2px;
}

.projects .detail .info_div{
	float: left; margin-left: 20px; 
	max-width: 440px; overflow: hidden;
}
._logo{
	width: 200px; height: 139px;
}

</style>
<div class="container title"  >
		<div class="innerLeftTitle " >
		<c:if test="${types=='rzjg' }"> <img  src="<c:url value='/resources/img/default/rzjgtitle1.png'></c:url> "></c:if>
		<c:if test="${types=='fhyq' }"> <img  src="<c:url value='/resources/img/default/fhytitle1.png'></c:url> "></c:if>
		</div>
 </div>
 	
 <div class="container projects">
 	<c:forEach items="${page.content}" var="page"  >
 			<div class="detail projectsbgcolor">
				<div class="logo_div"><a href="<c:url value='${page.webUrl}'></c:url>"><img src="<c:url value='${p_url}${page.logoPath }'></c:url> " class="_logo"></a></div>
				<div  class="info_div">
					<a href="#"><h4>${page.name }</h4></a>
					<div class="content_div" style="max-height: 120px; overflow: hidden;">
						${page.content }
					</div>
				</div>
			</div>
	</c:forEach>
 </div>
 <div style="margin-left: 20px;">
 <c:choose>
	<c:when test="${searchParam != null }">
		  <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
	 	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
</div>



