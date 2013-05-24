<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course {
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}
.container.course.detail {
	width: 100%;
}
.container.course.detail.desc{
	margin-left:70px;
	padding: 20px;
	width:83.5%;
}
.container.course.title{
	height: 240px;
	width:1024px;
	margin-bottom:15px;
	background-image: url('<c:url value='/resources/img/default/courseInfo.png'></c:url>');
	background-repeat: repeat-x;
	margin-top: 41px;
}
.container.course.content{
	width: 37%; 
	text-align:left; 
	float: left;
	margin-top: 32px;
	margin-left: 70px;
}
.nar{
	font-size:16px;
	color:#adcc75;
	height: 40px;
}
.nar >h4{
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 10px;
	padding-left:88px;
}
</style>
<div>
	<div>
		<div class="container course title" >
			<div style="width: 40%; text-align:center;float: left;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 240px; height: 180px;margin-top:30px;margin-left: 160px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 240px; height: 180px;margin-top: 30px;margin-left: 160px;" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="container course content">
					<span style="font-size: 25px;color:#80b029;" id="content">${course.courseName}</span><br/>
					<span style="font-size: 18px;color:#80b029;">${course.user.name}</span><br/>
					<span style="font-size: 14px;color: black;"> 类别：${course.cType.typeName }</span><br/>
    				<span style="font-size: 14px;color: black;">发布时间：${course.courseDate }</span><br/>
    				<span style="font-size: 14px;color: black;">评分：
					<c:choose>
    				<c:when test="${courseMark>0 }">
    					<fmt:formatNumber type="number" value="${courseMark }" maxFractionDigits="0" />
    				</c:when>
    				<c:otherwise>
    					0
    				</c:otherwise>
    				</c:choose>&nbsp;分</span>
    				<span style="font-size: 14px;color: black;">学员：${studentPerson} 人</span>
				<c:choose>
				<c:when test="${courseMark!=-1.0 }">
				<span style="font-size: 14px;color: black;">评论：${sumPerson} 条</span>
				</c:when>
				<c:otherwise>
				<span style="font-size: 14px;color: black;">评论：0 条</span>
				</c:otherwise>
				</c:choose><br/>
				<span style="font-size: 14px;color: black;">售价： ${course.price } 元</span><br/>
				<c:choose>
					<c:when test="${course.price != 0 }">
						<a  href='<c:url value="/course/pay/view/${course.id}"></c:url>'><img style="margin-top: 5px;" src='<c:url value="/resources/img/default/studyButton.png"></c:url>'  /></a>
					</c:when>
					<c:otherwise>
						<a  href='<c:url value="/course/study/view/${course.id}"></c:url>'><img style="margin-top: 5px;" src='<c:url value="/resources/img/default/studyButton.png"></c:url>'  /></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="nar">
			<h4>课程介绍</h4>
		</div>
			<div class="container course detail desc">
				${course.courseDesc}
			</div>
		<div  class="nar">
			<h4>目标人群</h4>
		</div>
			<div class="container course detail desc">
				<c:choose>
				<c:when test="${course.targetPerson!=null}">
					${course.targetPerson}
				</c:when>
				<c:otherwise>
					尚未添加目标人群
				</c:otherwise>
			</c:choose>
			</div>
		<div  class="nar">
			<h4>课程看点</h4>
		</div>
			<div class="container course detail desc">
			<c:choose>
				<c:when test="${course.courseCharacter!=null }">
					${course.courseCharacter}
				</c:when>
				<c:otherwise>
					尚未添加课程看点
				</c:otherwise>
			</c:choose>
			</div>
	</div>
</div>



