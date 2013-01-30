<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function checkCoursePwd(cid,tid){
		$("#errorMsg").html("");
		var pwd = $("#coursepwd").val();
		$.post('<c:url value="/checkCoursePwd" />', $("#checkpwd_form").serialize(), function(flag){
			//alert(typeof flag+flag);
				if('true'==flag){
					$("#course_id").val(cid);
					$("#teacher_id").val(tid);
					$("#course_pwd").val(pwd);
					$("#showCourseDetail").submit();
				}else{
					$("#errorMsg").html("密码错误！");
					return false;
				}			
		});
	}
	
	
	function requestCourseDetail(cid,tid){
		$("#course_id").val(cid);
		$("#teacher_id").val(tid);
		$("#showCourseDetail").submit();
	}
</script>
<style>
.row-fluid.custom {
	margin-bottom: 20px;
	padding: 0px 0px 10px;
	background: #FAFAFB;
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
	<div class="row">
		<h5>教师课程</h5>
	</div>
	<div class="row">
		<table class="table">
			<thead>
					<tr><th width=20%>课程名称</th><th  width=60%>课程描述</th><th width=20%>创建时间</th></tr>
			</thead>
			<tbody>
				<c:forEach var="course" items="${page.content}">
					<tr>
						<td ><!--   -->
							<c:choose>
								<c:when test='${course.pwd == "" || course.pwd == null}'>
									<a href="javascript:void(0)"  onclick="requestCourseDetail( ${course.id} , ${teacherInfo.id})"> ${course.courseName }</a>
								</c:when>
								<c:otherwise>
									<a href="#checkcourse" data-toggle="modal"> ${course.courseName }</a> 
									<div id="checkcourse" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px; height:180px; ">
										<div class="modal-header">
											<h4 id="myModalLabel">验证密码</h4>
										</div>
										<div class="modal-body">
											<form  method="post" id="checkpwd_form">
												<input type="hidden" value="${course.id}" name="cid"> 
												输入密码：<input type="text" name="coursepwd" id="coursepwd" placeholder="密码">
												<span id="errorMsg" style="font-size: 14px; color: red"></span>
											</form>
											<div style="margin-left: 120px;">
												<button class="btn btn-primary"  onclick="checkCoursePwd( ${course.id} , ${teacherInfo.id})">确定</button>&nbsp;&nbsp;
												<button class="btn"  type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							${course.courseDesc}
						</td>
						<td>
							${course.courseDate}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		<tfoot>
	    <tr><td colspan="3">
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
		
		<form action="<c:url value="/teacher/course/view"></c:url>" id="showCourseDetail" method="post">
			<input type="hidden"  name="teacherId" id="teacher_id" >
			<input type="hidden"  name="courseId" id="course_id">
			<input type="hidden"  name="coursepwd" id="course_pwd">
		</form>
	</div>
</div>