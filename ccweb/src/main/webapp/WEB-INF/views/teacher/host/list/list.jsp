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
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>

<div class="row-fluid custom round">
	<table class="table table-bordered">
	<thead><tr><th colspan="4">关注人名称</th></tr></thead>
	<tbody>
		<tr>
			<td>	<c:forEach items="${sessionScope.hostList}" var="host">
			<div style="float: left; padding-right: 10px;"><a href='<c:url value="/teacher/${sessionScope.host.id}"></c:url>'> ${sessionScope.host.name}</a></div>
		</c:forEach></td>
		</tr>
	
	</tbody>		 
		<tfoot>
	    	<tr><td colspan="4" align="right">
	        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
	</table>	 
</div>
