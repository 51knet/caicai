<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar {
	margin-bottom: 0px;
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}



.container.course {
	width: 990px;
	max-width:990px;
	text-align: left;
}

.container.course .row{
	margin-left:0px;;
}

.container.course.detail {
	width: 900px;
	height: 110px;
}
</style>

<script type="text/javascript">
	function selectType(id) {
		var sel = document.getElementById("type");
		var opt = sel.options;
		for ( var i = 0; i < opt.length; i++) {
			if (opt[i].selected) {
				var typeName = opt[i].innerHTML;
				//alert(typeName);
				window.location.href = '<c:url value="/teacher/'+id+'/course/type?detail='
						+ typeName + '"></c:url>';
			}
		}
	}
</script>
<!-- 
<div class="navbar">
	<div class="navbar-inner">
		<div class="container" style="text-align: center; margin-left: 80px; margin-right: 80px; padding: 10px 10px;">
			
			<form class="navbar-form">
				<input type="text" class="span6" placeholder="搜索教师、课程、学校">
				<button type="submit" class="btn btn-success">搜索</button>
			</form>
		</div>
	</div>
</div>
 -->
<div class="container course" style="margin-bottom: 10px; margin-top: 10px;">
	<div class="container course row">
	<div class="container course" style="background-color: #f7f7f7; height: 40px;">
		<div style="padding: 5px; margin-left: 30px;">
			<select id="type" onchange="selectType(${teacher.id })">
				<option>全部课程</option>
				<c:forEach items="${courseTypeList}" var="course">
					<c:choose>
						<c:when test="${courseType == course}">
							<option selected>${course}</option>
						</c:when>
						<c:otherwise>
							<option value="">${course}</option>
						</c:otherwise>
					</c:choose> 
				</c:forEach>
			</select>
		</div>
	</div>
	<h4 style="margin-left: 35px;">课程数（${courseCount }）</h4>

	<c:forEach items="${teacherCourseList}" var="course">
		<div class="container course detail" style="margin-bottom: 15px; border: 0px solid #cccccc;">
			<table class="table table-bordered" style="width: 95%; height: 100%;" cellpadding="5">
				<tr>
					<td valign="bottom" width="22%" align="center">
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 200px; height: 100px;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 200px; height: 100px;" />
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<div style="width:450px;" id="contentlimit">课程名称：${course.courseName}<br>课程类别：${course.courseType }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }</div>
					</td>
					<td width="25%">
						<a style="margin-top: 20px; margin-left: 20px;" href='<c:url value="/course/view/${course.id}"></c:url>' class="btn btn-large  btn-success"  >课程详情</a>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
	</div>
</div>