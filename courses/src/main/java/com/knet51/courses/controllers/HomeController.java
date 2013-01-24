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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.beans.CourseBeans;
import com.knet51.courses.controllers.defs.GlobalDefs;
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
		//List<TeacherCourseBeans> tcBeanList = courseService.getAllTeacherCourseBeans();
		//List<TeacherCourse> courseList = courseService.findAllCourses();
		List<CourseBeans> cBeans = courseService.getAllCourseBeans();
		//List<String> courseSchoolList = courseService.getAllSchool();
		List<Teacher> teacherList = teacherService.findAllTeacher();
		//model.addAttribute("schoolList", courseSchoolList);
		model.addAttribute("courseList", cBeans);
		model.addAttribute("courseCount", cBeans.size());
		model.addAttribute("teacherList", teacherList);
		UserInfo currentUser = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(currentUser !=null){
			List<TeacherCourse> userCourse = courseService.getCourseByUserId(currentUser.getId());
			model.addAttribute("userCourse", userCourse);
			model.addAttribute("userCourseCount", userCourse.size());
		}
		
		return "home";
	}
	@RequestMapping(value = "/course/study", method = RequestMethod.GET)
	public String adminStudent(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo != null) {
			return "redirect:/course/list/type";
		} else {
			return "redirect:/signin";
		}
		
	}
}
