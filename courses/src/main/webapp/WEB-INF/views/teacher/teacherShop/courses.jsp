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
<br>
<div class="container title"  >
 	 <table >
 	 	<tr>
 	 		<td width="20%" align="center"><h4>所有课程（${courseCount}）</h4></td>
 	 		<td></td>
 	 	</tr>
 	 </table>
 </div>
<div class="container user-course" style="height: 500px;">
 	<c:forEach items="${teacherCourseList}" var="course">
			<table cellpadding="20" style="width: 40%;   margin-bottom: 10px;  float: left; margin-left: 50px;"  >
				<tr >
				<td  width="24%" align="right"  valign="top">
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 162px; height: 120px;float: right;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 162px; height: 120px; float: right;" />
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td valign="top">
						<div style="width:200px;" id="contentlimit">
						<b style="font-size: 17px;">${course.courseName}</b><br>
						类别：${course.courseType }<br>
						描述：${course.courseDesc }<br>
						日期：${course.courseDate }</div>
					</td>
				</tr>
			</table>
	</c:forEach>
 </div>