function collectFormData(fields) {
	var data = {};
	for ( var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}

function checkAjaxs(formID, actionName) {
	$form = $('#' + formID);
	var action = actionName;
	$form.bind('submit', function(e) {
		var $inputs = $form.find('input');
		var $textarea = $form.find('textarea');
		var data = collectFormData($inputs);
		//alert($inputs);
		var textdata = collectFormData($textarea);
		var dataInfo = data['commentTitle'];
		var textInfo = textdata['commentDesc'];
		var annoInfo = '{"commentTitle":"' + dataInfo + '","commentDesc": "'
				+ textInfo + '"}';
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
					$controlGroup.find('.help-inline').html(item.message);
				}
			} else {
				$form.unbind('submit');
				/*$.ajax({
					data:$form.serializeArray(),
					type:"post",
					dataType:"json",
					url:"comment",
					success:function(data){
						String dataSum=eval(data);
						alert(1);
						if(dataSum==1){
							$("#commentError").html("<font color='#ff0000'>谢谢你的评论,你已评论过此课程</font>");
							return false;
						}else{
							
						}
					}
				});*/
				$form.submit();
			}
		}, 'json');
		e.preventDefault();
		return false;
	});
}
