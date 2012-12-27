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
	width: 990px;
	max-width:990px;
	text-align: left;
}

.container.teacher .row{
	 margin-left: 0px;
	 }

</style>
<div class="container teacher">
	<div style="height: 40px"></div>
    <br/>
    <div  align="left" style="width:100%;background-color:#F7F7F7; height: 40px;">
    	<h4 style="margin-left: 50px; float: left;">讲师介绍</h4>
    </div>
    <br/>
<<<<<<< HEAD
    <div  style="width: 120px;height: 210px; float:left; margin-left:30px">
=======
    <div  style="width: 120px;height: 210px; float:left; margin-left:20px">
>>>>>>> aff8b4dcef773b1ad41d034dba43d33e517eba90
	    <c:choose >
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
				<img src='<c:url value="${teacher.user.photo_url }"></c:url>'/>
			</c:when>
			<c:otherwise>
				 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'/>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="width:500px; margin-left: 100px; float: left;" >
    <table>
    <tr >
    <td style="width:150px;"><h4>${teacher.user.name }</h4></td><td>${teacher.college }</td>
    </tr>
    <tr>
    <td style="width:150px;height: 30px">${teacher.title }</td><td>${teacher.school }</td>
    </tr>
    <tr>
    <td style="width:150px;height: 30px">${teacher.teaching_subject }</td><td>${teacher.major }</td>
    </tr>
    </table>
    
  	</div>
</div>