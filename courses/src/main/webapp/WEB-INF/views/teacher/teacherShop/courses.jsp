<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function selectType(id) {
		var sel = document.getElementById("type");
		var opt = sel.options;
		for ( var i = 0; i < opt.length; i++) {
			if (opt[i].selected) {
				var typeName = opt[i].innerHTML;
				//alert(typeName);
				window.location.href = '<c:url value="/teacher/'+id+'/course/type?detail='
						+ typeName + '"></c:url>';
			}
		}
	}
</script>
<style>
	.detail{
		padding: 40px 40px
	}
	
	.detail .content{
		margin-left: 30px;
	}
</style>
<div class="container teacher">
<c:choose>
<c:when test="${teacher.isEnterprise == null}">
    <div class="detail">
	    <c:choose >
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
			<a href='<c:url value="${url}/teacher/${teacher.id}"></c:url>'><img src='<c:url value="${url}${teacher.user.photo_url }"></c:url>' style="width: 112px;height:112px; float:left; margin-left:30px" />
										</a>
			</c:when>
			<c:otherwise>
			<a href='<c:url value="${url}/teacher/${teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 112px;height: 112px; float:left; margin-left:30px" />
										</a>
			</c:otherwise>
		</c:choose>
    <span class="content" style="font-size: 18px;"><a href='<c:url value="${url}/teacher/${teacher.id}"></c:url>'> <b>${teacher.user.name }</b></a></span>
   	<br/><br/>
   	<span class="content">所在学校：${teacher.school }</span><br/> 
    <span class="content">职称：${teacher.major }</span><br/>
    <span class="content">专业：${teacher.college }</span><br/>
   </div>
</c:when>
<c:otherwise>
    <div class=" detail">
	    <c:choose>
			<c:when test="${teacher.user.photo_url != null && teacher.user.photo_url != ''}">
				<a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'><img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 112px;height:112px; float:left; margin-left:30px;" /></a>
			</c:when>
			<c:otherwise>
			<a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'> <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 112px;height: 112px; float:left; margin-left:30px;" /></a>
			</c:otherwise>
		</c:choose>
	<span style="margin-left: 20px;">
    <a href='<c:url value="${url}/teacher/${course.teacher.id}"></c:url>'> ${teacher.user.name }</a>
  	</span>
	</div>
</c:otherwise>
</c:choose>
</div>

<div class="container title"  >
 	 <table >
 	 	<tr>
 	 		<td width="20%" align="center"><h4>所有课程（${courseCount}）</h4></td>
 	 		<td></td>
 	 	</tr>
 	 </table>
 </div>
<div class="container user-course" >
	<br>
 	<c:forEach items="${teacherCourseList}" var="course">
			<table cellpadding="20" style="width: 40%;   margin-bottom: 10px;  float: left; margin-left: 50px;"  >
				<tr >
				<td  width="24%" align="right"  valign="top">
						<c:choose>
							<c:when test="${course.courseCover != null && course.courseCover != ''}">
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 162px; height: 120px;float: right;" />
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 162px; height: 120px; float: right;" />
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td valign="top">
						<div style="width:200px;" id="contentlimit">
						<b style="font-size: 17px;">${course.courseName}</b><br>
						类别：${course.courseType }<br>
						描述：${course.courseDesc }<br>
						日期：${course.courseDate }</div>
					</td>
				</tr>
			</table>
	</c:forEach>
	
 </div>