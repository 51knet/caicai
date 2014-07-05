<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/userResumeEdit.js" />"></script>
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
	/*color: #80b029;*/
	color: #3d4f67;
	border-bottom: solid #cccccc 1.5px;
}
.row-fluid.custom .content {
	margin: 20px 40px;
}
.nav-tabs >li >a:hover{
	background-color:#b1b6ba;
}
</style>
<script type="text/javascript">
/*   $(function(){
	  var mes=$("#message").attr("title");
	if(mes!=''){
		alert("信息已保存");
		return false;
	} 
	
}); */  
	setTimeout(function(){
 		document.getElementById("message").style.display="none";
	},2000);
	setTimeout(function(){
 		document.getElementById("mess").style.display="none";
	},2000);
	setTimeout(function(){
 		document.getElementById("messages").style.display="none";
	},2000);

 	  
</script>



<div class="row-fluid custom round">
	<div class="row">
		<h4>完善信息</h4>
		<i class="icon-star"></i>
		<i>必须填写项</i>
	</div>
	<div class="content">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li <c:if test='${active == "personal"}'>class="active"</c:if>><a href="#personal_info_tab" data-toggle="tab">个人信息</a></li>
				<li <c:if test='${active == "contact"}'>class="active"</c:if>><a href="#contact_info_tab" data-toggle="tab">联系方式</a></li>
					<li <c:if test='${active == "edu"}'>class="active"</c:if>><a href="#user_edu_bg_tab" data-toggle="tab">教育背景</a></li>
					<li <c:if test='${active == "work"}'>class="active"</c:if>><a href="#user_work_exp_tab" data-toggle="tab">工作经历</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "personal"}'>active</c:if>" id="personal_info_tab">
				
						<form id="personal_info_form" action="user/personalInfo" class="form-horizontal" method="post">
							<div id="message" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
							<div class="control-group" id="name">
								<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
								<div class="controls">
									<input type="text" name="name" placeholder="姓名" value="${sessionUserInfo.name}"> <span class="help-inline"><form:errors path="name"></form:errors></span>
								</div>
							</div>
							<div class="control-group" id="gender">
								<label class="control-label" for="gender"><i class="icon-star"></i> 性别</label>
								<div class="controls">
									<label class="radio"> 
									<input type="radio" name="gender" id="genderMale" value="男" <c:if test='${sessionUserInfo.gender != "女"}'>checked</c:if>>男
									</label> 
									<label class="radio"> 
									<input type="radio" name="gender" id="genderFemale" value="女" <c:if test='${sessionUserInfo.gender == "女"}'>checked</c:if>>女
									</label>
								</div>
							</div>
							
					
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return userPersonalOnclick();" class="btn  btn-success">保 存</button>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane <c:if test='${active == "contact"}'>active</c:if>" id="contact_info_tab">
					<form class="form-horizontal" action="contactInfo" method="post" id="teacher_contact_from">
					<div id="mess" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
						<div class="control-group" id="address">
							<label class="control-label" for="address">地址</label>
							<div class="controls">
								<input type="text"  name="address" placeholder="地址" value="${sessionUserInfo.user.address}">
								<span class="help-inline"><form:errors path="address"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="cellphone">
							<label class="control-label" for="cellphone">手机</label>
							<div class="controls">
								<input type="text"  name="cellphone" placeholder="手机号码" value="${sessionUserInfo.user.cell_phone}">
								<span class="help-inline"><form:errors path="cellphone"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="phone">
							<label class="control-label" for="phone">固话</label>
							<div class="controls">
								<input type="text"  name="phone" placeholder="固定电话" value="${sessionUserInfo.user.fix_phone}">
								<span class="help-inline"><form:errors path="phone"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="fax">
							<label class="control-label" for="fax">传真</label>
							<div class="controls">
								<input type="text"  name="fax" placeholder="传真号码" value="${sessionUserInfo.user.fax}">
								<span class="help-inline"><form:errors path="fax"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="qq">
							<label class="control-label" for="qq">QQ</label>
							<div class="controls">
								<input type="text" name="qq" placeholder="QQ" value="${sessionUserInfo.user.qq}">
								<span class="help-inline"><form:errors path="qq"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="msn">
							<label class="control-label" for="msn">MSN</label>
							<div class="controls">
								<input type="text"  name="msn" placeholder="MSN" value="${sessionUserInfo.user.msn}">
								<span class="help-inline"><form:errors path="msn"></form:errors></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return contactOnclick();"    class="btn  btn-success">保 存</button>
							</div>
						</div>
					</form>
				</div>
		
				<!-- Edu Info -->
				<div class="tab-pane  <c:if test='${active == "edu"}'>active</c:if>" id="user_edu_bg_tab">
					<div id="usereduDiv" style="display: none;  border: 0px solid; padding-left: 20px;">
						请选择教育等级：<a href="javascript:void(0)" onclick="showUserLowEduForm()">初等教育</a> 
						<a href="javascript:void(0)" onclick="showUserHighEduForm()">高等教育</a><br><br>
						
						<form style="display: block" action="user/lowEduInfo" method="post" id="user_lowedu_info_form" name="lowedu"  class="form-horizontal" >
							<input type="hidden" name="loweduId">
							<div class="control-group offset2"  >
								<label class="radio inline" >
									<input type="radio" name="level" value="1" checked="checked" >幼儿园
								</label>
								<label class="radio inline" >
									<input type="radio" name="level" value="2" >小学
								</label>
								<label class="radio inline" >
									<input type="radio" name="level" value="3" >初中
								</label>
								<label class="radio inline" >
									<input type="radio" name="level" value="4" >高中
								</label>
							</div>
							<div class="control-group" id="lowSchoolName">
								<label class="control-label" for="lowSchoolName">学校</label>
								<div class="controls">
									<input type="text" name="lowSchoolName"  placeholder="学校" > <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="lowClassNum">
								<label class="control-label" for="lowClassNum">班级</label>
								<div class="controls">
									<input type="text"  name="lowClassNum"  placeholder="专业"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="lowTeacherName">
								<label class="control-label" for="lowTeacherName">教师名称</label>
								<div class="controls">
									<input type="text"  name="lowTeacherName"  placeholder="教师名称"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="lowStartTime">
								<label class="control-label" for="lowStartTime">入学时间</label>
								<div class="controls">
									<input type="text"  name="lowStartTime"  placeholder="入学时间"  class="Wdate" onClick="WdatePicker()"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit"  class="btn  btn-success">保 存</button>
									<button type="reset" onclick="closeuserEduAddForm()" class="btn ">取消</button>
								</div>
							</div>
						</form>
						<form style="display: none" action="user/highEduInfo" method="post" id="user_highedu_info_form" name="highedu"  class="form-horizontal" >
							<input type="hidden" name="higheduId">
							<div class="control-group offset2"   >
								<label class="radio inline" >
									<input type="radio" name="level" value="5" checked="checked" >本科
								</label>
								<label class="radio inline" >
									<input type="radio" name="level" value="6" >研究生
								</label>
							</div>
							<div class="control-group" id="highSchoolName">
								<label class="control-label" for="highSchoolName">学校</label>
								<div class="controls">
									<input type="text" name="highSchoolName"  placeholder="学校" > <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="highCollegeName">
								<label class="control-label" for="highCollegeName">学院</label>
								<div class="controls">
									<input type="text" name="highCollegeName"   value="" placeholder="学院"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="highMajor">
								<label class="control-label" for="highMajor">专业</label>
								<div class="controls">
									<input type="text"  name="highMajor"  placeholder="专业"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="highClassNum">
								<label class="control-label" for="highClassNum">班级</label>
								<div class="controls">
									<input type="text"  name="highClassNum"  placeholder="专业"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="highTeacherName">
								<label class="control-label" for="highTeacherName">导师</label>
								<div class="controls">
									<input type="text"  name="highTeacherName"  placeholder="专业导师"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="highStartTime">
								<label class="control-label" for="highStartTime">入学时间</label>
								<div class="controls">
									<input type="text"  name="highStartTime"  placeholder="入学时间"  class="Wdate" onClick="WdatePicker()"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit"  class="btn  btn-success">保 存</button>
									<button type="reset" onclick="closeuserEduAddForm()" class="btn ">取消</button>
								</div>
							</div>
						</form>
					</div>
		
					<div id="usereduList" style="display: block">
						<c:choose>
							<c:when test="${eduCount>0 }">
								<table class="blue" width="100%" cellpadding="5">
									<thead>
										<tr>
											<th>教育程度</th>
											<th>学校</th>
											<th>学院</th> 
											<th>专业</th> 
											<th>班级</th>
											<th>教师</th>
											<th>入学时间</th> 
											<th>操作</th> 
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${eduInfo}" var="eduInfo">
											<tr>
												<td align="center"><c:forEach items="${levelmap}" var="map">
													<c:if test="${map.key == eduInfo.level }">${map.value }</c:if>
												</c:forEach></td>
												<td align="center">${eduInfo.school}</td>
												<td align="center">${eduInfo.college}<c:if test="${eduInfo.college == null }"> 无</c:if></td>
												<td align="center">${eduInfo.major}<c:if test="${eduInfo.major == null}"> 无</c:if></td>
												<td align="center">${eduInfo.classNum}</td>
												<td align="center">${eduInfo.teacherNam}</td>
												<td align="center" >${eduInfo.startTime}</td> 
												<td  width="15%" align="center" >
													<a class="deleteEduPostBtn" href="#deleteEduPostModal" role="button" data-toggle="modal" data-target="#deleteEduPostModal">删除</a> |
													<a class="edituserEduAjaxBtn" href="javascript:void(0)">修改</a><input type="hidden" value="${eduInfo.id }" id="eduInfo_id">
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								尚未添加内容
								<br>
								<br>
							</c:otherwise>
						</c:choose>
						<br>
						<button onclick="showuserEduAddForm()" class="btn btn-success">添加</button>
					</div>
		
					<!-- edu Modal -->
					<div class="modal hide fade" id="deleteEduPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/eduInfo/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="eduId" type="hidden" name="eduId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
		
				</div>
		
				<!-- work exp  -->
				<div class="tab-pane <c:if test='${active == "work"}'>active</c:if>" id="user_work_exp_tab">
		
					<div id="userworkForm" style="display: none; ">
						<form  action="user/workInfo" method="post" id="userworkExpForm" name="userwork" class="form-horizontal">
						<input type="hidden" name="workId">
							<div class="control-group" id="company">
								<label class="control-label" for="company">所在公司</label>
								<div class="controls">
									<input type="text" name="company" placeholder="单位"> <span class="help-inline"><form:errors path="company" /></span>
								</div>
							</div>
							<div class="control-group" id="position">
								<label class="control-label" for="position">所处职位</label>
								<div class="controls">
									<input type="text" name="position" placeholder="职位"> <span class="help-inline"><form:errors path="position" /></span>
								</div>
							</div>
							<div class="control-group" id="startTime">
								<label class="control-label" for="startTime">入职时间</label>
								<div class="controls">
									<input type="text" name="startTime" placeholder="入职时间"  class="Wdate" onClick="WdatePicker()"> <span class="help-inline"><form:errors path="startTime" /></span>
								</div>
							</div>
							<div class="control-group" id="endTime">
								<label class="control-label" for="endTime">离职时间</label>
								<div class="controls">
									<input type="text" name="endTime" placeholder="离职时间"  class="Wdate" onClick="WdatePicker()"> <span class="help-inline"><form:errors path="endTime" /></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit"  class="btn  btn-success">保 存</button>
									<button type="reset" onclick="closeuserWorkAddForm()" class="btn ">取消</button>
								</div>
							</div>
						</form>
					</div>
		
					<div id="userworkList" style="display: block">
						<c:choose>
							<c:when test="${(workCount >0)}">
								<table class="blue" width="100%" cellpadding="5">
									<thead>
										<tr>
											<th>公司名称</th>
											<th>公司职位</th>
											<th>起止时间</th> 
											<th>操作</th> 
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${workInfo}" var="workInfo">
											<tr>
												<td align="center">${workInfo.company}</td>
												<td align="center">${workInfo.position}</td>
												<td align="center" width="25%">${workInfo.startTime} - ${workInfo.endTime}</td> 
												<td align="center" width="15%">
													<a class="deleteuserWorkPostBtn" href="#deleteWorkPostModal" role="button" data-toggle="modal" data-target="#deleteuserWorkPostModal">删除</a><input type="hidden" value="${workInfo.id} "> |
													<a href='javascript:void(0)' class="edituserWorkAjaxBtn">修改</a><input type="hidden" value="${workInfo.id} ">
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
							<span style="font-size: 13px;">尚未添加内容<br>
								<br></span>
							</c:otherwise>
						</c:choose>
						<br>
						<button onclick="showuserWorkAddForm()"  class="btn btn-success">添加</button>
					</div>
		
					<!-- work Modal -->
					<div class="modal hide fade" id="deleteuserWorkPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/user/workInfo/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="userworkId" type="hidden" name="userWorkId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<c:url var="uploadJson" value="/file_upload/${sessionUserInfo.id}"></c:url>
<c:url var="fileManagerJson" value="/file_manager/${sessionUserInfo.id}"></c:url>
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/themes/default/default.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.css"/>" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/resources/kindeditor-4.1.3/plugins/code/prettify.js"/>"></script>