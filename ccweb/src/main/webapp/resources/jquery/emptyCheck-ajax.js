function collectFormData(fields) {
	var data = {};
	for ( var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}
function checkAjax(action,e) {
	var $inputs = $form.find('input');
	var $textarea = $form.find('textarea');
	var data = collectFormData($inputs);
	var textdata = collectFormData($textarea);
	var dataInfo = data['title'];
	var textInfo = textdata['content'];
	var annoInfo = '{"title":"' + dataInfo + '","content": "' + textInfo + '"}';
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
				$controlGroup.find('.help-inline').html(
						"<font color='#ff0000'>" + item.message + "</font>");
			}
		} else {
			$form.unbind('submit');
			$form.submit();
		}
	}, 'json');

	e.preventDefault();
	return false;
};

