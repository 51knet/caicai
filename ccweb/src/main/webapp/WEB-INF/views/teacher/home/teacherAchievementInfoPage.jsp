<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
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
 
 
</script>
<div style="text-align: center;">
 <div style="width:500px; text-align:left;">	
	<table width=500 border="0"><tr><td align=left>论文</td><td align=right><a href="javascript:void(0)" onclick="hiddenThesis()">收起</a> | <a  href="javascript:void(0)" onclick="showThesis()">展开</a></td></tr></table>
	<hr>
	<div id="thesis" style="display:block">
		<table width="500" border="1" cellspacing="0" cellpadding="5">
			<c:forEach items="${thesis}" var="t">
				<tr><td width=500 align="left">${t.content}</td></tr>
				<tr><td align="right">修改 | 删除</td></tr>
			</c:forEach>
		</table>
		<div id="thesisForm" style="display:none;">
			<form:form action="teacherThesisAdd" method="post">  
				内容：<textarea style="width:450px;" name="content" placeholder="Content" cols="40" rows="3"></textarea>
				<span class="help-block"><form:errors path="Content"></form:errors></span>
				<button type="submit" class="btn" onclick="hiddenThesisAddForm()">OK</button>
				<button class="btn" type="reset" onclick="hiddenThesisAddForm()">CANCEL</button>
			</form:form>
		</div>
		<div id="thesisButton" style="display:block"><button  onclick="showThesisAddForm()">添加</button></div>
	</div>
 </div>
 <br>
	 <div style="width:500px; text-align:left;">	
		<table width="500" border="0"><tr><td align="left">论文</td><td align="right"><a href="javascript:void(0)" onclick="hiddenProject()">收起</a> | <a href="javascript:void(0)" onclick="showProject()">展开</a></td></tr></table>
		<hr>
		<div id="project" style="display:block">
			<table width="500" border="1" cellspacing="0" cellpadding="5">
				<c:forEach items="${project}" var="p">
					<tr><td width="500" align="left">${p.content}</td></tr>
					<tr><td align="right">修改 | 删除</td></tr>
				</c:forEach>
			</table>
			<div id="projectForm" style="display:none;">
				<form:form action="teacherProjectAdd" method="post">  
					内容：<textarea style="width:450px;" name="content" placeholder="Content" cols="40" rows="3"/></textarea>
					<span class="help-block"><form:errors path="Content"/></span>
					<button type="submit" class="btn" onclick="hiddenProjectAddForm()">OK</button>
					<button class="btn" type="reset" onclick="hiddenProjectAddForm()">CANCEL</button>
				</form:form>
			</div>
			<div id="projectButton" style="display:block"><button onclick="showProjectAddForm()">添加</button></div>
		</div>
	 </div>

</div>