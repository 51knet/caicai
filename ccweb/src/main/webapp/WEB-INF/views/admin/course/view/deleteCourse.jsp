<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.price {
	margin-left: 6%;
	float: left;
}

.row-fluid.custom.price.power {
	margin-left: 10%;
	float: left;
	margin-top: 20px;
}
</style>
<div style="margin-top: 10px;">
	<a href="#">取消课程</a>
	<hr />
	<div style="margin-top: 30px;">
	<form action="deletecoursemodify">
		<div style="margin-left: 40px;">
				<label>如果你删除了本课程，本课程将不会恢复。请确认后再删除。</label> 
			<span class="row-fluid custom price power">
				<button class="btn btn-large btn-success">删除</button>
			</span>
		</div>
	</form>
	</div>
</div>