$(function() {
	$("#registerbtn").click(function() {
		$(".login-panel").hide(500);
		$(".register-panel").show(500);
		return false;
	});
	$("#loginbtn").click(function() {
		$(".login-panel").show(500);
		$(".register-panel").hide(500);
		return false;
	});
	$("#fogotPswbtn").click(function() {
		$(".forgotPsw-panel").show(500);
		$(".login-panel").hide(500);
		$(".register-panel").hide(500);
		return false;
	});
	$("#tologinbtn").click(function() {
		$(".login-panel").show(500);
		$(".forgotPsw-panel").hide(500);
		return false;
	});
	
	$("#psw").blur(function (){
		var psw=$("#psw").val();
		if(psw==""){
			$("#emptyPwd").html("<font color='#ff0000'>密码不能为空</font>");
			return false;
		}
	});
	
	
	$("#confirmpsw").blur(function(){
		var psw=$("#psw").val();
		var confirmpsw=$("#confirmpsw").val();
		if(confirmpsw==""){
			$("#passwordError").html("<font color='#ff0000'>确认密码不能为空</font>");
			return false;
		}
		if(psw!=confirmpsw){
			$("#passwordError").html("<font color='#ff0000'>两次输入的密码不一致,请重新输入</font>");
			return false;
		}
	});
	$("#psw").focus(function(){
		$("#emptyPwd").html("");
		return false;
	});
	
	$("#confirmpsw").focus(function(){
		$("#passwordError").html("");
		return false;
	});
	
	$("#emails").focus(function(){
		$("#checkEmails").html("");
		return false;
	});
	
	$("#emails").blur(function(){
		var email=$("#emails").val();
		if(email==""){
			$("#checkEmails").html("<font color='#ff0000'>邮箱不能为空,请输入邮箱</font>");
			return false;
		}
		var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;//邮箱验证正则表达式。 
		if(!reg.test(email)){                             //验证邮箱格式是否正确
			$("#checkEmails").html("<font color='#ff0000'>输入的邮箱格式不正确</font>");
			return false;
		}
		$.ajax({
		  type: "post",
		  url: "register/email",
		  data: "email="+email,
		  dataType:"text",
		  success:function(num){
				if(num=='1'){
				$("#checkEmails").html("<font color='#ff0000'>此邮箱地址已存在</font>");
				return false;
				}else{
					$("#checkEmails").html("");
					return false;
				}
			}
		});
	});
	
	$("#email").focus(function(){
		$("#emailErrors").html("");
		return false;
	});
	$("#email").blur(function(){
		var email=$("#email").val();
		if(email==""){
			$("#emailErrors").html("<font color='#ff0000'>邮箱不能为空</font>");
			return false;
		}
		var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;//邮箱验证正则表达式。 
		if(!reg.test(email)){                             //验证邮箱格式是否正确
			$("#emailErrors").html("<font color='#ff0000'>输入的邮箱格式不正确</font>");
			return false;
		}
	});
	
	$("#emailForPsw").blur(function(){
		var email=$("#emailForPsw").val();
		if(email==""){
			$("#emailError").html("<font color='#ff0000'>邮箱不能为空</font>");
			return false;
		}
		var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;//邮箱验证正则表达式。 
		if(!reg.test(email)){                             //验证邮箱格式是否正确
			$("#emailError").html("<font color='#ff0000'>输入的邮箱格式不正确</font>");
			return false;
		}
		$.ajax({
			  type: "post",
			  url: "register/email",
			  data: "email="+email,
			  dataType:"text",
			  success:function(num){
					if(num=='0'){
					$("#emailError").html("<font color='#ff0000'>此邮箱地址不存在</font>");
					return false;
					}else{
						$("#emailError").html("");
						return false;
					}
				}
			});
	});
	
	$("#emailForPsw").focus(function(){
		$("#emailError").html("");
		return false;
	});
	$("#password").focus(function(){
		$("#passwordErr").html("");
		return false;
	});
	
	$("#password").blur(function(){
		var email=$("#email").val();
		var password=$("#password").val();
		/* if(email==""){
			$("#emailError").html("<font color='#ff0000'>邮箱不能为空</font>");
			return false;
		} */
		if(password==""){
			$("#passwordErr").html("<font color='#ff0000'>密码不能为空</font>");
			return false;
		}
		$.ajax({
			  type: "post",
			  url: "checkEmailAndPassword",
			  data: "email="+email+"&password="+password,
			  dataType:"text",
			  success:function(number){
				if(number == "0"){
					$("#emailErrors").html("<font color='#ff0000'>邮箱或密码输入错误</font>");
					$("#passwordErr").html("<font color='#ff0000'>邮箱或密码输入错误");
					return false;
				}
			  }
			});
		
	});
	
	
	
	
	
});