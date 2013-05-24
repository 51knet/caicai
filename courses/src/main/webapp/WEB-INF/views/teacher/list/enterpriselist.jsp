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
				<option>全部企业</option>
			</select>
		</div>
	<div class="container title"  >
	 	 <table  >
	 	 	<tr>
	 	 		<td width="18%" align="center"><h4>热门企业（${fn:length(enterpriseList)}）</h4></td>
	 	 		<td></td>
	 	 	</tr>
	 	 </table>
  	</div>
  	<div class="bgimg">
	<c:choose>
	<c:when  test="${fn:length(enterpriseList)==0}">
       <div class="teacherInfo">
       	<h3>暂无企业数据</h3>
       </div>
	</c:when>
	<c:otherwise>
	<div class="teacherInfo">
	    <c:forEach items="${page.content}" var="enter" >
			<div class="span1" >
				<c:choose>
					<c:when test="${enter.user.photo_url!=null||enter.user.photo_url!=''}">
						<a href="${url }/enterprise/${enter.id }"><img src='<c:url value="${url }${enter.user.photo_url }"></c:url>' style="width: 127px; height:83px;" /></a>
					</c:when>
					<c:otherwise>
					<a href="${url }/enterprise/${enter.id }"><img src='<c:url value="/resources/img/avatar/avatar40.png"></c:url>' style="width: 127px; height:83px;" /></a>
					</c:otherwise>
				</c:choose>
					<div>
					<c:choose>
					<c:when test="${enter.user.name==null||enter.user.name==''}">
					<div id="contentlimite" style="width: 125px;">尚未添加</div>
					</c:when>
					<c:otherwise>
					<div id="contentlimite" style="width: 125px;">${enter.user.name }</div>
					</c:otherwise>
					</c:choose>				
					</div>
			</div>
		</c:forEach> 
	</div>
	</c:otherwise>
	</c:choose>
		<br>
		<div  class="teacherInfo" style="float: left; margin-left: 80px; margin-top: 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
	</div>
</div>



