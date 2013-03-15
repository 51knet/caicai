<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.basic {
	margin-left:5px;
	float: left;
}
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
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
</style>
<div  class="row-fluid custom round" style="height: 250px;">
	<div class="row">
		<h4>取消课程</h4>
	</div>
	<div class="row1">
		<form id="deleteCourseForm" action="<c:url value="/admin/teacher/course/edit/deletecoursemodify"></c:url>">
		<input type="hidden" name="courseId" value="${course.id}">
			<div style="margin-left: 40px; color: red;">
					<label>如果你删除了本课程，本课程将移入回收站。请确认后再删除。</label> 
				<span class="row-fluid custom price power"><br>
					<button class="btn  btn-success">删除</button>
				</span>
			</div>
		</form>
	</div>
</div>