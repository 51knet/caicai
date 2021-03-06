package com.knet51.diplomat.controllers.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.TeacherService;
import com.knet51.diplomat.jpa.services.UserService;

@Controller
public class UserTypeController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserTypeController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "/user/dispatcher", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String userType(HttpServletRequest request, HttpSession session) {
		String userType;
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		userType = userInfo.getRole();
		logger.info("### user type is " + userType + " ###");
		if (userType.equals("user")) {
			return "redirect:/admin";
		} else if (userType.equals("teacher")) {
			return "redirect:/teacher/dispatcher";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = "/teacher/dispatcher", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String teacher(HttpServletRequest request, HttpSession session) {
		String userType;
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userService.findOne(userInfo.getId());
		userType = userInfo.getRole();
		logger.info("### user type is " + userType + " ###");
		if (userType.equals("teacher")) {
			Teacher teacher = new Teacher(user);
			teacher = teacherService.createTeacher(teacher);
			userInfo.setUser(user);
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			return "redirect:/admin";
		} else if (userType.equals("user")) {
			return "redirect:/user/dispatcher";
		} else {
			return "home";
		}
	}

}
