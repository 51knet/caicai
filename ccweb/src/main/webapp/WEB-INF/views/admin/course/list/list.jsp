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
	<div class="row" style="margin-top:10px;">
		<a href='<c:url value="/admin/teacher/course/list"></c:url>' ><b>课程管理</b></a><hr>
		<div style="text-align: right;">
			<!-- 	<a style="margin-bottom: 10px; font-size: 14px;" href="#myModal" role="button"
							class="btn" data-toggle="modal">添加新课程&nbsp;&nbsp;</a> -->
			<span style="margin-right: 270px;"> <a href="#" style="margin-right: 20px;"> 查看未发布课程</a> <a href="#"> 查看已发布课程</a></span>
			<a  style="margin-bottom: 10px; font-size: 14px;" href='<c:url value="/admin/teacher/course/new"></c:url>' class="btn">添加新课程</a>
			<a  style="margin-bottom: 10px; font-size: 14px;" href='<c:url value="/admin/teacher/course/addcourse"><c:param name="active" value="first" /><c:param name="cid" value="0" /></c:url>' class="btn">添加新课程(Test)</a>				
			<br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th align="center">课程封面</th>
						<th align="center" width="15%">课程标题</th>
						<th align="center" width="20%">课程类别</th>
						<th align="center">是否发布</th>
						<th align="center" width="20%">发布时间</th>
						<th align="center" width="13%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
						<td align="left"><img src="/ccweb/${page.courseCover }" style="width: 80px; height: 40px;"/></td>
						<td align="left"><a href='<c:url value="/admin/teacher/course/view/${page.id}"></c:url>'>${page.courseName}</a></td>
						<td align="center">${page.courseType}</td>
						<td align="center"><c:if test="${page.publish ==1 }">未发布</c:if><c:if test="${page.publish ==2 }">已发布</c:if></td>
						<td align="center">${page.courseDate}</td>
						<td align="center">
						 	<!-- <div class="btn-group"> 
								<button class="btn">更多</button>  
								<button class="btn dropdown-toggle" data-toggle="dropdown">   
								<span class="caret"></span> </button>
								<ul class="dropdown-menu">
									<li><a href='<c:url value="/admin/teacher/course/edit/${page.id}"></c:url>'>修改</a></li>
									<li><a href='<c:url value="/admin/teacher/course/destory/${page.id}"></c:url>'>删除</a></li>
								</ul>
							</div> -->
							<a href='<c:url value="/admin/teacher/course/destory/${page.id}"></c:url>'>删除</a> | <a href="#">修改</a>
						</td></tr>
					</c:forEach>
				</tbody>
				<!-- <tfoot>
					<tr><td colspan="4" align="right">
			        	<jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
			   		 </td></tr>
				</tfoot> -->
			</table>
			<div class="row"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
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
	</div>
</div>