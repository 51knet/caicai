<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function courseOnclick(obj) {
		var courseId = obj.id;
		var id = courseId.substring(courseId.indexOf('_') + 1, courseId.length); // 这里indexOf('-')和lastIndexOf('-')相等
		$(".fileName_" + id).slideToggle();
		return false;
	}
</script>
<style>
body {
	background-color:#FFFFFF;
	background-image: url("");
}
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

.container.course.detail.desc .content{
	margin-left:40px;
}
.container.course.title{
	height: 240px;
	width:1024px;
	margin-bottom:15px;
	background-image: url('<c:url value='/resources/img/default/courseInfo.png'></c:url>');
	background-repeat: repeat-x;
	margin-top: 2px;
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
 .cont {
	margin-left:90px;
	margin-bottom: 43px;
	padding-top: 20px;
}
.cont .top{
	font-size: 14px; 
	 background-color: #cccccc; 
	 padding: 3px;
}
.comments-container .content {
	border-bottom: 1px dashed;
	text-align:left;
	margin-left:-1px;
	width: 100%
}

.comments-container h5 {
	padding:5px;
	text-align: left;
	padding-left: 88px;
}
</style>
<div>
	<div>
		<div  class="container course title">
			<div style="width: 40%; text-align:center;float: left;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<img src='<c:url value="${course.courseCover }"></c:url>' style="width: 240px; height: 180px;margin-top:30px;margin-left: 160px;" />
					</c:when>
					<c:otherwise>
						 <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 240px; height: 180px;margin-top: 30px;margin-left: 160px;" />
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
    				<span style="font-size: 14px;color: black;">&nbsp;&nbsp学员：${studentPerson}&nbsp;&nbsp</span>
				<c:choose>
				<c:when test="${courseMark!=-1.0 }">
				<span style="font-size: 14px;color: black;">&nbsp;&nbsp评论：${sumPerson}&nbsp;&nbsp;</span>
				</c:when>
				<c:otherwise>
				<span style="font-size: 14px;color: black;">&nbsp;&nbsp评论：0&nbsp;&nbsp;</span>
				</c:otherwise>
				</c:choose><br/>
			<div style="text-align: left;margin-top: 15px;">
				<a href='#' class="btn  btn-success">点击学习</a>
				<a href='<c:url value="/admin/course/edit/${course.id }/publish"></c:url>'  class="btn   btn-success"  >发布课程</a>
				</div>
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
<c:choose>
	<c:when test="${sessionsessionUserInfo.user.role == teacher}">
    <div  class="nar" >
			<h4>讲师介绍</h4>
		</div>
    <div class="container course detail desc"  >
	    <c:choose >
			<c:when test="${sessionUserInfo.user.photo_url != null && sessionUserInfo.user.photo_url != ''}">
			<img src='<c:url value="${sessionUserInfo.user.photo_url}"></c:url>' style="width: 100px;height:100px; float:left; margin-left:6px" />
			</c:when>
			<c:otherwise>
			 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height: 100px; float:left; margin-left:6px" />
			</c:otherwise>
		</c:choose>
    <span  class="content" style="font-size: 18px;"><b>${sessionUserInfo.user.name }</b></span>
   	<br/>
   	<br/>
   	<span class="content">所在学校：${sessionUserInfo.teacher.school }</span><br/> 
   	<span class="content">所在学院：${sessionUserInfo.teacher.college }</span><br/>
    <span class="content">专业：${sessionUserInfo.teacher.major }</span><br/>
   </div>
</c:when>
<c:otherwise>
     <div class="nar">
			<h4>企业介绍</h4>
		</div>
    <div class="container course detail desc">
	    <c:choose>
			<c:when test="${sessionUserInfo.user.photo_url != null && sessionUserInfo.user.photo_url != ''}">
				<img src='<c:url value="${sessionUserInfo.user.photo_url }"></c:url>' style="width: 100px;height:100px; float:left; margin-left:6px;" />
			</c:when>
			<c:otherwise>
			 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height: 100px; float:left; margin-left:6px;" />
			</c:otherwise>
		</c:choose>
	<span style="margin-left: 20px;">
     ${sessionUserInfo.user.name }
  	</span>
	</div>
</c:otherwise>
</c:choose>
		<div class="nar">
	<h4>课程学习</h4>
</div>
		<div class="cont">
		<c:choose>
			<c:when test="${resourceCount>0 }">
				<c:forEach var="course" items="${courseMap}" varStatus="status">
				<table style="width: 89%; border: 1px solid #f1f1f1;">
					<tbody>
						<tr>
							<td align="left">
								<div>
									<div class="top" id="course_${status.index}" onclick="javascript:courseOnclick(this);">
										<b>第${course.key}课时</b>
									</div>
									<div style="margin-top: 5px;">
									<c:forEach var="fileNames" items="${course.value}" >
										<c:if test="${fileNames.fileName!=null}">
											<div class="fileName_${status.index}" style="width: 100%; margin-top:5px; margin-bottom: 1px; border-bottom: 1px dotted #dcdcdc;">
												<table style="width: 94%; margin-left: 20px;">
													<tbody>
														<tr>
															<td width="4%"><c:if test="${fileNames.resourceType.id ==1 }">
																	<img src='<c:url value="/resources/resourceType/text.jpg"></c:url>' style="width: 40px; height: 40px;" />
																</c:if> <c:if test="${fileNames.resourceType.id ==2}">
																	<img src='<c:url value="/resources/resourceType/video.jpg"></c:url>' style="width: 40px; height: 40px;" />
																</c:if></td>
															<td align="left" width="60%"><a href='<c:url value="/course/resource/download/${fileNames.id}"></c:url>'> <span>${fileNames.fileName}</span>
															</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</c:if>
									</c:forEach>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<div>尚未添加资源</div>
			</c:otherwise>
		</c:choose>
</div>
		
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
				  <div  style="margin-left: 88px; margin-top: 10px; ">
				  <span>
				    	<c:choose >
							<c:when test="${usercourse.photoUrl != null && usercourse.photoUrl != ''}">
								<img src='<c:url value="${usercourse.photoUrl }"></c:url>'   style="width:100px;height: 100px;" />
							</c:when>
							<c:otherwise>
								<img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height:100px;" />
							</c:otherwise>
						</c:choose>
				    </span>
				    <span style="color:#80b029" >${usercourse.userName}&nbsp;&nbsp;&nbsp;&nbsp;</span>
				    <span>评分:${usercourse.userCourse.mark}&nbsp;&nbsp;&nbsp;&nbsp;
				 	<fmt:formatDate value="${usercourse.userCourse.commentDate}" pattern="yyyy-MM-dd HH:mm"/></span><br/>
				    <div style="margin-left:120px; width: 560px;">
				    <span >${usercourse.userCourse.commentDesc}</span>
				    </div>
				  </div>
				   </c:when>
				  </c:choose>
				  <div class="content"></div>
			</c:forEach>
			 <div style="margin-left: 85px;">
       				 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			 </div>
			</div>
			</c:when>
			<c:otherwise>
			<div style="margin-left: 88px;margin-top:16px">尚未有课程评论</div>
			</c:otherwise>
			</c:choose>		
		</div>
		
	</div>
</div>



