<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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