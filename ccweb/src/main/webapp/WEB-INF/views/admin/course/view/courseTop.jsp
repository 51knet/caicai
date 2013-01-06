<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.row-fluid.custom.teacherCourse {
	width:1024px;
	text-align: left;
	margin-top: 10px;
	border:solid 2px #f7f7f7;
}
</style>
<div class=" row-fluid custom teacherCourse">
    <div>
	    <c:choose >
			<c:when test="">
				<img src='<c:url value=""> </c:url>'style="width: 100px;height:100px; float:left; margin-left:30px"/>
			</c:when>
			<c:otherwise>
				 <img src='<c:url value="/resources/img/knetlogo.png"></c:url>'style="width: 100px;height: 100px; float:left; margin-left:30px"/>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="margin-top: 40px;">
	<h4>
	<span>c++基本入门课程</span></h4>
	<span style="margin-left: 70%;float: left;margin-top: -30px;"><button class="btn btn-large btn-success">预览</button></span>
	<br/>
	<div style="margin-top:2px;margin-left: 70%;float: left;padding-bottom: 40px;">
	<span><button class="btn btn-large btn-success">发布</button></span>
	</div>
</div>
</div>
					
					
			