<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/img.js" />"></script>
<style type="text/css">
#preview{}
#showimg {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
<script type="text/javascript">
function previewImages(file){
	previewImage(file);
}
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
	<div class="row" >
	<h4>课程管理>课程添加</h4> 
	</div>	
	<div class="row1">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li <c:if test='${active == "first"}'> class="active" </c:if>><a href="#" data-toggle="tab">第一步：基本信息</a></li>
				<li <c:if test='${active == "second"}'> class="active" </c:if>><a href="#" data-toggle="tab">第二部：权限设置</a></li>
				<%-- <li <c:if test='${active == "third"}'> class="active" </c:if>><a href="#" data-toggle="tab">第三部：计划</a></li> --%>
			</ul>
			
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "first"}'>active</c:if>" id="first_tab">
						<div style="width: 560px; text-align: left;">
							<span style="margin-left: 14px;">封面预览：</span> 
							<div id="preview" style="margin-left:90px;margin-top: -12px;">
								<img name="showimg" id="showimg" src="" style="display: none;" />
							</div>
							<div id="courseCover" style="margin-left: 90px; margin-top: -15px;">
								<span> <img src='<c:url value="/resources/img/teacher_front_bg.jpg"></c:url>' style="width: 210px; height: 110px;" />
								</span>
							</div>
							<form:form action="new/firststep" method="post" enctype="multipart/form-data" id="course_info_form" onsubmit="return checkPicture(this)">
								<div class="modal-body"> 
									上传封面：<input type="file" name="coverFile" style="margin-bottom: 10px; width: 350px;" onChange="previewImages(this);"/> <span style="font-size: 13px; color: red;">${errorMsg }</span>
									<br><span style="color: red; font-size: 14px; margin-left: 70px;">只支持jpg、gif、bmp、png格式，建议封面宽度260px，高度195px</span>
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
										<option value="历史">历史</option>
										<option value="金融">金融</option>
										<option value="英语">英语</option>
										<option value="哲学">哲学</option>
										<option value="其他">其他</option>
									</select>
									<span class="help-inline"></span>
								</div>
								<div class="control-group" id="courseDesc">
									<div class="controls" style="margin-left: 13px;">
									课程描述：
									<div style="margin-left: 70px; margin-top: -20px;">
										<textarea id="KEcourseDesc" name="courseDesc" rows="8" cols="5" style="width: 500px; height: 300px;"></textarea>
										<span class="help-inline"></span>
									</div>
									</div>
								</div>
								<div class="modal-body" style="text-align: center;">
									<button type="submit" class="btn  btn-success">下一步</button>
								</div>
							</form:form>
						</div>
				</div>
				<div class="tab-pane <c:if test='${active == "second"}'>active</c:if>" id="second_tab">
					<form:form class="form-horizontal" action="new/secondstep" method="post" onsubmit="return checkPwd()" enctype="multipart/form-data">
						<input type="hidden" name="courseId" value="${courseId }" />
						<div id="password"><input type="radio" value="0" checked="checked" name="pass" style="margin-bottom: 20px;">公开<br/>
						<input type="radio" value="1" name="pass">设置密码</div>
						<div id="passwordInput">
						<div class="modal-body" id="pwdform">
							设置密码：<input type="text" id="pwd" name="pwd" style="width: 250px;" onblur="showCheckpwd();" onfocus="clearHtml();">
						<span id="pwdError"></span>
						</div>
						<div class="modal-body" id="checkpwdform" style="display: none;">
							确认密码：<input type="text" id="checkpwd" name="checkpwd" style="width: 250px;" onblur="checkPwd()">
						</div>
						</div>
						<div class="modal-body" id="status">
							发布到知识超市：<input type="radio" name="status" value="2" checked="checked">是&nbsp;&nbsp;<input type="radio" name="status" value="1">否
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn  btn-success">完成</button>
							</div>
						</div>
					</form:form>
				</div>
				<!-- third -->
			<%-- 	<div class="tab-pane  <c:if test='${active == "third"}'>active</c:if>" id="third_tab">
					<form:form class="form-horizontal" action="new/thirdstep" method="post" enctype="multipart/form-data">
						<input type="hidden" value="${courseId }" name="courseId" />
						<div class="modal-body">
								课程创建已完成，请点击下一步进入资源添加页面
						</div>
						<div style="margin-top: 20px;"></div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn  btn-success">下一步</button>
							</div>
						</div>
					</form:form>
				</div> --%>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#passwordInput").hide();
		$("#password").click(function(){
			var statusValue=$('input:radio[name="pass"]:checked').val();
			if(statusValue=='0'){
				$("#pwd").val("");
				$("#checkpwd").val("");
				$("#passwordInput").hide();
			}else{
				$("#passwordInput").show();
			}
		});
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
		
		var courseDescEditor = KindEditor.create('textarea[name="courseDesc"]',{
			cssPath : '<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>',
			uploadJson : '${uploadJson}',
			fileManagerJson : '${fileManagerJson}',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				KindEditor.ctrl(document, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
				KindEditor.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['detail_form'].submit();
				});
			}
		});
		$("#course_info_form").submit(function(){
			courseDescEditor.sync();
			return checkEmptyAjax("course_info_form","courseInfoAJAX");
		});
		
	});
	
</script>