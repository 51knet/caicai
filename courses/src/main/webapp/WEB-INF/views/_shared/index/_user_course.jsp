<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
	.container .user-course{
		 background-image: url("<c:url value='/resources/img/default/carousel_bg.png'></c:url>");
		  height: 360px;
		  background-position: top center;
		  background-repeat: repeat-x;
		  text-align: center;
	}
	.bb{
		border-bottom: solid 1px #ccc;
	}
	.user-title{
		margin-left: 50px; 
		font-size: 18px;
		 font-weight: bold;
	}
</style>
<c:choose>
	<c:when test="${sessionScope.sessionUserInfo!= null}">
		<div class="carouselbg" style="text-align: left;">
			<c:choose>
				<c:when test="${userCourseCount>0}">
				<div style="margin-left: 10px; margin-right: 10px;">
				<table cellpadding="14" style="width: 100%; "  >
					<tr class="bb"><td colspan="3" align="left"><span class="user-title">我的课程</span></td></tr>
			 		 <c:forEach items="${userCourse}" var="course"  begin="0" end="1">
						<tr  >
							<td  width="24%" align="right"  valign="top">
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
								<div style="width:370px; margin-top: 10px;" id="contentlimit">
								<b style="font-size: 17px;">课程名称：${course.courseName}</b><br><br>课程类别：${course.courseType }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }</div>
							</td>	
							<td width="38%" align="left"  valign="top">
								<div style="width: 100%;height: 100%;">
									<div style="float: left; height: 100px; width: 140px; text-align: center;">
										<c:choose>
											<c:when test="${course.teacher.user.photo_url != null && course.teacher.user.photo_url != ''}">
												<a href='<c:url value="/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 100px; height: 100px;" />
												</a>
											</c:when>
											<c:otherwise>
												<a href='<c:url value="/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar90.png"></c:url>' style="width: 100px; height: 100px;" />
												</a>
											</c:otherwise>
										</c:choose>
									</div>
									<c:if test="${course.teacher.isEnterprise == null}">
										教师名称：${course.teacher.user.name } <br>所在学校：${course.teacher.college }<br>专业技术：${course.teacher.major }<br>
									</c:if>
									<c:if test="${course.teacher.isEnterprise != null}">
										企业名称：${course.teacher.user.name } <br><br>
									</c:if>
									<a style="margin-top: 10px;" href='<c:url value="/course/study/view/${course.id}"></c:url>' class="btn  btn-success">点击学习</a>
								</div>
							</td>
						</tr>
			    </c:forEach>
			   </table>
	    	</div>
	    	</c:when>
			<c:otherwise>
				<div style="text-align: center; padding-top: 30px;">
					<a href='<c:url value="/course/list/type?detail=all"></c:url>'><img  src='<c:url value="/resources/img/default/index/no_course.png"></c:url>' ></a>
					<img  src='<c:url value="/resources/img/default/index/find.png"></c:url>' >
	    		</div>
			</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	<c:otherwise>
		<div class="carouselbg">
			<div id="myCarousel" class="carousel slide">
				  <div class="carousel-inner" >
				    <div class="item">
				      <img src="<c:url value='/resources/img/advertise/slide3.png'></c:url>" alt="">
				    </div>
				    <div class="item">
				      <img src="<c:url value='/resources/img/advertise/slide1.png'></c:url>" alt="">
				    </div>
				    <div class="item active">
				      <img src="<c:url value='/resources/img/advertise/slide2.png'></c:url>" alt="">
				    </div>
			  </div>
			   <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
			  <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>	
