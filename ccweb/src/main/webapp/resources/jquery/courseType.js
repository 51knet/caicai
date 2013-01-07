$(document).ready(function (){
		$("#courseType option[value='计算机科学与技术']").removeAttr("selected");
		var value=$("#courseType").attr("title");
		if(value=='计算机科学与技术'){
			$("#courseType option[value='计算机科学与技术']").attr("selected","selected");
			return false;
		}
		if(value=='生物'){
			$("#courseType option[value='生物']").attr("selected","selected");
			return false;
		}
		if(value=='数学'){
			$("#courseType option[value='数学']").attr("selected","selected");
			return false;
		}
		if(value=='化学'){
			$("#courseType option[value='化学']").attr("selected","selected");
			return false;
		}
		if(value=='语文'){
			$("#courseType option[value='语文']").attr("selected","selected");
			return false;
		}
		if(value=='金融'){
			$("#courseType option[value='金融']").attr("selected","selected");
			return false;
		}
		if(value=='英语'){
			$("#courseType option[value='英语']").attr("selected","selected");
			return false;
		}
		if(value=='哲学'){
			$("#courseType option[value='哲学']").attr("selected","selected");
			return false;
		}
		if(value=='其他'){
			$("#courseType option[value='其他']").attr("selected","selected");
			return false;
		}
});