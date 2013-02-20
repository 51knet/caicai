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

	function hiddenProject() {
		var project = document.getElementById("project");
		project.style.display = "none";
	}

	function showProject() {
		var project = document.getElementById("project");
		project.style.display = "block";
	}

	function hiddenPatent() {
		var patent = document.getElementById("patent");
		patent.style.display = "none";
	}

	function showPatent() {
		var patent = document.getElementById("patent");
		patent.style.display = "block";
	}

	function hiddenHonor() {
		var honor = document.getElementById("honor");
		honor.style.display = "none";
	}

	function showHonor() {
		var honor = document.getElementById("honor");
		honor.style.display = "block";
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
	
	
/*	function editEdu(id){
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
				  	document.getElementById("workDesc").value=msg.workDesc;
			  }
		});
	};*/

	
	
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
		"" != pwd ? $("#checkpwdform").css("display","block") :$("#checkpwdform").css("display","none");
		var reg = /^[a-zA-Z]+|[\d]+$/;
		if(!reg.test(pwd)){
			$("#pwdError").html("只能输入数字或字母");
			return false;
		}
		if(pwd.length>6){
			$("#pwdError").html("输入的密码长度超过6个字符");
			return false;
		}
	}
	function deleSpace(){
		$("#pwd").val($.trim($("#pwd").val()));
		var reg = /^[a-zA-Z]+|[\d]+$/;
		var pwd=$.trim($("#pwd").val());
		if(!reg.test(pwd)){
			$("#pwdError").html("只能输入数字或字母");
			return false;
		}
		if(pwd.length>6){
			$("#pwdError").html("输入的密码长度超过6个字符");
			return false;
		}
	}
	function clearHtml(){
		$("#pwdError").html("");
		return false;
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
