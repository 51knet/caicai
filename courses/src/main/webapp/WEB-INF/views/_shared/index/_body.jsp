<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
  	
<div class="container marketing">
  <div class="container title">
 	 <table width="100%">
 	 	<tr>
 	 		<td width="16%" align="center"><h4>学习资源</h4></td>
 	 		<td><a  href='<c:url value="/course/list/type?detail=all"></c:url>' >全部课程</a></td>
 	 	</tr>
 	 </table>
  </div>
  <div class="row" >
    <c:forEach items="${courseList}" var="c"  begin="0" end="2">
    	<div class="span4">
    		<div>
   				<c:choose>
					<c:when test="${c.teacherCourse.courseCover != null && c.teacherCourse.courseCover != ''}">
					<!-- <a href='<c:url value="/course/view/${c.teacherCourse.id}"></c:url>'> <img src='<c:url value="${url }${c.teacherCourse.courseCover }"></c:url>' style="width: 250px; height: 187px;" />
						</a> -->	
						<div style="width: 250px; height: 187px; background-image: url('<c:url value="${url }${c.teacherCourse.courseCover }"></c:url>');  
								background-repeat:no-repeat;background-position:center;  ">
							<a href='<c:url value="/course/view/${c.teacherCourse.id}"></c:url>'><div style="height: 147px;"></div></a>
		    				<div style="height:40px;background-color:#000;   filter:alpha(opacity=70); color: #fff;">
		    					<h5 >&nbsp;&nbsp;&nbsp;${c.teacherCourse.courseName} — ${c.teacherCourse.courseType }</h5>
		   				 	</div>
						</div>
					</c:when>
					<c:otherwise>
						<!-- <a href='<c:url value="/course/view/${c.teacherCourse.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 250px; height: 187px;" />
						</a> -->
						<div style="width: 250px; height: 187px; background-image: url('<c:url value="/resources/img/logo.png"></c:url>');  background-repeat:no-repeat;background-position:center;">
							<a href='<c:url value="/course/view/${c.teacherCourse.id}"></c:url>'><div style="height: 147px;"></div></a>
		    				<div style="height:40px;background-color:#000;   filter:alpha(opacity=70); color: #fff;">
		    					<h5 >&nbsp;&nbsp;&nbsp;${c.teacherCourse.courseName} — ${c.teacherCourse.courseType }</h5>
		   				 	</div>
						</div>
					</c:otherwise>
				</c:choose>
    		</div>
    		<div style="margin-top: 5px;">
    			教师名称：<a href='<c:url value="/teacher/${c.teacherCourse.teacher.id}"></c:url>'>${c.teacherCourse.teacher.user.name}</a><br>
    			学习人数：${c.userCount}人&nbsp;&nbsp;&nbsp;课程评分：
    			<c:choose>
    				<c:when test="${c.courseMark !=null && c.courseMark>0 }">
    					<fmt:formatNumber type="number" value="${c.courseMark }" maxFractionDigits="0" />分
    				</c:when>
    				<c:otherwise>
    					0分
    				</c:otherwise>
    			</c:choose>
    		</div>
    	</div>
    </c:forEach>
  </div><!-- /.row -->
