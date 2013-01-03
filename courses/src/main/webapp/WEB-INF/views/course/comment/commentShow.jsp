<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="comment">
		<div  align="left" style="background-color:#F7F7F7;height:40px; line-height:40px;clear:both"><h4 style=" float:left; margin-left: 40px;">用户评价</h4></div> 
		<div style="margin-left:35px; margin-top: 10px; margin-bottom: 10px;">
    	<c:choose>
			<c:when test="${sumPerson>0 }">
			<span style="line-height:40px">总评论人数:&nbsp;&nbsp;&nbsp;${sumPerson}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			评论分数:
			<c:choose>
    				<c:when test="${courseMark !=null && courseMark>0 }">
    					<fmt:formatNumber type="number" value="${courseMark }" maxFractionDigits="0" />&nbsp;&nbsp;分
    				</c:when>
    				<c:otherwise>
    					0分
    				</c:otherwise>
    			</c:choose>
    			</span>
    			<hr>
				<table style="width: 100%">
			<c:forEach var="comment" items="${listcomment}">
				  <table>
				  <tr>
				    <td width="10%" rowspan="3" align="center" valign="top">
				    	 <c:choose >
							<c:when test="${comment.photoUrl != null && comment.photoUrl != ''}">
								<img src='<c:url value="${comment.photoUrl }"></c:url>'   style="width: 50px;height: 50px;" />
							</c:when>
							<c:otherwise>
								<img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 50px;height: 50px;" />
							</c:otherwise>
						</c:choose>
				    </td>
				    <td align="left" style="width: 15%">${comment.userName}</td>
				    <td align="left">评分:${comment.comment.mark}</td>
				  </tr>
				  <tr>
				    <td colspan="2" align="left"><br>${comment.comment.commentDesc}</td>
				  </tr>
				  <tr>
				    <td colspan="2" align="left"><br>${comment.comment.commentDate }</td>
				  </tr>
				  </table>
				  <hr>
			</c:forEach>
			 <tr><td>
       				 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
    			</td></tr>
			</table>
			</c:when>
			<c:otherwise>
			<h5 style="margin-left: 5px;margin-top:16px">尚未有课程评论</h5>
			</c:otherwise>
			</c:choose>		
		</div>
    
		
</div>
