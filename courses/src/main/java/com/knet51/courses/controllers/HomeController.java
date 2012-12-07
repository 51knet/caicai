package com.knet51.courses.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.courses.beans.TeacherCourseBeans;
import com.knet51.courses.jpa.services.TeacherCourseService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private TeacherCourseService courseService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		List<TeacherCourseBeans> tcBeanList = courseService.getAllTeacherCourseBeans();
		List<String> courseSchoolList = courseService.getAllSchool();
		List<Teacher> courseTeacher = courseService.getAllCourseTeacher(null);
		model.addAttribute("schoolList", courseSchoolList);
		model.addAttribute("tcBeanList", tcBeanList);
		model.addAttribute("teacher", courseTeacher);
		return "home";
	}
}
