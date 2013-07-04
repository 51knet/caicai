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
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>我的课程</h4>
	</div>
	<div class="content">	
		<div style="text-align: left;">
			<table class="yellow" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th align="center" width="30%">课程标题</th>
						<th align="center" width="15%">课程类别</th>
						<th align="center">课程状态</th>
						<th align="center">授课人</th>
						<th align="center" width="25%">发布时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courseList}" var="course">
						<tr>
						<td align="left">
							<div style="width: 200px;" id="content">
								<c:if test="${course.publish==1 }">
									${course.courseName}
								</c:if>
								<c:if test="${course.publish >1 }">
									<a href='<c:url value="/admin/mycourse/view/${course.id }"></c:url>'>${course.courseName}</a>
								</c:if>
							</div>
						</td>
						<td align="center">${course.cType.typeName}</td>
						<td align="center">
							<c:if test="${course.publish ==3 }">已上架</c:if>
							<c:if test="${course.publish <3 }">已下架</c:if>
						</td>
						<td align="center"> ${course.user.name }</td>
						<td align="center">${course.courseDate}</td></tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		</div>
	</div>
</div>
