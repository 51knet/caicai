<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
.container.university .row .span3{
  background-image: url(resources/img/ust/ust_logo_160x60.png);
  background-position: center;
  background-repeat: no-repeat;
  height: 80px;
  -webkit-box-shadow: #999 0px 1px 2px 0px;
  box-shadow: #999 0px 1px 2px 0px;
  border-top-width: 1px;
  border-top-style: solid;
  border-top-color: #EEE;
  margin: 2px 2px;
}

.container.teacher {
  width: 1024px;
  max-width:1024px; 
}
#myUniversity, #myTeacher {
  margin-bottom: 0px;
}
.container.teacher .row {
  margin-left: 0px;
}
.container.teacher .row .span1{
  background-image: url(resources/img/avatar/avatar40.png);
  background-position: center;
  background-repeat: no-repeat;
  height: 80px;
  -webkit-box-shadow: #999 0px 1px 2px 0px;
  box-shadow: #999 0px 1px 2px 0px;
  border-top-width: 1px;
  border-top-style: solid;
  border-top-color: #EEE;
  margin: 2px 2px;
  margin-right: 18px;
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
<!-- 
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
  -->    	
<div class="container marketing">
  <h2>教学资源（${courseCount }）</h2>
  <!-- Three columns of text below the carousel -->
  <div class="row" >
    <!-- <div class="span4">
      <a href="#"><img src="resources/img/topics/240x135_1.png" width="310px"></a> 
      <h4>宇宙的形成</h4>
      <h5>杜克大学</h5>
      <p><span>更新至第二集</span></p>
    </div>
    <div class="span4">
      <a href="#"><img src="resources/img/topics/240x135_2.png" width="324px"></a> 
      <h4>药物和大脑111</h4>
      <h5>加州科技大学</h5>
      <p><span>更新至第四集</span></p>
    </div>
    <div class="span4">
      <a href="#"><img src="resources/img/topics/240x135_3.png" width="324px"></a>
      <h4>如何解释和争论</h4>
      <h5>杜克大学</h5>
      <p><span>更新至第八集</span></p>
    </div>
     -->
    <c:forEach items="${courseList}" var="c"  begin="0" end="2">
    	<div class="span4">
    		<div>
   				<c:choose>
					<c:when test="${c.teacherCourse.courseCover != null && c.teacherCourse.courseCover != ''}">
						<a href='<c:url value="/course/view/${c.teacherCourse.id}"></c:url>'> <img src='<c:url value="http://localhost:8080/ccweb/${c.teacherCourse.courseCover }"></c:url>' style="width: 310px; height: 100px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${c.teacherCourse.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 310px; height: 100px;" />
						</a>
					</c:otherwise>
				</c:choose>
    		</div>
    		<div style="padding: 3px;"><b>${c.teacherCourse.courseName}</b> — ${c.teacherCourse.courseType }</div>
    		<div style="padding: 3px;">
    			教师名称：<a href='<c:url value="/teacher/${c.teacherCourse.teacher.id}"></c:url>'>${c.teacherCourse.teacher.user.name}</a>
    		</div>
    		<div style="padding: 3px;">
    			学习人数：${c.userCount}人&nbsp;&nbsp;&nbsp;课程评分：
    			<c:choose>
    				<c:when test="${c.courseMark !=null && c.courseMark>0 }">
    					<fmt:formatNumber type="number" value="${c.courseMark }" maxFractionDigits="0" />分
    				</c:when>
    				<c:otherwise>
    					0分
    				</c:otherwise>
    			</c:choose>
    			&nbsp;&nbsp;&nbsp;<span style="color: red;">￥ 20</span>
    		</div>
    	</div>
    </c:forEach>
  </div><!-- /.row -->
  <div class="row" >
  	<div style="margin-right: 35px;padding: 5px 5px;background: #F7F7F7;text-align: center;">
  		<a href='<c:url value="/course/list/type?detail=all"></c:url>' >全部课程</a>
  	</div>
  </div>

</div>



<div class="container teacher">
  <h2>热门教师（Top 50）</h2>
  <div id="myTeacher" class="carousel slide">
  <div class="carousel-inner">
    <div class="item active">
	      <div class="row">
	      	<c:forEach items="${teacherList}" var="t" begin="0" end="12"><div class="span1" ><a href="http://localhost:8080/ccweb/teacher/${t.id}">${t.user.name }</a></div></c:forEach>      	
	      </div>
	     
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach>  
	      </div>
    </div>
    <div class="item">
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach> 
	      </div>
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach>  
	      </div>
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach>  
	      </div>
    </div>
 	<div class="item">
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach>  
	      </div>
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach>  
	      </div>
	      <div class="row">
	      	<c:forEach begin="1" end="12"><div class="span1"></div></c:forEach>  
	      </div> 
    </div>
  </div>
   <div class="row" >
  	<div style="margin-right: 35px;padding: 5px 5px;background: #F7F7F7;text-align: center;">
  		<a href='<c:url value="/teacher/list"></c:url>' >全部教师</a>
  	</div>
  
  </div>
  <div style="display: none;">
	  <a class="left carousel-control" href="#myTeacher" data-slide="prev">‹</a>
	  <a class="right carousel-control" href="#myTeacher" data-slide="next">›</a>
  </div>
</div>

</div>


<div class="container university">
  <h2>企业（318）</h2>
  <div id="myUniversity" class="carousel slide">
  <div class="carousel-inner">
    <div class="item active">
     	<div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
	    <div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
	    <div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
    </div>
    <div class="item">
     	<div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
	    <div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div><div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
    </div>
    <div class="item">
		<div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
	    <div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div><div class="row">
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    	<div class="span3"></div>
	    </div>
    </div>
  </div>
  <div style="display: none;">
  <a class="left carousel-control" href="#myUniversity" data-slide="prev">‹</a>
  <a class="right carousel-control" href="#myUniversity" data-slide="next">›</a>
  </div>
</div>

</div>
