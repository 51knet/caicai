function collectFormData(fields) {
	var data = {};
	for ( var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val().trim();
	}
	return data;
}
function checkAjaxs(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('textarea');
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
			}else{
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');

		e.preventDefault();
		return false;

	});
}
