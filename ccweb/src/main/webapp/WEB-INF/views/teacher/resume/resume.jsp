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
		<div class="jumbotron">
			<c:if test="${teacherInfo.teacher.isEnterprise == null}">
			<hr />
			<h5>基本信息</h5>
				<address>
					 <abbr>性别:</abbr> ${teacherInfo.gender} <br> 
				 	<abbr>院校:</abbr> ${teacherInfo.college} <br> 
					 <abbr>院系:</abbr> ${teacherInfo.school}
				</address>
			</c:if>
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
		
	<c:if test="${teacherInfo.teacher.isEnterprise == null}">
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
											<tr>
											<td></td>
											<td>详细描述：</td>
											<td>
											${eduInfo.educationDesc}
											</td>
											<td></td>
											<td></td>
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
											<tr>
											<td></td>
											<td>详细描述：</td>
											<td>
											${workInfo.workDesc}
											</td>
											<td></td>
											<td></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
						</c:choose>
					</div>
				</div>
			</c:if>
		</c:if>
	</div>
	<div class="row">
		<c:if test="${teacherInfo.teacher.isEnterprise == null}"><h5>科研成果</h5></c:if>
		<div class="jumbotron">
			<c:if test="${teacherInfo.teacher.isEnterprise == null}">
			<b>论文</b>
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
			<!--<c:if test="${thesisCount > 2}"><tr><td align="right"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/thesis/list"></c:url>"> 查看所有>></a></div></td></tr></c:if> -->
		</table>
		<b>项目</b>
		<table class="table">
			<c:choose>
				<c:when test="${projectCount !=0}">
					<thead>
						<tr><th >项目名称</th><th >项目来源</th><th width=15%>开始时间</th><th width=15%>结束时间</th></tr>
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
							<tr>
								<td></td>
								<td>详细描述：</td>
								<td>
								${project.desc}
								</td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">无内容</td></tr>
				</c:otherwise>
			</c:choose>
			<!-- <c:if test="${projectCount > 2}"><tr><td align="right" colspan="3"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/project/list"></c:url>"> 查看所有>></a></div></td></tr></c:if> -->
		</table>
		<b>专利</b>
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
							<tr>
								<td></td>
								<td>详细描述：</td>
								<td>
								${patent.desc}
								</td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="4">无内容</td></tr>
				</c:otherwise>
			</c:choose>
			<!-- <c:if test="${patentCount > 2}"><tr><td align="right" colspan="4"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/patent/list"></c:url>"> 查看所有>></a></div></td></tr></c:if> -->	
		</table>
		</c:if>
		<b>荣誉</b>
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
							<tr>
								<td>详细描述：</td>
								<td>
								${honor.desc}
								</td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="2">无内容</td></tr>
				</c:otherwise>
			</c:choose>
			<!-- 	<c:if test="${honorCount > 2}"><tr><td align="right" colspan="2"><div style="text-align: right;"><a href="<c:url value="/teacher/${teacherInfo.id}/achievement/honor/list"></c:url>"> 查看所有>></a></div></td></tr></c:if> -->	
		</table>
		</div>
	</div>
</div>