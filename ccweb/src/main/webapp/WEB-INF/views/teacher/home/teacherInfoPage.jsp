<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>账号信息</h1>

<div class="tabbable">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#personal_info" data-toggle="tab">个人信息</a></li>
		<li><a href="#contact_info" data-toggle="tab">联系方式</a></li>
		<li><a href="#edu_bg" data-toggle="tab">教育背景</a></li>
		<li><a href="#work_exp" data-toggle="tab">工作经历</a></li>
		<li><a href="#security" data-toggle="tab">账号安全</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="personal_info">
			<form:form method="post">
				<input type="text" name="role" value="${sessionScope.userInfo.teacher.role }" placeholder="Role">
				<span class="help-block"><form:errors path="role"></form:errors></span>

				<input type="text" name="college" value="${sessionScope.userInfo.teacher.college }" placeholder="College">
				<span class="help-block"><form:errors path="college"></form:errors></span>

				<input type="text" name="major" value="${sessionScope.userInfo.teacher.major }" placeholder="Major">
				<span class="help-block"><form:errors path="major"></form:errors></span>

				<label style="clear: right;"></label>
				<button type="submit" class="btn">OK</button>
				<label style="clear: right;"></label>
				<a href='<c:url value="/admin/teacher/selfurl"></c:url>' class="btn">个性域名</a>
			</form:form>
		</div>
		<div class="tab-pane" id="contact_info">
			<p>联系方式页面</p>
		</div>
		<div class="tab-pane" id="edu_bg">
			<p>教育背景页面</p>
		</div>
		<div class="tab-pane" id="work_exp">
			<p>工作经历页面</p>
		</div>
		<div class="tab-pane" id="security">
			<p>账号安全页面</p>
		</div>
	</div>
</div>
