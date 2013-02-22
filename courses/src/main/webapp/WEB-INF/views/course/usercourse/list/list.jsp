<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
		<div class="container user-course">
			<c:choose>
				<c:when test="${userCourseCount>0}">
				<div class="row" style="text-align:left; margin-left: 0px;">
					<h2>您的课程(${userCourseCount})</h2>
				 	 <c:forEach items="${userCourse}" var="course"  >
				    	<table class="table table-bordered" style="width: 97%; height: 100%; margin-bottom: 10px;" cellpadding="5">
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
									<div style="width:400px;" id="contentlimit">课程名称：${course.courseName}<br>课程类别：${course.courseType }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }</div>
								</td>				
								<td width="35%" align="left">
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
										教师名称：${course.teacher.user.name } <br>所在学校：${course.teacher.college }<br>
										<a style="margin-top: 10px;" href='<c:url value="/course/study/view/${course.id}"></c:url>' class="btn  btn-success">点击学习</a>
									</div>
								</td>
							</tr>
						</table>
				    </c:forEach>
	    		</div></c:when>
				<c:otherwise>
					<div class="row" style="text-align:left; margin-left: 0px; margin-top: 10px;">
				    	<table class="table table-bordered" style="width: 97%; height: 100%;" cellpadding="5">
							<tr>
								<td valign="top"  align="left">
									<h3>您尚未学习任何课程</h3>
									<a href='<c:url value="/course/list/type?detail=all"></c:url>' class="btn btn-large btn-success">点击开始学习</a>
								</td>
							</tr>
						</table>
		    		</div>
				</c:otherwise>
			</c:choose>
		</div>

