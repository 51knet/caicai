<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
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
</style>
<div class="row-fluid custom round">
	<div class="round header"><h5>公告</h5></div>
	<hr/>
	<div class="row-fluid">
		${user.announcementContext}
	</div>
</div>

<div class="row-fluid custom round">
	<div class="row-fluid">
		<div class="round header"><h5>科研成果</h5></div>
		<hr/>
		...
	</div>
</div>

<div class="row-fluid custom round">
	<div class="row-fluid">
		<h5>课程资料</h5>
	</div>
	<hr/>
	...
</div>
<div class="row-fluid custom round">
	<div class="row-fluid">
		<h5>博客</h5>
	</div>
	<hr/>
	...
</div>