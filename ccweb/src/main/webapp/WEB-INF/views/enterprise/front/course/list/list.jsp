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
		},"text");
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

.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #cccccc 1.5px;
	padding-bottom: 4px;
	margin: 10px 0px 0px 0px;
}

.row-fluid.custom .row {
	margin: 10px 40px 0px 40px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed #cccccc 1px;
}
.row-fluid.custom .row .bro{
	margin-top:10px;
	margin-right:17px;
	margin-left:7px;
	margin-bottom:10px;
	border:1px solid #ccc;
	width: 210px;
	padding:5px;
	float: left;
}
</style>

<div class="row-fluid custom round">
	<div class="row">
		<h4>学习资源</h4>
	</div>
	<div class="row">
		<table  style="width: 100%" >
			<tbody>
				<tr ><td>
					<c:forEach var="course" items="${page.content}">
						<div class="bro">
						<div style="width: 210px; height: 155px; background-image: url('<c:url value="${course.courseCover }"></c:url>');  
								background-repeat:no-repeat;background-position:center;  ">
							<c:choose>
								<c:when test='${course.pwd == "" || course.pwd == null}'>
									<a href="javascript:void(0)"  onclick="requestCourseDetail( ${course.id} , ${teacherInfo.id})"> 	<div style="height: 125px;"></div></a>
								</c:when>
								<c:otherwise>
									<a href="#checkcourse" data-toggle="modal"> 	<div style="height: 125px;"></div></a> 
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
												<button class="btn btn-success"  onclick="checkCoursePwd( ${course.id} , ${teacherInfo.id})">确定</button>&nbsp;&nbsp;
												<button class="btn"  type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
		    				<div  style="height:24px;background-color:#000;  padding:3px; color: #fff;  Opacity:0.70; Filter:alpha(opacity=70);">
		    					<div  id="contentlimit" style="width: 240px;">
		    							${course.courseName }-${course.cType.typeName}
		    					</div>
		   				 	</div>
						</div>
			    		</div>
					</c:forEach>
					</td></tr>
			</tbody>
		<tfoot>
	    <tr><td >
	        <jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include>
	   		 </td></tr>
		</tfoot>
		</table>
		
		<form action="<c:url value="/course/view"></c:url>" id="showCourseDetail" method="post">
			<input type="hidden"  name="teacherId" id="teacher_id" >
			<input type="hidden"  name="courseId" id="course_id">
			<input type="hidden"  name="coursepwd" id="course_pwd">
		</form>
	</div>
</div>