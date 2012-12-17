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


.container.university {
  width: 1024px;
  max-width:1024px;
  margin-bottom: 0px;
}
.container.university .row {
  margin-left: 0px;
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

<div class="container course" style="margin-bottom: 20px; margin-top: 20px; ">
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
						<td valign="bottom" width="22%" >
							<c:choose>
								<c:when test="${course.courseCover != null && course.courseCover != ''}">
									 <a href='<c:url value="/course/view/${course.id}"></c:url>'>
									 	<img src='<c:url value="http://localhost:8080/ccweb/${course.courseCover }"></c:url>'  style="width: 200px; height: 100px;" />
									  </a> 
								</c:when>
								<c:otherwise>
									 <a href='<c:url value="/course/view/${course.id}"></c:url>'>
									 	<img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 200px; height: 100px;"/> 
									 </a> 
								</c:otherwise>
							</c:choose>
							
						</td>
						<td > 课程名称：${course.courseName}<br>课程描述：${course.courseDesc }<br>发布时间：${course.courseDate }</td>
						<td width="25%">教师名称：${course.teacher.user.name }
									<br>
									所在学校：${course.teacher.college }
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	  </div>
</div>



