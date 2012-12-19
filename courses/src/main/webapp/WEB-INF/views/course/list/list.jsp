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
	width: 1000px;
	text-align: left;
}

.container.course.detail {
	width: 990px;
	height: 110px;
}
</style>

<script type="text/javascript">
	function selectType() {
		var sel = document.getElementById("type");
		var opt = sel.options;
		for ( var i = 0; i < opt.length; i++) {
			if (opt[i].selected) {
				var typeName = opt[i].innerHTML;
				//alert(typeName);
				window.location.href = '<c:url value="/course/list/type?detail='
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
				<button type="submit" class="btn btn-primary">搜索</button>
			</form>
		</div>
	</div>
</div>
 -->
<div class="container course" style="margin-bottom: 20px; margin-top: 20px;">
	<div class="container course detail" style="background-color: #efefef; height: 40px;">
		<div style="padding: 5px;">
			<select id="type" onchange="selectType()">
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
	<h2>课程数（${courseCount }）</h2>

	<div style="text-align: center;">
		<c:forEach items="${courseList}" var="course">
			<div class="container course detail" style="margin-bottom: 15px; border: 0px solid #cccccc;">
				<table class="table table-bordered" style="width: 100%; height: 100%;" cellpadding="5">
					<tr>
						<td valign="bottom" width="22%" align="center">
							<c:choose>
								<c:when test="${course.courseCover != null && course.courseCover != ''}">
									<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="http://localhost:8080/ccweb/${course.courseCover }"></c:url>' style="width: 200px; height: 100px;" />
									</a>
								</c:when>
								<c:otherwise>
									<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 200px; height: 100px;" />
									</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<div style="width:400px;" id="contentlimit">课程名称：${course.courseName}<br>课程类别：${course.courseType }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }</div>
						</td>
						<td width="35%" align="left">
							<div style="width: 100%;height: 100%;">
								<div style="float: left; height: 100px; width: 120px;">
									<c:choose>
										<c:when test="${course.teacher.user.photo_url != null && course.teacher.user.photo_url != ''}">
											<a href='<c:url value="/course/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="http://localhost:8080/ccweb/${course.teacher.user.photo_url }"></c:url>' style="width: 100px; height: 100px;" />
											</a>
										</c:when>
										<c:otherwise>
											<a href='<c:url value="/course/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar90.png"></c:url>' style="width: 100px; height: 100px;" />
											</a>
										</c:otherwise>
									</c:choose>
								</div>
								<div style="float: left; height: 100px; ">教师名称：${course.teacher.user.name } <br>所在学校：${course.teacher.college }</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
</div>



