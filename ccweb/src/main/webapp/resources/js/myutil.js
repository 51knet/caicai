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

	function hiddenThesis() {
		var thesis = document.getElementById("thesis");
		thesis.style.display = "none";
	}

	function showThesis() {
		var thesis = document.getElementById("thesis");
		thesis.style.display = "block";
	}

	function showThesisAddForm() {
		$("#thesis").css("display","none");
		var thesis = document.getElementById("thesisForm");
		var thesisButton = document.getElementById("thesisButton");
		thesis.style.display = "block";
		thesisButton.style.display = "none";
	}

	function hiddenThesisAddForm() {
		$("#thesis").css("display","block");
		var thesisForm = document.getElementById("thesisForm");
		var thesisButton = document.getElementById("thesisButton");
		var thesisContentMsg = document.getElementById("thesisContentMsg");
		thesisContentMsg.innerHTML="";
		thesisForm.style.display = "none";
		thesisButton.style.display = "block";
	}

	function hiddenProject() {
		var project = document.getElementById("project");
		project.style.display = "none";
	}

	function showProject() {
		var project = document.getElementById("project");
		project.style.display = "block";
	}

	function showProjectAddForm() {
		var project = document.getElementById("projectForm");
		var projectButton = document.getElementById("projectButton");
		project.style.display = "block";
		projectButton.style.display = "none";
	}

	function hiddenProjectAddForm() {
		var projectForm = document.getElementById("projectForm");
		var projectButton = document.getElementById("projectButton");
		projectForm.style.display = "none";
		projectButton.style.display = "block";
	}

	function hiddenPatent() {
		var patent = document.getElementById("patent");
		patent.style.display = "none";
	}

	function showPatent() {
		var patent = document.getElementById("patent");
		patent.style.display = "block";
	}

	function showPatentAddForm() {
		var patent = document.getElementById("patentForm");
		var patentButton = document.getElementById("patentButton");
		patent.style.display = "block";
		patentButton.style.display = "none";
	}

	function hiddenPatentAddForm() {
		var patentForm = document.getElementById("patentForm");
		var patentButton = document.getElementById("patentButton");
		var inventerMsg = document.getElementById("inventerMsg");
		var nameMsg = document.getElementById("nameMsg");
		var typeMsg = document.getElementById("typeMsg");
		var numberMsg = document.getElementById("numberMsg");
		patentForm.style.display = "none";
		patentButton.style.display = "block";
		inventerMsg.innerHTML="";
		nameMsg.innerHTML="";
		typeMsg.innerHTML="";
		numberMsg.innerHTML="";
	}

	function hiddenHonor() {
		var honor = document.getElementById("honor");
		honor.style.display = "none";
	}

	function showHonor() {
		var honor = document.getElementById("honor");
		honor.style.display = "block";
	}

	function showHonorAddForm() {
		var honor = document.getElementById("honorForm");
		var honorButton = document.getElementById("honorButton");
		honor.style.display = "block";
		honorButton.style.display = "none";
	}

	function hiddenHonorAddForm() {
		var honorForm = document.getElementById("honorForm");
		var honorButton = document.getElementById("honorButton");
		honorForm.style.display = "none";
		honorButton.style.display = "block";
	}

	function checkThesisForm(obj) {
		var content = obj.content;
		var thesisContentMsg = document.getElementById("thesisContentMsg");
		//alert(content.innerHTML);
		var thesisContentFlag = checkField(content, thesisContentMsg,
				/^([\u4e00-\u9fa5]+)|(\w+)$/, "内容不能为空！", "只能是字母数字或汉字");
		return thesisContentFlag;
	}

	function checkProjectForm(obj) {
		var title = obj.title;
		var titleMsg = document.getElementById("titleMsg");
		var source = obj.source;
		var sourceMsg = document.getElementById("sourceMsg");
		var date = obj.date;
		var dateMsg = document.getElementById("dateMsg");

		var titleFlag = checkField(title, titleMsg,
				/^([\u4e00-\u9fa5]+)|(\w+)$/, "内容不能为空！", "只能是字母数字或汉字");
		var sourceFlag = checkField(source, sourceMsg,
				/^([\u4e00-\u9fa5]+)|(\w+)$/, "内容不能为空！", "只能是字母数字或汉字");
		var dateFlag = checkField(date, dateMsg,"", "不能为空！", "只能是数字！");
		return titleFlag && sourceFlag && dateFlag;
	}

	function checkPatentForm(obj) {
		var inventer = obj.inventer;
		var inventerMsg = document.getElementById("inventerMsg");

		var name = obj.name;
		var nameMsg = document.getElementById("nameMsg");

		var type = obj.type;
		var typeMsg = document.getElementById("typeMsg");

		var number = obj.number;
		var numberMsg = document.getElementById("numberMsg");

		var inventerFlag = checkField(inventer, inventerMsg,
				/^([\u4e00-\u9fa5]+)|(\w+)$/, "内容不能为空！", "只能是字母数字或汉字");
		var nameFlag = checkField(name, nameMsg, /^([\u4e00-\u9fa5]+)|(\w+)$/,
				"内容不能为空！", "只能是字母数字或汉字");
		var typeFlag = checkField(type, typeMsg, /^([\u4e00-\u9fa5]+)|(\w+)$/,
				"内容不能为空！", "只能是字母数字或汉字");
		var numberFlag = checkField(number, numberMsg,
				/^([\u4e00-\u9fa5]+)|(\w+)$/, "内容不能为空！", "只能是字母数字或汉字");
		//alert(inventerFlag+"---"+nameFlag+"---"+typeFlag+"---"+numberFlag);
		return inventerFlag && nameFlag && typeFlag && numberFlag;
	}

	function checkHonorForm(obj) {
		var name = obj.name;
		var nameMsg = document.getElementById("honorNameMsg");

		var reason = obj.reason;
		var reasonMsg = document.getElementById("reasonMsg");

		var nameFlag = checkField(name, nameMsg, /^([\u4e00-\u9fa5]+)|(\w+)$/,
				"内容不能为空！", "只能是字母数字或汉字");
		var reasonFlag = checkField(reason, reasonMsg,
				/^([\u4e00-\u9fa5]+)|(\w+)$/, "内容不能为空！", "只能是字母数字或汉字");
		return nameFlag && reasonFlag;
	}
	
	function workOnclick(){
		return checkEmptyAjax("workExpForm","workExpInfoAJAX");
	};
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
	
	
	function editEdu(id){
		//alert(id);
		$("#eduList").css("display","none");
		$("#eduForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "eduInfo/edit/ajax",
			  data: "eduId="+id,
			  dataType:"json",
			  success:function(msg){
					document.edu.eduId.value = msg.id;
				  	document.edu.schoolName.value=msg.school;
				  	document.edu.collegeName.value=msg.college;
				  	document.edu.degree.value=msg.degree;
				  	document.edu.startTime.value=msg.startTime;
				  	document.edu.endTime.value=msg.endTime;
				  	document.getElementById("educationDesc").value=msg.educationDesc;
			  }
		});
	};

	function editWork(id){
		//alert(id);
		$("#workList").css("display","none");
		$("#workForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "workInfo/edit/ajax",
			  data: "workId="+id,
			  dataType:"json",
			  success:function(msg){
					document.work.workId.value = msg.id;
				  	document.work.company.value=msg.company;
				  	document.work.department.value=msg.department;
				  	document.work.position.value=msg.position;
				  	document.work.startTimeName.value=msg.startTime;
				  	document.work.endTimeName.value=msg.endTime;
				  	document.getElementById("workDesc").value=msg.educationDesc;
			  }
		});
	};

	
	
	function checkPicture(obj){
		var imgflag = false;
		//var nameflag = true;
		//var descflag = true;
		var fileValue = obj.coverFile.value;
		var temp = fileValue.substr(fileValue.indexOf('.'),fileValue.length);
		if(".gif"==temp || ".jpg"==temp || ".bmp"==temp){
			imgflag=true;
		}else{
			alert("只支持gif、jpg、bmp格式的图片！！");
			imgflag=false;
		}
		/*var nameValue = $.trim($("#names").val());
		if(""==nameValue){
			alert("课程名称不能为空！");
			nameflag = false; 
		}
		var descValue = $.trim($("#descs").val());
		if(""==descValue){
			alert("课程描述不能为空！");
			descflag = false; 
		}*/
		return imgflag;
	}

	function showCheckpwd(){
		$("#pwd").val($.trim($("#pwd").val()));
		var pwd = $.trim($("#pwd").val());
		"" != pwd ? $("#checkpwdform").css("display","block") :$("#checkpwdform").css("display","none")
	}
	
	function deleSpace(){
		$("#pwd").val($.trim($("#pwd").val()));
	}
	function checkPwd(){
		var flag = true;
		var pwd = $("#pwd").val();
		var checkpwd = $("#checkpwd").val();
		if(pwd != checkpwd){
			alert("两次输入的密码不一致，请重新输入！");
			$("#checkpwd").val("");
			flag = false;
		}
		return flag;
	}
