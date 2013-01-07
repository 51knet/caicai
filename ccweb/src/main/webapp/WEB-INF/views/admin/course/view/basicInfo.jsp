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
	<a href="#">课程基本信息</a>
	<hr />
	<form action="">
		<div>
			<table style="margin-left: 8%;">
				<tr>
					<td><h5>课程名称:</h5></td>
					<td class="row-fluid custom basic"><input type="text"  style="width: 450px;" value="${course.courseName }"/></td>
				</tr>
				<tr>
					<td><h5>课程介绍:</h5></td>
					<td class="row-fluid custom basic"><textarea rows="3" cols="8" style="width: 459px;margin-top: 20px;">${course.courseDesc}</textarea></td>
				</tr>
				<tr>
					<td><h5>知识形式:</h5></td>
					<td class="row-fluid custom basic"><select style="width: 250px;margin-top: 20px;">
							<option value="volvo">在线视频</option>
							<option value="saab">文档</option>
							<option value="opel">数据</option>
							<option value="audi">其它</option>
					</select></td>
				</tr>
				<tr>
					<td><h5>分类:</h5></td>
					<td class="row-fluid custom basic">
					<select style="width:200px;margin-top: 20px;">
							<option value="volvo">课程资料</option>
							<option value="saab">教学资源</option>
							<option value="audi">其它</option>
					</select>
					<select style="width:200px;margin-left: 20px;margin-top: 20px;">
							<option value="volvo">文学</option>
							<option value="saab">计算机</option>
							<option value="opel">数学</option>
							<option value="audi">管理学</option>
							<option value="audi">其它</option>
					</select>
					</td>
				</tr>
				<tr><td style="text-align: left;margin-left: 80px;float: left;margin-top: 40px;" colspan="2" ><button class="btn btn-large btn-success">保存</button></td></tr>
			</table>
		</div>
	</form>
</div>