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
.row-fluid.custom .row1 {
	margin: 20px 40px;
}
</style>
<div class="row-fluid custom round">
	<div class="row" >
		<h4>课程理管>类别管理</h4>
	</div>
	<div class="row1">	
		<div style="text-align: center;">
			<table class="blue" id="mytab" cellpadding="7" width=90%  border=0>
				<thead><th>类别名称</th><th>详细操作</th></thead>
				<tbody>
					<c:forEach items="${list}" var="l">
						<tr><td align="left">${l.typeName}</td>
						<td><a href='<c:url value="/admin/teacher/course/type/destory/${l.id}" ></c:url>' > 删除</a></td></tr>
					</c:forEach>
					<tr><td colspan="2" > 	
					<form  method="post" action="<c:url value='/admin/teacher/course/type/new/create'></c:url>"  class="form-inline pull-right">
							<input type="text" name="typeName" placeholder="输入新建的分类">
							<input type="submit" class="btn btn-success" value="新建分类">
						</form></td></tr>
				</tbody>
			</table>
		</div>
	</div>
</div>