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
		<div class="selete_filter">
			<select >
				<option>全部教师</option>
			</select>
			<!-- <span>大学</span><span>中学</span><span>小学</span><span>其他</span> -->
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
	<br>
      	<c:forEach items="${page.content}" var="t">
			<div class="span3" >
				<c:choose>
					<c:when test="${t.user.photo_url!=null||t.user.photo_url!=''}">
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="${t.user.photo_url }"></c:url>' style="width: 122px; height:122px;" /></a>
					</c:when>
					<c:otherwise>
						<a href="/courses/teacher/${t.id }"><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 122px; height:122px;" /></a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${t.user.name==null||t.user.name==''}">
						<div id="contentlimite" style="width: 125px;"><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>尚未添加</a></div>
					</c:when>
					<c:otherwise>
						<div id="contentlimite" style="width: 125px;"><a href='<c:url value="${url}/teacher/${t.id}"></c:url>'>${t.user.name }</a></div>
					</c:otherwise>
				</c:choose>
				<div id="contentlimite" style="width: 125px;">${t.college}</div>
			</div>
		</c:forEach>
	</div>
	</c:otherwise>
	</c:choose>
	<div  class="teacherInfo" style="float: left; margin-left: 80px;  margin-top: 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>
