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

.row-fluid.custom .content {
	margin: 20px 40px;
	width:680px;
	word-wrap:break-word;
	
}

.row-fluid.custom .content .bb{
	border-bottom: dashed #cccccc 1px;
}


.row-fluid.custom .content .limitTable{
	margin-top: 10px; 
	width:675px; 
	table-layout:fixed
}

.row-fluid.custom .content .limittable ..limitTd{
	word-wrap:break-word; word-break:break-all;
}
</style>
<div class="row-fluid custom round">
	<div class="content ">
		<table width="100%">
			<tr>
				<td align="left" width="25%">
					<c:url var="avatar_url" value="${teacherInfo.avatar}"></c:url>
					<img width="150px" height="150px" src="${avatar_url}" style="margin-top: 10px;">
				</td>
				<td align="left">
					<h3>${teacherInfo.name}</h3>	
				</td>
			</tr>
		</table>
	</div>
	<div class="row ">
		<h4>联系方式</h4>
	</div>
	<div class="content ">
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
	
	<div class="row">
		<h4>公司发展</h4>
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
	
</div>