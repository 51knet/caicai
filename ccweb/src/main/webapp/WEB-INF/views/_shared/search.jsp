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
.row-fluid .custom .row .top_left{
	width:15%;
	float: left;
	height: 18px;
}
.row-fluid .custom .row .top_left >span{
	font-size: 15px;
	color:  #80b029;
}
.row-fluid .custom .row .top_right{
	width:85%;
	float: right;
	background-image: url("<c:url value='/resources/img/default/greenline.png'></c:url>");
	background-position: left center;
	background-repeat: repeat-x;
	height: 18px;
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
.border_ccc{
	border: 1px solid #ccc;
}

</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>搜索</h4>
	</div>
	<div class="content">	
		<c:if test="${types == 'users' }">
			<c:forEach items="${userList}" var="user">
				<div style="float: left; padding: 2px 8px;">
					 <table cellpadding="0" width="100">
					 	<tr><td align="center"><a href='<c:url value="/id/${user.id}"></c:url>' ><img class="border_ccc" src='<c:url value="${user.photo_url}"></c:url>' style="width: 80px;" /></a></td></tr>
					 	<tr><td align="center"><div  style="width: 90px;" id="content">${user.name}</div></td></tr>
					 </table>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${types == 'courses' }">
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
		</c:if>
		<c:if test="${types == 'all' }">
			<c:forEach items="${userList}" var="user">
				<div style="float: left; padding: 2px 8px;">
					 <table cellpadding="0" width="100">
					 	<tr><td align="center"><a href='<c:url value="/id/${user.id}"></c:url>' ><img class="border_ccc" src='<c:url value="${user.photo_url}"></c:url>' style="width: 80px;" /></a></td></tr>
					 	<tr><td align="center"><div  style="width: 90px;" id="content">${user.name}</div></td></tr>
					 </table>
				</div>
			</c:forEach>
			<hr>
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
		</c:if>
		<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>


