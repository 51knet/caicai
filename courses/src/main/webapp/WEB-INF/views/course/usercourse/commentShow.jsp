<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.comments-container .content {
	border-bottom: 1px dashed;
	text-align:left;
}
.nar{
	background-color: #adcc75;
	height: 40px;
	padding-top: 2px;
	width: 100%;
}
.nar >h4{
	margin-left: 88px;
}
.comments-container h5 {
	padding:5px;
	text-align: left;
	padding-left: 88px;
}
</style>
<div id="comment">
		<div  class="nar">
			<h4>用户评价</h4>
		</div> 
		<div class="comments-container">
    	<c:choose>
			<c:when test="${sumPerson>0}">
			<h5>总评论人数:&nbsp;&nbsp;&nbsp;${sumPerson}
			评论分数:
			<c:choose>
    				<c:when test="${courseMark>0 }">
    					<fmt:formatNumber type="number" value="${courseMark }" maxFractionDigits="0" />&nbsp;&nbsp;分
    				</c:when>
    				<c:otherwise>
    					0分
    				</c:otherwise>
    			</c:choose>
    			</h5>
				<div >
			<c:forEach var="usercourse" items="${listUserCourse}">
				 <c:choose>
				    <c:when test="${usercourse.userCourse.commentDesc!=null  }">
				  <div  style="margin-left: 88px;">
				  <span>
				    	<c:choose >
							<c:when test="${usercourse.photoUrl != null && usercourse.photoUrl != ''}">
								<img src='<c:url value="${url }${usercourse.photoUrl }"></c:url>'   style="width:80px;height: 60px;" />
							</c:when>
							<c:otherwise>
								<img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 80px;height: 60px;" />
							</c:otherwise>
						</c:choose>
				    </span>
				    <span style="color:#80b029">${usercourse.userName}&nbsp;&nbsp;&nbsp;&nbsp;</span>
				    <span>评分:${usercourse.userCourse.mark}&nbsp;&nbsp;&nbsp;&nbsp;
				 	<fmt:formatDate value="${usercourse.userCourse.commentDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
				    <span >${usercourse.userCourse.commentDesc}</span>
				  </div>
				   </c:when>
				  </c:choose>
				  <div class="content"></div>
			</c:forEach>
			 <span>
       				 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
    			</span>
			</div>
			</c:when>
			<c:otherwise>
			<div style="margin-left: 88px;margin-top:16px">尚未有课程评论</div>
			</c:otherwise>
			</c:choose>		
		</div>
    
		
</div>
