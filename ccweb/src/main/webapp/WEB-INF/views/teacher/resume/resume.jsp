<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

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
	margin: 40px 60px;
}
</style>

<div class="row-fluid custom round">
	<div class="row ">
		<h3>${teacherInfo.name}</h3>
		<hr />
		<div class="jumbotron">
			<h5>基本信息</h5>
			<address>
				<abbr>性别:</abbr> ${teacherInfo.gender} <br> 
				<abbr>院校:</abbr> ${teacherInfo.college} <br> 
				<abbr>院系:</abbr> ${teacherInfo.school}
			</address>
		</div>
		<hr />
		<div class="jumbotron">
			<h5>联系方式</h5>
			<address>
				<c:if test="${teacherInfo.address != null }">
				<abbr title="地址">联系地址:</abbr> ${teacherInfo.address} 
				<br>
				</c:if>
				<c:if test="${teacherInfo.fax != null }">
				<abbr title="传真">传真:</abbr> ${teacherInfo.fax}
				<br>
				</c:if>
				<c:if test="${teacherInfo.phone != null }">
				<abbr title="电话">电话:</abbr> ${teacherInfo.phone}
				<br>
				</c:if>
				<abbr title="电邮">电子邮件:</abbr> <a href="mailto:#">${teacherInfo.email}</a>
			</address>
		</div>
		<hr />
		<c:if test="${eduCount != 0 }">
			<div class="jumbotron">
				<h5>教育背景</h5>
				<div id="eduList" style="display: block">
					<c:choose>
						<c:when test="${eduCount>0 }">
							<table class="table">
								<thead>
									<tr>
										<th>学校名称</th>
										<th>学院名称</th>
										<th>学历</th>
										<th>起止时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${eduInfo}" var="eduInfo">
										<tr>
											<td align="center">${eduInfo.school}</td>
											<td align="center">${eduInfo.college}</td>
											<td align="center">${eduInfo.degree}</td>
											<td align="center">${eduInfo.startTime} - ${eduInfo.endTime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
					</c:choose>
				</div>
			</div>
			<hr />
		</c:if>
		<c:if test="${workCount != 0 }">
			<div class="jumbotron">
				<h5>工作经历</h5>
				<div id="workList" style="display: block">
					<c:choose>
						<c:when test="${(workCount >0)}">
							<table class="table">
								<thead>
									<tr>
										<th>公司名称</th>
										<th>部门名称</th>
										<th>职位</th>
										<th>起止时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${workInfo}" var="workInfo">
										<tr>
											<td align="center">${workInfo.company}</td>
											<td align="center">${workInfo.department}</td>
											<td align="center">${workInfo.position}</td>
											<td align="center">${workInfo.startTime} - ${workInfo.endTime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
					</c:choose>
				</div>
			</div>
		</c:if>
	</div>
</div>