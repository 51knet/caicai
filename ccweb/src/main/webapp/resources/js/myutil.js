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

