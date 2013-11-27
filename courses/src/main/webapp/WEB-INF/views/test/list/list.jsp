<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function selectType() {
		var sel = document.getElementById("type");
		var opt = sel.options;
		for ( var i = 0; i < opt.length; i++) {
			if (opt[i].selected) {
				var typeName = opt[i].value;
				//alert(typeName);
				window.location.href = '<c:url value="/patent/'+ typeName + '/list"></c:url>';
			}
		}
	}
</script>
<style>
.titlebg{
	background-color:#ccdfa8; 
	font-size: 14px;
	width: 100%;
}
</style>
<div class="selete_filter">
	<select id="type" onchange="selectType()">
		<option value="all" >全部类别</option>
		<c:forEach items="${fieldList}" var="field">
			<c:choose>
				<c:when test="${patentField == field.fieldName}">
					<option value="${field.fieldName }" selected>${field.fieldName }</option>
				</c:when>
				<c:otherwise>
					<option value="${field.fieldName }">${field.fieldName }</option>
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
	</select>
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
	<c:choose>
		<c:when test="${patentCount <=0}">
			<h4 style="margin-left: 70px;">未搜索到专利</h4>
		</c:when>
		<c:otherwise>
			 <table  style="width: 90%; margin-left: 60px;" cellpadding="8"  >
				 <thead>
					<tr class="titlebg">
					<th  width="20%" align="left"><b>专利号</b></th>
					<th   align="left"><b>专利名称</b></th>
					<th  width="10%" align="left"><b>专利类别</b></th>
					<th  width="12%"  align="left"><b>应用领域</b></th>
					<th  width='10%' align="left"><b>公开日期</b>
					</th></tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr class="bb">
						<td><a href="<c:url value="/patent/view/${ page.patentNum}"></c:url>">${page.patentNum}</a></td>
						<td>
							${page.patentName}
						</td>
						<td>
							${page.patentType.typeName}
						</td>
						<td >
							${page.patentField}
						</td>
						<td>${page.publishDate}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
			    	<tr><td colspan="5">
			        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			   		 </td></tr>
				</tfoot>
				</table>
		</c:otherwise>
	</c:choose>
 </div>



