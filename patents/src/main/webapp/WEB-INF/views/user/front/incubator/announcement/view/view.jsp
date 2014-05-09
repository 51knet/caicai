<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
.row-fluid.custom .row .content{
	margin-top: 20px;
}
.row-fluid.custom .row .bb{
	border-bottom: dashed #cccccc 1px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>新闻详细</h4>
	</div>
	<div class="row">
		<div >
			<label><h4>${announcement.title}</h4></label>
			<label>发表于：${announcement.date}</label> 
		</div>
		<div  class="content">${announcement.content}</div>
		<hr>
	</div>

</div>

