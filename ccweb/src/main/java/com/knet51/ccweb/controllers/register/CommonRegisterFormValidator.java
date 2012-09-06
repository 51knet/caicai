package com.knet51.ccweb.controllers.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.knet51.ccweb.jpa.services.UserService;

@Deprecated
@Component
public class CommonRegisterFormValidator implements Validator {

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return CommonRegisterForm.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "psw",
				"field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpsw",
				"field.required", "Required field");
	}

}
