<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container.course.top{
	height: 200px;
	background-color:#fff;
	background-image: url('<c:url value="/resources/img/default/user-course-top-bg.png"></c:url>');
	background-position: top center;
	background-repeat: no-repeat;
	text-align: center;
}
.container.course.top .content{
	margin: 20px 20px 0px 20px;
}

</style>
<div class="container course top" >
	<c:choose>
		<c:when test="${course != null}">
		<div class="content">
	    	<table width="100%"  cellpadding="10">
				<tr>
					<td  width="30%" align="center"  valign="top" >
					 <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 200px; height: 150px;" />
					</td>
					<td align="left" valign="top" >
						<div style=" float: left;width: 195px;">
						<h4>${course.courseName}</h4><h4>${course.cType.typeName }</h4></div>
					</td>
					<td width="30%" align="left"  valign="top" style="line-height: 30px;">
							<div style="float: left;padding-right: 15px;">
								<c:choose>
									<c:when test="${course.user.photo_url != null && course.user.photo_url != ''}">
										<a href='<c:url value="/teacher/${course.user.id}"></c:url>'><img src='<c:url value="${url }${course.user.photo_url }"></c:url>' style="width: 100px; height: 100px;" />
										</a>
									</c:when>
									<c:otherwise>
										<a href='<c:url value="/teacher/${course.user.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar90.png"></c:url>' style="width:100px; height:100px;" />
										</a>
									</c:otherwise>
								</c:choose>
							</div>
							<h4 >${course.user.name }</h4>
							<a  href='<c:url value="/teacher/${course.user.id}"></c:url>' class="btn  btn-success">查看发布课程</a>
					</td>
				</tr>
			</table>
   		</div></c:when>
		<c:otherwise>
			<div class="row" style="text-align:left; margin-left: 0px; margin-top: 10px;">
		    	<table class="table table-bordered" style="width: 97%; height: 100%;" cellpadding="5">
					<tr>
						<td valign="top"  align="left">
							<h3>您尚未学习任何课程</h3>
							<a href='<c:url value="/course/list/type?detail=all"></c:url>' class="btn btn-large btn-success">点击开始学习</a>
						</td>
					</tr>
				</table>
    		</div>
		</c:otherwise>
	</c:choose>
</div>



	  
 
