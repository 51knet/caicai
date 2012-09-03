package com.knet51.ccweb.controllers.login;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {   

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Locale locale, Model model, @ModelAttribute LoginForm loginForm, BindingResult result) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		new LoginFormValidator().validate(loginForm, result);
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed "+result);
			return "home";
		} else {
			boolean succeed = service.login(loginForm.getEmail(), loginForm.getPassword());
			logger.info("Login result " + succeed);
			
			return "redirect:home"; 
		}
	}

}
