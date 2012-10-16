package com.knet51.ccweb.controllers.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class UserTypeController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserTypeController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/dispatcher", method = {RequestMethod.POST, RequestMethod.GET})
	// public String userType(@RequestParam("userType") String userType,
	// @RequestParam("skip") String skip, HttpSession session) {
	public String userType(HttpServletRequest request, HttpSession session) {
		String userType = request.getParameter("userType");
		String skip = request.getParameter("skip");
		logger.info("### user type is " + userType + " ###");
		if (skip != null && skip.equals("skip")) {
			return "admin.user.basic";
		} else {

			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			
			User user = userService.findOne(userInfo.getId());

			if (userType.equals("teacher")) {
				user.setRole("teacher");
				userService.updateUser(user);
				return "admin.teacher.basic";
			} else if (userType.equals("student")) {
				user.setRole("student");
				userService.updateUser(user);
				return "home";
			} else if (userType.equals("enterprise")) {
				return "home";
			} else {
				return "admin.user.basic";
			}
		}
	}
}
