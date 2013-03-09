<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
	.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	
}
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<div  class="row-fluid custom round">
	<div class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>课程管理</b></a>>>
		<a href='<c:url value="/admin/teacher/course/view/${course.id}"></c:url>'><b>课程详细</b></a><hr>
		<div style="text-align: center;">
			<div style="text-align: center;">
				<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
					<thead><tr><th width="15%">课程标题</th><th>课程简述</th><th width="20%">发布时间</th></tr></thead>
					<tbody>
						<tr><td align="left" width="15%">${course.courseName}</td>
						<td align="left">${course.courseDesc}</td>
						<td align="center" width="20%">${course.courseDate}</td></tr>	
					</tbody>
				</table>
				<br><hr>
				<div class="row" style="text-align: right;">
				<a style="margin-bottom: 10px; font-size: 14px;" href='<c:url value="/admin/teacher/${course.id}/resource/new"></c:url>' class="btn">添加附件</a><br>
				</div>
				<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
					<thead><tr><th width="25%">资源名称</th><th width="10%">课时</th><th>资源描述</th><th width="20%">上传时间</th><th width="11%">操作</th></tr></thead>
					<tbody>
						<c:forEach items="${resourceList}" var="resource">
							<tr>
								<td align="center" >${resource.fileName}</td>
								<td align="center" >第${resource.resourceOrder}课</td>
								<td align="left" >${resource.resourceDesc}</td>
								<td align="center">${resource.date}</td>
								<td align="center"><a href='<c:url value="/course/resource/download/${resource.id }"></c:url>'>下载</a> | 
								<a href='<c:url value="/admin/teacher/${course.id}/resource/destory/${resource.id }"></c:url>'>删除</a>	</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<style>
.container.course {
	width: 990px;
	max-width:990px;
	text-align: left;
}

.container.course .row{
	 margin-left: 0px;
}

.container.course.detail {
	width: 990px;
}

.container.course.detail.desc{
	width: 950px;
	margin-left: 50px;
	margin-bottom: 10px;
}
</style>
<div class="container course" style=" margin-bottom: 20px;margin-top: 10px;">
	<div class="container course row">
		<div class="container course detail" style="margin-bottom: 20px; margin-top:10px; height: 180px;">
			<div style="width: 40%; height:150px; text-align:center;  float: left;border: 0px solid #cccccc;">
				<c:choose>
					<c:when test="${course.courseCover != null && course.courseCover != ''}">
						<a href='#'> <img src="/ccweb/${course.courseCover }" style="width: 240px; height: 120px;margin-top: 10px;" />
						</a>
					</c:when>
					<c:otherwise>
						<a href='<c:url value="/course/view/${course.id}"></c:url>'> <img src='<c:url value="/resources/img/logo.png"></c:url>' style="width: 240px; height: 120px;margin-top: 10px;" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="width: 60%; float: left; height:150px;border: 0px solid #cccccc; font-size: 13px;">
				<div style="width: 60%; float: left; ">
					<h4 style="width: 300px;" id="content">${course.courseName}</h4>
					<h4>${course.teacher.user.name}&nbsp;&nbsp;&nbsp;&nbsp;${course.teacher.college }</h4>
					<h5>类别：${course.courseType }&nbsp;&nbsp;&nbsp;&nbsp;课程评分：<span style="color: red;">0</span>分</h5>
					<h5>发布时间：${course.courseDate }</h5>
					学员（0）&nbsp;&nbsp;评论（0）&nbsp;&nbsp;
					<a href='#' class="btn  btn-success">点击学习</a>
				</div>
				<div style="float: left; width: 40%; text-align: right;">
					<a href='<c:url value="/admin/teacher/course/edit/${course.id }/publish"></c:url>'  class="btn   btn-success" style="width: 80px;float: right;"  >发布课程</a>
				</div>
				
			</div>
		</div>
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程介绍</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				<c:choose>
				<c:when test="${course.courseDesc!=null}">
					${course.courseDesc}
				</c:when>
				<c:otherwise>
					尚未添加课程介绍
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">目标人群</h4>
		</div>
		<div class="container course detail">
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
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程看点</h4>
		</div>
		<div class="container course detail">
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
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">讲师介绍</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				<div>
					    <c:choose >
							<c:when test="${course.teacher.user.photo_url != null &&course. teacher.user.photo_url != ''}">
								<img src='<c:url value="${course.teacher.user.photo_url }"> </c:url>'style="width: 100px;height:100px; float:left; margin-left:10px"/>
							</c:when>
							<c:otherwise>
								 <img src='<c:url value="/resources/img/avatar/avatar256.png"></c:url>'style="width: 100px;height: 100px; float:left; margin-left:30px"/>
							</c:otherwise>
						</c:choose>
				</div>
				<div style=" margin-left: 100px; float: left;" >
				    <table>
				    <tr >
				    <td style="width:150px;"><h4>${course.teacher.user.name }</h4></td><td>${course.teacher.college }</td>
				    </tr>
				    <tr>
				    <td style="width:150px;height: 30px">${course.teacher.title }</td><td>${course.teacher.school }</td>
				    </tr>
				    <tr>
				    <td style="width:150px;height: 30px">${course.teacher.major }</td>
				    </tr>
				    </table>
			  	</div>
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">课程计划</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				尚未添加计划
			</div>
		</div>
		
		<div class="container course detail" style="background-color: #f7f7f7; margin-bottom: 10px; ">
			<h4 style="margin-left: 50px;">用户评价</h4>
		</div>
		<div class="container course detail">
			<div class="container course detail desc">
				尚未添加内容
			</div>
		</div>
		
	</div>
</div> 