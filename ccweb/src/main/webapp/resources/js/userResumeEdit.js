$(document).ready(function() {
	$('.edituserEduAjaxBtn').on('click', function() {
		var edu_id = $(this).next().val();
		$("#usereduList").css("display","none");
		$("#usereduDiv").css("display","block");
		$.ajax({
			  type: "post",
			  url: "eduInfo/edit/ajax",
			  data: "eduId="+edu_id,
			  dataType:"json",
			  success:function(msg){
				  var useredu = null;
				  if(msg.level<=4 && msg.level >=0){
					  useredu = userLowEduForm;
				  }else if(msg.level<=6 && msg.level >4){
					  useredu = userHighEduForm;
				  }
					document.useredu.eduId.value = msg.id;
				  	document.useredu.schoolName.value=msg.school;
				  	document.useredu.collegeName.value=msg.college;
				  	document.useredu.degree.value=msg.degree;
				  	document.useredu.startTime.value=msg.startTime;
				  	document.useredu.endTime.value=msg.endTime;
			  }
		});
	});
	
	$('.edituserWorkAjaxBtn').on('click', function() {
		var work_id = $(this).next().val();
		$("#userworkList").css("display","none");
		$("#userworkForm").css("display","block");
		$.ajax({
			  type: "post",
			  url: "workInfo/edit/ajax",
			  data: "workId="+work_id,
			  dataType:"json",
			  success:function(msg){
					document.userwork.workId.value = msg.id;
					document.userwork.company.value=msg.company;
				  	document.userwork.position.value=msg.position;
				  	document.userwork.startTime.value=msg.startTime;
				  	document.userwork.endTime.value=msg.endTime;
			  }
		});
	});
	
	$("#userworkExpForm").submit(function(){
		return checkEmptyAjax("userworkExpForm","user/workExpInfoAJAX");
	});
	
	$("#user_highedu_info_form").submit(function(){
		return checkEmptyAjax("user_highedu_info_form","user/highEduInfoAJAX");
	});
	
	$("#user_lowedu_info_form").submit(function(){
		var f = checkEmptyAjax("user_lowedu_info_form","user/lowEduInfoAJAX");
		return f;
	});
	
	$('.deleteEduPostBtn').on('click', function() {
		var edu_id = $(this).next().next().val();
		$('#deleteEduPostModal #eduId').val(edu_id);	
	});
	
	$('.deleteuserWorkPostBtn').on('click', function() {
		var work_id = $(this).next().val();
		$('#deleteuserWorkPostModal #userworkId').val(work_id);	
	});
});


function showuserEduAddForm(){ 
	 var eduList = document.getElementById("usereduList"); 
	eduList.style.display="none"; 
	 var eduForm = document.getElementById("usereduDiv"); 
	 eduForm.style.display="block"; 
}; 
function closeuserEduAddForm(){ 
	 var eduList = document.getElementById("usereduList"); 
	eduList.style.display="block"; 
	 var eduForm = document.getElementById("usereduDiv"); 
	 eduForm.style.display="none"; 
	 $("#user_lowedu_info_form").css("display","none");
	 $("#user_highedu_info_form").css("display","none");
};

function showUserLowEduForm(level){
	$("#user_lowedu_info_form").css("display","block");
	$("#user_highedu_info_form").css("display","none");
};
function showUserHighEduForm(){
	$("#user_lowedu_info_form").css("display","none");
	$("#user_highedu_info_form").css("display","block");
};

function showuserWorkAddForm(){ 
	var workList = document.getElementById("userworkList"); 
	 workList.style.display="none"; 
	 var workForm = document.getElementById("userworkForm"); 
	 workForm.style.display="block"; 
}; 
function closeuserWorkAddForm(){ 
	 var workList = document.getElementById("userworkList"); 
	 workList.style.display="block"; 
	 var workForm = document.getElementById("userworkForm"); 
	 workForm.style.display="none"; 
};




