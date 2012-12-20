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
	margin: 0px 5px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h5>公告详细</h5>
	</div>
	<div class="row">
		<div class="span12" style="text-align: center;">
			<h4 >${announcement.title}</h4>
			<span >发布时间: ${announcement.date}</span>
		</div>
		<div class="clearfix"></div>
		<hr />
		<div class="span12" style="margin-left: 5px; margin-right: 5px;">${announcement.content}</div>
		<div class="clearfix"></div>
		<hr />
	</div>

</div>

