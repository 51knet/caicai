<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div align="center">
<div style="height: 40px"></div>
    <br/>
<div align="center" style="width:1024px;;">
    <div  align="left" style="width:100%;background-color:#F7F7F7; height: 40px;">
    	<h4 style="margin-left: 50px; float: left;">讲师介绍</h4>
    </div>
    <br/>
    <div  style="width: 120px;height: 210px; float:left; margin-left:10px">
    <c:choose >
		<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
			<img src='<c:url value="${teacher.user.photo_url }"></c:url>'/>
		</c:when>
		<c:otherwise>
			 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'/>
		</c:otherwise>
	</c:choose>
	</div>
	<div align="left" style="width:500px;height:100px " >
    <span>	&nbsp;&nbsp;&nbsp;&nbsp;姓名:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.user.name }&nbsp;&nbsp;&nbsp;&nbsp;
    	 	&nbsp;&nbsp;&nbsp;&nbsp;性别:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.user.gender }&nbsp;&nbsp;&nbsp;&nbsp;
    	 	&nbsp;&nbsp;&nbsp;&nbsp;所属高校:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.college }&nbsp;&nbsp;&nbsp;&nbsp;
    </span>
    <br/>
  	<span>
  			&nbsp;&nbsp;&nbsp;&nbsp;所属院系:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.school}&nbsp;&nbsp;&nbsp;&nbsp;
  			&nbsp;&nbsp;&nbsp;&nbsp;教授课程:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.major}&nbsp;&nbsp;&nbsp;&nbsp;
  	</span>
  	<br/>
  	<span>	&nbsp;&nbsp;&nbsp;&nbsp;职称:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.title}&nbsp;&nbsp;&nbsp;&nbsp;
  		 	&nbsp;&nbsp;&nbsp;&nbsp;导师类别:&nbsp;&nbsp;&nbsp;&nbsp;${teacher.role}&nbsp;&nbsp;&nbsp;&nbsp;
  	</span>
  	</div>
</div>
</div>