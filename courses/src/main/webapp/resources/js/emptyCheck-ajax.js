function collectFormData(fields) {
	var data = {};
	for ( var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}

function checkAjaxs(formID, actionName) {
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
	
	
	
	
}
