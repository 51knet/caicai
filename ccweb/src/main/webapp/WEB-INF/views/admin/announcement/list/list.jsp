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
	
	$('.deleteAnnoPostBtn').on('click', function() {
		var anno_id = $("#anno_id").val();
		$('#deleteAnnoPostModal #annoId').val(anno_id);	
	});
});

</script>
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
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>


<div class="row-fluid custom round">
	<div  class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/announcement/list"></c:url>'><b>教师公告</b></a>
		<hr>
		<div style="text-align: right;">
			<a style="margin-bottom: 10px; font-size: 14px;" href="#myModal" role="button"
							class="btn" data-toggle="modal">添加公告&nbsp;&nbsp;</a>
			<a href='<c:url value="/admin/teacher/announcement/create"></c:url>' style="margin-bottom: 10px; font-size: 14px;"class="btn">
				添加可编辑公告&nbsp;&nbsp;</a><br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead><tr>
						<th  align="center">公告标签</th>
						
						<th  align="center" width="25%">发布时间</th>
						<th  align="center" width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="left" >${page.title}</td>
							<td align="center">${page.date}</td>
							<td align="center">
							<!-- 
								<div class="btn-group">
									<button class="btn">更多</button>
									<button class="btn dropdown-toggle" data-toggle="dropdown">
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href='<c:url value="/admin/teacher/announcement/edit/${page.id}"></c:url>'>修改</a></li>
										<li></li>
									</ul>
								</div>
							 -->
								 <a class="deleteAnnoPostBtn" href="#deleteAnnoPostModal" role="button" data-toggle="modal" data-target="#deleteAnnoPostModal">删除</a> | 
								 <a href='<c:url value="/admin/teacher/announcement/edit/${page.id}"></c:url>'>修改</a>
								 <input type="hidden" value="${page.id} " id="anno_id" name="annoId" > 
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<!-- <tfoot>
					<tr>
						<td colspan="4" align="right"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></td>
					</tr>
				</tfoot> -->
			</table>
			<div class="row"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
			
			<div id="myModal" class="modal hide fade" tabindex="-1"  style="text-align: left;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3 id="myModalLabel">公告添加</h3>
				</div>
				<form:form action='new' method="post" id="anno_information">
					<div class="control-group" id="title">
						<div class="controls">
						<span style="margin-left:16px;">公告标签:</span>&nbsp;<input style="margin-left: 40px;margin-top: 16px;width: 349px;" type="text" name="title" id="t" placeholder="公告标签"  >
						<span class="help-inline"><form:errors path="title"></form:errors></span>
						</div>
					</div>
					<div class="control-group" id="content">
						<div class="controls">
						<span style="margin-left: 16px;">公告内容:</span>
						<textarea name="content" placeholder="公告内容" id="c" cols="5" rows="8" style="width:350px;margin-left: 40px;margin-top:4px; "></textarea>
						<span class="help-inline"><form:errors path="content"></form:errors></span>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" type="submit">保存</button>
							<button class="btn" type="reset" data-dismiss="modal">取消</button>
					</div>
				</form:form>
			</div>
		</div>
		<script type="text/javascript">
			function ApplyStyle(s){
				document.getElementById("mytab").className=s.innerText;
			}
		</script>
		<!-- <table><tr><td>
			点击链接切换样式：<a href="javascript:;" onclick="ApplyStyle(this)">t1</a>&nbsp;
			<a href="javascript:;" onclick="ApplyStyle(this)">t2</a>&nbsp;
			<a href="javascript:;" onclick="ApplyStyle(this)">t3</a>
		</td></tr></table> -->
	</div>	
</div>

<!-- delete annoForm -->
<div class="modal hide fade" id="deleteAnnoPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该公告吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/teacher/announcement/destory"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="annoId" type="hidden" name="annoId" />
	    	<button class="btn btn-primary">确定</button>
	    </form>
	  </div>
</div>