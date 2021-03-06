<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
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
	<div class="row" >
		<h4>课程管理</h4>
	</div>
	<div class="content">	
		<div style="text-align: left;">
	<!-- 
		<div id="player"></div>
			    <script type="text/javascript">
				    	//TODO: fix me, the file name extension should be mp4
					    jwplayer("player").setup({
					        file: '<c:url value="/resources/iphone.mp4" />',
					        //image: "/uploads/myPoster.jpg"
					        //TODO: each mp4 can have a preview image
					    });
				</script> -->
			<!-- 	<a style="margin-bottom: 10px; font-size: 14px;" href="#myModal" role="button"
							class="btn" data-toggle="modal">添加新课程&nbsp;&nbsp;</a> -->
			<div >
			 	<a href='<c:url value="/admin/course/list"></c:url>' style="margin-right: 10px;"> 全部课程</a> 
				 <a href='<c:url value="/admin/course/list/unpub"></c:url>' style="margin-right: 10px;"> 未发布课程</a> 
				<a href='<c:url value="/admin/course/list/publish"></c:url>'  style="margin-right: 10px;"> 已发布课程</a>
				<a href='<c:url value="/admin/course/list/recycle"></c:url>'> 已删除课程</a>
				<c:if test='${sessionScope.sessionUserInfo.user.email  == "tim@apple.com"}'>
					<a href='<c:url value="/admin/course/type/list"></c:url>'> 课程类别</a>
				</c:if>	
				<a  style=" font-size: 14px; float: right;" href='<c:url value="/admin/course/addcourse"><c:param name="active" value="first" /></c:url>' class="btn">添加新课程</a>
			</div>
			<br>
			<table class="blue" id="mytab" cellpadding="7" width=100%  border=0>
				<thead>
					<tr>
						<th align="center" width="15%">课程标题</th>
						<th align="center" width="15%">课程类别</th>
						<th align="center">课程状态</th>
								<th align="center" width="15%">价格</th>
						<th align="center" width="20%">发布时间</th>
						<th align="center" width="18%">操作				
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="page">
						<tr>
						<td align="center">
							<div style="width: 110px;" id="content">
								<c:if test="${page.publish==1 }">
									${page.courseName}
								</c:if>
								<c:if test="${page.publish >1 }">
									<a href='<c:url value="/admin/course/view/${page.id}"></c:url>'>${page.courseName}</a>
								</c:if>
							</div>
						</td>
						<td align="center">${page.cType.typeName}</td>
						<td align="center">
							<c:if test="${page.publish ==2 }">未发布</c:if>
							<c:if test="${page.publish ==3 }">已发布</c:if>
							<c:if test="${page.publish ==1 }">已删除</c:if>
						</td>
						<td align="center">
							${page.price}  元
						</td>
						<td align="center">${page.courseDate}</td>
						<td align="center">
						 	<!-- <div class="btn-group"> 
								<button class="btn">更多</button>  
								<button class="btn dropdown-toggle" data-toggle="dropdown">   
								<span class="caret"></span> </button>
								<ul class="dropdown-menu">
									<li><a href='<c:url value="/admin/course/edit/${page.id}"></c:url>'>修改</a></li>
									<li><a href='<c:url value="/admin/course/destory/${page.id}"></c:url>'>删除</a></li>
								</ul>
							</div> -->
							<c:if test="${page.publish ==1 }">
							<!--  	<a href='<c:url value="/admin/course/recover/${page.id}"></c:url>'>恢复</a>-->
								 <a class="recoverCoursePostBtn" href="#recoverCoursePostModal" role="button" data-toggle="modal" data-target="#recoverCoursePostModal">恢复</a><input type="hidden"  value="${page.id}" id="courseId"> 
								 | <!-- <a href='<c:url value="/admin/course/deleted/${page.id}"></c:url>'>彻底删除</a> -->
								 <a class="deleteCoursePostBtn" href="#deleteCoursePostModal" role="button" data-toggle="modal" data-target="#deleteCoursePostModal">彻底删除</a><input type="hidden"  value="${page.id}" id="courseId">
							</c:if>
							<c:if test="${page.publish ==2 ||page.publish ==3 }">
								<!-- <a href='<c:url value="/admin/course/destory/${page.id}"></c:url>'>删除</a>  -->	
									 <a class="destoryCoursePostBtn" href="#destoryCoursePostModal" role="button" data-toggle="modal" data-target="#destoryCoursePostModal">删除</a><input type="hidden"  value="${page.id}"> 
									| <a href='<c:url value="/admin/course/edit/${page.id}/modifycourse"></c:url>'>修改</a>							
							</c:if>
						</td></tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="content"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div> 
		</div>
	</div>
</div>
<!-- destory course (remove to recycle) -->
<div class="modal hide fade" id="destoryCoursePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定删除该课程吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/course/destory"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="c_delete_id" type="hidden" name="cId" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>

<!-- delete course from recycle) -->
<div class="modal hide fade" id="deleteCoursePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定彻底删除该课程吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/course/deleted"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="c_recycle_Id" type="hidden" name="cId" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>

<!-- recover course from recycle) -->
<div class="modal hide fade" id="recoverCoursePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">请注意</h3>
	  </div>
	  <div class="modal-body">
	    <p>你确定恢复该课程吗？</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	    <form action='<c:url value="/admin/course/recover"></c:url>' method="post" style="display: inline-block;" >
	    	<input id="c_recover_Id" type="hidden" name="cId" />
	    	<button class="btn btn-success">确定</button>
	    </form>
	  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	checkEmptyAjax("course_info_form","courseInfoAJAX");
	$('.destoryCoursePostBtn').on('click', function() {
		var course_id = $(this).next().val();
		$('#c_delete_id').val(course_id);	
	});
	
	$('.deleteCoursePostBtn').on('click', function() {
		var course_id =  $(this).next().val();
		$(' #c_recycle_Id').val(course_id);	
	});
	$('.recoverCoursePostBtn').on('click', function() {
		var course_id =  $(this).next().val();
		$('#c_recover_Id').val(course_id);	
	});
});

</script>
