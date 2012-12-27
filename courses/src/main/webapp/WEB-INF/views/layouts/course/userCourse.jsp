<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>

.container.mycourse {
  width: 1024px;
  max-width:1024px; 
}

.container.mycourse .row {
  margin-left: 0px;
}

</style>


<c:choose>
	<c:when test="${sessionScope.userInfo != null}">
		<div class="container mycourse">
			<c:choose>
				<c:when test="${userCourseCount>0}">
				<div class="row" style="text-align:left; margin-left: 0px;">
					<h2>您的课程</h2>
				 	 <c:forEach items="${userCourse}" var="course"  begin="0" end="2">
				    	<table class="table table-bordered" style="width: 97%; height: 100%; margin-bottom: 10px;" cellpadding="5">
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
										<div style="float: left; height: 100px; width: 140px; text-align: center;">
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
										教师名称：${course.teacher.user.name } <br>所在学校：${course.teacher.college }<br>
										<a style="margin-top: 10px;" href='<c:url value="/teachercourse/course/view/${course.id}"></c:url>' class="btn  btn-success">点击学习</a>
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
	</c:when>
	<c:otherwise>
		<div id="myCarousel" class="carousel slide">
		  <div class="carousel-inner">
		    <div class="item">
		      <img src="resources/img/advertise/slide-01.jpg" alt="">
		      <div class="container">
		        <div class="carousel-caption">
		          <h1>Another example headline.</h1>
		          <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
		          <a class="btn btn-large btn-primary" href="#">Learn more</a>
		        </div>
		      </div>
		    </div>
		    <div class="item">
		      <img src="resources/img/advertise/slide-02.jpg" alt="">
		      <div class="container">
		        <div class="carousel-caption">
		          <h1>Another example headline.</h1>
		          <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
		          <a class="btn btn-large btn-primary" href="#">Learn more</a>
		        </div>
		      </div>
		    </div>
		    <div class="item active">
		      <img src="resources/img/advertise/slide-03.jpg" alt="">
		      <div class="container">
		        <div class="carousel-caption">
		          <h1>One more for good measure.</h1>
		          <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
		          <a class="btn btn-large btn-primary" href="#">Browse gallery</a>
		        </div>
		      </div>
		    </div>
		  </div>
		  <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
		  <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
		</div>
	</c:otherwise>
</c:choose>	
