package com.knet51.patents.controllers.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application register page.
 */
@Controller
public class RegisterPageController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterPageController.class);


	@RequestMapping(value = "/register")
	public String RegisterPage() {
		logger.info("#### into RegisterPageController ####");

			return "register";
	}
}
