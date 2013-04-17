<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
	.detail{
		padding-top: 30px;
		padding-left: 40px;
		padding-bottom: 20px;
	}
	
	.detail .content{
		margin-left: 30px;
	}
</style>
<div class="container teacher">
	<div class="detail" >
		<div>
		    <c:choose >
				<c:when test="${userInfo.photo_url != null && userInfo.photo_url != ''}">
					<img src='<c:url value="${url }${userInfo.photo_url }"> </c:url>'style="width: 112px;height:112px; float:left; margin-left:30px"/>
				</c:when>
				<c:otherwise>
					 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'style="width: 112px;height: 112px; float:left; margin-left:30px"/>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="width:500px; margin-left: 20px; float: left;" >
		    <table>
			    <tr> <td style="width:150px;"><h4>${userInfo.name }</h4></td></tr>
				<tr><td>${userInfo.email }</td> </tr>
		    </table>
	  	</div>
   </div>
</div>
<br>
<div class="container title"  >
 	 <table >
 	 	<tr>
 	 		<td width="20%" align="center"><h4>课程总数（${userCourseCount}）</h4></td>
 	 		<td></td>
 	 	</tr>
 	 </table>
 </div>
<div class="container user-course" >
	<c:if test="${userCourseCount<= 0}">
		<h4 style="margin-left: 70px;">尚未学习课程</h4>
		<a href='<c:url value="/course/list/type?detail=all"></c:url>' class="btn  btn-success">点击开始学习</a>
	</c:if>
	<div style="height: 20px;"></div>
 	<c:forEach items="${userCourse}" var="course">
			<table cellpadding="10" style="width: 430px;   margin-bottom: 10px;  float: left; margin-left: 50px;"  >
				<tr >
				<td  align="right"  valign="top">
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 160px; height: 120px;float: right;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 160px; height: 120px; float: right;" />
								</a>
							</c:otherwise>
						</c:choose>
				</td>
				<td valign="top">
					<div style="width:230px; line-height: 23px;" id="contentlimit">
						<b style="font-size: 17px;">${course.courseName}</b><br>
						课程类别：${course.cType.typeName }<br>
						发布日期：${course.courseDate }<br>
						发布人：${course.user.name } <br>
						<a  href='<c:url value="/course/study/view/${course.id}"></c:url>' class="btn  btn-success">点击学习</a>
					</div>
				</td>
				</tr>
			</table>
	</c:forEach>
 </div>