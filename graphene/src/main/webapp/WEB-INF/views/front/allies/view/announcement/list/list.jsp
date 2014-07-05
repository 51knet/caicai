<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
 
</style>

<div class="cont_block">
 	<div class="_titles">
 		<img  src="<c:url value='/resources/img/default/alliestitle.png' ></c:url>">
 	</div>
	<table style=" width: 95%; "  cellpadding="5" align="center">
		<tr class="titlebg" ><th align="left"><b>标题</b></th><th width="15%" align="left"><b>发布时间</b></th></tr>
		<tbody>
			<c:forEach items="${page.content}" var="page">
				<tr  class="btl"><td align="left" >
					<div style="width: 660px;" id="contentlimit"><a href="<c:url value="/allies/${userInfo.id}/announcement/view/${page.id}"></c:url>">
					${page.title}</a></div>
				</td>
				<td >${page.date}</td>
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
