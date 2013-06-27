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
.row-fluid.custom .content {
	margin: 20px 40px;
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
					<li <c:if test='${active == "edu"}'>class="active"</c:if>><a href="#edu_bg_tab" data-toggle="tab">教育背景</a></li>
					<li <c:if test='${active == "work"}'>class="active"</c:if>><a href="#work_exp_tab" data-toggle="tab">工作经历</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane <c:if test='${active == "personal"}'>active</c:if>" id="personal_info_tab">
				
						<form id="personal_info_form" action="personalInfo" class="form-horizontal" method="post">
							<div id="message" style="margin-left: 155px;"><h4 style="color: #adcc75">${message}</h4></div>
							<div class="control-group" id="name">
								<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
								<div class="controls">
									<input type="text" name="name" placeholder="姓名" value="${sessionScope.sessionUserInfo.name}"> <span class="help-inline"><form:errors path="name"></form:errors></span>
								</div>
							</div>
							<div class="control-group" id="gender">
								<label class="control-label" for="gender"><i class="icon-star"></i> 性别</label>
								<div class="controls">
									<label class="radio"> 
									<input type="radio" name="gender" id="genderMale" value="男" <c:if test='${sessionScope.sessionUserInfo.gender != "女"}'>checked</c:if>>男
									</label> 
									<label class="radio"> 
									<input type="radio" name="gender" id="genderFemale" value="女" <c:if test='${sessionScope.sessionUserInfo.gender == "女"}'>checked</c:if>>女
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
									<input type="text" name="major" value="${sessionScope.sessionUserInfo.teacher.major}" placeholder="研究方向">
									<span class="help-inline"><form:errors path="major"></form:errors></span>
								</div>
							</div>
							<div class="control-group" id="title">
								<label class="control-label" for="title">职称/职务</label>
								<div class="controls">
									<input type="text" name="title" value="${sessionScope.sessionUserInfo.teacher.title}" placeholder="职称/职务">
									<span class="help-inline"><form:errors path="title"></form:errors></span>
								</div>
							</div>
					
						<div class="control-group">
							<div class="controls">
								<button type="submit" onclick="return personalOnclick();" class="btn  btn-success">保 存</button>
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
							<form action='<c:url value="/admin/eduInfo/destory"></c:url>' method="post" style="display: inline-block;">
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
							<form action='<c:url value="/admin/workInfo/destory"></c:url>' method="post" style="display: inline-block;">
								<input id="workId" type="hidden" name="workId" />
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