package com.knet51.patents.util.ajax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class AjaxValidationEngine {

	/**
	 * Example Usage:
	 * 
	 * <pre>
	 * &#064;Controller
	 * class UserController {
	 * 	&#064;RequestMapping(value = &quot;/userAjaxCheck.json&quot;, method = RequestMethod.POST)
	 * 	public @ResponseBody ValidationResponse processFormAjaxJson(Model model,
	 * 			&#064;ModelAttribute(value = &quot;user&quot;) @Valid User user,
	 * 			BindingResult result) {
	 * 		return AjaxValidationEngine.process(result);
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @param result
	 * @return
	 */
	public static ValidationResponse process(BindingResult result) {
		ValidationResponse res = new ValidationResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
		} else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);
		}

		return res;
	}
}