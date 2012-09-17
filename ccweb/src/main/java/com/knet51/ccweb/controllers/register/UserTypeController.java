package com.knet51.ccweb.controllers.register;

import javax.servlet.http.HttpSession;

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
	public String userType(@RequestParam("userType") String userType,
			@RequestParam("skip") String skip, HttpSession session) {
		logger.info("### user type is " + userType + " ###");
		if (skip != null && skip.equals("skip")) {
			return "useInfoPage";
		} else {
			if (userType.equals("teacher")) {
				return "teacherInfoPage";
			} else if (userType.equals("student")) {
				return "studentInfoPage";
			} else if (userType.equals("enterprise")) {
				return "home";
			} else {
				return "home";
			}
		}
	}
}
