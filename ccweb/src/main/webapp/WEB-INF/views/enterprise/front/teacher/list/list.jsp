<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	/*background: #FAFAFB;*/

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #f77605 1.5px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	padding:0px 10px 5px 10px;
}

.row-fluid.custom .row {
	margin: 0px 10px 0px 10px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed  1px;
}
.row-fluid.custom .row .teacher{
	margin-top:10px;
	margin-left:13px;
	margin-bottom:10px;
	border:1px dotted #dadada;
	width: 100px;
	padding:5px;
	float: left;
}
</style>

<div class="row-fluid custom round">
	<div class="row"><h4>知名教师 </h4></div>
	<div class="row" style="border: solid 1px #f77605;" >
		<c:choose>
			<c:when test="${eTeacherCount !=0}">
				<c:forEach items="${page.content }" var="et">
					<div class="teacher">
						<div><a href='<c:url value="/enterprise/${userInfo.id}/teacher/view/${et.id}"></c:url>' ><img src='<c:url value="${et.photourl}" ></c:url>'  ></a></div>
						<div>${et.content }</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<br>
				无内容<br>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="margin: 10px 0px 0px 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
</div>