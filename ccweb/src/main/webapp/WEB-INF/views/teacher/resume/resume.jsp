<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

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
	margin: 20px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 20px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>
<!-- 
<div class="row-fluid custom round">
	<div class="row ">
		<h3>${teacherInfo.name}</h3>	
		<div class="jumbotron">
			<c:if test="${teacherInfo.teacher.isEnterprise == null}">
			<hr />
			<h4>基本信息</h4>
			<address>
			<c:choose>
				<c:when test="${teacherInfo.gender == null||teacherInfo.gender == '' }">
				</c:when>
				<c:otherwise>
				<abbr>性别:</abbr> ${teacherInfo.gender} <br>
				</c:otherwise>
				</c:choose><c:choose>
				<c:when test="${teacherInfo.college == null||teacherInfo.college == '' }">
				</c:when>
				<c:otherwise>
				<abbr>院校:</abbr> ${teacherInfo.college} <br>
				</c:otherwise>
				</c:choose><c:choose>
				<c:when test="${teacherInfo.school == null||teacherInfo.school == '' }">
				</c:when>
				<c:otherwise>
				<abbr>院系:</abbr> ${teacherInfo.school}
				</c:otherwise>
				</c:choose>
				</address>
			</c:if>
		</div>
		<hr />
		<div class="jumbotron">
			<h4>联系方式</h4>
			<address>
				<c:choose>
				<c:when test="${teacherInfo.address == null||teacherInfo.address == '' }">
				</c:when>
				<c:otherwise>
				<abbr title="地址">联系地址:</abbr> ${teacherInfo.address} 
				<br>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${teacherInfo.fax == null||teacherInfo.fax == '' }">
				</c:when>
				<c:otherwise>
				<abbr title="传真">传真:</abbr> ${teacherInfo.fax}
				<br>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${teacherInfo.phone == null||teacherInfo.phone == '' }">
				</c:when>
				<c:otherwise>
				<abbr title="电话">电话:</abbr> ${teacherInfo.phone} 
				<br>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${teacherInfo.email == null||teacherInfo.email == '' }">
				</c:when>
				<c:otherwise>
				<abbr title="电邮">电子邮件:</abbr> <a href="mailto:#">${teacherInfo.email}</a>
				</c:otherwise>
				</c:choose>
			</address>
		</div>
		<hr />
		
	<c:if test="${teacherInfo.teacher.isEnterprise == null}">
			<c:if test="${eduCount != 0 }">
				<div class="jumbotron">
					<h4>教育背景</h4>
					<div id="eduList" style="display: block">
						<c:choose>
							<c:when test="${eduCount>0 }">
								<table class="table">
									<tbody>
										<c:forEach items="${eduInfo}" var="eduInfo">
											<tr>
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
					<h4>工作经历</h4>
					<div id="workList" style="display: block">
						<c:choose>
							<c:when test="${(workCount >0)}">
								<table class="table">
									<tbody>
										<c:forEach items="${workInfo}" var="workInfo">
											<tr>
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
		<c:if test="${teacherInfo.teacher.isEnterprise == null}"><h4>科研成果</h4></c:if>
		<div class="jumbotron">
			<c:if test="${teacherInfo.teacher.isEnterprise == null}">
			<b>论文</b>
		<table class="table">
			<c:choose>
				<c:when test="${thesisCount !=0}">
					<tbody>
						<c:forEach var="thesis" items="${thesisList}">
							<tr>
								<td>
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
		</table>
		<b>项目</b>
		<table class="table">
			<c:choose>
				<c:when test="${projectCount !=0}">
					<tbody>
						<c:forEach var="project" items="${projectList}">
							<tr>
								<td>
								${project.desc}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">无内容</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		<b>专利</b>
		<table class="table">
			<c:choose>
				<c:when test="${patentCount !=0}">
					<tbody>
						<c:forEach var="patent" items="${patentList}">
							<tr>
								<td>
								${patent.desc}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="4">无内容</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		</c:if>
		<b>荣誉</b>
		<table class="table">
			<c:choose>
				<c:when test="${honorCount !=0}">
					<tbody>
						<c:forEach var="honor" items="${honorList}">
							<tr>
								<td>
								${honor.desc}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr><td colspan="2">无内容</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		</div>
	</div>
