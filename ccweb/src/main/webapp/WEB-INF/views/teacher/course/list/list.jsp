<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h5>教师课程</h5>
	</div>
	<div class="row">
		<table class="table">
			<thead>
					<tr><th width=20%>课程名称</th><th  width=60%>课程描述</th><th width=20%>创建时间</th></tr>
			</thead>
			<tbody>
				<c:forEach var="course" items="${page.content}">
					<tr>
						<td ><a href="<c:url value="/teacher/${teacherInfo.id}/course/view/${course.id}"></c:url>">${course.courseName}</a></td>
						<td >${course.courseDesc}</td>
						<td >${course.courseDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		<tfoot>
	    <tr><td colspan="3">
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
	</div>
</div>