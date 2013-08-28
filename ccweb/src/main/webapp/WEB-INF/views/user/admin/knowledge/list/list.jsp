<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}

.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.row-fluid.custom .content .course{
	margin-right:10px;
	margin-bottom:10px;
	border:1px dotted #dadada;
	width: 230px;
	padding:5px;
	float: left;
}
.row-fluid.custom .content .page{
	float: left;
	clear:both;
	margin-left: 10px;
}

</style>
<script type="text/javascript">

</script>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>知识库</h4>
	</div>
	<div class="content" >
		<c:choose>
			<c:when test="${courseCount !=0}">
				<c:forEach items="${courseList}" var="course" >
			    	<div class="course">
						<div style="width: 230px; height: 155px; background-image: url('<c:url value="${course.courseCover }"></c:url>');  
								background-repeat:no-repeat;background-position:center;  ">
							<a href='<c:url value="/admin/mycourse/view/${course.id }"></c:url>'><div style="height: 125px;"></div></a>
		    				<div  style="height:24px;background-color:#000;  padding:3px; color: #fff;  Opacity:0.70; Filter:alpha(opacity=70);">
		    					<div  id="contentlimit" style="width: 240px;">
		    							${course.courseName } — ${course.cType.typeName }
		    					</div>
		   				 	</div>
						</div>
			    	</div>
			    </c:forEach>
			</c:when>
			<c:otherwise>
				<div >暂无内容</div>
			</c:otherwise>
		</c:choose>
		<div class="page"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


