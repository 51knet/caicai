<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row-fluid custom round">
<div align="center">
	<div style="width:52%;">
    <div align="left" style="background-color: #F7F7F7;height:40px"  ><h4><span>课程计划</span></h4></div>
    <br/>
		<c:forEach var="teacherCourse" items="${teacherCourseList}">
		    <div align="left">
		    &nbsp;&nbsp; &nbsp;&nbsp;课程名称:${teacherCourse.courseName }&nbsp;&nbsp; &nbsp;&nbsp;
		   &nbsp;&nbsp; &nbsp;&nbsp; 所属类型:${teacherCourse.courseType }&nbsp;&nbsp; &nbsp;&nbsp;
		    &nbsp;&nbsp; &nbsp;&nbsp;发布时间:${teacherCourse.courseDate }&nbsp;&nbsp; &nbsp;&nbsp;</div>
		  <br/>
		   <div align="left" >&nbsp;&nbsp; &nbsp;&nbsp;课程描述:${teacherCourse.courseDesc }&nbsp;&nbsp; &nbsp;&nbsp;</div>
		  <br/>
		</c:forEach>
	</div>
	</div>
</div>