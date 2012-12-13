<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/js/myutil.js"></script>
<script type="text/javascript">
	
</script>
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
		<div class="row"><h5>论文</h5></div>
	<table class="table">
		<c:choose>
			<c:when test="${thesisCount !=0}">
				<thead>
					<tr><th >论文内容</th></tr>
				</thead>
				<tbody>
					<c:forEach var="thesis" items="${thesisList}">
						<tr>
							<td><!-- <a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a> --> 
								${thesis.content }
							</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td >无内容</td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${thesisCount > 2}"><tr><td align="right"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/thesis/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table>
	
	<div class="row"><h5>项目</h5></div>
	<table class="table">
		<c:choose>
			<c:when test="${projectCount !=0}">
				<thead>
					<tr><th >项目名称</th><th >项目来源</th><th >开始时间</th><th >结束时间</th></tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${projectList}">
						<tr>
							
							<td  ><!-- <a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a> --> 
								${project.title}</td>
							<td  >${project.source}</td>
							<td  >${project.startTime}</td>
							<td  >${project.endTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td colspan="3">无内容</td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${projectCount > 2}"><tr><td align="right" colspan="3"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/project/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table>
	<div class="row"><h5>专利</h5></div>
	<table class="table">
		<c:choose>
			<c:when test="${patentCount !=0}">
				<thead>
						<tr><th>发明人</th><th>专利名称</th><th>专利类型</th><th>专利申请号</th></tr>
				</thead>
				<tbody>
					<c:forEach var="patent" items="${patentList}">
						<tr>
							<td >${patent.inventer}</td>
							<td >${patent.name}</td>
							<td >${patent.type}</td>
							<td >${patent.number}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4">无内容</td></tr>
			</c:otherwise>
		</c:choose>
			<c:if test="${patentCount > 2}"><tr><td align="right" colspan="4"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/patent/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table>
	<div class="row"><h5>荣誉</h5></div>
	<table class="table">
		<c:choose>
			<c:when test="${honorCount !=0}">
				<thead>
					<tr><th >荣誉名称</th><th>获奖原因</th></tr>
				</thead>
				<tbody>
					<c:forEach var="honor" items="${honorList}">
						<tr>
							<td><!-- <a href="<c:url value="/teacher/${teacherInfo.id}/blog/view/${blogPost.id}"></c:url>"> ${blogPost.title} </a> --> 
								${honor.name }
							</td>
							<td>
								${honor.reason}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tr><td colspan="2">无内容</td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${honorCount > 2}"><tr><td align="right" colspan="2"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/honor/list"></c:url>"> 查看所有>></a></div></td></tr></c:if>
	</table>
	</div>
</div>
