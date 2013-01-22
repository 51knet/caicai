<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="<c:url value="/resources/jquery/emptyCheck-ajax.js" />"></script>
<h3>完善简历</h3><i class="icon-star"></i><i>必须填写项</i>
<hr />
<div class="tabbable">
	<ul class="nav nav-tabs">
		<li <c:if test='${active == "personal"}'>class="active"</c:if>><a href="#personal_info_tab" data-toggle="tab">个人信息</a></li>
		<li <c:if test='${active == "contact"}'>class="active"</c:if>><a href="#contact_info_tab" data-toggle="tab">联系方式</a></li>
		<li <c:if test='${active == "edu"}'>class="active"</c:if>><a href="#edu_bg_tab" data-toggle="tab">教育背景</a></li>
		<li <c:if test='${active == "work"}'>class="active"</c:if>><a href="#work_exp_tab" data-toggle="tab">工作经历</a></li>
		<li <c:if test='${active == "thesis"}'>class="active"</c:if>><a href="#thesis_tab" data-toggle="tab">论文</a></li>
		<li <c:if test='${active == "project"}'>class="active"</c:if>><a href="#project_tab" data-toggle="tab">项目</a></li>
		<li <c:if test='${active == "patent"}'>class="active"</c:if>><a href="#patent_tab" data-toggle="tab">专利</a></li>
		<li <c:if test='${active == "honor"}'>class="active"</c:if>><a href="#honor_tab" data-toggle="tab">荣誉</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane <c:if test='${active == "personal"}'>active</c:if>" id="personal_info_tab">
			<form id="personal_info_form" action="personalInfo" class="form-horizontal" method="post">
				<div class="control-group" id="name">
					<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
					<div class="controls">
						<input type="text"  name="name" placeholder="姓名" value="${sessionScope.sessionUserInfo.name}" >
						<span class="help-inline"><form:errors path="name"></form:errors></span>
					</div>
				</div>
				<div class="control-group" id="gender">
					<label class="control-label" for="gender"><i class="icon-star"></i> 性别</label>
					<div class="controls">
						<label class="radio"> <input type="radio" name="gender" id="genderMale" value="男" <c:if test='${sessionScope.sessionUserInfo.gender != "女"}'>checked</c:if>>男</label>
						<label class="radio"> <input type="radio" name="gender" id="genderFemale" value="女" <c:if test='${sessionScope.sessionUserInfo.gender == "女"}'>checked</c:if>>女</label>
					</div>
				</div>
				<div class="control-group" id="college">
					<label class="control-label" for="college"><i class="icon-star"></i> 所属高校</label>
					<div class="controls">
						<input type="text"  name="college" placeholder="所属高校" value="${sessionScope.sessionUserInfo.teacher.college}" style="margin: 0 auto;" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
						<span class="help-inline"><form:errors path="college" /></span>
					</div>
				</div>
				<div class="control-group" id="school">
					<label class="control-label" for="school"><i class="icon-star"></i> 所属院系</label>
					<div class="controls">
						<input type="text"  name="school" placeholder="所属院系" value="${sessionScope.sessionUserInfo.teacher.school}" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source='["计算机学院","财金学院","女子学院"]'>
						<span class="help-inline"><form:errors path="school" /></span>
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
					<label class="control-label" for="major">教授课程</label>
					<div class="controls">
						<input type="text"  name="major" value="${sessionScope.sessionUserInfo.teacher.major}" placeholder="教授课程">
					</div>
				</div>
				<div class="control-group" id="title">
					<label class="control-label" for="title">职称</label>
					<div class="controls">
						<input type="text"  name="title" value="${sessionScope.sessionUserInfo.teacher.title}" placeholder="职称">
					</div>
				</div>
				<div class="control-group" id="role">
					<label class="control-label" for="role">导师类别</label>
					<div class="controls">
						<input type="text"  name="role" value="${sessionScope.sessionUserInfo.teacher.role}" placeholder="导师类别">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" onclick="return personalOnclick();"    class="btn btn-large btn-success">保 存</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane <c:if test='${active == "contact"}'>active</c:if>" id="contact_info_tab">
			<form class="form-horizontal"  action="contactInfo" method="post">
				<div class="control-group" id="address">
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
				</div>
				<div class="control-group" id="QQ">
					<label class="control-label" for="qq">QQ</label>
					<div class="controls">
						<input type="text"  name="qq" placeholder="QQ" value="${sessionScope.sessionUserInfo.user.qq}">
					</div>
				</div>
				<div class="control-group" id="msn">
					<label class="control-label" for="msn">MSN</label>
					<div class="controls">
						<input type="text" id="msn" name="msn" placeholder="MSN" value="${sessionScope.sessionUserInfo.user.msn}">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-large btn-success">保 存</button>
					</div>
				</div>
			</form>
		</div>
		
		<!-- Edu Info -->
		<div class="tab-pane  <c:if test='${active == "edu"}'>active</c:if>" id="edu_bg_tab">
			<div id="eduForm" style="display: none;">
				<form class="form-horizontal" action="eduInfo" method="post" id="edu_info_form" name="edu">
					<input type="hidden" name="eduId" >
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
					</div>
					<div class="control-group">
						<label class="control-label" for="educationDesc">详细介绍</label>
						<div class="controls">
							<textarea id="educationDesc" name="educationDesc" rows="6" cols="8" style="width: 500px; height:300px;"></textarea>
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" onclick="return eduOnclick();"  class="btn btn-large btn-success">保 存</button>
							<button type="reset" onclick="closeEduAddForm()" class="btn btn-large">取消</button>
						</div>
					</div>
				</form>
			</div>	
			
			<div id="eduList" style="display: block">
				<c:choose>
					<c:when test="${eduCount>0 }">
						<table  class="table">
							<thead><tr><th>学校</th><th>学院</th><th>学历</th><th>起止时间</th><th>操作</th></tr></thead>
							<tbody>
								<c:forEach items="${eduInfo}" var="eduInfo">
									<tr>
										<td  align="center" >${eduInfo.school}</td>
										<td  align="center">${eduInfo.college}</td>
										<td  align="center">${eduInfo.degree}</td>
										<td  align="center" width="25%">${eduInfo.startTime} - ${eduInfo.endTime}</td>
										<td>
											 <a href='<c:url value="/admin/teacher/eduInfo/destory/${eduInfo.id}"></c:url>'>删除</a> |
											 <a href='javascript:void(0)' onclick="editEdu(${eduInfo.id})">修改</a>
										</td>
									</tr>
									<tr>
										<td></td>
										<td>详细描述：</td>
										<td>
										${eduInfo.educationDesc}
										</td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<b>尚未添加内容！！</b><br><br>
					</c:otherwise>
				</c:choose>
				<button onclick="showEduAddForm()">添加</button>
			</div>
		</div>
		
		<!-- work exp  -->
		<div class="tab-pane <c:if test='${active == "work"}'>active</c:if>" id="work_exp_tab">
			
			<div id="workForm" style="display: none;">
				<form class="form-horizontal" action="workInfo" method="post" id="workExpForm" name="work">
					<input type="hidden" name="workId">
					<div class="control-group" id="company">
						<label class="control-label" for="company">单位</label>
						<div class="controls">
							<input type="text"  name="company" placeholder="单位" >
							<span class="help-inline"><form:errors path="company" /></span>
						</div>
					</div>
					<div class="control-group" id="department">
						<label class="control-label" for="department">部门</label>
						<div class="controls">
							<input type="text"  name="department" placeholder="部门"  >
							<span class="help-inline"><form:errors path="department" /></span>
						</div>
					</div>
					<div class="control-group" id="position">
						<label class="control-label" for="position">职位</label>
						<div class="controls">
							<input type="text"  name="position" placeholder="职位"  >
							<span class="help-inline"><form:errors path="position" /></span>
						</div>
					</div>
					<div class="control-group" id="startTimeName">
						<label class="control-label" for="startTimeName">开始时间</label>
						<div class="controls">
							<input type="text"  name="startTimeName" placeholder="开始时间" >
							<span class="help-inline"><form:errors path="startTime" /></span>
						</div>
					</div>
					<div class="control-group" id="endTimeName">
						<label class="control-label" for="endTimeName">结束时间</label>
						<div class="controls">
							<input type="text"  name="endTimeName" placeholder="结束时间" >
							<span class="help-inline"><form:errors path="endTime" /></span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" onclick="return workOnclick();"  class="btn btn-large btn-success">保 存</button>
							<button type="reset" onclick="closeWorkAddForm()" class="btn btn-large">取消</button>
						</div>
					</div>
				</form>
			</div>	
			
			<div id="workList" style="display: block">
				<c:choose>
					<c:when test="${(workCount >0)}">
						<table  class="table">
							<thead><tr><th>单位</th><th>部门</th><th>职位</th><th>起止时间</th><th>操作</th></tr></thead>
							<tbody>
								<c:forEach items="${workInfo}" var="workInfo">
									<tr>
										<td  align="center">${workInfo.company}</td>
										<td  align="center">${workInfo.department}</td>
										<td  align="center">${workInfo.position}</td>
										<td  align="center"  width="25%">${workInfo.startTime} - ${workInfo.endTime}</td>
										<td>
											<a href='<c:url value="/admin/teacher/workInfo/destory/${workInfo.id}"></c:url>'>删除</a> |
											 <a href='javascript:void(0)' onclick="editWork(${workInfo.id})">修改</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise><b>尚未添加内容！！</b><br><br></c:otherwise>
				</c:choose>
				
				<button onclick="showWorkAddForm()">添加</button>
			</div>
		</div>
		
		<!-- Thesis -->
		
		<div class="tab-pane <c:if test='${active == "thesis"}'>active</c:if>" id="thesis_tab">
			<div id="thesis" style="display: block">
					<c:choose>
						<c:when test="${thesisCount>0}">
							<table class="table">
							<thead><tr><th>论文内容</th><th>操作</th></tr></thead>
							<tbody>
								<c:forEach items="${thesisList}" var="thesis">
									<tr>
										<td width=90% align="left">${thesis.content}</td>
										<td >
											<a href='<c:url value="/admin/teacher/thesis/destory/${thesis.id}"></c:url>'>删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
						</c:when>
						<c:otherwise><span style="font-size: 13px;">尚未添加内容<br><br></span></c:otherwise>
					</c:choose>
				</div>
				<div id="thesisForm" style="display: none;">
					<form action="thesis/new" method="post"  id="thesis_info_form">
						<div class="control-group" id="content">
						<div class="controls">
						内容:
							<textarea style="width: 450px;margin-left: 20px;" id="context" name="content" cols="40" rows="3" ></textarea>
						    <span class="help-inline"></span>
						</div>
					</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit"  onclick="return thesisOnclick();" class="btn btn-large btn-success">保 存</button>
						   		<button class="btn" type="reset" onclick="hiddenThesisAddForm()">取消</button>
							</div>
					     </div>
					</form>
				</div>
				<div id="thesisButton" style="display: block">
					<button onclick="showThesisAddForm();">添加</button>
				</div>
		</div>
		
		<div class="tab-pane <c:if test='${active == "project"}'>active</c:if>" id="project_tab">
			<div id="project" style="display: block">
				<div id="projectForm" style="display: none;">
					<form action="project/new" method="post"  id="project_info_form" class="form-horizontal">
						<div class="control-group" id="projectTitle">
							<label class="control-label" for="projectTitle">项目名称</label>
							<div class="controls">
								<input type="text"  name="projectTitle" placeholder="项目名称"  >
								<span class="help-inline"><form:errors path="projectTitle" /></span>
							</div>
						</div>
						<div class="control-group" id="projectSource">
							<label class="control-label" for="projectSource">项目来源</label>
							<div class="controls">
								<input type="text"  name="projectSource" placeholder="项目来源"  >
								<span class="help-inline"><form:errors path="projectSource" /></span>
							</div>
						</div>
						<div class="control-group" id="projectStartTime">
							<label class="control-label" for="projectStartTime">开始时间</label>
							<div class="controls">
								<input type="text"  name="projectStartTime" placeholder="开始时间" >
								<span class="help-inline"><form:errors path="projectStartTime" /></span>
							</div>
						</div>
						<div class="control-group" id="projectEndTime">
							<label class="control-label" for="projectEndTime">结束时间</label>
							<div class="controls">
								<input type="text"  name="projectEndTime" placeholder="结束时间" >
								<span class="help-inline"><form:errors path="projectEndTime" /></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick ="return projectOnclick();" class="btn btn-large btn-success">保存</button>&nbsp;&nbsp;
								<button class="btn  btn-large" type="reset" onclick="hiddenProjectAddForm()">取消</button>
							</div>
						</div>
					</form>
				</div>
				<div id="projectButton" style="display: block">
					<c:choose>
						<c:when test="${projectCount>0}">
							<table  class="table">
								<thead><tr><th width="30%">项目名称</th><th width="30%">项目来源</th><th width="15%">开始时间</th><th width="15%">结束时间</th><th width="10%">操作</th></tr></thead>
								<tbody>
									<c:forEach items="${projectList}" var="project">
										<tr>
											<td align="left" >${project.title}</td>
											<td align="left" >${project.source}</td>
											<td align="left" >${project.startTime}</td>
											<td align="left" >${project.endTime}</td>
											<td >
												<a href='<c:url value="/admin/teacher/project/destory/${project.id}"></c:url>'>删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise><span style="font-size: 13px;">尚未添加内容<br><br></span></c:otherwise>
					</c:choose>
					<button onclick="showProjectAddForm()">添加</button>
				</div>
			</div>
		</div>
		
		<!-- patent -->
		<div class="tab-pane <c:if test='${active == "patent"}'>active</c:if>" id="patent_tab">
			<div id="patent" style="display: block">
				<div id="patentForm" style="display: none;">
						<form action="patent/new" method="post" id="patent_info_form"  class="form-horizontal">
							<div class="control-group" id="inventer">
								<label class="control-label" for="inventer">发明人</label>
								<div class="controls">
									<input type="text"  name="inventer" placeholder="发明人"  >
									<span class="help-inline"><form:errors path="inventer" /></span>
								</div>
							</div>
							<div class="control-group" id="patentName">
								<label class="control-label" for="patentName">专利名称</label>
								<div class="controls">
									<input type="text"  name="patentName" placeholder="专利名称"  >
									<span class="help-inline"><form:errors path="patentName" /></span>
								</div>
							</div>
							<div class="control-group" id="patentType">
								<label class="control-label" for="patentType">专利类型</label>
								<div class="controls">
									<input type="text"  name="patentType" placeholder="专利类型" >
									<span class="help-inline"><form:errors path="patentType" /></span>
								</div>
							</div>
							<div class="control-group" id="number">
								<label class="control-label" for="number">专利号</label>
								<div class="controls">
									<input type="text"  name="number" placeholder="专利号" >
									<span class="help-inline"><form:errors path="number" /></span>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" onclick="return patentOnclick();" class="btn btn-large btn-success">保存</button>
									<button class="btn  btn-large" type="reset" onclick="hiddenPatentAddForm()">取消</button>
								</div>
							</div>	
						</form>
				</div>
				<div id="patentButton" style="display: block">
					<c:choose>
						<c:when test="${patentCount>0 }">
							<table class="table">
								<thead>
									<tr><th width="23%">发明人</th><th width="23%">专利名称</th><th width="22%">专利类型</th><th width="22%">专利申请号</th><th width="10%">操作</th></tr>
								</thead>
								<tbody>
								<c:forEach items="${patentList}" var="patent">
									<tr>
										<td align="center">${patent.inventer}</td>
										<td align="center">${patent.name}</td>
										<td align="center">${patent.type}</td>
										<td align="center">${patent.number}</td>
										<td >
											<a href='<c:url value="/admin/teacher/patent/destory/${patent.id}"></c:url>'>删除</a>
										</td>
									</tr>
								</c:forEach>
							    </tbody>
							</table>
						</c:when>
						<c:otherwise><span style="font-size: 13px;">尚未添加内容<br><br></span></c:otherwise>
					</c:choose>
					<button onclick="showPatentAddForm()">添加</button>
				</div>
			</div>
		</div>
		
		<!-- honor -->
		<div class="tab-pane <c:if test='${active == "honor"}'>active</c:if>" id="honor_tab">
			<div id="honor" style="display: block">
				<div id="honorForm" style="display: none;">
					<form action="honor/new" method="post"  id="honor_info_Form"  class="form-horizontal">
						<div class="control-group" id="honorName">
							<label class="control-label" for="honorName">奖励或荣誉</label>
							<div class="controls">
								<input type="text"  name="honorName" placeholder="奖励或荣誉" >
								<span class="help-inline"><form:errors path="honorName" /></span>
							</div>
						</div>
						<div class="control-group" id="reason">
							<label class="control-label" for="reason">获奖原因</label>
							<div class="controls">
								<input type="text"  name="reason" placeholder="获奖原因" >
								<span class="help-inline"><form:errors path="reason" /></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit"  onclick="return honorOnclick();" class="btn btn-large btn-success">保存</button>
								<button class="btn  btn-large" type="reset" onclick="hiddenHonorAddForm()">取消</button>
							</div>
						</div>	
					</form>
				</div>
				<div id="honorButton" style="display: block">
					<c:choose>
						<c:when test="${honorCount >0}">
							<table class="table ">
								<thead><tr><th align="center" width="30%">奖励或荣誉</th><th align="center" width="60%">获奖原因</th><th width="10%">操作</th></tr></thead>
								<tbody>
									<c:forEach items="${honorList}" var="honor">
										<tr>
											<td align="center">${honor.name}</td>
											<td align="center">${honor.reason}</td>
											<td>
												<a href='<c:url value="/admin/teacher/honor/destory/${honor.id}"></c:url>'>删除</a></li>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise><span style="font-size: 13px;">尚未添加内容<br><br></span></c:otherwise>
					</c:choose>
					<button onclick="showHonorAddForm()">添加</button>
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
<script type="text/javascript">
$(document).ready(function() {
	var editor = KindEditor.create('textarea[name="educationDesc"]',{
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
	
	prettyPrint();
});

function personalOnclick(){
	return checkEmptyAjax("personal_info_form","personalInfoAJAX");
};
function eduOnclick(){
	return checkEmptyAjax("edu_info_form","eduInfoAJAX");
};

function thesisOnclick(){
	return checkTextAjax("thesis_info_form","thesisInfoAJAX");
};
 function projectOnclick (){
	return checkEmptyAjax("project_info_form","projectInfoAJAX");
};
function patentOnclick (){
	return checkEmptyAjax("patent_info_form","patentInfoAJAX");
};
function honorOnclick(){
	return checkEmptyAjax("honor_info_Form","honorInfoAJAX");
};
$(document).ready(function(){
	$("#context").focus(function(){
		$("#thesisContentMsg").html("");
	});
	$("input").focus(function(){
		$(".help-inline").html("");
	});
});

function editEdu(id){
	//alert(id);
	$("#eduList").css("display","none");
	$("#eduForm").css("display","block");
	$.ajax({
		  type: "post",
		  url: "<c:url value='/admin/teacher/eduInfo/edit/ajax' />",
		  data: "eduId="+id,
		  dataType:"json",
		  success:function(msg){
				document.edu.eduId.value = msg.id;
			  	document.edu.schoolName.value=msg.school;
			  	document.edu.collegeName.value=msg.college;
			  	document.edu.degree.value=msg.degree;
			  	document.edu.startTime.value=msg.startTime;
			  	document.edu.endTime.value=msg.endTime;
			  	document.getElementById("educationDesc").value=msg.educationDesc;
		  }
	});
};

function editWork(id){
	//alert(id);
	$("#workList").css("display","none");
	$("#workForm").css("display","block");
	$.ajax({
		  type: "post",
		  url: "<c:url value='/admin/teacher/workInfo/edit/ajax' />",
		  data: "workId="+id,
		  dataType:"json",
		  success:function(msg){
				document.work.workId.value = msg.id;
			  	document.work.company.value=msg.company;
			  	document.work.department.value=msg.department;
			  	document.work.position.value=msg.position;
			  	document.work.startTimeName.value=msg.startTime;
			  	document.work.endTimeName.value=msg.endTime;
		  }
	});
};

</script>