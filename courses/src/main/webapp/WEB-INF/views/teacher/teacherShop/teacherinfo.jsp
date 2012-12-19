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
		<h5>讲师介绍</h5>
	</div>
<div align="center">
<table class="table" style="width:65%;">
  <tr>
    <td  style="width:20%"  valign="middle" bordercolor="#F0F0F0" bgcolor="#CCCCCC"><h1><span class="STYLE1"> 讲师介绍</span></h1>
    </td>
  </tr>
  <tr>
    <td rowspan="3" valign="bottom" width="20%">
    <c:choose>
		<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
			<img src='<c:url value="${teacher.user.photo_url }"></c:url>'/>
		</c:when>
		<c:otherwise>
			 <img src='<c:url value="/resources/img/logo.png"></c:url>'/>
		</c:otherwise>
	</c:choose>
	</td>
    <td>姓名:${teacher.user.name }</td>
    <td>性别:${teacher.user.gender }</td>
    <td>所属高校:${teacher.college }</td>
  </tr>
  <tr>
    <td>所属高校:${teacher.college }</td>
    <td>所属院系:${teacher.school}</td>
    <td>教授课程:${teacher.major}</td>
  </tr>
  <tr>
    <td>职称:${teacher.title}</td>
    <td>导师类别:${teacher.role}</td>
    <td></td>
  </tr>
</table>
<div >
<jsp:include page="/WEB-INF/views/teacher/teacherShop/courses.jsp"></jsp:include>
</div>
</div>
</div>