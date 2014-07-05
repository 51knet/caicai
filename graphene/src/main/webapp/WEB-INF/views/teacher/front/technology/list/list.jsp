<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
 .titlebg{
	background-color:#3d4f65; 
	font-size: 14px;
	width: 100%;
	color: #fff;
	font-weight: bold;
}
.td_right_line{
	background-image: url(' <c:url value="/resources/img/default/blueline.png" ></c:url> ' );
	background-position: right center;
	background-repeat: no-repeat;
}
</style>

<div class="cont_block">
 	<div class="_titles">
 		<img  src="<c:url value='/resources/img/default/title2.png' ></c:url>">
 	</div>
	<table style=" width: 95%; "  cellpadding="5" align="center">
		<thead>
			<tr class="titlebg">
				<th  align="left" class="td_right_line">项目名称</th>
				<th width="20%"   align="left" class="td_right_line">所属领域</th>
				<th  width="20%" align="left" class="td_right_line">合作方式</th>
				</tr>
			</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr class="btl">
						<td>
							<div class="limit_block"><a href="<c:url value="/teacher/${userInfo.id }/technology/view/${ page.id}"></c:url>"> ${page.techName}</a></div>
						</td>
						<td>
							${page.techField}
						</td>
						<td>
							<div class="limit_block">${page.cooperation}</div>
						</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
			    	<tr><td colspan="3">
							 <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			   		 </td></tr>
				</tfoot>
	</table>
 </div>
