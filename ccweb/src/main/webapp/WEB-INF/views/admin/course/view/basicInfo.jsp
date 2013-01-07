<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/courseType.js" />"></script>
<style>
.row-fluid.custom.basic {
	margin-left: 10px;
	float: left;
	margin-top: 20px;
}
</style>
<div style="margin-top: 10px;">
	<a href="#">基本信息</a>
	<hr />
	<form action="">
		<div>
			<table style="margin-left: 8%;">
				<tr>
					<td>课程名称：</td>
					<td class="row-fluid custom basic"><input type="text" style="width: 450px;" value="${course.courseName }" /></td>
				</tr>
				<tr >
					<td >课程类别：</td>
					<td class="row-fluid custom basic">
					<select name="courseType" style="width: 465px;" id="courseType" title="${course.courseType}">
							<option selected value="计算机科学与技术">计算机科学与技术</option>
							<option value="生物">生物</option>
							<option value="数学">数学</option>
							<option value="化学">化学</option>
							<option value="语文">语文</option>
							<option value="金融">金融</option>
							<option value="英语">英语</option>
							<option value="哲学">哲学</option>
							<option value="其他">其他</option>
					</select>
					</td>
				</tr>
				<tr>
					<td style="padding-top: -40px;">课程介绍：</td>
					<td class="row-fluid custom basic"><textarea rows="5" cols="8" style="width: 459px;height: 100px;">${course.courseDesc}</textarea></td>
				</tr>
				<!-- <tr>
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
				</tr> -->
				<tr>
					<td style="text-align: left; margin-left: 80px; float: left; margin-top: 40px;" colspan="2"><button class="btn btn-large btn-success">保存</button></td>
				</tr>
			</table>
		</div>
	</form>
</div>