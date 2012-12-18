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
.raw_image {
    width: 100%;
    height: 100%;
}
</style>

<div class="row-fluid custom round">
	<div>
		<c:forEach var="teacherCourse" items="${teacherCourseList}">
		<tr>
		    <td>&nbsp;&nbsp;课程名称:${teacherCourse.courseName }&nbsp;&nbsp;</td>
		    <td>&nbsp;&nbsp;此课程所属类型:${teacherCourse.courseType }&nbsp;&nbsp;</td>
		  </tr>
		  <br/>
		  <tr>
		    <td>&nbsp;&nbsp;课程描述:${teacherCourse.courseDesc }&nbsp;&nbsp;</td>
		   </tr>
		    <br/>
		    <tr>
		    <td>
		    &nbsp;&nbsp;此课程发布时间:${teacherCourse.courseDate }&nbsp;&nbsp;</td>
		  </tr>
		 <br/>
		</c:forEach>
	</div>
</div>