<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.limitTable{
	margin-top: 10px; 
	width:750px; 
	table-layout:fixed
}

 .limitTd{
	word-wrap:break-word; word-break:break-all;
}
 .row {
	margin: 20px 20px;
	border-bottom: solid #cccccc 1.5px;
}
.content {
	margin: 20px 40px;
	width:750px;
	max-width:750px;
	word-wrap:break-word;	
}
</style>

<div class="cont_block">
 	<div class="_titles">
 		<img  src="<c:url value='/resources/img/default/resumetitle.png' ></c:url>">
 	</div>
	<div >
		<table width="95%">
			<tr>
				<td align="left" width="20%">
					<c:url var="avatar_url" value="${userInfo.avatar}"></c:url>
					<img src="${avatar_url}" style="margin-top: 10px; width: 150px;">
				</td>
				<td width="3%"></td>
				<td align="left">
					<h3>${userInfo.name}</h3>	
					<address style="line-height: 30px;">
						<c:choose>
						<c:when test="${userInfo.teacher.college == null||userInfo.teacher.college == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="所属高校">所属高校：</abbr> ${userInfo.teacher.college} 
						<br>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${userInfo.teacher.school == null||userInfo.teacher.school == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="所属院系">所属院系：</abbr> ${userInfo.teacher.school}
						<br>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${userInfo.teacher.major == null||userInfo.teacher.major== '' }">
						</c:when>
						<c:otherwise>
						<abbr title="研究方向">研究方向：</abbr> ${userInfo.teacher.major} 
						<br>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${userInfo.teacher.title == null||userInfo.teacher.title == '' }">
						</c:when>
						<c:otherwise>
						<abbr title="职称职务">职称/职务：</abbr>${userInfo.teacher.title}
						</c:otherwise>
						</c:choose>
					</address>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="row ">
		<h4>联系方式</h4>
	</div>
	<div class="content ">
		<address>
			<c:choose>
			<c:when test="${userInfo.address == null||userInfo.address == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="地址">联系地址：</abbr> ${userInfo.address} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.fax == null||userInfo.fax == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="传真">传真：</abbr> ${userInfo.fax}
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.phone == null||userInfo.phone == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电话">电话：</abbr> ${userInfo.phone} 
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.email == null||userInfo.email == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电邮">电子邮件：</abbr> <a href="mailto:#">${userInfo.email}</a>
			</c:otherwise>
			</c:choose>
		</address>
	</div>
	
	<div class="row ">
		<h4>教育背景</h4>
	</div>
	<div class="content">
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
	<div class="content">
		<c:choose>
			<c:when test="${workCount>0 }">
				<table  cellpadding="4"    class="limitTable">
					<tbody>
						<c:forEach items="${workInfo}" var="workInfo">
							<tr>
								<td class="limitTd">
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
	<div class="content">
		<table  cellpadding="4"  class="limitTable">
			<tbody>
			<c:choose>
				<c:when test="${thesisCount >0}">
					<c:forEach var="thesis" items="${thesisList}">
						<tr>
							<td class="limitTd">
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
	<div class="content">
		<table   cellpadding="4" class="limitTable">
			<tbody>
			<c:choose>
				<c:when test="${projectCount>0}">
					<c:forEach var="project" items="${projectList}">
						<tr>
							<td class="limitTd">
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
	<div class="content">
		<table   cellpadding="4" class="limitTable">
			<tbody>
			<c:choose>
				<c:when test="${patentCount>0}">
					<c:forEach var="patent" items="${patentList}">
						<tr>
							<td class="limitTd">
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
	<div class="content">
		<table   cellpadding="4" class="limitTable">
			<tbody>
			<c:choose>
				<c:when test="${honorCount>0}">
					<c:forEach var="honor" items="${honorList}">
						<tr>
							<td class="limitTd">
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
				</td>
			</tr>
		</table>
	</div>
	
 </div>
