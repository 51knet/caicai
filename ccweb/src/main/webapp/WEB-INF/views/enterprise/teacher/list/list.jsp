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
	background: #FAFAFB;
}

.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 10px 0px 0px 0px;
}

.row-fluid.custom .row {
	margin: 10px 40px 0px 40px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed #cccccc 1px;
}
.row-fluid.custom .row .bro{
	margin-top:10px;
	margin-right:17px;
	margin-left:7px;
	margin-bottom:10px;
	border:1px solid #ccc;
	width: 210px;
	padding:5px;
	float: left;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>知名教师</h4>
	</div>
	<div class="row">
		<table  style="width: 100%" >
			<tbody>
				<tr ><td>
					<c:choose>
						<c:when test="${eTeacherCount !=0}">
							<c:forEach items="${page.content }" var="et">
								<div class="bro">
									<div><a href='<c:url value="/enterprise/${teacherInfo.id}/teacher/view/${et.id}"></c:url>' ><img src='<c:url value="${et.photourl}" ></c:url>'  ></a></div>
									<div>${et.content }</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
						<br>
							无内容<br><br>
						</c:otherwise>
					</c:choose>
				</td></tr>
			</tbody>
		<tfoot>
	    <tr><td >
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
		
		<form action="<c:url value="/course/view"></c:url>" id="showCourseDetail" method="post">
			<input type="hidden"  name="teacherId" id="teacher_id" >
			<input type="hidden"  name="courseId" id="course_id">
			<input type="hidden"  name="coursepwd" id="course_pwd">
		</form>
	</div>
</div>