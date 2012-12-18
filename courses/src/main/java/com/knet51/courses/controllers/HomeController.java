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
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.beans.TeacherCourseBeans;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private TeacherCourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserCourseService userCourseService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		logger.info("###### into the HomeController ######");
		User user = new User();
		user.setId(2L);
		session.setAttribute("userInfo", user);
		//List<TeacherCourseBeans> tcBeanList = courseService.getAllTeacherCourseBeans();
		List<TeacherCourse> courseList = courseService.findAllCourses();
		//List<String> courseSchoolList = courseService.getAllSchool();
		List<Teacher> teacherList = teacherService.findAllTeacher();
		//model.addAttribute("schoolList", courseSchoolList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		model.addAttribute("teacherList", teacherList);
		User currentUser = (User) session.getAttribute("userInfo");
		if(currentUser !=null){
			List<TeacherCourse> userCourse = userCourseService.getCourseByUserId(currentUser.getId());
			model.addAttribute("userCourse", userCourse);
		}
		
		return "home";
	}
}
