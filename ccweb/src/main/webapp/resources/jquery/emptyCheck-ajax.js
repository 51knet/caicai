function collectFormData(fields) {
	var data = {};
	for ( var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}
/**
 * 对input和textarea是否为空进行验证
 * 
 * @param action
 * @param e
 * @returns {Boolean}
 */
function checkAjax(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var $textarea = $form.find('textarea');
		var data = collectFormData($inputs);
		var textdata = collectFormData($textarea);
		var dataInfo = data['title'];
		var textInfo = textdata['content'];
		var annoInfo = '{"title":"' + dataInfo + '","content": "' + textInfo
				+ '"}';
		var annoData = eval("(" + annoInfo + ")");
		$.post(action, annoData, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline')
							.html(
									"<font color='#ff0000'>" + item.message
											+ "</font>");
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;

	});
}
function checkAjaxs(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var $textarea = $form.find('textarea');
		var data = collectFormData($inputs);
		var textdata = collectFormData($textarea);
		var dataInfo = data['courseName'];
		var textInfo = textdata['courseDesc'];
		var annoInfo = '{"courseName":"' + dataInfo + '","courseDesc": "'
				+ textInfo + '"}';
		var annoData = eval("(" + annoInfo + ")");
		$.post(action, annoData, function(response) {
			alert(annoData);
			alert(action);
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html("<font color='#ff0000'>" + item.message+ "</font>");
					alert(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;

	});
}
/**
 * 对input是否为空进行验证
 * 
 * @param action
 * @param e
 * @returns {Boolean}
 */
/*function checkEmptyAjax(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var data = collectFormData($inputs);
		$.post(action, data, function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;

	});
}*/

function checkEmptyAjax(formID, actionName) {
	var flag = false;
	var $form = $('#' + formID);
	var action = actionName;
	var $inputs = $form.find('input');
	var data = collectFormData($inputs);
	$.ajax({
		type:"post",
		url : action,
		data : data,
		async:false,
		success : function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html(item.message);
				}
				flag = false;
			} else {
				flag = true;
			}
		}
	});
	// e.preventDefault();
	return flag;
	// });
}







function checkTextAjax(formID, actionName) {
	var flag = false;
	var $form = $('#' + formID);
	var action = actionName;
	var $textarea = $form.find('textarea');
	var textdata = collectFormData($textarea);
	$.ajax({
		type:"post",
		url : action,
		data : textdata,
		async:false,
		success : function(response) {
			$form.find('.modal-body').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			if (response.status == 'FAIL') {
				for ( var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					var $controlGroup = $('#' + item.fieldName);
					$controlGroup.addClass('error');
					$controlGroup.find('.help-inline').html(item.message);
				}
				flag = false;
			} else {
				flag = true;
			}
		}
	});
	// e.preventDefault();
	return flag;
	// });
}