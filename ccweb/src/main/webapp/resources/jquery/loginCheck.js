function collectFormData(fields) {
	var data = {};
	for ( var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val().trim();
	}
	return data;
	
}
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
	
	$("#es").focus(function(){
		$("#emailsError").html("");
		return false;
	});
	$("#ps").focus(function(){
		$("#passwordErr").html("");
		return false;
	});
	$("#em").focus(function(){
		$("#emailErrors").html("");
		return false;
	});
	$("#conf").focus(function(){
		$("#passwordError").html("");
		return false;
	});
	/*$("#conf").blur(function(){
		var psw=$("#p").val();
		var confirmpsw=$("#conf").val();
		if(psw!=confirmpsw){
			$("#passwordError").html("<font color='#ff0000'>两次输入的密码不一致,请重新输入</font>");
			return false;
		}
	});*/
});
/**
 * 验证用户登陆
 * @param formID
 * @param actionName
 */
function checkEmailAndPass(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var dataValues = collectFormData($inputs);
		$.post(action, dataValues, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html("<font color='#ff0000'>"+item.message+"</font>");
				}
			} else{
				$.ajax({
					  type: "post",
					  url: "checkLogin",
					  data:dataValues,
					  dataType:"text",
					  success:function(number){
						if(number == "0"){
							$("#passwordErr").html("<font color='#ff0000'>邮箱密码错误,或账号禁用</font>");
							return false;
						}else{
							$form.unbind('submit');
							$form.submit();
						}
					  }
					});
				
			}
		}, 'json');
		e.preventDefault();
		return false;

	});
}
/**
 * 注册验证
 * @param formID
 * @param actionName
 */
function reginsterLogin(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	var psw=$("#p").val();
	var confirmpsw=$("#conf").val();
	if(psw!=confirmpsw){
		$("#passwordError").html("<font color='#ff0000'>两次输入的密码不一致,请重新输入</font>");
		return false;
	}
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var datas = collectFormData($inputs);
		$.post(action, datas, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html("<font color='#ff0000'>"+item.message+"</font>");
				}
			} else{
				$.ajax({
					type: "post",
					url: "register/email",
					data:datas,
					dataType:"text",
					success:function(number){
						if(number == "1"){
							$("#passwordError").html("<font color='#ff0000'>邮箱已存在</font>");
							return false;
						}else{
							$form.unbind('submit');
							$form.submit();
						}
					}
				});
				
				
				
			}
		}, 'json');
		e.preventDefault();
		return false;
		
	});
}

function thirdPartyRegister(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	var psw=$("#p").val();
	var confirmpsw=$("#conf").val();
	if(psw!=confirmpsw){
		$("#passwordError").html("<font color='#ff0000'>两次输入的密码不一致,请重新输入</font>");
		return false;
	}
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var datas = collectFormData($inputs);
		$.post(action, datas, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline-third').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline-third').html("<font color='#ff0000'>"+item.message+"</font>");
				}
			} else{
				$.ajax({
					type: "post",
					url: "register/thirdPartyEmail",
					data:datas,
					dataType:"text",
					success:function(number){
						if(number == "1"){
							$("#passwordError").html("<font color='#ff0000'>邮箱已存在</font>");
							return false;
						}else{
							$form.unbind('submit');
							$form.submit();
						}
					}
				});
				
				
				
			}
		}, 'json');
		e.preventDefault();
		return false;
		
	});
}
/***
 * 发送邮件验证
 * @param formID
 * @param actionName
 */
function forgetPwd(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var datas = collectFormData($inputs);
		$.post(action, datas, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html("<font color='#ff0000'>"+item.message+"</font>");
				}
			} else{
				$.ajax({
					type: "post",
					url: "forgetPwdAjax",
					data:datas,
					dataType:"text",
					success:function(number){
						if(number == "0"){
							$("#emailError").html("<font color='#ff0000'>邮箱不存在</font>");
							return false;
						}else{
							$form.unbind('submit');
							$form.submit();
						}
					}
				});
				
			}
		}, 'json');
		e.preventDefault();
		return false;
		
	});
}
/**
 * 密码修改
 * @param formID
 * @param actionName
 */
function modifyPwd(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	var psw=$("#alter_new_psw").val();
	var confirmpsw=$("#alter_confirm_new_psw").val();
	if(psw!=confirmpsw){
		$("#errorPs").html("<font color='#ff0000'>两次输入的密码不一致,请重新输入</font>");
		return false;
	}
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var datas = collectFormData($inputs);
		$.post(action, datas, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html("<font color='#ff0000'>"+item.message+"</font>");
				}
			} else{
				$.ajax({
					type: "post",
					url: "pswInfoCheck",
					data:datas,
					dataType:"text",
					success:function(num){
						if(num=='0'){
							$("#oriError").html("<font color='#ff0000'>" + "输入的密码不正确"
									+ "</font>");
							return false;
						}else{
							$form.unbind('submit');
							$form.submit();
						}
					}
				});
				
			}
		}, 'json');
		e.preventDefault();
		return false;
		
	});
}
