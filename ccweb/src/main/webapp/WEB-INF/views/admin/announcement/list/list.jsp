<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#t").focus(function() {
		$(".help-inline").html("");
	});
	$("#c").focus(function() {
		$(".help-inline").html("");
	});
	checkAjax("anno_information","annoInfoAJAX");
});
</script>

<a href='<c:url value="/admin/teacher/announcement/list"></c:url>'><b>教师公告</b></a>
<hr>
<div style="text-align: center;">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="4">全部公告</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="4" align="right"><a href="#myModal" role="button"
					class="btn" data-toggle="modal">添加公告</a></td>
			</tr>
			<tr>
				<td>公告标题</td>
				<td>公告内容</td>
				<td>发布时间</td>
				<td>详细操作</td>
			</tr>
			<c:forEach items="${page.content}" var="page">
				<tr>
					<td align="left">${page.title}</td>
					<td align="center">${page.content}</td>
					<td align="center">${page.date}</td>
					<td>
						<div class="btn-group">
							<button class="btn">更多</button>
							<button class="btn dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a
									href='<c:url value="/admin/teacher/announcement/edit/${page.id}"></c:url>'>修改</a></li>
								<li><a
									href='<c:url value="/admin/teacher/announcement/destory/${page.id}"></c:url>'>
										删除</a></li>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" align="right"><jsp:include
						page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></td>
			</tr>
		</tfoot>
	</table>
	<br />


	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">公告添加</h3>
		</div>
		<form:form action='new' method="post" id="anno_information">
			<div class="modal-body" id="title">

				公告标题：<input type="text" name="title" id="t" placeholder="Title">
				<span class="help-inline"><form:errors path="title"></form:errors></span>
			</div>
			<div class="modal-body" id="content">
				公告内容：
				<textarea name="content" placeholder="Content" id="c" cols="5"
					rows="8"></textarea>
				<span class="help-inline"><form:errors path="content"></form:errors></span>
			</div>
			<div class="modal-footer">
				<button class="btn" type="reset" data-dismiss="modal"
					aria-hidden="true">取消</button>
				<button class="btn btn-primary" type="submit">保存</button>
			</div>
		</form:form>
	</div>

</div>
