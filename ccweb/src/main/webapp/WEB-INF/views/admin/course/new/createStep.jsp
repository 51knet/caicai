<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript">
function checkPicture(obj){
	var flag = false;
	
	var fileValue = obj.coverFile.value;
	var temp = fileValue.substr(fileValue.indexOf('.'),fileValue.length);
	if(".gif"==temp || ".jpg"==temp || ".bmp"==temp){
		
		flag=true;
	}else{
		alert("只支持gif、jpg、bmp格式的图片！！");
		flag=false;
	}
	
	return flag;
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
.row-fluid.custom .row {
	margin: 0px 5px;
}
</style>
<h3>添加课程</h3><i class="icon-star"></i><i>必须填写项</i>
<hr />
<div class="tabbable">
	<ul class="nav nav-tabs">
		<li <c:if test='${active == "first"}'> class="active" </c:if>><a href="#" data-toggle="tab">第一步：基本信息</a></li>
		<li <c:if test='${active == "second"}'> class="active" </c:if>><a href="#" data-toggle="tab">第二部：权限价格</a></li>
		<li <c:if test='${active == "third"}'> class="active" </c:if>><a href="#" data-toggle="tab">第三部：计划</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane <c:if test='${active == "first"}'>active</c:if>" id="first_tab">
			<div  class="row-fluid custom round">
	<div class="row" style="margin-top: 10px;">
		
		
			<div style="text-align:center;">
				<div style="width:560px; text-align:left;" >
					<form:form action="firststep" method="post" enctype="multipart/form-data" id="course_info_form" onsubmit="return checkPicture(this)">  
						<div class="modal-body">
							上传封面：<input type="file" name="coverFile"  style="margin-bottom: 10px;"/><br><span style="color:red;font-size:14px;">只支持jpg、gif、bmp格式,建议封面宽度200px,高度100px</span>
						</div>
					
						<div class="modal-body" id="courseName">
							课程标题：<input type="text" id="names" name="courseName"  placeholder="课程标题">
							<span class="help-inline"><form:errors path="courseName"></form:errors></span>
						</div>
						
						<div class="modal-body" id="course">
							课程类别：<select name="courseType">
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
						
						<div  class="modal-body" id="courseDesc">
							课程描述：<textarea name="courseDesc" id="descs" placeholder="课程描述" cols="5" rows="8" style="width:380px;"></textarea>
							<span class="help-inline"><form:errors path="courseDesc"></form:errors></span>
						</div>
						<div class="modal-body" style="text-align: center;">
							<button type="submit"    class="btn btn-large btn-success">下一步</button>&nbsp;&nbsp;
							<button type="reset" class="btn">取消</button>
						</div>
					</form:form>
				</div>
			</div>
		
	</div>
</div>
					
			first
		</div>
		
		<div class="tab-pane <c:if test='${active == "second"}'>active</c:if>" id="second_tab">
			<form class="form-horizontal"  action="secondstep" method="post">
				<!--<div class="control-group" id="address">
					<label class="control-label" for="address">地址</label>
					<div class="controls">
						<input type="text" id="address" name="address" placeholder="地址" value="${sessionScope.sessionUserInfo.user.address}">
					</div>
				</div>
				<div class="control-group" id="cellphone">
					<label class="control-label" for="cellphone">手机</label>
					<div class="controls">
						<input type="text" id="cellphone" name="cellphone" placeholder="手机号码" value="${sessionScope.sessionUserInfo.user.cell_phone}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="phone">固话</label>
					<div class="controls">
						<input type="text" id="phone" name="phone" placeholder="固定电话" value="${sessionScope.sessionUserInfo.user.fix_phone}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="fax">传真</label>
					<div class="controls">
						<input type="text" id="fax" name="fax" placeholder="传真号码" value="${sessionScope.sessionUserInfo.user.fax}">
					</div>
				</div>-->
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-large btn-success">下一步</button>
					</div>
				</div>
			</form> 
			second
		</div>
		
		<!-- third -->
		<div class="tab-pane  <c:if test='${active == "third"}'>active</c:if>" id="third_tab">
			<form class="form-horizontal" action="thirdstep" method="post"  name="edu">
					<!--  <input type="hidden" name="eduId" >
					<div class="control-group" id="schoolName">
						<label class="control-label" for="schoolName">学校</label>
						<div class="controls">
							<input type="text"  name="schoolName"  id="schoolName" placeholder="学校" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="collegeName">
						<label class="control-label" for="collegeName">学院</label>
						<div class="controls">
							<input type="text" name="collegeName" id="collegeName" value="" placeholder="学院"  >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="degree">
						<label class="control-label" for="degree">学历</label>
						<div class="controls">
							<input type="text" id="degree"   name="degree" placeholder="学历"  >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="startTime">
						<label class="control-label" for="startTime">开始时间</label>
						<div class="controls">
							<input type="text" id="startTime"  name="startTime" placeholder="开始时间" >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="endTime">
						<label class="control-label" for="endTime">结束时间</label>
						<div class="controls">
							<input type="text" id="endTime" name="endTime" placeholder="结束时间" >
							<span class="help-inline"></span>
						</div>
					</div> -->
					<div class="control-group">
						<div class="controls">
							<button type="submit"   class="btn btn-large btn-success">保 存</button>
						</div>
					</div>
				</form>
				third
		</div>

	</div>
</div>


<script type="text/javascript">


</script>