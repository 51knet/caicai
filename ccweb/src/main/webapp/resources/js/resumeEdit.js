$(document).ready(function() {
	var eduDescEditor = KindEditor.create('textarea[name="educationDesc"]',{
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
	$('.editEduAjaxBtn').on('click', function() {
		var edu_id = $(this).next().val();
		$("#eduList").css("display","none");
		$("#eduForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "eduInfo/edit/ajax",
			  data: "eduId="+edu_id,
			  dataType:"json",
			  success:function(msg){
					document.edu.eduId.value = msg.id;
				  	document.edu.schoolName.value=msg.school;
				  	document.edu.collegeName.value=msg.college;
				  	document.edu.degree.value=msg.degree;
				  	document.edu.startTime.value=msg.startTime;
				  	document.edu.endTime.value=msg.endTime;
				  	eduDescEditor.html(msg.educationDesc);
			  }
		});
	});
	$("#edu_info_form").submit(function(){
		eduDescEditor.sync();
		var KEeducationDesc =$("#KEeducationDesc").val();
		if(KEeducationDesc.length>=1000){
			alert("内容过多，请精减内容");
			return false;
		}
		return checkEmptyAjax("edu_info_form","eduInfoAJAX");
	});
	
	
	var workDescEditor = KindEditor.create('textarea[name="workDesc"]',{
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
	
	$('.editWorkAjaxBtn').on('click', function() {
		var work_id = $(this).next().val();
		$("#workList").css("display","none");
		$("#workForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "workInfo/edit/ajax",
			  data: "workId="+work_id,
			  dataType:"json",
			  success:function(msg){
					document.work.workId.value = msg.id;
				  	document.work.company.value=msg.company;
				  	document.work.department.value=msg.department;
				  	document.work.position.value=msg.position;
				  	document.work.startTimeName.value=msg.startTime;
				  	document.work.endTimeName.value=msg.endTime;
				  	workDescEditor.html(msg.workDesc);
			  }
		});
	});
	
	$("#workExpForm").submit(function(){
		workDescEditor.sync();
		var workDescs=$("#workDescs").val();
		if(workDescs.length>=1000){
			alert("内容过多，请精减内容");
			return false;
		}
		return checkEmptyAjax("workExpForm","workExpInfoAJAX");
	});
	
	var contextEditor = KindEditor.create('textarea[name="content"]',{
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
	$('.editThesisAjaxBtn').on('click', function() {
		var thesis_id = $(this).next().val();
		$("#thesisList").css("display","none");
		$("#thesisNewInfoForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "thesisInfo/edit/ajax",
			  data: "thesisId="+thesis_id,
			  dataType:"json",
			  success:function(msg){
					document.thesis.thesisId.value = msg.id;
				  	contextEditor.html(msg.content);
			  }
		});
	});
	$("#thesis_info_form").submit(function(){
		var contents=$("#contents").val().trim();
		if(contents.length>=1000){
			alert("内容过多，请精减内容");
			return false;
		}else{
			contextEditor.sync();
			return checkEmptyAjax("thesis_info_form","thesisInfoAJAX");
		}
	});
	
	var projectEditor = KindEditor.create('textarea[name="projectDesc"]',{
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
	$("#project_info_form").submit(function(){
		projectEditor.sync();
		var projectDescs=$("#projectDescs").val();
		if(projectDescs.length>=1000){
			alert("内容过多，请精减内容");
			return false;
		}
		return checkEmptyAjax("project_info_form","projectInfoAJAX");
	});
	$('.editProjectAjaxBtn').on('click', function() {
		var project_id = $(this).next().val();
		$("#projectList").css("display","none");
		$("#projectForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "projectInfo/edit/ajax",
			  data: "projectId="+project_id,
			  dataType:"json",
			  success:function(msg){
					document.project.projectId.value = msg.id;
					document.project.projectTitle.value=msg.title;
				  	document.project.projectSource.value=msg.source;
				  	document.project.projectStartTime.value=msg.startTime;
				  	document.project.projectEndTime.value=msg.endTime;
				  	projectEditor.html(msg.detailDesc);
			  }
		});
	});
	var patentEditor = KindEditor.create('textarea[name="patentDesc"]',{
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
	$("#patent_info_form").submit(function(){
		patentEditor.sync();
		var patentDescs=$("#patentDescs").val();
		if(patentDescs.length>=1000){
			alert("内容过多，请精减内容");
			return false;
		}
		return checkEmptyAjax("patent_info_form","patentInfoAJAX");
	});
	$('.editPatentAjaxBtn').on('click', function() {
		var patent_id = $(this).next().val();
		$("#patentList").css("display","none");
		$("#patentForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "patentInfo/edit/ajax",
			  data: "patentId="+patent_id,
			  dataType:"json",
			  success:function(msg){
					document.teacherPatent.patentId.value = msg.id;
					document.teacherPatent.inventer.value=msg.inventer;
				  	document.teacherPatent.patentName.value=msg.name;
				  	document.teacherPatent.patentType.value=msg.type;
				  	document.teacherPatent.number.value=msg.number;
				  	patentEditor.html(msg.detailDesc);
			  }
		});
	});
	var honorEditor = KindEditor.create('textarea[name="honorDesc"]',{
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
	$("#honor_info_Form").submit(function(){
		honorEditor.sync();
		var honorDescs=$("#honorDescs").val();
		if(honorDescs.length>=1000){
			alert("内容过多，请精减内容");
			return false;
		}
		return checkEmptyAjax("honor_info_Form","honorInfoAJAX");
	});
	$('.editHonorAjaxBtn').on('click', function() {
		var honor_id = $(this).next().val();
		$("#honorList").css("display","none");
		$("#honorForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "honorInfo/edit/ajax",
			  data: "honorId="+honor_id,
			  dataType:"json",
			  success:function(msg){
					document.teacherHonor.honorId.value = msg.id;
					document.teacherHonor.honorName.value=msg.name;
				  	document.teacherHonor.reason.value=msg.reason;
				  	honorEditor.html(msg.detailDesc);
			  }
		});
	});
	prettyPrint();
});
function personalOnclick(){
	return checkEmptyAjaxs("personal_info_form","personalInfoAJAX");
};
$(document).ready(function(){
	$("#context").focus(function(){
		$("#thesisContentMsg").html("");
	});
	$("input").focus(function(){
		$(".help-inline").html("");
	});
	// 表格里的删除按钮按下的时候，需要为对话框动态修改一些属性的值
	$('.deleteProjectPostBtn').on('click', function() {
		var project_id = $(this).next().next().val();
		$('#deleteProjectPostModal #projectId').val(project_id);	
	});
	
	$('.deleteEduPostBtn').on('click', function() {
		var edu_id = $(this).next().next().val();
		$('#deleteEduPostModal #eduId').val(edu_id);	
	});
	
	$('.deleteWorkPostBtn').on('click', function() {
		var work_id = $(this).next().next().val();
		$('#deleteWorkPostModal #workId').val(work_id);	
	});
	
	$('.deleteThesisPostBtn').on('click', function() {
		var thesis_id = $(this).next().next().val();
		$('#deleteThesisPostModal #thesisId').val(thesis_id);	
	});
	
	$('.deletePatentPostBtn').on('click', function() {
		var patent_id = $(this).next().next().val();
		$('#deletePatentPostModal #patentId').val(patent_id);	
	});
	
	$('.deleteHonorPostBtn').on('click', function() {
		var honor_id = $(this).next().next().val();
		$('#deleteHonorPostModal #honorId').val(honor_id);	
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

function showThesisAddForm() {
	$("#thesisList").css("display","none");
	$("#thesisNewInfoForm").css("display","block");
};

function hiddenThesisAddForm() {
	$("#thesisList").css("display","block");
	$("#thesisNewInfoForm").css("display","none");
};

function showProjectAddForm() {
	$("#projectList").css("display","none");
	$("#projectForm").css("display","block");
};

function hiddenProjectAddForm() {
	$("#projectList").css("display","block");
	$("#projectForm").css("display","none");
};

function showPatentAddForm() {
	$("#patentList").css("display","none");
	$("#patentForm").css("display","block");
};

function hiddenPatentAddForm() {
	$("#patentList").css("display","block");
	$("#patentForm").css("display","none");
};

function showHonorAddForm() {
	$("#honorList").css("display","none");
	$("#honorForm").css("display","block");
};

function hiddenHonorAddForm() {
	$("#honorList").css("display","block");
	$("#honorForm").css("display","none");
};

