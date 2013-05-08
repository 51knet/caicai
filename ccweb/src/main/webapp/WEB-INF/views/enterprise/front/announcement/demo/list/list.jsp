<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
}

.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 10px 0px 0px 0px;
}

.row-fluid.custom .anno {
	margin: 20px 0px 0px 0px;
}

.row-fluid.custom .anno .bb{
	border-bottom: dashed #cccccc 1px;
}

</style>

<div class="row-fluid custom">
	<div class="anno">
		<c:choose>
			<c:when test="${annoCount>0}">
				<table cellpadding="4" width="100%" style="margin-top: 10px;">
					<tbody>
						<tr>
							<td width="50%"  align="left" valign="top" style="background-color:#59abda; height:220px;">
								<c:forEach var="annophoto" items="${annoPhoto}" begin="0" end="1">
									<a href="<c:url value="/enterprise/${teacherInfo.id}/announcement/view/${annophoto.id}"></c:url>"><img src='<c:url value="${annophoto.photourl}" ></c:url>'  /></a>
								</c:forEach>
							</td>
							<td width="40%" align="left" valign="top">
								<table cellpadding="3" width="100%" >
									<c:forEach var="anno" items="${annolist}" begin="0" end="2">
										<tr  class="bb">
											<td    align="left" valign="top">
												<div style="width: 160px" id="content"><a href="<c:url value="/enterprise/${teacherInfo.id}/announcement/view/${anno.id}"></c:url>">${anno.title}</a></div>
											</td>
											<td  align="left" valign="top">
												${anno.date}
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
			<br>
				无内容
			</c:otherwise>
		</c:choose>
	</div>	 
</div>
