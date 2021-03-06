<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.centralize.right {
	text-align: center;
	margin-bottom: 20px;
	padding: 0px 0px 10px;
 	background-color:#fff;
 	background-position:top center;
 	background-repeat:repeat-y;
	vertical-align: middle;
}
.row-fluid.centralize.right .row{
	background-image: url("<c:url value='/resources/img/default/blackline.png'></c:url>");
	background-position: center center;
	background-repeat: repeat-x;
	height: 20px;
	width: 98%;
	margin: 10px 2px ;
	text-align: center;
}
.row-fluid.centralize.right .row >div{
	color: #666;
	font-weight: bold;
	font-size: 14px;
	width: 80px;
	height:100%;
	background-color: #fff;
	margin-left: 0px;
}

.row-fluid.centralize.right .avator_width{
	width: 60px;
	height:60px;
	border: 1px solid #ccc; 
}

.row-fluid.centralize.right .courseCover{
 width:120px;
 border: 1px dashed #ccc;
}

.row-fluid .content{
	margin: 10px 10px;
}
</style>
<!--<c:url var="avatar_url" value="${sessionUserInfo.avatar}"></c:url> -->
<div class="row-fluid centralize right border-green-all" ><br>
	<c:if test="${view == 'view' }">
		<a href='<c:url value="/user/${userInfo.id }/trend/view/${trendBeans.trend.id }"></c:url>' >换一换</a>
	</c:if>
	<c:if test="${view != 'view' }">
		<a href='<c:url value="/id/${userInfo.id }"></c:url>' >换一换</a>
	</c:if>
	<div class="row">
		<div>推荐好友</div>
	</div>
	<div class="row-fluid">
		<c:forEach items="${recommendUser }" var="recommendUser">
			<table width="28%"  cellpadding="0" style="margin: 5px 5px 5px 7px; float: left;">			  
				  <tr>
				    <td><a href='<c:url value="/id/${recommendUser.id }"></c:url>' ><img src=' <c:url value="${recommendUser.photo_url }"></c:url>'  class="avator_width"></a></td>			
				  </tr>
				  <tr>
				    <td align="center"><div style="width: 70px;" id="content">${recommendUser.name }<c:if test="${recommendUser.name == null || recommendUser.name == '' }">未命名</c:if></div></td>
				  </tr>
				   <tr>
				    <td ><a href='<c:url value="/id/${recommendUser.id }"></c:url>' ><img src="<c:url value='/resources/img/default/focus.png'></c:url>" ></a></td>
				  </tr>
			</table>
		</c:forEach>
	</div>
	
	<div class="row">
		<div>推荐教师</div>
	</div>
	<div class="row-fluid">
		<c:forEach items="${recommendTeacher }" var="recommendTeacher">
			<table width="28%"  cellpadding="0" style="margin: 5px 5px 5px 7px; float: left;">			  
				   <tr>
				    <td><a href='<c:url value="/id/${recommendTeacher.id }"></c:url>' ><img src=' <c:url value="${recommendTeacher.photo_url }"></c:url>'  class="avator_width"></a></td>			
				  </tr>
				  <tr>
				    <td align="center"><div style="width: 70px;" id="content">${recommendTeacher.name }</div></td>
				  </tr>
				   <tr>
				    <td ><a href='<c:url value="/id/${recommendTeacher.id }"></c:url>' ><img src="<c:url value='/resources/img/default/focus.png'></c:url>" ></a></td>
				  </tr>
			</table>
		</c:forEach>
	</div>

	<div class="row">
		<div>推荐课程</div>
	</div>
	<div class="row-fluid">
		<c:forEach items="${recommendCourse}" var="recommendCourse">
			<div class="content">
				<a href="<c:url value="/trend/course/${recommendCourse.user.id}"></c:url>" ><img src="<c:url value='${recommendCourse.courseCover}'></c:url>" class="courseCover"></a><br>
				${recommendCourse.courseName }
			</div>
		</c:forEach>
	
	</div>
</div>

