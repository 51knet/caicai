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
  background-image: url(resources/img/avatar/avatar40.png );
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
<jsp:include page="/WEB-INF/views/_shared/index/_user_course.jsp"></jsp:include>
<div class="container teacher">
	<c:choose>
	<c:when test="${isEnterPrise=='null'}">
	<h2>热门教师（Top 50）</h2>
   	<div class="row">
      	<c:forEach items="${teacherList}" var="t">
			<div class="span2" style="width: 175px;height: 200px;">
			<c:if test="${!t.user.name.equals('')}">
			<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }
				</a>
			</c:if>
			<c:if test="${t.user.name.equals('')}">
			<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>无名氏
			</a>
			</c:if>
			<c:choose>
			<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
			<img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 175px; height:180px;margin-bottom: 10px;" />
			</c:when>
			<c:otherwise>
			<img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 175px; height:180px;margin-bottom: 10px;" />
			</c:otherwise>
			</c:choose>
			</div>
		</c:forEach>
	 </div>
	</c:when>
	<c:otherwise>
	<h2>热门企业（Top 50）</h2>
   	<div class="row">
      	<c:forEach items="${enterPriseList}" var="t" >
				<div class="span2" style="width: 175px;height: 200px;">
				<c:if test="${!t.user.name.equals('')}">
						<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }
							</a>
						</c:if>
						<c:if test="${t.user.name.equals('')}">
						<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>无名氏
						</a>
						</c:if>
					<c:choose>
						<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
						<img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 175px; height:180px;margin-bottom: 10px;" />
						</c:when>
						<c:otherwise>
						<img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 175px; height:180px;margin-bottom: 10px;" />
						</c:otherwise>
						</c:choose>
				</div>
			</c:forEach>    	
	 </div>
	</c:otherwise>
	</c:choose>
	 <div class="row"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp" ></jsp:include></div>
</div>



