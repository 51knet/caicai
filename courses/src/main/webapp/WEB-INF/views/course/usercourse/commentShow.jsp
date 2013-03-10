<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.nar {
	background-color: #ccdfa8; 
	width:100%; 
	font-size:14px;
	height: 20px; 
	padding-top: 10px;
	margin-bottom: 10px;  
	padding: 5px; 
}
.nar .content{
	margin-left: 30px;
	font-size: 15px;
}
.comments-container .content {
	border-bottom: 1px dashed;
	padding:10px;
	margin-left: 60px;
}
</style>
<div id="comment">
		<div  class="nar">
			<span class="content" style="padding-left:55px;"><b>用户评价</b></span>
		</div> 
		<div class="comments-container">
    	<c:choose>
			<c:when test="${sumPerson>0}">
			<span style="line-height:40px">总评论人数:&nbsp;&nbsp;&nbsp;${sumPerson}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			评论分数:
			<c:choose>
    				<c:when test="${courseMark>0 }">
    					<fmt:formatNumber type="number" value="${courseMark }" maxFractionDigits="0" />&nbsp;&nbsp;分
    				</c:when>
    				<c:otherwise>
    					0分
    				</c:otherwise>
    			</c:choose>
    			</span>
    		<div class="content"></div>
				<div >
			<c:forEach var="usercourse" items="${listUserCourse}">
				 <c:choose>
				    <c:when test="${usercourse.userCourse.commentDesc!=null  }">
				  <div class="content" style="padding: 15px;">
				  <span>
				    	<c:choose >
							<c:when test="${usercourse.photoUrl != null && usercourse.photoUrl != ''}">
								<img src='<c:url value="${url }${usercourse.photoUrl }"></c:url>'   style="width: 40px;height: 30px;" />
							</c:when>
							<c:otherwise>
								<img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 40px;height: 30px;" />
							</c:otherwise>
						</c:choose>
				    </span>
				    <span style="color:#80b029">${usercourse.userName}</span>
				    <span>评分:${usercourse.userCourse.mark}</span>
				 	<span></span>
				    <span >${usercourse.userCourse.commentDesc}</span>
				  <%-- <fmt:formatDate value="${usercourse.userCourse.commentDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
				  </div>
				   </c:when>
				  </c:choose>
				    <tr ><td class="content"></td></tr>	 
			</c:forEach>
			 <span>
       				 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
    			</span>
			</div>
			</c:when>
			<c:otherwise>
			<div style="margin-left: 90px;margin-top:16px">尚未有课程评论</div>
			</c:otherwise>
			</c:choose>		
		</div>
    
		
</div>
