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
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="row ">
		<h4>联系方式</h4>
	</div>
	<div class="content ">
		<address style="line-height: 30px;">
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
			<abbr title="传真">联系传真：</abbr> ${userInfo.fax}
			<br>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${userInfo.phone == null||userInfo.phone == '' }">
			</c:when>
			<c:otherwise>
			<abbr title="电话">联系电话：</abbr>  ${userInfo.phone} 
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
		<table class="blue" width="100%" cellpadding="5">
			<thead>
				<tr>
					<th>教育程度</th>
					<th>学校</th>
					<th>学院</th> 
					<th>专业</th> 
					<th>班级</th>
					<th>教师</th>
					<th>入学时间</th> 
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eduInfo}" var="eduInfo">
					<tr>
						<td align="center"><c:forEach items="${levelmap}" var="map">
							<c:if test="${map.key == eduInfo.level }">${map.value }</c:if>
						</c:forEach></td>
						<td align="center">${eduInfo.school}</td>
						<td align="center">${eduInfo.college}<c:if test="${eduInfo.college == null }"> 无</c:if></td>
						<td align="center">${eduInfo.major}<c:if test="${eduInfo.major == null}"> 无</c:if></td>
						<td align="center">${eduInfo.classNum}</td>
						<td align="center">${eduInfo.teacherNam}</td>
						<td align="center" >${eduInfo.startTime}</td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="row">
		<h4>工作经历</h4>
	</div>
	<div class="content">
		<table class="blue" width="100%" cellpadding="5">
			<thead>
				<tr>
					<th>公司名称</th>
					<th>公司职位</th>
					<th>起止时间</th> 
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${workInfo}" var="workInfo">
					<tr>
						<td align="center">${workInfo.company}</td>
						<td align="center">${workInfo.position}</td>
						<td align="center" width="25%">${workInfo.startTime} 至 ${workInfo.endTime}</td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		</td>
		</tr>
	</table>
	</div>
	
 </div>
