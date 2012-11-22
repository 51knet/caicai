<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>账号信息</h3><i class="icon-star"></i><i>必须填写项</i>
<hr />
<div class="tabbable">
	<ul class="nav nav-tabs">
		<li <c:if test='${active == "personal"}'>class="active"</c:if>><a href="#personal_info_tab" data-toggle="tab">个人信息</a></li>
		<li <c:if test='${active == "contact"}'>class="active"</c:if>><a href="#contact_info_tab" data-toggle="tab">联系方式</a></li>
		<li <c:if test='${active == "edu"}'>class="active"</c:if>><a href="#edu_bg_tab" data-toggle="tab">教育背景</a></li>
		<li <c:if test='${active == "work"}'>class="active"</c:if>><a href="#work_exp_tab" data-toggle="tab">工作经历</a></li>
		<li <c:if test='${active == "psw"}'>class="active"</c:if>><a href="#security_tab" data-toggle="tab">账号安全</a></li>
		<li <c:if test='${active == "url"}'>class="active"</c:if>><a href="#p_url_tab" data-toggle="tab">个性域名</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane <c:if test='${active == "personal"}'>active</c:if>" id="personal_info_tab">
			<form id="personal_info_form" action="personalInfo" class="form-horizontal" method="post">
				<div class="control-group" id="name">
					<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="姓名" value="${sessionScope.sessionUserInfo.name}" >
						<span class="help-inline"><form:errors path="name"></form:errors></span>
					</div>
				</div>
				<div class="control-group" id="gender">
					<label class="control-label" for="gender"><i class="icon-star"></i> 性别</label>
					<div class="controls">
						<label class="radio"> <input type="radio" name="gender" id="genderMale" value="男" checked>男</label>
						<label class="radio"> <input type="radio" name="gender" id="genderFemale" value="女">女</label>
					</div>
				</div>
				<div class="control-group" id="college">
					<label class="control-label" for="college"><i class="icon-star"></i> 所属高校</label>
					<div class="controls">
						<input type="text" id="college" name="college" placeholder="所属高校" value="${sessionScope.sessionUserInfo.teacher.college}" style="margin: 0 auto;" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
						<span class="help-inline"><form:errors path="college" /></span>
					</div>
				</div>
				<div class="control-group" id="school">
					<label class="control-label" for="school"><i class="icon-star"></i> 所属院系</label>
					<div class="controls">
						<input type="text" id="school" name="school" placeholder="所属院系" value="${sessionScope.sessionUserInfo.teacher.school}" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source='["计算机学院","财金学院","女子学院"]'>
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
						<input type="text" id="major" name="major" value="${sessionScope.sessionUserInfo.teacher.major}" placeholder="教授课程">
					</div>
				</div>
				<div class="control-group" id="title">
					<label class="control-label" for="title">职称</label>
					<div class="controls">
						<input type="text" id="title" name="title" value="${sessionScope.sessionUserInfo.teacher.title}" placeholder="职称">
					</div>
				</div>
				<div class="control-group" id="role">
					<label class="control-label" for="role">导师类别</label>
					<div class="controls">
						<input type="text" id="role" name="role" value="${sessionScope.sessionUserInfo.teacher.role}" placeholder="导师类别">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-large btn-success">保 存</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane <c:if test='${active == "contact"}'>active</c:if>" id="contact_info_tab">
			<form class="form-horizontal" id="relation_info_form" action="contactInfo" method="post">
				<div class="control-group" id="address">
					<label class="control-label" for="address">地址</label>
					<div class="controls">
						<input type="text" id="address" name="address" placeholder="地址" value="${sessionScope.sessionUserInfo.user.address}">
					    <span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group" id="cellphone">
					<label class="control-label" for="cellphone">手机</label>
					<div class="controls">
						<input type="text" id="cellphone" name="cellphone" placeholder="手机号码" value="${sessionScope.sessionUserInfo.user.cell_phone}">
						<span class="help-inline"></span>
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
						<input type="text"  name="QQ" placeholder="QQ" value="${sessionScope.sessionUserInfo.user.qq}">
						<span class="help-inline"></span>
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
				<form class="form-horizontal" action="eduInfo" method="post" id="edu_info_form">
					<div class="control-group" id="schoolName">
						<label class="control-label" for="schoolName">学校</label>
						<div class="controls">
							<input type="text"  name="schoolName" placeholder="学校名称" >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="collegeName">
						<label class="control-label" for="collegeName">学院</label>
						<div class="controls">
							<input type="text"   name="collegeName" placeholder="学院名称"  >
						</div>
					</div>
					<div class="control-group" id="school">
						<label class="control-label" for="school">学校</label>
						<div class="controls">
							<input type="text"   name="school" placeholder="学校名称" >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="college">
						<label class="control-label" for="college">学院</label>
						<div class="controls">
							<input type="text"   name="college" placeholder="学院名称"  >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="degree">
						<label class="control-label" for="degree">学历</label>
						<div class="controls">
							<input type="text"   name="degree" placeholder="学历"  >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="startTime">
						<label class="control-label" for="startTime">开始时间</label>
						<div class="controls">
							<input type="text"   name="startTime" placeholder="开始时间" >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group" id="endTime">
						<label class="control-label" for="endTime">结束时间</label>
						<div class="controls">
							<input type="text"  name="endTime" placeholder="结束时间" >
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit"  class="btn btn-large btn-success">保 存</button>
							<button type="reset" onclick="closeEduAddForm()" class="btn btn-large">取消</button>
						</div>
					</div>
				</form>
			</div>	
			
			<div id="eduList" style="display: block">
			
			<c:choose>
				<c:when test="${eduCount>0 }">
					<table  class="table">
						<thead><tr><th>学校名称</th><th>学院名称</th><th>学历</th><th>起止时间</th><th>更多操作</th></tr></thead>
						<tbody>
							<c:forEach items="${eduInfo}" var="eduInfo">
								<tr>
									<td  align="center">${eduInfo.school}</td>
									<td  align="center">${eduInfo.college}</td>
									<td  align="center">${eduInfo.degree}</td>
									<td  align="center">${eduInfo.startTime} - ${eduInfo.endTime}</td>
									<td>
										 <div class="btn-group"> 
											<button class="btn">操作</button>  
											<button class="btn dropdown-toggle" data-toggle="dropdown">   
											<span class="caret"></span> </button>
											<ul class="dropdown-menu">
												<li><a href='#'>修改</a></li>
												<li><a href='<c:url value="/admin/teacher/eduInfo/destory/${eduInfo.id}"></c:url>'>删除</a></li>
											</ul> 
										</div>
									</td>
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
				<form class="form-horizontal" action="workInfo" method="post" id="workExpForm">
					<div class="control-group" id="company">
						<label class="control-label" for="company">公司名称</label>
						<div class="controls">
							<input type="text" id="company" name="company" placeholder="公司名称" >
							<span class="help-inline"><form:errors path="company" /></span>
						</div>
					</div>
					<div class="control-group" id="department">
						<label class="control-label" for="department">部门名称</label>
						<div class="controls">
							<input type="text" id="department" name="department" placeholder="部门名称"  >
							<span class="help-inline"><form:errors path="department" /></span>
						</div>
					</div>
					<div class="control-group" id="position">
						<label class="control-label" for="position">职位</label>
						<div class="controls">
							<input type="text" id="position" name="position" placeholder="职位"  >
							<span class="help-inline"><form:errors path="position" /></span>
						</div>
					</div>
					<div class="control-group" id="startTime">
						<label class="control-label" for="startTime">开始时间</label>
						<div class="controls">
							<input type="text" id="startTime" name="startTime" placeholder="开始时间" >
							<span class="help-inline"><form:errors path="startTime" /></span>
						</div>
					</div>
					<div class="control-group" id="endTime">
						<label class="control-label" for="endTime">结束时间</label>
						<div class="controls">
							<input type="text" id="endTime" name="endTime" placeholder="结束时间" >
							<span class="help-inline"><form:errors path="endTime" /></span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit"  class="btn btn-large btn-success">保 存</button>
							<button type="reset" onclick="closeWorkAddForm()" class="btn btn-large">取消</button>
						</div>
					</div>
				</form>
			</div>	
			
			<div id="workList" style="display: block">
		
				<c:choose>
					<c:when test="${(workCount >0)}">
						<table  class="table">
							<thead><tr><th>公司名称</th><th>部门名称</th><th>职位</th><th>起止时间</th><th>更多操作</th></tr></thead>
							<tbody>
								<c:forEach items="${workInfo}" var="workInfo">
									<tr>
										<td  align="center">${workInfo.company}</td>
										<td  align="center">${workInfo.department}</td>
										<td  align="center">${workInfo.position}</td>
										<td  align="center">${workInfo.startTime} - ${workInfo.endTime}</td>
										<td>
											 <div class="btn-group"> 
												<button class="btn">操作</button>  
												<button class="btn dropdown-toggle" data-toggle="dropdown">   
												<span class="caret"></span> </button>
												<ul class="dropdown-menu">
													<li><a href='#'>修改</a></li>
													<li><a href='<c:url value="/admin/teacher/workInfo/destory/${workInfo.id}"></c:url>'>删除</a></li>
												</ul> 
											</div>
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
		
		<div class="tab-pane <c:if test='${active == "psw"}'>active</c:if>" id="security_tab">
			<form class="form-horizontal" action="changePsw" method="post">
				<div class="control-group">
					<label class="control-label" for="ori_psw">当前密码</label>
					<div class="controls">
						<input type="password" id="ori_psw" name="ori_psw" placeholder="请输入您的当前密码">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="new_psw">新密码</label>
					<div class="controls">
						<input type="password" id="new_psw" name="new_psw" placeholder="请输入您的新密码">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="confirm_new_psw">确认密码</label>
					<div class="controls">
						<input type="password" id="confirm_new_psw" name="confirm_new_psw" placeholder="再次输入一遍您的新密码">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-large btn-success">修改密码</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane <c:if test='${active == "url"}'>active</c:if>" id="p_url_tab">
			<form class="form-horizontal" action="selfurl" method="post">
				<div class="control-group">
					<label class="control-label" for="p_url">个性域名</label>
					<div class="controls">
						<input type="text" id="p_url" value="${sessionScope.userInfo.user.self_url }" placeholder="请输入您的个性域名">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-large btn-success">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	function collectFormData(fields) {
		var data = {};
		for (var i = 0; i < fields.length; i++) {
			var $item = $(fields[i]);
			data[$item.attr('name')] = $item.val();
		}
		return data;
	}

	$(document).ready(function() {
		var $form = $('#personal_info_form');
		$form.bind('submit', function(e) {
			// Ajax validation
			var $inputs = $form.find('input');
			var data = collectFormData($inputs);
			$.post('personalInfoAJAX', data, function(response) {
				$form.find('.control-group').removeClass('error');
				$form.find('.help-inline').empty();
				$form.find('.alert').remove();
				if (response.status == 'FAIL') {
					for (var i = 0; i < response.errorMessageList.length; i++) {
						var item = response.errorMessageList[i];
						var $controlGroup = $('#' + item.fieldName);
						$controlGroup.addClass('error');
						$controlGroup.find('.help-inline').html(item.message);
					}
				} else {
					$form.unbind('submit');
					$form.submit();
				}
			}, 'json');
	
			e.preventDefault();
			return false;
		});
	
	
	var $relation_form = $('#relation_info_form');
	$relation_form.bind('submit', function(e) {
		// Ajax validation
		var $inputs = $relation_form.find('input');
		var data = collectFormData($inputs);
		$.post('contactInfoAJAX', data, function(response) {
			$relation_form.find('.control-group').removeClass('error');
			$relation_form.find('.help-inline').empty();
			$relation_form.find('.alert').remove();

			if (response.status == 'FAIL') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html(item.message);
				}
			} else {
				$relation_form.unbind('submit');
				$relation_form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;
	});
	
	
	
	
	var $edu_form=$('#edu_info_form');
	    $edu_form.bind('submit',function(e){
		var $inputs=$edu_form.find('input');
		var data=collectFormData($inputs);
		$.post('eduInfoAJAX',data,function(response){
			$edu_form.find('.control-group').removeClass('error');
			$edu_form.find('.help_inline').empty();
			//$edu_form.find('.alert').remove();
			
			if(response.status == 'FAIL'){
				for(var i = 0; i < response.errorMessageList.length; i++){
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html(item.message);
				}
			}else{
				$edu_form.unbind('submit');
				$edu_form.submit();
			}
		},'json');
		e.preventDefault();
		return false;
	});
		var $relation_form = $('#relation_info_form');
		$relation_form.bind('submit', function(e) {
			// Ajax validation
			var $inputs = $relation_form.find('input');
			var data = collectFormData($inputs);
			$.post('contactInfoAJAX', data, function(response) {
				$relation_form.find('.control-group').removeClass('error');
				$relation_form.find('.help-inline').empty();
				$relation_form.find('.alert').remove();
	
				if (response.status == 'FAIL') {
					for (var i = 0; i < response.errorMessageList.length; i++) {
						var item = response.errorMessageList[i];
						var $controlGroup = $('#' + item.fieldName);
						$controlGroup.addClass('error');
						$controlGroup.find('.help-inline').html(item.message);
					}
				} else {
					$relation_form.unbind('submit');
					$relation_form.submit();
				}
			}, 'json');
	
			e.preventDefault();
			return false;
		});
		
		var $relation_form = $('#workExpForm');
		$relation_form.bind('submit', function(e) {
			// Ajax validation
			var $inputs = $relation_form.find('input');
			var data = collectFormData($inputs);
			$.post('workExpInfoAJAX', data, function(response) {
				$relation_form.find('.control-group').removeClass('error');
				$relation_form.find('.help-inline').empty();
				$relation_form.find('.alert').remove();
	
				if (response.status == 'FAIL') {
					for (var i = 0; i < response.errorMessageList.length; i++) {
						var item = response.errorMessageList[i];
						var $controlGroup = $('#' + item.fieldName);
						$controlGroup.addClass('error');
						$controlGroup.find('.help-inline').html(item.message);
					}
				} else {
					$relation_form.unbind('submit');
					$relation_form.submit();
				}
			}, 'json');
	
			e.preventDefault();
			return false;
		});
	});

function showEduAddForm(){
	var eduList = document.getElementById("eduList");
	eduList.style.display="none";
	var eduForm = document.getElementById("eduForm");
	eduForm.style.display="block";
};

function closeEduAddForm(){
	var eduList = document.getElementById("eduList");
	eduList.style.display="block";
	var eduForm = document.getElementById("eduForm");
	eduForm.style.display="none";
};

function showWorkAddForm(){
	//alert("111");
	var workList = document.getElementById("workList");
	workList.style.display="none";
	var workForm = document.getElementById("workForm");
	workForm.style.display="block";
};

function closeWorkAddForm(){
	var workList = document.getElementById("workList");
	workList.style.display="block";
	var workForm = document.getElementById("workForm");
	workForm.style.display="none";
};

</script>
		
