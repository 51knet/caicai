<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	/*background: #FAFAFB;*/
}

.row-fluid.custom .row {
	margin: 0px 10px 0px 10px;
	border: solid 1px #f77605; padding:10px 10px 10px 10px
}
.row-fluid.custom .row .content {
	width: 85%; margin: 15px auto;
}

.row-fluid.custom .row  .title{
		margin: 10px 30px 0px 30px;
}

.row-fluid.custom .row  .title > h4{
	color: #80b029;
	border-bottom: solid #f77605 1.5px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	padding:0px 10px 5px 10px;
}



</style>


<div class="row-fluid custom">
	<div class="row" >
		<div class="title">
			<h4>${announcement.title}</h4>
		</div>
		<div   class="content" >
	 		<label>发表于：${announcement.date}</label> 
	 		${announcement.content}
		</div>
	</div>
	<div style="margin: 0px 0px 0px 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>	 
</div>

