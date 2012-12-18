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
	<div class="row">
		<h5>教师资源</h5>
	</div>
	<div>
		<table class="table">
		  <tr>
		    <td rowspan="2">&nbsp;${teacher.user.photo_url}</td>
		    <td >&nbsp;&nbsp;姓名:&nbsp;&nbsp;${teacher.user.name }</td><td >&nbsp;&nbsp;性别:&nbsp;&nbsp;${teacher.user.gender }</td><td>&nbsp;&nbsp;部门: &nbsp;&nbsp;${teacher.teaching_subject}&nbsp;&nbsp;</td>
		  </tr>
		 	<br/>
		  	<tr>
		    <td>&nbsp;&nbsp;所属高校:${teacher.college }&nbsp;&nbsp;</td><td>&nbsp;&nbsp;所属院系:${teacher.school}&nbsp;&nbsp;</td>
		    </tr>
		    <br/>
		    <tr>
		    <td>教授课程:${teacher.major}&nbsp;&nbsp;</td><td>&nbsp;&nbsp;职称:${teacher.title}&nbsp;&nbsp;</td><td>&nbsp;&nbsp;导师类别:${teacher.role}&nbsp;&nbsp;</td>
		  </tr>
	</table>
	<div>
	<jsp:include page="/WEB-INF/views/teacher/teacherShop/courses.jsp"></jsp:include></div>
	</div>
</div>