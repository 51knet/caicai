package com.knet51.ccweb.controllers.register;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class UserTypeController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserTypeController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/usertype", method = RequestMethod.POST)
	public String userType(@RequestParam String userType) {
		logger.info("### user type is " + userType + " ###");
		
		// TODO: return to the homepage of user's type;
		return "teacherRegisterPage";
	}
}