</div> -->
<div class="row-fluid custom round">
	<div class="row1 ">
		<table width="100%">
			<tr>
				<td align="left" width="25%"><img width="150px" height="150px" src="${avatar_url}" style="margin-top: 10px;"></td>
				<td align="left">
					<h3>${teacherInfo.name}</h3>	
					<address style="line-height: 30px;">
						<c:choose>
						<c:when test="${teacherInfo.address == null||teacherInfo.address == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="地址">联系地址:</abbr> ${teacherInfo.address} 
						<br>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${teacherInfo.fax == null||teacherInfo.fax == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="传真">传真:</abbr> ${teacherInfo.fax}
						<br>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${teacherInfo.phone == null||teacherInfo.phone == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="电话">电话:</abbr> ${teacherInfo.phone} 
						<br>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${teacherInfo.email == null||teacherInfo.email == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="电邮">电子邮件:</abbr> <a href="mailto:#">${teacherInfo.email}</a>
						</c:otherwise>
						</c:choose>
					</address>
				</td>
			</tr>
		</table>
	</div>
	<div class="row ">
		<h4>联系方式</h4>
	</div>
	<div class="row1 ">
		<address>
			<c:choose>
			<c:when test="${teacherInfo.address == null||teacherInfo.address == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="地址">联系地址：</abbr> ${teacherInfo.address} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${teacherInfo.fax == null||teacherInfo.fax == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="传真">传真：</abbr> ${teacherInfo.fax}
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${teacherInfo.phone == null||teacherInfo.phone == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电话">电话：</abbr> ${teacherInfo.phone} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${teacherInfo.email == null||teacherInfo.email == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电邮">电子邮件：</abbr> <a href="mailto:#">${teacherInfo.email}</a>
			</c:otherwise>
			</c:choose>
		</address>
	</div>
	
	<div class="row ">
		<h4>教育背景</h4>
	</div>
	<div class="row1">
		<c:choose>
			<c:when test="${eduCount>0 }">
				<table  cellpadding="4" width="100%" style="margin-top: 10px;">
					<tbody>
						<c:forEach items="${eduInfo}" var="eduInfo">
							<tr>
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
			<c:otherwise>
				尚未添加内容
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="row">
		<h4>工作经历</h4>
	</div>
	<div class="row1">
		<c:choose>
			<c:when test="${eduCount>0 }">
				<table  cellpadding="4" width="100%" style="margin-top: 10px;">
					<tbody>
						<c:forEach items="${workInfo}" var="workInfo">
							<tr>
								<td>
								${workInfo.workDesc}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				尚未添加内容
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="row">
		<h4>发表论文</h4>
	</div>
	<div class="row1">
		<table  cellpadding="4" width="100%" style="margin-top: 10px;">
			<tbody>
			<c:choose>
				<c:when test="${thesisCount >0}">
					<c:forEach var="thesis" items="${thesisList}">
						<tr>
							<td>
								${thesis.content }
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td >无内容</td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	
	<div class="row">
		<h4>研究项目</h4>
	</div>
	<div class="row1">
		<table   cellpadding="4" width="100%" style="margin-top: 10px;">
			<tbody>
			<c:choose>
				<c:when test="${projectCount>0}">
					<c:forEach var="project" items="${projectList}">
						<tr>
							<td>
							${project.desc}
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">无内容</td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	
	<div class="row">
		<h4>获得专利</h4>
	</div>
	<div class="row1">
		<table   cellpadding="4" width="100%" style="margin-top: 10px;">
			<tbody>
			<c:choose>
				<c:when test="${patentCount>0}">
					<c:forEach var="patent" items="${patentList}">
						<tr>
							<td>
							${patent.desc}
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">无内容</td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	
	<div class="row">
		<h4>获得荣誉</h4>
	</div>
	<div class="row1">
		<table   cellpadding="4" width="100%" style="margin-top: 10px;">
			<tbody>
			<c:choose>
				<c:when test="${honorCount>0}">
					<c:forEach var="honor" items="${honorList}">
						<tr>
							<td>
							${honor.desc}
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">无内容</td></tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	
</div>