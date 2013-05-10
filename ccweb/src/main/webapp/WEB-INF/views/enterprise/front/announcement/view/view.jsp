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
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #f77605 1.5px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	padding:0px 10px 5px 10px;
}

.row-fluid.custom .row {
	margin: 0px 10px 0px 10px;
}
.row-fluid.custom .row .photo{
	margin: 10px 0px 0px 0px;
	text-align: center;
}
.row-fluid.custom .row .content{
	margin: 10px 20px 10px 20px;
	text-align: left;
}
</style>

<div class="row-fluid custom">
	<div class="row">
		<h4>公告详细</h4>
	</div>
	<div class="row"  style="border: solid 1px #f77605;">
		<div class="content">
			<label><h4>${announcement.title}</h4></label>
			<label>发表于：${announcement.date}</label> 
		</div>
		<div class="content">${announcement.content}</div>
	</div>

</div>

