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
		var anno_id = $(this).next().val();
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
	<div  class="row" >
		<!-- <h4><a href='<c:url value="/admin/teacher/announcement/list"></c:url>'>教师公告</a></h4> -->
		<h4>公告管理</h4>
	</div>
	<div class="row1">
		<div style="text-align: right;">
			<a href='<c:url value="/admin/teacher/announcement/create"></c:url>' style="margin-bottom: 10px; font-size: 14px;"class="btn">
				添加公告</a><br>
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
								 <a class="deleteAnnoPostBtn" href="#deleteAnnoPostModal" role="button" data-toggle="modal" data-target="#deleteAnnoPostModal">删除</a><input type="hidden" value="${page.id} ">  | 
								 <a href='<c:url value="/admin/teacher/announcement/edit/${page.id}"></c:url>'>修改</a>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row1"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
			<br />
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
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>