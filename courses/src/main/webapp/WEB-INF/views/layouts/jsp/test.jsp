<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.navbar {
	margin-bottom: 0px;
}
/* CUSTOMIZE THE NAVBAR
-------------------------------------------------- */
    
.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}
/* Carousel base class */
.carousel {
  margin-bottom: 60px;
}

.carousel .container {
  position: absolute;
  right: 0;
  bottom: 0;
  left: 0;
}

.carousel-control {
  background-color: transparent;
  border: 0;
  font-size: 120px;
  margin-top: 0;
  text-shadow: 0 1px 1px rgba(0,0,0,.4);
}

.carousel .item {
  height: 300px;
}
#myCarousel .carousel img {
  min-width: 100%;
  height: 300px;
}

.carousel-caption {
  background-color: transparent;
  position: static;
  max-width: 550px;
  padding: 0 40px;
  margin-bottom: 100px;
}
.carousel-caption h1,
.carousel-caption .lead {
  margin: 0;
  line-height: 1.25;
  color: #fff;
  text-shadow: 0 1px 1px rgba(0,0,0,.4);
}
.carousel-caption .btn {
  margin-top: 10px;
}
/* MARKETING CONTENT
-------------------------------------------------- */
.container.marketing {
  width: 1024px;
  max-width:1024px;
}
.container.marketing .row {
  margin-left: 0px;
}
.row .span4 {
width: 310px;
margin: 12px 30px 24px 0px;
-webkit-box-shadow: #999 0px 1px 2px 0px;
box-shadow: #999 0px 1px 2px 0px;
border-top-width: 1px;
border-top-style: solid;
border-top-color: #EEE;
background: #F7F7F7;
}

.container.university {
  width: 1024px;
  max-width:1024px;
  margin-bottom: 0px;
}
.container.university .row {
  margin-left: 0px;
}


.container.mycourse {
  width: 1024px;
  max-width:1024px; 
}

.container.mycourse .row {
  margin-left: 0px;
}

</style>

<script type="text/javascript">
!function ($) {
  $(function(){
    // carousel demo
    $('#myCarousel').carousel();
    $('#myUniversity').carousel();
    $('#myTeacher').carousel();
  });
}(window.jQuery);
</script>
<div class="container mycourse">
	<div class="row" style="text-align:left; margin-left: 0px;">
		<c:choose>
			<c:when test="${sessionScope.userInfo != null}">
				<h2>您的课程</h2>
			  <c:forEach items="${userCourse}" var="course"  begin="0" end="0">
			    	<table class="table table-bordered" style="width: 97%; height: 100%;" cellpadding="5">
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
							<td>课程名称：${course.courseName}<br>课程类别：${course.courseType }<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }
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
			    </c:forEach>
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
	</div>
</div>