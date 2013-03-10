<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>

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
		<div class="selete_filter">
			<select >
				<option>全部教师</option>
			</select>
			<span>大学</span><span>中学</span><span>小学</span><span>其他</span>
		</div>
		<div class="container title"  >
		 	 <table  >
		 	 	<tr>
		 	 		<td width="18%" align="center"><h4>热门教师（${fn:length(teacherList)}）</h4></td>
		 	 		<td></td>
		 	 	</tr>
		 	 </table>
  		</div>
  	<div class="bgimg">
	<c:choose>
	<c:when test="${fn:length(teacherList)==0}">
       <div class="teacherInfo">
       	<h3>暂无教师数据</h3>
       </div>
	</c:when>
	<c:otherwise>
	<div class="teacherInfo">
      	<c:forEach items="${teacherList}" var="t">
			<div class="span3" >
				<c:choose>
					<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 122px; height:122px;" /></a>
					</c:when>
					<c:otherwise>
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 122px; height:122px;" /></a>
					</c:otherwise>
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${t.user.name==null||t.user.name==''}">
						<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>无名氏</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a>
					</c:otherwise>
				</c:choose>
				<br/>
				${t.school}
			</div>
		</c:forEach>
	</div>
	</c:otherwise>
	</c:choose>
	</div>
	<!-- <div ><jsp:include page="/WEB-INF/views/_shared/pagination.jsp" ></jsp:include></div> -->
	</c:when>
	<c:otherwise>
		<div class="selete_filter">
			<select >
				<option>全部企业</option>
			</select>
		</div>
	<div class="container title"  >
	 	 <table  >
	 	 	<tr>
	 	 		<td width="18%" align="center"><h4>热门企业（${fn:length(enterPriseList)}）</h4></td>
	 	 		<td></td>
	 	 	</tr>
	 	 </table>
  	</div>
  	<div class="bgimg">
	<c:choose>
	<c:when  test="${fn:length(enterPriseList)==0}">
       <div class="teacherInfo">
       	<h3>暂无企业数据</h3>
       </div>
	</c:when>
	<c:otherwise>
	<div class="teacherInfo">
	    <c:forEach items="${enterPriseList}" var="t" >
			<div class="span1" >
				<c:choose>
					<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="${url }${t.user.photo_url }"></c:url>' style="width: 127px; height:83px;" /></a>
					</c:when>
					<c:otherwise>
					<a href="/courses/teacher/${t.id }"><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 127px; height:83px;" /></a>
					</c:otherwise>
				</c:choose>
					<div>
					<c:choose>
					<c:when test="${t.user.name==null||t.user.name==''}">
					<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>无名氏</a>
					</c:when>
					<c:otherwise>
					<a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a>
					</c:otherwise>
					</c:choose>				
					</div>
			</div>
		</c:forEach> 
	</div>
	</c:otherwise>
	</c:choose>
	</div>
	</c:otherwise>
</c:choose>
</div>



