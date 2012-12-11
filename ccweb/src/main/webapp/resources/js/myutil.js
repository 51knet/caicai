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
		var thesis = document.getElementById("thesisForm");
		var thesisButton = document.getElementById("thesisButton");
		thesis.style.display = "block";
		thesisButton.style.display = "none";
	}

	function hiddenThesisAddForm() {
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
		var titleMsg = document.getElementById("titleMsg");
		var sourceMsg = document.getElementById("sourceMsg");
		var dateMsg = document.getElementById("dateMsg");
		titleMsg.innerHTML="";
		sourceMsg.innerHTML="";
		dateMsg.innerHTML="2000.01.01-2001.01.01";
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

