<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
}

.container.teacher {
	width: 1024px;
	max-width:1024px;
	text-align: left;
}

.container.teacher .row{
	 margin-left: 0px;
	 }

</style>
<div class="container teacher">
    <div  align="left" style="width:100%;background-color:#F7F7F7; height: 40px;">
 
    </div>
    <br/>
    <div>
	    <c:choose >
			<c:when test="${userInfo.photo_url != null && userInfo.photo_url != ''}">
				<img src='<c:url value="${url }${userInfo.photo_url }"> </c:url>'style="width: 100px;height:100px; float:left; margin-left:30px"/>
			</c:when>
			<c:otherwise>
				 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'style="width: 100px;height: 100px; float:left; margin-left:30px"/>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="width:500px; margin-left: 100px; float: left;" >
    <table>
    <tr >
    <td style="width:150px;"><h4>${userInfo.name }</h4></td><td>${userInfo.email }</td>
    </tr>
    </table>
    
  	</div>
</div>