</div>
<div class="container teacher" >
   <div class="container title">
 	 <table width="100%">
 	 	<tr>
 	 		<td width="16%" align="center"><h4>热门教师 </h4></td>
 	 		<td><a  href='<c:url value="/teacher/list?isEnterPrise=null"></c:url>'  >全部教师</a></td>
 	 	</tr>
 	 </table>
  </div>
  <div class="bgimg">
	  <div id="myTeacher" class="carousel slide">
	  <div class="carousel-inner" style="margin-bottom: -50px;">
	    <c:if test="${fn:length(teacherLists)==0}">
		    <div class="item">
		       <div class="row">
		       	<h3>暂无教师数据</h3>
		       </div>
		    </div>
	 	 </c:if>
	    <div class="item active">
		      <div class="row">
					<c:forEach items="${teacherLists}" var="t" begin="0" end="11">
						<div class="span2">
							<c:choose>
								<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
									<img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 70px; height:70px;margin-bottom: 5px;" />
								</c:when>
								<c:otherwise>
									<a href='<c:url value="${url}teacher/${t.id}"></c:url>'>${t.user.name }<img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 175px; height:180px;margin-bottom: 10px;" /></a>
								</c:otherwise>
							</c:choose>
							<div style="margin-top: 5px;">
								<c:choose>
								<c:when test="${t.user.name==null||t.user.name==''}">
								<a href='<c:url value="${url}teacher/${t.id}"></c:url>'>无名氏</a>
								</c:when>
								<c:otherwise>
								<a href='<c:url value="${url}teacher/${t.id}"></c:url>'>${t.user.name }</a>
								</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
			</div>
	    </div>
	    <c:if test="${fn:length(teacherLists) >12}">
	    <div class="item">
	       <div class="row">
				<c:forEach items="${teacherLists}" var="t" begin="12" end="23">
					<div class="span2" >
							<c:choose>
							<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
							<img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 70px; height:70px;" />
							</c:when>
							<c:otherwise>
							<img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 70px; height:70px;" />
							</c:otherwise>
							</c:choose>
							<div style="margin-top: 5px;">
							<c:choose>
							<c:when test="${t.user.name==null||t.user.name==''}">
							<a href='<c:url value="${url}teacher/${t.id}"></c:url>'>无名氏</a>
							</c:when>
							<c:otherwise>
							<a href='<c:url value="${url}teacher/${t.id}"></c:url>'>${t.user.name }</a>
							</c:otherwise>
							</c:choose>
							</div>
					</div>
				</c:forEach>
			</div>
	    </div>
	    </c:if>
	  </div>
	
	  <div style="display: none;">
		  <a class="left carousel-control" href="#myTeacher" data-slide="prev">‹</a>
		  <a class="right carousel-control" href="#myTeacher" data-slide="next">›</a>
	  </div>
	</div>
</div>
</div>

<div class="container teacher" >
   <div class="container title">
 	 <table width="100%">
 	 	<tr>
 	 		<td width="16%" align="center"><h4>热门企业 </h4></td>
 	 		<td><a   href='<c:url value="/teacher/list?isEnterPrise=1"></c:url>'   >全部企业</a></td>
 	 	</tr>
 	 </table>
  </div>
  <div class="bgimg">
	  <c:if test="${fn:length(enterPriseList)==0}">
			   <div class="item">
			      <div class="row">
			      <h3>暂无企业数据</h3>	
			      </div></div>
	</c:if>
	  <div id="myUniversity" class="carousel slide">
	  <div class="carousel-inner" style="margin-bottom: -50px;">
	    <div class="item active">
		      <div class="row">
					<c:forEach items="${enterPriseList}" var="t" begin="0" end="9">
						<div class="span1" >
							<c:choose>
							<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
							<img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 127px; height:83px;" />
							</c:when>
							<c:otherwise>
							<img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 127px; height:83px;" />
							</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
	    </div>
	    <c:if test="${fn:length(enterPriseList) >10}">
	    <div class="item">
	       <div class="row">
				<c:forEach items="${enterPriseList}" var="t" begin="10" end="19">
					<div class="span1">
						<c:choose>
							<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
							<img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 127px; height:83px;" />
							</c:when>
							<c:otherwise>
							<img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 127px; height:83px;" />
							</c:otherwise>
							</c:choose>
					</div>
				</c:forEach>
			</div>
	    </div>
	    </c:if>
	  </div>
	  <div style="display: none;">
		  <a class="left carousel-control" href="#myTeacher" data-slide="prev">‹</a>
		  <a class="right carousel-control" href="#myTeacher" data-slide="next">›</a>
	  </div>
	</div>
</div>
</div>