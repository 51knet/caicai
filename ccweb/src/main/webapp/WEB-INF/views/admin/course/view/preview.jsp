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
}
 .cont {
	margin-left:90px;
	margin-bottom: 10px;
	padding-top: 10px;
}
.cont .top{
	font-size: 14px; 
	 background-color: #cccccc; 
	 padding: 3px;
}
.container.course {
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}
.container.course.detail{
	margin-left: 46px;
	padding: 15px;
}
.container.course.detail.desc{
	margin-left: 75px;
	padding: 15px;
	width:800px;
}
.container.course.title{
	height: 240px;
	width:1024px;
	margin-bottom:35px;
	background-image: url('<c:url value='/resources/img/default/courseInfo.png'></c:url>');
	background-repeat: repeat-x;
}
.nar{
	background-color: #adcc75;
	height: 40px;
	padding-top: 2px;
	width: 1024px;
}
.nar >h4{
	margin-left: 88px;
}
.content{
	margin-left: 20px;
	font-size: 15px;
}
.comments-container .content {
	border-bottom: 1px dashed;
	text-align:left;
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
			<div style="width: 42%; text-align:center; float: left;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="${url }${course.courseCover }"></c:url>' style="width: 240px; height: 180px;margin-top:30px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 240px; height: 180px;margin-top: 10px;" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="margin-top: 33px;">
					<span style="font-size: 20px;color:#80b029" id="content">${course.courseName}</span><br/>
					<span style="font-size: 14px;color:#80b029">${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</span><br/>
					<span style="font-size: 14px;color: black;"> 类别：${course.courseType }</span><br/>
					<span style="font-size: 14px;color: black;">课程评分：${course.courseDate }
					<c:choose>
    				<c:when test="${courseMark>0 }">
    					<fmt:formatNumber type="number" value="${courseMark }" maxFractionDigits="0" />
    				</c:when>
    				<c:otherwise>
    					0
    				</c:otherwise>
    				</c:choose></span>&nbsp;分<br/>
    				<span style="font-size: 14px;color: black;">发布时间：${course.courseDate }</span><br/>
    				<span style="font-size: 14px;color: black;">学员（${studentPerson}）</span>
				<c:choose>
				<c:when test="${courseMark!=-1.0 }">
				评论（${sumPerson}）&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
				评论（0）&nbsp;&nbsp;
				</c:otherwise>
				</c:choose><br/>
			</div>
			<div style="text-align: left;margin-top: 15px;">
				<a href='#' class="btn  btn-success">点击学习</a>
				<a href='<c:url value="/admin/teacher/course/edit/${course.id }/publish"></c:url>'  class="btn   btn-success"  >发布课程</a>
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
<c:when test="${course.teacher.isEnterprise == null}">
    <div  class="nar" >
			<h4>讲师介绍</h4>
		</div>
    <div class="container course detail">
	    <c:choose >
			<c:when test="${course.teacher.user.photo_url != null && course.teacher.user.photo_url != ''}">
			<img src='<c:url value="${url}${course.teacher.user.photo_url }"></c:url>' style="width: 112px;height:112px; float:left; margin-left:30px" />
			</c:when>
			<c:otherwise>
			 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 112px;height: 112px; float:left; margin-left:30px" />
			</c:otherwise>
		</c:choose>
    <span style="margin-left: 40px;font-size: 18px;"><b>${course.teacher.user.name }</b></span>
   	<br/>
   	<br/>
   	<span class="content">所在学校：${course.teacher.school }</span><br/> 
   	<span class="content">所在学院：${course.teacher.college }</span><br/>
    <span class="content">专业：${course.teacher.major }</span><br/>
   </div>
</c:when>
<c:otherwise>
     <div class="nar">
			<h4>企业介绍</h4>
		</div>
    <div class="container course detail">
	    <c:choose>
			<c:when test="${course.teacher.user.photo_url != null && course.teacher.user.photo_url != ''}">
				<img src='<c:url value="${url }${course.teacher.user.photo_url }"></c:url>' style="width: 100px;height:100px; float:left; margin-left:30px;" />
			</c:when>
			<c:otherwise>
			 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>' style="width: 100px;height: 100px; float:left; margin-left:30px;" />
			</c:otherwise>
		</c:choose>
	<span style="margin-left: 20px;">
     ${course.teacher.user.name }
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
				<c:forEach var="course" items="${courseMap}" varStatus="i">
						<table   style="width: 89%;border: 2px solid #f1f1f1;">
							<tbody>
								<tr>
									<td align="left">
										<div >
											<div class="top" id="course_${i.count}" onclick="javascript:courseOnclick(this);"><b>第${course.key}课时</b></div>
												<c:forEach var="fileNames" items="${course.value}" varStatus="resourceStatus">
												<c:if test="${fileNames.fileName!=null}">
												<div class="fileName_${i.count}" >
													<div style="width: 87%; float: left; margin-left: 5px; margin-top: 3px;">${fileNames.fileName}</div>
												</div>
												</c:if>
												</c:forEach>
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
								<img src='<c:url value="${url }${usercourse.photoUrl }"></c:url>'   style="width:100px;height: 100px;" />
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



