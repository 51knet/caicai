<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
}

.row-fluid.custom .row > h3 {
	color: #000;
	border-bottom: solid #cccccc 0px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	background-image: url('<c:url value="/resources/img/default/faqline.png"></c:url>');
	background-position: left bottom;
	background-repeat: no-repeat;
}


.row-fluid.custom .row {
	margin: 0px 40px 0px 40px;
}

.row-fluid.custom .row .content {
	line-height:25px;
	margin: 10px 10px;
}


</style>

<div class="row-fluid custom round">
	<div class="row">
		<h3>联系我们</h3>
	</div>
	<div class="row ">
	<br>
		<div class="content">
			<h5>客户服务</h5>
			联系人：方经理<br>
			电话：021-55520056-801<br>
			Email：funny@51knet.com<br>
			<br>
			<h5>商务合作</h5>
			联系人：张经理<br>
			电话：021-55520056-802<br>
			Email：service@51knet.com <br><br>
			公司地址：上海市杨浦区国定路335号（复旦大学对面） 杨浦创业园1号楼5007
		</div>
	</div>
</div>
