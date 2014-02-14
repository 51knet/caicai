<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function sessionUserNull(){
		alert("请登录后查看详细信息");
	}
</script>
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
 .selete_filter{
 	margin-top:10px;
	text-align: left;
}
.patent{
	width:100%;
	 
}
</style>
 <div class="container patent">
	<c:choose>
		<c:when test="${searchpatentCount <=0}">
			<h4 style="margin-left:60px;">未搜索到专利</h4>
		</c:when>
		<c:otherwise>
			 <table  style="width: 100%; " cellpadding="8"  >
				 <thead>
					<tr class="titlebg">
					<th  align="left" class="td_right_line">项目名称</th>
					<th width="20%"   align="left" class="td_right_line">所属领域</th>
					<th  width="20%" align="left" class="td_right_line">合作方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr class="bLine_dash">
						<td>
							<a href="<c:url value="/technology/view/${ page.id}"></c:url>"><img src="<c:url value='/resources/img/default/icon_new.png'></c:url>" >  ${page.techName}</a>
						</td>
						<td>
							${page.techField}
						</td>
						<td>
							${page.cooperation}
						</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
			    	<tr><td colspan="5">
			        	<c:choose>
							<c:when test="${searchParam != null }">
								  <jsp:include page="/WEB-INF/views/_shared/pagination_query.jsp"></jsp:include>
							</c:when>
							<c:otherwise>
							 	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
							</c:otherwise>
						</c:choose>
			   		 </td></tr>
				</tfoot>
				</table>
		</c:otherwise>
	</c:choose>
 </div>


