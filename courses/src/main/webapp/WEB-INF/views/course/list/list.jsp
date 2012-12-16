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


.container.course{
	width:1000px;
	text-align: left;
}

.container.course.detail{
	width:990px;
	height:110px;
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


function DrawImage(ImgD){ 
	flag = false;	
	var image=new Image(); 
	image.src=ImgD.src; 
	if(image.width>0 && image.height>0){ 
	  flag=true; 
	  if(image.width/image.height>= 220/90){ 
	   if(image.width>220){
	    ImgD.width=220; 
	    ImgD.height=(image.height*90)/image.width; 
	   }else{ 
	    ImgD.width=image.width;
	    ImgD.height=image.height; 
	   } 
	   /*ImgD.alt="bigpic"  */
	  } 
	  else{ 
	   if(image.height>90){
	    ImgD.height=90; 
	    ImgD.width=(image.width*90)/image.height; 
	   }else{ 
	    ImgD.width=image.width;
	    ImgD.height=image.height; 
	   } 
	    /*ImgD.alt="bigpic"  */ 
	  } 
	}
}

function selectType(){
	var sel = document.getElementById("type");
	var opt = sel.options;
	for(var i=0;i<opt.length;i++){
		if(opt[i].selected){
			var typeName = opt[i].innerHTML;
			//alert(typeName);
			window.location.href='<c:url value="/course/list/type?detail='+typeName+'"></c:url>';
		}
	}
}
</script>
<div class="navbar">
	<div class="navbar-inner">    
     <div class="container" style="text-align: center;margin-left: 80px; margin-right: 80px;padding: 10px 10px;"><br><br>
           <form class="navbar-form" >
             <input type="text" class="span6" placeholder="搜索教师、课程、学校">
             <button type="submit" class="btn btn-primary">搜索</button>
           </form>
		</div>
	</div>
</div>
 
<div id="myCarousel" class="carousel slide">
  <div class="carousel-inner">
    <div class="item">
      <img  src='<c:url value="/resources/img/advertise/slide-01.jpg"></c:url>' alt="">
      <div class="container">
        <div class="carousel-caption">
          <h1>Another example headline.</h1>
          <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
          <a class="btn btn-large btn-primary" href="#">Learn more</a>
        </div>
      </div>
    </div>
    <div class="item">
      <img src='<c:url value="/resources/img/advertise/slide-02.jpg"></c:url>' alt="">
      <div class="container">
        <div class="carousel-caption">
          <h1>Another example headline.</h1>
          <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
          <a class="btn btn-large btn-primary" href="#">Learn more</a>
        </div>
      </div>
    </div>
    <div class="item active">
      <img src='<c:url value="/resources/img/advertise/slide-03.jpg"></c:url>'alt="">
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
 
     	




<div class="container course" style="margin-bottom: 20px; ">
	<div class="container course detail"  style="background-color: #efefef; height: 40px;">
		<div style="padding: 5px;">
			<select  id="type" onchange="selectType()">
				<option>全部课程</option>
				<c:forEach items="${courseTypeList}"  var="course">
					<c:choose>
						<c:when test="${courseType == course}">
							<option selected>${course}</option>
						</c:when>
						<c:otherwise>
							<option value="">${course}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
	</div>
    <h2>课程数（${courseCount }）</h2>
 
	  <div  style="text-align: center;">
		<c:forEach items="${courseList}" var="course">
			<div class="container course detail" style=" margin-bottom: 15px; border:0px solid #cccccc;">
				<table class="table table-bordered" style="width: 100%;height: 100%;" cellpadding="5">
					<tr>
						<td valign="bottom" width="20%" background='<c:url value="http://localhost:8080/${course.courseCover }"></c:url>'>
							<div style="width:100%; height: 100%; vertical-align: bottom;"></div>
							<!-- <img src='<c:url value="http://localhost:8080/${course.courseCover }"></c:url>'  width="200px" height="90px"  /> -->
						</td>
						<td > 课程名称：${course.courseName}<br>课程描述：${course.courseDesc }<br><br>
						<a href="<c:url value="/admin/teacherCourse/course/view/${course.id}"></c:url>">点击查看详细</a>
						</td>
						<td width="25%">教师名称：${course.teacher.user.name }</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	  </div>
</div>



