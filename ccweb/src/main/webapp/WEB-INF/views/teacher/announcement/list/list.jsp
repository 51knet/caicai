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
	<div class="row">
		<h5>教师公告</h5>
	</div>
	<div class="row">
		<table class="table">
			<thead><tr><th >标题</th><th >内容</th><th >发布时间</th></tr></thead>
			<tbody>
				<c:forEach items="${page.content}" var="page">
					<tr><td align="left">${page.title}</td><td align="center">${page.content}</td><td align="center">${page.date}</td>
					</tr>
				</c:forEach>
			</tbody>		 
			<tfoot>
		    	<tr><td colspan="3" align="right">
		        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
		   		 </td></tr>
			</tfoot>
		</table>
	</div>	 
</div>
