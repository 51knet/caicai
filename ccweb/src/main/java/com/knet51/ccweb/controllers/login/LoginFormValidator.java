package com.knet51.ccweb.controllers.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.knet51.ccweb.jpa.services.UserService;

/**
 * use jsr-303 bean validation since it's the common approach 
 * @author ehaojii
 *
 */
@Deprecated
@Component
public class LoginFormValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(LoginFormValidator.class);

	@Autowired
	private UserService userService;
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		LoginForm loginForm = (LoginForm) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required","Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required","Required field");
		//TODO? should it be a good idea to use userService here for checking login succeed or not.
	}

}
