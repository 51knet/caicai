<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">

</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 20px 0px 0px 0px;
}
.row-fluid.custom .row {
	margin: 0px 40px 0px 40px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed  1px #ccc;
}
</style>

<div class="row-fluid custom round ccc_border">
		<div class="row">
			<h4>教师简介</h4>
		</div>
		<div class="row" style="max-width: 100%; max-height: 190px; overflow: hidden;border-bottom: 1px dashed #ccc;padding-bottom: 10px;">
				<table  cellpadding="4" width="100%" style="margin: 10px 0px; ">
					<tbody>
						<c:forEach items="${eduInfo}" var="eduInfo">
							<tr>
							<td>
							${eduInfo.educationDesc}
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div style="text-align: right;" class="row">
			<br><a href="<c:url value="/teacher/${teacherInfo.id}/resume"></c:url>"> 查看所有>></a></div>
</div>

<div class="row-fluid custom round ccc_border">
	<div class="row"><h4>公告 </h4></div>
	
	<div class="row">
		<c:choose>
			<c:when test="${annoCount>0}">
				<table cellpadding="4" width="100%" style="margin-top: 10px;">
					<tbody>
						<c:forEach var="anno" items="${annolist}" begin="0" end="2">
							<tr>
								<td  width="80%" class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a>
								</td>
								<td class="bb">
									${anno.date}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
			<br>
				无内容
			</c:otherwise>
		</c:choose>
		<div style="text-align: right;">
			<c:if test="${annoCount>3}"><br><a href="<c:url value="/teacher/${teacherInfo.id}/announcement/list"></c:url>"> 查看所有>></a></c:if></div>
		</div>
</div>

<!-- teacher resource -->


<div class="row-fluid custom round ccc_border">
	<div class="row">
		<h4>专利</h4>
	</div>
	<div class="row">
	<table cellpadding="4" width="100%" style="margin-top: 10px;">
		<tbody>
			<c:forEach var="patent" items="${patentPage.content}">
			<tr>
				<td width="80%" class="bb"><a href="<c:url value="/teacher/${teacherInfo.id}/patent/view?id=${patent.patentNum}"></c:url>">${patent.patentNum}</a></td>
				<td class="bb">
				${patent.publishDate}
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="text-align: right;">
			<br><a href="<c:url value="/teacher/${teacherInfo.id}/patent/list"></c:url>"> 查看所有>></a></div>
	</div>
</div>


