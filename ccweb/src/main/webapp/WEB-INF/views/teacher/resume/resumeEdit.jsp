<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/resumeEdit.js" />"></script>
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
<script type="text/javascript">
$(function(){
	var mes=$("#message").attr("title");
	if(mes!=''){
		alert("信息已保存");
		return false;
	}
});
</script>



<div class="row-fluid custom round">
	<div class="row">
		<h4>完善简历</h4>
		<i class="icon-star"></i>
		<i>必须填写项</i>
	</div>
	<div class="row1">
		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li <c:if test='${active == "personal"}'>class="active"</c:if>><a href="#personal_info_tab" data-toggle="tab">个人信息</a></li>
				<li <c:if test='${active == "contact"}'>class="active"</c:if>><a href="#contact_info_tab" data-toggle="tab">联系方式</a></li>
				<c:if test="${sessionScope.sessionUserInfo.teacher.isEnterprise == null}">
					<li <c:if test='${active == "edu"}'>class="active"</c:if>><a href="#edu_bg_tab" data-toggle="tab">教育背景</a></li>
					<li <c:if test='${active == "work"}'>class="active"</c:if>><a href="#work_exp_tab" data-toggle="tab">工作经历</a></li>
					<li <c:if test='${active == "thesis"}'>class="active"</c:if>><a href="#thesis_tab" data-toggle="tab">论文</a></li>
					<li <c:if test='${active == "project"}'>class="active"</c:if>><a href="#project_tab" data-toggle="tab">项目</a></li>
					<li <c:if test='${active == "patent"}'>class="active"</c:if>><a href="#patent_tab" data-toggle="tab">专利</a></li>
				</c:if>
				<li <c:if test='${active == "honor"}'>class="active"</c:if>><a href="#honor_tab" data-toggle="tab">荣誉</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "personal"}'>active</c:if>" id="personal_info_tab">
					<c:if test="${sessionScope.sessionUserInfo.teacher.isEnterprise == null}">
						<form id="personal_info_form" action="personalInfo" class="form-horizontal" method="post">
							<div id="message" style="margin-left: 155px;" title="${message}"></div>
							<div class="control-group" id="name">
								<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
								<div class="controls">
									<input type="text" name="name" placeholder="姓名" value="${sessionScope.sessionUserInfo.name}"> <span class="help-inline"><form:errors path="name"></form:errors></span>
								</div>
							</div>
							<div class="control-group" id="gender">
								<label class="control-label" for="gender"><i class="icon-star"></i> 性别</label>
								<div class="controls">
									<label class="radio"> <input type="radio" name="gender" id="genderMale" value="男" <c:if test='${sessionScope.sessionUserInfo.gender != "女"}'>checked</c:if>>男
									</label> <label class="radio"> <input type="radio" name="gender" id="genderFemale" value="女" <c:if test='${sessionScope.sessionUserInfo.gender == "女"}'>checked</c:if>>女
									</label>
								</div>
							</div>
							<div class="control-group" id="college">
								<label class="control-label" for="college"><i class="icon-star"></i> 所属高校</label>
								<div class="controls">
									<input type="text" name="college"   placeholder="所属高校" value="${sessionScope.sessionUserInfo.teacher.college}" style="margin: 0 auto;" data-provide="typeahead" data-items="8"
										data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'> <span class="help-inline"><form:errors path="college" /></span>
								</div>
							</div>
							<div class="control-group" id="school">
								<label class="control-label" for="school"><i class="icon-star"></i> 所属院系</label>
								<div class="controls">
									<input type="text" name="school"   placeholder="所属院系" value="${sessionScope.sessionUserInfo.teacher.school}" style="margin: 0 auto;" data-provide="typeahead" data-items="4"
										data-source='["计算机学院","财金学院","女子学院"]'> <span class="help-inline"><form:errors path="school" /></span>
								</div>
								<!--  
								<div class="controls">
									<select class="span3" name="college">
										<option>计算机学院</option>
										<option>财金学院</option>
										<option>女子学院</option>
									</select>
								</div>
								-->
							</div>
							<div class="control-group" id="major">
								<label class="control-label" for="major">研究方向</label>
								<div class="controls">
									<input type="text" name="major" value="${sessionScope.sessionUserInfo.teacher.major}" placeholder="教授课程">
									<span class="help-inline"><form:errors path="major"></form:errors></span>
								</div>
							</div>
							<div class="control-group" id="title">
								<label class="control-label" for="title">职称/职务</label>
								<div class="controls">
									<input type="text" name="title" value="${sessionScope.sessionUserInfo.teacher.title}" placeholder="职称">
									<span class="help-inline"><form:errors path="title"></form:errors></span>
								</div>
							</div>
							<div class="control-group" id="role">
								<label class="control-label" for="role">导师类别</label>
								<div class="controls">
									<input type="text" name="role" value="${sessionScope.sessionUserInfo.teacher.role}" placeholder="导师类别">
									<span class="help-inline"><form:errors path="role"></form:errors></span>
								</div>
							</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return personalOnclick();" class="btn  btn-success">保 存</button>
							</div>
						</div>
					</form>
					</c:if>
					<c:if  test="${sessionScope.sessionUserInfo.teacher.isEnterprise != null}">
						<form id="enterprise_info_form" action="enterprisepersonalInfo" class="form-horizontal" method="post">
							<div id="message" style="margin-left: 155px;" title="${message}"></div>
							<div class="control-group" id="name">
								<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
								<div class="controls">
									<input type="text" name="name" placeholder="姓名" value="${sessionScope.sessionUserInfo.name}"> <span class="help-inline"><form:errors path="name"></form:errors></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" onclick="return personalOnclick();" class="btn  btn-success">保 存</button>
								</div>
							</div>
						</form>
					</c:if>
				</div>
				<div class="tab-pane <c:if test='${active == "contact"}'>active</c:if>" id="contact_info_tab">
					<form class="form-horizontal" action="contactInfo" method="post" id="teacher_contact_from">
					<div style="margin-left: 155px;" id="message" title="${message}"></div>
						<div class="control-group" id="address">
							<label class="control-label" for="address">地址</label>
							<div class="controls">
								<input type="text"  name="address" placeholder="地址" value="${sessionScope.sessionUserInfo.user.address}">
								<span class="help-inline"><form:errors path="address"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="cellphone">
							<label class="control-label" for="cellphone">手机</label>
							<div class="controls">
								<input type="text"  name="cellphone" placeholder="手机号码" value="${sessionScope.sessionUserInfo.user.cell_phone}">
								<span class="help-inline"><form:errors path="cellphone"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="phone">
							<label class="control-label" for="phone">固话</label>
							<div class="controls">
								<input type="text"  name="phone" placeholder="固定电话" value="${sessionScope.sessionUserInfo.user.fix_phone}">
								<span class="help-inline"><form:errors path="phone"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="fax">
							<label class="control-label" for="fax">传真</label>
							<div class="controls">
								<input type="text"  name="fax" placeholder="传真号码" value="${sessionScope.sessionUserInfo.user.fax}">
								<span class="help-inline"><form:errors path="fax"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="qq">
							<label class="control-label" for="qq">QQ</label>
							<div class="controls">
								<input type="text" name="qq" placeholder="QQ" value="${sessionScope.sessionUserInfo.user.qq}">
								<span class="help-inline"><form:errors path="qq"></form:errors></span>
							</div>
						</div>
						<div class="control-group" id="msn">
							<label class="control-label" for="msn">MSN</label>
							<div class="controls">
								<input type="text"  name="msn" placeholder="MSN" value="${sessionScope.sessionUserInfo.user.msn}">
								<span class="help-inline"><form:errors path="msn"></form:errors></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="reuturn:contactOnclick();"    class="btn  btn-success">保 存</button>
							</div>
						</div>
					</form>
				</div>
		
				<!-- Edu Info -->
				<div class="tab-pane  <c:if test='${active == "edu"}'>active</c:if>" id="edu_bg_tab">
					<div id="eduForm" style="display: none;  border: 0px solid; padding-left: 20px;">
						<form action="eduInfo" method="post" id="edu_info_form" name="edu" >
							<input type="hidden" name="eduId">
							<!-- 
							<div class="control-group" id="schoolName">
								<label class="control-label" for="schoolName">学校</label>
								<div class="controls">
									<input type="text" name="schoolName"  placeholder="学校" data-provide="typeahead" data-items="8"
										data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="collegeName">
								<label class="control-label" for="collegeName">学院</label>
								<div class="controls">
									<input type="text" name="collegeName"   value="" placeholder="学院"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="degree">
								<label class="control-label" for="degree">学历</label>
								<div class="controls">
									<input type="text"  name="degree"  placeholder="学历"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="startTime">
								<label class="control-label" for="startTime">开始时间</label>
								<div class="controls">
									<input type="text"  name="startTime"  placeholder="开始时间"> <span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group" id="endTime">
								<label class="control-label" for="endTime">结束时间</label>
								<div class="controls">
									<input type="text"  name="endTime"  placeholder="结束时间"> <span class="help-inline"></span>
								</div>
							</div>
							-->
							<div class="control-group"  id="educationDesc" >
								<div class="controls">
									<textarea  id="KEeducationDesc"  name="educationDesc" rows="6" cols="8" style="width: 600px; height: 300px;"></textarea>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit"  class="btn  btn-success">保 存</button>
									<button type="reset" onclick="closeEduAddForm()" class="btn ">取消</button>
								</div>
							</div>
						</form>
					</div>
		
					<div id="eduList" style="display: block">
						<c:choose>
							<c:when test="${eduCount>0 }">
								<table class="table">
									<thead>
										<tr>
											<th colspan="2">详细内容</th>
											<!-- <th>学院</th>
											<th>学历</th>
											<th>起止时间</th>
											<th>操作</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${eduInfo}" var="eduInfo">
											<tr>
												<td colspan="2" >${eduInfo.educationDesc}</td>
											</tr>
											<tr>
											<!-- 
												<td align="center">${eduInfo.school}</td>
												<td align="center">${eduInfo.college}</td>
												<td align="center">${eduInfo.degree}</td>
												<td align="center" width="25%">${eduInfo.startTime} - ${eduInfo.endTime}</td> -->
												<td width="85%"></td>
												<td  width="15%">
													<a class="deleteEduPostBtn" href="#deleteEduPostModal" role="button" data-toggle="modal" data-target="#deleteEduPostModal">删除</a> |
													<a class="editEduAjaxBtn" href="javascript:void(0)">修改</a><input type="hidden" value="${eduInfo.id }" id="eduInfo_id">
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
						<button onclick="showEduAddForm()" class="btn btn-success">添加</button>
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
							<form action='<c:url value="/admin/teacher/eduInfo/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="eduId" type="hidden" name="eduId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
		
				</div>
		
				<!-- work exp  -->
				<div class="tab-pane <c:if test='${active == "work"}'>active</c:if>" id="work_exp_tab">
		
					<div id="workForm" style="display: none; padding-left: 20px;">
						<form  action="workInfo" method="post" id="workExpForm" name="work">
							<input type="hidden" name="workId">
							<!--  
							<div class="control-group" id="company">
								<label class="control-label" for="company">单位</label>
								<div class="controls">
									<input type="text" name="company" placeholder="单位"> <span class="help-inline"><form:errors path="company" /></span>
								</div>
							</div>
							<div class="control-group" id="department">
								<label class="control-label" for="department">部门</label>
								<div class="controls">
									<input type="text" name="department" placeholder="部门"> <span class="help-inline"><form:errors path="department" /></span>
								</div>
							</div>
							<div class="control-group" id="position">
								<label class="control-label" for="position">职位</label>
								<div class="controls">
									<input type="text" name="position" placeholder="职位"> <span class="help-inline"><form:errors path="position" /></span>
								</div>
							</div>
							<div class="control-group" id="startTimeName">
								<label class="control-label" for="startTimeName">开始时间</label>
								<div class="controls">
									<input type="text" name="startTimeName" placeholder="开始时间"> <span class="help-inline"><form:errors path="startTime" /></span>
								</div>
							</div>
							<div class="control-group" id="endTimeName">
								<label class="control-label" for="endTimeName">结束时间</label>
								<div class="controls">
									<input type="text" name="endTimeName" placeholder="结束时间"> <span class="help-inline"><form:errors path="endTime" /></span>
								</div>
							</div>
							-->
							<div class="control-group" id="workDesc">
								<div class="controls">
									<textarea  name="workDesc"   id="workDescs" rows="6" cols="8" style="width: 600px; height: 300px;"></textarea>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit"  class="btn  btn-success">保 存</button>
									<button type="reset" onclick="closeWorkAddForm()" class="btn ">取消</button>
								</div>
							</div>
						</form>
					</div>
		
					<div id="workList" style="display: block">
						<c:choose>
							<c:when test="${(workCount >0)}">
								<table class="table">
									<thead>
										<tr>
											<!-- 
											<th>单位</th>
											<th>部门</th>
											<th>职位</th>
											<th>起止时间</th> -->
											<th colspan="2">详细内容</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${workInfo}" var="workInfo">
											<tr>
												<td colspan="2">${workInfo.workDesc}</td>
											</tr>
											<tr>
												
												<!-- <td align="center">${workInfo.company}</td>
												<td align="center">${workInfo.department}</td>
												<td align="center">${workInfo.position}</td>
												<td align="center" width="25%">${workInfo.startTime} - ${workInfo.endTime}</td> -->
												<td width="85%">&nbsp;</td>
												<td width="15%">
													<a class="deleteWorkPostBtn" href="#deleteWorkPostModal" role="button" data-toggle="modal" data-target="#deleteWorkPostModal">删除</a> |
													<a href='javascript:void(0)' class="editWorkAjaxBtn">修改</a>
													<input type="hidden" value="${workInfo.id} ">
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
		
						<button onclick="showWorkAddForm()"  class="btn btn-success">添加</button>
					</div>
		
					<!-- work Modal -->
					<div class="modal hide fade" id="deleteWorkPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/teacher/workInfo/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="workId" type="hidden" name="workId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
				</div>
		
				<!-- Thesis -->
		
				<div class="tab-pane <c:if test='${active == "thesis"}'>active</c:if>" id="thesis_tab">
					<div id="thesisList" style="display: block">
						<c:choose>
							<c:when test="${thesisCount>0}">
								<table class="table">
									<thead>
										<tr>
											<th colspan="2">详细内容</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${thesisList}" var="thesis">
											<tr>
												<td colspan="2">${thesis.content}</td>
											</tr>
											<tr>
												<td width="85%"></td>
												<td>
													<a class="deleteThesisPostBtn" href="#deleteWorkPostModal" role="button" data-toggle="modal" data-target="#deleteThesisPostModal">删除</a>
													| <a href='javascript:void(0)' class="editThesisAjaxBtn">修改</a>
													<input type="hidden" value="${thesis.id}">
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
						<button onclick="showThesisAddForm();" class="btn btn-success">添加</button>
					</div>
					<div id="thesisNewInfoForm" style="display: none; padding-left: 20px;">
						<form action="thesis/new" method="post" id="thesis_info_form" name="thesis">
						<input type="hidden" name="thesisId">
							<div class="control-group" id="content">
								<div class="controls">
									<textarea  name="content" id="contents" rows="6" cols="8" style="width: 600px; height: 300px;"></textarea>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn  btn-success">保 存</button>
									<button class="btn" type="reset" onclick="hiddenThesisAddForm()">取消</button>
								</div>
							</div>
						</form>
					</div>
		
					<!-- thesis Modal -->
					<div class="modal hide fade" id="deleteThesisPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/teacher/thesis/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="thesisId" type="hidden" name="thesisId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
				</div>
		
				<!--  project  -->
				<div class="tab-pane <c:if test='${active == "project"}'>active</c:if>" id="project_tab">
					<div id="project" style="display: block">
						<div id="projectForm" style="display: none; padding-left: 20px;">
							<form action="project/new" method="post" id="project_info_form"  name="project">
								<input type="hidden" name="projectId">
								<!--  
								<div class="control-group" id="projectTitle">
									<label class="control-label" for="projectTitle">项目名称</label>
									<div class="controls">
										<input type="text" name="projectTitle" placeholder="项目名称"> <span class="help-inline"><form:errors path="projectTitle" /></span>
									</div>
								</div>
								<div class="control-group" id="projectSource">
									<label class="control-label" for="projectSource">项目来源</label>
									<div class="controls">
										<input type="text" name="projectSource" placeholder="项目来源"> <span class="help-inline"><form:errors path="projectSource" /></span>
									</div>
								</div>
								<div class="control-group" id="projectStartTime">
									<label class="control-label" for="projectStartTime">开始时间</label>
									<div class="controls">
										<input type="text" name="projectStartTime" placeholder="开始时间"> <span class="help-inline"><form:errors path="projectStartTime" /></span>
									</div>
								</div>
								<div class="control-group" id="projectEndTime">
									<label class="control-label" for="projectEndTime">结束时间</label>
									<div class="controls">
										<input type="text" name="projectEndTime" placeholder="结束时间"> <span class="help-inline"><form:errors path="projectEndTime" /></span>
									</div>
								</div>
								-->
								<div class="control-group" id="projectDesc">
									<div class="controls">
										<textarea  name="projectDesc" id="projectDescs"   rows="6" cols="8" style="width: 600px; height: 300px;"></textarea>
										<span class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit"  class="btn  btn-success">保存</button>
										&nbsp;&nbsp;
										<button class="btn  " type="reset" onclick="hiddenProjectAddForm()">取消</button>
									</div>
								</div>
							</form>
						</div>
						<div id="projectList" style="display: block">
							<c:choose>
								<c:when test="${projectCount>0}">
									<table class="table">
										<thead>
											<tr>
												<th colspan="2">详细内容</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${projectList}" var="project">
												<tr style="margin-left: -70px;">
													<td colspan="2">${project.desc}</td>
												</tr>
												<tr>
													<!-- 
													<td align="left">${project.title}</td>
													<td align="left">${project.source}</td>
													<td align="left">${project.startTime}</td>
													<td align="left">${project.endTime}</td> -->
													<td width="85%"></td>
													<td >
														<a class="deleteProjectPostBtn" href="#deleteProjectPostModal" role="button" data-toggle="modal" data-target="#deleteProjectPostModal">删除</a> 
														| <a href='javascript:void(0)' class="editProjectAjaxBtn">修改</a><input type="hidden" value="${project.id}">
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
							<button onclick="showProjectAddForm()" class="btn btn-success">添加</button>
						</div>
					</div>
		
					<!-- project Modal -->
					<div class="modal hide fade" id="deleteProjectPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/teacher/project/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="projectId" type="hidden" name="projectId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
				</div>
		
				<!-- patent -->
				<div class="tab-pane <c:if test='${active == "patent"}'>active</c:if>" id="patent_tab">
					<div id="patent" style="display: block">
						<div id="patentForm" style="display: none; padding-left: 20px;">
							<form action="patent/new" method="post" id="patent_info_form" name="teacherPatent" >
							<input type="hidden" name="patentId">
							<!-- 
								<div class="control-group" id="inventer">
									<label class="control-label" for="inventer">发明人</label>
									<div class="controls">
										<input type="text" name="inventer" placeholder="发明人"> <span class="help-inline"><form:errors path="inventer" /></span>
									</div>
								</div>
								<div class="control-group" id="patentName">
									<label class="control-label" for="patentName">专利名称</label>
									<div class="controls">
										<input type="text" name="patentName" placeholder="专利名称"> <span class="help-inline"><form:errors path="patentName" /></span>
									</div>
								</div>
								<div class="control-group" id="patentType">
									<label class="control-label" for="patentType">专利类型</label>
									<div class="controls">
										<input type="text" name="patentType" placeholder="专利类型"> <span class="help-inline"><form:errors path="patentType" /></span>
									</div>
								</div>
								<div class="control-group" id="number">
									<label class="control-label" for="number">专利号</label>
									<div class="controls">
										<input type="text" name="number" placeholder="专利号"> <span class="help-inline"><form:errors path="number" /></span>
									</div>
								</div> -->
								<div class="control-group"  id="patentDesc">
									<div class="controls">
										<textarea name="patentDesc" id="patentDescs" rows="6" cols="8" style="width: 600px; height: 300px;"></textarea>
										<span class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit"  class="btn  btn-success">保存</button>
										<button class="btn  " type="reset" onclick="hiddenPatentAddForm()">取消</button>
									</div>
								</div>
							</form>
						</div>
						<div id="patentList" style="display: block">
							<c:choose>
								<c:when test="${patentCount>0 }">
									<table class="table">
										<thead>
											<tr>
												<th >详细内容</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${patentList}" var="patent">
												<tr>
													<td colspan="2">${patent.desc}</td>
												</tr>
												<tr>
												<!-- 
													<td align="center">${patent.inventer}</td>
													<td align="center">${patent.name}</td>
													<td align="center">${patent.type}</td>
													<td align="center">${patent.number}</td> -->
													<td width="85%"></td>
													<td><a class="deletePatentPostBtn" href="#deletePatentPostModal" role="button" data-toggle="modal" data-target="#deletePatentPostModal">删除</a>
														| <a href='javascript:void(0)' class="editPatentAjaxBtn">修改</a>
														<input type="hidden" value="${patent.id}">
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
							<button onclick="showPatentAddForm()" class="btn btn-success">添加</button>
						</div>
					</div>
		
					<!-- patentModal -->
					<div class="modal hide fade" id="deletePatentPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/teacher/patent/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="patentId" type="hidden" name="patentId" />
								<button class="btn btn-success">确定</button>
							</form>
						</div>
					</div>
				</div>
		
				<!-- honor -->
				<div class="tab-pane <c:if test='${active == "honor"}'>active</c:if>" id="honor_tab">
					<div id="honor" style="display: block">
						<div id="honorForm" style="display: none; padding-left: 20px;">
							<form action="honor/new" method="post" id="honor_info_Form" name="teacherHonor" >
							<input type="hidden" name="honorId">
								<!-- <div class="control-group" id="honorName">
									<label class="control-label" for="honorName">奖励荣誉</label>
									<div class="controls">
										<input type="text" name="honorName" placeholder="奖励或荣誉"> <span class="help-inline"><form:errors path="honorName" /></span>
									</div>
								</div>
								<div class="control-group" id="reason">
									<label class="control-label" for="reason">获奖原因</label>
									<div class="controls">
										<input type="text" name="reason" placeholder="获奖原因"> <span class="help-inline"><form:errors path="reason" /></span>
									</div>
								</div> -->
								<div class="control-group" id="honorDesc">
									<div class="controls">
										<textarea  name="honorDesc" id="honorDescs" rows="6" cols="8" style="width: 600px; height:300px;"></textarea>
										<span class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit"  class="btn  btn-success">保存</button>
										<button class="btn  " type="reset" onclick="hiddenHonorAddForm()">取消</button>
									</div>
								</div>
							</form>
						</div>
						<div id="honorList" style="display: block">
							<c:choose>
								<c:when test="${honorCount >0}">
									<table class="table ">
										<thead>
											<tr>
												<th  colspan="2">详细内容</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${honorList}" var="honor">
												<tr>
													<td colspan="2">${ honor.desc}</td>
												</tr>
												<tr>
													<!-- <td align="center">${honor.name}</td>
													<td align="center">${honor.reason}</td> -->
													<td width="85%"></td>
													<td><a class="deleteHonorPostBtn" href="#deleteHonorPostModal" role="button" data-toggle="modal" data-target="#deleteHonorPostModal">删除</a> 
														| <a href='javascript:void(0)' class="editHonorAjaxBtn">修改</a><input type="hidden" value="${honor.id}">
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
							<button onclick="showHonorAddForm()" class="btn btn-success">添加</button>
						</div>
					</div>
		
					<!-- honorModal -->
					<div class="modal hide fade" id="deleteHonorPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">请注意</h3>
						</div>
						<div class="modal-body">
							<p>你确定删除吗？</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
							<form action='<c:url value="/admin/teacher/honor/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="honorId" type="hidden" name="honorId" />
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