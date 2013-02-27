<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
	
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
	<div class="row" style="margin-top: 10px;">
		<a href='<c:url value="/admin/teacher/course/list"></c:url>'><b>课程管理</b></a>>> <a href='<c:url value="/admin/teacher/course/new"></c:url>'><b>课程添加</b></a>
		<hr>
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li <c:if test='${active == "first"}'> class="active" </c:if>><a href="#" data-toggle="tab">第一步：基本信息</a></li>
				<li <c:if test='${active == "second"}'> class="active" </c:if>><a href="#" data-toggle="tab">第二部：权限价格</a></li>
				<li <c:if test='${active == "third"}'> class="active" </c:if>><a href="#" data-toggle="tab">第三部：计划</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "first"}'>active</c:if>" id="first_tab">
					<div class="row-fluid custom round">
						<div class="row" style="margin-top: 10px;">
							<div style="width: 560px; text-align: left;">
								<form:form action="new/firststep" method="post" enctype="multipart/form-data" id="course_info_form" onsubmit="return checkPicture(this)">
									<div class="modal-body"> 
										上传封面：<input type="file" name="coverFile" style="margin-bottom: 10px; width: 350px;" /> <span style="font-size: 13px; color: red;">${errorMsg }</span>
										<br><span style="color: red; font-size: 14px; margin-left: 70px;">只支持jpg、gif、bmp格式，建议封面宽度260px，高度150px</span>
									</div>
									<div class="control-group" id="courseName" >
										<div class="controls" style="margin-left: 13px;">
											课程名称：<input type="text" id="coursenames" name="courseName" placeholder="课程名称"> 
											<span class="help-inline"><form:errors path="courseName" /></span>
										</div>
									</div>
									<div class="modal-body" id="course">
										课程类别：<select name="courseType" style="width: 380px;">
											<option selected value="计算机科学与技术">计算机科学与技术</option>
											<option value="生物">生物</option>
											<option value="数学">数学</option>
											<option value="化学">化学</option>
											<option value="语文">语文</option>
											<option value="金融">金融</option>
											<option value="英语">英语</option>
											<option value="哲学">哲学</option>
											<option value="其他">其他</option>
										</select>
									</div>
									<div class="control-group" id="courseDesc">
										<div class="controls" style="margin-left: 13px;">
										课程描述：
											<textarea name="courseDesc" rows="8" cols="5" style="width: 380px;"></textarea>
											<span class="help-inline"></span>
										</div>
									</div>




									<div class="modal-body" style="text-align: center;">
										<button type="submit" onclick="firststep();" class="btn btn-large btn-success">下一步</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>

				<div class="tab-pane <c:if test='${active == "second"}'>active</c:if>" id="second_tab">
					<form:form class="form-horizontal" action="new/secondstep" method="post" onsubmit="return checkPwd()">
						<input type="hidden" name="courseId" value="${courseId }" />
						<div class="modal-body" id="pwdform">
							设置密码：<input type="text" id="pwd" name="pwd" style="width: 250px;" onblur="showCheckpwd();" onfocus="clearHtml();">
						<span id="pwdError"></span>
						</div>
						<div class="modal-body" id="checkpwdform" style="display: none;">
							确认密码：<input type="text" id="checkpwd" name="checkpwd" style="width: 250px;" onblur="checkPwd()">
						</div>
						<div class="modal-body" id="status">
							发布到知识超市：<input type="radio" name="status" value="2" checked="checked">是&nbsp;&nbsp;<input type="radio" name="status" value="1">否
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-large btn-success">下一步</button>
							</div>
						</div>
					</form:form>

				</div>

				<!-- third -->
				<div class="tab-pane  <c:if test='${active == "third"}'>active</c:if>" id="third_tab">
					<form:form class="form-horizontal" action="new/thirdstep" method="post" enctype="multipart/form-data">
						<input type="hidden" value="${courseId }" name="courseId" />
						<div class="modal-body">
								课程创建已完成，请点击下一步进入资源添加页面
						</div>
						<div style="margin-top: 20px;"></div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-large btn-success">下一步</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function firststep() {
		checkAjaxs("course_info_form", "courseInfoAJAX");
		return false;
	}
	$(document).ready(function(){
		$("#coursenames").blur(function(){
			var coursename =$("#coursenames").val();
				$.ajax({
					   type: "post",
					   url: "<c:url value='/admin/teacher/course/checkcoursename' />",
					   data: "courseName="+coursename,
					   dataType:"text",
					   success: function(num){
					     if(num=="1"){
					    	alert('课程名已存在,请重新输入');
					    	$("#coursenames").val("");
					    	return false;
					     }
					   }
					});
			});
	});
	
</script>