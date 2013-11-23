<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.titlebg{
	background-color:#ccdfa8; 
	font-size: 14px;
	width: 100%;
}
</style>
<div class="selete_filter">
</div>
<div class="container title"  >
	 	 <table >
	 	 	<tr>
	 	 		<td width="20%" align="center"><h4>所有专利（${patentCount}）</h4></td>
	 	 		<td></td>
	 	 	</tr>
	 	 </table>
 </div>
 <div class="container user-course">
	 <c:if test="${patentCount <=0}">
	  <br>
	 	<h4 style="margin-left: 70px;">未搜索到专利</h4>
	 </c:if>
	 <br>

	 <table  style="width: 90%; margin-left: 60px;" cellpadding="10" >
		<tr class="titlebg"><th align="left"><b>专利号</b></th><th align="left"><b>专利名称</b></th><th align="left"><b>专利类别</b></th><th align="left"><b>应用领域</b></th><th align="left" width=10%><b>公开日期</b></th></tr>
			<tbody>
				<c:forEach items="${page.content}" var="page">
					<tr class="bb">
						<td width="20%"><a href="<c:url value="/patent/view/${ page.patentNum}"></c:url>">${page.patentNum}</a></td>
						<td >
							${page.patentName}
						</td>
						<td >
							${page.patentType.typeName}
						</td>
						<td >
							${page.patentField}
						</td>
						<td  width="20%">${page.publishDate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
		    	<tr><td colspan="3">
		        <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
		   		 </td></tr>
			</tfoot>
		</table>

 </div>



