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
.row-fluid.custom .content{
	margin: 20px 40px;

}
</style>


<div class="row-fluid custom round">
	<div  class="row" >
		<!-- <h4><a href='<c:url value="/admin/teacher/announcement/list"></c:url>'>教师公告</a></h4> -->
		<h4>教师管理</h4>
	</div>
	<div class="content">
		<div style="text-align: right;">
			<a href='<c:url value="/admin/enterprise/teacher/create"></c:url>' style="margin-bottom: 10px; font-size: 14px;"class="btn">
				添加教师</a><br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead><tr>
					<!-- <th> 图片</th> -->	
						<th  align="center">教师照片</th>
						<th  align="center" width="30%">教师简介</th>
						<th  align="center" width="30%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
							<td align="center"><img src='<c:url value="${page.photourl}" ></c:url>' style="width: 120px; height: 80px;" /></td> 
							<td align="left" >${page.content}</td>
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
	</div>	
</div>

<!-- delete teacherForm -->
<div class="modal hide fade" id="deleteAnnoPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该教师吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/enterprise/teacher/destory"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="annoId" type="hidden" name="eteacherid" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>