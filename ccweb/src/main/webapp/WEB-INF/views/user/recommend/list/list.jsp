<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.centralize {
	text-align: center;
	margin-bottom: 20px;
	padding: 0px 0px 10px;
 	/*background-image:url('<c:url value='/resources/img/default/admin_left_bg.png'></c:url>');*/
 	/*background-color:#ccdfa8;*/
 	background-color:#fff;
 	background-position:top center;
 	background-repeat:repeat-y;
	vertical-align: middle;
}
.left-menu-container {
background-color: #fff;

}

.border{
		background-color: #fff; border: 1.5px solid #eed593;
	}
</style>
<!--<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url> -->
<div class="row-fluid centralize border" style="height: 400px;">
	<br>
	<a href='<c:url value="/admin/trend"></c:url>' >换一换</a><hr>
	<div class="row-fluid">
		推荐好友<br>
		<c:forEach items="${recommendUser }" var="recommendUser">
			${recommendUser.name }-
		</c:forEach>
	</div>
	<hr>
	<div class="row-fluid">
		推荐教师<br>
		<c:forEach items="${recommendTeacher }" var="recommendTeacher">
			${recommendTeacher.name }- 
		</c:forEach>
	</div>
	<hr>
	<div class="row-fluid">
		推荐课程<br>
		<c:forEach items="${recommendCourse}" var="recommendCourse">
			${recommendCourse.courseName }- 
		</c:forEach>
	</div>
</div>

