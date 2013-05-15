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
	/*background: #FAFAFB;*/

}
.row-fluid.custom .row > h4 {
	color: #80b029;
	border-bottom: solid #f77605 1.5px;
	padding-bottom: 4px;
	margin: 0px 0px 0px 0px;
	padding:0px 10px 5px 10px;
}
.row-fluid.custom .row > h4 >span {
	font-size: 14px;
	color:#666;
}

.row-fluid.custom .row {
	margin: 0px 10px 0px 10px;
}

.row-fluid.custom .row .bb{
	border-bottom: dashed  1px;
}
.row-fluid.custom .row .course{
	margin-top:10px;
	margin-left:10px;
	margin-bottom:10px;
	border:1px dotted #dadada;
	width: 230px;
	padding:5px;
	float: left;
}
</style>

<div class="row-fluid custom round">
		<div class="row">
			<h4>学习资源 </h4>
		</div>
		<div class="row"  style="border: solid 1px #f77605;">
			<c:choose>
				<c:when test="${courseCount !=0}">
					<c:forEach items="${page.content}" var="page" >
				    	<div class="course">
							<div style="width: 230px; height: 155px; background-image: url('<c:url value="${page.courseCover }"></c:url>');  
									background-repeat:no-repeat;background-position:center;  ">
								<c:choose>
									<c:when test='${page.pwd == "" || page.pwd == null}'>
										<a href="javascript:void(0)"  onclick="requestCourseDetail( ${page.id} , ${teacherInfo.id})"> 	<div style="height: 125px;"></div></a>
									</c:when>
									<c:otherwise>
										<a href="#checkcourse" data-toggle="modal"> <div style="height: 125px;"></div></a> 
										<div id="checkcourse" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px; height:180px; ">
											<div class="modal-header">
												<h4 id="myModalLabel">验证密码</h4>
											</div>
											<div class="modal-body">
												<form  method="post" id="checkpwd_form">
													<input type="hidden" value="${page.id}" name="cid"> 
													输入密码：<input type="text" name="coursepwd" id="coursepwd" placeholder="密码">
													<span id="errorMsg" style="font-size: 14px; color: red"></span>
												</form>
												<div style="margin-left: 120px;">
													<button class="btn btn-success"  onclick="checkCoursePwd( ${page.id} , ${teacherInfo.id})">确定</button>&nbsp;&nbsp;
													<button class="btn"  type="reset" data-dismiss="modal" aria-hidden="true">取消</button>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
			    				<div  style="height:24px;background-color:#000;  padding:3px; color: #fff;  Opacity:0.70; Filter:alpha(opacity=70);">
			    					<div  id="contentlimit" style="width: 240px;">
			    							${page.courseName } — ${page.cType.typeName }
			    					</div>
			   				 	</div>
							</div>
				    	</div>
				    </c:forEach>
				</c:when>
				<c:otherwise>
				<br>
					无内容<br>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="margin: 10px 0px 0px 10px;"><jsp:include page="/WEB-INF/views/_shared/pagination.jsp"></jsp:include></div>
		<form action="<c:url value="/course/view"></c:url>" id="showCourseDetail" method="post">
			<input type="hidden"  name="teacherId" id="teacher_id" >
			<input type="hidden"  name="courseId" id="course_id">
			<input type="hidden"  name="coursepwd" id="course_pwd">
		</form>
</div>


