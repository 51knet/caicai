package com.knet51.ccweb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.StudentService;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		if (userInfo != null && !(userInfo.getUser().getEmail().equals(""))) {
			String id = userInfo.getUser().getId().toString();
			return "forward:/" + id;
		}

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String userHome(@PathVariable String id, HttpSession session) {
		User user;
//		boolean isHomePage = false;
		try {
			user = userService.findOne(Long.parseLong(id));
//			UserInfo userInfo = (UserInfo) session.getAttribute("user");
//			if (userInfo != null
//					&& userInfo.getUser().getId() == Long.parseLong(id)) {
//				isHomePage = true;
//			}
			String role = user.getRole();
			if (role.equals("user")) {
				// TODO: change to user front page;
				return "userHomePage";
			} else if (role.equals("teacher")) {
				return "teacherFrontPage";
			} else if (role.equals("student")) {
				// TODO: change to student front page;
				return "studentHomePage";
			} else {
				return "home";
			}
		} catch (Exception e) {
			// TODO: refining exception later;
			return "404";
		}

	}

}
