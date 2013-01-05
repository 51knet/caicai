<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.container.teacherCourse {
	width:1024px;
	text-align: left;
	margin-top: 40px;
	border:1px solid;
}
</style>
<div class="container teacherCourse">
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
	<div >
    <table>
    <tr >
    <td style="padding: 20px;"><h4>c++基本入门课程</h4></td>
    </tr>
    <tr>
    <td><input type="button" value="预览"></td>
    </tr>
    <tr><td><input type="button" value="发布"></td></tr>
    </table>
  	</div>
</div>
					
					
			