<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
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

<div class="row-fluid custom round">
	<div class="row">
		<h5>课程详细</h5>
	</div>
	<div class="row">
		<!--<table class="table ">
			<tbody>
				<tr><td align="left" width="12%">课程标题：</td><td align="left">${course.courseName}</td></tr>
				<tr><td align="left">发布时间：</td><td align="left">${course.courseName}</td></tr>
				<tr><td align="left">课程概述：</td><td align="left">${course.courseDesc}</td></tr>	
			</tbody>
		</table> -->
		
		<div class="span12" style="text-align: left;">
			<h4 >${course.courseName}</h4>
			<span >发布时间: ${course.courseDate}</span>
		</div>
		<div class="clearfix"></div>
		<hr />
		<div class="span12" style="margin-left: 5px; margin-right: 5px;">${course.courseDesc}</div>
		<div class="clearfix"></div>
		<hr />
		
		<br>
		<div class="row">
			<h5>课程资源</h5>
		</div>
			
		
		<c:choose>
			<c:when test="${resourceCount>0}">
				<table class="table ">
					<thead><tr><th>资源名称</th><th width="20%">课时</th><th width="30%">上传时间</th><th width="10%">下载</th></tr></thead>
					<tbody>
						<c:forEach items="${resourceList}" var="resource">
							<tr>
								<td align="left">${resource.fileName}</td>
								<td align="left">第${resource.resourceOrder}课</td>
								<td align="center">${resource.date}</td>
								<td align="center"><a href='<c:url value="/course/resource/download/${resource.id }"></c:url>'>下载</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise><tr><td colspan="3">尚未添加资源！</td></tr></c:otherwise>
		</c:choose>
		
		
	</div>

</div>

