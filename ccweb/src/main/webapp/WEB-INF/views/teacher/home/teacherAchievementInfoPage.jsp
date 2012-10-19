<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/js/myutil.js"></script>
<script type="text/javascript">

	 function checkField(fieldObj, msgObj, re, nullMsg, errorMsg) {
		//alert("test");
		msgObj.innerHTML = "";
		var v = fieldObj.value.replace(/(^\s+)|(\s+$)/g, "");
		var flag = true;
		if (v.length == 0) {
			msgObj.innerHTML = nullMsg;
			flag = false;
		} else {
			if (!(re.test(v))) {
				msgObj.innerHTML = errorMsg;
				flag = false;
			}
		}
		return flag;
	 }

	 function hiddenThesis(){
	 	var thesis = document.getElementById("thesis");
	 	thesis.style.display="none";
	 }
	 
	 function showThesis(){
	 	var thesis = document.getElementById("thesis");
	 	thesis.style.display="block";
	 }
	 
	 function showThesisAddForm(){
	 	var thesis = document.getElementById("thesisForm");
	 	var thesisButton = document.getElementById("thesisButton");
	 	thesis.style.display="block";
	 	thesisButton.style.display="none";
	 }
	 
	 function hiddenThesisAddForm(){
	 	var thesisForm = document.getElementById("thesisForm");
	 	var thesisButton = document.getElementById("thesisButton");
	 	thesisForm.style.display="none";
	 	thesisButton.style.display="block";
	 }
	 
	 
	  function hiddenProject(){
	 	var project = document.getElementById("project");
	 	project.style.display="none";
	 }
	 
	 function showProject(){
	 	var project = document.getElementById("project");
	 	project.style.display="block";
	 }
	 
	 function showProjectAddForm(){
	 	var project = document.getElementById("projectForm");
	 	var projectButton = document.getElementById("projectButton");
	 	project.style.display="block";
	 	projectButton.style.display="none";
	 }
	 
	 function hiddenProjectAddForm(){
	 	var projectForm = document.getElementById("projectForm");
	 	var projectButton = document.getElementById("projectButton");
	 	projectForm.style.display="none";
	 	projectButton.style.display="block";
	 }
	 
 	 function hiddenPatent(){
	 	var patent = document.getElementById("patent");
	 	patent.style.display="none";
	 }
	 
	 function showPatent(){
	 	var patent = document.getElementById("patent");
	 	patent.style.display="block";
	 }
	 
	 function showPatentAddForm(){
	 	var patent = document.getElementById("patentForm");
	 	var patentButton = document.getElementById("patentButton");
	 	patent.style.display="block";
	 	patentButton.style.display="none";
	 }
	 
	 function hiddenPatentAddForm(){
	 	var patentForm = document.getElementById("patentForm");
	 	var patentButton = document.getElementById("patentButton");
	 	patentForm.style.display="none";
	 	patentButton.style.display="block";
	 }
	 
	 function hiddenHonor(){
		 var honor = document.getElementById("honor");
		 honor.style.display="none";
	 }
		 
	 function showHonor(){
		 var honor = document.getElementById("honor");
		 honor.style.display="block";
	 }
		 
	 function showHonorAddForm(){
		 	var honor = document.getElementById("honorForm");
		 	var honorButton = document.getElementById("honorButton");
		 	honor.style.display="block";
		 	honorButton.style.display="none";
	 }
	 
	 function hiddenHonorAddForm(){
		 var honorForm = document.getElementById("honorForm");
		 var honorButton = document.getElementById("honorButton");
		 honorForm.style.display="none";
		 honorButton.style.display="block";
	 }
 
 	function checkThesisForm(obj){
 		var content = obj.content;
 		var thesisContentMsg = document.getElementById("thesisContentMsg");
 		//alert(content.innerHTML);
 		var thesisContentFlag = checkField(content,thesisContentMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		return thesisContentFlag;
 	}
 	
 	function checkProjectForm(obj){
 		var title = obj.title;
 		var titleMsg = document.getElementById("titleMsg");
 		var source = obj.source;
 		var sourceMsg = document.getElementById("sourceMsg");
 		var date = obj.date;
 		var dateMsg = document.getElementById("dateMsg");
 		
 		var titleFlag = checkField(title,titleMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		var sourceFlag = checkField(source,sourceMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		var dateFlag = checkField(date,dateMsg,/(\d+\.\-)/,"不能为空！","只能是数字！");
 		return titleFlag && sourceFlag && dateFlag;
 	}
 	
 	function checkPatentForm(obj){
 		var inventer = obj.inventer;
 		var inventerMsg = document.getElementById("inventerMsg");
 		
 		var name = obj.name;
 		var nameMsg = document.getElementById("nameMsg");
 		
 		var type = obj.type
 		var typeMsg = document.getElementById("typeMsg");
 		
 		var number = obj.number;
 		var numberMsg = document.getElementById("numberMsg");
 		
 		var inventerFlag = checkField(inventer,inventerMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		var nameFlag = checkField(name,nameMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		var typeFlag = checkField(type,typeMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		var numberFlag = checkField(number,numberMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		//alert(inventerFlag+"---"+nameFlag+"---"+typeFlag+"---"+numberFlag);
 		return inventerFlag && nameFlag && typeFlag && numberFlag;
 	}
 	
 	function checkHonorForm(obj){
 		var name = obj.name;
 		var nameMsg = document.getElementById("honorNameMsg");
 		
 		var reason = obj.reason;
 		var reasonMsg = document.getElementById("reasonMsg");
 		
 		var nameFlag = checkField(name,nameMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		var reasonFlag = checkField(reason,reasonMsg,/^([\u4e00-\u9fa5]+)|(\w+)$/,"内容不能为空！","只能是字母数字或汉字");
 		return nameFlag && reasonFlag;
 	}
 	
</script>
<div style="text-align: center;">
 <div style="width:500px; text-align:left;">	
	<table width=500 border="0"><tr><td align=left>论文</td><td align=right><a href="javascript:void(0)" onclick="hiddenThesis()">收起</a> | <a  href="javascript:void(0)" onclick="showThesis()">展开</a></td></tr></table>
	<hr>
	<div id="thesis" style="display:block">
		<table width="500" border="0" cellspacing="0" cellpadding="5">
			<c:forEach items="${thesis}" var="thesis">
				<tr><td width=500 align="left">${thesis.content}</td></tr>
				<tr><td align="right">修改 | <a href='<c:url value="/admin/teacher/achievement/thesis/deleThesis?id=${thesis.id}"></c:url>'>删除</a></td></tr>
			</c:forEach>
		</table>
		<div id="thesisForm" style="display:none;">
			<form action="thesis/addThesis" method="post" onSubmit="return checkThesisForm(this)" >  
				内容：<textarea style="width:450px;" name="content"  cols="40" rows="3" id="thesisContent"></textarea>
				<span class="help-block" style="color:red;font-size:13px;" id="thesisContentMsg"></span>
				<button type="submit" class="btn" >OK</button>
				<button class="btn" type="reset" onclick="hiddenThesisAddForm()">CANCEL</button>
			</form>
		</div>
		<div id="thesisButton" style="display:block"><button  onclick="showThesisAddForm()">添加</button></div>
	</div>
 </div>
 <br>
	 <div style="width:500px; text-align:left;">
		<table width="500" border="0"><tr><td align="left">项目</td><td align="right"><a href="javascript:void(0)" onclick="hiddenProject()">收起</a> | <a href="javascript:void(0)" onclick="showProject()">展开</a></td></tr></table>
		<hr>
		<div id="project" style="display:block">
			<div id="projectForm" style="display:none;">
				<form action="project/addProject" method="post" onSubmit="return checkProjectForm(this)">  
					<table width="500" border="0" cellspacing="0" cellpadding="5">
						<tr><td align="center">项目名称</td><td align="center">项目来源</td><td align="center">项目起止时间</td></tr>
					<tr>
						<td  align="center"><input type="text" name="title"/>
						<span class="help-block" style="color:red;font-size:13px;" id="titleMsg"></span>
						</td>
						<td  align="center"><input type="text" name="source"/>
						<span class="help-block" style="color:red;font-size:13px;" id="sourceMsg"></span>
						</td>
						<td  align="center"><input type="text" name="date"/><span id="dateMsg" style="color:red; font-size:13px;">2000.01.01-2001.01.01</span></td>
					</tr>
					</table>
					<button type="submit" class="btn" >OK</button>
					<button class="btn" type="reset" onclick="hiddenProjectAddForm()">CANCEL</button>
				</form>
			</div>
			<div id="projectButton" style="display:block">
				<table width="500" border="0" cellspacing="0" cellpadding="5">
			<tr><td align="center">项目名称</td><td align="center">项目来源</td><td align="center">项目起止时间</td></tr>
				<c:forEach items="${project}" var="project">
					<tr>
					<td width="500" align="center">${project.title}</td>
					<td width="500" align="center">${project.source}</td>
					<td width="500" align="center">${project.date}</td>
					</tr>
					<tr><td colspan="3" align="right">修改 |  <a href='<c:url value="/admin/teacher/achievement/project/deleProject?id=${project.id}"></c:url>'>删除</a></td></tr>
				</c:forEach>
			</table>
			<button onclick="showProjectAddForm()">添加</button></div>
		</div>
	 </div>
	 
	  <br>
		 <div style="width:500px; text-align:left;">
		<table width="500" border="0"><tr><td align="left">专利</td><td align="right"><a href="javascript:void(0)" onclick="hiddenPatent()">收起</a> | <a href="javascript:void(0)" onclick="showPatent()">展开</a></td></tr></table>
		<hr>
		<div id="patent" style="display:block">
			<div id="patentForm" style="display:none;">
				<form action="patent/addPatent" method="post" onSubmit="return checkPatentForm(this)">  
					<table  border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td align="center">发明人</td><td align="center">专利名称</td>
							<td align="center">专利类型</td><td align="center">专利申请号</td>
						</tr>
						<tr>
							<td  align="center"><input type="text" name="inventer"/>
							<span class="help-block" style="color:red;font-size:13px;" id="inventerMsg"></span>
							</td>
							<td  align="center"><input type="text" name="name"/>
							<span class="help-block" style="color:red;font-size:13px;" id="nameMsg"></span>
							</td>
							<td  align="center"><input type="text" name="type"/>
							<span class="help-block" style="color:red;font-size:13px;" id="typeMsg"></span>
							</td>
							<td  align="center"><input type="text" name="number"/>
							<span class="help-block" style="color:red;font-size:13px;" id="numberMsg"></span>
							</td>
						</tr>
					</table>
					<button type="submit" class="btn">OK</button>
					<button class="btn" type="reset" onclick="hiddenPatentAddForm()">CANCEL</button>
				</form>
			</div>
			<div id="patentButton" style="display:block">
				<table width="500" border="0" cellspacing="0" cellpadding="5">
					<tr><td align="center">发明人</td><td align="center">专利名称</td><td align="center">专利类型</td><td align="center">专利申请号</td></tr>
					<c:forEach items="${patent}" var="patent">
						<tr>
							<td  align="center">${patent.inventer}</td>
							<td  align="center">${patent.name}</td>
							<td  align="center">${patent.type}</td>
							<td  align="center">${patent.number}</td>
						</tr>
						<tr><td colspan="4" align="right">修改 |  <a href='<c:url value="/admin/teacher/achievement/patent/delePatent?id=${patent.id}"></c:url>'>删除</a></td></tr>
					</c:forEach>
			</table>
			<button onclick="showPatentAddForm()">添加</button></div>
		</div>
	 </div>
	
	  <br>
		 <div style="width:500px; text-align:left;">
		<table width="500" border="0"><tr><td align="left">荣誉和奖项</td><td align="right"><a href="javascript:void(0)" onclick="hiddenPatent()">收起</a> | <a href="javascript:void(0)" onclick="showPatent()">展开</a></td></tr></table>
		<hr>
		<div id="honor" style="display:block">
			<div id="honorForm" style="display:none;">
				<form action="honor/addHonor" method="post" onSubmit="return checkHonorForm(this)">  
					<table  border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td align="center">奖励或荣誉</td><td align="center">获奖原因</td>
						
						</tr>
						<tr>
							<td  align="center"><input type="text" name="name"/>
							<span class="help-block" style="color:red;font-size:13px;" id="honorNameMsg"></span>
							</td>
							<td  align="center"><input type="text" name="reason"/>
							<span class="help-block" style="color:red;font-size:13px;" id="reasonMsg"></span>
							</td>
						</tr>
					</table>
					<button type="submit" class="btn">OK</button>
					<button class="btn" type="reset" onclick="hiddenHonorAddForm()">CANCEL</button>
				</form>
			</div>
			<div id="honorButton" style="display:block">
				<table width="500" border="0" cellspacing="0" cellpadding="5">
					<tr><td align="center">奖励或荣誉</td><td align="center">获奖原因</td></tr>
					<c:forEach items="${honor}" var="honor">
						<tr>
							<td  align="center">${honor.name}</td>
							<td  align="center">${honor.reason}</td>
						
						</tr>
						<tr><td colspan="4" align="right">修改 |  <a href='<c:url value="/admin/teacher/achievement/honor/deleHonor?id=${honor.id}"></c:url>'>删除</a></td></tr>
					</c:forEach>
			</table>
			<button onclick="showHonorAddForm()">添加</button></div>
		</div>
	 </div>

</div>