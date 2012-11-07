<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/js/myutil.js"></script>
<script type="text/javascript">
	
</script>
<div style="text-align: center;">
	<div style="width: 500px; text-align: left;">
		<table width=500 border="0">
			<tr>
				<td align="left"><span style="color:red;"><b>论文</b></span></td>
				<td align=right><a href="javascript:void(0)" onclick="hiddenThesis()">收起</a> | <a href="javascript:void(0)" onclick="showThesis()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="thesis" style="display: block">
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
									<li><a href='<c:url value="/admin/teacher/achievement/thesis/deleThesis?id=${thesis.id}"></c:url>'>删除</a></li>
								</ul>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
				
			</table>
		<div id="thesisForm" style="display: none;">
				<form action="thesis/addThesis" method="post" onSubmit="return checkThesisForm(this)">
					内容：
					<textarea style="width: 450px;" name="content" cols="40" rows="3" id="thesisContent"></textarea>
					<span class="help-block" style="color: red; font-size: 13px;" id="thesisContentMsg"></span>
					<button type="submit" class="btn">OK</button>
					<button class="btn" type="reset" onclick="hiddenThesisAddForm()">CANCEL</button>
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
				<td align="left"><span style="color:red;"><b>项目</b></span></td>
				<td align="right"><a href="javascript:void(0)" onclick="hiddenProject()">收起</a> | <a href="javascript:void(0)" onclick="showProject()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="project" style="display: block">
			<div id="projectForm" style="display: none;">
				<form action="project/addProject" method="post" onSubmit="return checkProjectForm(this)">
				<table  class="table">
					<thead><tr><th>项目名称</th><th>项目来源</th><th>项目起止时间</th></tr></thead>
					<tbody>
						<tr>
							<td align="center"><input type="text" name="title" /> <span class="help-block" style="color: red; font-size: 13px;" id="titleMsg"></span></td>
							<td align="center"><input type="text" name="source" /> <span class="help-block" style="color: red; font-size: 13px;" id="sourceMsg"></span></td>
							<td align="center"><input type="text" name="date" /><span id="dateMsg" style="color: red; font-size: 13px;">2000.01.01-2001.01.01</span></td>
						</tr>
					</tbody>
				</table>
					<button type="submit" class="btn">OK</button>
					<button class="btn" type="reset" onclick="hiddenProjectAddForm()">CANCEL</button>
				</form>
			</div>
			<div id="projectButton" style="display: block">
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
										<li><a href='<c:url value="/admin/teacher/achievement/project/deleProject?id=${project.id}"></c:url>'>删除</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
				<button onclick="showProjectAddForm()">添加</button>
			</div>
		</div>
	</div>

	<br>
	<div style="width: 500px; text-align: left;">
		<table width="500" border="0">
			<tr>
				<td align="left"><span style="color:red;"><b>专利</b></span></td>
				<td align="right"><a href="javascript:void(0)" onclick="hiddenPatent()">收起</a> | <a href="javascript:void(0)" onclick="showPatent()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="patent" style="display: block">
			<div id="patentForm" style="display: none;">
				<form action="patent/addPatent" method="post" onSubmit="return checkPatentForm(this)">
					<table class="table">
					<thead>
						<tr><th>发明人</th><th>专利名称</th><th>专利类型</th><th>专利申请号</th></tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><input type="text" style="width:150px;" name="inventer" /> <span class="help-block" style="color: red; font-size: 13px;" id="inventerMsg"></span></td>
							<td align="center"><input type="text" style="width:150px;" name="name" /> <span class="help-block" style="color: red; font-size: 13px;" id="nameMsg"></span></td>
							<td align="center"><input type="text" style="width:150px;" name="type" /> <span class="help-block" style="color: red; font-size: 13px;" id="typeMsg"></span></td>
							<td align="center"><input type="text" style="width:150px;" name="number" /> <span class="help-block" style="color: red; font-size: 13px;" id="numberMsg"></span></td>
						</tr>
					</tbody>
					</table>
					<button type="submit" class="btn">OK</button>
					<button class="btn" type="reset" onclick="hiddenPatentAddForm()">CANCEL</button>
				</form>
			</div>
			<div id="patentButton" style="display: block">
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
										<li><a href='<c:url value="/admin/teacher/achievement/patent/delePatent?id=${patent.id}"></c:url>'>删除</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<button onclick="showPatentAddForm()">添加</button>
			</div>
		</div>
	</div>

	<br>
	<div style="width: 500px; text-align: left;">
		<table width="500" border="0">
			<tr>
				<td align="left"><span style="color:red;"><b>荣誉和奖项</b></span></td>
				<td align="right"><a href="javascript:void(0)" onclick="hiddenHonor()">收起</a> | <a href="javascript:void(0)" onclick="showHonor()">展开</a></td>
			</tr>
		</table>
		<hr>
		<div id="honor" style="display: block">
			<div id="honorForm" style="display: none;">
				<form action="honor/addHonor" method="post" onSubmit="return checkHonorForm(this)">
					<table class="table ">
					<thead><tr><th align="center">奖励或荣誉</th><th align="center">获奖原因</th></tr></thead>
					<tbody>
						<tr>
							<td align="center"><input type="text" name="name" /> <span class="help-block" style="color: red; font-size: 13px;" id="honorNameMsg"></span></td>
							<td align="center"><input type="text" name="reason" /> <span class="help-block" style="color: red; font-size: 13px;" id="reasonMsg"></span></td>
						</tr>
					</table>
					</tbody>
					<button type="submit" class="btn">OK</button>
					<button class="btn" type="reset" onclick="hiddenHonorAddForm()">CANCEL</button>
				</form>
			</div>
			<div id="honorButton" style="display: block">
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
										<li><a href='<c:url value="/admin/teacher/achievement/honor/deleHonor?id=${honor.id}"></c:url>'>删除</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<button onclick="showHonorAddForm()">添加</button>
			</div>
		</div>
	</div>

</div>