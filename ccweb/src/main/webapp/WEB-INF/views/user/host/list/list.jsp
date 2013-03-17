<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--

//-->


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
	margin: 10px 0px 0px 0px;
}

.row-fluid.custom .row {
	margin: 10px 40px 0px 40px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed #cccccc 1px;
}

</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>关注人名称</h4>
	</div>
	<div class="row">
		<table width="100%"  cellpadding="5">
			<tbody>
				<tr class="bb">
					<td align="left"  >
						<c:forEach items="${hostList}" var="host">
							<div style="float: left; padding-right: 10px;"><a href='<c:url value="/teacher/${host.id}"></c:url>'> ${host.name}</a></div>
						</c:forEach>
					</td>
				</tr>
			</tbody>		 
			<tfoot>
		    	<tr><td >
		        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
		   		 </td></tr>
			</tfoot>
		</table>
	</div>	 
</div>




