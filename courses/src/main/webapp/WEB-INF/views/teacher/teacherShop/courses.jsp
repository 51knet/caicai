<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<div>
	<table class="table" style="width:65%;">
	<tr>
    <td  valign="middle" style="width:20%" bordercolor="#F0F0F0" bgcolor="#CCCCCC"><h1><span class="STYLE1">课程计划</span></h1>
    </td>
  	</tr>
		<c:forEach var="teacherCourse" items="${teacherCourseList}">
		  <tr>
		    <td>课程名称:${teacherCourse.courseName }</td>
		    <td>此课程所属类型:${teacherCourse.courseType }</td>
		    <td colspan="2">此课程发布时间:${teacherCourse.courseDate }</td>
		  </tr>
		  <tr>
		    <td>课程描述:${teacherCourse.courseDesc }</td>
		  </tr>
		</c:forEach>
	</table>
	</div>
</div>