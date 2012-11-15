<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>账号信息</h3><i class="icon-star"></i><i>必须填写项</i>
<hr />
<div class="tabbable">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#personal_info_tab" data-toggle="tab">个人信息</a></li>
		<li><a href="#contact_info_tab" data-toggle="tab">联系方式</a></li>
		<li><a href="#edu_bg_tab" data-toggle="tab">教育背景</a></li>
		<li><a href="#work_exp_tab" data-toggle="tab">工作经历</a></li>
		<li><a href="#security_tab" data-toggle="tab">账号安全</a></li>
		<li><a href="#p_url_tab" data-toggle="tab">个性域名</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="personal_info_tab">
			<form action="personalInfo" class="form-horizontal" method="post">
				<div class="control-group">
					<label class="control-label" for="name"><i class="icon-star"></i> 姓名</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="姓名" value="${sessionScope.sessionUserInfo.user.name}" >
						<span class="help-block"><form:errors path="name"></form:errors></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gender"><i class="icon-star"></i> 性别</label>
					<div class="controls">
						<label class="radio"> <input type="radio" name="gender" id="genderMale" value="male" checked>男</label>
						<label class="radio"> <input type="radio" name="gender" id="genderFemale" value="female">女</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="college"><i class="icon-star"></i> 所属高校</label>
					<div class="controls">
						<input type="text" id="college" name="college" placeholder="所属高校" value="${sessionScope.sessionUserInfo.teacher.college}" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source='["复旦大学","同济大学","上海交通大学","上海财金大学","上海外国语大学","上海大学"]'>
					</div>
					<!--  
					<div class="controls">
						<select class="span3" name="university_province">
							<option>上海</option>
						</select>
						<select class="span3" name="university_city">
							<option>上海</option>
						</select>
						<select class="span3" name="university">
							<option>复旦大学</option>
							<option>同济大学</option>
							<option>交通大学</option>
						</select>
					</div>
					-->
				</div>
				<div class="control-group">
					<label class="control-label" for="school"><i class="icon-star"></i> 所属院系</label>
					<div class="controls">
						<input type="text" id="school" name="school" placeholder="所属院系" value="${sessionScope.sessionUserInfo.teacher.school}" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source='["计算机学院","财金学院","女子学院"]'>
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
				<div class="control-group">
					<label class="control-label" for="major">教授课程</label>
					<div class="controls">
						<input type="text" id="major" name="major" value="${sessionScope.sessionUserInfo.teacher.major}" placeholder="教授课程">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="title">职称</label>
					<div class="controls">
						<input type="text" id="title" name="title" value="${sessionScope.sessionUserInfo.teacher.title}" placeholder="职称">
					</div>
				</div>
				<div class="control-group">
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
		<div class="tab-pane" id="contact_info_tab">
			<form class="form-horizontal" action="contactInfo" method="post">
				<div class="control-group">
					<label class="control-label" for="address">地址</label>
					<div class="controls">
						<input type="text" id="address" name="address" placeholder="地址" value="${sessionScope.sessionUserInfo.user.address}">
					</div>
				</div>
				<div class="control-group">
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
				<div class="control-group">
					<label class="control-label" for="qq">QQ</label>
					<div class="controls">
						<input type="text" id="qq" name="qq" placeholder="QQ" value="${sessionScope.sessionUserInfo.user.qq}">
					</div>
				</div>
				<div class="control-group">
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
		<div class="tab-pane" id="edu_bg_tab">
			<p>教育背景页面</p>
		</div>
		<div class="tab-pane" id="work_exp_tab">
			<p>工作经历页面</p>
		</div>
		<div class="tab-pane" id="security_tab">
			<form class="form-horizontal" action="changePsw" method="post">
				<div class="control-group">
					<label class="control-label" for="ori_psw">当前密码</label>
					<div class="controls">
						<input type="text" id="ori_psw" placeholder="请输入您的当前密码">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="new_psw">新密码</label>
					<div class="controls">
						<input type="text" id="new_psw" placeholder="请输入您的新密码">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="confirm_new_psw">确认密码</label>
					<div class="controls">
						<input type="text" id="confirm_new_psw" placeholder="再次输入一遍您的新密码">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-large btn-success">修改密码</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane" id="p_url_tab">
			<form class="form-horizontal">
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