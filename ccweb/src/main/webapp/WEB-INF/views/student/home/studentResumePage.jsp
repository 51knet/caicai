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
				<div class="control-group" id="junior_high_school">
					<label class="control-label" for="junior_high_school"><i class="icon-star"></i> 所属小学</label>
					<div class="controls">
						<input type="text"  name="junior_high_school" placeholder="所属小学" value="${sessionScope.sessionUserInfo.student.junior_high_school}" style="margin: 0 auto;" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
						<span class="help-inline"><form:errors path="junior_high_school" /></span>
					</div>
				</div>
				<div class="control-group" id="senior_high_school">
					<label class="control-label" for="senior_high_school"><i class="icon-star"></i> 所属中学</label>
					<div class="controls">
						<input type="text"  name="senior_high_school" placeholder="所属中学" value="${sessionScope.sessionUserInfo.student.senior_high_school}" style="margin: 0 auto;" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
						<span class="help-inline"><form:errors path="senior_high_school" /></span>
					</div>
				</div>
				<div class="control-group" id="primary_school">
					<label class="control-label" for="primary_school"><i class="icon-star"></i> 所属大学</label>
					<div class="controls">
						<input type="text"  name="primary_school" placeholder="所属大学" value="${sessionScope.sessionUserInfo.student.primary_school}" style="margin: 0 auto;" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
						<span class="help-inline"><form:errors path="primary_school" /></span>
					</div>
				</div>
				<div class="control-group" id="college">
					<label class="control-label" for="college"><i class="icon-star"></i>所属院系</label>
					<div class="controls">
						<input type="text"  name="college" placeholder="所属院系" value="${sessionScope.sessionUserInfo.student.college}" style="margin: 0 auto;" data-provide="typeahead" data-items="8" data-source='[<c:forEach items="${universityList}" var="university">"${university}",</c:forEach>"N/A"]'>
						<span class="help-inline"><form:errors path="college" /></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit"   onclick="return personalOnclick();"  class="btn btn-large btn-success">保 存</button>
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
	</div>
</div>
<script type="text/javascript">
function personalOnclick(){
	return checkEmptyAjax("personal_info_form","personalInfoAJAX");
};
/* function eduOnclick(){
	return checkEmptyAjax("edu_info_form","eduInfoAJAX");
};
function workOnclick(){
	return checkEmptyAjax("workExpForm","workExpInfoAJAX");
}; */
/* function showEduAddForm(){ 
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
	};  */
</script>