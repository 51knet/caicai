<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--

//-->

	function showAnnoForm(){
		var annoForm = document.getElementById("annoForm");
		annoForm.style.display="block";
	}
	function hidAnnoForm(){
		var annoForm = document.getElementById("annoForm");
		annoForm.style.display="none";
	}
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
	<thead><tr><th colspan="4">全部公告</th></tr></thead>
	<tbody>
		<tr><td>公告标题</td><td>公告内容</td><td>发布时间</td></tr>
		<c:forEach items="${page.content}" var="page">
			<tr><td align="left">${page.title}</td><td align="center">${page.content}</td><td align="center">${page.date}</td>
			</tr>
		</c:forEach>
	</tbody>		 
		<tfoot>
	    	<tr><td colspan="4" align="right">
	        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
	</table>	 
</div>
