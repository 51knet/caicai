<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
}
.row-fluid.custom .row {
	margin: 10px 40px 0px 40px;
}
.row-fluid.custom .row .content{
	margin-top: 20px;
}
.row-fluid.custom .row .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>


<div class="cont_block">
 	<div class="_titles">
 		<img  src="<c:url value='/resources/img/default/title1.png' ></c:url>">
 	</div>
	<div class="row-fluid custom round">
		<div class="row">
			<div class="bb">
				<label><h4>${announcement.title}</h4></label>
				<label>发表于：${announcement.date}</label> 
			</div>
			<div  class="content">${announcement.content}</div>
		</div>

	</div>
 </div>

