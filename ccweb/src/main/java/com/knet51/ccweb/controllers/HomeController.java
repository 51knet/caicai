package com.knet51.ccweb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.TeacherService;
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
	private TeacherService teacherService;

	@Autowired
	private AnnouncementService announcementService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		if (userInfo != null && userInfo.getRole().equals("user")) {
			String id = userInfo.getId().toString();
			return "redirect:/user/" + id;
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			String id = userInfo.getId().toString();
			return "redirect:/teacher/" + id;
		} else if (userInfo != null && userInfo.getRole().equals("student")) {
			String id = userInfo.getId().toString();
			return "redirect:/student/" + id;
		}

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user/{id}")
	public String userFront(@PathVariable String id, HttpSession session,
			Model model) {
		User user;
		UserInfo userInfo;
		try {
			user = userService.findOne(Long.parseLong(id));
			userInfo = new UserInfo(user);
			model.addAttribute("userInfoModel", userInfo);

			String role = user.getRole();
			if (role.equals("user")) {
				return "user.basic";
			} else if (role.equals("teacher")) {
				return "redirect:/teacher/" + id;
			} else {
				// TODO: student and other role;
				return "404";
			}
		} catch (Exception e) {
			// TODO: refining exception;
			return "404";
		}

	}

	@RequestMapping(value = "/teacher/{id}")
	public String teacherFront(@PathVariable String id, Model model) {
		User user;
		UserInfo userInfo;
		Announcement announcement;
		try {
			user = userService.findOne(Long.parseLong(id));
			announcement = announcementService.findLatestByUid(Long
					.parseLong(id));
			userInfo = new UserInfo(user);
			userInfo.setAnnouncement(announcement);
			model.addAttribute("userInfoModel", userInfo);
			model.addAttribute("annContext", userInfo.getAnnouncementContext());
			model.addAttribute("photoUrl", userInfo.getPhotoUrl());

			String role = user.getRole();
			if (role.equals("teacher")) {
				return "teacher.basic";
			} else if (role.equals("user")) {
				return "redirect:/user/" + id;
			} else {
				// TODO: student and other role;
				return "404";
			}
		} catch (Exception e) {
			// TODO: refining exception;
			return "404";
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "redirect:/admin/user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			return "redirect:/admin/teacher";
		} else {
			return "home";
		}

	}
	
	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String adminUser(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "admin.user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			return "redirect:/admin/teacher";
		} else {
			return "home";
		}

	}
	
	@RequestMapping(value = "/admin/teacher", method = RequestMethod.GET)
	public String adminTeacher(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "redirect:/admin/user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			return "admin.teacher";
		} else {
			return "home";
		}

	}
}
