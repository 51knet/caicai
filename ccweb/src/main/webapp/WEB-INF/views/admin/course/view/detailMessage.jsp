<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.row-fluid.custom.basic {
	margin-left: 4%;
	float: left;
}
</style>
<div style="margin-top: 10px;">
	<a href="#">课程详细信息</a>
	<hr />
	<form action="">
		<div>
			<table style="margin-left: 8%;">
				<tr>
					<td><h5>目标人群:</h5></td>
					<td class="row-fluid custom basic"><textarea rows="6" cols="8" style="width: 459px;margin-top: 20px;"></textarea></td>
				</tr>
				<tr>
					<td><h5>课程看点:</h5></td>
					<td class="row-fluid custom basic"><textarea rows="6" cols="8" style="width: 459px;margin-top: 20px;"></textarea></td>
				</tr>
				<tr><td style="text-align: left;margin-left: 80px;float: left;margin-top: 40px;" colspan="2" ><button class="btn btn-large btn-success">保存</button></td></tr>
			</table>
		</div>
	</form>
</div>