<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#names").focus(function() {
		$(".help-inline").html("");
	});
	$("#descs").focus(function() {
		$(".help-inline").html("");
	});
	checkAjaxs("course_info_form","courserInfoAJAX");
});
</script>
<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>课程管理</b></a><hr>
<div style="text-align: center;">
	
	<table class="table table-bordered">
		<tbody>
			<tr><td colspan="4">
					<a href="#myModal" role="button" class="btn" data-toggle="modal">添加新课程</a>
				</td></tr>
			<tr><td>课程标题</td><td>课程简述</td><td>发布时间</td><td>课程操作</td></tr>
			<c:forEach items="${page.content}" var="page">
				<tr><td align="left" width="15%"><a href='<c:url value="/admin/teacher/course/view/${page.id}"></c:url>'>${page.courseName}</a></td>
				<td align="center">${page.courseDesc}</td>
				<td align="center" width="20%">${page.courseDate}</td>
				<td align="center" width="15%">
				 	<!-- <div class="btn-group"> 
						<button class="btn">更多</button>  
						<button class="btn dropdown-toggle" data-toggle="dropdown">   
						<span class="caret"></span> </button>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/admin/teacher/course/edit/${page.id}"></c:url>'>修改</a></li>
							<li><a href='<c:url value="/admin/teacher/course/destory/${page.id}"></c:url>'>删除</a></li>
						</ul>
					</div> -->
					<a href='<c:url value="/admin/teacher/course/destory/${page.id}"></c:url>'>删除</a>
				</td></tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr><td colspan="4" align="right">
	        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
	</table>
	
	<br/>
	
		
	<div id="myModal" style="text-align: left;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">新课程添加</h3>
		</div>
		<form:form  action='new' method="post" id="course_info_form">
			<div class="modal-body" id="courseName">
				课程标题：<input type="text" id="names" name="courseName"  placeholder="课程标题">
				<span class="help-inline"><form:errors path="courseName"></form:errors></span>
			</div>
			<div  class="modal-body" id="courseDesc">
				课程描述：<textarea name="courseDesc" id="descs" placeholder="课程描述" cols="5" rows="8" style="width:380px;"></textarea>
				<span class="help-inline"><form:errors path="courseDesc"></form:errors></span>
				<!-- 
					<button type="submit" class="btn btn-primary">OK</button>&nbsp;&nbsp;
					<button type="reset" class="btn" onclick="hidAnnoForm()">Cancel</button> -->
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" type="submit">保存</button>
				<button class="btn" type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
				
			</div>
		</form:form>
	</div>
</div>