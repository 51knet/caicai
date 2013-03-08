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
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid.custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}

.row-fluid.custom .row1 {
	margin: 10px 40px;
	color: cccccc;
}

.row-fluid.custom .row1 .bb{
	border-bottom: dashed #cccccc 1px;
}
.row-fluid.custom .row2 {
	margin: 10px 40px;
	border-bottom: solid #cccccc 1.5px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>公告详细</h4>
	</div>
	<div class="row1">
		<div>
		<label ><b style="font-size: 16px;margin-right: 10px;">${announcement.title}</b >发表于: ${announcement.date}</label>
		</div>
		<div  style="text-align: center;margin-top: 20px;">${announcement.content}</div>
		<div class="row2" style="width: 680px;margin-left: -2px;"><span>${comment.content} </span></div>
	</div>

</div>

