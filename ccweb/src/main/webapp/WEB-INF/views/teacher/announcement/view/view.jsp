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
		<table class="table ">
			<tbody>
				<tr><td align="left" width="12%">公告标题：</td><td align="left">${announcement.title}</td></tr>
				<tr><td align="left">发布时间：</td><td align="left">${announcement.date}</td></tr>
				<tr><td align="left">公告内容：</td><td align="left">${announcement.content}</td></tr>	
			</tbody>
		</table>
	</div>

</div>

