package com.knet51.ccweb.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application register page.
 */
@Controller
public class UserInfoPageController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInfoPageController.class);


	@RequestMapping(value = "/userInfoPage")
	public String RegisterPage() {
		logger.info("#### into UserInfoController ####");

			return "userInfoPage";
	}
}
