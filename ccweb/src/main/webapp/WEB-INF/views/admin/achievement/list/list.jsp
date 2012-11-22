<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/js/myutil.js"></script>
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
	var $form = $('#thesis_info_form');
	$form.bind('submit', function(e) {
		// Ajax validation
		var $textareas = $form.find('textarea');
		var data = collectFormData($textareas);
		$.post('thesisInfoAJAX',data, function(response) {
			//$form.find('.modal-body').removeClass('error');
			//$form.find('.help-block').empty();
			//$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-block').html(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;
	});
});


$(document).ready(function() {
	var $form = $('#project_info_form');
	$form.bind('submit', function(e) {
		// Ajax validation
		var $inputs = $form.find('input');
		var data = collectFormData($inputs);
		$.post('projectInfoAJAX',data, function(response) {
			//$form.find('.modal-body').removeClass('error');
			//$form.find('.help-block').empty();
			//$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-block').html(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;
	});
});


$(document).ready(function() {
	var $form = $('#patent_info_form');
	$form.bind('submit', function(e) {
		// Ajax validation
		var $inputs = $form.find('input');
		var data = collectFormData($inputs);
		$.post('patentInfoAJAX',data, function(response) {
			//$form.find('.modal-body').removeClass('error');
			//$form.find('.help-block').empty();
			//$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-block').html(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;
	});
});
$(document).ready(function() {
	var $form = $('#honor_info_Form');
	$form.bind('submit', function(e) {
		// Ajax validation
		var $inputs = $form.find('input');
		var data = collectFormData($inputs);
		$.post('honorInfoAJAX',data, function(response) {
			//$form.find('.modal-body').removeClass('error');
			//$form.find('.help-block').empty();
			//$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-block').html(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;
	});
});
	
</script>
<div style="text-align: center;">
	<div style="width: 500px; text-align: left;">
		<table width=500 border="0">
			<tr>
				<td align="left"><span style="color:red; font-size: 15px;"><b>论文</b></span></td>
				<td align=right><a href="javascript:void(0)" onclick="hiddenThesis()">收起</a> | <a href="javascript:void(0)" onclick="showThesis()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="thesis" style="display: block">
			<c:choose>
				<c:when test="${thesisCount>0}">
					<table class="table">
					<thead><tr><th>论文内容</th><th>详细操作</th></tr></thead>
					<tbody>
						<c:forEach items="${thesis}" var="thesis">
							<tr>
								<td width=500 align="left">${thesis.content}</td>
								<td>
									 <div class="btn-group"> 
										<button class="btn">操作</button>  
										<button class="btn dropdown-toggle" data-toggle="dropdown">   
										<span class="caret"></span> </button>
										<ul class="dropdown-menu">
											<li><a href='#'>修改</a></li>
											<li><a href='<c:url value="/admin/teacher/achievement/thesis/destory/${thesis.id}"></c:url>'>删除</a></li>
										</ul>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					</table>
				</c:when>
				<c:otherwise><b style="font-size: 13px;">尚未添加内容！！<br><br></b></c:otherwise>
			</c:choose>
		<div id="thesisForm" style="display: none;">
				<form action="thesis/new" method="post"  id="thesis_info_form">
					内容：
					<div class="modal-body" id="content">
						<textarea style="width: 450px;" name="content" cols="40" rows="3" ></textarea>
					    <span class="help-block" style="color: red; font-size: 13px;" id="thesisContentMsg"></span>
			        </div>
			        <div class="modal-footer">
					<button type="submit" class="btn btn-primary">保存</button>
					<button class="btn" type="reset" onclick="hiddenThesisAddForm()">取消</button>
					</div>
				</form>
			</div>
			<div id="thesisButton" style="display: block">
				<button onclick="showThesisAddForm()">添加</button>
			</div>
		</div>
	</div>
	<br>
	<div style="width: 500px; text-align: left;">
		<table width="500" border="0">
			<tr>
				<td align="left"><span style="color:red; font-size: 15px;"><b>项目</b></span></td>
				<td align="right"><a href="javascript:void(0)" onclick="hiddenProject()">收起</a> | <a href="javascript:void(0)" onclick="showProject()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="project" style="display: block">
			<div id="projectForm" style="display: none;">
				<form action="project/new" method="post" onSubmit="return checkProjectForm(this)" id="project_info_form">
				<table  class="table">
					<thead><tr><th>项目名称</th><th>项目来源</th><th>项目起止时间</th></tr></thead>
					<tbody>
						<tr>
							<td align="center" class="modal-body" id="title"><input type="text" name="title" /> <span class="help-block" style="color: red; font-size: 13px;" id="titleMsg"></span></td>
							<td align="center" class="modal-body" id="source"><input type="text" name="source" /> <span class="help-block" style="color: red; font-size: 13px;" id="sourceMsg"></span></td>
							<td align="center" class="modal-body" id="date"><input type="text" name="date" /><span id="dateMsg" class="help-block" style="color: red; font-size: 13px;">2000.01.01-2001.01.01</span></td>
						</tr>
						<tr><td align="right" colspan="3"><button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
						<button class="btn" type="reset" onclick="hiddenProjectAddForm()">取消</button></td></tr>
					</tbody>
					
				</table>
					
				</form>
			</div>
			<div id="projectButton" style="display: block">
			<c:choose>
				<c:when test="${projectCount>0}">
					<table  class="table">
						<thead><tr><th>项目名称</th><th>项目来源</th><th>项目起止时间</th><th>更多操作</th></tr></thead>
						<tbody>
							<c:forEach items="${project}" var="project">
								<tr>
									<td  align="center">${project.title}</td>
									<td  align="center">${project.source}</td>
									<td  align="center">${project.date}</td>
									<td>
										 <div class="btn-group"> 
											<button class="btn">操作</button>  
											<button class="btn dropdown-toggle" data-toggle="dropdown">   
											<span class="caret"></span> </button>
											<ul class="dropdown-menu">
												<li><a href='#'>修改</a></li>
												<li><a href='<c:url value="/admin/teacher/achievement/project/destory/${project.id}"></c:url>'>删除</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise><b style="font-size: 13px;">尚未添加内容！！<br><br></b></c:otherwise>
			</c:choose>
			<button onclick="showProjectAddForm()">添加</button>
			</div>
		</div>
	</div>

	<br>
	<div style="width: 500px; text-align: left;">
		<table width="500" border="0">
			<tr>
				<td align="left"><span style="color:red; font-size: 15px;"><b>专利</b></span></td>
				<td align="right"><a href="javascript:void(0)" onclick="hiddenPatent()">收起</a> | <a href="javascript:void(0)" onclick="showPatent()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="patent" style="display: block">
			<div id="patentForm" style="display: none;">
				<form action="patent/new" method="post" id="patent_info_form" >
					<table class="table">
					<thead>
						<tr><th>发明人</th><th>专利名称</th><th>专利类型</th><th>专利申请号</th></tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"  class="modal-body" id="inventer"><input type="text" style="width:150px;" name="inventer" /> <span class="help-block" style="color: red; font-size: 13px;" id="inventerMsg"></span></td>
							<td align="center" class="modal-body" id="name"><input type="text" style="width:150px;" name="name" /> <span class="help-block" style="color: red; font-size: 13px;" id="nameMsg"></span></td>
							<td align="center" class="modal-body" id="type"><input type="text" style="width:150px;" name="type" /> <span class="help-block" style="color: red; font-size: 13px;" id="typeMsg"></span></td>
							<td align="center" class="modal-body" id="number"><input type="text" style="width:150px;" name="number" /> <span class="help-block" style="color: red; font-size: 13px;" id="numberMsg"></span></td>
						</tr>
						<tr><td align="right" colspan="4">
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">保存</button>
							<button class="btn" type="reset" onclick="hiddenPatentAddForm()">取消</button>
						</div>
						</td></tr>
					</tbody>
					</table>
					
				</form>
			</div>
			<div id="patentButton" style="display: block">
				<c:choose>
					<c:when test="${patentCount>0 }">
						<table class="table">
							<thead>
								<tr><th>发明人</th><th>专利名称</th><th>专利类型</th><th>专利申请号</th><th>操作</th></tr>
							</thead>
							<tbody>
							<c:forEach items="${patent}" var="patent">
								<tr>
									<td align="center">${patent.inventer}</td>
									<td align="center">${patent.name}</td>
									<td align="center">${patent.type}</td>
									<td align="center">${patent.number}</td>
									<td>
										 <div class="btn-group"> 
											<button class="btn">操作</button>  
											<button class="btn dropdown-toggle" data-toggle="dropdown">   
											<span class="caret"></span> </button>
											<ul class="dropdown-menu">
												<li><a href='#'>修改</a></li>
												<li><a href='<c:url value="/admin/teacher/achievement/patent/destory/${patent.id}"></c:url>'>删除</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</c:forEach>
						    </tbody>
					</table>
				</c:when>
				<c:otherwise><b style="font-size: 13px;">尚未添加内容！！<br><br></b></c:otherwise>
			</c:choose>
				<button onclick="showPatentAddForm()">添加</button>
			</div>
		</div>
	</div>

	<br>
	<div style="width: 500px; text-align: left;">
		<table width="500" border="0">
			<tr>
				<td align="left"><span style="color:red; font-size: 15px;"><b>荣誉和奖项</b></span></td>
				<td align="right"><a href="javascript:void(0)" onclick="hiddenHonor()">收起</a> | <a href="javascript:void(0)" onclick="showHonor()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="honor" style="display: block">
			<div id="honorForm" style="display: none;">
				<form action="honor/new" method="post"  id="honor_info_Form">
					<table class="table ">
					<thead><tr><th align="center">奖励或荣誉</th><th align="center">获奖原因</th></tr></thead>
					<tbody>
						<tr>
							<td align="center" class="modal-body" id="honorName"><input type="text" name="honorName" /> <span class="help-block" style="color: red; font-size: 13px;" id="honorNameMsg"></span></td>
							<td align="center" class="modal-body" id="reason"><input type="text" name="reason" /> <span class="help-block" style="color: red; font-size: 13px;" id="reasonMsg"></span></td>
						</tr>
						<tr><td align="right" colspan="2">
							<button type="submit" class="btn btn-primary">保存</button>
							<button class="btn" type="reset" onclick="hiddenHonorAddForm()">取消</button>
						</td></tr>
						</tbody>
					</table>
					
					
				</form>
			</div>
			<div id="honorButton" style="display: block">
				<c:choose>
					<c:when test="${honorCount >0}">
						<table class="table ">
							<thead><tr><th align="center">奖励或荣誉</th><th align="center">获奖原因</th><th>操作</th></tr></thead>
							<tbody>
								<c:forEach items="${honor}" var="honor">
									<tr>
										<td align="center">${honor.name}</td>
										<td align="center">${honor.reason}</td>
										<td>
											 <div class="btn-group"> 
												<button class="btn">操作</button>  
												<button class="btn dropdown-toggle" data-toggle="dropdown">   
												<span class="caret"></span> </button>
												<ul class="dropdown-menu">
													<li><a href='#'>修改</a></li>
													<li><a href='<c:url value="/admin/teacher/achievement/honor/destory/${honor.id}"></c:url>'>删除</a></li>
												</ul>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise><b style="font-size: 13px;">尚未添加内容！！<br><br></b></c:otherwise>
				</c:choose>
		
				<button onclick="showHonorAddForm()">添加</button>
			</div>
		</div>
	</div>

</div>