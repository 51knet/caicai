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
.round {
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
.row-fluid .custom .row {
	margin: 10px 40px;
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>个人专利</h4>
	</div>
	<div class="content">
				<a  style=" font-size: 14px; float: right;" href='<c:url value="/admin/patent/new"></c:url>' class="btn">添加新专利</a><br><br>
		<table class="blue" id="mytab" cellpadding="4" width=100%  border=0>
			<thead>
				<tr><th >申请号</th><th>名称</th><th>主分类</th><th>分类</th><th>申请人</th><th>发明人</th><th>公开日</th>
					<th>公开号</th><th>代理机构</th><th>代理人</th><th>申请日</th><th>地址</th><th>摘要</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${page.content}" var="patent">
					<tr>
						<td ><a href="/admin/patent/view/${patent.patentNum }">${patent.patentNum }</a></td><td >${patent.patentName }</td><td >${patent.mainClassNum }</td><td >${patent.classNum}</td><td >${patent.applicant }</td>
						<td >${patent.inventer }</td><td >${patent.publishDate }</td><td >${patent.publishNum }</td><td >${patent.agency }</td><td >${patent.agent }</td>
						<td >${patent.applicationDate }</td><td >${patent.address }</td><td >${patent.summary }</td>
					</tr>
				</c:forEach>
					<tr>

						<td >123123</td>
						<td >贷款后的</td>
						<td >1233</td>
						<td >1232</td>
						<td >王二</td>
						<td >王三</td>
						<td >2013-01-01</td>
						<td >1235</td>
						<td >华夏</td>
						<td >王四</td>
						<td >2013-01-04</td>
						<td >上海</td>
						<td >无</td>

					</tr>
			</tbody>
	<!--	<tfoot>
	     <tr><td colspan="13">
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot> -->
		</table>
	</div>
</div>